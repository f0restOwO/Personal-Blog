package com.yang.service.impl;

import com.yang.dao.AdminLoginLogDao;
import com.yang.entity.AdminLoginLog;
import com.yang.service.AdminLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLoginLogServiceImpl implements AdminLoginLogService {

    @Autowired
    AdminLoginLogDao adminLoginLogDao;

    @Override
    public List<AdminLoginLog> selectRecent(Integer admin_id) {
        return adminLoginLogDao.selectRencent(admin_id);
    }

    @Override
    public int insert(AdminLoginLog adminLoginLog) {
        return adminLoginLogDao.insert(adminLoginLog);
    }

    @Override
    public int selectCountByAdminId(Integer admin_id) {
        return adminLoginLogDao.selectCountByAdminId(admin_id);
    }
}
