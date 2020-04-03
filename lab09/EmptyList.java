import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EmptyList<T> implements InfiniteList<T> {
    @Override
    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<>();
    }

    @Override
    public InfiniteList<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    @Override
    public InfiniteList<T> peek() {
        return this;
    }

    @Override
    public InfiniteList<T> limit(long n) {
        return this;
    }

    @Override
    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        return this;
    }

    @Override
    public void forEach(Consumer<? super T> action) { }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        return identity;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}