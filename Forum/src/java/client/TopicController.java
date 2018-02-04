package client;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import metier.ForumService;
import metier.MetierFactory;
import metier.TopicService;
import metier.entitys.Forum;
import metier.entitys.Topic;

@ManagedBean
@ViewScoped
public class TopicController implements Serializable {
    
    private TopicService topicSrv;
    private ForumService forumSrv;
    private LoginController login;
    private String titre;
    private Forum forum;
    
    @PostConstruct
    public void init(){
        this.topicSrv = MetierFactory.getTopicService();
        this.forumSrv = MetierFactory.getForumService();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        this.login = (LoginController) session.getAttribute("loginController");
    }
    
    public void add(){
        Topic topic = new Topic();
        topic.setTitre(this.titre);
        topic.setFotum(this.forum);
        topic.setCreateur(this.login.getMembre());
        topic.setDate_topic(new Date());
        try {
            topic = this.topicSrv.add(topic);
            List<Topic> topics = forum.getTopics();
            topics.add(topic);
            forum.setTopics(topics);
            this.forumSrv.update(forum);
        } catch (Exception ex) {
            Logger.getLogger(TopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }
       
}
