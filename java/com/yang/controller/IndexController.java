package com.yang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.dao.AdminDao;
import com.yang.entity.Admin;
import com.yang.entity.Article;
import com.yang.service.ArticleService;
import com.yang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    @Autowired
    AdminDao adminDao;

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(required = true, defaultValue="1") Integer page,
                              @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        ModelAndView mav = new ModelAndView("index");
        List<Article> articles = articleService.queryAll();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        mav.addObject("articles", articles);
        mav.addObject("pageInfo", pageInfo);
        return mav;
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }


}
