<%-- <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ page import="com.uway.sports.common.config.Global"%>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="ctxDownload" value="${pageContext.request.contextPath}/download"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="jsversion" value="?v=20191120"/>