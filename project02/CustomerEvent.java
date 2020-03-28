public class CustomerEvent implements Comparable<CustomerEvent> {
    private final Customer customer;
    private final Server server;
    private final double timing;
    private final CustomerStatus status;

    /**
     * Creates a new customer event with the given customer, server, timing,
     * and status.
     */
    public CustomerEvent(Customer customer, Server server, double timing, CustomerStatus status) {
        this.customer = customer;
        this.server = server;
        this.timing = timing;
        this.status = status;
    }

    /**
     * Gets the timing which the customer arrives at.
     * @return the timing
     */
    public Server getServer() {
        return server;
    }

    /**
     * Creates a new Customer with the specified server and returns it.
     * @param newServer the new server
     * @return a customer with the new server
     */ 
    public CustomerEvent setServer(Server newServer) {
        return new CustomerEvent(customer, newServer, timing, status);
    }

    /**
     * Gets the timing which the customer arrives at.
     * @return the timing
     */
    public double getTiming() {
        return timing;
    }

    /**
     * Creates a new Customer with the specified timing and returns it.
     * @param newTiming the new timing
     * @return a customer with the new timing
     */
    public CustomerEvent setTiming(double newTiming) {
        return new CustomerEvent(customer, server, newTiming, status);
    }

    /**
     * Gets the status of the customer.
     * @return CustomerStatus
     */
    public CustomerStatus getStatus() {
        return status;
    }

    /**
     * Creates a new Customer with the specified status based on the current
     * customer.
     * @param newStatus the new status
     * @return the customer with the new status
     */
    public CustomerEvent setStatus(CustomerStatus newStatus) {
        return new CustomerEvent(customer, server, timing, newStatus);
    }

    @Override
    public String toString() {
        String description = status.toString();
        
        switch (status) {
            case WAITS: 
                description = String.format(
                    "waits to be served by %d",
                    server.getId());
                break;
            case SERVED:
                description = String.format("served by %d", server.getId());
                break;
            case DONE: 
                description = String.format(
                    "done serving by %d",
                    server.getId());
                break;
            default:
                // use enum name
        }

        return String.format("%.3f %d %s", timing, customer.getId(), description);
    }

    @Override
    public int compareTo(CustomerEvent otherCustomerEvent) {
        return timing == otherCustomerEvent.timing
            ? customer.getId() - otherCustomerEvent.customer.getId()
            : timing > otherCustomerEvent.timing
                ? 1
                : -1;
    }
}