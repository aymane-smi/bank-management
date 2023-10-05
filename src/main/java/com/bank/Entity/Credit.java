package com.bank.Entity;

import com.bank.Enum.CreditStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Credit {
    public static final double TAUX = 0.12;
    @NonNull
    private int id;
    @NonNull
    private int value;
    @NonNull
    private CreditStatus status;
    @NonNull
    private int duration;
    @NonNull
    private String remark;
    @NonNull
    private Client client;
    @NonNull
    private Agency agency;
    @NonNull
    private Employee employee;
}