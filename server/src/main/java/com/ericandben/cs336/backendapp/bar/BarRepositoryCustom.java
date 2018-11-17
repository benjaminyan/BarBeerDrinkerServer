package com.ericandben.cs336.backendapp.bar;
import java.util.List;
import java.util.Map;
import com.ericandben.cs336.backendapp.bar.*;
import com.ericandben.cs336.backendapp.transaction.Transaction;

import org.springframework.data.jpa.repository.Query;

public interface BarRepositoryCustom {
    public List drinkerSpentTotal(String barName);
}