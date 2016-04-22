package com.mtt.airline.converters;

import com.mtt.airline.dtos.FarePrice;
import com.mtt.airline.dtos.Flight;
import com.mtt.airline.dtos.TicketClass;
import com.mtt.airline.models.Availability;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.test.util.ReflectionTestUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by dmunteanu on 3/9/2016.
 */
public class Flight2FlightConverterTest
{
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mma");

    private static final int MINUTE = 1000 * 60;
    private static final int HOUR = MINUTE * 60;

    private String timeZoneId = "UTC"; //GMT;

    private Converter converter;

    @Before
    public  void setup()
    {
        converter = new Flight2FlightConverter();
        ReflectionTestUtils.setField(converter, "timeZoneId", "GMT");
    }

    @Test
    public void testConvert() throws Exception
    {
        Availability.Flight origFlight = getXmlData();

        Flight convertedFlight = (Flight) converter.convert(origFlight);

        assertEquals(origFlight.getOriginAirport(), convertedFlight.getDepartsFrom());
        assertEquals(origFlight.getDestinationAirport(), convertedFlight.getArrivesAt());
        assertEquals(origFlight.getCarrierCode(), convertedFlight.getOperator());
        assertEquals(origFlight.getFlightDesignator(), convertedFlight.getFlightNumber());

        Calendar cal = origFlight.getDepartureDate().toGregorianCalendar();
        TimeZone tz = cal.getTimeZone();
        Calendar locCal = Calendar.getInstance();
        SortedSet<String> zones =  new TreeSet<>(ZoneId.getAvailableZoneIds());

      //  assertEquals("GMT", tz.getID());
        /*
        <DepartureDate>2014-01-24T20:14:00.000Z</DepartureDate> <ArrivalDate>2014-01-25T01:50:00.000Z</ArrivalDate>
         */
        String duration = "05:36";
        assertEquals(duration, convertedFlight.getFlightTime());

        assertEquals("24-01-2014", convertedFlight.getDepartsOn().getDate());
        assertEquals("08:14PM", convertedFlight.getDepartsOn().getTime());

        Availability.Flight.Fares.Fare firstFare = origFlight.getFares().getFare().get(0);
        String clazz = firstFare.getClazz();
        String clazz1 = TicketClass.valueOf(clazz).getClazz();
        FarePrice fp = convertedFlight.getFarePrices().get(clazz1);

        assertEquals(true, convertedFlight.getFarePrices().containsKey(clazz1));

        assertEquals(firstFare.getBasePrice(), fp.getTicket().getCurrency() + " " + fp.getTicket().getAmount());

    }

    private Availability.Flight getXmlData()
    {
        try
        {
            //read xml data from file to avoid big original Flight object setup
            File file = new File("src/test/resources/3rd-party-flights.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(Availability.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Availability origFlights = (Availability) jaxbUnmarshaller.unmarshal(file);
            if (origFlights.getFlight().size() > 0)
            {
                // get one original flight sample
                return origFlights.getFlight().get(0);
            }
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}