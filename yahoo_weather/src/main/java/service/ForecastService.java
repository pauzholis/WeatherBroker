package service;


import exception.EmptyRequestException;
import exception.WeatherBrokerServiceException;
import model.City;
import model.Forecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import view.YahooResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Сервис для приема JMS сообщения, отправки запроса на Yahoo, приема ответа от Yahoo и отправки его в базу данных
 */
@RequestScoped
public class ForecastService {
    private static Logger log = LoggerFactory.getLogger(ForecastService.class);

    private MessageService messageService;
    private YahooJMSSendService yahooJMSSendService;

    public ForecastService() {
    }

    @Inject
    public ForecastService(MessageService messageService, YahooJMSSendService yahooJMSSendService) {
        this.messageService = messageService;
        this.yahooJMSSendService = yahooJMSSendService;
    }

    /**
     * Создание xml сообщения из объекта созданного из объекта-ответа на запрос к Yahoo
     *
     * @param xml Объект с названием города в виде XML
     * @throws WeatherBrokerServiceException Исключение возникающее при отправке JMS сообщения
     * @throws EmptyRequestException         Исключение возникающее при незаполненной форме с названием города
     */
    public void sendXmlToDataBaseFromYahooResponse(String xml) throws WeatherBrokerServiceException,
            EmptyRequestException {
        City city = messageService.readXmlMessage(xml, City.class);
        String cityName = city.getName();
        YahooResponse yahooResponse = getYahooResponse(cityName);
        Forecast forecast = forecastObjectBuilder(yahooResponse);
        String xmlForSend = messageService.createXmlMessage(forecast);
        yahooJMSSendService.sendMessage(xmlForSend);
    }

    private YahooResponse getYahooResponse(String city) throws WeatherBrokerServiceException {
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%2Clocation%20from%20weather" +
                ".forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + city +
                "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        RestTemplate restTemplate = new RestTemplate();
        YahooResponse yahooResponse = null;
        try {
            yahooResponse = restTemplate.getForObject(new URI(url), YahooResponse.class);
        } catch (URISyntaxException e) {
            //TODO
            throw new WeatherBrokerServiceException("");
        }
        log.info("Yahoo's response object: " + yahooResponse.toString());
        return yahooResponse;
    }

    private Forecast forecastObjectBuilder(YahooResponse yahooResponse) {
        return new Forecast(yahooResponse.getQuery().getResults().getChannel().getItem().getCondition().getDate(),
                yahooResponse.getQuery().getResults().getChannel().getLocation().getCity(),
                yahooResponse.getQuery().getResults().getChannel().getItem().getCondition().getTemp(),
                yahooResponse.getQuery().getResults().getChannel().getItem().getCondition().getText());
    }
}
