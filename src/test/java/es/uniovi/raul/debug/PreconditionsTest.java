package es.uniovi.raul.debug;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import es.uniovi.raul.debug.Preconditions;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Preconditions utility class.
 * Covers positive-path and error-path tests for all public static methods.
 */
class PreconditionsTest {

    // ----------------------------------------------------
    // require tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void require_trueCondition_doesNotThrow() {
        assertDoesNotThrow(() -> Preconditions.require(true, "Should not throw"));
    }

    // Error-path tests -----------------
    @Test
    void require_falseCondition_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class, () -> Preconditions.require(false, "fail"));
        assertTrue(ex.getMessage().contains("Precondition failed: fail"));
    }

    // ----------------------------------------------------
    // requireNonNull tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void requireNonNull_validObject_doesNotThrow() {
        assertDoesNotThrow(() -> Preconditions.requireNonNull("obj", new Object()));
    }

    // Error-path tests -----------------
    @Test
    void requireNonNull_nullObject_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class, () -> Preconditions.requireNonNull("obj", null));
        assertTrue(ex.getMessage().contains("Parameter 'obj' cannot be null"));
    }

    // ----------------------------------------------------
    // requireNonBlank tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @ParameterizedTest
    @ValueSource(strings = { "a", "abc", "  a", "ñ", "😀", "!@#", "\u00A0" })
    void requireNonBlank_validStrings_doesNotThrow(String input) {
        assertDoesNotThrow(() -> Preconditions.requireNonBlank("text", input));
    }

    // Error-path tests -----------------
    @ParameterizedTest
    @ValueSource(strings = { "", "   ", "\t", "\n", "\u2003" })
    void requireNonBlank_blankStrings_throwsIllegalArgumentException(String input) {
        var ex = assertThrows(IllegalArgumentException.class, () -> Preconditions.requireNonBlank("text", input));
        assertTrue(ex.getMessage().contains("Parameter 'text' cannot be null or empty"));
    }

    @Test
    void requireNonBlank_nullString_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class, () -> Preconditions.requireNonBlank("text", null));
        assertTrue(ex.getMessage().contains("Parameter 'text' cannot be null or empty"));
    }

    // ----------------------------------------------------
    // requireAllNonNull tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void requireAllNonNull_allNonNull_doesNotThrow() {
        assertDoesNotThrow(() -> Preconditions.requireAllNonNull("list", List.of(1, 2, 3)));
        assertDoesNotThrow(() -> Preconditions.requireAllNonNull("list", List.of("a", "b")));
        assertDoesNotThrow(() -> Preconditions.requireAllNonNull("list", Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void requireAllNonNull_nullCollection_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class, () -> Preconditions.requireAllNonNull("list", null));
        assertTrue(ex.getMessage().contains("Parameter 'list' cannot be null nor contain null elements"));
    }

    @Test
    void requireAllNonNull_containsNull_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> Preconditions.requireAllNonNull("list", Arrays.asList(1, null, 3)));
        assertTrue(ex.getMessage().contains("Parameter 'list' cannot be null nor contain null elements"));
    }

    // ----------------------------------------------------
    // requireAllDistinct tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void requireAllDistinct_allDistinct_doesNotThrow() {
        assertDoesNotThrow(() -> Preconditions.requireAllDistinct("list", List.of(1, 2, 3)));
        assertDoesNotThrow(() -> Preconditions.requireAllDistinct("list", List.of("a", "b", "c")));
        assertDoesNotThrow(() -> Preconditions.requireAllDistinct("list", Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void requireAllDistinct_nullCollection_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class, () -> Preconditions.requireAllDistinct("list", null));
        assertTrue(ex.getMessage().contains("Parameter 'list' cannot be null nor contain null or duplicate elements"));
    }

    @Test
    void requireAllDistinct_containsNull_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> Preconditions.requireAllDistinct("list", Arrays.asList(1, null, 3)));
        assertTrue(ex.getMessage().contains("Parameter 'list' cannot be null nor contain null or duplicate elements"));
    }

    @Test
    void requireAllDistinct_containsDuplicates_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> Preconditions.requireAllDistinct("list", Arrays.asList(1, 2, 2)));
        assertTrue(ex.getMessage().contains("Parameter 'list' cannot be null nor contain null or duplicate elements"));
    }

    // ----------------------------------------------------
    // requireAllNonBlank tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void requireAllNonBlank_allValidStrings_doesNotThrow() {
        assertDoesNotThrow(() -> Preconditions.requireAllNonBlank("list", List.of("a", "b", "c")));
        assertDoesNotThrow(() -> Preconditions.requireAllNonBlank("list", Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void requireAllNonBlank_nullCollection_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class, () -> Preconditions.requireAllNonBlank("list", null));
        assertTrue(ex.getMessage().contains("Parameter 'list' cannot be null nor contain null or blank elements"));
    }

    @Test
    void requireAllNonBlank_containsNull_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> Preconditions.requireAllNonBlank("list", Arrays.asList("a", null, "b")));
        assertTrue(ex.getMessage().contains("Parameter 'list' cannot be null nor contain null or blank elements"));
    }

    @Test
    void requireAllNonBlank_containsBlank_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> Preconditions.requireAllNonBlank("list", Arrays.asList("a", "", "b")));
        assertTrue(ex.getMessage().contains("Parameter 'list' cannot be null nor contain null or blank elements"));
    }

    // ----------------------------------------------------
    // requireAllNonBlankAndDistinct tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void requireAllNonBlankAndDistinct_allValidDistinctStrings_doesNotThrow() {
        assertDoesNotThrow(() -> Preconditions.requireAllNonBlankAndDistinct("list", List.of("a", "b", "c")));
        assertDoesNotThrow(() -> Preconditions.requireAllNonBlankAndDistinct("list", Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void requireAllNonBlankAndDistinct_nullCollection_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> Preconditions.requireAllNonBlankAndDistinct("list", null));
        assertTrue(ex.getMessage()
                .contains("Parameter 'list' cannot be null nor contain null, blank, or duplicate elements"));
    }

    @Test
    void requireAllNonBlankAndDistinct_containsNull_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> Preconditions.requireAllNonBlankAndDistinct("list", Arrays.asList("a", null, "b")));
        assertTrue(ex.getMessage()
                .contains("Parameter 'list' cannot be null nor contain null, blank, or duplicate elements"));
    }

    @Test
    void requireAllNonBlankAndDistinct_containsBlank_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> Preconditions.requireAllNonBlankAndDistinct("list", Arrays.asList("a", "", "b")));
        assertTrue(ex.getMessage()
                .contains("Parameter 'list' cannot be null nor contain null, blank, or duplicate elements"));
    }

    @Test
    void requireAllNonBlankAndDistinct_containsDuplicates_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class,
                () -> Preconditions.requireAllNonBlankAndDistinct("list", Arrays.asList("a", "b", "a")));
        assertTrue(ex.getMessage()
                .contains("Parameter 'list' cannot be null nor contain null, blank, or duplicate elements"));
    }

    // ----------------------------------------------------
    // requireNonNullArgs tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void requireNonNullArgs_allNonNull_doesNotThrow() {
        assertDoesNotThrow(() -> Preconditions.requireNonNullArgs("a", 1, new Object()));
    }

    // Error-path tests -----------------
    @Test
    void requireNonNullArgs_nullArgument_throwsIllegalArgumentException() {
        var ex = assertThrows(IllegalArgumentException.class, () -> Preconditions.requireNonNullArgs("a", null, 1));
        assertTrue(ex.getMessage().contains("Precondition failed: Parameter #2 must not be null"));
    }

    @Test
    void requireNonNullArgs_noArguments_assertionError() {
        AssertionError error = assertThrows(AssertionError.class, () -> Preconditions.requireNonNullArgs());
        assertTrue(error.getMessage().contains("At least one argument must be provided"));
    }
}
