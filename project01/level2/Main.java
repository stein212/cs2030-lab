import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {

    /**
     * Reads all timing inputs and then processes them.
     */
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        
        PriorityQueue<Double> timings = new PriorityQueue<>();
        while (scanner.hasNextDouble()) {
            double timing = scanner.nextDouble();
            timings.add(timing);
        }

        processTimings(timings);
    }

    /**
     * Processes the timings. Outputs the arrival timings of customers and 
     * number of customers.
     * @param timings list of timings of customers
     */
    public static void processTimings(PriorityQueue<Double> timings) {
        String output = "";

        Server server = new Server();
        int counter = 0;

        while (timings.size() > 0) {
            double timing = timings.poll();
            output = String.format("%d arrives at %.3f", ++counter, timing);
            System.out.println(output);

            Server newServer = server.serve(timing);
            if (newServer == null) {
                output = "Customer leaves";
                System.out.println(output);
                continue;
            }

            server = newServer;
            output = String.format(
                    "Customer served; next service @ %.3f",
                    server.nextServiceTiming());
            System.out.println(output);
        }

        output = String.format("Number of customers: %d", counter);
        System.out.println(output);
    }
}
