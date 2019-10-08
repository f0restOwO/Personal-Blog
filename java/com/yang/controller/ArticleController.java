package com.yang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.yang.entity.Article;
import com.yang.entity.Comment;
import com.yang.service.ArticleService;
import com.yang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;

    @RequestMapping("/article")
    public ModelAndView detail(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("detail");
        int id = Integer.parseInt(request.getParameter("id"));
        List<Comment> comments = commentService.allComments(id, 0, 10);
        Article article = articleService.selectById(id);
        Article lastArticle = articleService.selectLastArticle(id);
        Article nextArticle = articleService.selectNextArticle(id);
        int clickNum = article.getClick();
        article.setClick(clickNum + 1);
        articleService.updateArticle(article);
        modelAndView.addObject("article",article);
        modelAndView.addObject("comments",comments);
        modelAndView.addObject("lastArticle",lastArticle);
        modelAndView.addObject("nextArticle",nextArticle);
        return modelAndView;
    }

    @RequestMapping("/admin/article/detail")
    public ModelAndView adminArticleDetail(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        ModelAndView modelAndView = new ModelAndView("admin/article_detail");
        Article article = articleService.selectById(id);
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    @RequestMapping("/admin/article/comment")
    public ModelAndView adminArticleComment(@RequestParam(required = true, name = "id") int id) {
//        int article_id = Integer.parseInt(request.getParameter("id"));
        List<Comment> comments = commentService.allComments(id, 0, 10);
        ModelAndView modelAndView = new ModelAndView("admin/comment_list");
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }

    @RequestMapping("/admin/article/list")
    public ModelAndView articleList(@RequestParam(required = true, defaultValue = "1") Integer page,
                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Article> articles = articleService.queryAll();
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        ModelAndView modelAndView = new ModelAndView("admin/article_list");
        modelAndView.addObject("articles", articles);
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    @RequestMapping("/admin/article/add")
    public String articleAdd() {
        return "admin/article_add";
    }

    @RequestMapping("/admin/article/add/do")
    public String articleAddDo(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setCatalog_id(Integer.parseInt(request.getParameter("catalog_Id")));
        article.setKeywords(request.getParameter("keywords"));
        article.setDesci(request.getParameter("desci"));
        article.setContent(request.getParameter("content"));
        article.setTime(new Date());
        if (articleService.insert(article)) {
            redirectAttributes.addFlashAttribute("succ", "发表文章成功");
        } else {
            redirectAttributes.addFlashAttribute("error", "发表文章失败");
        }
        return "redirect:/admin/article/add";
    }

    @RequestMapping(value = {"/admin/article/search"})
    public ModelAndView article(HttpServletRequest request) {
        String word = request.getParameter("word");
        List<Article> articles = articleService.selectByWord(word);
        ModelAndView modelAndView = new ModelAndView("admin/article_list");
        modelAndView.addObject("articles", articles);
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/article/edit"})
    public ModelAndView articleEdit(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Article article = articleService.selectById(id);
        ModelAndView modelAndView = new ModelAndView("admin/article_edit");
        modelAndView.addObject("article", article);
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/article/edit/do"})
    public ModelAndView articleEditDo(HttpServletRequest request) {
        Article article = new Article();
        article.setId(Integer.parseInt(request.getParameter("id")));
        article.setTitle(request.getParameter("title"));
        article.setCatalog_id(Integer.parseInt(request.getParameter("catalogId")));
        article.setKeywords(request.getParameter("keywords"));
        article.setDesci(request.getParameter("desci"));
        article.setContent(request.getParameter("content"));
        ModelAndView modelAndView=new ModelAndView("admin/article_edit");
        if (articleService.updateArticle(article)){
            modelAndView.addObject("succ", "修改文章成功！");

        }else {
            modelAndView.addObject("error", "修改文章失败！");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/api/article/del", method = RequestMethod.POST)
    public @ResponseBody Object loginCheck(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        int result = articleService.deleteById(id);
        HashMap<String, String> res = new HashMap<>();
        if (result == 1) {
            res.put("stateCode", "1");
        } else {
            res.put("stateCode", "0");
        }
        return res;
    }


}
