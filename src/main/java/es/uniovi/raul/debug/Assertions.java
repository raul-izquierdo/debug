package es.uniovi.raul.debug;

import static es.uniovi.raul.debug.CommonChecks.*;

import java.util.Collection;

/**
 * Utility class for checking preconditions in private methods, and postconditions and invariants in all methods. For preconditions in public methods, use {@code Preconditions} instead.
 * <p>
 * All methods throw {@code AssertionError} when assertions fail.
 * <ul>
 * <li>{@code assertNonNullArgs()} is for checking arguments in private methods.
 * <li>Other methods check postconditions and invariants in all methods (e.g., {@code assertNonBlank()}, {@code assertAllNonBlank()}, etc.). This can be done with assert statements directly, but these methods provide clearer error messages and avoid writing custom messages every time.</li>
 * </ul>
 * @see Preconditions for precondition checks that throw {@code IllegalArgumentException}
 * @see CommonChecks for utility methods that check common conditions (e.g., non-blank strings, collections with no nulls, etc.)
 *
 */
public class Assertions {

    static {
        boolean assertsEnabled = false;
        assert assertsEnabled = true; // Intentional side effect
        ASSERTS_ENABLED = assertsEnabled;
    }

    /** Indicates whether assertions are enabled in the JVM. */
    public static final boolean ASSERTS_ENABLED;

    //# ------------------------------------------------------------------
    //# Common shortcuts for specific assertions to avoid writing custom messages every time
    //# ------------------------------------------------------------------

    public static void assertNonNull(Object value) {
        assert value != null : "Object cannot be null";
    }

    public static void assertNonBlank(String value) {
        assert isNonBlank(value) : "String cannot be null or empty";
    }

    public static void assertAllNonNull(Collection<?> values) {
        assert allNonNull(values) : "Collection cannot be null and cannot contain null elements";
    }

    public static void assertAllDistinct(Collection<?> values) {
        assert allDistinct(values) : "Collection cannot be null nor contain null or duplicate elements";
    }

    public static void assertAllNonBlank(Collection<String> values) {
        assert allNonBlank(values) : "String collection cannot be null nor contain null or blank elements";
    }

    public static void assertAllNonBlankAndDistinct(Collection<String> values) {
        assert allNonBlankAndDistinct(values)
                : "String collection cannot be null nor contain null, blank, or duplicate elements";
    }

    //# ------------------------------------------------------------------
    //# Shortcut to check that none of the parameters are null
    //# ------------------------------------------------------------------

    public static void assertNonNullArgs(Object... arguments) {

        assert (arguments != null) && arguments.length > 0
                : "At least one argument must be provided to 'assertNonNullArgs'";

        for (int i = 0; i < arguments.length; i++)
            assert arguments[i] != null : "Parameter #" + (i + 1) + " cannot be null";
    }

}
