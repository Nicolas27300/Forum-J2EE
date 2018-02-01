package client;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import metier.MembreService;
import metier.MetierFactory;
import metier.entitys.Membre;

@ManagedBean
@ViewScoped
public class MembreController implements Serializable {
    
    MembreService membreSrv;
    private String pseudo;
    private String password;
    private String password_confirm;
    private String mail;
    
    @PostConstruct
    public void init(){
        this.membreSrv = MetierFactory.getMembreService();
    }

    
    public void add(){
        Membre membre = new Membre();
        membre.setPseudo(this.pseudo);
        membre.setPassword(this.encodeMd5(this.password));
        membre.setMail(this.mail);
        try {
            if (this.password.equals(this.password_confirm)){
                this.membreSrv.add(membre);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur", "Mot de passe non identique"));
            }
        } catch (Exception ex) {
            Logger.getLogger(MembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
     public String encodeMd5(String mdp) {
        byte[] uniqueKey = mdp.getBytes();
        byte[] hash = null;
        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuilder hashString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else {
                hashString.append(hex.substring(hex.length() - 2));
            }
        }
        return hashString.toString();
    }
    
}
