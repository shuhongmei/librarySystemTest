package com.ly.ssm.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.ssm.model.Book;
import com.ly.ssm.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {
    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private BookService bookService;

    @RequestMapping("search")
    @ResponseBody
    public JSONArray search_books(HttpServletRequest request, HttpServletResponse response) {
        String bookname = request.getParameter("bookName");
        System.out.println(bookname);
        List<Book> books = bookService.selectByName(bookname);

        JSONArray json = bookJson(books);

        HttpSession session = request.getSession();
        session.setAttribute("searchResult", json);
        System.out.println(json.toString());
        return json;
    }

    //返回json數據的方法
    private JSONArray bookJson(List<Book> books) {
        JSONArray json = new JSONArray();
        for (Book b : books) {
            JSONObject jo = new JSONObject();
            jo.put("book_id", b.getId());
            jo.put("bookName", b.getBookName());
            jo.put("bookWriter", b.getBookWriter());
            jo.put("Publisher", b.getPublisher());
            jo.put("isrent", b.getIsRent());
            json.add(jo);
        }
        return json;
    }

    @RequestMapping("borrowBook")
    @ResponseBody
    public boolean borrow(HttpServletRequest request, HttpServletResponse response) {
        Long id = new Long(request.getParameter("id"));
        System.out.println(id);
        Object user = request.getSession().getAttribute("user");
        System.out.println(user.toString());
        bookService.borrow(id, user.toString());
        return true;
    }

    @RequestMapping("showBorrow")
    @ResponseBody
    public JSONArray showBorrow(HttpServletRequest request, HttpServletResponse response) {
        Object user = request.getSession().getAttribute("user");
        System.out.println(user.toString());
        List<Book> books = bookService.selectBookByBorrowPerson(user.toString());

        JSONArray json = bookJson(books);

        System.out.println(json.toString());
        return json;
    }

    @RequestMapping("returnBook")
    @ResponseBody
    public boolean returnBook(HttpServletRequest request, HttpServletResponse response) {
        Long id = new Long(request.getParameter("id"));
        System.out.println(id);
        bookService.returnBook(id);
        return true;
    }
}
