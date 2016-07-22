package com.webservice.dao;

import com.webservice.entity.News;

public interface NewsDAO {
	public News createNews(News news);
	public News getNewsById(int id);
	public boolean updateNews(News news);
	public boolean deleteNews(News news);
}