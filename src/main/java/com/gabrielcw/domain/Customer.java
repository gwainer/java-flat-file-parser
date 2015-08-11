package com.gabrielcw.domain;

import com.github.ffpojo.metadata.delimited.annotation.DelimitedField;
import com.github.ffpojo.metadata.delimited.annotation.DelimitedRecord;

@DelimitedRecord(delimiter = "ç")
public class Customer {
    private String cnpj;
    private String name;
    private String businessArea;

    @DelimitedField(positionIndex = 2)
    public String getCnpj() {
        return cnpj;
    }

    @DelimitedField(positionIndex = 3)
    public String getName() {
        return name;
    }

    @DelimitedField(positionIndex = 4)
    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setName(String name) {
        this.name = name;
    }
}
