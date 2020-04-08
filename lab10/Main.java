import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * This program finds different ways one can travel by bus (with a bit of
 * walking) from one bus stop to another.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
public class Main {
    /**
     * The program read a sequence of (id, search string) from standard input.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);

        ArrayList<CompletableFuture<String>> descriptionCfs = new ArrayList<>();

        while (sc.hasNext()) {
            BusStop srcId = new BusStop(sc.next());
            String searchString = sc.next();
            descriptionCfs
                    .add(BusSg.findBusServicesBetween(srcId, searchString).thenApply(bs -> bs.description().join()));
        }
        sc.close();

        CompletableFuture.allOf(descriptionCfs.toArray(new CompletableFuture<?>[0]))
                .thenRun(() -> descriptionCfs.stream().map(dcf -> dcf.join()).forEach(System.out::println)).join();

        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}
