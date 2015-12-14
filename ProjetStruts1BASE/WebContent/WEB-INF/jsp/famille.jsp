<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ page import="fr.esigelec.projetStruts.dto.*,java.util.*,org.apache.commons.lang.*"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Famille</title>
	</head>
	<body>
		<h1><bean:message key="label.listeNoTaglibs"/></h1>
		<div>
			<%List<Personne> l = (List<Personne>) request.getAttribute("liste");
			for (Personne p : l) {%>
				<%=p.getId()%>-
				<%=StringEscapeUtils.escapeHtml(p.toString())%><br/>
			<%}%>
		</div>
		<div>
			<h1><bean:message key="label.listeTaglibs"/></h1>
			<logic:iterate name="liste" id="pers">
				<bean:write name="pers" property="id" /> - 
				<bean:write name="pers" property="nom" />
				<bean:write name="pers" property="prenom" /><br/>
			</logic:iterate>
		</div>
		<%--autre exemple d'affichage de l'attribut liste --%>
		${liste}
	</body>
</html>