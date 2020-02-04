public class Loader {
    final private int id;
    final private Cruise currentCruise;

    public Loader(int id) {
        this.id = id;
        this.currentCruise = null;
    }

    private Loader(int id, Cruise cruise) {
        this.id = id;
        this.currentCruise = cruise;
    }

    public boolean canServe(Cruise cruise) {
        if (currentCruise == null) {
            return true;
        }
        
        return !(currentCruise.getArrivalTime() >= cruise.getArrivalTime()
            && currentCruise.getArrivalTime() < cruise.getServiceCompletionTime()
            || currentCruise.getServiceCompletionTime() >= cruise.getArrivalTime()
            && currentCruise.getServiceCompletionTime() < cruise.getServiceCompletionTime());
    }

    public Loader serve(Cruise cruise) {
        if (canServe(cruise)) {
            return new Loader(id, cruise);
        }

        return null;
    }

    @Override
    public String toString() {
        return currentCruise == null
            ? String.format("Loader %d", id)
            : String.format("Loader %d serving %s", id, currentCruise);
    }
}
