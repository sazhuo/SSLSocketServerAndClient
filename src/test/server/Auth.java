package test.server;
import java.io.FileInputStream;  
import java.security.KeyStore;  
import java.util.Properties;  
  
import javax.net.ssl.KeyManager;  
import javax.net.ssl.KeyManagerFactory;  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.TrustManager;  
import javax.net.ssl.TrustManagerFactory;
public class Auth {  
    private static SSLContext sslContext;  
      
    public static SSLContext getSSLContext() throws Exception{  
        Properties p = Configuration.getConfig();  
        String protocol = p.getProperty("protocol");  
        String serverCer = p.getProperty("serverCer");  
        String serverCerPwd = p.getProperty("serverCerPwd");  
        String serverKeyPwd = p.getProperty("serverKeyPwd");  
          
        //Key Stroe  
        KeyStore keyStore = KeyStore.getInstance("JKS");    
        keyStore.load(new FileInputStream(serverCer),   
                serverCerPwd.toCharArray());    
          
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");   
        keyManagerFactory.init(keyStore, serverKeyPwd.toCharArray());   
        KeyManager[] kms = keyManagerFactory.getKeyManagers();  
          
        TrustManager[] tms = null;  
        if(Configuration.getConfig().getProperty("authority").equals("2")){  
            String serverTrustCer = p.getProperty("serverTrustCer");  
            String serverTrustCerPwd = p.getProperty("serverTrustCerPwd");  
              
            //Trust Key Store  
            keyStore = KeyStore.getInstance("JKS");    
            keyStore.load(new FileInputStream(serverTrustCer),   
                    serverTrustCerPwd.toCharArray());    
              
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");   
            trustManagerFactory.init(keyStore);   
            tms = trustManagerFactory.getTrustManagers();  
        }  
        sslContext = SSLContext.getInstance(protocol);  
        sslContext.init(kms, tms, null);    
          
        return sslContext;  
    }  
}  