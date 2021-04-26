package com.andrei.exchange.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Cube")
public class Cube {

    private double rate;

    private String currency;

    public double getRate ()
    {
        return rate;
    }

    public void setRate (double rate)
    {
        this.rate = rate;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "Cube [rate = "+rate+", currency = "+currency+"]";
    }

}


