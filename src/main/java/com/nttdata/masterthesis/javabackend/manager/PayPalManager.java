/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.nttdata.masterthesis.javabackend.dao.CategoryDAO;
import com.nttdata.masterthesis.javabackend.dao.MappedCategoryDAO;
import com.nttdata.masterthesis.javabackend.entities.Category;
import com.nttdata.masterthesis.javabackend.entities.MappedCategory;
import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;

import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.TransactionSearchReq;
import urn.ebay.api.PayPalAPI.TransactionSearchRequestType;
import urn.ebay.api.PayPalAPI.TransactionSearchResponseType;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionSearchResultType;

/**
 * PayPal Manager controlls the access to PayPal Service.
 * @author MATHAF
 */
@Stateless
@LocalBean
public class PayPalManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( PayPalManager.class );

    /**
     * filename of paypal config vaules.
     */
    public static final String PAYPAL_CONFIG_FILE = "paypal-sdk-config.properties";

    /**
     * search offset from current date.
     */
    public static final int PAYPAL_DATE_OFFSET = 100;

    /**
     * datetime format of paypal dates.
     */
    public static final String PAYPAL_DATEFORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @EJB
    private MappedCategoryDAO mappedCategoryDao;

    @EJB
    private CategoryDAO categoryDAO;

    /**
     * List of Transaction.
     * PayPal access is configured in config-file.
     * @return List of Transaction DTO
     */
    public List<TransactionDTO> getTransactions()
    {

        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();

        PayPalAPIInterfaceServiceService service;
        List<PaymentTransactionSearchResultType> paymentTransactions;
        try
        {

            InputStream is = this.getClass().getClassLoader().getResourceAsStream( PAYPAL_CONFIG_FILE );

            service = new PayPalAPIInterfaceServiceService( is );
            TransactionSearchReq txSearchReq = new TransactionSearchReq();
            TransactionSearchRequestType txReqType = new TransactionSearchRequestType();

            DateTime dt = new DateTime();
            dt = dt.minusDays( PAYPAL_DATE_OFFSET );

            txReqType.setStartDate( dt.toString() );

            txSearchReq.setTransactionSearchRequest( txReqType );

            TransactionSearchResponseType txSearchRespType;
            txSearchRespType = service.transactionSearch( txSearchReq );

            paymentTransactions = txSearchRespType.getPaymentTransactions();

            for ( PaymentTransactionSearchResultType transaction : paymentTransactions )
            {
                System.out.println( transaction.getPayer() );

                TransactionDTO transactionDto = new TransactionDTO();

                transactionDto.setId( transaction.getTransactionID() );
                transactionDto.setAmount( Float.valueOf( transaction.getGrossAmount().getValue() ) );

                MappedCategory mappedCategory = mappedCategoryDao.findByMappedId( transaction.getTransactionID() );
                if ( mappedCategory != null )
                {
                    transactionDto.setCategory( mappedCategory.getCategory().getName() );
                    transactionDto.setCategoryId( mappedCategory.getCategory().getId() );
                }

                DateTimeFormatter fmt = DateTimeFormat.forPattern( PAYPAL_DATEFORMAT_PATTERN );
                dt = fmt.parseDateTime( transaction.getTimestamp() );
                transactionDto.setBillingDate( dt.toDate() );
                transactionDto.setBillingDateMillis( dt.getMillis() );
                transactionDto.setValueDate( dt.toDate() );
                transactionDto.setPurpose( transaction.getStatus() );
                transactionDto.setAccount( transaction.getPayer() );

                transactionList.add( transactionDto );
            }


        }
        catch ( OAuthException ex )
        {
            LOG.error( "OAUTH exception", ex );

        }
        catch ( IOException ex )
        {
            LOG.error( "IO exception", ex );
        }
        catch ( SSLConfigurationException ex )
        {
            LOG.error( "SSL config exception", ex );
        }
        catch ( InvalidCredentialException ex )
        {
            LOG.error( "invalid credentials exception", ex );
        }
        catch ( HttpErrorException ex )
        {
            LOG.error( "http error exception", ex );
        }
        catch ( InvalidResponseDataException ex )
        {
            LOG.error( "invalid response exception", ex );
        }
        catch ( ClientActionRequiredException ex )
        {
            LOG.error( "client action rejected exception", ex );
        }
        catch ( MissingCredentialException ex )
        {
            LOG.error( "no credentials exception", ex );
        }
        catch ( InterruptedException ex )
        {
            LOG.error( "interrupted exception", ex );
        }
        catch ( ParserConfigurationException ex )
        {
            LOG.error( "parser config exception", ex );
        }
        catch ( SAXException ex )
        {
            LOG.error( "sax exception", ex );
        }

        return transactionList;

    }

    /**
     * Updates the category of a transaction.
     * @param user username
     * @param transaction transaction transfer object
     * @return transaction transfer object
     */
    public TransactionDTO updateTransactionCategory( String user,
                                                     TransactionDTO transaction )
    {
        String transactionId = transaction.getId();
        //TODO check if user can access this transaction.
        MappedCategory mappedCategory = mappedCategoryDao.findByMappedId( transactionId );
        Category category = categoryDAO.find( transaction.getCategoryId() );
        if ( mappedCategory != null )
        {
            mappedCategory.setCategory( category );
        }
        else
        {
            mappedCategory = new MappedCategory();
            mappedCategory.setMappedId( transactionId );
            mappedCategory.setCategory( category );
        }
        mappedCategoryDao.save( mappedCategory );
        return transaction;
    }
}
