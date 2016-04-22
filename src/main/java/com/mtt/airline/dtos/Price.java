package com.mtt.airline.dtos;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by dmunteanu on 3/9/2016.
 */
public class Price
{
    private Currency currency;
    private BigDecimal amount;

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Currency getCurrency()
    {
        return currency;
    }

    public void setCurrency(Currency currency)
    {
        this.currency = currency;
    }
}
