package com.home.domotica.models.dao;

import com.home.domotica.models.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISensorDAO extends JpaRepository<Sensor, Long> {
    Sensor findById(long id);
    Sensor findByname(String name);
}
