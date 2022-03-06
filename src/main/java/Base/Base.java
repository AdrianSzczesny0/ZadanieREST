package Base;

import Utility.ENV;

import java.util.HashMap;
import java.util.Map;

public class Base {


    public String baseURI ="";
    public  ENV env;

    public static Map<String,String> initializeHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Connection", "keep-alive");
        header.put("Accept", "application/json");
        header.put("Content-Type","application/json");
        header.put("Origin","http://127.0.0.1:8000");
        header.put("Sec-Fetch-Site", "same-origin");
        header.put("Sec-Fetch-Mode", "cors");
        header.put("Sec-Fetch-Dest", "empty");
        header.put("Referer", "http://127.0.0.1:8000/docs");
        header.put("Accept-Language", "en-US,en;q=0.9,pl-PL;q=0.8,pl;q=0.7");
        return header;
    }

    public void baseURI(){
        switch(env){
            case DEV:{
                baseURI="http://127.0.0.1:8000";
                break;
            }
            case DEV2:{
                baseURI="link to DEV api";
                break;
            }
            case PROD:{
                baseURI="link to PROD api";
                break;
            }
            case PRE_PROD:{
                baseURI="link to PRE PROD api";
                break;
            }
        }
    }
    public String getAPI(){
     return "";
    }

    public void SetUp(ENV environment){
        env = environment;
        baseURI();
    }

}
