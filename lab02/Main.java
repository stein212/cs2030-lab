import java.util.Scanner;

public class Main {
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

        for (int i = 0; i < numOfCruises; i++) {
            System.out.println(cruises[i]);
        }
    }
}
