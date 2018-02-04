package client;

import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import metier.MembreService;
import metier.MetierFactory;
import metier.entitys.Membre;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

    private MembreService membreSrv;
    private boolean logged = false;
    private String pseudo;
    private String password;
    private Membre membre;

    @PostConstruct
    public void init() {
        this.membreSrv = MetierFactory.getMembreService();
    }

    public void connexion() {
        try {
            List<Membre> membres = this.membreSrv.getByPseudo(this.pseudo);
            if (!membres.isEmpty()) {
                if (membres.get(0).getPassword().equals(this.encodeMd5(this.password))){
                    this.logged = true;
                    this.membre = membres.get(0);
                    ExternalContext content = FacesContext.getCurrentInstance().getExternalContext();
                    content.redirect("/Forum");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur", "Pseudo ou mot de passe incorrect"));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deconnexion() throws IOException{
        this.logged = false;
        this.pseudo = null;
        this.password = null;
        this.membre = null;
        ExternalContext content = FacesContext.getCurrentInstance().getExternalContext();
        content.redirect("/Forum");
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
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

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
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
