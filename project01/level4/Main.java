import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {

    /**
     * Reads all timing inputs and then processes them.
     */
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        
        PriorityQueue<Customer> customers = new PriorityQueue<>();
        int counter = 0;

        System.out.println("# Adding arrivals");
        while (scanner.hasNextDouble()) {
            double timing = scanner.nextDouble();
            Customer customer = new Customer(++counter, timing);
            customers.add(customer);
            System.out.println(customer);
        }

        System.out.println();

        processCustomers(customers);
    }

    /**
     * Processes the customers. Outputs the status of the customers and 
     * number of customers.
     * @param customers list of customers
     */
    public static void processCustomers(PriorityQueue<Customer> customers) {
        String output = "";

        Server server = new Server();
        int counter = customers.size();

        while (!customers.isEmpty()) {
            Customer currentCustomer = customers.poll();
            output = String.format("# Get next event: %s", currentCustomer);
            System.out.println(output);
            
            Server updatedServer = server.serve(currentCustomer);

            switch (currentCustomer.getStatus()) {
                case ARRIVES:
                    if (updatedServer == null) {
                        currentCustomer = currentCustomer.setStatus(CustomerStatus.LEAVES);
                    } else {
                        server = updatedServer;
                        currentCustomer = currentCustomer.setStatus(CustomerStatus.SERVED);
                    }

                    customers.add(currentCustomer);
                    break;
                case SERVED:
                    currentCustomer = currentCustomer
                        .setTiming(server.nextServiceTiming())
                        .setStatus(CustomerStatus.DONE);
                    customers.add(currentCustomer);
                    break;
                default:
                    break;
            }

            for (Customer customer : customers) {
                System.out.println(customer);
            }

            System.out.println();
        }

        output = String.format("Number of customers: %d", counter); 
        System.out.println(output);
    }
}
