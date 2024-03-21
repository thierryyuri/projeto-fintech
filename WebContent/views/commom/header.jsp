<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/VoceAlcanca/css/header/header.css">
<title>Header</title>
</head>
<body>
	<header>
		<a href="/VoceAlcanca/pagina-principal.jsp">Home</a>
	    <a href="/VoceAlcanca/ClienteServelet?acao=listar">Cliente</a>
	    <a href="/VoceAlcanca/MetaServelet?acao=listar">Metas</a>
	    <a href="/VoceAlcanca/GastoServelet?acao=listar">Gastos</a>
	    <a href="/VoceAlcanca/RendaServelet?acao=listar">Renda</a>
	    <a href="/VoceAlcanca/InvestimentoServelet?acao=listar">Investimentos</a>
	    <a href="/VoceAlcanca/index.jsp">LogOut</a>
	</header>
</body>
</html>