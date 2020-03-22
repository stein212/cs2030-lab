import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Optional;

public class KeyableMap<T, K, V extends Keyable<K>> implements Keyable<T> {
    protected final T key;
    protected final Map<K, V> map;

    public KeyableMap(T key) {
        this.key = key;
        map = new HashMap<K, V>();
    }

    @Override
    public T getKey() {
        return key;
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(map.get(key));
    }

    public KeyableMap<T, K, V> put(V item) {
        map.put(item.getKey(), item);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(key)
            .append(':')
            .append(' ')
            .append('{');
    
        Set<Map.Entry<K, V>> entrySet = map.entrySet();
        Iterator<Map.Entry<K, V>> iter = entrySet.iterator();

        if (!iter.hasNext()) {
            return stringBuilder.append('}').toString();
        }

        for (;;) {
            V currentItem = iter.next().getValue();

            stringBuilder.append(currentItem);
            if (!iter.hasNext()) {
                return stringBuilder.append('}')
                    .toString();
            }

            stringBuilder.append(',').append(' ');
        }
    }
}
    
