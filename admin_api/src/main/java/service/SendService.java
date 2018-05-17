package service;


import exception.EmptyRequestException;

/**
 * Сервис отправки названия города
 */
public interface SendService {

    /**
     * @param txt название города
     * @throws EmptyRequestException Ошибка возникшая по проичине незаполненной формы на странице
     */
    public void sendMassage(String txt) throws EmptyRequestException;

}
