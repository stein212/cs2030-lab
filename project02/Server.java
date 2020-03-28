/**
 * An object that serves customer. Server is immutable. A Server will 
 * determine if a customer can be served base on the timing supplied. Once a 
 * customer is served, a new Server with the updated next service timing will 
 * returned. Each Server has a service time of 1.0.
 */
public class Server {
    /**
     * Stores the next service timing of the Server.
     */
    private final int id;
    private final double nextTiming;
    private final int numberOfServes;

    public static final Server EMPTY_SERVER = new Server(-1);

    /**
     * Constructs a new Server with the next service timing being 0.
     */
    public Server(int id) {
        this.id = id;
        this.nextTiming = 0;
        this.numberOfServes = 0;
    }

    /**
     * Constructs a new server with a specfied next service timing.
     * @param timing the next service timing of the server
     */
    private Server(
            int id,
            double timing,
            int numberOfServes) {
        this.id = id;
        this.nextTiming = timing;
        this.numberOfServes = numberOfServes;
    }

    public int getId() {
        return id;
    }

    /**
     * Gets the next service timing of the Server.
     * @return the next service timing of the Server 
     */
    public double nextServiceTiming() {
        return this.nextTiming;
    }

    /**
     * Determines if the Server can serve at a specified timing.
     * @param timing the timing to be checked
     * @return the result of the check
     */
    public boolean canServe(double timing) {
        return this == EMPTY_SERVER ? false : timing >= this.nextTiming;
    }

    /**
     * Serves at a specified timing. If a server can serve at the specified 
     * timing, it will return a new server with the specified timing. 
     * Othwerwise, it will return an empty server.
     * @param timing the timing the server serves
     * @return the new Server or null
     */
    public Server serve(double timing) {
        if (canServe(timing)) {
            Server newServer = new Server(
                    id,
                    timing + 1,
                    numberOfServes + 1);
            
            return newServer;
        }

        return EMPTY_SERVER;
    }

    /**
     * Gets the number of serves of a server.
     * @return number of serves
     */
    public int getNumberOfServes() {
        return numberOfServes;
    }
}
