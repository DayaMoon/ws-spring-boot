package com.mtt.airline.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mtt.airline.serializers.JsonDateSerializer;
import com.mtt.airline.serializers.JsonTimeSerializer;

import java.time.LocalDateTime;

/**
 * Created by dmunteanu on 3/8/2016.
 */
public class FlightDateTime
{
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDateTime date;

    @JsonSerialize(using = JsonTimeSerializer.class)
    private LocalDateTime time;

    public LocalDateTime getDate()
    {
        return date;
    }

    public void setDate(LocalDateTime date)
    {
        this.date = date;
    }

    public LocalDateTime getTime()
    {
        return time;
    }

    public void setTime(LocalDateTime time)
    {
        this.time = time;
    }
}
