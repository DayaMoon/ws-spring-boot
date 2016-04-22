package com.mtt.airline.converters;

import com.mtt.airline.dtos.*;
import com.mtt.airline.models.Availability;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmunteanu on 3/9/2016.
 */
public class Flight2FlightConverter implements Converter<com.mtt.airline.models.Availability.Flight, Flight>
{

    private final static MathContext mathContext = new MathContext(2);
    private static final int MINUTE = 1000 * 60;
    private static final int HOUR = MINUTE * 60;

    @Value("${configuredTimeZone.id}")
    private String timeZoneId;


    @Override
    public Flight convert(Availability.Flight sourceFlight)
    {
        if (null != sourceFlight)
        {
            Flight targetFlight = new Flight();

            targetFlight.setOperator(sourceFlight.getCarrierCode());
            targetFlight.setFlightNumber(sourceFlight.getFlightDesignator());
            targetFlight.setDepartsFrom(sourceFlight.getOriginAirport());
            targetFlight.setArrivesAt(sourceFlight.getDestinationAirport());

            populateFarePrices(targetFlight, sourceFlight);
            populateDateTimes(targetFlight, sourceFlight);

            return targetFlight;
        }
        return null;
    }

    private void populateFarePrices(Flight flightDTO, Availability.Flight sourceFlight)
    {
        final Map<String, FarePrice> farePrices = new HashMap<>();

        if (null != sourceFlight.getFares())
        {
            for (Availability.Flight.Fares.Fare fare : sourceFlight.getFares().getFare())
            {
                FarePrice farePrice = new FarePrice();

                farePrice.setBookingFee(convertPrice(fare.getFees()));
                farePrice.setTax(convertPrice(fare.getTax()));
                farePrice.setTicket(convertPrice(fare.getBasePrice()));
                farePrices.put(TicketClass.valueOf(fare.getClazz()).getClazz(), farePrice);
            }
            flightDTO.setFarePrices(farePrices);
        }
    }

    private Price convertPrice(String fee)
    {
        final String[] fees = fee.trim().split("\\s");
        if (fees.length == 2)
        {
            Price price = new Price();
            price.setCurrency(Currency.getInstance(fees[0]));
            price.setAmount(new BigDecimal(fees[1]).setScale(2, RoundingMode.HALF_UP));
            return price;
        }
        return null;
    }

    private void populateDateTimes(Flight flightDTO, Availability.Flight sourceFlight)
    {
        Date departDate = sourceFlight.getDepartureDate().toGregorianCalendar().getTime();
        Date arrivalDate = sourceFlight.getArrivalDate().toGregorianCalendar().getTime();

        LocalDateTime localDateTimeDeparture = LocalDateTime.ofInstant(departDate.toInstant(), ZoneId.of(timeZoneId));
        flightDTO.setDepartsOn(populateDateTime(localDateTimeDeparture));

        LocalDateTime localDateTimeArrival = LocalDateTime.ofInstant(arrivalDate.toInstant(), ZoneId.of(timeZoneId));
        flightDTO.setArrivesOn(populateDateTime(localDateTimeArrival));

        long flightTime = (arrivalDate.getTime() - departDate.getTime());
        flightDTO.setFlightTime(String.format("%02d", (flightTime / HOUR % 24)) + ":" + String.format("%02d",(flightTime / MINUTE % 60)));
    }

    private FlightDateTime populateDateTime(LocalDateTime localDateTime)
    {
        FlightDateTime flightDateTime = new FlightDateTime();
        flightDateTime.setDate(localDateTime);
        flightDateTime.setTime(localDateTime);
        return flightDateTime;
    }

}