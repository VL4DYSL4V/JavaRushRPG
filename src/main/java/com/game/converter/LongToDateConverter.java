package com.game.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public final class LongToDateConverter implements Converter<Long, Date> {
    @Override
    public Date convert(Long source) {
        return new Date(source);
    }
}
