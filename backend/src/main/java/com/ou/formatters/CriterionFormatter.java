package com.ou.formatters;

import com.ou.pojo.Criterion;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CriterionFormatter  implements Formatter<Criterion> {

    @Override
    public Criterion parse(String id, Locale locale) throws ParseException {
        Criterion c = new Criterion();
        c.setId(Integer.parseInt(id));

        return c;
    }

    @Override
    public String print(Criterion object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
