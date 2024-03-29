package com.disk91.etl.data.object.sub;

import fr.ingeniousthings.tools.ClonnableObject;

import java.util.ArrayList;
import java.util.List;

public class WitnessHistory  implements ClonnableObject<WitnessHistory> {

    private long timeRef;           // corresponding to the hour of the history
    private int countWitnesses;     // number of witness during this period of time

    private int seletedWitness = 0;     // number of witness selected during this period of time

    private int totLateMs = 0;      // distance in ms from the first hotspot responding to poc, give an idea of the cpu/network response time

    private List<WitnessRejectionCause> rejections = new ArrayList<>(); // witness rejection cause during this time slot

    // ---------

    public void addTotLateMs(long deltaMs) {
        this.totLateMs += (int)deltaMs;
    }

    public void addCause(int cause) {
        if ( rejections == null ) {
            rejections = new ArrayList<>();
        } else {
            for (WitnessRejectionCause r : rejections) {
                if (r.getCause() == cause) {
                    r.setCount(r.getCount() + 1);
                    return;
                }
            }
        }
        WitnessRejectionCause r = new WitnessRejectionCause();
        r.setCause(cause);
        r.setCount(1);
        rejections.add(r);
    }

    public WitnessHistory clone() {
        WitnessHistory c = new WitnessHistory();
        c.setTimeRef(timeRef);
        c.setCountWitnesses(countWitnesses);
        c.setSeletedWitness(seletedWitness);
        c.setTotLateMs(totLateMs);
        c.setRejections(new ArrayList<WitnessRejectionCause>());
        if ( rejections != null ) {
            for (WitnessRejectionCause r : rejections) {
                c.getRejections().add(r.clone());
            }
        }
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

    public int getSeletedWitness() {
        return seletedWitness;
    }

    public void setSeletedWitness(int seletedWitness) {
        this.seletedWitness = seletedWitness;
    }

    public int getTotLateMs() {
        return totLateMs;
    }

    public void setTotLateMs(int totLateMs) {
        this.totLateMs = totLateMs;
    }

    public List<WitnessRejectionCause> getRejections() {
        return rejections;
    }

    public void setRejections(List<WitnessRejectionCause> rejections) {
        this.rejections = rejections;
    }
}
