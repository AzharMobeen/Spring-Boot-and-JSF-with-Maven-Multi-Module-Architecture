package com.az.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.az.entities.Forecast;
import com.az.entities.ForecastDetail;
import com.az.entities.Place;
import com.az.entities.Weather;
import com.az.repositories.ForecastDetailRepository;
import com.az.repositories.ForecastRepository;
import com.az.repositories.PlaceRepository;

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
        List<Forecast> temp = null;
        if(CollectionUtils.isEmpty(weather.getForecastList())){
            weather.getForecastList().forEach(forecast -> {
                forecast.setForecastDetailList(new ArrayList<>());                                
                setForecastDetailForDay(forecast);                                
                setForecastDetailForNight(forecast);                	
            });
            temp = forecastRepository.saveAll(weather.getForecastList());
        }
        return temp;
    }
    

    public List<Place> getPlaceListForSelectedForecastDetail(ForecastDetail forecastDetail){
        return placeRepository.findByForecastDetail(forecastDetail);
    }    

    public List<ForecastDetail> getForecastDetailListByLocation(String location){
        return forecastDetailRepository.findByPlaceListNameContaining(location);
    }

    public List<ForecastDetail> getAllForecastDetailList(){
        return forecastDetailRepository.findAll();
    }
    
    private void setForecastDetailForDay(Forecast forecast) {
    	if(forecast.getDay()!=null) {    		
	    	forecast.getDay().setDayOrNight("Day");	    	
	    	ForecastDetail forecastDetail = setForecastDetailForBoth(forecast.getDay());
	        forecast.addForecastDetail(forecastDetail);        
    	}
    }
    private void setForecastDetailForNight(Forecast forecast) {
    	if(forecast.getNight()!=null) {
    		forecast.getNight().setDayOrNight("Night");
    		ForecastDetail forecastDetail = setForecastDetailForBoth(forecast.getNight());
    		forecast.addForecastDetail(forecastDetail);
    	}
                
    }
    private ForecastDetail setForecastDetailForBoth(ForecastDetail forecastDetail) {
    		
    	setForecastDetailPlaceList(forecastDetail);        
		setForecastDetailWindList(forecastDetail);
    	return forecastDetail;
    	                
    }       
    
    private void setForecastDetailPlaceList(ForecastDetail forecastDetail) {
    	
    	if(CollectionUtils.isEmpty(forecastDetail.getPlaceList())) {
        	
    		forecastDetail.getPlaceList().forEach(place -> {
                place.setForecastDetail(forecastDetail);
            	
            });
        }        
    }
    private void setForecastDetailWindList(ForecastDetail forecastDetail) {
    	if(CollectionUtils.isEmpty(forecastDetail.getWindList())) {
    		forecastDetail.getWindList().forEach(wind -> {
                wind.setForecastDetail(forecastDetail);
            });
        }
    }
}