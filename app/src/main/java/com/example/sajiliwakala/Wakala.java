package com.example.sajiliwakala;

public class Wakala {
    public String ID_No, FirstName, MiddleName, LastName, DoB, Licence_No, SIM_No, Code_No, TIN_No, BusinessRegion;

    public Wakala(){

    }

    public Wakala(String ID_No, String FirstName, String MiddleName, String LastName, String DoB, String LicenceNo, String SIMNo, String CodeNo, String TINNo, String BizRegion){
        this.ID_No = ID_No;
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
        this.DoB = DoB;
        this.Licence_No = LicenceNo;
        this.SIM_No = SIMNo;
        this.Code_No = CodeNo;
        this.TIN_No = TINNo;
        this.BusinessRegion = BizRegion;
    }
}
