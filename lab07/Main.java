import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Pair<T, V> {
    final T head;
    final V tail;

    Pair(T left, V right) {
        this.head = left;
        this.tail = right;
    }
}

class Normalize {
    final int sum;
    final int count;
    final int min;
    final int max;

    Normalize(int sum, int count, int min, int max) {
        this.sum = sum;
        this.count = count;
        this.min = min;
        this.max = max;
    }

    Normalize add(Normalize normalize) {
        return new Normalize(sum + normalize.sum, count + 1, Math.min(min, normalize.min), Math.max(max, normalize.max));
    }

    double getNormalizedMean() {
        return count == 0 || max == min ? 0 : ((double)sum / count - min) / (max - min);
    }
}

class Main {
    static boolean isPrime(int x) {
        return IntStream.rangeClosed(2, (int)Math.sqrt(x)).noneMatch(y -> x % y == 0);
    }

    static int[] twinPrimes(int n) {
        return IntStream.rangeClosed(3, n)
            .filter(x -> isPrime(x)
                && (isPrime(x-2) || isPrime(x+2)))
            .toArray();
    }

    static int gcd(int m, int n) {
        return Stream.iterate(new Pair<>(m, n),
            p -> p.tail != 0,
            p -> new Pair<>(p.tail, p.head % p.tail))
            .map(p -> p.tail)
            .reduce((acc, x) -> x)
            .orElse(1);
    }

    // do not include countRepeats function when submitting for gcd
    // gcd test cases checks for array usage
    static long countRepeats(int... array) {
        return IntStream.range(0, array.length-1)
            .filter(i -> array[i] == array[i+1]
                ? i == 0
                    ? true
                    : array[i] != array[i-1]
                : false)
            .count();
    }
    
    static double normalizedMean(Stream<Integer> stream) {
        return stream.map(x -> new Normalize(x, 1, x, x))
            .reduce((acc, normalize) -> acc.add(normalize))
            .orElse(new Normalize(0, 0, 0, 0))
            .getNormalizedMean();
    }
}