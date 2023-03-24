package com.disk91.etl.data.object.sub;

import com.disk91.etl.data.object.Hotspot;
import fr.ingeniousthings.tools.ClonnableObject;

public class BeaconHistory implements ClonnableObject<BeaconHistory>  {

    private long timeRef;           // corresponding to the hour of the history
    private int countBeacon;        // number of beacon during this period of time

    // --------

    public BeaconHistory clone() {
        BeaconHistory c = new BeaconHistory();
        c.setTimeRef(timeRef);
        c.setCountBeacon(countBeacon);
        return c;
    }

    // ---------


    public long getTimeRef() {
        return timeRef;
    }

    public void setTimeRef(long timeRef) {
        this.timeRef = timeRef;
    }

    public int getCountBeacon() {
        return countBeacon;
    }

    public void setCountBeacon(int countBeacon) {
        this.countBeacon = countBeacon;
    }
}
