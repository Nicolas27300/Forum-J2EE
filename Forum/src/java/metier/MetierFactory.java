package metier;

public class MetierFactory {
    
    private static MembreService membreSrv = null;
    
    public synchronized static MembreService getMembreService(){
        if (membreSrv == null){
            membreSrv = new MembreServiceImpl("ForumPU");
        }
        return MetierFactory.membreSrv;
    }
    
}
