package eu.ase.ro.damapp.fragments;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable {
    private Date date;
    private int amount;
    private String category;
    private String description;

    public Expense(Date date, String amount, String category, String description) {
        this.date = date;
        this.amount = Integer.parseInt(amount);
        this.category = category;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "Expense{" +
                "date=" + date +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
