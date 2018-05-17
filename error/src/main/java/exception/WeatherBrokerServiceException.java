package exception;

/**
 * Исключение возникающее при ошибке отправки JMS сообщения
 */
public class WeatherBrokerServiceException extends java.lang.Exception {
    public WeatherBrokerServiceException(String message) {
        super(message);
    }
}
