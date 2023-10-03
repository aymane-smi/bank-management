package com.bank.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class AgencyEmployee {
    @NonNull
    private String id;
    @NonNull
    private Employee employee;
    @NonNull
    private Agency agency;
    @NonNull
    private LocalDate transfer_date;
}
