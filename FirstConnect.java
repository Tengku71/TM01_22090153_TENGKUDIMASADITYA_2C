import Model.ResponseModel;
import network.ConnectURI;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FirstConnect {
    public static void First() throws IOException {
        ConnectURI koneksisaya = new ConnectURI();
        URL myAddress = koneksisaya.buildURL("https://harber.mimoapps.xyz/api/getaccess.php");
        String response = koneksisaya.getResponseFromHttpUrl(myAddress);
        System.out.println(response);

        JSONArray responseJSON = new JSONArray(response);
        ArrayList<ResponseModel> responseModel = new ArrayList<>();
        for (int i = 0; i < responseJSON.length(); i++) {
            ResponseModel resModel = new ResponseModel();
            JSONObject myJSONObject = responseJSON.getJSONObject(i);
            resModel.setMsg(myJSONObject.getString("message"));
            resModel.setComment(myJSONObject.getString("status"));
            resModel.setStatus(myJSONObject.getString("comment"));
            responseModel.add(resModel);
        }

        System.out.println("Response are: ");
        MainActivity mainActivity = new MainActivity();
        for (int index = 0; index < responseModel.size(); index++) {
            System.out.println("MESSAGE\t:\t" + responseModel.get(index).getMsg());
            System.out.println("STATUS\t:\t" + responseModel.get(index).getStatus());
            System.out.println("COMMENT\t:\t" + responseModel.get(index).getComment());
        }
    }
}
