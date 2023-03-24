package com.disk91.etl.data.object.sub;

import fr.ingeniousthings.tools.ClonnableObject;

public class WitnessHistory  implements ClonnableObject<WitnessHistory> {

    private long timeRef;           // corresponding to the hour of the history
    private int countWitnesses;     // number of witness during this period of time

    // ---------

    public WitnessHistory clone() {
        WitnessHistory c = new WitnessHistory();
        c.setTimeRef(timeRef);
        c.setCountWitnesses(countWitnesses);
        return c;
    }


    // ---------


    public long getTimeRef() {
        return timeRef;
    }

    public void setTimeRef(long timeRef) {
        this.timeRef = timeRef;
    }

    public int getCountWitnesses() {
        return countWitnesses;
    }

    public void setCountWitnesses(int countWitnesses) {
        this.countWitnesses = countWitnesses;
    }
}
