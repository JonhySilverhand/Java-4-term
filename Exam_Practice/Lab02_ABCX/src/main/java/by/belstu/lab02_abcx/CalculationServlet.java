package by.belstu.lab02_abcx;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CalculationServlet", value = "/calculationServlet")
public class CalculationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение значений из запроса
        String aValue = request.getParameter("a");
        String bValue = request.getParameter("b");
        String cValue = request.getParameter("c");
        String xValue = request.getParameter("x");

        // Проверка наличия всех значений
        if (aValue.isEmpty() || bValue.isEmpty() || cValue.isEmpty() || xValue.isEmpty()) {
            response.sendRedirect("error.jsp"); // Перенаправление на страницу ошибки
        } else {
            // Преобразование значений в числа
            double a = Double.parseDouble(aValue);
            double b = Double.parseDouble(bValue);
            double c = Double.parseDouble(cValue);
            double x = Double.parseDouble(xValue);

            // Вычисление значения y
            double y = a + (b * x) + (c * x * x);

            // Передача значения y на новую JSP-страницу
            request.setAttribute("result", y);
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }
}
