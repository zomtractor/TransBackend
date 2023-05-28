package com.xiaosuange.clipboardjava.web;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SynConnect extends Thread {
    @Override
    public void run() {
        try{
            ServerSocket ssk = new ServerSocket(8081);
            Socket sk = ssk.accept();
            Scanner sc = new Scanner(System.in);
            OutputStream os = sk.getOutputStream();
            while(sc.hasNext()){
                int x = sc.nextInt();
                os.write(x);
                System.out.println(x);
            }
        } catch (Exception e){
            e.printStackTrace();
            run();

        }
    }
}
