import java.rmi.UnexpectedException;
import java.util.Scanner;
import cs2030.simulator.Simulator;

public class Main {

    /**
     * Get input for parameters for simulation.
     */
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        // get parameters
        int seed = scanner.nextInt();
        int numberOfServers = scanner.nextInt();
        int numberOfSelfCheckout = scanner.nextInt();
        int maxQueueLength = scanner.nextInt();
        int numberOfCustomers = scanner.nextInt();
        double arrivalRate = scanner.nextDouble();
        double serviceRate = scanner.nextDouble();
        double restRate = scanner.nextDouble();
        double restProbability = scanner.nextDouble();

        scanner.close();

        Simulator simulator = new Simulator();

        try {
            simulator.simulate(seed, numberOfServers, numberOfSelfCheckout, 
                    maxQueueLength, numberOfCustomers, arrivalRate, serviceRate, 
                    restRate, restProbability);
        } catch (UnexpectedException ue) {
            System.out.println(ue);
        }
    }
}
