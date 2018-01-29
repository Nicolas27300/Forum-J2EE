package metier;

import java.io.Serializable;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import metier.entitys.Categorie;

public class CategorieServiceImpl extends AbstracCrudServiceJPA<Categorie> implements CategorieService, Serializable {
    
    public CategorieServiceImpl(String PU){
        super(PU);
    }
    
}
