package com.ericandben.cs336.backendapp.sql;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

class SQLResponse {

    List<String> colNames;
    List<Map<String, String>> rows;
    String error = null;

    @JsonIgnore
    Map<String, String> currentRow;


    public SQLResponse() {
        this.colNames = new ArrayList<>();
        this.rows = new ArrayList<>();
        
        // Init the first row
        //this.currentRow = new HashMap<>();
        //this.rows.add(this.currentRow);
    }

    public void addColumn(String name) {
        this.colNames.add(name);
    }

    public void setColumnName(int index, String name) {
        this.colNames.add(index-1, name);
    }

    public void nextRow() {
        Map<String, String> newRowData = new HashMap<>();
        this.rows.add(newRowData);
        this.currentRow = newRowData;
    }

    public void initRowAtIndex(int index) {
        Map<String, String> newRowData = new HashMap<>();
        this.rows.set(index-1, newRowData);
    }

    public void setRowData(int rowIndex, int colIndex, String data) {

        Map<String, String> row = this.rows.get(rowIndex-1);
        if (row == null) {
            this.initRowAtIndex(rowIndex);
        }

    }

    public void setRowData(int rowIndex, String colName, String data) {
        
        Map<String, String> row = this.rows.get(rowIndex-1);
        if (row == null) {
            this.initRowAtIndex(rowIndex);
        }

        row.put(colName, data);
    }

    public void setRowData(int colIndex, String data) {
        Map<String, String> row = this.currentRow; // Make sure this isn't null

        String colName = this.colNames.get(colIndex-1); // Make sure this isn't null
        row.put(colName, data);
    }

    public void setRowData(String colName, String data) {
        Map<String, String> row = this.currentRow; // Make sure this isn't null
        row.put(colName, data);
    }


    private boolean colExists(String colName) {

        for (String col : this.colNames) {
            if (col.equals(colName)) return true;
        }
        return false;
    }
    
}

@Controller
@RequestMapping(path="/sql")
public class SqlController<T, K extends Serializable> {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping(path="object/run")
    public @ResponseBody SQLResponse runQuery2(@RequestParam String query) {
        
        SQLResponse response = new SQLResponse();

        ResultSetExtractor<SQLResponse> rse = new ResultSetExtractor<SQLResponse>() {

            public SQLResponse extractData(ResultSet rs) throws SQLException {

                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                
                // Extract column names
                String colName;
                for (int colNum = 1; colNum <= columnCount; colNum++) {

                    colName = metadata.getColumnName(colNum);
                    response.setColumnName(colNum, colName);
                }

                // Extract data for each row
                String data;
                while (rs.next()) {
                    response.nextRow(); // Advance the row

                    // could loop over column names instead
                    for (int colNum = 1; colNum <= columnCount; colNum++) {
                        data = rs.getString(colNum);
                        response.setRowData(colNum, data);
                    }
                }

                return response;

            }
        };

        // Run the query, and use rse to deal with the results.
        // We cannot use a RowMapper becasue that handles individual rows. We need a way to
        // extract the column metadata.
        jdbcTemplate.query(query, rse);
        return response;

    }

    @PostMapping(path="text/run")
    public @ResponseBody String runQuery(@RequestParam String query) {

        String result;
        
        ResultSetExtractor<String> rse = new ResultSetExtractor<String>() {

            public String extractData(ResultSet rs) throws SQLException {

                ResultSetMetaData metadata = rs.getMetaData();
                int columnCount = metadata.getColumnCount();
                StringBuilder sb = new StringBuilder();
                
                // Extract column names
                for (int colNum = 1; colNum <= columnCount; colNum++) {
                    sb.append(metadata.getColumnName(colNum) + "\t\t");
                }
                sb.append("\n");

                // Extract data for each row
                while (rs.next()) {
                    for (int colNum = 1; colNum <= columnCount; colNum++) {
                        sb.append(rs.getString(colNum) + "\t\t");
                    }
                    sb.append("\n");
                }

                return sb.toString();

            }


        };

        // Run the query, and use rse to deal with the results.
        // We cannot use a RowMapper becasue that handles individual rows. We need a way to
        // extract the column metadata.
        result = jdbcTemplate.query(query, rse);
        return result;
    }

}