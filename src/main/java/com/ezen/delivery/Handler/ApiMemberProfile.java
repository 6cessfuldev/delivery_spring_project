package com.ezen.delivery.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ezen.delivery.domain.UserVO;


public class ApiMemberProfile {


    public static UserVO getProfile(String accessToken) {
        String token = accessToken; // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가

        String apiURL = "https://openapi.naver.com/v1/nid/me";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);

        UserVO uvo = new UserVO();

		try {
			
			JSONParser parser = new JSONParser();
	        Object obj = parser.parse( responseBody );
	        
			JSONObject jsonObj = (JSONObject) obj;
			jsonObj =  (JSONObject) jsonObj.get("response");

			String email = (String) jsonObj.get("email");
			uvo.setUser_email(email);

			String id = email.substring(0, email.indexOf("@"));
			uvo.setUser_id(id);
			
			String name = (String) jsonObj.get("name");
			uvo.setUser_name(name);

			String birthday = (String) jsonObj.get("birthday");
			String birthyear =(String) jsonObj.get("birthyear");

			birthyear = birthyear.substring(2);
			birthday = birthday.replace("-","");
			String birth = birthyear+birthday;
			uvo.setUser_birth(birth);
			
			String mobile = (String) jsonObj.get("mobile");
			uvo.setUser_phone(mobile);		
			
			String naver_id = (String) jsonObj.get("id");
			uvo.setUser_naver_id(naver_id);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		return uvo;
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}

