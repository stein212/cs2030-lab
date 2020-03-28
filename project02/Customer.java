/**
 * A customer that contains an id, timing, and status.
 * Implements Comparable for usage in a  priority queue
 */
public class Customer {
    private final int id;

    /**
     * Creates a new customer with the given id and timing.
     */
    public Customer(int id, double timing) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
