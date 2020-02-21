public class Box<T> {
    private final T t;

    private static final Box<?> EMPTY_BOX = new Box<>(null);

    private Box(T t) {
        this.t = t;
    }

    public static <T> Box<T> of(T item) {
        if (item == null) {
            return null;
        }
        
        return new Box<>(item);
    }

    public static <T> Box<T> ofNullable(T item) {
        if (item == null) {
            return empty();
        }
        
        return new Box<>(item);
    }

    @SuppressWarnings("unchecked")
    public static <T> Box<T> empty() {
        return (Box<T>) EMPTY_BOX;
    }

    public boolean isPresent() {
        return this != EMPTY_BOX;
    }

    public Box<T> filter(BooleanCondition<? super T> condition) {
        if (!condition.test(t)) {
            return empty();
        }

        return this;
    }

    public <U> Box<U> map(Transformer<? super T, U> transformer) {
        if (this == EMPTY_BOX) {
            return empty();
        }

        return ofNullable(transformer.transform(t));
    }

    @Override
    public String toString() {
        if (t == null) {
            return "[]";
        }

        return String.format("[%s]", t);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (t == null) {
            return false;
        }

        if (!(other instanceof Box)) {
            return false;
        }

        Box otherBox = (Box) other;

        return t.equals(otherBox.t);
    }
}
