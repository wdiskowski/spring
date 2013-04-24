<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Listing Kinder</title>
</head>
<body>
<h1>Listing Kinder</h1>
<c:forEach items="${kitas}" var="v_kita">
	${v_kita.name}
	<br />
	<c:forEach items="${v_kita.kinder}" var="v_kind">
		<a style="padding-left: 10px" href="../kind/edit?id=${v_kind.id}">${v_kind.id} -
		${v_kind.firstName} ${v_kind.lastName}</a>
		<br />
	</c:forEach>
</c:forEach>
<a href="../kind/edit"> Add Kind</a>
</body>
</html>
