/open Lazy.java
Lazy.ofNullable(4) // 4
Lazy.ofNullable(4).get() // Optional[4]
Lazy.ofNullable(4).map(x -> x + 4) // ?
Lazy.ofNullable(4).filter(x -> x > 2) // ?
Lazy.ofNullable(4).map(x -> 1).get() // Optional[1]
Lazy.ofNullable(4).filter(x -> true).get() // Optional[4]
Lazy.ofNullable(4).filter(x -> false).get() // Optional.empty
Lazy.ofNullable(4).map(x -> 1).filter(x -> false).get() // Optional.empty
Lazy.ofNullable(4).filter(x -> false).map(x -> 1).get() // Optional.empty
Lazy.ofNullable(4).filter(x -> true).map(x -> 1).get() // Optional[1]
Lazy.ofNullable(4).filter(x -> false).filter(x -> true).get() // Optional.empty

Lazy.ofNullable(null) // null
Lazy.ofNullable(null).get() // Optional.empty
Lazy.ofNullable(null).map(x -> 1) // ?
Lazy.ofNullable(null).filter(x -> true) // ?
Lazy.ofNullable(null).filter(x -> false) // ?
Lazy.ofNullable(null).map(x -> 1).get() // Optional.empty
Lazy.ofNullable(null).filter(x -> true).get() // Optional.empty
Lazy.ofNullable(null).filter(x -> false).get() // Optional.empty
Lazy.generate(() -> 4) // ?
Lazy.generate(() -> 4).get() // Optional[4]
Lazy.generate(() -> 4).map(x -> 1) // ?
Lazy.generate(() -> 4).filter(x -> true) // ?
Lazy.generate(() -> 4).map(x -> 1).get() // Optional[1]
Lazy.generate(() -> 4).filter(x -> true).get() // Optional[4]
Lazy.generate(() -> 4).filter(x -> false).get() // Optional.empty
Lazy<Integer> lazy = Lazy.generate(() -> 4) 
lazy // ?
lazy.get() // Optional[4]
lazy // 4

Lazy.generate(() -> null) // ?
Lazy.generate(() -> null).get() // Optional.empty
Lazy.generate(() -> null).map(x -> 1) // ?
Lazy.generate(() -> null).filter(x -> false) // ?
Lazy.generate(() -> null).map(x -> 1).get() // Optional.empty
Lazy.generate(() -> null).filter(x -> true).get() // Optional.empty
Lazy.generate(() -> null).filter(x -> false).get() // Optional.empty
Lazy<Integer> lazy = Lazy.generate(() -> null) 
lazy // ?
lazy.get() // Optional.empty
lazy // null
