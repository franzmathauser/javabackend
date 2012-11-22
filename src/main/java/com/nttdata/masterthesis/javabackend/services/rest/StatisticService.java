/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.StatisticManager;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import com.nttdata.masterthesis.javabackend.ressource.StatisticDTO;

/**
 * REST-Service for statistic domain. Available Actions: GET
 * @author MATHAF
 */
@Stateless
@Path( "secure/bankaccount/{bankAccountId}/statistic" )
@Interceptors( ServicesLoggingInterceptor.class )
public class StatisticService
{
    @Context
    private HttpServletRequest request;

    @EJB
    private StatisticManager statisticMgr;

    /**
     * Statistic shows transaction amounts per week of year.
     * @param bankAccountId id of account number
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @GET
    @Path("/byWeekOfYear")
    public ResponseEnvelope getTransactionAmountPerWeekOfYear(
    @PathParam( "bankAccountId" ) Long bankAccountId ) throws ForbiddenException
    {

        ResponseEnvelope response = new ResponseEnvelope();
        List<StatisticDTO> statisticList = new ArrayList<StatisticDTO>();

        String user = request.getRemoteUser();
        try
        {
            Map<String, Float> statistics = statisticMgr.getTransactionAmountPerWeekOfYear( user, bankAccountId );

            for ( Map.Entry<String, Float> e : statistics.entrySet() )
            {
                StatisticDTO statistic = new StatisticDTO();
                statistic.setxValue( e.getKey() );
                statistic.setY1Value( e.getValue() );
                statisticList.add( statistic );
            }

            response.setSuccess( true );
            response.setBodyData( statisticList );

            return response;
        }
        catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }

    /**
     * Statistic shows transaction amounts per category.
     * @param bankAccountId id of account number
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @GET
    @Path("/byCategory")
    public ResponseEnvelope getTransactionByCategory(
    @PathParam( "bankAccountId" ) Long bankAccountId ) throws ForbiddenException
    {

        ResponseEnvelope response = new ResponseEnvelope();
        List<StatisticDTO> statisticList = new ArrayList<StatisticDTO>();

        String user = request.getRemoteUser();
        try
        {
            Map<String, Float> statistics = statisticMgr.getTransactionByCategory( user, bankAccountId );

            for ( Map.Entry<String, Float> e : statistics.entrySet() )
            {
                StatisticDTO statistic = new StatisticDTO();
                statistic.setxValue( e.getKey() );
                statistic.setY1Value( e.getValue() );
                statisticList.add( statistic );
            }

            response.setSuccess( true );
            response.setBodyData( statisticList );

            return response;
        }
        catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }
}
