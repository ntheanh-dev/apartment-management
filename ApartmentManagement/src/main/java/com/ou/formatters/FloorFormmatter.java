package com.ou.formatters;

import com.ou.pojo.Floor;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class FloorFormmatter implements Formatter<Floor> {

    @Override
    public Floor parse(String id, Locale locale) throws ParseException {
        Floor f = new Floor();
        f.setId(Integer.parseInt(id));

        return f;
    }

    @Override
    public String print(Floor object, Locale locale) {
        return String.valueOf(object.getId());
    }
}