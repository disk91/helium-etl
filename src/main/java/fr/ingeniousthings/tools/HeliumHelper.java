package fr.ingeniousthings.tools;

import com.google.protobuf.ByteString;

import java.util.Arrays;

public class HeliumHelper {

    public static byte[] pubAddressWithType( byte[] pubAddr ) {
        if (pubAddr.length > 0) {
            byte [] extra= new byte[pubAddr.length+1];
            extra[0] = 0;
            System.arraycopy(pubAddr, 0, extra, 1, pubAddr.length);
            return extra;
        } else return null;
    }


    public static String pubAddressToName( byte[] pubAddr ) {
        if (pubAddr.length > 0) {
            byte [] extra= new byte[pubAddr.length+1];
            extra[0] = 0;
            System.arraycopy(pubAddr, 0, extra, 1, pubAddr.length);
            return Base58.encode(extra, true);
        } else return "";
    }

    public static String pubAddressToName( ByteString pubAddr ) {
        return pubAddressToName(pubAddr.toByteArray());
    }

    /**
     * Convert a public address as a byte [] into a solana address
     */
    public static String solanaAddress( byte [] pubAddr ) {
        if ( pubAddr.length > 0 ) {
            byte[] r = new byte[pubAddr.length-1];
            System.arraycopy(pubAddr,1,r,0,pubAddr.length-1);
            return Base58.encode(r,false);
        } else return "";
    }

    /**
     * Convert a Solana Base58 address into a public address as a byte []
     */
    public static byte[] solanaToPubAddress(String name) {
        try {
            if (name.length() > 0) {
                byte[] decoded = Base58.decode(name, false);
                byte[] r = new byte[decoded.length+1];
                r[0] = 0x01; 
                System.arraycopy(decoded,0,r,1,decoded.length);
                return r;
            }
        } catch (ITParseException x) {
            return null;
        }
        return null;
    }

    public static byte[] nameToPubAddress(String name) {
        try {
            if (name.length() > 0) {
                byte[] decoded = Base58.decode(name, true);
                byte[] r = new byte[decoded.length-1];
                System.arraycopy(decoded,1,r,0,decoded.length-1);
                return r;
            }
        } catch (ITParseException x) {
            return null;
        }
        return null;
    }


}
