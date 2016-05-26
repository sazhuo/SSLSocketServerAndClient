package test.clinet;

import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;  
import java.util.Properties;  
  
import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLSocket;  
import javax.net.ssl.SSLSocketFactory;  
  
import test.server.Configuration;  
import test.server.MyHandshakeCompletedListener;  
import test.server.SocketIO;  
  
public class ClientNormal {  
    private SSLContext sslContext;  
//    private int serverPort = 10001;//  
//    private String serverAddress ="127.0.0.1";  
    
    private int serverPort =  5060;//10001;//  
    private String serverAddress ="192.168.41.86";// "127.0.0.1";  //
    
    private Socket socket;  
    private Properties p;  
      
    public ClientNormal(){  
        try {  
            p = Configuration.getConfig();  
              
//            sslContext = Auth2.getSSLContext();  
//            SSLSocketFactory factory = (SSLSocketFactory) sslContext.getSocketFactory();    
//            socket = (SSLSocket)factory.createSocket();   
//            String[] pwdsuits = socket.getSupportedCipherSuites();  
//            //socket可以使用所有支持的加密套件  
//            socket.setEnabledCipherSuites(pwdsuits);  
//            //默认就是true  
//            socket.setUseClientMode(true);  
//              
//            SocketAddress address = new InetSocketAddress(serverAddress, serverPort);  
//            socket.connect(address, 0);  
//              
//            MyHandshakeCompletedListener listener = new MyHandshakeCompletedListener();  
//            socket.addHandshakeCompletedListener(listener);  
            
            socket = new Socket(serverAddress,serverPort);
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("socket establish failed!");  
        }  
    }  
      
    public void request(){  
        try{  
            String encoding = p.getProperty("socketStreamEncoding");  
              
            DataOutputStream output = SocketIO.getDataOutput(socket);  
            String user = "name";  
            byte[] bytes = user.getBytes();//.getBytes(encoding);  
            int length =128;  
            int pwd = 123;  
            String name = "1000";
            
            String address = socket.getLocalAddress().toString().substring(1);
			int port = socket.getLocalPort();
			
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(output));
			pw.println("REGISTER sip:" + serverAddress + ":5060 SIP/2.0");
			pw.println("Via: SIP/2.0/TLS " + address + ":" + port + ";alias;branch=z9hG4bK.hYKNAg7r7;rport");
			pw.println("From: <sip:" + name + "@" + serverAddress + ">;tag=uXpl3xe1G");
			pw.println("To: sip:" + name + "@" + serverAddress);
			pw.println("CSeq: 20 REGISTER");
			pw.println("Call-ID: aw--tyHvKT");
			pw.println("Max-Forwards: 70");
			pw.println("Supported: replaces, outbound");
			pw.println("Accept: application/sdp");
			pw.println("Accept: text/plain");
			pw.println("Accept: application/vnd.gsma.rcs-ft-http+xml");
			pw.println("Contact: <sip:" + name + "@" + address + ":" + port
					+ ";transport=tls>;+sip.instance=\"<urn:uuid:aa11ae5c-b87c-44d8-8bb2-ae04c1" + name + ">\"");
			pw.println("Expires: 660");
			pw.println("User-Agent: Justek/1.0 (belle-sip/1.4.2)");
			pw.println("Content-Length: 0");
			pw.flush();

			System.out.println("Write data done");  

            DataInputStream input = SocketIO.getDataInput(socket);  
//            length = input.readShort();  
            bytes = new byte[1028];  
           int len = input.read(bytes);
              
            System.out.println("request result:"+new String(bytes,encoding));  
        }catch(Exception e){  
            e.printStackTrace();  
            System.out.println("request error");  
        }finally{  
            try {  
                socket.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    public static void main(String[] args){  
        ClientNormal client = new ClientNormal();  
        client.request();  
    }  
}  
