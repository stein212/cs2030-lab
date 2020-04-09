package cs2030.simulator;

/**
 * A customer that contains an id, timing, and status. Implements Comparable for
 * usage in a priority queue
 */
public class Customer {
    private final int id;
    private final CustomerType type;

    /**
     * Creates a new customer with the given id and timing.
     */
    public Customer(int id, CustomerType type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public CustomerType getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + (type == CustomerType.GREEDY ? "(greedy)" : "");
    }
}
