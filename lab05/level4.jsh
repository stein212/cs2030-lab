/open ImmutableList.java
new ImmutableList<Integer>(3,2,1).sorted()
class A { }
try {
  new ImmutableList<A>(new A(), new A()).sorted();
} catch (IllegalStateException e) { 
  System.out.println(e);
}
ImmutableList<Integer> list = new ImmutableList<Integer>().sorted()
list
new ImmutableList<A>().sorted()
list
list = new ImmutableList<Integer>(1,2,3)
list.sorted((x,y) -> y - x)
list
try {
  new ImmutableList<Integer>(1,2,3).sorted(null);
} catch (NullPointerException e) {
  System.out.println(e);
}
new ImmutableList<Integer>(4,5,3,6,7,2,1).filter(x -> x % 2 == 0).sorted().map(i -> "P" + i);
