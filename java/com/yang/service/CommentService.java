package com.yang.service;

import com.yang.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    List<Comment> allComments(Integer article_id, int offset, int limit);

    int insertComment(Comment comment);

    int countAllNum();

    boolean deleteById(Long id);
}
