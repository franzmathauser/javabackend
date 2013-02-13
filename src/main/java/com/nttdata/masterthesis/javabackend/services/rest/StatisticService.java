/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.StatisticManager;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.IncomeOutcomeSaldoDTO;
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
    @Path( "/byWeekOfYear" )
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
    @Path( "/byCategory" )
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

    @GET
    @Path( "/incomeOutcomeSaldo" )
    public ResponseEnvelope getIncomeOutcomeSaldo(
    @PathParam( "bankAccountId" ) Long bankAccountId ) throws ForbiddenException
    {

        ResponseEnvelope response = new ResponseEnvelope();
        List<StatisticDTO> statisticList = new ArrayList<StatisticDTO>();

        String user = request.getRemoteUser();
        try
        {
            Map<String, IncomeOutcomeSaldoDTO> statistics = statisticMgr.getIncomeOutcomeStatistic( user, bankAccountId );
            float saldo = 0f;

            for ( Map.Entry<String, IncomeOutcomeSaldoDTO> e : statistics.entrySet() )
            {
                StatisticDTO statistic = new StatisticDTO();
                statistic.setxValue( e.getKey() );
                float income = e.getValue().getIncome();
                float outcome = e.getValue().getOutcome();
                statistic.setY1Value( income );
                statistic.setY2Value( Math.abs( outcome ) );
                statistic.setY3Value( e.getValue().getSaldo() );
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

    @GET
    @Path( "/byMonthlyCategory" )
    public ResponseEnvelope getByMonthlyCategory(
    @PathParam( "bankAccountId" ) Long bankAccountId,
    @QueryParam( "maxCategories" ) int maxCategories ) throws ForbiddenException
    {

        ResponseEnvelope response = new ResponseEnvelope();

        if ( maxCategories > 10 )
        {
            response.setSuccess( false );
            response.setErrorMsg( "max categories to large." );
            return response;
        }
        List<StatisticDTO> statisticList = new ArrayList<StatisticDTO>();

        String user = request.getRemoteUser();
        try
        {
            Map<String, Map<String, Float>> statistics = statisticMgr.getMonthlyCategories( user, bankAccountId, maxCategories );

            for ( Map.Entry<String, Map<String, Float>> e : statistics.entrySet() )
            {
                StatisticDTO statistic = new StatisticDTO();
                statistic.setxValue( e.getKey() );
                Map<String, Float> value = e.getValue();
                int i = 0;
                for ( Map.Entry<String, Float> e2 : value.entrySet() )
                {
                    String category = e2.getKey();
                    Float amount = e2.getValue();

                    switch ( i )
                    {
                        case 0:
                            //statistic.setId(category);
                            statistic.setY1Value( amount );
                            break;
                        case 1:
                            statistic.setY2Value( amount );
                            break;
                        case 2:
                            statistic.setY3Value( amount );
                            break;
                        case 3:
                            statistic.setY4Value( amount );
                            break;
                        case 4:
                            statistic.setY5Value( amount );
                            break;
                        case 5:
                            statistic.setY6Value( amount );
                            break;
                        case 6:
                            statistic.setY7Value( amount );
                            break;
                        case 7:
                            statistic.setY8Value( amount );
                            break;
                        case 8:
                            statistic.setY9Value( amount );
                            break;
                        case 9:
                            statistic.setY10Value( amount );
                            break;
                        default:
                            break;
                    }
                    i++;
                }

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
