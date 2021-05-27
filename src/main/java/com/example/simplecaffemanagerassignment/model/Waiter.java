package com.example.simplecaffemanagerassignment.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Waiter extends User {
    @OneToMany
    private List<Table> tables;
    private Integer servingTablesCount;

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public Integer getServingTablesCount() {
        return servingTablesCount;
    }

    public void setServingTablesCount(Integer servingTablesCount) {
        this.servingTablesCount = servingTablesCount;
    }
}
