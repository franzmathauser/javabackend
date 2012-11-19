/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 *
 * @author MATHAF
 */
public class CustomJsonDateTimeSerializer extends JsonSerializer<Date>
{

    public static final String DATETIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    @Override
    public void serialize( Date aDate, JsonGenerator aJsonGenerator, SerializerProvider aSerializerProvider )
    throws IOException, JsonProcessingException
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat( DATETIME_FORMAT );
        String dateString = dateFormat.format( aDate );
        aJsonGenerator.writeString( dateString );
    }
}
