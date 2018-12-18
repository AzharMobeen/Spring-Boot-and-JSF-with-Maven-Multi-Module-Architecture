package com.az.business;

import com.az.entities.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class SchedulerComponet {

    @Autowired
    private WeatherForecastService wheaterForecastService;

    private RestTemplate restTemplate;

    public SchedulerComponet(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    //1800000 = 30 Min
    @Scheduled(fixedDelay = 1800000)
    public void scheduledTask() {
        //System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
        Weather wheather = null;
        String url = "http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
        ResponseEntity<Weather> responseEntity = restTemplate.exchange(url, HttpMethod.GET, this.getHeaders(), Weather.class);
        if (responseEntity!=null && responseEntity.hasBody()){
            Weather weather = responseEntity.getBody();
            wheaterForecastService.saveRestApiResponse(responseEntity.getBody());
        }
    }

    private HttpEntity<?> getHeaders()  {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return new HttpEntity<>(headers);
    }
}
