import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.BinaryOperator;

public interface InfiniteList<T> {
    
    static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                return Optional.ofNullable(supplier.get());
            }
        };
    }
    
    static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteListImpl<T>() {
            private T element = seed;
            
            public Optional<T> get() {
                Optional<T> current = Optional.ofNullable(element);
                element = f.apply(element);
                
                return current;
            }
        };
    }
    
    Optional<T> get();
    
    InfiniteList<T> limit(long maxSize);
    void forEach(Consumer<? super T> action);
    Object[] toArray();
    
    <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper);
    InfiniteList<T> filter(Predicate<? super T> predicate);
    InfiniteList<T> takeWhile(Predicate<? super T> predicate);
    
    long count();
    public Optional<T> reduce(BinaryOperator<T> accumulator);
    public T reduce(T identity, BinaryOperator<T> accumulator);
}