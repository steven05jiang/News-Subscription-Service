package com.webservice.test;

public class FunctionTest {

	public static void main(String[] args){
		String key = "alice@gmail.com:acclice";
		String[] keyPair = key.split(":");
		String username = keyPair[0];
		String password = keyPair[1];
		System.out.println(username);
		System.out.println(password);
	}
}
