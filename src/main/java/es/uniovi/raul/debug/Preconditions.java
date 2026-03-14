package es.uniovi.raul.debug;

import static es.uniovi.raul.debug.CommonChecks.*;

import java.util.Collection;

/**
 * Utility class for cheking preconditions in public methods. For checking preconditions in private methods and for postconditions and invariants in all methods, use {@code Assertions} instead.
 * <p>
 * All methods throw {@code IllegalArgumentException} when preconditions fail (even null values passed).
 * <p>
 * <b>Expected usage pattern:</b>
 * <ol>
 *   <li>First, check all parameters using {@code requireNonNullArgs()}, preferably passing them in the same order as the method signature for clearer error messages</li>
 *   <li>Then, add additional preconditions for specific parameter types (e.g., {@code requireNonBlank()}, {@code requireAllNonBlank()}, etc.)</li>
 * </ol>
 * <p>
 * Example:
 * <pre>
 * public static void f(Object object, String text, List<String> words, List<Integer> names) {
 *
 *     // First, check all parameters for null in a single call (same order as the method signature)
 *     requireNonNullArgs(object, text, words, names);
 *
 *     // Additional checks for specific parameters
 *     requireNonBlank("text", text);
 *     requireAllNonBlank("words", words);
 *     require(words.size() > 5, "`words` must contain more than 5 elements");
 *     require(allNonBlankAndDistinct(names), "`names` cannot contain null elements, blanks, or duplicates");
 *
 *     // Code starts here...
 * }
 * </pre>
 * @see Assertions for precondition checks that throw {@code AssertionError}
 * @see CommonChecks for utility methods to check common conditions (e.g., non-blank strings, collections with no nulls, etc.)
 */
public final class Preconditions {

    //# ------------------------------------------------------------------
    //# Basic precondition check
    //# ------------------------------------------------------------------

    /**
     * Validates that a condition is true; throws an exception with the provided message if it fails.
     *
     * @param condition the condition to validate
     * @param message the error message to include if the condition is false
     * @throws IllegalArgumentException if the condition is false
     */
    public static void require(boolean condition, String message) {
        if (!condition)
            throw new IllegalArgumentException("Precondition failed: " + message);
    }

    //# ------------------------------------------------------------------
    //# Common shortcuts for specific preconditions
    //# ------------------------------------------------------------------

    /**
     * Validates that an object is not null.
     * <p>
     * Equivalent to {@link #require(boolean, String) precondition(obj != null, "Argument '" + paramName + "' cannot be null")}.
     *
     * @param value the object to validate
     * @param paramName the name of the parameter being validated
     * @throws IllegalArgumentException if the object is null
     */
    public static void requireNonNull(String paramName, Object value) {
        assert (paramName != null) && !paramName.isBlank() : "The parameter name should not be null or blank";

        require(value != null, "Parameter '" + paramName + "' cannot be null");
    }

    /**
     * Validates that a string is not null or blank.
     * <p>
     * Equivalent to {@link #require(boolean, String) precondition(notBlank(text), "Parameter '" + paramName + "' (string) cannot be null or empty")}.
     *
     * @param value the string to validate
     * @param paramName the name of the parameter being validated
     * @throws IllegalArgumentException if the string is null or blank
     */
    public static void requireNonBlank(String paramName, String value) {
        assert (paramName != null) && !paramName.isBlank() : "The parameter name should not be null or blank";

        require(isNonBlank(value), "Parameter '" + paramName + "' cannot be null or empty");
    }

    public static void requireAllNonNull(String paramName, Collection<?> values) {
        assert (paramName != null) && !paramName.isBlank() : "The parameter name should not be null or blank";

        require(allNonNull(values),
                "Parameter '" + paramName + "' cannot be null nor contain null elements");
    }

    public static void requireAllDistinct(String paramName, Collection<?> values) {
        assert (paramName != null) && !paramName.isBlank() : "The parameter name should not be null or blank";

        require(allDistinct(values),
                "Parameter '" + paramName + "' cannot be null nor contain null or duplicate elements");
    }

    public static void requireAllNonBlank(String paramName, Collection<String> values) {
        assert (paramName != null) && !paramName.isBlank() : "The parameter name should not be null or blank";

        require(allNonBlank(values),
                "Parameter '" + paramName + "' cannot be null nor contain null or blank elements");
    }

    public static void requireAllNonBlankAndDistinct(String paramName, Collection<String> values) {
        assert (paramName != null) && !paramName.isBlank() : "The parameter name should not be null or blank";

        require(allNonBlankAndDistinct(values),
                "Parameter '" + paramName + "' cannot be null nor contain null, blank, or duplicate elements");
    }

    //# ------------------------------------------------------------------
    //# Shortcut for check that none of the parameters are null
    //# ------------------------------------------------------------------

    /**
    * Validates that none of the provided arguments are null. Intended to be used to check all parameters of a method in a single call.
    * <p>
    * Pass all parameters in the same order as they are declared in the method signature to get meaningful error messages
    * that identify which parameter is null by its position (e.g., "Argument #1", "Argument #2").
    * <p>
    * Note: Arguments are numbered starting from 1 (not 0) in error messages.
    *
    * @example
    * <pre>
    * public void processData(String name, List<?> items, Map<?, ?> config) {
    *     requireNonNullArgs(name, items, config);  // Parameters in same order → clear error message if any is null
    *     // ... rest of method
    * }
    *
    * // Example with null argument:
    * processData("John", null, new HashMap<>());
    * // Throws: IllegalArgumentException: Precondition failed: Argument #2 cannot be null
    * </pre>
    * @param arguments the arguments to validate
    * @throws IllegalArgumentException if any argument is null or if no arguments are provided
    *
    */
    public static void requireNonNullArgs(Object... arguments) {

        assert (arguments != null) && arguments.length > 0
                : "At least one argument must be provided to 'requireNonNullArgs'";

        for (int i = 0; i < arguments.length; i++)
            if (arguments[i] == null)
                throw new IllegalArgumentException(
                        "Precondition failed: " + "Parameter #" + (i + 1) + " must not be null");
    }
}
