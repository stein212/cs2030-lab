import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.Optional;

public interface InfiniteList<T> {

    public static <T> InfiniteList<T> generate(Supplier<? extends T> s) {
        return InfiniteListImpl.generate(s);
    }

    public static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> next) {
        return InfiniteListImpl.iterate(seed, next); 
    }

    <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper);
    InfiniteList<T> filter(Predicate<? super T> predicate);
    void forEach(Consumer<? super T> action);
    Object[] toArray();
    InfiniteList<T> limit(long n);
    long count();
    <U> U reduce (U identity, BiFunction<U, ? super T, U> accumulator);
    InfiniteList<T> takeWhile(Predicate<? super T> predicate);
    InfiniteList<T> peek();

    boolean isEmpty();
}
