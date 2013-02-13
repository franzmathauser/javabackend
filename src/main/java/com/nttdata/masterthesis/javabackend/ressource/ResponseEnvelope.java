/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAnyElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Resource for response content.
 * This object represents the json structure, send in a response to the client.
 * @author MATHAF
 */
//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_ATOM_XML, MediaType.TEXT_XML})
//@XmlRootElement( name = "responseEnv")
@JsonSerialize( include = JsonSerialize.Inclusion.NON_NULL )
@Produces(
{
    MediaType.APPLICATION_JSON
} )
@Consumes(
{
    MediaType.APPLICATION_JSON
} )
public class ResponseEnvelope
{
    /**
     * version of the json structure.
     */
    private final float version = 1.0f;

    private boolean success = false;

    private String errorMsg;

    private Map<String, Object> fieldErrors;

    @XmlAnyElement( lax = true )
    private Object bodyData;

    /**
     * Default constructor.
     */
    public ResponseEnvelope()
    {
    }

    /**
     * Constructor creates object with success flag.
     * @param s success flag
     */
    public ResponseEnvelope( boolean s )
    {
        this.success = s;
    }

    /**
     * Constructor creates object with error message.
     * @param errorMessage error message send in response
     */
    public ResponseEnvelope( String errorMessage )
    {
        this.success = false;
        this.errorMsg = errorMessage;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess( boolean success )
    {
        this.success = success;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    /**
     * Sets Error Message.
     * @param msg Error message
     * @return ResponseEnvelope
     */
    public ResponseEnvelope setErrorMsg( String msg )
    {
        this.errorMsg = msg;
        return this;
    }

    public Map<String, Object> getFieldErrors()
    {
        return fieldErrors;
    }

    public void setFieldErrors( Map<String, Object> fieldErrors )
    {
        this.fieldErrors = fieldErrors;
    }

    public Object getBodyData()
    {
        return bodyData;
    }

    public void setBodyData( Object bodyData )
    {
        this.bodyData = bodyData;
    }

    public float getVersion()
    {
        return version;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 67 * hash + Float.floatToIntBits( this.version );
        hash = 67 * hash + ( this.success ? 1 : 0 );
        hash = 67 * hash + ( this.errorMsg != null ? this.errorMsg.hashCode() : 0 );
        hash = 67 * hash + ( this.fieldErrors != null ? this.fieldErrors.hashCode() : 0 );
        hash = 67 * hash + ( this.bodyData != null ? this.bodyData.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        final ResponseEnvelope other = (ResponseEnvelope) obj;
        if ( Float.floatToIntBits( this.version ) != Float.floatToIntBits( other.version ) )
        {
            return false;
        }
        if ( this.success != other.success )
        {
            return false;
        }
        if ( ( this.errorMsg == null ) ? ( other.errorMsg != null ) : !this.errorMsg.equals( other.errorMsg ) )
        {
            return false;
        }
        if ( this.fieldErrors != other.fieldErrors && ( this.fieldErrors == null || !this.fieldErrors.equals( other.fieldErrors ) ) )
        {
            return false;
        }
        if ( this.bodyData != other.bodyData && ( this.bodyData == null || !this.bodyData.equals( other.bodyData ) ) )
        {
            return false;
        }
        return true;
    }
}
