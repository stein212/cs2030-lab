public class RecycledLoader extends Loader {
    
    public RecycledLoader(int id) {
        super(id);
    }

    protected RecycledLoader(int id, Cruise cruise) {
        super(id, cruise);
    }

    @Override
    public boolean canServe(Cruise cruise) {
        return currentCruise == null
            || currentCruise.getServiceCompletionTime() + 60 <= cruise.getArrivalTime()
            || currentCruise.getArrivalTime() > cruise.getServiceCompletionTime();
    }

    @Override
    public RecycledLoader serve(Cruise cruise) {
        if (canServe(cruise)) {
            return new RecycledLoader(id, cruise);
        }

        return null;
    }

    @Override
    public String toString() {
        return String.format("Loader %d (recycled) serving %s", id, currentCruise);
    }
}
