package com.mtt.airline.services;

import com.mtt.airline.dtos.AvailabilityResult;

/**
 * Created by dmunteanu on 3/7/2016.
 */
public interface FlightService
{
    AvailabilityResult findFlights(String from, String to, String departsOn, String arrivesOn, Integer ticketNumber);
}
