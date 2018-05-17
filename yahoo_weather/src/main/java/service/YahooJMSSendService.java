package service;

import exception.WeatherBrokerServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Сервис для отправки JMS сообщения
 */
@RequestScoped
public class YahooJMSSendService {
    private final Logger log = LoggerFactory.getLogger(YahooJMSSendService.class);

    @Resource(mappedName = "java:jboss/exported/jms/queue/database")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    public YahooJMSSendService() {
    }

    /**
     * Отправка JMS сообщения
     *
     * @param xml Объект Forecast в виде xml
     * @throws WeatherBrokerServiceException Исключение возникающее при отправке JMS сообщения
     */
    public void sendMessage(String xml) throws WeatherBrokerServiceException {
        if (xml == null) {
            throw new WeatherBrokerServiceException("Empty JMS message");
        }
        jmsContext.createProducer().send(queue, xml);
    }
}
