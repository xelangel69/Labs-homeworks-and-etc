package com.common.network;

import com.common.model.Route;

import java.io.Serializable;

public final class Request implements Serializable {
    private String commandName;
    private String stringArg;
    private Route routeArg;

    public Request(String commandName, String primitiveArg, Route routeArg) {
        this.commandName = commandName;
        this.stringArg = primitiveArg;
        this.routeArg = routeArg;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getPrimitiveArg() {
        return stringArg;
    }

    public void setPrimitiveArg(String stringArg) {
        this.stringArg = stringArg;
    }

    public Route getRouteArg() {
        return routeArg;
    }

    public void setObjArg(Route routeArg) {
        this.routeArg = routeArg;
    }

    @Override
    public String toString() {
        return "{" + commandName + "}" + "{" + stringArg + "}" + "{" + routeArg + "}";
    }
}