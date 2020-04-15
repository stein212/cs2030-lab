import java.util.function.Supplier;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

class IFLa<T> {
    Supplier<T> head;
    Supplier<IFL<T>> tail;
    /* FIELDS AND METHODS START: DO NOT REMOVE */

    /* FIELDS AND METHODS END: DO NOT REMOVE */

    IFL(Supplier<T> head, Supplier<IFL<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <T> IFL<T> of(List<? extends T> list) {
        /* OF START: DO NOT REMOVE!!! */
return new IFL<T>(() -> list.get(0), () -> list.size() > 0
   ? IFL.of(list.subList(1, list.size()))
   :  IFL.of(list));
        /* OF END: DO NOT REMOVE!!! */
    }

    Optional<T> findMatch(Predicate<? super T> predicate) {
        /* FINDMATCH START: DO NOT REMOVE!!! */
return Optional.ofNullable(head.get()).filter(predicate).orElse(() -> tail.get().filter(predicate));
        /* FINDMATCH END: DO NOT REMOVE!!! */
    }
}

/* ADDITIONAL CODE START: DO NOT REMOVE */

/* ADDITIONAL CODE END: DO NOT REMOVE */
