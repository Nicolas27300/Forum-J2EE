package metier;

import java.io.Serializable;
import lml.persistence.jpa.AbstracCrudServiceJPA;
import metier.entitys.Topic;

public class TopicServiceImpl extends AbstracCrudServiceJPA<Topic> implements TopicService, Serializable {
    
    TopicServiceImpl(String PU){
        super(PU);
    }
    
}
