<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/meta/lista-meta.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/meta/meta.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/global.css">
<title>Lista de meta</title>
</head>
<%@ include file="../commom/header.jsp" %>
<body>
	<div class="container">
		<h1>Meta</h1>
		<table class="table table-striped">
			<tr>
				<th>Nome</th>
				<th>Valor</th>
				<th>Descrição</th>
				<th>Tipo</th>
			</tr>
			<c:forEach items="${metas}" var="p">
				<tr>
					<td>${p.nome}</td>
					<td>${p.valorMeta}</td>
					<td>${p.descricao}</td>
					<td>${p.tipoMeta}</td>
					<td>
						<c:url value="MetaServelet" var="link">
							<c:param name="acao" value="abrir-form-edicao"/>
							<c:param name="codigo" value="${p.getIdMeta()}"/>
						</c:url>
						<a href="${link}">Editar</a>
					</td>
					<td>
						<form action="MetaServelet" method="post">
					        <input type="hidden" name="acao" value="excluir"/>
					        <input type="hidden" name="codigo" value="${p.getIdMeta()}"/>
					        <button type="submit">Excluir</button>
				   		 </form>
					</td>
				</tr>
			</c:forEach>
		</table>
		<h2>Adicionar Metas</h2>
		<p><a href="./views/meta/cadastro-meta.jsp">Adicionar metas</a></p>
	</div>
		
<%@ include file="../commom/footer.jsp" %>
</body>
</html>