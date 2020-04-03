import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.Optional;

public class Lazy<T> {
    private boolean isEvaluated;
    private T t;
    private final Supplier<? extends T> supplier;

    private Lazy(T t) {
        this.t = t;
        isEvaluated = true;
        supplier = () -> t;
    }

    private Lazy(Supplier<? extends T> supplier) {
        t = null;
        isEvaluated = false;
        this.supplier = supplier;
    }
    
    static <T> Lazy<T> ofNullable(T t) {
        return new Lazy<T>(t);
    }

    Optional<T> get() {
        if (isEvaluated) {
            return Optional.ofNullable(t);
        }

        t = supplier.get();
        isEvaluated = true;
        return Optional.ofNullable(t);
    }

    static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        return new Lazy<T>(supplier);
    }

    <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        return new Lazy<R>(() -> get().map(mapper).orElse(null));
    }

    Lazy<T> filter(Predicate<? super T> predicate) {
        return Lazy.generate(() -> get().filter(predicate).orElse(null));
    }

    public boolean isEvaluated() {
        return isEvaluated;
    }

    @Override
    public String toString() {
        return isEvaluated
            ? String.valueOf(t)
            : "?";
    }
}