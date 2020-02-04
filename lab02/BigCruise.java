public class BigCruise extends Cruise {
    final private static double LOADERS_PER_LENGTH = 40, PASSENGERS_PER_MINUTE = 50;

    public BigCruise(String id, int arrivalTime, int length, int numberOfPassengers) {
        super(
            id,
            arrivalTime,
            (int)Math.ceil(length / LOADERS_PER_LENGTH),
            (int)Math.ceil(numberOfPassengers / PASSENGERS_PER_MINUTE)
        );
    }
}

