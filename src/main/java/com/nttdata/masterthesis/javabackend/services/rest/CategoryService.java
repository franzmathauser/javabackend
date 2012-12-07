/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.CategoryManager;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.CategoryDTO;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;

/**
 * REST-Service for category domain. Available Actions: GET, PUT, POST, DELETE
 * @author MATHAF
 */
@Stateless
@Path( "secure/bankaccount/{bankAccountId}/categories" )
@Interceptors( ServicesLoggingInterceptor.class )
public class CategoryService
{
    @Context
    private HttpServletRequest request;

    @EJB
    private CategoryManager categoryManager;

    /**
     * List of all categories to a bankaccount.
     * @param bankAccountId id of account number
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @GET
    public ResponseEnvelope getBankAccountCategories(
    @PathParam( "bankAccountId" ) Long bankAccountId ) throws ForbiddenException
    {

        ResponseEnvelope response = new ResponseEnvelope();
        List<CategoryDTO> categories;

        String user = request.getRemoteUser();
        try
        {
            categories = categoryManager.getCategoryList( user, bankAccountId );
            response.setSuccess( true );
            response.setBodyData( categories );

            return response;
        }
        catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }

    /**
     * Creates a new category into a bankaccount.
     * @param bankAccountId id of account number
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @POST
    public ResponseEnvelope createBankAccountCategory(
    @PathParam( "bankAccountId" ) Long bankAccountId, CategoryDTO category ) throws ForbiddenException
    {

        ResponseEnvelope response = new ResponseEnvelope();

        String user = request.getRemoteUser();
        try
        {
            category = categoryManager.createCategory( user, bankAccountId, category );
            response.setSuccess( true );
            response.setBodyData( category );

            return response;
        }
        catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }

    /**
     * Updates a category from a bankaccount.
     * @param bankAccountId id of account number
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @PUT
    @Path( "/{categoryId}" )
    public ResponseEnvelope updateBankAccountCategory(
    @PathParam( "bankAccountId" ) Long bankAccountId,
    @PathParam( "categoryId" ) long categoryId, CategoryDTO category ) throws ForbiddenException
    {

        ResponseEnvelope response = new ResponseEnvelope();

        String user = request.getRemoteUser();
        try
        {
            category.setId( categoryId );
            category = categoryManager.updateCategory( user, bankAccountId, category );
            response.setSuccess( true );
            response.setBodyData( category );

            return response;
        }
        catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }

    /**
     * Delete a category from a bankaccount.
     * @param bankAccountId id of account number
     * @param categoryId category identifier
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @DELETE
    @Path( "/{categoryId}" )
    public ResponseEnvelope updateBankAccountCategory(
    @PathParam( "bankAccountId" ) Long bankAccountId,
    @PathParam( "categoryId" ) long categoryId ) throws ForbiddenException
    {

        ResponseEnvelope response = new ResponseEnvelope();

        String user = request.getRemoteUser();
        try
        {
            categoryManager.deleteCategory( user, bankAccountId, categoryId );
            response.setSuccess( true );
            return response;
        }
        catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }
}
