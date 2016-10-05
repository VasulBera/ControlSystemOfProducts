package IntegrationTests.ConstantsUtils;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

/**
 * @author Solomiia Riznychok
 * @version 1.0
 * @since 2016-11-08
 * The RandomConstantValues.java class created for definition all random constant values.
 * @see IntegrationTests.TestsSuite.TestPostMethod;
 */

public abstract class RandomConstantValues {
    public static final String ID_ETITIES_RANDOM_VALID = randomAlphabetic(2);
    public static final String NAME_ETITIES_RANDOM_VALID = randomAlphabetic(1);
    public static final String SHEMA_NAME_ETITIES_RANDOM_VALID = randomAlphabetic(1);
    public static final String TABLE_NAME_ETITIES_RANDOM_VALID = randomAlphabetic(1);
    public static final String ID_FIELDS_RANDOM_VALID = randomAlphabetic(3);
    public static final String NAME_FIELDS_RANDOM_VALID = randomAlphabetic(1);
    public static final String COLUMN_NAME_FIELDS_RANDOM_VALID = randomAlphabetic(1);
    public static final String TYPE_FIELDS_RANDOM = "INT";
    public static final Integer LENGTH_FIELDS_RANDOM_VALID = Integer.valueOf(randomNumeric(2));
}
