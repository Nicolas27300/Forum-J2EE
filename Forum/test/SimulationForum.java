
import java.util.List;
import metier.CategorieService;
import metier.ForumService;
import metier.MetierFactory;
import metier.entitys.Categorie;
import metier.entitys.Forum;

public class SimulationForum {

    public SimulationForum() throws Exception {
        CategorieService categorieSrv = MetierFactory.getCategorieService();
        ForumService forumSrv = MetierFactory.getForumService();
        List<Categorie> categories = categorieSrv.getAll();
        Forum forum = new Forum();
        forum.setName("Règlement");
        forum.setCategorie(categories.get(0));
        forumSrv.add(forum);
        List<Forum> forums = forumSrv.getByCategorie(categories.get(0));
        categories.get(0).setForums(forums);
        categorieSrv.update(categories.get(0));
    }
    
}
