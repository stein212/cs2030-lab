public interface Transformer<T, U> {
    U transform(T t);
}
