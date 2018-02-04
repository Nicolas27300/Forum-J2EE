package metier;

import java.io.Serializable;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import metier.entitys.Post;

public class PostServiceImpl extends AbstracCrudServiceJPA<Post> implements PostService, Serializable {
    
    PostServiceImpl(String PU){
        super(PU);
    }
    
}
