package com.cyzs.tcp;

import javax.sound.midi.SoundbankResource;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * author: xh
 */
public class FileTransfer {

    public static void main(String[] args) {
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            System.out.println("your ip:"+ip4.getHostAddress());
            Scanner scanner = new Scanner(System.in);
            System.out.println("you need receive file? yes/no");
            String s1 = scanner.nextLine();
            if ("yes".equals(s1)){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        server();
                    }
                }).start();
            }
            Thread.sleep(300);
            System.out.println("you need send file? yes/no");
            String s = scanner.nextLine();

            if ("yes".equals(s)){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        client();
                    }
                }).start();
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public static void server(){
        try {
            int sum = 0;
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(17211));
            System.out.println("start receive file");
            while (true){
                try {
                    Socket socket = serverSocket.accept();
                    InputStream inputStream = socket.getInputStream();
                    long time = System.currentTimeMillis();
                    File dir = new File("C:\\filetransfer");
                    if (!dir.exists()){ dir.mkdirs(); }

                    LocalDateTime date = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.hh.mm.ss.SSS");
                    long l = Files.copy(inputStream, Path.of("C:\\filetransfer\\"+formatter.format(date)+".zip"), StandardCopyOption.REPLACE_EXISTING);

                    long time1 = System.currentTimeMillis();
                    double t = (time1 - time)/1000;
                    double s = l/(1000*1000*t);
                    System.out.println("time:" + t + "s  speed:" + String.format("%.2f",s) + "MB/s" );
                    socket.close();
                    System.out.println("dir C:\\filetransfer");
                    sum +=1;
                    System.out.println(sum + "st finished");
                }catch (Exception e){
                    System.out.println("远程连接关闭");
                }
            }
        }catch (Exception e){
            System.out.println("");
        }
    }

    public static void client(){
        try {
            System.out.println("[send file command: send ip filepath], [quit command: quit]");
            System.out.print("[command]:");
            while (true){
                Socket socket = null;
                try {
                    Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();
                    if ("quit".equals(s)){
                        break;
                    }
                    String[] arr = s.split(" ");
                    if (arr.length != 3){
                        return;
                    }
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(arr[1],17211));
                    OutputStream outputStream = socket.getOutputStream();
                    long copy = Files.copy(Path.of(arr[2]), outputStream);
                    System.out.println("send finished");
                    System.out.print("[send]:");
                    outputStream.close();
                    socket.close();
                }catch (Exception e){
                    System.out.println("send fail");
                }finally {
                    if (socket != null){
                        socket.close();
                    }
                }
            }
        }catch (Exception e){
            System.out.println("fail");
        }
    }
}
