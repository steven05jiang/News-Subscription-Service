package com.webservice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.hibernate.Session;
import com.webservice.dao.EventDAO;
import com.webservice.dao.EventDAOImp;
import com.webservice.dao.UserDAO;
import com.webservice.dao.UserDAOImp;
import com.webservice.entity.Event;
import com.webservice.entity.User;
import com.webservice.hibernateConf.HibernateUtil;

public class MainClass {
	public static void main(String[] args){
		FileInputStream ios = null;
		File file = new File(System.getProperty("user.home")+"/Downloads","1.jpg");
		byte[] bFile = new byte[(int) file.length()];
		try {
			ios = new FileInputStream(file);
			ios.read(bFile);
			ios.close();
		    for (int i = 0; i < bFile.length; i++) {
		       	System.out.print((char)bFile[i]);
	           }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
