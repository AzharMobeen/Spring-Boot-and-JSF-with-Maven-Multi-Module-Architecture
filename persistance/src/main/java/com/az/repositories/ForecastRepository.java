package com.az.repositories;

import com.az.entities.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast,Long> {

    List<Forecast> findByForecastDetailListPlaceListNameContaining(String name);
}
