package com.yang.dao;

import com.yang.entity.Admin;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@ContextConfiguration(locations = {"classpath*:spring/spring-dao.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class AdminDaoTest {
    @Autowired
    private AdminDao adminDao;

    @org.junit.Test
    public void deleteByPrimaryKey() {
        System.out.println(adminDao.deleteByPrimaryKey(10080));
    }

    @org.junit.Test
    public void insert() {
        Admin admin = new Admin();
        admin.setId(10080);
        admin.setUsername("f0rest");
        admin.setPassword("111111");
        adminDao.insert(admin);
        System.out.println(admin);
    }

    @org.junit.Test
    public void insertSelective() {
    }

    @org.junit.Test
    public void selectByPrimaryKey() {
        Admin admin = adminDao.selectByPrimaryKey(10080);
        System.out.println(admin.getPassword());
    }

    @org.junit.Test
    public void updateByPrimaryKeySelective() {
    }

    @org.junit.Test
    public void updateByPrimaryKey() {
    }
}