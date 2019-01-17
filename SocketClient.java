/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedHashMap;
/**
 *
 * @author Kirby
 */
public class SocketClient {

    //class variables
    private Socket socket;
    private InputStream input;
    private BufferedReader reader;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	System.out.println("Start");
        JavaClient socketEx = new JavaClient();
        socketEx.openSocketClient("163.11.236.50", 8080);
        System.out.println("Everyone is connected!");
        //System.out.println("sending .....");
        String ReceivedMess;
        LinkedHashMap<String,String> sendVals = new LinkedHashMap <String,String>(50);
        sendVals.put("lat", "1");
        sendVals.put("lon", "12");
        sendVals.put("height", "123");
        boolean ClientWantstoTalk = true;
        while (ClientWantstoTalk) {
            socketEx.sendSocketData("3: lat lon height");
            ReceivedMess = socketEx.readFromSocket();          
           
            for(String keys: sendVals.keySet()){
                if((ReceivedMess != null) && (ReceivedMess).equals("ack") )  {
                    socketEx.sendSocketData(sendVals.get(keys));
                    ReceivedMess = socketEx.readFromSocket();
                }else{
                    System.out.println("Error: Server did not acknowledge element:"+keys);
                }
            }
            
            socketEx.closeSocketClient();
            ClientWantstoTalk = false;
            
            //System.out.println("connection ended");
        }
        //System.out.println("attempting to transmit destination");
        //socketEx.sendDestination("Hello Darling~");
        //System.out.println("attempting to transmit present coordinates");
        //socketEx.sendCurrCoord("coord");
        //String fromSocket = socketEx.readFromSocket();
        //socketEx.closeSocketClient();
    }

    public void openSocketClient(String ipAddress, int portNum) {

        try {

            //how to get ip address (aka InetAddress) 
            //Socket(String hostname, int port)....HOSTNAME = textual representation of IP address
            //InetAddress addr = InetAddress.getByName()
            socket = new Socket(ipAddress, portNum); //kedarst cu-secure IP: 10.13.74.237
            input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));

        } catch (IOException ex) {
            //Logger.getLogger(SocketClientExtension.class.getName()).log(Level.SEVERE, null, ex);
            //return "exception";
        }

        //SendDestination("HELLOOOO DARLINGS~");
        //return "connected";
    }

    public void closeSocketClient() {
        try {
            socket.close();
        } catch (IOException ex) {
            //Logger.getLogger(SocketClientExtension.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendSocketData(String data) {
        //data = "HELLOOOOOOOOO DARLINGS~";
        try {
            OutputStream output = socket.getOutputStream();
            //System.out.println("outputStream setup");
            //convert data into bytes
            byte[] dataByteArr = data.getBytes();
            output.write(dataByteArr);
            //System.out.println("outputStream written to");
        } catch (IOException ex) {
            //Logger.getLogger(SocketClientExtension.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String readFromSocket() {
			String message = "not initialized yet :P";
			try { 
				message = reader.readLine(); 
				if ((message) != null) { 
					System.out.println(message + " <----------read from socket"); 
				}    
			} catch (IOException ex) { 
				return "error receiving message";
			}
			
			return message;
    }
}
