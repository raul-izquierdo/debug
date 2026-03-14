package es.uniovi.raul.debug;

import java.util.*;

/**
 * Utility class for common checks to be used in preconditions and assertions.
 */
public final class CommonChecks {

    /**
    * Checks whether a string is non-blank.
     *
     * @param text the string to check
     * @return true if the string is not null and contains at least one non-whitespace character, false otherwise
     */
    public static boolean isNonBlank(String text) {
        return text != null && !text.isBlank();
    }

    //# ------------------------------------------------------------------
    //# Collections
    //# ------------------------------------------------------------------

    /**
     * Checks if a collection contains no null elements.
     *
     * @param collection the collection to check
     * @return true if the collection is not null and contains no null elements, false otherwise
     */
    public static boolean allNonNull(Collection<?> collection) {
        // Do not use collection.contains(null):
        // Immutable collections (e.g., List.of(...)) throw NullPointerException when contains(null) is called.
        // Instead, iterate to check for null elements.
        if (collection == null)
            return false;
        for (Object element : collection)
            if (element == null)
                return false;
        return true;
    }

    /**
     * Checks if a collection contains no null elements and no duplicates.
     *
     * @param <T> the type of elements in the collection
     * @param collection the collection to check
     * @return true if the collection is not null and contains neither null elements nor duplicates, false otherwise
     */
    public static <T> boolean allDistinct(Collection<T> collection) {

        if (collection == null)
            return false;

        var seen = new HashSet<T>();
        for (T element : collection)
            if (element == null || !seen.add(element))
                return false;

        return true;
    }

    //# ------------------------------------------------------------------
    //# Collections of String
    //# ------------------------------------------------------------------

    /**
    * Checks if a collection of strings contains no null or blank elements.
    *
    * @param strings the collection to check
    * @return true if the collection is not null and contains no null or blank strings, false otherwise
    */
    public static boolean allNonBlank(Collection<String> strings) {
        return (strings != null) &&
                strings.stream().allMatch(s -> s != null && !s.isBlank());
    }

    /**
     * Checks if a collection of strings contains no null, blank, or duplicate elements.
     *
     * @param strings the collection to check
     * @return true if the collection is not null and contains no null, blank, or duplicate strings, false otherwise
     */
    public static boolean allNonBlankAndDistinct(Collection<String> collection) {

        if (collection == null)
            return false;

        var seen = new HashSet<String>();
        for (String element : collection)
            if (element == null || element.isBlank() || !seen.add(element))
                return false;

        return true;
    }

}
