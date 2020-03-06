/**
 * Status of a customer.
 */
public enum CustomerStatus {
    ARRIVES("arrives"),
    SERVED("served"),
    LEAVES("leaves"),
    DONE("done");

    private final String name;

    private CustomerStatus(String name) {
        this.name = name;
    }

    /**
     * Get the integer representation of the enum value.
     * @return int
     */
    public int getValue() {
        return ordinal() + 1;
    }

    @Override
    public String toString() {
        return name;
    }
}
