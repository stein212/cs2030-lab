/open InfiniteList.java
/open InfiniteListImpl.java
InfiniteList<String> dots = InfiniteList.generate(() -> ".").limit(4)
dots.forEach(System.out::println)
dots.forEach(System.out::println)
InfiniteList<Integer> even = InfiniteList.iterate(0, i -> i + 2).limit(5)
even.forEach(System.out::println)
even = InfiniteList.iterate(0, i -> i + 2).limit(2)
even.get()
even.get()
even.get()
InfiniteList.iterate(0, i -> i + 2).limit(0).get()
try { 
  InfiniteList.iterate(0, i -> i + 2).limit(-1);
} catch (IllegalArgumentException e) {
  System.out.println(e);
}
InfiniteList.iterate(0, i -> i + 1).limit(10).limit(3).toArray()
InfiniteList.iterate(0, i -> i + 1).limit(3).limit(100).toArray()
InfiniteList.generate(() -> 1).limit(0).toArray()
InfiniteList.generate(() -> 1).limit(2).toArray()


Consumer<Object> blackhole = obj -> {}
InfiniteList.<Integer>generate(() -> 1).limit(3).forEach(blackhole)