package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Calculadora;

/**
 * Servlet implementation class CalculadoraServlet
 */
@WebServlet("/calculadora")
public class CalculadoraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculadoraServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		float num1 = Float.parseFloat(request.getParameter("num1"));
		float num2 = Float.parseFloat(request.getParameter("num2"));
		
		out.println("<html>");
		out.println("<head><title> Resultado das operações </title></head>");
		out.println("<body>");
		out.println("<h1> Resultado das operações </h1>");
		
		out.println("<h3> " + num1 + " + " + num2  + " = " + 
				(new Calculadora().soma(num1, num2)) + " </h3>");
		out.println("<h3> " + num1 + " - " + num2  + " = " + 
				(new Calculadora().subtracao(num1, num2)) + " </h3>");
		
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
