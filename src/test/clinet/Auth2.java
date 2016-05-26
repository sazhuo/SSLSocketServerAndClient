package test.clinet;

import java.io.FileInputStream;  
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;  
  
import javax.net.ssl.KeyManager;  
import javax.net.ssl.KeyManagerFactory;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.TrustManager;  
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import test.server.Configuration;  
  
public class Auth2 {  
    private static SSLContext sslContext;  
      
    public static SSLContext getSSLContext() throws Exception{  
		X509TrustManager tm = new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
			}
		};
        sslContext = SSLContext.getInstance("TLS"); 
		sslContext.init(null, new TrustManager[] { tm }, new SecureRandom());  
          
        return sslContext;  
    }  
}  
