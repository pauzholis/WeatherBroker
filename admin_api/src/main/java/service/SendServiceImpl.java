package service;

import exception.EmptyRequestException;
import model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class SendServiceImpl implements SendService {
    private final Logger log = LoggerFactory.getLogger(SendService.class);

    @Resource(mappedName = "java:jboss/exported/jms/topic/weather")
    private Topic topic;

    @Inject
    private JMSContext context;

    private MessageService messageService;

    @Inject
    public SendServiceImpl(MessageService messageService) {
        this.messageService = messageService;
    }

    public SendServiceImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMassage(String txt) throws EmptyRequestException {
        if (txt == null) {
            throw new EmptyRequestException("Field \"City\" is empty");
        }
        City writeCity = new City(txt);
        log.info("Sending message to yahoo_weather module: " + txt);
        String xml = messageService.createXmlMessage(writeCity);
        context.createProducer().send(topic, xml);
    }
}
