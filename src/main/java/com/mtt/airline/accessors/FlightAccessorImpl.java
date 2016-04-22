package com.mtt.airline.accessors;

import com.mtt.airline.models.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by dmunteanu on 3/7/2016.
 */
@Component
public class FlightAccessorImpl implements FlightAccessor
{
    private static final String AIRPORT_ORIGIN = "airportOrigin";
    private static final String AIRPORT_DESTINATION = "airportDestination";
    private static final String DEPART_DATE = "departDate";
    private static final String ARRIVAL_DATE = "arrivalDate";
    private static final String TICKET_NR = "ticketNr";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ws.72732-mockairline.endpoint}")
    private String wsAccessPath;

    public Availability getFlight(String from, String to, String departsOn, String arrivesOn, Integer ticketNumber)
    {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(wsAccessPath)
                .queryParam(AIRPORT_ORIGIN, from)
                .queryParam(AIRPORT_DESTINATION, to)
                .queryParam(DEPART_DATE, departsOn)
                .queryParam(ARRIVAL_DATE, arrivesOn)
                .queryParam(TICKET_NR, ticketNumber);

        Availability result = restTemplate.getForObject(builder.build().encode().toUri(),
                Availability.class);

        return result;
    }
}
