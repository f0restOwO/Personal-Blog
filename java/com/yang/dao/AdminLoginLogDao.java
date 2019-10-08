package com.yang.dao;

import com.yang.entity.AdminLoginLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminLoginLogDao {
    int deleteByPrimaryKey(Long id);
    int insert(AdminLoginLog record);
    int insertSelective(AdminLoginLog record);
    AdminLoginLog selectByPrimaryKey(Long id);
    int updateByPrimaryKey(AdminLoginLog adminLoginLog);
    int updateByPrimaryKeySelective(AdminLoginLog adminLoginLog);
    List<AdminLoginLog> queryAll();
    List<AdminLoginLog> selectRencent(Integer admin_id);
    int selectCountByAdminId(Integer admin_id);
}
