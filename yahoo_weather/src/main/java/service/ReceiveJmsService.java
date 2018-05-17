package service;


import exception.EmptyRequestException;
import exception.WeatherBrokerServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Сервис для приема JMS сообщения
 */
@MessageDriven(name = "ReceiveJmsService", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:jboss/exported/jms/topic/weather"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class ReceiveJmsService implements MessageListener {
    private final Logger log = LoggerFactory.getLogger(ReceiveJmsService.class);

    private ForecastService forecastService;

    public ReceiveJmsService() {
    }

    @Inject
    public ReceiveJmsService(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    /**
     * Прием JMS сообщения и передача его в метод дальнейшей отправки в базу данных
     *
     * @param message JMS сообщение
     */
    @Override
    public void onMessage(Message message) {
        try {
            log.info("Message: " + message.getBody(String.class));
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            forecastService.sendXmlToDataBaseFromYahooResponse(text);
        } catch (JMSException | EmptyRequestException | WeatherBrokerServiceException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
