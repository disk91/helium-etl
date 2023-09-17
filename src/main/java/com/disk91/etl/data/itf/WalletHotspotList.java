package com.disk91.etl.data.itf;

import java.util.ArrayList;
import java.util.List;

public class WalletHotspotList {

    protected String walletId;
    protected List<String> hotspotsECCId;

    // ---

    public void init(String _walletId) {
        this.walletId = _walletId;
        this.hotspotsECCId = new ArrayList<>();
    }

    // ---

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public List<String> getHotspotsECCId() {
        return hotspotsECCId;
    }

    public void setHotspotsECCId(List<String> hotspotsECCId) {
        this.hotspotsECCId = hotspotsECCId;
    }
}
