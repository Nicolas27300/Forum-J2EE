package client;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import metier.MembreService;
import metier.MetierFactory;
import metier.entitys.Membre;

@ManagedBean
@ViewScoped
public class MembreController implements Serializable {
    
    MembreService membreSrv;
    private String pseudo;
    private String password;
    private String mail;
    
    @PostConstruct
    public void init(){
        this.membreSrv = MetierFactory.getMembreService();
    }

    
    public void add(){
        Membre membre = new Membre();
        membre.setPseudo(this.pseudo);
        membre.setPassword(this.password);
        membre.setMail(this.mail);
        try {
            this.membreSrv.add(membre);
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
