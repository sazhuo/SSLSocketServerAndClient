package test.server;

import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.net.Socket;  
import java.util.Properties;  
  
public class Job implements Runnable {  
    private Socket socket;  
      
    public Job(Socket socket){  
        this.socket = socket;  
    }  
  
    public void run() {  
        Properties p = Configuration.getConfig();  
        String encoding = p.getProperty("socketStreamEncoding");  
          
        DataInputStream input = null;  
        DataOutputStream output = null;  
        try{  
            input = SocketIO.getDataInput(socket);  
          
            byte[] bytes = new byte[1028];  
            input.read(bytes);  
            String user = new String(bytes,encoding).trim();  
//            int pwd = input.read();  
              
            String result = null;  
            if(null != user && !user.equals("") && user.equals("name") ){  
                result = "login success";  
            }else{  
                result = "login failed";  
            }  
            System.out.println("request user:"+user);  
              
            output = SocketIO.getDataOutput(socket);  
              
            bytes = result.getBytes(encoding);  
            output.write(bytes);  
              
            System.out.println("response info:"+result);  
        }catch(Exception e){  
            e.printStackTrace();  
            System.out.println("business thread run exception");  
        }finally{  
            try {  
                socket.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("server socket close error");  
            }  
        }  
    }  
}  
