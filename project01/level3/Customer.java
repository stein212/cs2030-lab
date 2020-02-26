/**
 * A customer that contains an id, timing, and status.
 */
public class Customer implements Comparable<Customer>{
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
        return String.format("%.3f %d %s", timing, id, status.toString());
    }

    @Override
    public int compareTo(Customer otherCustomer) {
        return timing == otherCustomer.timing
            ? 0
            : timing > otherCustomer.timing
                ? 1
                : -1;
    }
}
