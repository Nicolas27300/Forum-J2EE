package metier;

import java.io.Serializable;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import metier.entitys.Membre;

public class MembreServiceImpl extends AbstracCrudServiceJPA<Membre> implements MembreService, Serializable {
    
    public MembreServiceImpl(String PU){
        super(PU);
    }
    
}
