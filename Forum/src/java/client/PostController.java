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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.MetierFactory;
import metier.PostService;
import metier.TopicService;
import metier.entitys.Post;
import metier.entitys.Topic;

@ManagedBean
@ViewScoped
public class PostController implements Serializable {
    
    private TopicService topicSrv;
    private PostService postSrv;
    private LoginController login;
    private Topic topic;
    private String message;
    
    @PostConstruct
    public void init(){
        this.topicSrv = MetierFactory.getTopicService();
        this.postSrv = MetierFactory.getPostService();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        long id = Long.parseLong(req.getParameter("id"));
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        this.login = (LoginController) session.getAttribute("loginController");
        try {
            this.topic = this.topicSrv.getById(id);
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void add(){
        Post post = new Post();
        post.setCreateur(this.login.getMembre());
        post.setMessage(this.message);
        post.setPost_date(new Date());
        post.setTopic(this.topic);
        try {
            post = this.postSrv.add(post);
            List<Post> posts = this.topic.getPosts();
            posts.add(post);
            this.topic.setPosts(posts);
            this.topicSrv.update(this.topic);
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     
}
