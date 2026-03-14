package es.uniovi.raul.debug;

import org.junit.jupiter.api.*;

import static es.uniovi.raul.debug.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.*;

/**
 * Unit tests for Assertions utility class.
 * Covers positive-path and error-path tests for all public static methods.
 * All methods throw AssertionError when assertions fail.
 */
class AssertionsTest {

    // ----------------------------------------------------
    // assertNonNull tests
    // ----------------------------------------------------
    // Positive-path tests -----------------
    @Test
    void assertNonNull_validObject_doesNotThrow() {
        assertDoesNotThrow(() -> assertNonNull(new Object()));
    }

    // Error-path tests -----------------
    @Test
    void assertNonNull_nullObject_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertNonNull(null));
        assertTrue(ex.getMessage().contains("Object cannot be null"));
    }

    // ----------------------------------------------------
    // assertNonBlank tests
    // ----------------------------------------------------
    // Positive-path tests -----------------
    @ParameterizedTest
    @ValueSource(strings = { "a", "abc", "  a", "\u00f1", "\ud83d\ude00", "!@#", "\u00A0" })
    void assertNonBlank_validStrings_doesNotThrow(String input) {
        assertDoesNotThrow(() -> assertNonBlank(input));
    }

    // Error-path tests -----------------
    @ParameterizedTest
    @ValueSource(strings = { "", "   ", "\t", "\n", "\u2003" })
    void assertNonBlank_blankStrings_throwsAssertionError(String input) {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertNonBlank(input));
        assertTrue(ex.getMessage().contains("String cannot be null or empty"));
    }

    @Test
    void assertNonBlank_nullString_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertNonBlank(null));
        assertTrue(ex.getMessage().contains("String cannot be null or empty"));
    }

    // ----------------------------------------------------
    // assertAllNonNull tests
    // ----------------------------------------------------
    // Positive-path tests -----------------
    @Test
    void assertAllNonNull_nonNullElements_doesNotThrow() {
        assertDoesNotThrow(() -> assertAllNonNull(List.of(1, 2, 3)));
        assertDoesNotThrow(() -> assertAllNonNull(List.of("a", "b")));
        assertDoesNotThrow(() -> assertAllNonNull(Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void assertAllNonNull_nullCollection_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertAllNonNull(null));
        assertTrue(ex.getMessage().contains("Collection cannot be null and cannot contain null elements"));
    }

    @Test
    void assertAllNonNull_containsNull_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertAllNonNull(Arrays.asList(1, null, 3)));
        assertTrue(ex.getMessage().contains("Collection cannot be null and cannot contain null elements"));
    }

    // ----------------------------------------------------
    // assertAllDistinct tests
    // ----------------------------------------------------
    // Positive-path tests -----------------
    @Test
    void assertAllDistinct_distinctElements_doesNotThrow() {
        assertDoesNotThrow(() -> assertAllDistinct(List.of(1, 2, 3)));
        assertDoesNotThrow(() -> assertAllDistinct(List.of("a", "b", "c")));
        assertDoesNotThrow(() -> assertAllDistinct(Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void assertAllDistinct_nullCollection_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertAllDistinct(null));
        assertTrue(ex.getMessage().contains("Collection cannot be null nor contain null or duplicate elements"));
    }

    @Test
    void assertAllDistinct_containsNull_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertAllDistinct(Arrays.asList("a", null, "b")));
        assertTrue(ex.getMessage().contains("Collection cannot be null nor contain null or duplicate elements"));
    }

    @Test
    void assertAllDistinct_containsDuplicates_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertAllDistinct(Arrays.asList("a", "b", "a")));
        assertTrue(ex.getMessage().contains("Collection cannot be null nor contain null or duplicate elements"));
    }

    // ----------------------------------------------------
    // assertAllNonBlank tests
    // ----------------------------------------------------
    // Positive-path tests -----------------
    @Test
    void assertAllNonBlank_validStrings_doesNotThrow() {
        assertDoesNotThrow(() -> assertAllNonBlank(List.of("a", "b", "c")));
        assertDoesNotThrow(() -> assertAllNonBlank(Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void assertAllNonBlank_nullCollection_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertAllNonBlank(null));
        assertTrue(ex.getMessage().contains("String collection cannot be null nor contain null or blank elements"));
    }

    @Test
    void assertAllNonBlank_containsNull_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertAllNonBlank(Arrays.asList("a", null, "b")));
        assertTrue(ex.getMessage().contains("String collection cannot be null nor contain null or blank elements"));
    }

    @Test
    void assertAllNonBlank_containsBlank_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertAllNonBlank(Arrays.asList("a", "", "b")));
        assertTrue(ex.getMessage().contains("String collection cannot be null nor contain null or blank elements"));
    }

    // ----------------------------------------------------
    // assertAllNonBlankAndDistinct tests
    // ----------------------------------------------------
    // Positive-path tests -----------------
    @Test
    void assertAllNonBlankAndDistinct_validStrings_doesNotThrow() {
        assertDoesNotThrow(() -> assertAllNonBlankAndDistinct(List.of("a", "b", "c")));
        assertDoesNotThrow(() -> assertAllNonBlankAndDistinct(Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void assertAllNonBlankAndDistinct_nullCollection_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertAllNonBlankAndDistinct(null));
        assertTrue(ex.getMessage()
                .contains("String collection cannot be null nor contain null, blank, or duplicate elements"));
    }

    @Test
    void assertAllNonBlankAndDistinct_containsNull_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class,
                () -> assertAllNonBlankAndDistinct(Arrays.asList("a", null, "b")));
        assertTrue(ex.getMessage()
                .contains("String collection cannot be null nor contain null, blank, or duplicate elements"));
    }

    @Test
    void assertAllNonBlankAndDistinct_containsBlank_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class,
                () -> assertAllNonBlankAndDistinct(Arrays.asList("a", "", "b")));
        assertTrue(ex.getMessage()
                .contains("String collection cannot be null nor contain null, blank, or duplicate elements"));
    }

    @Test
    void assertAllNonBlankAndDistinct_containsDuplicates_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class,
                () -> assertAllNonBlankAndDistinct(Arrays.asList("a", "b", "a")));
        assertTrue(ex.getMessage()
                .contains("String collection cannot be null nor contain null, blank, or duplicate elements"));
    }

    // ----------------------------------------------------
    // assertNonNullArgs tests
    // ----------------------------------------------------
    // Positive-path tests -----------------
    @Test
    void assertNonNullArgs_validArgs_doesNotThrow() {
        assertDoesNotThrow(() -> assertNonNullArgs("a", 1, new Object()));
    }

    // Error-path tests -----------------
    @Test
    void assertNonNullArgs_nullArgumentsArray_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertNonNullArgs((Object[]) null));
        assertTrue(ex.getMessage().contains("At least one argument must be provided to 'assertNonNullArgs'"));
    }

    @Test
    void assertNonNullArgs_emptyArgumentsArray_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertNonNullArgs());
        assertTrue(ex.getMessage().contains("At least one argument must be provided to 'assertNonNullArgs'"));
    }

    @Test
    void assertNonNullArgs_containsNull_throwsAssertionError() {
        AssertionError ex = assertThrows(AssertionError.class, () -> assertNonNullArgs("a", null, "b"));
        assertTrue(ex.getMessage().contains("Parameter #2 cannot be null"));
    }
}
