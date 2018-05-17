package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Сервис для приема JMS сообщения
 */
@MessageDriven(name = "DataBaseJmsRecieverService", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:jboss/exported/jms/queue/database"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")})
public class DataBaseJmsRecieverService implements MessageListener {
    private final Logger log = LoggerFactory.getLogger(DataBaseJmsRecieverService.class);

    /**
     * Прием JMS сообщения
     *
     * @param message JMS сообщение
     */
    @Override
    public void onMessage(Message message) {
        try {
            log.info("Massage: " + message.getBody(String.class));
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
        } catch (JMSException e) {
            log.error(e.getMessage(), e);
        }
    }
}
