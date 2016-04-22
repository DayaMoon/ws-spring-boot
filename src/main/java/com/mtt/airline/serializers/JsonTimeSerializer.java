package com.mtt.airline.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by dmunteanu on 3/15/2016.
 */
@Component
public class JsonTimeSerializer extends JsonSerializer<LocalDateTime>
{

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("hh:mma");

    @Override
    public void serialize(LocalDateTime date, JsonGenerator gen, SerializerProvider provider) throws IOException
    {
        String formattedDate = DATE_FORMATTER.format(date);
        gen.writeString(formattedDate);
    }
}