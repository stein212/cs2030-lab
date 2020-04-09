package cs2030.simulator;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

public class Simulator {

    /**
     * Starts simulation with the specified parameters.
     * 
     * @param seed seed for RandomGenerator
     * @param numberOfServers number of servers to simulate
     * @param maxQueueLength maximum queue length for each server
     * @param numberOfCustomers number of customers to simulate
     * @param arrivalRate customer arrival rate
     * @param serviceRate server service rate
     */
    public void simulate(int seed, int numberOfServers, int maxQueueLength,
            int numberOfCustomers, double arrivalRate, double serviceRate,
            double restRate, double restProbability) {

        PriorityQueue<SimulatorEvent> events = new PriorityQueue<>();
        RandomGenerator rng = new RandomGenerator(seed, arrivalRate,
                serviceRate, restRate);

        double timing = 0;

        // get the customer arrival events
        for (int i = 0; i < numberOfCustomers; i++) {
            Customer customer = new Customer(i + 1, timing);

            SimulatorEvent event = new SimulatorEvent(customer,
                    Server.EMPTY_SERVER, timing, SimulatorStatus.ARRIVES);

            events.add(event);

            timing += rng.genInterArrivalTime();
        }

        processCustomers(numberOfServers, maxQueueLength, restProbability, 
                events, rng);
    }
    
    /**
     * Processes the customers.
     * Outputs the events of each of the customers and statistics after 
     * processing the customers.
     * 
     * @param numberOfServers the number of services to simulate
     * @param events the initial events
     * @param maxQueueLength the maximum queue length
     * @param rng the randomGenerator to be used
     */
    public void processCustomers(int numberOfServers, int maxQueueLength,
            double restProbability,
            PriorityQueue<SimulatorEvent> events, RandomGenerator rng) {
        // Main string variable to store string to be output to console.
        String output = "";

        // Initialise the main server for processing customers
        List<Server> servers = IntStream.rangeClosed(1, numberOfServers)
                .mapToObj(i -> new Server(i))
                .collect(Collectors.toList());

        // Get all customers before the priority queue gets mutated
        int totalCustomers = events.size();

        // keep track of number of waiting customers
        Map<Integer, Queue<Customer>> waitingCounters = new HashMap<>();

        servers.forEach(s -> waitingCounters.put(s.getId(),
                new LinkedList<Customer>()));

        // totalWaitingTime for calculating average waiting time later
        double totalWaitingTime = 0;

        while (!events.isEmpty()) {
            // Get the customer event that is most recent
            final SimulatorEvent currentEvent = events.poll();

            if (currentEvent.getStatus() != SimulatorStatus.SERVER_REST
                && currentEvent.getStatus() != SimulatorStatus.SERVER_BACK) {
                System.out.println(currentEvent);
            }

            switch (currentEvent.getStatus()) {
                case ARRIVES:
                    // Check if there is an available server
                    Server potentialServer = servers.stream()
                            .filter(s -> s.canServe(currentEvent.getTiming()))
                            .findFirst().orElse(Server.EMPTY_SERVER);

                    // if there is an available server add 'SERVED' event
                    // and break
                    if (potentialServer != Server.EMPTY_SERVER) {
                        events.add(currentEvent.setServer(potentialServer)
                                .setStatus(SimulatorStatus.SERVED));
                        break;
                    }

                    // otherwise check if there is any server to wait at
                    potentialServer = servers.stream()
                            .filter(s -> waitingCounters.get(
                                s.getId()).size() < maxQueueLength).findFirst()
                            .orElse(Server.EMPTY_SERVER);

                    // if there is an available server add 'WAITS' event
                    // and break
                    if (potentialServer != Server.EMPTY_SERVER) {
                        events.add(currentEvent.setServer(potentialServer)
                            .setStatus(SimulatorStatus.WAITS));
                        break;
                    }

                    // finally customer just leaves
                    events.add(currentEvent.setStatus(SimulatorStatus.LEAVES));
                    break;
                case SERVED:
                    // These events implies that the server is going to
                    // serve this customer. So we update the server after it
                    // serves.
                    Server updatedServer = currentEvent.getServer()
                        .serve(currentEvent.getTiming(), rng.genServiceTime());

                    // update the server in the servers list
                    servers.set(updatedServer.getId() - 1, updatedServer);

                    // server will have the next available service timing,
                    // which is also the done timing for the customer
                    // Add the customer event back into the pq
                    events.add(currentEvent.setServer(updatedServer)
                            .setTiming(updatedServer.nextServiceTiming())
                            .setStatus(SimulatorStatus.DONE));
                    break;
                case WAITS:
                    // calculate the amount of time the customer waits till
                    // the server serves it
                    Server waitingServer = currentEvent.getServer();

                    // minus now, when served add the serving timing to get
                    // the waiting time
                    totalWaitingTime -= currentEvent.getTiming();

                    // since it is waiting, increment the number of waiting
                    // customers for the server.
                    waitingCounters.get(waitingServer.getId())
                        .add(currentEvent.getCustomer());
                    break;
                case DONE:
                    // Decrement the number of waiting customers as it implies
                    // the next customer waiting, if any, will be served
                    Server doneServer = currentEvent.getServer();

                    if (rng.genRandomRest() < restProbability) {
                        events.add(new SimulatorEvent(null, doneServer, 
                                currentEvent.getTiming(),
                                SimulatorStatus.SERVER_REST));
                        break;
                    }

                    Customer nextCustomer = waitingCounters
                        .get(doneServer.getId())
                        .poll();

                    if (nextCustomer != null) {
                        totalWaitingTime += currentEvent.getTiming();
                        events.add(new SimulatorEvent(nextCustomer, doneServer,
                                currentEvent.getTiming(),
                                SimulatorStatus.SERVED));
                    }

                    // Nothing comes after DONE so no need to add back to pq
                    break;
                case SERVER_REST:
                    // get an unavailable server
                    Server serverToRest = currentEvent.getServer()
                        .setAvailability(false);
                
                    // update the server in the list of servers
                    servers.set(serverToRest.getId() - 1, serverToRest);

                    // add back to pq
                    events.add(currentEvent
                        .setServer(serverToRest)
                        .setTiming(currentEvent.getTiming()
                            + rng.genRestPeriod())
                        .setStatus(SimulatorStatus.SERVER_BACK));
                    break;
                case SERVER_BACK:
                    // get an available server
                    Server backServer = currentEvent.getServer()
                        .setAvailability(true);

                    // update the server in the list of servers
                    servers.set(backServer.getId() - 1, backServer);
                    
                    // get next customer to serve
                    Customer backNextCustomer = waitingCounters
                        .get(backServer.getId())
                        .poll();

                    // serve if any
                    if (backNextCustomer != null) {
                        totalWaitingTime += currentEvent.getTiming();
                        events.add(new SimulatorEvent(backNextCustomer,
                                backServer,
                                currentEvent.getTiming(),
                                SimulatorStatus.SERVED));
                    }
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

        double averageWaitingTime = numberOfServers < 1
            ? 0
            : totalWaitingTime / numberOfServes;

        output = String.format("[%.3f %d %d]", averageWaitingTime,
                numberOfServes, totalCustomers - numberOfServes);
        System.out.println(output);
    }
}