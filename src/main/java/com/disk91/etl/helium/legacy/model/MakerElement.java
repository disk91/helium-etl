package com.disk91.etl.helium.legacy.model;

import com.disk91.etl.data.object.Hotspot;

import javax.annotation.PostConstruct;
import java.util.HashMap;

public class MakerElement {
    protected String id;
    protected String name;
    protected Hotspot.HotspotBrand brand;

    public MakerElement(String _id, String _name) {
        this.id = _id;
        this.name = _name;
        this.brand = GetHotspotBrand(_id);
    }

    public static HashMap<String, MakerElement> InitHostpotBrandCache() {
        HashMap<String, MakerElement> cache = new HashMap<>();
        MakerElement m = new MakerElement("13daGGWvDQyTyHFDCPz8zDSVTWgPNNfJ4oh31Teec4TRWfjMx53", "Helium Inc" ); cache.put(m.getId(), m);
        m = new MakerElement("13ENbEQPAvytjLnqavnbSAzurhGoCSNkGECMx7eHHDAfEaDirdY", "Cal-Chip Connected Devices" ); cache.put(m.getId(), m);
        m = new MakerElement("13Zni1he7KY9pUmkXMhEhTwfUpL9AcEV1m2UbbvFsrU9QPTMgE3", "Nebra Ltd" ); cache.put(m.getId(), m);
        m = new MakerElement("14rb2UcfS9U89QmKswpZpjRCUVCVu1haSyqyGY486EvsYtvdJmR", "SyncroB.it" ); cache.put(m.getId(), m);
        m = new MakerElement("14sKWeeYWQWrBSnLGq79uRQqZyw3Ldi7oBdxbF6a54QboTNBXDL", "Bobcat" ); cache.put(m.getId(), m);
        m = new MakerElement("12zX4jgDGMbJgRwmCfRNGXBuphkQRqkUTcLzYHTQvd4Qgu8kiL4", "LongAP" ); cache.put(m.getId(), m);
        m = new MakerElement("13MS2kZHU4h6wp3tExgoHdDFjBsb9HB9JBvcbK9XmfNyJ7jqzVv", "Smart Mimic" ); cache.put(m.getId(), m);
        m = new MakerElement("14h2zf1gEr9NmvDb2U53qucLN2jLrKU1ECBoxGnSnQ6tiT6V2kM", "RAKwireless" ); cache.put(m.getId(), m);
        m = new MakerElement("13Mpg5hCNjSxHJvWjaanwJPBuTXu1d4g5pGvGBkqQe3F8mAwXhK", "Kerlink" ); cache.put(m.getId(), m);
        m = new MakerElement("13LVwCqZEKLTVnf3sjGPY1NMkTE7fWtUVjmDfeuscMFgeK3f9pn", "DeWi Foundation" ); cache.put(m.getId(), m);
        m = new MakerElement("14NBXJE5kAAZTMigY4dcjXSMG4CSqjYwvteQWwQsYhsu2TKN6AF", "SenseCAP" ); cache.put(m.getId(), m);
        m = new MakerElement("134C7Hn3vhfBLQZex4PVwtxQ2uPJH97h9YD2bhzy1W2XhMJyY6d", "Pisces Miner" ); cache.put(m.getId(), m);
        m = new MakerElement("13XuP2DjHEHVkKguDDZD2ev5AeqMLuJ8UQ44efEcDmVTnBcvc6F", "ClodPi" ); cache.put(m.getId(), m);
        m = new MakerElement("14eUfY1GsjK4WH6uZYoeagnFtigBKdvPruAXLmc5UsUMEDj3yib", "Linxdot" ); cache.put(m.getId(), m);
        m = new MakerElement("13cbbZXzqwp6YMM5JvAu5T1TRhenENEJVU5Q8vpLhunQYE1Acpp", "COTX" ); cache.put(m.getId(), m);
        m = new MakerElement("14go8hvEDnotWTyhYv6Hu5PTnRUAQzJqbB6dsDm1oThkCcZe9zd", "Controllino" ); cache.put(m.getId(), m);
        m = new MakerElement("14iC6N1HkqUjH7WEChHVQhPqJ1hbWBKpZXZVeHHykCA7tNDYF2C", "Heltec Automation" ); cache.put(m.getId(), m);
        m = new MakerElement("13y2EqUUzyQhQGtDSoXktz8m5jHNSiwAKLTYnHNxZq2uH5GGGym", "FreedomFi" ); cache.put(m.getId(), m);
        m = new MakerElement("13v9iGhjvQUtVaZXcFFRCEbL1nPR4R8QJowBgMUcaGM2v1aV6mn", "PantherX" ); cache.put(m.getId(), m);
        m = new MakerElement("14DdSjvEkBQ46xQ24LAtHwQkAeoUUZHfGCosgJe33nRQ6rZwPG3", "hummingbird" ); cache.put(m.getId(), m);
        m = new MakerElement("143QF5dTGyAg1FqaE85mPApvjauFU8fUy6wavirQfJJDgSkC4gn", "RisingHF" ); cache.put(m.getId(), m);
        m = new MakerElement("14iLrXUuGVhb7w1P8X3iBvRwoT9oemgbDonm5VVZJk56TdV8NV1", "Browan/MerryIoT" ); cache.put(m.getId(), m);
        m = new MakerElement("13H6RpJojJjkqPRfzdrFBDbpYw4b3A6HyMxgGFRgXf31Nuwq4xJ", "Milesight" ); cache.put(m.getId(), m);
        m = new MakerElement("146S5XCtvB1VwqYzJnRr8goiADUFTscGAUb2ihKUBVYuvMrrNCx", "Deeper" ); cache.put(m.getId(), m);
        m = new MakerElement("14fzfjFcHpDR1rTH8BNPvSi5dKBbgxaDnmsVPbCjuq9ENjpZbxh", "Helium Inc (Old)" ); cache.put(m.getId(), m);
        m = new MakerElement("13eZdVcXajB3HrxKqeoPRPVPXbpqbDbaFpey5KfALk44VUphC5Z", "Midas" ); cache.put(m.getId(), m);
        m = new MakerElement("13gb8SJg8MZSyLakrx8ago146Gxk7tpvY4R3kgMSYGzAKE188m2", "Dragino" ); cache.put(m.getId(), m);
        m = new MakerElement("14ZVsWQ8D98NCW673qxNepcYu1nfBiuuF6rhU2JHCFpfgqtnc5S", "Pycom"); cache.put(m.getId(), m);
        return cache;
    }

    public static Hotspot.HotspotBrand GetHotspotBrand(String id) {
        if ( id != null ) {
            if (id.compareToIgnoreCase("13daGGWvDQyTyHFDCPz8zDSVTWgPNNfJ4oh31Teec4TRWfjMx53") == 0)
                return Hotspot.HotspotBrand.HELIUM;
            if (id.compareToIgnoreCase("13ENbEQPAvytjLnqavnbSAzurhGoCSNkGECMx7eHHDAfEaDirdY") == 0)
                return Hotspot.HotspotBrand.CALCHIP;
            if (id.compareToIgnoreCase("13Zni1he7KY9pUmkXMhEhTwfUpL9AcEV1m2UbbvFsrU9QPTMgE3") == 0)
                return Hotspot.HotspotBrand.NEBRA;
            if (id.compareToIgnoreCase("14rb2UcfS9U89QmKswpZpjRCUVCVu1haSyqyGY486EvsYtvdJmR") == 0)
                return Hotspot.HotspotBrand.SYNCROBIT;
            if (id.compareToIgnoreCase("12zX4jgDGMbJgRwmCfRNGXBuphkQRqkUTcLzYHTQvd4Qgu8kiL4") == 0)
                return Hotspot.HotspotBrand.LONGAP;
            if (id.compareToIgnoreCase("14sKWeeYWQWrBSnLGq79uRQqZyw3Ldi7oBdxbF6a54QboTNBXDL") == 0)
                return Hotspot.HotspotBrand.BOBCAT;
            if (id.compareToIgnoreCase("13MS2kZHU4h6wp3tExgoHdDFjBsb9HB9JBvcbK9XmfNyJ7jqzVv") == 0)
                return Hotspot.HotspotBrand.SMARTMIMIC;
            if (id.compareToIgnoreCase("14h2zf1gEr9NmvDb2U53qucLN2jLrKU1ECBoxGnSnQ6tiT6V2kM") == 0)
                return Hotspot.HotspotBrand.RAK;
            if (id.compareToIgnoreCase("13Mpg5hCNjSxHJvWjaanwJPBuTXu1d4g5pGvGBkqQe3F8mAwXhK") == 0)
                return Hotspot.HotspotBrand.KERLINK;
            if (id.compareToIgnoreCase("13LVwCqZEKLTVnf3sjGPY1NMkTE7fWtUVjmDfeuscMFgeK3f9pn") == 0)
                return Hotspot.HotspotBrand.DEWI;
            if (id.compareToIgnoreCase("14NBXJE5kAAZTMigY4dcjXSMG4CSqjYwvteQWwQsYhsu2TKN6AF") == 0)
                return Hotspot.HotspotBrand.SENSECAP;
            if (id.compareToIgnoreCase("134C7Hn3vhfBLQZex4PVwtxQ2uPJH97h9YD2bhzy1W2XhMJyY6d") == 0)
                return Hotspot.HotspotBrand.PISCES;
            if (id.compareToIgnoreCase("13XuP2DjHEHVkKguDDZD2ev5AeqMLuJ8UQ44efEcDmVTnBcvc6F") == 0)
                return Hotspot.HotspotBrand.CLODPI;
            if (id.compareToIgnoreCase("14eUfY1GsjK4WH6uZYoeagnFtigBKdvPruAXLmc5UsUMEDj3yib") == 0)
                return Hotspot.HotspotBrand.LINXDOT;
            if (id.compareToIgnoreCase("13cbbZXzqwp6YMM5JvAu5T1TRhenENEJVU5Q8vpLhunQYE1Acpp") == 0)
                return Hotspot.HotspotBrand.COTX;
            if (id.compareToIgnoreCase("14go8hvEDnotWTyhYv6Hu5PTnRUAQzJqbB6dsDm1oThkCcZe9zd") == 0)
                return Hotspot.HotspotBrand.CONTROLLINO;
            if (id.compareToIgnoreCase("14iC6N1HkqUjH7WEChHVQhPqJ1hbWBKpZXZVeHHykCA7tNDYF2C") == 0)
                return Hotspot.HotspotBrand.HELTEC;
            if (id.compareToIgnoreCase("13y2EqUUzyQhQGtDSoXktz8m5jHNSiwAKLTYnHNxZq2uH5GGGym") == 0)
                return Hotspot.HotspotBrand.FREEDOMFI;
            if (id.compareToIgnoreCase("13v9iGhjvQUtVaZXcFFRCEbL1nPR4R8QJowBgMUcaGM2v1aV6mn") == 0)
                return Hotspot.HotspotBrand.PANTHERX;
            if (id.compareToIgnoreCase("14DdSjvEkBQ46xQ24LAtHwQkAeoUUZHfGCosgJe33nRQ6rZwPG3") == 0)
                return Hotspot.HotspotBrand.HUMMINGBIRD;
            if (id.compareToIgnoreCase("143QF5dTGyAg1FqaE85mPApvjauFU8fUy6wavirQfJJDgSkC4gn") == 0)
                return Hotspot.HotspotBrand.RISINGHF;
            if (id.compareToIgnoreCase("14iLrXUuGVhb7w1P8X3iBvRwoT9oemgbDonm5VVZJk56TdV8NV1") == 0)
                return Hotspot.HotspotBrand.BROWAN;
            if (id.compareToIgnoreCase("13H6RpJojJjkqPRfzdrFBDbpYw4b3A6HyMxgGFRgXf31Nuwq4xJ") == 0)
                return Hotspot.HotspotBrand.MILESIGHT;
            if (id.compareToIgnoreCase("146S5XCtvB1VwqYzJnRr8goiADUFTscGAUb2ihKUBVYuvMrrNCx") == 0)
                return Hotspot.HotspotBrand.DEEPER;
            if (id.compareToIgnoreCase("14fzfjFcHpDR1rTH8BNPvSi5dKBbgxaDnmsVPbCjuq9ENjpZbxh") == 0)
                return Hotspot.HotspotBrand.HELIUM;
            if (id.compareToIgnoreCase("13eZdVcXajB3HrxKqeoPRPVPXbpqbDbaFpey5KfALk44VUphC5Z") == 0)
                return Hotspot.HotspotBrand.MIDAS;
            if (id.compareToIgnoreCase("13gb8SJg8MZSyLakrx8ago146Gxk7tpvY4R3kgMSYGzAKE188m2") == 0)
                return Hotspot.HotspotBrand.DRAGINO;
            if (id.compareToIgnoreCase("14ZVsWQ8D98NCW673qxNepcYu1nfBiuuF6rhU2JHCFpfgqtnc5S") == 0)
                return Hotspot.HotspotBrand.PYCOM;
        }
        return Hotspot.HotspotBrand.UNKNOWN;
    }

    // ---


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hotspot.HotspotBrand getBrand() {
        return brand;
    }

    public void setBrand(Hotspot.HotspotBrand brand) {
        this.brand = brand;
    }
}
