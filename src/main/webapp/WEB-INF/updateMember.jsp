<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>롤 프로게이머 삭제하기</title>
</head>

<body>
<form name="inputBoard" id="inputBoard" method="post" action="/update">
    <table border="1">
        <thead>
        <tr>
            <th>번호</th>
        </tr>
        <tr>
            <th>변경 전 이름</th>
        </tr>
        <tr>
            <th>번경 후 이름</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="text" id="id" name="id" /></td>
            <td><input type="text" id="beforename" name="beforename" /></td>
            <td><input type="text" id="aftername" name="aftername" /></td>
        </tr>
        </tbody>
    </table>
    <input type="submit" name="변경하기" value="변경하기" id="update" />
</form>
</body>

</html>