package fr.ingeniousthings.tools;

import com.google.protobuf.ByteString;

import java.util.Arrays;

public class HeliumHelper {

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

}
