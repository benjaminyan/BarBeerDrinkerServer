package com.ericandben.cs336.backendapp.drinker;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.sells.Sells;
import com.ericandben.cs336.backendapp.likes.*;
import com.ericandben.cs336.backendapp.transaction.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
//import javax.persistence.Query; TODO do we need this too?
import javax.persistence.criteria.Expression;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;




@Service
public class DrinkerRepositoryImpl implements DrinkerRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    /*
        select t.bar, sum(t.amountPaid) as total from Transactions t
        where t.drinker = 'Aaron Endo'
        and t.dateTime > '2018-10-22' and t.dateTime < '2018-10-23'
        group by t.bar
        order by total desc;

    */



    public List<Likes> getLikes() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Likes> q = cb.createQuery(Likes.class);
        Root<Likes> t = q.from(Likes.class);
        q.select(t);

        TypedQuery<Likes> query = em.createQuery(q);

        List<Likes> results = query.getResultList();
        return results;
    }

    public List<Sells> getSells() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sells> q = cb.createQuery(Sells.class);
        Root<Sells> t = q.from(Sells.class);
        q.select(t);

        TypedQuery<Sells> query = em.createQuery(q);

        List<Sells> results = query.getResultList();
        return results;
    }



    // TODO look into CriteriaBuilder::createTupleQuery()

    public List<BarAndTotalSpent> barsByTotalSpentWithinTimeInterval(String drinkerName, 
        String dateBeginning, String dateEnd)
    {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BarAndTotalSpent> q = cb.createQuery(BarAndTotalSpent.class);
        Root<Transaction> t = q.from(Transaction.class);

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        
        try {

            Predicate drinkerNamePredicate = cb.equal(t.get("drinker"), new Drinker(drinkerName));
            Predicate dateTimePredicate = cb.between(t.get("dateTime"), ft.parse(dateBeginning), ft.parse(dateEnd));
            q.where(drinkerNamePredicate, dateTimePredicate);
            
            Expression<Number> sum = cb.sum(t.get("amountPaid"));
            q.multiselect(
                t.get("bar").get("name"), // Cannot use pkey.bar
                sum
            );

            q.groupBy(t.get("bar").get("name"));
            q.orderBy(cb.desc(sum));

            //TypedQuery<Transaction> query = em.createQuery(q);
            //List<Transaction> results = query.getResultList();
            TypedQuery<BarAndTotalSpent> query = em.createQuery(q);
            
            query.setMaxResults(5);
            List<BarAndTotalSpent> results = query.getResultList();

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

        } catch (ParseException e) {
            System.out.println(e);
            return null;
        }

    }
    
}