package com.route_manager.util;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.route_manager.model.Route;

import java.util.HashSet;
import java.util.Set;

@JacksonXmlRootElement(localName = "Routes")
public final class CollectionWrapper {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Route")
    private Set<Route> routes;

    public CollectionWrapper() {
        this.routes = new HashSet<>();
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }
}