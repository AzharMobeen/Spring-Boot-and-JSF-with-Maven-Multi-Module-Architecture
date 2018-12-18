package com.az.repositories;

import com.az.entities.ForecastDetail;
import com.az.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {
    List<Place> findByForecastDetail(ForecastDetail forecastDetail);
}
