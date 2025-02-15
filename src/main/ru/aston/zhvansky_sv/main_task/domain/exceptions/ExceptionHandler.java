package domain.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility class for exception handling.
 */
public final class ExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * Private constructor to prevent instantiation of the class.
     * This class is intended to be used statically.
     */
    private ExceptionHandler() {
    }

    /**
     * Handles the exception depending on the type.
     *
     * @param e The exception for handling.
     */
    public static void handleException(final Exception e) {
        if (e instanceof NullValueException) {
            log.warn("Null value exception: ", e);
        } else if (e instanceof IllegalOperationException) {
            log.warn("Illegal operation exception: ", e);
        } else {
            log.warn("Unexpected exception: ", e);
        }
    }
}
