package service;

import exception.WeatherBrokerServiceException;
import model.XmlModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class MessageServiceImpl implements MessageService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritDoc}
     */
    public String createXmlMessage(XmlModel objectForTransform) {
        String xmlString = "";
        try {
            JAXBContext context = JAXBContext.newInstance(objectForTransform.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(objectForTransform, stringWriter);
            xmlString = stringWriter.toString();
//            System.out.println(xmlString);
        } catch (JAXBException e) {
            log.error("Transform Object to XML error", e);
        }

        return xmlString;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T extends XmlModel> T readXmlMessage(String xml, Class<T> modelClass) throws WeatherBrokerServiceException {

        try {
            JAXBContext context = JAXBContext.newInstance(modelClass);
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object result = unmarshaller.unmarshal(inputStream);
            if (result == null) {
                throw new WeatherBrokerServiceException("Transform XML to Object error");
            }
            return (T) result;
        } catch (JAXBException e) {
            throw new WeatherBrokerServiceException("Transform XML to Object error");
        }
    }
}
