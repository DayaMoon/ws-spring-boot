package com.mtt.airline.services;

import com.mtt.airline.accessors.FlightAccessor;
import com.mtt.airline.dtos.Availability;
import com.mtt.airline.dtos.AvailabilityResult;
import com.mtt.airline.dtos.Flight;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by dmunteanu on 3/7/2016.
 */
@Service
public class FlightServiceImpl implements FlightService
{

    @Autowired
    private FlightAccessor flightAccessor;

    @Autowired
    private Converter converter;

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public AvailabilityResult findFlights(String from, String to, String departsOn, String arrivesOn, Integer
            ticketNumber)
    {
        com.mtt.airline.models.Availability availableFlights = flightAccessor.getFlight(from, to, departsOn,
                arrivesOn, ticketNumber);
        Objects.nonNull(availableFlights);
        return extractFlights(availableFlights);

    }

    private AvailabilityResult extractFlights(@NotNull com.mtt.airline.models.Availability availableFlights)
    {
        AvailabilityResult flightResult = new AvailabilityResult();

        List<Flight> foundFlights = new ArrayList<>();
        for (com.mtt.airline.models.Availability.Flight flight : availableFlights.getFlight())
        {
            Flight flight1 = (Flight) converter.convert(flight);
            foundFlights.add(flight1);
        }
        flightResult.setAvailability(foundFlights);

        return flightResult;
    }
}
