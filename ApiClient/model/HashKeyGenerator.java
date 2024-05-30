package model;

public class HashKeyGenerator {

    public static int generateKey(String... values) {
        StringBuilder combined = new StringBuilder();
        for (String value : values) {
            combined.append(value.trim().toLowerCase());
        }
        return combined.toString().hashCode();
    }
}
