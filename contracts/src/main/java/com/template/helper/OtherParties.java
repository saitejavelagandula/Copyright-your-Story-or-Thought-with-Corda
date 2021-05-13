package com.template.helper;

import net.corda.core.serialization.CordaSerializable;

import java.util.List;

@CordaSerializable
public class OtherParties {
    List<String> otherParties;

    public OtherParties(List<String> otherParties) {
        this.otherParties = otherParties;
    }

    public List<String> getOtherParties() {
        return otherParties;
    }

    public void setOtherParties(List<String> otherParties) {
        this.otherParties = otherParties;
    }
}
