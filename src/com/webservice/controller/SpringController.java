package com.webservice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.webservice.dao.*;
import com.webservice.entity.*;
import com.webservice.exceptions.AuthenticationException;
import com.webservice.exceptions.InvalidFileException;
import com.webservice.exceptions.InvalidIndexException;
import com.webservice.hibernateConf.HibernateUtil;
import com.webservice.jsonEntity.EventBrief;
import com.webservice.jsonEntity.EventDetail;
import com.webservice.jsonEntity.EventEntry;
import com.webservice.jsonEntity.NewsEntry;
import com.webservice.jsonEntity.Notice;
import com.webservice.jsonEntity.PasswordChange;
import com.webservice.jsonEntity.UserEntry;

@RestController
public class SpringController {
	
	@RequestMapping(value = "/uploadimg",
			method = RequestMethod.POST)
	public String uploadImg(@RequestHeader("Authentication") String key,
			@RequestParam("photo") MultipartFile file) throws IOException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			UserDAO udao = new UserDAOImp(session);
			User user;
			String username, password;
			try{
				String[] keyPair = key.split(":");
				username = keyPair[0];
				password = keyPair[1];
				user = udao.getUserByUsername(username);
			}catch(RuntimeException e){
				throw new AuthenticationException();
			}
			if(!user.getPassword().equals(password)){
				throw new AuthenticationException();
			}
			String uploadPath = System.getProperty("user.home")+"/Documents/Eclipse/Workspace/Web Service/users";
			File folder = new File(uploadPath, username);
			if(!folder.exists()){
				folder.mkdirs();
			}
			File newFile = new File(uploadPath+File.separator+username, file.getOriginalFilename());
			String newFileName = newFile.getName();
			String pattern = ".*\\.jpg$";
			Pattern reg = Pattern.compile(pattern);
			Matcher matcher = reg.matcher(newFileName);
			if(!matcher.find() || newFile.exists()){
				throw new InvalidFileException();
			}
			newFile.createNewFile();
			FileOutputStream out = null;
			out = new FileOutputStream(newFile);
			int len;
			byte[] buffer = new byte[1024];
			FileInputStream in =(FileInputStream) file.getInputStream();
			while((len = in.read(buffer)) > 0){
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();
			return "Uploaded Successfully!";
		}finally{
			session.close();
		}
	}
	
	@RequestMapping(value = "/mysubscriptions",
			method = RequestMethod.GET)
	public Set<EventEntry> getMySubs(@RequestHeader("Authentication") String key){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			UserDAO udao = new UserDAOImp(session);
			User user;
			String username, password;
			try{
				String[] keyPair = key.split(":");
				username = keyPair[0];
				password = keyPair[1];
				user = udao.getUserByUsername(username);
			}catch(RuntimeException e){
				throw new AuthenticationException();
			}
			if(!user.getPassword().equals(password)){
				throw new AuthenticationException();
			}
			Set<Event> events = user.getSubEvents();
			Set<EventEntry> rs = new HashSet<EventEntry>();
			for(Event e : events){
				EventEntry ee = new EventEntry(e);
				rs.add(ee);
			}
			return rs;	
		}finally{
			session.close();
		}

	}
	
	@RequestMapping(value = "/createaccount",
			method = RequestMethod.POST)
	public Notice createAccount(@RequestBody User user){
		Notice notice = new Notice();
		notice.setTitle("Unsuccessfully create account");
		notice.setDetail("Application form is in bad format");
		notice.setTips("Provide email as username and password");
		if(user == null){
			return notice;
		}
		String username = user.getUsername();
		String password = user.getPassword();
		if(username == null || password == null){
			return notice;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		UserDAO udao = new UserDAOImp(session);
		if(udao.getUserByUsername(username) != null){
			notice.setDetail("Email has been used");
			notice.setTips("Use another email");
			session.close();
			return notice;
		}
		User newUser = new User();
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		try{
			udao.createUser(newUser);
			notice.setTitle("Successfully");
			notice.setDetail("Login and explore more");
			notice.setTips("Lookup helps information");
			return notice;
		}catch(HibernateException e){
			notice.setDetail(e.getMessage());
			notice.setTips("Use correct information or contact admin");
			return notice;
		}finally{
			session.close();
		}
	}
	
	@RequestMapping(value = "/changepassword",
			method = RequestMethod.POST)
	public Notice changePassword(@RequestBody PasswordChange pc){
		Notice notice = new Notice();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			String username = pc.getUsername();
			String oPassword = pc.getOldPassword();
			String nPassword = pc.getNewPassword();
			String cPassword = pc.getConfirmedPassword();
			UserDAO udao = new UserDAOImp(session);
			User user = udao.getUserByUsername(username);
			if(user == null){
				notice.setTitle("Unseccessfully Change Password");
				notice.setDetail("User is not existed");
				notice.setTips("Use correct username");
				return notice;
			}
			if(!oPassword.equals(user.getPassword())){
				notice.setTitle("Unseccessfully Change Password");
				notice.setDetail("Password and username not matched");
				notice.setTips("Use correct username and password");
				return notice;
			}
			if(!nPassword.equals(cPassword)){
				notice.setTitle("Unseccessfully Change Password");
				notice.setDetail("New passwords are different");
				notice.setTips("Use same new password");
				return notice;
			}
			user.setPassword(nPassword);
			udao.updateUser(user);
			notice.setTitle("Seccessfully Change Password");
			notice.setDetail("Use new password to login");
			notice.setTips("Lookup helps for more information");
			return notice;
		}catch(NullPointerException e){
			notice.setTitle("Unseccessfully Change Password");
			notice.setDetail(e.getMessage());
			notice.setTips("Use correct information");
			return notice;
		}catch(HibernateException e){
			notice.setTitle("Unseccessfully Change Password");
			notice.setDetail(e.getMessage());
			notice.setTips("Use correct information or contact admin");
			return notice;
		}finally{
			session.close();
		}
	}
	
	@RequestMapping(value = "/events",
			method = RequestMethod.GET)
	public Set<EventEntry> getEventsInJson(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<EventEntry> rs = new HashSet<EventEntry>();
		Criteria criteria = session.createCriteria(Event.class);  
		List<Event> events = criteria.list();
		for(Event e : events){
			EventEntry ee = new EventEntry(e);
			rs.add(ee);
		}
		session.close();
		return rs;
	}
	
	@RequestMapping(value = "/user/{id}",
			method = RequestMethod.GET)
	public UserEntry getUserByIdInJson(@PathVariable int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try{
			UserDAO udao = new UserDAOImp(session);
			User user = udao.getUserById(id);
			Set<Event> events = user.getPubEvents();
			Set<EventBrief> ebs = new HashSet<EventBrief>();
			for(Event e : events){
				EventBrief eb = new EventBrief(e);
				ebs.add(eb);
			}
			UserEntry rs = new UserEntry();
			rs.setEvents(ebs);
			rs.setId(user.getId());
			rs.setUsername(user.getUsername());
			return rs;
		}finally{
			session.close();
		}

	}
	
	@RequestMapping(value = "/event/{id}",
			method = RequestMethod.GET)
	public EventDetail getEventByIdInJson(@PathVariable int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		EventDAO edao = new EventDAOImp(session);
		Event event = edao.getEventById(id);
		Set<News> news = event.getNews();
		Set<NewsEntry> nes = new HashSet<NewsEntry>();
		for(News n : news){
			NewsEntry ne = new NewsEntry(n);
			nes.add(ne);
		}
		EventDetail rs = new EventDetail();
		EventEntry ee = new EventEntry(event);
		rs.setEvent(ee);
		rs.setNews(nes);
		session.close();
		return rs;
	}
	
	//Exception Handler
	@ResponseStatus(value=HttpStatus.UNAUTHORIZED ,reason="Authentication error. Please try correct login infomation.")
	@ExceptionHandler(AuthenticationException.class)
	public void userNotFoundError(){
		
	}
	
	//@ResponseStatus(value=HttpStatus.FORBIDDEN ,reason="DB Error")
	//@ExceptionHandler(HibernateException.class)
	//public void dbError(){
	//	
	//}
	/*
	@ResponseStatus(value=HttpStatus.NOT_FOUND ,reason="Try valid request")
	@ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
	public void notFoundError(){
		
	}
	*/
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR ,reason="Unknown server error")
	@ExceptionHandler(RuntimeException.class)
	public void unknownError(){
		
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND ,reason="Requested information is not found")
	@ExceptionHandler(InvalidIndexException.class)
	public void invalidIndexError(){
		
	}
	
	@ResponseStatus(value=HttpStatus.FORBIDDEN ,reason="File is in invalid file type or duplicated file name.")
	@ExceptionHandler(InvalidFileException.class)
	public void fileInfoError(){
		
	}
}
