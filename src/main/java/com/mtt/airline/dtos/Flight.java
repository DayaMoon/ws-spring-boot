package com.mtt.airline.dtos;
/**
 * { "availability": [
 {"flight": {
 "operator" : "UA",
 "flightNumber" : "UA304",
 "departsFrom" : "MUC",
 "arrivesAt" : "IST",
 "departsOn" : {"date": "01-01-2014", "time": "08:52AM"},
 "arrivesOn" : {"date": "01-01-2014", "time": "12:49PM"},
 "flightTime" : "03:57",
 "farePrices" : {
 "first" : {
 "ticket" : { "currency" : "USD", "amount" : 472.00},
 "bookingFee" : { "currency" : "USD", "amount" : 29.50},
 "tax" : { "currency" : "USD", "amount" : 23.60}
 },
 "business" : {
 "ticket" : { "currency" : "USD", "amount" : 236.00},
 "bookingFee" : { "currency" : "USD", "amount" : 29.50},
 "tax" : { "currency" : "USD", "amount" : 23.60}
 },
 "economy" : {
 "ticket" : { "currency" : "USD", "amount" : 118.00},
 "bookingFee" : { "currency" : "USD", "amount" : 29.50},
 "tax" : { "currency" : "USD", "amount" : 23.60}
 }
 }
 }
 }
 ]}
 */

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Map;

/**
 * Created by dmunteanu on 3/7/2016.
 */
@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
public class Flight
{
    private String operator;
    private String flightNumber;
    private String departsFrom;
    private String arrivesAt;
    private FlightDateTime departsOn;
    private FlightDateTime arrivesOn;
    private String flightTime;
    private Map<String, FarePrice> farePrices;

    public String getArrivesAt()
    {
        return arrivesAt;
    }

    public void setArrivesAt(String arrivesAt)
    {
        this.arrivesAt = arrivesAt;
    }

    public FlightDateTime getArrivesOn()
    {
        return arrivesOn;
    }

    public void setArrivesOn(FlightDateTime arrivesOn)
    {
        this.arrivesOn = arrivesOn;
    }

    public String getDepartsFrom()
    {
        return departsFrom;
    }

    public void setDepartsFrom(String departsFrom)
    {
        this.departsFrom = departsFrom;
    }

    public FlightDateTime getDepartsOn()
    {
        return departsOn;
    }

    public void setDepartsOn(FlightDateTime departsOn)
    {
        this.departsOn = departsOn;
    }

    public Map<String, FarePrice> getFarePrices()
    {
        return farePrices;
    }

    public void setFarePrices(Map<String, FarePrice> farePrices)
    {
        this.farePrices = farePrices;
    }

    public String getFlightNumber()
    {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber)
    {
        this.flightNumber = flightNumber;
    }

    public String getFlightTime()
    {
        return flightTime;
    }

    public void setFlightTime(String flightTime)
    {
        this.flightTime = flightTime;
    }

    public String getOperator()
    {
        return operator;
    }

    public void setOperator(String operator)
    {
        this.operator = operator;
    }

}
