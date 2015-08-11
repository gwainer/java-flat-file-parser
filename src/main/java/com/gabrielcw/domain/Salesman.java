package com.gabrielcw.domain;

import com.github.ffpojo.metadata.DefaultFieldDecorator;
import com.github.ffpojo.metadata.delimited.annotation.DelimitedField;
import com.github.ffpojo.metadata.delimited.annotation.DelimitedRecord;

@DelimitedRecord(delimiter = "ç")
public class Salesman {
    private String cpf;
    private String name;
    private Float salary;

    @DelimitedField(positionIndex = 2)
    public String getCpf() {
        return cpf;
    }

    @DelimitedField(positionIndex = 3)
    public String getName() {
        return name;
    }

    @DelimitedField(positionIndex = 4, decorator = FloatDecorator.class)
    public Float getSalary() {
        return salary;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public static class FloatDecorator extends DefaultFieldDecorator {
        @Override
        public Object fromString(String str) {
            return Float.valueOf(str);
        }
    }
}
