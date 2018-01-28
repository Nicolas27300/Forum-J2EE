package client;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import metier.MembreService;
import metier.MetierFactory;

@ManagedBean
@ViewScoped
public class MembreController implements Serializable {
    
    MembreService membreSrv;
    private long count;
    
    @PostConstruct
    public void init(){
        this.membreSrv = MetierFactory.getMembreService();
        try {
            this.count = this.membreSrv.getCount();
        } catch (Exception ex) {
            Logger.getLogger(MembreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
