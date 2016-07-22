package com.webservice.dao;

import org.hibernate.Session;

import com.webservice.entity.News;

public class NewsDAOImp implements NewsDAO {

	private Session session;
	
	public NewsDAOImp(Session session){
		this.session = session;
	}
	
	@Override
	public News createNews(News news) {
		// TODO Auto-generated method stub
		if(news == null) return null;
		if(getNewsById(news.getId()) == null){
			session.beginTransaction();
			session.save(news);
			session.getTransaction().commit();
			session.refresh(news);
			return news;
		}
		return null;
	}

	@Override
	public News getNewsById(int id) {
		// TODO Auto-generated method stub
		News news = (News)session.get(News.class, id);
		if(news == null) return null;
		session.refresh(news);
		return news;
	}

	@Override
	public boolean updateNews(News news) {
		// TODO Auto-generated method stub
		if(news == null) return false;
		if(getNewsById(news.getId()) != null){
			session.beginTransaction();
			session.update(news);
			session.getTransaction().commit();
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteNews(News news) {
		// TODO Auto-generated method stub
		if(news == null) return false;
		if(getNewsById(news.getId()) == null) return false;
		session.beginTransaction();
		session.delete(news);
		session.getTransaction().commit();
		return true;
	}

}
