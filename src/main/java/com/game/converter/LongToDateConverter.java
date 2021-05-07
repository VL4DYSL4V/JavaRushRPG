package com.game.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class LongToDateConverter implements Converter<Long, Date> {
    @Override
    public Date convert(Long source) {
//        Instant instant = Instant.ofEpochMilli(source);
//        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
//        return Date.from(zonedDateTime.toInstant());
        LocalDateTime date = new Timestamp(source).toLocalDateTime();

        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }
}
