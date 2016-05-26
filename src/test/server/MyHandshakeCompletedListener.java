package test.server;

import javax.net.ssl.HandshakeCompletedEvent;  
import javax.net.ssl.HandshakeCompletedListener;  
  
public class MyHandshakeCompletedListener implements HandshakeCompletedListener {  
  
    public void handshakeCompleted(HandshakeCompletedEvent arg0) {  
        System.out.println("Handshake finished successfully");  
    }  
} 