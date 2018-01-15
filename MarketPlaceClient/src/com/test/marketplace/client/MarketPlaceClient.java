package com.test.marketplace.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MarketPlaceClient {

	private static String CREATE_PROJECT_URL = "http://localhost:8080/market-place/service/project/create";
	private static String CREATE_BID_URL = "http://localhost:8080/market-place/service/project/bid";
	private static String GET_OPEN_PROJECTS = "http://localhost:8080/market-place/service/project/openProjects";
	private static String GET_PROJECT = "http://localhost:8080/market-place/service/project";

	public static void main(String[] args) {
		String createPayload = "{\"title\" : \"Test3\", \"description\" : \"Test3\", \"maxBidAmout\" : \"200\", \"maxbidDate\" : \"19/01/2018\",\"userId\":\"100\"}";
		String createResp = sendRequest(CREATE_PROJECT_URL, createPayload, "POST", false);
		System.out.println(createResp);
		
		String bidPayload = "{\"bidAmount\" : \"100\",\"userId\":\"202\",\"projectId\":1}";
		String bidResp = sendRequest(CREATE_BID_URL, bidPayload, "POST", false);
		System.out.println(bidResp);

		String openProjects = sendRequest(GET_OPEN_PROJECTS, null, "GET", true);
		System.out.println(openProjects);
		
		
		String project = sendRequest(GET_PROJECT+"/1", null, "GET", true);
		System.out.println(project);

	}

	private static String sendRequest(String requestUrl, String payload, String requestMethod, boolean IsJsonOutput) {
		StringBuilder jsonString = new StringBuilder();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(requestMethod);
			connection.setConnectTimeout(5000);
			if(IsJsonOutput) {
				connection.setRequestProperty("Accept", "application/json");
			}
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			if (payload != null) {
				OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
				writer.write(payload);
				writer.close();
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonString.toString();
	}
}
