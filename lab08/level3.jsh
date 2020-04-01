/open InfiniteList.java
/open InfiniteListImpl.java
InfiniteList<String> s = InfiniteList.generate(() -> "A")
InfiniteList<String> s = InfiniteList.generate(() -> "A").map(x -> x + "-")
InfiniteList<Integer> i = InfiniteList.generate(() -> "A").map(x -> x + "-").map(str -> str.length())
InfiniteList<Integer> i = InfiniteList.generate(() -> "A").map(x -> x + "-").map(str -> str.length())
InfiniteList.generate(() -> "A").map(x -> x + "-").map(str -> str.length()).limit(4).toArray()
InfiniteList.generate(() -> "A").limit(4).map(x -> x + "-").map(str -> str.length()).toArray()
InfiniteList.generate(() -> "A").map(x -> x + "-").limit(4).map(str -> str.length()).toArray()

InfiniteList<Integer> i = InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0)
InfiniteList<Integer> i = InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).filter(x -> x % 4 == 0)
InfiniteList.iterate(1, x -> x + 1).limit(4).filter(x -> x % 2 == 0).toArray()
InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0).limit(4).toArray()
InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).filter(x -> x < 20).limit(5).toArray()
InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).map(x -> x.hashCode() % 30).filter(x -> x < 20).limit(5).toArray()

InfiniteList<Integer> i = InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 3)
InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 3).toArray()
InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 0).toArray()
InfiniteList.iterate(0, x -> x + 1).takeWhile(x -> x < 10).takeWhile(x -> x < 5).toArray()
InfiniteList.iterate(0, x -> x + 1).map(x -> x).takeWhile(x -> x < 4).limit(1).toArray()
InfiniteList.iterate(0, x -> x + 1).limit(4).takeWhile(x -> x < 2).toArray()
InfiniteList.iterate(0, x -> x + 1).map(x -> x * x).filter(x -> x > 10).limit(2).toArray()
InfiniteList.iterate(0, x -> x + 1).filter(x -> x > 10).map(x -> x * x).limit(2).toArray()

Function<Object,List<Integer>> f = obj -> List.<Integer>of(obj.hashCode() % 100, obj.hashCode() % 10)
InfiniteList<List<? super Integer>> list = InfiniteList.iterate("", x -> "-" + x).map(f)
Predicate<Object> p = obj -> obj.hashCode() % 2 == 0
InfiniteList<String> s = InfiniteList.generate(() -> "A").filter(p)
InfiniteList<String> s = InfiniteList.<String>generate(() -> "A").takeWhile(p)