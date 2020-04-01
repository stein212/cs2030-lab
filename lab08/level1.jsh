/open InfiniteList.java
/open InfiniteListImpl.java
InfiniteList<String> dots = InfiniteList.generate(() -> ".");
dots.get()
dots.get()
dots.get()
InfiniteList<Integer> even = InfiniteList.iterate(0, i -> i + 2);
even.get()
even.get()
even.get()
Random rnd = new Random(1) 
Supplier<List<Integer>> randListSupplier = () -> List.of(rnd.nextInt());
InfiniteList<List<? extends Number>> list = InfiniteList.generate(randListSupplier);