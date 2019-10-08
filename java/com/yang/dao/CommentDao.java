package com.yang.dao;

import com.yang.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    int deletePrimaryKey(@Param("id") Long id);
    int insert(Comment record);
    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Long id);

    int updateByPriamryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    int countAllNum();

    List<Comment> queryAll(@Param("article_id") Integer article_id, @Param("offset") int offset, @Param("limit") int limit);
}
