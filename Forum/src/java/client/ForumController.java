package client;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import metier.ForumService;
import metier.MetierFactory;

@ManagedBean
@ViewScoped
public class ForumController implements Serializable {
    
    ForumService forumSrv;
    
    @PostConstruct
    public void init(){
        this.forumSrv = MetierFactory.getForumService();
    }
    
}
