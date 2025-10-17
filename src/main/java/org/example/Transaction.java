package org.example;

public class Transaction implements Comparable<Transaction>{

    private String date;
    private final String time;
    private final String description;
    private final String vendor;
    private final double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @SuppressWarnings("unused")
    public void setDate(String date) {
        this.date = date;
    }

    @SuppressWarnings("unused")
    public String getTime() {
        return time;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return this.description;
    }

    @Override
    public int compareTo(Transaction otherTransaction) {
        return Integer.compare(Integer.parseInt(this.time), Integer.parseInt(otherTransaction.time));
    }
}