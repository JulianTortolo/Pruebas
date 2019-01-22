package org.apache.maven.clients;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.maven.domain.Tasks.Login.GetTokenRequest;
import org.apache.maven.domain.Tasks.Login.GetTokenResponse;
import org.apache.maven.model.UserModel;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public final class UsersAPI {

    public static UserModel GetUser(int id){
        GetTokenResponse tokenResponse = GetApiToken("kinexo", "kinexo");
        String apiUrl = "https://gentle-eyrie-95237.herokuapp.com/users/" + id;
        UserModel userModel = new UserModel();
        Gson jsonParser = new Gson();
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", tokenResponse.getToken());

        try {
            HttpResponse<String> getUserHttpResult = Unirest.get(apiUrl)
                    .headers(headers).asString();

            if (getUserHttpResult.getStatus() == HttpStatus.OK.value()) {
                userModel = jsonParser.fromJson(getUserHttpResult.getBody(), UserModel.class);
            }
        } catch (Exception ex) {

        }

        return userModel;
    }

    public static GetTokenResponse GetApiToken(String user, String password){
        Gson jsonParser = new Gson();
        Map<String, String> headers = new HashMap<>();
        GetTokenResponse tokenResponse = new GetTokenResponse();
        GetTokenRequest tokenRequest = new GetTokenRequest(user, password);

        String apiUrl = "https://gentle-eyrie-95237.herokuapp.com/login";

        headers.put("Content-Type", "application/json");
        String requestBody = jsonParser.toJson(tokenRequest);
        try{
            HttpResponse<String> getTokenHttpResult = Unirest.post(apiUrl)
                    .headers(headers)
                    .body(requestBody)
                    .asString();

            tokenResponse = jsonParser.fromJson(getTokenHttpResult.getBody(), GetTokenResponse.class);
        }
        catch (Exception e){

        }

        return tokenResponse;
    }
}