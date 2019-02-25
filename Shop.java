import java.util.ArrayList;
import java.io.Serializable;

class Shop implements Serializable {
public double level;  // niveau
public ArrayList<Item> inventory;// inventraire
public String name;
public static String[] SurNames = Local.SHOP_SURNAMES;

public Shop(Player p)
	{
	double levelD = p.niveau_boutique_base() * p.multiplicateur_niveau_boutique();
	level = Math.floor(levelD);
    double fPart = levelD - level;
	if(Math.random() < fPart) {level++;}
	
	int nbitem =  StaticItem.getRandomInt(p.taille_boutique());
	name = StaticItem.nameGenerator.getName() + " " + SurNames[(int)(Math.random()*SurNames.length)];
	
	double clp=p.universe.quantite_ressource_base_marchands()*p.universe.multiplicateur_res_marchands(level);
	
	if(Math.random() < p.universe.proba_clearance_sale())
	{
			clp=clp*p.clearance_sale_inventory_multiplier();
			name = name + " (" + Local.CLEARANCE_SALE + ")";
	}
	
	Item it;
	inventory = new ArrayList<Item>();
	for(int i=0; i<nbitem; i++)
		{
			it = new Item(level,p,Item.ITEM_SHOP);
			if(it.stackable)
			{
				it.qty = it.qty * clp;
				it.update_poids();
			}
		    inventory.add(it);
		}
	}

}