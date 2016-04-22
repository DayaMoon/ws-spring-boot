package com.mtt.airline.accessors;

import com.mtt.airline.models.Availability;

/**
 * Created by dmunteanu on 3/7/2016.
 */

public interface FlightAccessor
{

    Availability getFlight(String from, String to, String departsOn, String arrivesOn, Integer ticketNumber);

}
