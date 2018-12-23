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

import lombok.extern.java.Log;

@Log
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
    	log.info("saveRestApiResponse Start");
        List<Forecast> temp = null;
        if(CollectionUtils.isEmpty(weather.getForecastList())){
            weather.getForecastList().forEach(forecast -> {
                forecast.setForecastDetailList(new ArrayList<>());                                
                setForecastDetailForDay(forecast);                                
                setForecastDetailForNight(forecast);                	
            });
            temp = forecastRepository.saveAll(weather.getForecastList());
        }
        log.info("saveRestApiResponse End");
        return temp;
    }
    

    public List<Place> getPlaceListForSelectedForecastDetail(ForecastDetail forecastDetail){
    	log.info("getPlaceListForSelectedForecastDetail Start");
        return placeRepository.findByForecastDetail(forecastDetail);
    }    

    public List<ForecastDetail> getForecastDetailListByLocation(String location){
    	log.info("getForecastDetailListByLocation Start");
        return forecastDetailRepository.findByPlaceListNameContaining(location);
    }

    public List<ForecastDetail> getAllForecastDetailList(){
    	log.info("getAllForecastDetailList Start");
        return forecastDetailRepository.findAll();
    }
    
    private void setForecastDetailForDay(Forecast forecast) {
    	log.info("setForecastDetailForDay Start");
    	if(forecast.getDay()!=null) {    		
	    	forecast.getDay().setDayOrNight("Day");	    	
	    	ForecastDetail forecastDetail = setForecastDetailForBoth(forecast.getDay());
	        forecast.addForecastDetail(forecastDetail);        
    	}
    	log.info("setForecastDetailForDay End");
    }
    private void setForecastDetailForNight(Forecast forecast) {
    	log.info("setForecastDetailForNight Start");
    	if(forecast.getNight()!=null) {
    		forecast.getNight().setDayOrNight("Night");
    		ForecastDetail forecastDetail = setForecastDetailForBoth(forecast.getNight());
    		forecast.addForecastDetail(forecastDetail);
    	}
    	log.info("setForecastDetailForNight End");
                
    }
    private ForecastDetail setForecastDetailForBoth(ForecastDetail forecastDetail) {
    	log.info("setForecastDetailForBoth Start");
    	setForecastDetailPlaceList(forecastDetail);        
		setForecastDetailWindList(forecastDetail);
		log.info("setForecastDetailForBoth End");
    	return forecastDetail;
    	                
    }       
    
    private void setForecastDetailPlaceList(ForecastDetail forecastDetail) {
    	log.info("setForecastDetailPlaceList Start");
    	if(CollectionUtils.isEmpty(forecastDetail.getPlaceList())) {
        	
    		forecastDetail.getPlaceList().forEach(place -> {
                place.setForecastDetail(forecastDetail);
            	
            });
        }
    	log.info("setForecastDetailPlaceList End");
    }
    private void setForecastDetailWindList(ForecastDetail forecastDetail) {
    	log.info("setForecastDetailWindList Start");
    	if(CollectionUtils.isEmpty(forecastDetail.getWindList())) {
    		forecastDetail.getWindList().forEach(wind -> {
                wind.setForecastDetail(forecastDetail);
            });
        }
    	log.info("setForecastDetailWindList End");
    }
}