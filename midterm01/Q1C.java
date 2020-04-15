   import java.util.List;
   import java.util.ArrayList;
class D {public String x;
public D(List<String> s) {}public D(String s) {x=s;}public String toString() {return x;}
public D add(D d) { return new D(x+d.x);}}
   class B extends D {
       /*private final List<String> strs;

       private B(List<String> strs) {
           this.strs = strs;
       }*/
       public B() {
           super("B");
           /*strs = new ArrayList<>();
           strs.add("B");*/
       }
       /*public B add(B b) {
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
       }*/
   }

   class C extends D {
       /*private final List<String> strs;

       private C(List<String> strs) {
           this.strs = strs;
       }*/
       public C() {
           super("C");
           /*strs = new ArrayList<>();
           strs.add("C");*/
       }
       /*public C add(B b) {
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
       }*/
   }
