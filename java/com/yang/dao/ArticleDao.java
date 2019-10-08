package com.yang.dao;

import com.yang.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ArticleDao {
    int insert(Article article);
    int insertSelective(Article article);
    Article selectByPrimaryKey(Integer id);
    Article selectLastArticle(Integer id);
    Article selectNextArticle(Integer id);
    int deleteByPrimaryKey(Integer id);
    int updateByPrimaryKey(Article record);
    int updateByPrimaryKeySelective(Article article);
    int updateByPrimaryKeyWithBLOBs(Article article);
    int countAllNum();

    List<Article> queryAll();

    List<Article> selectByWord(String word);

}
