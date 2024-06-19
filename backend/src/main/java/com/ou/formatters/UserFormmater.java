package com.ou.formatters;

import com.ou.pojo.User;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class UserFormmater implements Formatter<User> {

    @Override
    public User parse(String id, Locale locale) throws ParseException {
        User f = new User();
        f.setId(Integer.parseInt(id));

        return f;
    }

    @Override
    public String print(User object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
