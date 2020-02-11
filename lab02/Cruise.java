public class Cruise implements Comparable<Cruise> {
    private final String id;
    private final int arrivalTime, numberOfLoaders, serviceTime;

    public Cruise(String id, int arrivalTime, int numberOfLoaders, int serviceTime) {
        this.id = id;
        this.arrivalTime = (int)(arrivalTime / 100) * 60  + arrivalTime % 100 % 60;
        this.numberOfLoaders = numberOfLoaders;
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return String.format("%s@%04d", id, arrivalTime/60 * 100 + arrivalTime%60);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!(object instanceof Cruise)) {
            return false;
        }

        Cruise otherCruise = (Cruise)object;

        return id.equals(otherCruise.id);
    }

    @Override
    public int compareTo(Cruise otherCruise) {
        return arrivalTime - otherCruise.arrivalTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getNumOfLoadersRequired() {
        return numberOfLoaders;
    }

    public int getServiceCompletionTime() {
        return arrivalTime + serviceTime;
    }
}
