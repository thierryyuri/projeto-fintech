<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/investimento/lista-investimentos.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/investimento/investimento.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/global.css">
<title>Lista de investimento</title>
</head>
<body>
<%@ include file="../commom/header.jsp" %>
<div class="container">
		<h1>Investimentos</h1>
		<table class="table table-striped">
			<tr>
				<th>ID do Investimento</th>
				<th>Valor do Aporte Mensal</th>
				<th>Tipo do Investimento</th>
				<th>Quantidade de Aportes Mensais</th>
				<th>Valor</th>
				<th>Valor Total</th>
				<th>Data de Inicio</th>
				<th>Data Final</th>
				<th>Descrição</th>
			</tr>
			<c:forEach items="${investimentos}" var="p">
				<tr>
					<td>${p.idInvestimento}</td>
					<td>${p.valorAporteMensal}</td>
					<td>${p.tipoInvestimento}</td>
					<td>${p.numAportesMensais}</td>
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
						<c:url value="InvestimentoServelet" var="link">
							<c:param name="acao" value="abrir-form-edicao"/>
							<c:param name="idInvestimento" value="${p.getIdInvestimento()}"/>
							<c:param name="idReceita" value="${p.getIdReceita()}"/>
						</c:url>
						<a href="${link}">Editar</a>
					</td>
            		<td>
						<form action="InvestimentoServelet" method="post">
					        <input type="hidden" name="acao" value="excluir"/>
					        <input type="hidden" name="codigo" value="${p.getIdInvestimento()}"/>
					        <button type="submit">Excluir</button>
				   		 </form>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<h2>Adicionar Investimento</h2>
		<p><a href="./views/investimento/cadastro-investimento.jsp">Adicionar Investimento</a></p>
		
		
	</div>
<%@ include file="../commom/footer.jsp" %>
</body>
</html>