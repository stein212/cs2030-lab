String pattern(int n) {
   return IntStream.rangeClosed(1, n)
      .mapToObj(x -> IntStream.rangeClosed(1, x).mapToObj(i -> String.valueOf(i)).reduce("", (acc, x1) -> x1 + acc)
         + IntStream.rangeClosed(1, n-x+1).mapToObj(i -> ".").reduce("", (acc, x2) -> acc + x2))
      .reduce("", (acc, x) -> x + acc)
      .substring(0, n*n);
}
