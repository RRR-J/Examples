package com.test;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * Created by g01027567 on 11/08/2016.
 */
public class ReadFromKS {

    public static void main(String[] args) {

        try {

            FileInputStream is = new FileInputStream("c:/test.jks");
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            String password = "password";
            char[] passwd = password.toCharArray();
            keystore.load(is, passwd);
            String alias = "selfsigned";
            Key key = keystore.getKey(alias, passwd);

            if (key instanceof PrivateKey) {
                // Get certificate of public key
                Certificate cert = keystore.getCertificate(alias);
                // Get public key
                PublicKey publicKey = cert.getPublicKey();

                System.out.println(bytesToHex(publicKey.getEncoded()));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
