package com.yang.service;

import com.yang.entity.AdminLoginLog;

import java.util.List;

public interface AdminLoginLogService {

    List<AdminLoginLog> selectRecent(Integer adminId);

    int insert(AdminLoginLog adminLoginLog);

    int selectCountByAdminId(Integer adminId);
}
