<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
안녕하세요
<%
 //자바 소스입니다. // 이영역의 소스는 편집이 없음. -> .java(서블릿) 으로 가보면 그대로 들어가있음.
 // 이 퍼센트 태그 안 말고는 out.write("df"); 이런식으로 싹다 들어감.
	String name = "홍길동";
%>
<%-- 여기는 jsp주석으로 .java파일을 가도 주석이 없음!! 다른 주석들은 .java에 그대로 주석으로 들어감!!!!--%>  
<%! %> <!-- 얘는 멤버볌수 / 메소드 선언할때 사용 -->

</head>
<body>
hello 한글 <br>
이름 : 
<%
	out.println(name);     /* 얘는 .java(서블릿) 보면 그대로 똑같이 out.println(name); 들어가있음. */
%>
<br>
이름 : <%=  name     %>  <!-- 얘는 .java(서블릿) 보면 out.print(  name     ); 으로 들어감 -->
</body>
</html>