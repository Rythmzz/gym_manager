
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>hello admin</h1>


<ul>
	<c:forEach var="item" items="${menus}">
		<li>${item}</li>
	</c:forEach>
	
</ul>