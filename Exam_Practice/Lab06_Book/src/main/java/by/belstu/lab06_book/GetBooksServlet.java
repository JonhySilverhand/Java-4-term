package by.belstu.lab06_book;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetBooksServlet", value = "/books")
public class GetBooksServlet extends HttpServlet {
    private static final BookDB db = new BookDB();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageStr = request.getParameter("page");
        String dbSize = request.getParameter("dbSize");


        if (dbSize == null){
            request.setAttribute("dbSize", db.getDbSize());
            dbSize= db.getDbSize() + "";
        }


        int pageInt;
        if(pageStr != null) {
            pageInt = Integer.parseInt(pageStr);
        } else {
            pageInt = 0;
        }

        if (pageInt *2 + 2 >= Integer.parseInt(dbSize)){
            request.setAttribute("IsNextAvailable",false);
        }
        else {
            request.setAttribute("IsNextAvailable",true);
        }


        ArrayList<Book> books = db.select(pageInt);
        if(books.size() == 0) {
            pageInt = 1;
            books = db.select(pageInt);
        }
        request.setAttribute("books", books);
        request.setAttribute("nextPage", pageInt + 1);

        getServletContext().getRequestDispatcher("/views/books.jsp").forward(request, response);
    }
}
