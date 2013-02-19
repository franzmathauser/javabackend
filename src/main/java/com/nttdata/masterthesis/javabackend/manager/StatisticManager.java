/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.manager;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.IncomeOutcomeSaldoDTO;
import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;

/**
 * Statistic Manager creates statistic values from content.
 * @author MATHAF
 */
@Stateless
@LocalBean
public class StatisticManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( StatisticManager.class );

    @EJB
    private TransactionManager transactionManager;

    /**
     * Get transaction amount grouped by week of year.
     * @param user session username
     * @param bankAccountId bankaccount identifier
     * @return map of statistic values. key is week of year, value is transaction amount.
     * @throws ForbiddenException user tries to access an account of another user
     */
    public Map<String, Float> getTransactionAmountPerWeekOfYear( String user,
                                                                 Long bankAccountId ) throws ForbiddenException
    {
        List<TransactionDTO> transactions = transactionManager.getTransactionList( user, bankAccountId );
        Map<String, Float> statistics = new HashMap<String, Float>();

        for ( TransactionDTO transaction : transactions )
        {
            DateTime billingDate = new DateTime( transaction.getBillingDate() );
            String weekText = Integer.toString( billingDate.getWeekOfWeekyear() );
            Float currentAmount = statistics.get( weekText );
            if ( currentAmount == null )
            {
                currentAmount = 0f;
            }
            currentAmount += transaction.getAmount();
            statistics.put( weekText, currentAmount );
        }

        return statistics;
    }

    /**
     * Get transaction amout by category.
     * The sum of transaction amount is calculated by the sum of the absolute value.
     * @param user session username
     * @param bankAccountId bankaccount identifier
     * @return map of statistic values. key is category, value is the absolute value of the transaction amount.
     * @throws ForbiddenException user tries to access an account of another user
     */
    public Map<String, Float> getTransactionByCategory( String user,
                                                        Long bankAccountId ) throws ForbiddenException
    {
        List<TransactionDTO> transactions = transactionManager.getTransactionList( user, bankAccountId );
        Map<String, Float> statistics = new HashMap<String, Float>();

        for ( TransactionDTO transaction : transactions )
        {
            String categoryText = transaction.getCategoryName();
            Float currentAmount = statistics.get( categoryText );
            if ( currentAmount == null )
            {
                currentAmount = 0f;
            }
            if ( transaction.getAmount() < 0 )
            {
                currentAmount += Math.abs( transaction.getAmount() );
            }
            statistics.put( categoryText, currentAmount );
        }

        return statistics;
    }

    /**
     * Get transaction amout by income, outcome and saldo.
     * @param user session username
     * @param bankAccountId bankaccount identifier
     * @return map of statistic values. key is month, values are income, outcome, saldo.
     * @throws ForbiddenException user tries to access an account of another user
     */
    public Map<String, IncomeOutcomeSaldoDTO> getIncomeOutcomeStatistic(
    String user,
    Long bankAccountId ) throws ForbiddenException
    {
        List<TransactionDTO> transactions = transactionManager.getTransactionList( user, bankAccountId );

        Collections.sort( transactions );
        Collections.reverse( transactions );
        Map<String, IncomeOutcomeSaldoDTO> statistics = new TreeMap<String, IncomeOutcomeSaldoDTO>();

        float saldo = 0f;

        for ( TransactionDTO transaction : transactions )
        {
            DateTime billingDate = new DateTime( transaction.getBillingDate() );
            DecimalFormat df = new DecimalFormat( "00" );

            String monthText = billingDate.getYear() + "/" + df.format( billingDate.getMonthOfYear() );

            IncomeOutcomeSaldoDTO entry = statistics.get( monthText );
            if ( entry == null )
            {
                entry = new IncomeOutcomeSaldoDTO();
            }
            if ( transaction.getAmount() >= 0 )
            {
                float newIncome = entry.getIncome() + transaction.getAmount();
                entry.setIncome( newIncome );
                saldo += transaction.getAmount();
            }
            else
            {
                float newOutcome = entry.getOutcome() + transaction.getAmount();
                entry.setOutcome( newOutcome );
                saldo += transaction.getAmount();
            }
            entry.setSaldo( saldo );
            statistics.put( monthText, entry );

        }

        return statistics;
    }

    /**
     * Get transaction amout by month and category.
     * @param user session user
     * @param bankAccountId bank account identifier
     * @param maxCategories max number of categories
     * @return map of statistic values.
     * @throws ForbiddenException user tries to access an account of another user
     */
    public Map<String, Map<String, Float>> getMonthlyCategories(
    String user,
    Long bankAccountId, Integer maxCategories ) throws ForbiddenException
    {
        List<TransactionDTO> transactions = transactionManager.getTransactionList( user, bankAccountId );

        Map<String, Map<String, Float>> statistics = new HashMap<String, Map<String, Float>>();

        Map<String, Float> categoryMaxAmount = new HashMap<String, Float>();
        ValueComparator bvc = new ValueComparator( categoryMaxAmount );
        TreeMap<String, Float> sortedMap = new TreeMap<String, Float>( bvc );


        for ( TransactionDTO transaction : transactions )
        {
            DateTime billingDate = new DateTime( transaction.getBillingDate() );

            String monthText = Integer.toString( billingDate.getMonthOfYear() );

            Map<String, Float> entry = statistics.get( monthText );
            if ( entry == null )
            {
                entry = new HashMap<String, Float>();
            }
            String category = transaction.getCategoryName();
            Float transactionAmount = Math.abs( transaction.getAmount() );

            Float categoryAmount = entry.get( category );
            if ( categoryAmount == null )
            {
                categoryAmount = 0f;
            }
            float newAmount = categoryAmount + transactionAmount;

            Float maxCatAmount = categoryMaxAmount.get( category );
            if ( maxCatAmount == null )
            {
                maxCatAmount = 0f;
            }
            categoryMaxAmount.put( category, maxCatAmount + transactionAmount );
            entry.put( category, newAmount );

            statistics.put( monthText, entry );

        }

        sortedMap.putAll( categoryMaxAmount );

        // pretty format

        return formatMonthlyCategories( statistics, sortedMap, maxCategories );

    }

    private Map<String, Map<String, Float>> formatMonthlyCategories(
    Map<String, Map<String, Float>> statistics,
    TreeMap<String, Float> sortOrder,
    int maxCategories )
    {
        Map<String, Map<String, Float>> formattedStatistic = new HashMap<String, Map<String, Float>>();

        for ( Map.Entry<String, Map<String, Float>> entry : statistics.entrySet() )
        {

            Map<String, Float> newEntry = new HashMap<String, Float>();
            String month = entry.getKey();

            int i = 0;

            for ( Map.Entry<String, Float> sortedEntry : sortOrder.entrySet() )
            {
                i++;

                if ( i > maxCategories )
                {
                    break;
                }

                String category = sortedEntry.getKey();
                Float amount = entry.getValue().get( category );

                if ( amount == null )
                {
                    amount = 0f;
                }

                newEntry.put( category, amount );

            }
            formattedStatistic.put( month, newEntry );

        }

        return formattedStatistic;
    }

    /**
     * Comperator for values.
     */
    private static class ValueComparator implements Comparator<String>
    {
        private Map<String, Float> base;

        public ValueComparator( Map<String, Float> b )
        {
            this.base = b;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.
        @Override
        public int compare( String a, String b )
        {
            if ( base.get( a ) >= base.get( b ) )
            {
                return -1;
            }
            else
            {
                return 1;
            } // returning 0 would merge keys
        }
    }
}
