package by.belstu.Lab8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
public class Connection {
    public static void main(String[] args) {
        //Connection information
        String urlName = "https://it.belstu.by";
        int timeout = 10_000;
        URL url;
        try {
            url = new URL(urlName);
            final URLConnection connection = url.openConnection();
            //Set connection timeout
            connection.setConnectTimeout(timeout);
            System.out.println(urlName + "\nContent type: "+
                    "\n" + connection.getContentType()+
                    "\n" + connection.getClass()+
                    "\n" + connection.getContentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Getting content from the page

        URL belstu = null;
        try {
            belstu = new URL(urlName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (belstu == null) {
            throw new RuntimeException();
        }
    }
}

