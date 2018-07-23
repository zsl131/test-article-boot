package com.zslin.dao;

import com.zslin.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zsl on 2018/7/19.
 */
public interface IArticleDao extends JpaRepository<Article, Integer> {

    @Query("SELECT a FROM Article a WHERE a.title LIKE ?1")
    List<Article> findLikeTitle(String title);

    Article findByUsername(String username);

    Article findByUsernameAndTitle(String username, String title);

    @Query("UPDATE Article a SET a.title=?2 WHERE a.id=?1")
    @Modifying
    @Transactional
    void update(Integer id, String title);
}
