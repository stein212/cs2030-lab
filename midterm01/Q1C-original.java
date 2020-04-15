   import java.util.List;
   import java.util.ArrayList;
   class B {
       private final List<String> strs;

       private B(List<String> strs) {
           this.strs = strs;
       }
       public B() {
           strs = new ArrayList<>();
           strs.add("B");
       }
       public B add(B b) {
           List<String> copy = new ArrayList<>(strs);
           copy.add("B");
           return new B(copy);
       }
       public B add(C c) {
           List<String> copy = new ArrayList<>(strs);
           copy.add("C");
           return new C(copy);
       }
       @Override
       public String toString() {
           return String.join("", strs);
       }
   }

   class C {
       private final List<String> strs;

       private C(List<String> strs) {
           this.strs = strs;
       }
       public C() {
           strs = new ArrayList<>();
           strs.add("C");
       }
       public C add(B b) {
           List<String> copy = new ArrayList<>(strs);
           copy.add("B");
           return new B(copy);
       }
       public C add(C c) {
           List<String> copy = new ArrayList<>(strs);
           copy.add("C");
           return new C(copy);
       }
       @Override
       public String toString() {
           return String.join("", strs);
       }
   }
