package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringGenerator {

    private static final RandomStringUtils RANDOM = RandomStringUtils.secure();

    private RandomStringGenerator() {
    }

    public static String generateRandomString(int length) {
        return RANDOM.nextAlphabetic(length);
    }
}
