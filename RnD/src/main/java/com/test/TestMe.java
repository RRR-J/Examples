package com.test;

import javax.net.ssl.*;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

/**
 * Created by g01027567 on 28/06/2016.
 */
public class TestMe {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException, CertificateEncodingException {

        URL url = new URL("https://gbrdsr000001001.intranet.barcapint.com:10075");

        SSLContext sslCtx = SSLContext.getInstance("TLS");
        sslCtx.init(null, new TrustManager[]{new X509TrustManager() {

            private X509Certificate[] accepted;

            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String string) {
                accepted = xcs;
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return accepted;
            }
        }}, null);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String string, SSLSession ssls) {
                return true;
            }
        });

        connection.setSSLSocketFactory(sslCtx.getSocketFactory());

        if (connection.getResponseCode() == 200) {
            Certificate[] certificates = connection.getServerCertificates();
            for (int i = 0; i < certificates.length; i++) {
                Certificate certificate = certificates[i];
                PublicKey publicKey = certificate.getPublicKey();
                publicKey.toString();
                File file = new File("c:/newcert_" + i + ".crt");
                byte[] buf = certificate.getEncoded();

                FileOutputStream os = new FileOutputStream(file);
                os.write(buf);
                os.close();
            }
        }

        connection.disconnect();
    }

}
