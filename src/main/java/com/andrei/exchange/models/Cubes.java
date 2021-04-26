package com.andrei.exchange.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Date;
import java.util.List;


@JacksonXmlRootElement(localName = "Cube")
public class Cubes {

    @JacksonXmlProperty(localName = "time", isAttribute = true)
    private Date time;

    @JacksonXmlProperty(localName = "Cube")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Cube> cubes;


    public void setTime(Date time) {
        this.time = time;
    }

    public void setCubes(List<Cube> cubes) {
        this.cubes = cubes;
    }

    public List<Cube> getCubes() {
        return cubes;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public String toString()
    {
        return "Cubes [time = "+time+", cubes = "+cubes+"]";
    }
}
