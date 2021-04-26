package com.andrei.exchange.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Cube")
public class CubeWrapper {

    @JacksonXmlProperty(localName = "Cube")
    private Cubes cubes;

    public Cubes getCubes() {
        return cubes;
    }

    public void setCubes(Cubes cubes) {
        this.cubes = cubes;
    }

    @Override
    public String toString() {
        return "CubeWrapper[" +
                "cubes=" + cubes +
                ']';
    }
}
