/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MATHAF
 */
@WebFilter( filterName = "cors" )
public class CrossOriginResourceSharingFilter implements Filter
{

    @Override
    public void init( FilterConfig fConfig ) throws ServletException
    {
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter(
    ServletRequest request, ServletResponse response,
    FilterChain chain ) throws IOException, ServletException
    {

        ( ( HttpServletResponse ) response ).addHeader(
        "Access-Control-Allow-Origin", "http://localhost" );

        ( ( HttpServletResponse ) response ).addHeader(
        "Access-Control-Allow-Credentials", "true" );

        ( ( HttpServletResponse ) response ).addHeader(
        "Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, Origin, X-Requested-With" );

        ( ( HttpServletResponse ) response ).addHeader(
        "Access-Control-Allow-Methods", "GET, POST, OPTIONS" );

        System.out.println( "Filter:" + ( ( HttpServletResponse ) response ).getHeaderNames().toString() );

        chain.doFilter( request, response );
    }
}
