/open ImmutableList.java
new ImmutableList<Integer>(1,2,3).toArray()
new ImmutableList<Integer>().toArray()
new ImmutableList<Integer>(1,2,3).toArray(new Integer[0])
try {
  new ImmutableList<Integer>(1,2,3).toArray(new String[0]);
} catch (ArrayStoreException e) {
  System.out.println(e);
}
try {
  new ImmutableList<Integer>(1,2,3).toArray(null);
} catch (NullPointerException e) {
  System.out.println(e);
}
