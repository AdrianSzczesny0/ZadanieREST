package Api;

import Base.Base;

public class Form extends Base {
    private String apiUri ="/form";

    @Override
    public String getAPI(){
        return apiUri;
    }
}
