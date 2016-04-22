package com.mtt.airline.dtos;

import java.util.List;

/**
 * Created by dmunteanu on 3/7/2016.
 */

public class AvailabilityResult
{
    private List<Flight> availability;

    public List<Flight> getAvailability()
    {
        return availability;
    }

    public void setAvailability(List<Flight> availability)
    {
        this.availability = availability;
    }
}

