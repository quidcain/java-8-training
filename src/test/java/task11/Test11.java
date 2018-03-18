package task11;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Write a method lexicographicComparator(String... fieldNames)
 * that yields a comparator that compares the given fields in the given order.
 * For example, a lexicographicComparator("lastname", "firstname") takes two objects and,
 * using reflection, gets the values of the lastname field.
 * If they are different, return the difference, otherwise move on to the firstname field.
 * If all fields match, return 0.
 */
public class Test11 {
    public static <T> Comparator<T> lexicographicComparator(String... fieldNames) {
        return Stream.of(fieldNames).map(fieldName -> Comparator.<T, Comparable>comparing(object -> {
            try {
                final Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                return (Comparable) field.get(object);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        })).reduce(Comparator::thenComparing).get();
    }

    @Test
    public void testLexicographicComparator() {
        Cat felix = new Cat("Felix", 1);
        Cat oscar = new Cat("Oscar", 1);
        Comparator<Cat> nameComparator = lexicographicComparator("name");
        Comparator<Object> ageNameComparator = lexicographicComparator("age", "name");
        assertEquals("Felix".compareTo("Oscar"), nameComparator.compare(felix, oscar));
        assertEquals("Felix".compareTo("Oscar"), ageNameComparator.compare(felix, oscar));
        assertEquals(0, ageNameComparator.compare(felix, new Cat("Felix", 1)));
    }

    private class Cat {
        private String name;
        private int age;

        Cat(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
