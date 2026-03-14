package es.uniovi.raul.debug;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import es.uniovi.raul.debug.CommonChecks;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CommonChecks utility class.
 * Covers positive-path and error-path tests for all public static methods.
 */
class CommonChecksTest {

    // ----------------------------------------------------
    // isNonBlank tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @ParameterizedTest
    @ValueSource(strings = { "a", "abc", "  a", "ñ", "😀", "!@#", "\u00A0" })
    void isNonBlank_validStrings_returnTrue(String input) {
        assertTrue(CommonChecks.isNonBlank(input));
    }

    // Error-path tests -----------------
    @ParameterizedTest
    @ValueSource(strings = { "", "   ", "\t", "\n", "\u2003" })
    void isNonBlank_blankStrings_returnFalse(String input) {
        assertFalse(CommonChecks.isNonBlank(input));
    }

    @Test
    void isNonBlank_null_returnsFalse() {
        assertFalse(CommonChecks.isNonBlank(null));
    }

    // ----------------------------------------------------
    // allNonNull tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void allNonNull_nonNullElements_returnsTrue() {
        assertTrue(CommonChecks.allNonNull(List.of(1, 2, 3)));
        assertTrue(CommonChecks.allNonNull(List.of("a", "b")));
        assertTrue(CommonChecks.allNonNull(Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void allNonNull_nullCollection_returnsFalse() {
        assertFalse(CommonChecks.allNonNull(null));
    }

    @Test
    void allNonNull_containsNull_returnsFalse() {
        assertFalse(CommonChecks.allNonNull(Arrays.asList(1, null, 3)));
        assertFalse(CommonChecks.allNonNull(Arrays.asList("a", null, "b")));
    }

    // ----------------------------------------------------
    // allDistinct tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void allDistinct_distinctNonNull_returnsTrue() {
        assertTrue(CommonChecks.allDistinct(List.of(1, 2, 3)));
        assertTrue(CommonChecks.allDistinct(List.of("a", "b", "c")));
        assertTrue(CommonChecks.allDistinct(Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void allDistinct_nullCollection_returnsFalse() {
        assertFalse(CommonChecks.allDistinct(null));
    }

    @Test
    void allDistinct_containsNull_returnsFalse() {
        assertFalse(CommonChecks.allDistinct(Arrays.asList(1, null, 3)));
    }

    @Test
    void allDistinct_containsDuplicates_returnsFalse() {
        assertFalse(CommonChecks.allDistinct(Arrays.asList(1, 2, 2)));
        assertFalse(CommonChecks.allDistinct(Arrays.asList("a", "b", "a")));
    }

    // ----------------------------------------------------
    // allNonBlank tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void allNonBlank_allValidStrings_returnsTrue() {
        assertTrue(CommonChecks.allNonBlank(List.of("a", "b", "c")));
        assertTrue(CommonChecks.allNonBlank(Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void allNonBlank_nullCollection_returnsFalse() {
        assertFalse(CommonChecks.allNonBlank(null));
    }

    @Test
    void allNonBlank_containsNull_returnsFalse() {
        assertFalse(CommonChecks.allNonBlank(Arrays.asList("a", null, "b")));
    }

    @Test
    void allNonBlank_containsBlank_returnsFalse() {
        assertFalse(CommonChecks.allNonBlank(Arrays.asList("a", "", "b")));
        assertFalse(CommonChecks.allNonBlank(Arrays.asList("a", "   ", "b")));
    }

    // ----------------------------------------------------
    // allNonBlankAndDistinct tests
    // ----------------------------------------------------

    // Positive-path tests -----------------
    @Test
    void allNonBlankAndDistinct_allValidDistinctStrings_returnsTrue() {
        assertTrue(CommonChecks.allNonBlankAndDistinct(List.of("a", "b", "c")));
        assertTrue(CommonChecks.allNonBlankAndDistinct(Collections.emptyList()));
    }

    // Error-path tests -----------------
    @Test
    void allNonBlankAndDistinct_nullCollection_returnsFalse() {
        assertFalse(CommonChecks.allNonBlankAndDistinct(null));
    }

    @Test
    void allNonBlankAndDistinct_containsNull_returnsFalse() {
        assertFalse(CommonChecks.allNonBlankAndDistinct(Arrays.asList("a", null, "b")));
    }

    @Test
    void allNonBlankAndDistinct_containsBlank_returnsFalse() {
        assertFalse(CommonChecks.allNonBlankAndDistinct(Arrays.asList("a", "", "b")));
        assertFalse(CommonChecks.allNonBlankAndDistinct(Arrays.asList("a", "   ", "b")));
    }

    @Test
    void allNonBlankAndDistinct_containsDuplicates_returnsFalse() {
        assertFalse(CommonChecks.allNonBlankAndDistinct(Arrays.asList("a", "b", "a")));
    }
}
