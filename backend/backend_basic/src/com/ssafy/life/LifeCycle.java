package com.ssafy.life;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lifecycle.do")
public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int a =10; //여기 변수는 모두가 같이 사용 , 그래서 잘 안쓰임
    
	public LifeCycle() { //// Printer 객체 생성 후  out.println하면 웹 브라우저 에서 출력   
		System.out.println("Constructor() Call!!!!!");  //System.out.println 은 톰캣에다 출력됨. 톰캣의 콘솔!!!에출력
	}

	@Override
	public void init() throws ServletException {  //생성자 및 init은 처음 생성될때 한번만
		a++;
		System.out.println("init() Call!!!!!"+a);
	}
	

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service");
		super.service(arg0, arg1);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int b =40;
		b++;
		a++;
		System.out.println("doGet() Call!!!!!"+a+" "+b); //새로고침할때마다 호출됨
	}

	@Override
	public void destroy() { //서버가 종료되는 시점 또는 서블릿 객체가 사라지는 시점에 호출됨.(밑에 프린트 안에 ! 하나 넣고 저장 누르면 서버가 리로드 되면서 호출됨)
		System.out.println("destroy() Call!!!!!!!!");
	}
}
