public class DivisibleBy implements BooleanCondition<Integer> {
    private final int denominator;

    public DivisibleBy(int denominator) {
        this.denominator = denominator;
    }

    @Override
    public boolean test(Integer i) {
        return i != null && i % denominator == 0;
    }
}
