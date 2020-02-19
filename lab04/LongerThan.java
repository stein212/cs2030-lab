public class LongerThan implements BooleanCondition<String> {
    private final int limit;

    public LongerThan(int limit) {
        this.limit = limit;
    }

    public boolean test(String str) {
        return str != null && str.length() > limit;
    }
}
