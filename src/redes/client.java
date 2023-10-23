package redes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args){
        final Socket clientSocket;
        final BufferedReader in; 
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in); 
        String ipAddress ;

        
        try {
            System.out.println("IP : ");
        	ipAddress = sc.nextLine();
            clientSocket = new Socket(ipAddress, 3000);
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread sender = new Thread(new Runnable() {
                String msg;
              
                public void run() {
                    while(true){
                        System.out.println("Cliente, escreva a mensagem: ");
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });
            sender.start();
            Thread receiver = new Thread(new Runnable() {
                String msg;
               
                public void run() {
                    try {
                        msg = in.readLine();
                        while(msg!=null){
                            System.out.println("Servidor : "+msg);
                            msg = in.readLine();
                        }
                        System.out.println("Servidor fora de serviço");
                        out.close();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiver .start();
    }catch (IOException e){
        e.printStackTrace();
        }
    }
}