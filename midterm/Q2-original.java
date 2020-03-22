   interface TypeCaster<S, T> {
       T cast (S s);
   }
   class ToString<S> implements TypeCaster<S, String> {
       @Override
       public String cast(S s) {
           return s.toString();
       }
   }

   class Round implements TypeCaster<Double, Integer> {
       public Integer cast (Double d) {
           return (int) Math.round(d);
       }
   }

   import java.util.Arrays;
   class ToList<T> implements TypeCaster<T[], List<T>> {
       public List<T> cast (T[] ts) {
           return Arrays.toList(ts);
       }
   }

   class ListCaster {
       public static <S, T> List<T> castList(List<S> ss, TypeCaster<?
   super S, ? extends T> caster) {
           ss.stream()
               .map(caster)
               .Collect(Collectors.toList());
       }
   }
