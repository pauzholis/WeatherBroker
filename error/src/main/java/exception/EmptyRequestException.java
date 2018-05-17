package exception;

/**
 * Исключение возникающее при незаполнении формы на странице
 */
public class EmptyRequestException extends Exception {
    public EmptyRequestException(String message) {
        super(message);
    }
}
