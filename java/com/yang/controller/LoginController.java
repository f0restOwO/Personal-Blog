package com.yang.controller;


import com.yang.entity.AdminLoginLog;
import com.yang.service.AdminLoginLogService;
import com.yang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

@Controller
public class LoginController {
    @Autowired
    AdminLoginLogService adminLoginLogService;
    @Autowired
    AdminService adminService;

    @RequestMapping(value = {"/admin/index", "/admin", "/admin/login"})
    public String toIndex() {
        return "admin/login";
    }

    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    public @ResponseBody Object loginCheck(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String password= request.getParameter("password");
        HashMap<String, String> res = new HashMap<>();
        if (adminService.getById(id) == null) {
            res.put("stateCode", "0");
        } else if (!adminService.getById(id).getPassword().equals(password)) {
            res.put("stateCode", "1");
        } else {
            String ip = request.getRemoteAddr();
            AdminLoginLog adminLoginLog = new AdminLoginLog();
            adminLoginLog.setAdmin_id(id);
            adminLoginLog.setDate(new Date());
            adminLoginLog.setIp(ip);
            int log = adminLoginLogService.insert(adminLoginLog);
            Cookie cookie = new Cookie("userId", "" + id);
            cookie.setMaxAge(3600 * 24);
            response.addCookie(cookie);
            request.getSession().setAttribute("admin", adminService.getById(id));
            res.put("stateCode", "2");
        }
        return res;
    }

    @RequestMapping(value = {"/admin/logout"})
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "forward:/admin";
    }

}
