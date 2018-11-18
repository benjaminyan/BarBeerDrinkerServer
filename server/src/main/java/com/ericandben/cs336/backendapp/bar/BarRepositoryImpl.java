package com.ericandben.cs336.backendapp.bar;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.drinker.*;
import com.ericandben.cs336.backendapp.transaction.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.persistence.Query;
import javax.persistence.criteria.Expression;
import javax.naming.spi.DirStateFactory.Result;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;


import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


class DrinkerAndTotalSpent {
    String drinker;
    double total;

    public DrinkerAndTotalSpent(String drinker, double total) {
        this.drinker = drinker;
        this.total = total;
    }
}

/*class TimeDistObject {
    ;
    double total;

    public TimeDistObject(String label, double total) {
        this.label = label;
        this.total = total;
    }
    public String toString(){
        return this.total + "";
    }
}*/

@Service
public class BarRepositoryImpl implements BarRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(BarRepositoryImpl.class);


    /*
        select t.bar, sum(t.amountPaid) as total from Transactions t
        where t.drinker = 'Aaron Endo'
        and t.dateTime > '2018-10-22' and t.dateTime < '2018-10-23'
        group by t.bar
        order by total desc;

    */



    // TODO look into CriteriaBuilder::createTupleQuery()

    public List<DrinkerAndTotalSpent> drinkerSpentTotal(String barName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DrinkerAndTotalSpent> q = cb.createQuery(DrinkerAndTotalSpent.class);
        Root<Transaction> t = q.from(Transaction.class);
        Predicate barNamePredicate = cb.equal(t.get("bar"), new Bar(barName));
        q.where(barNamePredicate);
        
        Expression<Number> sum = cb.sum(t.get("amountPaid"));
        q.multiselect(
            t.get("drinker").get("name"), // Cannot use pkey.bar
            sum
        );

        q.groupBy(t.get("drinker").get("name"));
        q.orderBy(cb.desc(sum));

        //TypedQuery<Transaction> query = em.createQuery(q);
        //List<Transaction> results = query.getResultList();
        TypedQuery<DrinkerAndTotalSpent> query = em.createQuery(q);
        
        query.setMaxResults(5);
        List<DrinkerAndTotalSpent> results = query.getResultList();

        return results;

        /*
        Map<String, Double> map = new HashMap<>();
        for (Transaction transaction : results) {
            String barName = transaction.getPkey().getBar().getName();
            if (map.containsKey(barName)) {
                double currentTotal = map.get(barName);
                double newTotal = currentTotal + transaction.getAmountPaid();
                map.put(barName, newTotal);
            } else {
                map.put(barName, transaction.getAmountPaid());
            }
        }

        return map;
        */



    }
}