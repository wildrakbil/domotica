package com.home.domotica.facade.mapper;

import com.home.domotica.facade.dto.SensorDTO;
import com.home.domotica.models.entity.Sensor;
import org.springframework.stereotype.Component;

@Component(value = "mapper")
public class Mapper implements IMapper {
    @Override
    public SensorDTO mapSensor(Sensor in) {
        if (in == null) {
            return null;
        }
        SensorDTO out = new SensorDTO();
        out.setId(in.getId());
        out.setName(in.getName());
        out.setDescription(in.getDescription());
        out.setValue(in.getValue());
        return out;
    }

    @Override
    public Sensor mapSensor(SensorDTO in) {
        if (in == null) {
            return null;
        }
        Sensor out = new Sensor();
        out.setId(in.getId());
        out.setName(in.getName());
        out.setDescription(in.getDescription());
        out.setValue(in.getValue());
        return out;
    }
}
