package com.mtt.airline.web.controllers;

import com.mtt.airline.dtos.AvailabilityResult;
import com.mtt.airline.services.FlightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class FlightRestController
{

    @Autowired
    FlightService flightService;

    @RequestMapping("/flights/{from}/{to}/{departsOn}/{arrivesOn}/{ticketNr}")
    public AvailabilityResult findFlights(@PathVariable("from") String from, @PathVariable("to") String to,
                                          @PathVariable("departsOn") String departsOn, @PathVariable("arrivesOn")
                                          String arrivesOn, @PathVariable("ticketNr") String ticketNumber)
    {
        return flightService.findFlights(from, to, departsOn, arrivesOn, Integer.parseInt(ticketNumber));
    }

}
