package com.bank.Entity;

import java.util.Date;

public class MissionEmployee {
    private Date startDate;
    private Date endDate;
    public MissionEmployee(){}

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
