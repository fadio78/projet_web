/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import modele.*;

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
        InscriptionDAO inscriptionDao = new InscriptionDAO(ds);
        String action = request.getParameter("action");
       
        try {
            if(action.equals("Se connecter")) {
                actionConnection(request,response,inscriptionDao);
            }
            else if(action.equals("S'inscrire")){
                actionInscrire(request,response,inscriptionDao);
            }

        } catch (RuntimeException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Identification de l'utilisateur
     */
    private void actionConnection(HttpServletRequest request,
                                    HttpServletResponse response, InscriptionDAO inscriptionDao
            )
            throws IOException, ServletException,DAOException {
        
            PrintWriter out = response.getWriter();
            
            try {
            if (inscriptionDao.connexion(request.getParameter("login"),request.getParameter("mdp"))==false) {
                getServletContext().getRequestDispatcher("/connection.jsp").forward(request, response);
            }
            else {
                HttpSession session = request.getSession();
                Utilisateur user = new Utilisateur(request.getParameter("nom"),request.getParameter("prenom"), 
                                                        request.getParameter("login"),request.getParameter("mdp"),request.getParameter("email"));                
                session.setAttribute("client",user);
                getServletContext().getRequestDispatcher("/").forward(request, response);
            }
            }
            catch (DAOException d) {
                out.println("probleme BD");
            }
    }

    private void actionInscrire(HttpServletRequest request, HttpServletResponse response,InscriptionDAO inscriptionDao) throws ServletException, IOException, DAOException {
            
            if ( request.getParameter("nom").equals("") 
                  || request.getParameter("prenom").equals("") 
                    || request.getParameter("login").equals("")
                     || request.getParameter("mdp").equals("") ) {

                Utilisateur user = new Utilisateur(request.getParameter("nom"),request.getParameter("prenom"), 
                                                        request.getParameter("login"),request.getParameter("mdp"),request.getParameter("email"));
                
                request.setAttribute("utilisateur",user);               
                getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response); }

            else { 
                
                Utilisateur user = new Utilisateur(request.getParameter("nom"),request.getParameter("prenom"),
                                                        request.getParameter("login"),request.getParameter("mdp"),request.getParameter("email"));
                  
                 if (inscriptionDao.verification(request.getParameter("login"))==false) {
                     
                    HttpSession session = request.getSession();
                    session.setAttribute("client",user);
                    inscriptionDao.inscription(user);
                    getServletContext().getRequestDispatcher("/index.html").forward(request, response);
                }
                               
                else {
                    user.setLogin("");
                    request.setAttribute("utilisateur",user);
                    getServletContext().getRequestDispatcher("/inscription.jsp").forward(request, response);
                }
               
            }     
    }
    



    
    
}
