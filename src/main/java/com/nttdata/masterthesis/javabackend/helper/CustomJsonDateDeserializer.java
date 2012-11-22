/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.helper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * JsonDate deserializer deserializes a german date format as Java-Date object.
 * @author MATHAF
 */
public class CustomJsonDateDeserializer extends JsonDeserializer<Date>
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( CustomJsonDateDeserializer.class );

    @Override
    public Date deserialize( JsonParser jp, DeserializationContext dc ) throws IOException, JsonProcessingException
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat( CustomJsonDateSerializer.DATE_FORMAT );
        try
        {
            return dateFormat.parse( jp.getText() );
        }
        catch ( ParseException ex )
        {
            LOG.error( "error parsing date format", ex );
        }
        return null;
    }
}
