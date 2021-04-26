package com.andrei.exchange.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(namespace = "http://www.gesmes.org/xml/2002-08-01")
public class Envelope
{
    @JacksonXmlProperty
    private String subject;

    @JacksonXmlProperty
    private Object Sender;

    public Object getSender() {
        return Sender;
    }

    @JacksonXmlProperty(namespace = "Cube")
    private CubeWrapper Cube;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSender(Object sender) {
        Sender = sender;
    }

    public CubeWrapper getCubeWrapper ()
    {
        return Cube;
    }

    public void setCubeWrapper (CubeWrapper Cube)
    {
        this.Cube = Cube;
    }

    @Override
    public String toString()
    {
        return "Envelope [Cube = "+Cube+"]";
    }
}