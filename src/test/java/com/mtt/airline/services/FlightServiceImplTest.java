package com.mtt.airline.services;

import com.mtt.airline.accessors.FlightAccessor;
import com.mtt.airline.accessors.FlightAccessorImpl;
import com.mtt.airline.converters.Flight2FlightConverter;
import com.mtt.airline.dtos.AvailabilityResult;
import com.mtt.airline.models.Availability;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.convert.converter.Converter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by dmunteanu on 3/9/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightServiceImplTest
{
    @InjectMocks
    private FlightService flightService = new FlightServiceImpl();

    @Mock
    private FlightAccessor flightAccessor = new FlightAccessorImpl();

    @Mock
    private Converter converter = new Flight2FlightConverter();


    @Test
    public void testFindFlights() throws Exception
    {
        Availability flights = getXmlData();

        when(flightAccessor.getFlight(anyString(), anyString(), anyString(), anyString(), anyInt()))
                .thenReturn(flights);

        AvailabilityResult flightResult = flightService.findFlights(anyString(), anyString(), anyString(), anyString(),
                anyInt());
        assertEquals(flights.getFlight().size(),
                flightResult.getAvailability().size());
    }

    private Availability getXmlData()
    {
        try
        {
            File file = new File("src/test/resources/3rd-party-flights.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Availability.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Availability result = (Availability) jaxbUnmarshaller.unmarshal(file);
            return result;

        } catch (JAXBException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}