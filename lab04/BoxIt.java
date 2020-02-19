public class BoxIt<T> implements Transformer<T, Box<T>> {
   public Box<T> transform(T t) {
      return Box.ofNullable(t);
   }
}
