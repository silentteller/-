package com.silentteller.controller;

import com.silentteller.entity.Book;
import com.silentteller.entity.Borrow;
import com.silentteller.entity.Reader;
import com.silentteller.repository.BookRepository;
import com.silentteller.repository.impl.BookRepositoryImpl;
import com.silentteller.service.BookService;
import com.silentteller.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method == null)
            method = "findAll";
        HttpSession session = req.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        switch (method){
            case "findAll":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Book> list = bookService.findAll(page);
                req.setAttribute("list", list);
                req.setAttribute("dataPrePage", 6);
                req.setAttribute("currentPage", page);
                req.setAttribute("pages", bookService.getPages());
                //index.jsp
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
            case "addBorrow":
                String bookidStr = req.getParameter("bookid");
                Integer bookid = Integer.parseInt(bookidStr);
                System.out.println(bookid+ " " +reader.getId());
                bookService.addBorrow(bookid, reader.getId());
                resp.sendRedirect("/book?method=findAllBorrow&page=1");
                break;
                //展示当前用户的所有借书记录
            case "findAllBorrow":
                pageStr = req.getParameter("page");
                page  = Integer.parseInt(pageStr);
                List<Borrow> borrowList = bookService.findAllBorrowByReaderId(reader.getId(), page);
                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPages(reader.getId()));
                req.getRequestDispatcher("borrow.jsp").forward(req,resp);
                break;
        }
    }
}
