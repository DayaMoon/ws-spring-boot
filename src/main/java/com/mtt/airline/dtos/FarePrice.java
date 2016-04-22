package com.mtt.airline.dtos;

/**
 * Created by dmunteanu on 3/7/2016.
 */
public class FarePrice
{
    private Price ticket;
    private Price bookingFee;
    private Price tax;

    public Price getBookingFee()
    {
        return bookingFee;
    }

    public void setBookingFee(Price bookingFee)
    {
        this.bookingFee = bookingFee;
    }

    public Price getTax()
    {
        return tax;
    }

    public void setTax(Price tax)
    {
        this.tax = tax;
    }

    public Price getTicket()
    {
        return ticket;
    }

    public void setTicket(Price ticket)
    {
        this.ticket = ticket;
    }

}
