
import metier.CategorieService;
import metier.MetierFactory;
import metier.entitys.Categorie;

public class SimulationCategorie {

    public SimulationCategorie() throws Exception{
        CategorieService categorieSrv = MetierFactory.getCategorieService();
        Categorie categorie1 = new Categorie();
        Categorie categorie2 = new Categorie();
        Categorie categorie3 = new Categorie();
        categorie1.setName("Règlement");
        categorie2.setName("Minecraft");
        categorie3.setName("Général");
        categorieSrv.add(categorie1);
        categorieSrv.add(categorie2);
        categorieSrv.add(categorie3);
    }
     
}
