package com.zslin.controller;

import com.zslin.dao.IArticleDao;
import com.zslin.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zsl on 2018/7/19.
 */
@RestController
@RequestMapping(value = "test/article")
public class ArticleController {

    @Autowired
    private IArticleDao articleDao;

//    @RequestMapping(value = "list", method = RequestMethod.GET)
    @GetMapping(value = "list")
    public List<Article> list(HttpServletRequest request) {
        List<Article> list = articleDao.findAll();
        return list;
    }

    @GetMapping(value = "show")
    public Article show(Integer id) {
        Article a = articleDao.findOne(id);
        return a;
    }
}
