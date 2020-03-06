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
    private final double nextTiming;

    /**
     * Constructs a new Server with the next service timing being 0.
     */
    public Server() {
        this.nextTiming = 0;
    }

    /**
     * Constructs a new server with a specfied next service timing.
     * @param timing the next service timing of the server
     */
    private Server(double timing) {
        this.nextTiming = timing;
    }

    /**
     * Gets the next service timing of the Server.
     * @return the next service timing of the Server 
     */
    public double nextServiceTiming() {
        return this.nextTiming;
    }

    /**
     * Determines if the Server can serve the specified customer.
     * @param customer the timing to be checked
     * @return the result of the check
     */
    public boolean canServe(Customer customer) {
        return canServe(customer.getTiming());
    }

    /**
     * Determines if the Server can serve at a specified timing.
     * @param timing the timing to be checked
     * @return the result of the check
     */
    public boolean canServe(double timing) {
        return timing >= this.nextTiming;
    }

    /**
     * Serves a customer. Firstly, it determines if the Server can serve the 
     * customer. It then returns a new Server with the updated next service 
     * timing if it can serve the customer. Otherwise it returns null.
     * @param customer the customer to be served
     * @return the new Server or null
     */
    public Server serve(Customer customer) {
        if (canServe(customer)) {
            return new Server(customer.getTiming() + 1);
        }

        return null;
    }
}
