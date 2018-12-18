package com.az.business;

import com.az.entities.Forecast;
import com.az.entities.ForecastDetail;
import com.az.entities.Place;
import com.az.entities.Weather;
import com.az.repositories.ForecastDetailRepository;
import com.az.repositories.ForecastRepository;
import com.az.repositories.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WeatherForecastService {

    @Autowired
    private ForecastRepository forecastRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ForecastDetailRepository forecastDetailRepository;

    public List<Forecast> saveRestApiResponse(Weather weather){
        List<Forecast> temp = new ArrayList<>();
        if(weather.getForecastList()!=null && !weather.getForecastList().isEmpty()){

            weather.getForecastList().forEach(forecast -> {
                forecast.setForecastDetailList(new ArrayList<>());
                if(forecast.getDay()!=null) {
                    //forecast.getDay().setForecast(forecast);
                    forecast.getDay().setDayOrNight("Day");
                    if(forecast.getDay().getPlaceList()!=null && !forecast.getDay().getPlaceList().isEmpty()) {
                        forecast.getDay().getPlaceList().forEach(place -> {
                            place.setForecastDetail(forecast.getDay());

                        });
                    }

                    if(forecast.getDay().getWindList()!=null && !forecast.getDay().getWindList().isEmpty()) {
                        forecast.getDay().getWindList().forEach(wind -> {
                            wind.setForecastDetail(forecast.getDay());

                        });
                    }
                    forecast.addForecastDetail(forecast.getDay());
                }
                if(forecast.getNight()!=null){
                    //forecast.getNight().setForecast(forecast);
                    forecast.getNight().setDayOrNight("Night");

                    if(forecast.getNight().getPlaceList()!=null && !forecast.getNight().getPlaceList().isEmpty()) {
                        forecast.getNight().getPlaceList().forEach(place -> {
                            place.setForecastDetail(forecast.getNight());

                        });
                    }

                    if(forecast.getNight().getWindList()!=null && !forecast.getNight().getWindList().isEmpty()) {
                        forecast.getNight().getWindList().forEach(wind -> {
                            wind.setForecastDetail(forecast.getNight());

                        });
                    }
                    forecast.addForecastDetail(forecast.getNight());
                }

            });

            temp = forecastRepository.saveAll(weather.getForecastList());

        }

        return temp;
    }

    public List<Forecast> getAllForecastList(){
        List<Forecast> forecastList = forecastRepository.findAll();
        if(!forecastList.isEmpty())
            forecastList = this.initializeForecastDetailList(forecastList);
        return forecastList;
    }

    public List<Place> getPlaceListForSelectedForecastDetail(ForecastDetail forecastDetail){
        return placeRepository.findByForecastDetail(forecastDetail);
    }

    public List<Forecast> getForecastListByLocation(String location){
        List<Forecast> forecastList = forecastRepository.findByForecastDetailListPlaceListNameContaining(location);
        if(!forecastList.isEmpty())
            forecastList = this.initializeForecastDetailList(forecastList);
        return forecastList;
    }
    private List<Forecast> initializeForecastDetailList(List<Forecast> forecastList){
        forecastList.forEach(forecast -> {
            forecast.getForecastDetailList().size();
        });
        return forecastList;
    }

    public List<ForecastDetail> getForecastDetailListByLocation(String location){
        return forecastDetailRepository.findByPlaceListNameContaining(location);
    }

    public List<ForecastDetail> getAllForecastDetailList(){
        return forecastDetailRepository.findAll();
    }
}