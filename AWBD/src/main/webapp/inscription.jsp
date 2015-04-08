<%-- 
    Document   : inscription
    Created on : 28 mars 2015, 22:42:24
    Author     : floyd-money
--%>

<%@page import="modele.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        
        <form method="GET" action="controleur">
            Nom : <input type="text" name="nom" value = "${utilisateur.nom}" > <br/>
            Prénom : <input type="text" name="prenom" value = "${utilisateur.prenom}" > <br/>
            E-mail* : <input type="text" name="mail" value = "${utilisateur.email}"> <br/>
            Nom utilisateur : <input type="text" name="login" value = "${utilisateur.login}"> <br/>
            Mot de passe : <input type="password" name="mdp" value = "${utilisateur.mdp}"> <br/>
    
            <input type="submit" name="action" VALUE="S'inscrire">
        </form>
        
        <p> (* champs facultatif) </p>

    </body>
</html>
