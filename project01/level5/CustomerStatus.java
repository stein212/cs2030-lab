/**
 * Status of a customer.
 */
public enum CustomerStatus {
    ARRIVES("arrives"),
    SERVED("served"),
    WAITS("waits"),
    LEAVES("leaves"),
    DONE("done");

    private final String name;

    private CustomerStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
