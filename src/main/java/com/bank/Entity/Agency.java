package com.bank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Agency {
    @NonNull
    private String code;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String phone;
    private List<Employee> employees = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Employee> employeeHistory = new ArrayList<>();
}
