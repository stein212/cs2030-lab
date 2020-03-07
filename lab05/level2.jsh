/open ImmutableList.java
new ImmutableList<Integer>(1,2,3).filter(x -> x % 2 == 0)
new ImmutableList<String>("one", "two", "three").filter(x -> x.hashCode()%10 > 5)
Predicate<Object> p = x -> x.hashCode()%10 == 0
new ImmutableList<String>("one", "two", "three").filter(p)
ImmutableList<Integer> list = new ImmutableList<String>("one", "two", "three").map(x -> x.length())
/var list
Function<Object,Integer> f = x -> x.hashCode()
ImmutableList<Number> list = new ImmutableList<String>("one", "two", "three").map(f)
/var list
new ImmutableList<Integer>(1,2,3).filter(x -> x > 3).map(x -> x + 1)
new ImmutableList<Integer>(1,2,3).filter(x -> x > 2).map(x -> x + 1)
new ImmutableList<Integer>(1,2,3).map(x -> x + 1).filter(x -> x > 2)
new ImmutableList<String>().filter(s -> s.endsWith("s"))
new ImmutableList<String>().map(s -> s.endsWith("s"))
