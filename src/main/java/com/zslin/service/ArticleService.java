package com.zslin.service;

import com.zslin.dao.IArticleDao;
import com.zslin.dto.JsonObj;
import com.zslin.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zsl on 2018/7/23.
 */
@Service
public class ArticleService {

    @Autowired
    private IArticleDao articleDao;

    public JsonObj list() {
        List<Article> list = articleDao.findAll();
        return new JsonObj(list.size(), list);
    }
}
