package client;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import metier.CategorieService;
import metier.MetierFactory;
import metier.entitys.Categorie;

@ManagedBean
@ViewScoped
public class CategorieController implements Serializable {
    
    private CategorieService categorieSrv;
    private List<Categorie> categories;
    
    @PostConstruct
    public void init(){
        this.categorieSrv = MetierFactory.getCategorieService();
        try {
            this.categories = this.categorieSrv.getAll();
        } catch (Exception ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CategorieService getCategorieSrv() {
        return categorieSrv;
    }

    public void setCategorieSrv(CategorieService categorieSrv) {
        this.categorieSrv = categorieSrv;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }
 
}
