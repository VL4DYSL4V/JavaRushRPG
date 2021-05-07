package com.game.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public final class LongToDateConverter implements Converter<Long, Date> {
    @Override
    public Date convert(Long source) {
        Instant instant = Instant.ofEpochMilli(source);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }
}
