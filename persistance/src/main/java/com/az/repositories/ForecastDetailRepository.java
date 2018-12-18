package com.az.repositories;

import com.az.entities.ForecastDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForecastDetailRepository  extends JpaRepository<ForecastDetail,Long> {
    List<ForecastDetail> findByPlaceListNameContaining(String name);
}
