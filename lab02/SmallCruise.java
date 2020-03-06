public class SmallCruise extends Cruise {
    private final static int NUM_OF_LOADERS = 1;
    private final static int SERVICE_TIME = 30;
    public SmallCruise(String id, int arrivalTime) {
        super(id, arrivalTime, NUM_OF_LOADERS, SERVICE_TIME);
    }
}

