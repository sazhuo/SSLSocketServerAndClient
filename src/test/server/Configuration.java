package test.server;


import java.io.File;  
import java.io.FileInputStream;  
import java.io.InputStream;  
import java.util.Properties;  


public class Configuration {  
  private static Properties config;  
    
    
  public static Properties getConfig(){  
//      try{  
//          if(null == config){  
//              File configFile = new File("./conf/conf.properties");  
//              if(configFile.exists() && configFile.isFile()  
//                      && configFile.canRead()){  
//                  InputStream input = new FileInputStream(configFile);  
//                  config = new Properties();  
//                  config.load(input);  
//              }  
//          }  
//      }catch(Exception e){  
          //default set  
          config = new Properties();  
          config.setProperty("authority", "1");
          config.setProperty("protocol", "TLSV1");  
          config.setProperty("serverCer", "/Users/test/Desktop/sean_appserver.jks");  
          config.setProperty("serverCerPwd", "1234sp");  
          config.setProperty("serverKeyPwd", "1234kp");  
          config.setProperty("serverTrustCer", "/Users/test/Desktop/sean_appserverTrust.jks");  
          config.setProperty("serverTrustCerPwd", "1234sp");  
          config.setProperty("clientCer", "/Users/test/Desktop/sean_appclient.jks");  
          config.setProperty("clientCerPwd", "1234sp");  
          config.setProperty("clientKeyPwd", "1234kp");  
          config.setProperty("clientTrustCer", "/Users/test/Desktop/sean_appclientTrust.jks");  
          config.setProperty("clientTrustCerPwd", "1234sp");  
          config.setProperty("serverListenPort", "10001");  
          config.setProperty("serverThreadPoolSize", "5");  
          config.setProperty("serverRequestQueueSize", "10");  
          config.setProperty("socketStreamEncoding", "UTF-8");  
//      }  
      return config;  
  }  
}  