package com.ericandben.cs336.backendapp.drinker;
import java.util.List;
import java.util.Map;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.transaction.Transaction;

public interface DrinkerRepositoryCustom {
    public List barsByTotalSpentWithinTimeInterval(String drinkerName, String dateBeginning, String dateEnd);
}