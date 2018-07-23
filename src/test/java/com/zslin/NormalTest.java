package com.zslin;

import com.zslin.dao.IArticleDao;
import com.zslin.model.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by zsl on 2018/7/19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class NormalTest {

    @Autowired
    private IArticleDao articleDao;

    @Test
    public void test01() {
        for(int i=0; i<10; i++) {
            Article a = new Article();
            a.setTitle("测试标题"+i);
            a.setContent("测试内容"+i);
            a.setAuthor("测试作者"+i);
            a.setUsername("username"+i);
            articleDao.save(a);
        }
    }

    @Test
    public void test02() {
        List<Article> list = articleDao.findAll();
        for(Article a : list) {
            System.out.println(a);
        }
    }

    @Test
    public void test03() {
        List<Article> list = articleDao.findLikeTitle("8");
        for(Article a : list) {
            System.out.println(a);
        }
    }

    @Test
    public void test04() {
        Article a = articleDao.findOne(4);
    }

    @Test
    public void test05() {
        Article a = articleDao.findByUsername("username2");
        System.out.println(a);
    }

    @Test
    public void test06() {
        articleDao.update(2, "修改的标题");
    }
}
