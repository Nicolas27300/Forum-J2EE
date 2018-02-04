package metier;

public class MetierFactory {
    
    private static MembreService membreSrv = null;
    private static CategorieService categorieSrv = null;
    private static ForumService forumSrv = null;
    private static TopicService topicSrv = null;
    
    public synchronized static MembreService getMembreService(){
        if (membreSrv == null){
            membreSrv = new MembreServiceImpl("ForumPU");
        }
        return MetierFactory.membreSrv;
    }
    
    public synchronized static CategorieService getCategorieService(){
        if (categorieSrv == null){
            categorieSrv = new CategorieServiceImpl("ForumPU");
        }
        return MetierFactory.categorieSrv;
    }
    
    public synchronized static  ForumService getForumService(){
        if (forumSrv == null){
            forumSrv = new ForumServiceImpl("ForumPU");
        }
        return MetierFactory.forumSrv;
    }
    
    public synchronized static TopicService getTopicService(){
        if (topicSrv == null){
            topicSrv = new TopicServiceImpl("ForumPU");
        }
        return MetierFactory.topicSrv;
    }
}
