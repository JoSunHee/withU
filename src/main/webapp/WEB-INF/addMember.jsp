<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>롤 프로게이머 추가하기</title>
</head>

<body>
<form name="inputBoard" id="inputBoard" method="post" action="/insert">
    <table border="1">
        <thead>
        <tr>
            <th>번호</th>
            <th>이름</th>
            <th>닉네임</th>
            <th>포지션</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="text" id="id" name="id" /></td>
            <td><input type="text" id="name" name="name" /></td>
            <td><input type="text" id="nickname" name="nickname" /></td>
            <td><input type="text" id="position" name="position" /></td>
        </tr>
        </tbody>
    </table>
    <input type="submit" name="추가하기" value="추가하기" id="insert" />
</form>
</body>

</html>