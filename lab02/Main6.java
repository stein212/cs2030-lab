import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.Arrays;

public class Main6 {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        int numOfCruises = scanner.nextInt();
        
        Cruise[] cruises = new Cruise[numOfCruises];

        for (int i = 0; i < numOfCruises; i++) {
            String cruiseId = scanner.next();
            if (cruiseId.charAt(0) == 'B') {
                cruises[i] = new BigCruise(
                    cruiseId,
                    scanner.nextInt(),
                    scanner.nextInt(),
                    scanner.nextInt()
                );
            } else { // small cruises
                cruises[i] = new SmallCruise(
                    cruiseId,
                    scanner.nextInt()
                );
            }
        }

        Arrays.sort(cruises);

        // assume worse case number of loaders and cannot use ArrayList yet
        // but for some reason assume can stream
        Loader[] loaders = IntStream.rangeClosed(1, cruises.length * 9)
            .mapToObj(i -> i % 3 == 0 ? new RecycledLoader(i) : new Loader(i))
            .toArray(Loader[]::new);

        for (Cruise cruise : cruises) {
            for (int i = 0, numOfLoaders = cruise.getNumOfLoadersRequired(); numOfLoaders > 0; i++) {
                Loader serveLoader = loaders[i].serve(cruise);
                if (serveLoader != null) {
                    System.out.println(serveLoader);
                    loaders[i] = serveLoader;
                    numOfLoaders--;
                }
            }
        }
    }
}
