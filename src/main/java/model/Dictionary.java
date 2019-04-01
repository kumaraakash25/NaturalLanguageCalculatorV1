package model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Dictionary {

    // A statically loaded dictionary for looking up the numbers and mathematical operations based on plain english words
    // defined in Dictionary and Numbers enumerations
    private static final Map<String, String> DICTIONARY;

    static {
        DICTIONARY = new HashMap<>();
        Stream.of(Operations.values()).forEach(element -> DICTIONARY.put(element.name(),
                String.valueOf(element.getOperation())));
        Stream.of(Numbers.values()).forEach(element -> DICTIONARY.put(element.name(),
                String.valueOf(element.getNumericEquivalent())));
    }

    public static Map<String, String> getDictionary() {
        return DICTIONARY;
    }
}
