/open ImmutableList.java
new ImmutableList<Integer>(1,2,3).limit(1)
new ImmutableList<Integer>(1,2,3).limit(10)
ImmutableList<Integer> list = new ImmutableList<Integer>(1,2,3)
list
list = list.limit(0)
try {
  new ImmutableList<Integer>(1,2,3).limit(-1);
} catch (IllegalArgumentException e) {
  System.out.println(e);
}
