package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import metier.entitys.Membre;

public class MembreServiceImpl extends AbstracCrudServiceJPA<Membre> implements MembreService, Serializable {
    
    public MembreServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Membre> getByPseudo(String pseudo) throws Exception {
        List<Membre> membres = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Membre m WHERE m.pseudo :pseudo");
            query.setParameter("membre", pseudo);
            membres = query.getResultList();
        } finally {
            this.close();
        }
        return membres;
    }
    
}
