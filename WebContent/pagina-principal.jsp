<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/home/home.css">
<link rel="stylesheet" type="text/css" href="css/global.css">
<title>Fintech One Home</title>
</head>
<body>
<%@ include file="./views/commom/header.jsp" %>

<main class="apresentacao">
        <section class="apresentacao__conteudo">
            
            <h1 class="apresentacao__conteudo__titulo">Eleve sua carteira e negócio digital a outro nível<strong 
            class="titulo-destaque"> com controles de qualidade!
            </strong>
            </h1>
            
            <p class="apresentacao__conteudo__texto">Olá! Somos a Fintech ONE, fazemos a perfeita combinação de de tecnologia e finanças,
            acesse nossos recursos e obtenha maior controle da sua carteira com facilidade e simplicidade. Comece a navegar!
            </p>
            
            <div class="apresentacao__links">
                <h2 class="apresentacao__links__subtitulo">
                    Cadastre seus recursos
                </h2>
                <a class="apresentacao__links__link" href="views/meta/cadastro-meta.jsp">
                METAS FINANCEIRAS</a>
                
                <a class="apresentacao__links__link" href="views/gasto/cadastro-gasto.jsp">    
                GASTOS</a>
                
                <a class="apresentacao__links__link" href="views/investimento/cadastro-investimento.jsp">
                    
                INVESTIMENTOS</a>
            
            </div>
        
        </section>
        <img src="assets/financas.png" alt="Foto da Joana Santos programando">
    </main>

<%@ include file="./views/commom/footer.jsp" %>

</body>
</html>