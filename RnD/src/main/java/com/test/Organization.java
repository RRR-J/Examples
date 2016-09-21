package com.test;

import java.util.Optional;

/**
 * Created by g01027567 on 11/07/2016.
 */
public class Organization {

    private Employee employee;

    public Optional<Employee> getEmployee() {
        return Optional.ofNullable(employee);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
