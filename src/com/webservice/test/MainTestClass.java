package com.webservice.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;

public class MainTestClass {

	public static void main(String[] args) throws IllegalStateException, IOException{
		String url = "http://localhost:8080/Web_Service/uploadimg";
		//JSONObject obj = new JSONObject();
		//obj.put("username", "dog@yahoo.com");
		//obj.put("oldPassword", "Bigdog");
		//obj.put("newPassword", "smallDog");
		//obj.put("confirmedPassword", "smallDog");
		
		File photo = new File(System.getProperty("user.home")+"/Downloads","1.jpg");
		//System.out.println(photo.getAbsolutePath());
		FileInputStream ios = new FileInputStream(photo);
		byte[] bPhoto = new byte[(int) photo.length()];
		ios.read(bPhoto);
		ios.close();
		ContentBody cb = new ByteArrayBody(bPhoto, "Mi.jpg");
		FileBody fb = new FileBody(photo);
		ByteArrayBody bab = new ByteArrayBody(bPhoto, "Mice.jpg");
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();  
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		builder.addPart("photo", cb);
		HttpEntity he = builder.build();
		
		String url2 = "google.com";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet get = new HttpGet(url2);
		HttpPost post = new HttpPost(url);
		post.setHeader("Authentication", "alice@qq.com:acclice");
		post.setHeader("Accept", "application/json");
		post.setEntity(he);
		HttpResponse response = client.execute(post);
		System.out.println(response.getStatusLine());
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while((line = br.readLine())!=null){
			System.out.println(line);
		}
		
		/*
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Authentication", "alice@qq.com:acclice");
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.addRequestProperty("Content-length", he.toString().length()+"");
		
		OutputStream output = connection.getOutputStream();
		
		output.write(obj.toJSONString().getBytes());
		InputStream response = connection.getInputStream();
		try (Scanner scanner = new Scanner(response)) {
			//String responseBody = scanner.nextLine();
		    String responseBody = scanner.useDelimiter("\\A").next();
		    System.out.println(responseBody);
		}
		*/
	}
}
