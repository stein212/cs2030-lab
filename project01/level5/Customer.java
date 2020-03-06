/**
 * A customer that contains an id, timing, and status.
 * Implements Comparable for usage in a  priority queue
 */
public class Customer implements Comparable<Customer> {
    private final double timing;
    private final int id;
    private final CustomerStatus status;

    /**
     * Creates a new customer with the given id and timing.
     * Status is defaulted to 'arrives'
     */
    public Customer(int id, double timing) {
        this.id = id;
        this.timing = timing;
        this.status = CustomerStatus.ARRIVES;
    }

    /**
     * Creates a new customer with the given id, timing, and status.
     */
    private Customer(int id, double timing, CustomerStatus status) {
        this.id = id;
        this.timing = timing;
        this.status = status;
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
    public Customer setTiming(double newTiming) {
        return new Customer(id, newTiming, status);
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
     * @param status the new status
     * @return the customer with the new status
     */
    public Customer setStatus(CustomerStatus status) {
        return new Customer(id, timing, status);
    }

    @Override
    public String toString() {
        return String.format("%.3f %d %s", timing, id, status);
    }

    @Override
    public int compareTo(Customer otherCustomer) {
        return timing == otherCustomer.timing
            ? id - otherCustomer.id
            : timing > otherCustomer.timing
                ? 1
                : -1;
    }
}
