package com.example.android.currencyrates;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ujjwal on 20-02-2018.
 */

public class NetworkUtils {

    final static String MY_URL = "https://api.fixer.io/latest";

    public static URL buildUrl(String base){

        Uri uri;

        if(base == ""){
            uri = Uri.parse(MY_URL).buildUpon().build();
        }else{
            uri = Uri.parse(MY_URL).buildUpon().appendQueryParameter("base", base).build();
        }



        URL url = null;
        try{
            url = new URL(uri.toString());
        }catch(MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try{
            InputStream is = connection.getInputStream();

            Scanner scanner = new Scanner(is);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }else{
                return null;
            }
        }finally {
            connection.disconnect();
        }

    }

}
