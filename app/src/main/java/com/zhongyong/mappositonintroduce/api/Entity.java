package com.zhongyong.mappositonintroduce.api;

import java.io.Serializable;

/**
 * Created by fyc on 2017/12/22.
 */

public class Entity implements Serializable {
    private String TicketName;
    private String Amount;
    private String BeginDate;
    private String EndDate;
    private String PriceName;
    private int TicketTypeId;
    private String AmountAdvice;
    private int PriceInSceneryId;
    private int PriceId;

    public String getTicketName() {
        return TicketName;
    }

    public void setTicketName(String ticketName) {
        TicketName = ticketName;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(String beginDate) {
        BeginDate = beginDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getPriceName() {
        return PriceName;
    }

    public void setPriceName(String priceName) {
        PriceName = priceName;
    }

    public int getTicketTypeId() {
        return TicketTypeId;
    }

    public void setTicketTypeId(int ticketTypeId) {
        TicketTypeId = ticketTypeId;
    }

    public String getAmountAdvice() {
        return AmountAdvice;
    }

    public void setAmountAdvice(String amountAdvice) {
        AmountAdvice = amountAdvice;
    }

    public int getPriceInSceneryId() {
        return PriceInSceneryId;
    }

    public void setPriceInSceneryId(int priceInSceneryId) {
        PriceInSceneryId = priceInSceneryId;
    }

    public int getPriceId() {
        return PriceId;
    }

    public void setPriceId(int priceId) {
        PriceId = priceId;
    }
}
