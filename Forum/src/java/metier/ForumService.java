package metier;

import java.util.List;
import lml.persistence.CrudService;
import metier.entitys.Categorie;
import metier.entitys.Forum;

public interface ForumService extends CrudService<Forum> {
    List<Forum> getByCategorie(Categorie categorie) throws Exception;
}
