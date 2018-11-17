package com.ericandben.cs336.backendapp.drinker;
import java.util.List;
import java.util.Map;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.transaction.Transaction;
import com.ericandben.cs336.backendapp.sells.Sells;
import com.ericandben.cs336.backendapp.likes.*;

import org.springframework.data.jpa.repository.Query;

class BarAndTotalSpent {
    String bar;
    double total;

    public BarAndTotalSpent(String bar, double total) {
        this.bar = bar;
        this.total = total;
    }
}

public interface DrinkerRepositoryCustom {

    public List<BarAndTotalSpent> barsByTotalSpentWithinTimeInterval(String drinkerName, String dateBeginning, String dateEnd);

    public List<Likes> getLikes();

    public List<Sells> getSells();
}