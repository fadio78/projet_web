/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;
import modele.*;

/**
 *
 * @author claudotn
 */
@WebServlet(name = "InscriptionDAO", urlPatterns = {"/InscriptionDAO"})
public class InscriptionDAO extends AbstractDataBaseDAO {

    public InscriptionDAO(DataSource ds) {
        super(ds);
    }
    
    public void inscription(Utilisateur u) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "INSERT INTO utilisateu(nom,prenom,email,login,mdp) VALUES('"+u.getNom()+"','"+u.getPrenom()+"','"+u.getEmail()+"','"+u.getLogin()+"','"+u.getMdp()+"')";
            rs = st.executeQuery(requeteSQL);

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }     
    }
    
        
    public String connexion(String u) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        String s = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from utilisateu where login="+u;
            rs = st.executeQuery(requeteSQL);
            s = rs.getString("login");

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return s;
    }
    
    public boolean verification(String u) throws DAOException {
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        String s = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from utilisateu where login="+u;
            rs = st.executeQuery(requeteSQL);
            s = rs.getString("login");

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        if (s.equals("")) {
            return true;
        }
        else {
            return false;
        }
    }
    
}
