package com.yang.service.impl;

import com.yang.dao.AdminDao;
import com.yang.entity.Admin;
import com.yang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public Admin getById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }
}
