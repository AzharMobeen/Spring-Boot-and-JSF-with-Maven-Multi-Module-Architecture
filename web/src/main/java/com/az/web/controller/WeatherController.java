package com.az.web.controller;

import com.az.business.WeatherForecastService;
import com.az.entities.ForecastDetail;
import com.az.entities.Place;
import com.az.web.util.JsfUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;


@Data
@Controller
@ViewScoped
public class WeatherController implements Serializable {

    @Autowired
    private WeatherForecastService weatherForecastService;

    private List<Place> placeList;
    private String location;
    private String selectedLocation;
    private List<ForecastDetail> forecastDetailList;

    @PostConstruct
    public void init(){
        System.out.println("Working fine");
    }

    public void initSearch(){
        if(!JsfUtil.isAjaxRequest()) {
            selectedLocation = null;
            this.forecastDetailList = weatherForecastService.getAllForecastDetailList();
        }
    }
    public void searchPlaceList(ForecastDetail forecastDetail){

        if (forecastDetail!=null){
            placeList = weatherForecastService.getPlaceListForSelectedForecastDetail(forecastDetail);
            if(placeList.isEmpty())
                JsfUtil.addErrorMessage("No Record Found!");
            else
                JsfUtil.showDialog("placeDialog");
        }
    }

    public void searchForecastByLocation(){
        if(location!=null)
            this.forecastDetailList = weatherForecastService.getForecastDetailListByLocation(this.location);
        if(this.forecastDetailList.isEmpty())
            JsfUtil.addErrorMessage("We Don't have record for your location!");
        else {
            JsfUtil.addSuccessMessage("Please check bellow table details");
            this.selectedLocation = location;
        }
        this.location = null;
    }
    /*private void organizeForecastListForView(){
        if (this.forecastList != null) {
            this.forecastDetailList = new ArrayList<>();

            Forecast dayForecast = new Forecast();
            dayForecast.setDayOrNightTitle("Day");
            dayForecast.setForecastDetailList(new ArrayList<>());

            Forecast nightForecast = new Forecast();
            nightForecast.setDayOrNightTitle("Night");
            nightForecast.setForecastDetailList(new ArrayList<>());

            List<Forecast> forecastList = new ArrayList<>();

            this.forecastList.forEach(forecast -> {

                forecast.getForecastDetailList().forEach(forecastDetail -> {
                    forecastDetailList.add(forecastDetail);
                    if (forecastDetail.getDayOrNight().equalsIgnoreCase("Day")) {
                        dayForecast.getForecastDetailList().add(forecastDetail);
                    } else
                        nightForecast.getForecastDetailList().add(forecastDetail);

                });

            });
            forecastList.add(dayForecast);
            forecastList.add(nightForecast);
            this.forecastList = forecastList;
        }
    }*/
}
