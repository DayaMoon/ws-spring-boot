package com.mtt.airline.dtos;

/**
 * Created by dmunteanu on 3/7/2016.
 */
public enum TicketClass
{
    FIF("first"), CIF("business"), YIF("economy");

    private String clazz;

    private TicketClass(String clazz)
    {
        this.clazz = clazz;
    }


    public String getClazz()
    {
        return this.clazz;
    }
}
