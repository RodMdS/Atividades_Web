package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Imc
 */
@WebServlet("/imc")
public class ImcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImcServlet() {
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
		
		String[] classes = {"magreza grave", "magreza moderada", "magreza leve",
				"saudável", "sobrepeso", "obesidade", "obesidade severa",
				"obesidade mórbida"};
		
		float massa = Float.parseFloat(request.getParameter("massa"));
		float altura = Float.parseFloat(request.getParameter("altura"));
		
		float resultado = (new controller.Imc()).calcular(massa, altura);
		
		out.println("<html>");
		out.println("<head><title> Resultado do IMC </title></head>");
		out.println("<body>");
		
		if(resultado < 16)
			out.println("<h3> Seu IMC é " + resultado + ", considerado " 
		+ classes[0] + ". </h3>");
		else if(resultado < 17)
			out.println("<h3> Seu IMC é " + resultado + ", considerado " 
					+ classes[1] + ". </h3>");
		else if(resultado < 18.5)
			out.println("<h3> Seu IMC é " + resultado + ", considerado " 
					+ classes[2] + ". </h3>");
		else if(resultado < 25)
			out.println("<h3> Seu IMC é " + resultado + ", considerado " 
					+ classes[3] + ". </h3>");
		else if(resultado < 30)
			out.println("<h3> Seu IMC é " + resultado + ", considerado " 
					+ classes[4] + ". </h3>");
		else if(resultado < 35)
			out.println("<h3> Seu IMC é " + resultado + ", considerado " 
					+ classes[5] + ". </h3>");
		else if(resultado < 40)
			out.println("<h3> Seu IMC é " + resultado + ", considerado " 
					+ classes[6] + ". </h3>");
		else 
			out.println("<h3> Seu IMC é " + resultado + ", considerado " 
					+ classes[7] + ". </h3>");
		
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
