<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<title>Edit Kind</title>
</head>
<body>
<h1>
	Editing Kind ${kind.id} - ${kind.firstName}  ${kind.lastName} 
</h1>
<form:form commandName="kind" style="padding:8px">
    ID - ${kind.id}<br/>
    <p>
        First Name<br/>
        <form:input path="firstName"/>
    </p>
    <p>
        Last Name<br/>
        <form:input path="lastName"/>
    </p>
    <p>
        Kita<br/>
    	<form:select path="kita">
			<form:options items="${kitas}" itemValue="id" itemLabel="name" />
		</form:select>
    </p>
    
    <input type="submit" value="Save"/>
</form:form>
</body>
</html>
