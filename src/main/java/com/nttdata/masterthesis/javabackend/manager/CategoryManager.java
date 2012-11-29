/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.dao.BankAccountDAO;
import com.nttdata.masterthesis.javabackend.dao.CategoryDAO;
import com.nttdata.masterthesis.javabackend.dao.UserDAO;
import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.Category;
import com.nttdata.masterthesis.javabackend.entities.User;
import com.nttdata.masterthesis.javabackend.interceptor.CategoryIconInterceptor;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.CategoryDTO;

/**
 * Db Transaction Manager controlls the access to Transactions from database.
 * @author MATHAF
 */
@Stateless
@LocalBean
public class CategoryManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( CategoryManager.class );

    @EJB
    private CategoryDAO categoryDAO;

    @EJB
    private UserDAO userDAO;

    @EJB
    private BankAccountDAO bankAccountDAO;

    @EJB
    private AccessManager accessManager;

    /**
     * List of Transaction Entites.
     * @param userName login-username
     * @param bankAccountId bankaccount identifier
     * @return Lis of Transaction Entites
     * @throws ForbiddenException user tries to access an account of another user
     */
    @Interceptors( CategoryIconInterceptor.class )
    public List<CategoryDTO> getCategoryList( String userName,
                                              Long bankAccountId ) throws ForbiddenException
    {
        List<CategoryDTO> categories = new ArrayList<CategoryDTO>();

        if ( userName == null )
        {
            throw new IllegalArgumentException( "user is null" );
        }

        BankAccount bankAccount = checkUserAccess( userName, bankAccountId );
        categories.addAll( getCategoryList( bankAccount ) );
        return categories;
    }

    /**
     * List of Category Entities.
     * @param bankAccount backaccount entity
     * @return List of Category Entities
     */
    private List<CategoryDTO> getCategoryList( BankAccount bankAccount )
    {
        return convertToDTO( categoryDAO.findByBankAccount( bankAccount ) );
    }

    private BankAccount checkUserAccess( String userName, Long bankAccountId ) throws ForbiddenException
    {
        User user = userDAO.findByName( userName );
        BankAccount bankAccount = bankAccountDAO.find( bankAccountId );
        accessManager.isAllowed( user, bankAccount );
        return bankAccount;
    }

    private List<CategoryDTO> convertToDTO( List<Category> categories )
    {
        List<CategoryDTO> categoriesList = new ArrayList<CategoryDTO>();

        for ( Category categoryEntity : categories )
        {
            categoriesList.add( convertToDTO( categoryEntity ) );
        }

        return categoriesList;
    }

    private CategoryDTO convertToDTO( Category categoryEntity )
    {

        CategoryDTO category = new CategoryDTO();
        category.setId( categoryEntity.getId() );
        category.setName( categoryEntity.getName() );

        return category;
    }

    /**
     * Creates a new Category Entry for a Bankaccount.
     * @param username session username
     * @param bankAccountId bankaccount identifier
     * @param categoryDTO category transfer object
     * @return category transfer object
     * @throws ForbiddenException user tries to access an account of another user
     */
    @Interceptors( CategoryIconInterceptor.class )
    public CategoryDTO createCategory( String username, Long bankAccountId,
                                       CategoryDTO categoryDTO ) throws ForbiddenException
    {

        User user = userDAO.findByName( username );
        BankAccount bankAccount = bankAccountDAO.find( bankAccountId );

        accessManager.isAllowed( user, bankAccount );

        Category category = new Category();
        category.setName( categoryDTO.getName() );
        category.setBankAccount( bankAccount );

        categoryDAO.save( category );

        return convertToDTO( category );

    }

    /**
     * Update a Category Entry for a Bankaccount.
     * @param username session username
     * @param bankAccountId bankaccount identifier
     * @param categoryDTO category transfer object
     * @return category transfer object
     * @throws ForbiddenException user tries to access an account of another user
     */
    @Interceptors( CategoryIconInterceptor.class )
    public CategoryDTO updateCategory( String username, Long bankAccountId,
                                       CategoryDTO categoryDTO ) throws ForbiddenException
    {

        User user = userDAO.findByName( username );
        BankAccount bankAccount = bankAccountDAO.find( bankAccountId );

        accessManager.isAllowed( user, bankAccount );

        Category category = categoryDAO.find( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );

        categoryDAO.save( category );

        return convertToDTO( category );

    }

    /**
     * Delete a Category Entry from a Bankaccount.
     * @param username session username
     * @param bankAccountId bankaccount identifier
     * @param categoryDTO category transfer object
     * @throws ForbiddenException user tries to access an account of another user
     */
    public void deleteCategory( String username, Long bankAccountId,
                                Long categoryId ) throws ForbiddenException
    {

        User user = userDAO.findByName( username );
        BankAccount bankAccount = bankAccountDAO.find( bankAccountId );

        accessManager.isAllowed( user, bankAccount );
        categoryDAO.remove( categoryId );

    }
}
