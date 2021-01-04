package com.home.domotica.util;

import org.springframework.validation.BindingResult;

import java.util.Map;

public interface IUtils {

    boolean validBindingResult(BindingResult result, Map<String, Object> response);

}
