   import java.util.*;
   class D {
       public static <T> List<T> add(List<T> ts, T t) {
           List<T> copy = new ArrayList<T>(ts);
           copy.add(t);
           return copy;
       }
       public static <T> List<T> join(List<T> as, List<? extends T> bs) {
           List<T> copy = new ArrayList<>(as);
           copy.addAll(bs);
           return as == bs ? as : copy;
       }
   }
