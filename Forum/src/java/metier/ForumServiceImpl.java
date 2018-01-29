package metier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import metier.entitys.Categorie;
import metier.entitys.Forum;

public class ForumServiceImpl extends AbstracCrudServiceJPA<Forum> implements ForumService, Serializable {
    
    public ForumServiceImpl(String PU){
        super(PU);
    }

    @Override
    public List<Forum> getByCategorie(Categorie categorie) throws Exception {
        List<Forum> forums = new ArrayList();
        try {
            this.open();
            Query query = em.createQuery("SELECT m FROM Forum m WHERE m.categorie = :categorie");
            query.setParameter("categorie", categorie);
            forums = query.getResultList();
        } finally {
            this.close();
        }
        return forums;
    }
    
}
