/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author nathan
 */
public class Utilisateur {
    
    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    private String email;
    
    public Utilisateur(String n,String p,String l,String m,String e) {
        nom = n;
        prenom = p;
        login = l;
        mdp = m;
        email = e;
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }    
    
    public String getEmail() {
        return email;
    }    

    public void setLogin(String newLogin) {
        this.login = newLogin;
    }
}
