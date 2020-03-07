import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.Comparator;

public class ImmutableList<T> {
    private final List<T> items;

    public ImmutableList(List<T> items) {
        for (T item : items) {
            if (!(item instanceof Comparable)) {
                throw new IllegalStateException("List elements do not implement Comparable");
            }
        }
        this.items = new ArrayList<>(items);
    }

    @SafeVarargs
    public ImmutableList(T... items) {
        this.items = new ArrayList<>();
        for (T item : items) {
            if (!(item instanceof Comparable)) {
                throw new IllegalStateException("List elements do not implement Comparable");
            }

            this.items.add(item);
        }
    }

    public ImmutableList<T> add(T item) {
        List<T> itemsCopy = new ArrayList<>(items);
        itemsCopy.add(item);
        return new ImmutableList<T>(itemsCopy);
    }

    public ImmutableList<T> replace(T itemToReplace, T replacement) {
        List<T> itemsCopy = new ArrayList<>(items);
        Collections.replaceAll(itemsCopy, itemToReplace, replacement);
        return new ImmutableList<T>(itemsCopy);
    }

    public ImmutableList<T> remove(T itemToRemove) {
        List<T> itemsCopy = new ArrayList<>(items);
        itemsCopy.remove(itemToRemove);
        return new ImmutableList<T>(itemsCopy);
    }

    public <U> ImmutableList<U> map(Function<? super T, ? extends U> func) {
        List<U> itemsCopy = items.stream()
            .map(func)
            .collect(Collectors.toList());
        
        return new ImmutableList<U>(itemsCopy);
    }

    public ImmutableList<T> filter(Predicate<? super T> pred) {
        List<T> itemsCopy = items.stream()
            .filter(pred)
            .collect(Collectors.toList());

        return new ImmutableList<T>(itemsCopy);
    }

    public ImmutableList<T> limit(long size) {
        if (size < 0) {
            throw new IllegalArgumentException("limit size < 0");
        }

        List<T> itemsCopy = items.stream()
            .limit(size)
            .collect(Collectors.toList());

        return new ImmutableList<T>(itemsCopy);
    }

    public ImmutableList<T> sorted() {
        List<T> itemsCopy = items.stream()
            .sorted()
            .collect(Collectors.toList());

        return new ImmutableList<T>(itemsCopy);
    }

    public ImmutableList<T> sorted(Comparator<T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Comparator is null");
        }

        List<T> itemsCopy = items.stream()
            .sorted(comparator)
            .collect(Collectors.toList());

        return new ImmutableList<T>(itemsCopy);
    }

    public Object[] toArray() {
        return items.toArray();
    }

    public <U> U[] toArray(U[] a) {
        if (a == null) {
            throw new NullPointerException("Input array cannot be null");
        }

        try {
            return items.toArray(a);
        } catch (ArrayStoreException e) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        if (items.size() > 0) {
            items.stream()
                .limit(items.size()-1) 
                .forEach(i -> stringBuilder.append(i).append(", "));

            stringBuilder.append(items.get(items.size()-1));
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
