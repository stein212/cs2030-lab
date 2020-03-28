import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

public class Main {

    /**
     * Reads all timing inputs and then processes them.
     */
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        // get number of servers for this simulation
        int numberOfServers = scanner.nextInt();

        PriorityQueue<CustomerEvent> customerEvents = new PriorityQueue<>();
        int counter = 0;

        // get the customer arrival events
        while (scanner.hasNextDouble()) {
            double timing = scanner.nextDouble();
            Customer customer = new Customer(++counter, timing);

            CustomerEvent customerEvent = new CustomerEvent(
                customer,
                Server.EMPTY_SERVER,
                timing,
                CustomerStatus.ARRIVES);
            
            customerEvents.add(customerEvent);
        }

        scanner.close();
        processCustomers(numberOfServers, customerEvents);
    }

    /**
     * Processes the customers. Outputs the events of each of the customers and 
     * then the statistics after processing the customers.
     * 
     * @param customerEvents PriorityQueue of customerEvents
     */
    public static void processCustomers(
            int numberOfServers,
            PriorityQueue<CustomerEvent> customerEvents) {
        // Main string variable to store string to be output to console.
        String output = "";

        // Initialise the main server for processing customers
        List<Server> servers = IntStream.rangeClosed(1, numberOfServers)
            .mapToObj(i -> new Server(i))
            .collect(Collectors.toList());
        
        // Get all customers before the priority queue gets mutated
        int totalCustomers = customerEvents.size();

        // keep track of number of waiting customers
        Map<Integer, Integer> waitingCounters = new HashMap<>();

        servers.forEach(s -> waitingCounters.put(s.getId(), 0));

        // totalWaitingTime for calculating average waiting time later
        double totalWaitingTime = 0;

        while (!customerEvents.isEmpty()) {
            // Get the customer event that is most recent
            final CustomerEvent currentCustomerEvent = customerEvents.poll();
            System.out.println(currentCustomerEvent);

            switch (currentCustomerEvent.getStatus()) {
                case ARRIVES:
                    // Check if there is an available server
                    Server potentialServer = servers.stream()
                        .filter(s -> s
                            .canServe(currentCustomerEvent.getTiming()))
                        .findFirst()
                        .orElse(Server.EMPTY_SERVER);

                    // if there is an available server add 'SERVED' event
                    // and break
                    if (potentialServer != Server.EMPTY_SERVER) {
                        customerEvents.add(currentCustomerEvent
                            .setServer(potentialServer)
                            .setStatus(CustomerStatus.SERVED));
                        break;
                    }

                    // otherwise check if there is any server to wait at
                    potentialServer = servers.stream()
                        .filter(s -> waitingCounters.get(s.getId()) < 1)
                        .findFirst()
                        .orElse(Server.EMPTY_SERVER);

                    // if there is an available server add 'WAITS' event  
                    // and break
                    if (potentialServer != Server.EMPTY_SERVER) {
                        customerEvents.add(currentCustomerEvent
                            .setServer(potentialServer)
                            .setStatus(CustomerStatus.WAITS));
                        break;
                    }

                    // finally customer just leaves
                    customerEvents.add(currentCustomerEvent
                        .setStatus(CustomerStatus.LEAVES));
                    break;
                case SERVED:
                    // These events implies that the server is going to
                    // serve this customer. So we update the server after it
                    // serves.
                    Server updatedServer = currentCustomerEvent.getServer()
                        .serve(currentCustomerEvent.getTiming());

                    // update the server in the servers list
                    servers.set(updatedServer.getId() - 1, updatedServer);

                    // server will have the next available service timing,
                    // which is also the done timing for the customer
                    // Add the customer event back into the pq
                    customerEvents.add(currentCustomerEvent
                        .setServer(updatedServer)
                        .setTiming(updatedServer.nextServiceTiming())
                        .setStatus(CustomerStatus.DONE));
                    break;
                case WAITS:
                    // calculate the amount of time the customer waits till
                    // the server serves it
                    Server waitingServer = currentCustomerEvent.getServer();
                    totalWaitingTime += currentCustomerEvent.getServer()
                        .nextServiceTiming() - currentCustomerEvent.getTiming();

                    // since it is waiting, increment the number of waiting
                    // customers for the server.
                    waitingCounters.put(
                        waitingServer.getId(),
                        waitingCounters.get(waitingServer.getId()) + 1);

                    // The timing that the customer will be served at is the
                    // server's next service timing
                    // Add back to pq
                    customerEvents.add(currentCustomerEvent
                        .setTiming(waitingServer.nextServiceTiming())
                        .setStatus(CustomerStatus.SERVED));
                    break;
                case DONE:
                    // Decrement the number of waiting customers as it implies
                    // the next customer waiting, if any, will be served
                    Server doneServer = currentCustomerEvent.getServer();
                    int waiting = waitingCounters.get(doneServer.getId());

                    if (waiting > 0) {
                        waitingCounters.put(doneServer.getId(), waiting - 1);
                    }

                    // Nothing comes after DONE so no need to add back to pq
                    break;
                default:
                    // The only state that comes in here is LEAVES
                    // Nothing needs to be done when a customer leaves
                    break;
            }
        }

        // calculate statistics
        int numberOfServes = servers.stream()
            .mapToInt(s -> s.getNumberOfServes())
            .sum();

        double averageWaitingTime = totalWaitingTime / numberOfServes;

        output = String.format("[%.3f %d %d]",
                averageWaitingTime,
                numberOfServes,
                totalCustomers - numberOfServes);
        System.out.println(output);
    }
}
