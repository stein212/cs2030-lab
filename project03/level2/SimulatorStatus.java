package cs2030.simulator;

/**
 * Status of a customer.
 */
public enum SimulatorStatus {
    ARRIVES("arrives"),
    SERVED("served"),
    WAITS("waits"),
    LEAVES("leaves"),
    DONE("done"),
    SERVER_REST("server rest"),
    SERVER_BACK("server back");

    private final String name;

    private SimulatorStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
