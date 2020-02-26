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
        while (scanner.hasNextDouble()) {
            double timing = scanner.nextDouble();
            Customer customer = new Customer(++counter, timing);
            customers.add(customer);
        }

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
            Customer customer = customers.poll();
            System.out.println(customer);
            
            Server updatedServer = server.serve(customer);

            if (updatedServer == null) {
                customer = customer.setStatus(CustomerStatus.LEAVES);
            } else {
                server = updatedServer;
                customer = customer.setStatus(CustomerStatus.SERVED);
            }

            System.out.println(customer);
        }

        output = String.format("Number of customers: %d", counter); 
        System.out.println(output);
    }
}
