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
        } catch (RuntimeException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    /**
     * Ajout d'un ouvrage.
     */
    private void actionConnection(HttpServletRequest request,
            HttpServletResponse response
            )
            throws IOException, ServletException {
        // accession a la base de donnee en theorie
        if (request.getParameter("nomUtilisateur").equals("nathan") & request.getParameter("mdp").equals("claudot")) {
            HttpSession session = request.getSession();
            getServletContext().getRequestDispatcher("/index.html").forward(request, response);
        }
        else {
            getServletContext().getRequestDispatcher("/AWBD/erreur.html").forward(request, response);
        }
    }


    
    
}
