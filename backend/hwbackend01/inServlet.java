

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/in")
public class inServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	
		process(request,response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if(action.equals("regist")) {
			doRegist(request,response);
		}

	}

	private void doRegist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String name = request.getParameter("product_name");
		int price = Integer.parseInt(request.getParameter("price"));
		String explain = request.getParameter("explain");
		
		response.setContentType("text/html; charset=UTF-8");
		TV tv = new TV(name,price,explain);
		
		
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<html><body>").append("<h1>입력내용</h1>").append(tv).append("</body></html>");
		response.getWriter().write(sb.toString());
	}



}
