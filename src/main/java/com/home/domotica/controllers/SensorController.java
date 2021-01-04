package com.home.domotica.controllers;

import com.home.domotica.facade.dto.SensorDTO;
import com.home.domotica.facade.mapper.IMapper;
import com.home.domotica.models.dao.ISensorDAO;
import com.home.domotica.models.entity.Sensor;
import com.home.domotica.util.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/home")
public class SensorController {

    @Resource(name = "mapper")
    private IMapper mapper;

    @Autowired
    private IUtils utils;

    @Autowired
    private ISensorDAO sensorDAO;

    @GetMapping("/sensors")
    public List<SensorDTO> getAllSensors() {
        List<Sensor> SensorList = sensorDAO.findAll();
        List<SensorDTO> out = new ArrayList<>();
        if (SensorList != null) {
            for (Sensor in : SensorList) {
                out.add(mapper.mapSensor(in));
            }
        }
        return out;
    }

    @GetMapping("/sensor/{id}")
    public SensorDTO getSensor(@PathVariable long id) {
        Sensor out = sensorDAO.findById(id);
        return mapper.mapSensor(out);
    }

    @PostMapping("/sensor")
    public ResponseEntity<?> createSensor(@RequestBody SensorDTO in, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Sensor sensorNew = null;
        if (utils.validBindingResult(result, response)) {
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            sensorNew = sensorDAO.save(mapper.mapSensor(in));
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El sensor ha sido creado con éxito!");
        response.put("data", sensorNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @PutMapping("/sensor/{id}")
    public ResponseEntity<?> updateSensor(@PathVariable long id, @RequestBody SensorDTO in, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Sensor SensorUpdated = null;
        if (utils.validBindingResult(result, response)) {
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Sensor SensorActual = sensorDAO.findById(id);
            if (SensorActual == null) {
                response.put("mensaje", "Error: no se pudo editar, el sensor con ID: "
                        .concat(String.valueOf(id).concat(" no existe en la base de datos!")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
            }
            in.setId(SensorActual.getId());
            SensorActual = mapper.mapSensor(in);
            SensorUpdated = sensorDAO.save(SensorActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el sensor en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El sensor ha sido actualizado con éxito!");
        response.put("data", SensorUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/sensor/{id}")
    public ResponseEntity<?> deleteSensor(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        Sensor sensor = sensorDAO.findById(id);
        if (sensor != null) {
            sensorDAO.delete(sensor);
        } else {
            response.put("mensaje", "La Sensor no fue encontrado para ser borrado");
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}
