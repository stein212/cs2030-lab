package cs2030.simulator;

/**
 * An object that serves customer. Server is immutable. A Server will determine
 * if a customer can be served base on the timing supplied. Once a customer is
 * served, a new Server with the updated next service timing will returned. Each
 * Server has a service time of 1.0.
 */
public class Server {
    /**
     * Stores the next service timing of the Server.
     */
    protected final int id;
    protected final double nextTiming;
    protected final int numberOfServes;
    protected final boolean isAvailable;
    protected final ServerType type;

    public static final Server EMPTY_SERVER = new Server(-1, ServerType.EMPTY);

    /**
     * Constructs a new Server with the next service timing being 0.
     */
    public Server(int id, ServerType type) {
        this.id = id;
        this.nextTiming = 0;
        this.numberOfServes = 0;
        this.type = type;
        isAvailable = true;
    }

    /**
     * Constructs a new server with a specfied next service timing.
     * 
     * @param timing the next service timing of the server
     */
    protected Server(int id, double timing, int numberOfServes,
            boolean isAvailable, ServerType type) {
        this.id = id;
        this.nextTiming = timing;
        this.numberOfServes = numberOfServes;
        this.isAvailable = isAvailable;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public ServerType getType() {
        return type;
    }

    /**
     * Gets the next service timing of the Server.
     * 
     * @return the next service timing of the Server
     */
    public double nextServiceTiming() {
        return this.nextTiming;
    }

    /**
     * Determines if the Server can serve at a specified timing.
     * 
     * @param timing the timing to be checked
     * @return the result of the check
     */
    public boolean canServe(double timing) {
        return this == EMPTY_SERVER || !isAvailable
            ? false
            : timing >= this.nextTiming;
    }

    /**
     * Serves at a specified timing. If a server can serve at the specified 
     * timing, it will return a new server with the specified timing. 
     * Othwerwise, it will return an empty server.
     * 
     * @param timing the timing to serve
     * @param serviceTime the time taken to serve if able to serve
     * @return the new server
     */
    public Server serve(double timing, double serviceTime) {
        if (canServe(timing)) {
            Server newServer = new Server(id, timing + serviceTime,
                    numberOfServes + 1, isAvailable, type);

            return newServer;
        }

        return EMPTY_SERVER;
    }

    /**
     * Creates a new server with the specified availability.
     * 
     * @param isAvailable the availability of the new Server
     * @return the new server
     */
    public Server setAvailability(boolean isAvailable) {
        return new Server(id, nextTiming, numberOfServes, isAvailable, type);
    }

    /**
     * Gets the number of serves of a server.
     * 
     * @return number of serves
     */
    public int getNumberOfServes() {
        return numberOfServes;
    }

    @Override
    public String toString() {
        switch (type) {
            case HUMAN:
                return "server " + id;
            case SELF:
                return "self-check " + id;
            default:
                return "empty server";
        }
    }
}
