package com.yang.service.impl;

import com.yang.dao.ArticleDao;
import com.yang.entity.Article;
import com.yang.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao;

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
    @Override
    public Article selectById(Integer id) {
        return articleDao.selectByPrimaryKey(id);
    }

    @Override
    public Article selectLastArticle(Integer id) {
        return articleDao.selectLastArticle(id);
    }

    @Override
    public Article selectNextArticle(Integer id) {
        return articleDao.selectNextArticle(id);
    }

    @Override
    public List<Article> queryAll() {
        return articleDao.queryAll();
    }

    @Override
    public int countAllNum() {
        return articleDao.countAllNum();
    }

    @Override
    public boolean updateArticle(Article article) {
        return articleDao.updateByPrimaryKeySelective(article) > 0;
    }

    @Override
    public int deleteById(Integer id) {
        return articleDao.deleteByPrimaryKey(id);
    }

    @Override
    public int selectCount() {
        return articleDao.countAllNum();
    }

    @Override
    public List<Article> selectByWord(String word) {
        return articleDao.selectByWord(word);
    }

    @Override
    public boolean insert(Article article) {
        return articleDao.insert(article) > 0;
    }
}
