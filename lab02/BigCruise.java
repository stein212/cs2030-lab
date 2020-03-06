public class BigCruise extends Cruise {
    private final static int LOADERS_PER_LENGTH = 40;
    private final static int PASSENGERS_PER_MINUTE = 50;

    public BigCruise(String id, int arrivalTime, int length, int numberOfPassengers) {
        super(
            id,
            arrivalTime,
            (int)Math.ceil((double)length / LOADERS_PER_LENGTH),
            (int)Math.ceil((double)numberOfPassengers / PASSENGERS_PER_MINUTE)
        );
    }
}

