package com.ou.formatters;

import com.ou.pojo.Cabinet;
import com.ou.pojo.Floor;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CabinetFormatter implements Formatter<Cabinet> {

    @Override
    public Cabinet parse(String id, Locale locale) throws ParseException {
        Cabinet f = new Cabinet();
        f.setId(Integer.parseInt(id));

        return f;
    }

    @Override
    public String print(Cabinet object, Locale locale) {
        return String.valueOf(object.getId());
    }

}
