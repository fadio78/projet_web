/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.awbd;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Nathan Claudot
 */
@WebServlet(name = "Controleur", urlPatterns = {"/controleur"})
public class Controleur extends HttpServlet {

    @Resource(name = "jdbc/AWBD")
    private DataSource ds;
    
public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
       
        try {
            if(action.equals("Se connecter")) {
                actionConnection(request,response);
            }
            else if(action.equals("S'inscrire")){
                actionInscrire(request,response);
            }

        } catch (RuntimeException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    /**
     * Identification de l'utilisateur
     */
    private void actionConnection(HttpServletRequest request,
            HttpServletResponse response
            )
            throws IOException, ServletException {
        // accession a la base de donnee en theorie
        try {
            if (request.getParameter("login").equals("nathan") & request.getParameter("mdp").equals("claudot")) {
                HttpSession session = request.getSession();
                getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            }
            else {
                getServletContext().getRequestDispatcher("/AWBD/erreur.html").forward(request, response);
            }
        } catch (RuntimeException e /*DAOExcepion evrai*/) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);            
        }
    }

    private void actionInscrire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // acces a la base de donne, ajout dans la base de donne, verifi que personne a le meme login
        try {
            if ( request.getParameter("nom").equals("") 
                  || request.getParameter("prenom").equals("") 
                    || request.getParameter("login").equals("")
                     || request.getParameter("mdp").equals("") ) {
                // faire un bean Personne, eventuellement creer un attribut personne
                // comme sa dans la jsp on le recupere et on re rempli ce qu'il etait bon
                // puis specifier: ce champs est obligatoire la il a pas rempli
                
                getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response); }

            else {
                getServletContext().getRequestDispatcher("/index.html").forward(request, response); 
            }
           
        } catch (RuntimeException e /*DAOExcepion evrai*/) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);            
        }    
    }
    



    
    
}
