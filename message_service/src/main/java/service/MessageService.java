package service;

import exception.WeatherBrokerServiceException;
import model.XmlModel;

/**
 * Сервис для создания и чтения xml
 */
public interface MessageService {

    /**
     * Создание xml для сообщения
     *
     * @param xml Объект класса, реализующего интерфейс {@link XmlModel}  передаваемый для преобразования в xml
     * @return Строка в формате xml, полученная после преобразования объекта
     */

    String createXmlMessage(XmlModel xml);

    /**
     * Чтение xml сообщения
     *
     * @param xml Строка в формате xml, переданная для преобразования в объект
     * @return Объект класса, реализующий интерфейс {@link XmlModel}
     */
    <T extends XmlModel> T readXmlMessage(String xml, Class<T> modelClass) throws WeatherBrokerServiceException;
}
