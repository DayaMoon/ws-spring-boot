package com.mtt.airline.dtos;

import java.util.List;

/**
 * Created by dmunteanu on 3/9/2016.
 */
public class Availability
{
    private List<Flight> flight;

    public List<Flight> getFlight()
    {
        return flight;
    }

    public void setFlight(List<Flight> flight)
    {
        this.flight = flight;
    }

}
