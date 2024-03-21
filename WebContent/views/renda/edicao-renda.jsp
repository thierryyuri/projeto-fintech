<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/renda/edicao-renda.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/renda/renda.css">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/global.css">
<title>Edição de renda</title>
</head>
<body>
<%@ include file="../commom/header.jsp" %>
<div class="container">
	<h1>Edição de Renda</h1>
	<c:if test="${not empty msg }">
		<div class="alert alert-success">${msg}</div>
	</c:if>
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	</c:if>
	<form action="RendaServelet" method="post">
		<input type="hidden" value="editar" name="acao">
		<input type="hidden" value="${renda.idRenda}" name="idRenda">
		<input type="hidden" value="${renda.getIdReceita()}" name="idReceita">
		<div class="form-group">
			<label for="id-nome">Nome</label>
			<input type="text" name="nome" id="id-nome" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-valor">Valor</label>
			<input type="text" name="valor" id="id-valor" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-valorTotal">Valor Total</label>
			<input type="text" name=valorTotal id="id-valorTotal" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-tipoRenda">Tipo da Renda</label>
			<input type="text" name="tipoRenda" id="id-tipoRenda" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-fonte">Fonte</label>
			<input type="text" name="fonte" id="id-fonte" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-descricao">Descrição</label>
			<input type="text" name="descricao" id="id-descricao" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-periodicidade">Periodicidade</label>
			<input type="text" name="periodicidade" id="id-periodicidade" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-dataInicio">Data Inicio</label>
			<input type="text" name="dataInicio" id="id-dataInicio" class="form-control">
		</div>
		<div class="form-group">
			<label for="id-dataFinal">Data Final</label>
			<input type="text" name="dataFinal" id="id-dataFinal" class="form-control">
		</div>
		<input type="submit" value="Salvar" class="btn btn-primary">
		<a href="RendaServelet?acao=listar" class="btn btn-danger">Cancelar</a>
	</form>
</div>
<%@ include file="../commom/footer.jsp" %>
</body>
</html>