package task3;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Form a subclass Collection2 from Collection and add a default method
 * void forEachIf(Consumer<T> action, Predicate<T> filter)
 * that applies action to each element for which filter returns true.
 * @param <E>
 */
public interface Collection2<E> extends Collection<E> {
    default void forEachIf(Consumer<? super E> action, Predicate<? super E> filter) {
        Objects.requireNonNull(action);
        for (E e : this) {
            if (filter.test(e))
                action.accept(e);
        }
    }
}
