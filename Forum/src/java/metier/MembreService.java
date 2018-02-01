package metier;

import java.util.List;
import lml.persistence.CrudService;
import metier.entitys.Membre;

public interface MembreService extends CrudService<Membre> {
    List<Membre> getByPseudo(String pseudo) throws Exception;
}
