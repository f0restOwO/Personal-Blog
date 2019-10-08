package com.yang.service.impl;

import com.yang.dao.CommentDao;
import com.yang.entity.Comment;
import com.yang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;
    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
    @Override
    public List<Comment> allComments(Integer article_id, int offset, int limit) {
        return commentDao.queryAll(article_id, offset, limit);
    }

    @Override
    public int insertComment(Comment comment) {
        return commentDao.insert(comment);
    }

    @Override
    public int countAllNum() {
        return commentDao.countAllNum();
    }

    @Override
    public boolean deleteById(Long id) {
        return commentDao.deletePrimaryKey(id) > 0;
    }
}
