class AlwaysTrue<T> implements BooleanCondition<T> {
    public boolean test(T t) { return true; }
}

class AlwaysFalse<T> implements BooleanCondition<T> {
    public boolean test(T t) { return false; }
}

Box.ofNullable(4).filter(new AlwaysTrue<>())
Box.ofNullable(null).filter(new AlwaysTrue<>())
Box.ofNullable("string").filter(new AlwaysFalse<>())
Box.empty().filter(new AlwaysFalse<>())
