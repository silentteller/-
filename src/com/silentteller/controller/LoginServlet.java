package com.silentteller.controller;

import com.silentteller.entity.Book;
import com.silentteller.entity.Reader;
import com.silentteller.entity.Admin;
import com.silentteller.service.BookService;
import com.silentteller.service.LoginService;
import com.silentteller.service.impl.BookServiceImpl;
import com.silentteller.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();
    private BookService bookService = new BookServiceImpl();
    /**
     * 处理登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        Object object = loginService.login(username, password, type);
        //object为null，则表明用户名密码错误
        if(object != null) {
            //将从数据库中读出的object存入session中
            HttpSession session = req.getSession();
            switch (type){
                case "reader":
                    Reader reader = (Reader) object;
                    session.setAttribute("reader", reader);
//                    List<Book> list = bookService.findAll(1);
//                    req.setAttribute("list", list);
//                    req.setAttribute("dataPrePage", 6);
//                    req.setAttribute("currentPage", 1);
//                    req.setAttribute("pages", bookService.getPages());
//                    //index.jsp
//                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                    resp.sendRedirect("/book?page=1");
                    break;
                case "admin":
                    Admin admin = (Admin) object;
                    session.setAttribute("admin", admin);
                    //admin.jsp

                    resp.sendRedirect("/admin?method=findAllBorrow&page=1");
                    break;
            }
        }else {
            resp.sendRedirect("login.jsp");
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }
}
