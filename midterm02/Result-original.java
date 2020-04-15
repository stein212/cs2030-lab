import java.util.function.*;
 
class Resulta<T> {
   T t = null;
   String em = null;
 
   private Result(T t, String em) {
      if (em != null) {
         this.em = em;
      } else {
         this.t = t;
      }
   }
 
   static <T> Result<T> success(T value) {
      return new Result<T>(value);
   }
   
   static Result<T> error(String em) {
      return new Result<T>(null, em);
   }
 
   public String toString() {
      return t == null || em != null
         ? em
         : "Success: " + t.toString();
   }
 
   public Result<T> filter(Predicate<? super T> predicate, String em) {
      return t == null
         ? new Result<T>(null, em)
         : predicate.test(t)
            ? this
            : new Result<T>(null, em);
   }
 
   public Result<T> flatMap(Function<T, Result<T>> func) {
      return t == null || em != null
         ? this
         : func.apply(t);
   }
 
   public T orElseThrow(Exception e) {
      if (t == null || em != null) {
         throw e;
      }
 
      return t;
   }
}
