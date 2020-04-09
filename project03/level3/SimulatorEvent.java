package cs2030.simulator;

public class SimulatorEvent implements Comparable<SimulatorEvent> {
    private final Customer customer;
    private final Server server;
    private final double timing;
    private final SimulatorStatus status;

    /**
     * Creates a new event with the given customer, server, timing, and
     * status.
     */
    public SimulatorEvent(Customer customer, Server server, double timing,
            SimulatorStatus status) {
        this.customer = customer;
        this.server = server;
        this.timing = timing;
        this.status = status;
    }

    /**
     * Gets the timing which the customer arrives at.
     * 
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the timing which the customer arrives at.
     * 
     * @return the server
     */
    public Server getServer() {
        return server;
    }

    /**
     * Creates a new event with the specified server and returns it.
     * 
     * @param newServer the new server
     * @return an event with the new server
     */
    public SimulatorEvent setServer(Server newServer) {
        return new SimulatorEvent(customer, newServer, timing, status);
    }

    /**
     * Gets the timing of the event.
     * 
     * @return the timing
     */
    public double getTiming() {
        return timing;
    }

    /**
     * Creates a new event with the specified timing and returns it.
     * 
     * @param newTiming the new timing
     * @return an event with the new timing
     */
    public SimulatorEvent setTiming(double newTiming) {
        return new SimulatorEvent(customer, server, newTiming, status);
    }

    /**
     * Gets the status of the customer.
     * 
     * @return CustomerStatus
     */
    public SimulatorStatus getStatus() {
        return status;
    }

    /**
     * Creates a new Customer with the specified status based on the current
     * customer.
     * 
     * @param newStatus the new status
     * @return the customer with the new status
     */
    public SimulatorEvent setStatus(SimulatorStatus newStatus) {
        return new SimulatorEvent(customer, server, timing, newStatus);
    }

    @Override
    public String toString() {
        String description = status.toString();

        switch (status) {
            case WAITS:
                description = String.format("waits to be served by %s",
                        server);
                break;
            case SERVED:
                description = String.format("served by %s",
                        server);
                break;
            case DONE:
                description = String.format("done serving by %s",
                        server);
                break;
            default:
                // use enum name
        }

        return String.format("%.3f %d %s", timing, customer.getId(),
                description);
    }

    @Override
    public int compareTo(SimulatorEvent otherEvent) {
        return timing == otherEvent.timing
            ? customer.getId() - otherEvent.customer.getId()
            : timing > otherEvent.timing ? 1 : -1;
    }
}