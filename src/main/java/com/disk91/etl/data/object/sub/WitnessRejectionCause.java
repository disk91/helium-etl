package com.disk91.etl.data.object.sub;

import fr.ingeniousthings.tools.ClonnableObject;

import java.util.List;

public class WitnessRejectionCause implements ClonnableObject<WitnessRejectionCause> {

    private int cause=0 ;     // seen cause
    private int count=0;     // times this cause has been seen


    // ---------


    public WitnessRejectionCause clone() {
        WitnessRejectionCause c = new WitnessRejectionCause();
        c.setCause(cause);
        c.setCount(count);
        return c;
    }


    // ---------

    public int getCause() {
        return cause;
    }

    public void setCause(int cause) {
        this.cause = cause;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
