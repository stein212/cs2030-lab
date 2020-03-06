import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {

    /**
     * Reads all timing inputs and then processes them.
     */
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        PriorityQueue<Customer> customers = new PriorityQueue<>();
        int counter = 0;

        while (scanner.hasNextDouble()) {
            double timing = scanner.nextDouble();
            Customer customer = new Customer(++counter, timing);
            customers.add(customer);
        }

        scanner.close();
        processCustomers(customers);
    }

    /**
     * Processes the customers. Outputs the events of each of the customers and then
     * the statistics after processing the customers.
     * 
     * @param customers PriorityQueue of customers
     */
    public static void processCustomers(PriorityQueue<Customer> customers) {
        // Main string variable to store string to be output to console.
        String output = "";

        // Initialise the main server for processing customers
        Server server = new Server();
        // Get all customers before the priority queue gets mutated
        int totalCustomers = customers.size();

        // keep track of number of waiting customers
        int waitingCustomers = 0;

        // totalWaitingTime for calculating average waiting time later
        double totalWaitingTime = 0;

        while (!customers.isEmpty()) {
            // Get the customer event that is most recent
            Customer currentCustomer = customers.poll();
            System.out.println(currentCustomer);

            Server updatedServer = server.serve(currentCustomer);

            switch (currentCustomer.getStatus()) {
                case ARRIVES:
                    // If number of waiting customers is > 0, it means
                    // someone is already waiting and the customer should leave
                    if (waitingCustomers > 0) {
                        currentCustomer = currentCustomer
                            .setStatus(CustomerStatus.LEAVES);
                    } else if (updatedServer == null) {
                        // if number of waiting is <= 0 but server is unable
                        // to serve, customer will wait
                        currentCustomer = currentCustomer
                            .setStatus(CustomerStatus.WAITS);
                    } else {
                        // if number of waiting is <= 0 and server is able to
                        // serve, serve the customer
                        currentCustomer = currentCustomer
                            .setStatus(CustomerStatus.SERVED);
                    }

                    // Add the customer event back into the pq
                    customers.add(currentCustomer);
                    break;
                case SERVED:
                    // These events implies that the server is going to
                    // serve this customer. So we update the server after it
                    // serves.
                    server = updatedServer;
                    // server will have the next available service timing,
                    // which is also the done timing for the customer
                    currentCustomer = currentCustomer.setTiming(server.nextServiceTiming())
                            .setStatus(CustomerStatus.DONE);

                    // Add the customer event back into the pq
                    customers.add(currentCustomer);
                    break;
                case WAITS:
                    // calculate the amount of time the customer waits till
                    // the server serves it
                    totalWaitingTime += server.nextServiceTiming() - currentCustomer.getTiming();

                    // since it is waiting, increment the number of waiting
                    // customers.
                    waitingCustomers++;

                    // The timing that the customer will be served at is the
                    // server's next service timing
                    currentCustomer = currentCustomer.setTiming(server.nextServiceTiming())
                            .setStatus(CustomerStatus.SERVED);

                    // Add back to pq
                    customers.add(currentCustomer);
                    break;
                case DONE:
                    // Decrement the number of waiting customers as it implies
                    // the next customer waiting, if any, will be served
                    if (waitingCustomers > 0) {
                        waitingCustomers--;
                    }

                    // Nothing comes after DONE so no need to add back to pq
                    break;
                default:
                    // The only state that comes in here is LEAVES
                    // Nothing needs to be done when a customer leaves
                    break;
            }
        }

        int numberOfServes = server.getNumberOfServes();
        double averageWaitingTime = totalWaitingTime / numberOfServes;
        output = String.format("[%.3f %d %d]",
                averageWaitingTime,
                numberOfServes,
                totalCustomers - numberOfServes);
        System.out.println(output);
    }
}
