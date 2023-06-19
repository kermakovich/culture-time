package solvd.ermakovich.ct.domain.exception;

/**
 * @author Ermakovich Kseniya
 */
public class EntityDoesNotExistException extends RuntimeException {

    public EntityDoesNotExistException(final String message) {
        super(message);
    }

}
