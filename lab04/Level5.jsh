class AddOne implements Transformer<Integer,Integer> {
    public Integer transform(Integer t) { return t + 1; }
}

class StringLength implements Transformer<String,Integer> {
    public Integer transform(String t) { return t.length(); }
}

Box.of(4).map(new AddOne())
Box<Integer> empty = Box.empty()
empty.map(new AddOne())
Box.of("string").map(new StringLength())
Box.of("string").map(new StringLength()).map(new AddOne())
Box.of("string").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne())
Box.of("chocolates").map(new StringLength()).filter(new DivisibleBy(5)).map(new AddOne())
Box<String> empty = Box.empty()
empty.map(new StringLength())

class AlwaysNull implements Transformer<Integer,Object> {
    public Object transform(Integer t) { return null; }
}

Box.of(4).map(new AlwaysNull())
new LastDigitsOfHashCode(4).transform("string")
new LastDigitsOfHashCode(4).transform(123456)
Box.of("string").map(new LastDigitsOfHashCode(2))
Box.of(123456).map(new LastDigitsOfHashCode(5))
