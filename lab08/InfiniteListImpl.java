import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class InfiniteListImpl<T> implements InfiniteList<T> {

    public abstract Optional<T> get();

    public InfiniteList<T> limit(long maxSize) {
        if (maxSize < 0) {
            throw new IllegalArgumentException(String.valueOf(maxSize));
        }

        return new InfiniteListImpl<T>() {
            private long i = 0;

            public Optional<T> get() {
                if (i >= maxSize) {
                    return Optional.empty();
                }
                
                i++;
                return InfiniteListImpl.this.get();
            }
        };
    }

    public void forEach(Consumer<? super T> action) {
        Optional<T> element = get();

        while (element.isPresent()) {
            action.accept(element.get());

            element = get();
        }
    }

    public Object[] toArray() {
        List<T> list = new ArrayList<>();

        Optional<T> element = get();

        while (element.isPresent()) {
            list.add(element.get());

            element = get();
        }

        return list.toArray();
    }

    public <S> InfiniteList<S> map(Function<? super T, ? extends S> mapper) {
        return new InfiniteListImpl<S>() {
            public Optional<S> get() {
                return InfiniteListImpl.this.get()
                    .flatMap(x -> Optional.ofNullable(mapper.apply(x)));
            }
        };
    }

    public InfiniteList<T> filter(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                Optional<T> element = InfiniteListImpl.this.get();
                while (element.isPresent()) {
                    element = element.filter(predicate);

                    if (element.isPresent()) {
                        break;
                    }

                    element = InfiniteListImpl.this.get();
                }
                return element;
            }
        };
    }

    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            private boolean hasEnded = false;
            public Optional<T> get() {
                Optional<T> element = InfiniteListImpl.this.get();

                if (hasEnded) {
                    return element;
                }

                while (element.isPresent()) {
                    element = element.filter(predicate);

                    if (element.isPresent()) {
                        break;
                    }

                    hasEnded = true;
                }

                return element;
            }
        };
    }

    public long count() {
        long sum = 0;

        Optional<T> element = get();

        while (element.isPresent()) {
            sum++;

            element = get();
        }

        return sum;
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> acc = get();

        while (acc.isPresent()) {
            Optional<T> cur = get();

            if (cur.isEmpty()) {
                break;
            }

            acc = Optional.ofNullable(accumulator.apply(acc.get(), cur.get()));
        }

        return acc;
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        T acc = identity;

        Optional<T> cur = get();

        while (cur.isPresent()) {
            if (cur.isEmpty()) {
                break;
            }

            acc = accumulator.apply(acc, cur.get());
            
            cur = get();
        }

        return acc;
    }
}