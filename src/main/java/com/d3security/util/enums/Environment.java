package com.d3security.util.enums;

/**
 * This enum represents a testing environment
 * Created by Lucy on 5/5/2014.
 */
public enum Environment {
    DEVELOPMENT("Development", "DEV"), STAGING("Staging", "STG"), LIVE("Live", "Live"), PROOF_OF_CONCEPT(
            "proof_of_concept", "poc"), MULTIPLE("Multiple", "Multiple"), LTV("Live Test VIP", "LTV"), AUTOMATION_UNIT_TESTING("automation", "automation");
    private static Environment CURRENT;
    private final String environment;
    private final String abbreviation;

    private Environment(final String environment, String abbreviation) {
        this.environment = environment;
        this.abbreviation = abbreviation;
    }

    public synchronized static void setCurrent(Environment environment) {
        CURRENT = environment;
    }

    public static Environment getCurrentEnvironment() {
        return CURRENT;
    }


    /**
     * Parse an Environment based on the given string
     *
     * @param environment a string value presenting an Environment constant
     * @return the resolved Environment constant or null if no matching Environment found
     */
    public static Environment parse(final String environment) {
        if (environment == null) {
            return null;
        }
        for (final Environment currentEnvironment : values()) {
            if (currentEnvironment.toString().equalsIgnoreCase(
                    environment.toString())) {
                return currentEnvironment;
            }
        }
        return null;
    }

    /**
     * Parse an Environment based on the given string that represents its abbreviation
     *
     * @param abbreviatedEnvironment a string value presenting an Environment constant
     * @return the resolved Environment constant or null if no matching Environment found
     */
    public static Environment parseAbbreviation(
            final String abbreviatedEnvironment) {
        for (final Environment currentEnvironment : values()) {
            if (currentEnvironment.getAbbreviation().equalsIgnoreCase(
                    abbreviatedEnvironment)) {
                return currentEnvironment;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return environment;
    }

    /**
     * Return the Environment abbreviation. This value is based on Test Link Platforms which aren't always abbreviations :)
     *
     * @return a string value representing the designated Environment
     */
    public String getAbbreviation() {
        return abbreviation;
    }


}
