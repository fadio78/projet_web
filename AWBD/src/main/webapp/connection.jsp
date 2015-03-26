<%-- 
    Document   : connection
    Created on : 24 mars 2015, 21:13:24
    Author     : floyd-money
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form method="GET" action="controleur">
            Nom utilisateur : <input type="text" name="nomUtilisateur" >
            Mot de passe : <input type="text" name="mdp" >
            <input type="submit" name="action" VALUE="Se connecter">
        </form>
        
    </body>
</html>
