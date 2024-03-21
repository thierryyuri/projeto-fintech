<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/renda/lista-rendas.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/renda/renda.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/global.css">
<title>Lista de rendas</title>
</head>
<body>
<%@ include file="../commom/header.jsp" %>
<div class="container">
		<h1>Rendas</h1>
		<table class="table table-striped">
			<tr>
				<th>ID da Renda</th>
				<th>Fonte da Renda</th>
				<th>Tipo da Renda</th>
				<th>Periodicidade</th>
				<th>Valor</th>
				<th>Valor Total</th>
				<th>Data de Inicio</th>
				<th>Data Final</th>
				<th>Descrição</th>
			</tr>
			<c:forEach items="${rendas}" var="p">
				<tr>
					<td>${p.idRenda}</td>
					<td>${p.fonteRenda}</td>
					<td>${p.tipoRenda}</td>
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
						<c:url value="RendaServelet" var="link">
							<c:param name="acao" value="abrir-form-edicao"/>
							<c:param name="idRenda" value="${p.getIdRenda()}"/>
							<c:param name="idReceita" value="${p.getIdReceita()}"/>
						</c:url>
						<a href="${link}">Editar</a>
					</td>
            		<td>
						<form action="RendaServelet" method="post">
					        <input type="hidden" name="acao" value="excluir"/>
					        <input type="hidden" name="codigo" value="${p.getIdRenda()}"/>
					        <button type="submit">Excluir</button>
				   		 </form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<h2>Adicionar Rendas</h2>
		<p><a href="./views/renda/cadastro-renda.jsp">Adicionar renda</a></p>
		
		
	</div>
<%@ include file="../commom/footer.jsp" %>
</body>
</html>