package com.example.sajiliwakala;

public class Customer {
    public String FirstName, MiddleName, LastName, DoB, SIM_No;
    public double Balance;
    public int PIN;

    public Customer(){

    }

    public Customer(String FirstName, String MiddleName, String LastName, String DoB, String SIMNo, double Balance, int PIN){
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
        this.DoB = DoB;
        this.SIM_No = SIMNo;
        this.Balance = Balance;
        this.PIN = PIN;
    }
}
