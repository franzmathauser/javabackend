/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * JsonDate serializer serializes a Java-Date object as german date format.
 * @author MATHAF
 */
public class CustomJsonDateSerializer extends JsonSerializer<Date>
{
    /**
     * dateformat of json response, for example. 01.01.2012 for January, 01 2012
     */
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    @Override
    public void serialize( Date aDate, JsonGenerator aJsonGenerator,
                           SerializerProvider aSerializerProvider )
    throws IOException
    {

        SimpleDateFormat dateFormat = new SimpleDateFormat( DATE_FORMAT );
        String dateString = dateFormat.format( aDate );
        aJsonGenerator.writeString( dateString );
    }
}
