package com.home.domotica.facade.mapper;

import com.home.domotica.facade.dto.SensorDTO;
import com.home.domotica.models.entity.Sensor;

public interface IMapper {

    SensorDTO mapSensor(Sensor in);
    Sensor mapSensor(SensorDTO in);
}
