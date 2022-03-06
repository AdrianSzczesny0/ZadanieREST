package Api;
import Base.Base;
import org.json.simple.JSONObject;

public class Game extends Base {

    private String apiUri ="/game";

    @Override
    public String getAPI(){
        return apiUri;
    }

    public String getGamesAPI(){
        return apiUri = "/games";
    }
    public String putGamesAPI(int vendorID){
        return apiUri = "/games/"+vendorID;
    }
    public String getGamesAPI(int vendorID){
        return apiUri = "/games/"+vendorID;
    }

    public JSONObject setPUTGamePayload(int vendorId,String vendor,String gameName,boolean active,boolean mobile,boolean visible){
        JSONObject requestBody = new JSONObject();
        requestBody.put("vendorId",vendorId);
        requestBody.put("vendor",vendor);
        requestBody.put("gameName",gameName);
        requestBody.put("active",active);
        requestBody.put("mobile",mobile);
        requestBody.put("visible",visible);
        return requestBody;
    }

}
