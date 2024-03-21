<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/gasto/lista-gastos.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/gasto/gasto.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/global.css">
<title>Lista de gastos</title>
</head>
<body>
<%@ include file="../commom/header.jsp" %>
<div class="container">
		<h1>Gastos</h1>
		<table class="table table-striped">
			<tr>
				<th>ID do Gasto</th>
				<th>Fonte do Gasto</th>
				<th>Tipo do Gasto</th>
				<th>Periodicidade</th>
				<th>Valor</th>
				<th>Valor Total</th>
				<th>Data de Inicio</th>
				<th>Data Final</th>
				<th>Descrição</th>
			</tr>
			<c:forEach items="${gastos}" var="p">
				<tr>
					<td>${p.idGasto}</td>
					<td>${p.fonteGasto}</td>
					<td>${p.tipoGasto}</td>
					<td>${p.periodicidade}</td>
					<td>${p.getValor()}</td>
					<td>${p.getValorTotal()}</td>
					<td>
                		<fmt:formatDate value="${p.getDataInicio().time}" pattern="dd/MM/yyyy"/>
            		</td>
            		<td>
                		<fmt:formatDate value="${p.getDataFinal().time}" pattern="dd/MM/yyyy"/>
            		</td>
            		<td>${p.getDescricao()}</td>
            		<td>
						<c:url value="GastoServelet" var="link">
							<c:param name="acao" value="abrir-form-edicao"/>
							<c:param name="idGasto" value="${p.getIdGasto()}"/>
							<c:param name="idReceita" value="${p.getIdReceita()}"/>
						</c:url>
						<a href="${link}">Editar</a>
					</td>
            		<td>
						<form action="GastoServelet" method="post">
					        <input type="hidden" name="acao" value="excluir"/>
					        <input type="hidden" name="codigo" value="${p.getIdGasto()}"/>
					        <button type="submit">Excluir</button>
				   		 </form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<h2>Adicionar Gastos</h2>
		<p><a href="./views/gasto/cadastro-gasto.jsp">Adicionar Gasto</a></p>
		
		
	</div>
<%@ include file="../commom/footer.jsp" %>
</body>
</html>