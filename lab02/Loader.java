public class Loader {
    final protected int id;
    final protected Cruise currentCruise;

    public Loader(int id) {
        this.id = id;
        this.currentCruise = null;
    }

    protected Loader(int id, Cruise cruise) {
        this.id = id;
        this.currentCruise = cruise;
    }

    public boolean canServe(Cruise cruise) {
        if (currentCruise == null) {
            return true;
        }

        return currentCruise.getServiceCompletionTime() <= cruise.getArrivalTime()
            || currentCruise.getArrivalTime() > cruise.getServiceCompletionTime();
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
