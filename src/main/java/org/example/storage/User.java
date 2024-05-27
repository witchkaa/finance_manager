package org.example.storage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    private String name;
    private Integer budget;
    private HashMap<String, Integer> incomes;
    private HashMap<String, Integer> expences;
    private List<Integer> history;
    public List<Integer> getHistory() {
        return history;
    }
    public void setHistory(List<Integer> history) {
        this.history = history;
    }
    public Integer getBudget() {
        return budget;
    }
    public Map<String, Integer> getIncomes() {
        return incomes;
    }
    public Map<String, Integer> getExpences() {
        return expences;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setIncomes(Map<String, Integer> incomes) {
        this.incomes = (HashMap<String, Integer>) incomes;
    }
    public void setExpends(Map<String, Integer> expends) {
        this.expences = (HashMap<String, Integer>) expends;
    }
    public User(String name, Integer budget, Map<String, Integer> incomes, Map<String, Integer> expends) {
        this.name = name;
        this.budget = budget;
        this.incomes = (HashMap<String, Integer>) incomes;
        this.expences = (HashMap<String, Integer>) expends;
    }
    public User() {}
    public void setBudget(Integer budget) {
        this.budget = budget;
    }

}