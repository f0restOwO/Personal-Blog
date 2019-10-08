package com.yang.controller;

import com.yang.entity.Admin;
import com.yang.entity.AdminLoginLog;
import com.yang.service.AdminLoginLogService;
import com.yang.service.ArticleService;
import com.yang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminLoginLogService adminLoginLogService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/main")
    public ModelAndView toMain(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/main");
        String clientIp = request.getRemoteAddr();
        String hostIp = request.getLocalAddr();
        int hostPort = request.getLocalPort();
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String dates = df.format(date);
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        AdminLoginLog lastLoginLog = null;
        try {
            if (adminLoginLogService.selectRecent(admin.getId()) != null && adminLoginLogService.selectRecent(admin.getId()).size()==2) {
                List<AdminLoginLog> adminloginLogs = adminLoginLogService.selectRecent(admin.getId());
                lastLoginLog = adminloginLogs.get(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            int articleCount = articleService.selectCount();
            int commentCount = commentService.countAllNum();

            modelAndView.addObject("clientIp", clientIp);
            modelAndView.addObject("articleCount", articleCount);
            modelAndView.addObject("commentCount", commentCount);
            modelAndView.addObject("hostIp", hostIp);
            modelAndView.addObject("hostPort", hostPort);
            modelAndView.addObject("date", dates);
            if (lastLoginLog != null) {
                modelAndView.addObject("loginLog", lastLoginLog);
            }
            if (admin != null) {
                int loginNum = adminLoginLogService.selectCountByAdminId(admin.getId());
                modelAndView.addObject("admin", admin);
                modelAndView.addObject("loginNum", loginNum);
            }
            return modelAndView;
        }
    }
}
