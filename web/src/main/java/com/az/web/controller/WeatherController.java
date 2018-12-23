package com.az.web.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.az.business.WeatherForecastService;
import com.az.entities.ForecastDetail;
import com.az.entities.Place;
import com.az.web.util.JsfUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@Controller
@ViewScoped
public class WeatherController implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Autowired
    private WeatherForecastService weatherForecastService;

    private List<Place> placeList;
    private String location;
    private String selectedLocation;
    private List<ForecastDetail> forecastDetailList;   

    public void initSearch(){
        if(!JsfUtil.isAjaxRequest()) {
        	log.info("initSearch Info");
            selectedLocation = null;
            this.forecastDetailList = weatherForecastService.getAllForecastDetailList();
            log.debug("initSearch Debug");
        }
    }
    public void searchPlaceList(ForecastDetail forecastDetail){
    	log.info("searchPlaceList Start");    	
        if (forecastDetail!=null){
            placeList = weatherForecastService.getPlaceListForSelectedForecastDetail(forecastDetail);
            if(placeList.isEmpty()) {
                JsfUtil.addErrorMessage("No Record Found!");
                
            }else {
            	log.debug("placeList size :: "+placeList.size());
                JsfUtil.showDialog("placeDialog");
            }
        }
        log.info("searchPlaceList End");
    }

    public void searchForecastByLocation(){
    	log.info("searchForecastByLocation Start");
        if(location!=null)
            this.forecastDetailList = weatherForecastService.getForecastDetailListByLocation(this.location);
        if(this.forecastDetailList.isEmpty())
            JsfUtil.addErrorMessage("We Don't have record for your location!");
        else {
            JsfUtil.addSuccessMessage("Please check bellow table details");
            this.selectedLocation = location;
        }
        this.location = null;
        log.info("searchForecastByLocation End");
    }
    
}
