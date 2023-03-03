<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %> --%>
<c:forEach var="info" items="${customerServices}">
								<tr>
									<td>${info.maKH}</td>
									<td>${info.tenKH}</td>
									<td>${info.diaChi}</td>
									<td></td>
									<td></td>
									<td></td>
									</tr>
								</c:forEach>