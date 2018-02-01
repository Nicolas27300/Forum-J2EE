package client;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import metier.ForumService;
import metier.MetierFactory;
import metier.entitys.Forum;

@ManagedBean
@ViewScoped
public class ForumController implements Serializable {
    
    ForumService forumSrv;
    Forum forum;
    
    @PostConstruct
    public void init(){
        this.forumSrv = MetierFactory.getForumService();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        long id = Long.parseLong(req.getParameter("id"));
        try {
            this.forum = this.forumSrv.getById(id);
        } catch (Exception ex) {
            Logger.getLogger(ForumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }
 
}
