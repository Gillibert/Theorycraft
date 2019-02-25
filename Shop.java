import java.util.ArrayList;
import java.io.Serializable;

class Shop implements Serializable {
public int level;  // niveau
public ArrayList<Item> inventory;// inventraire
public String name;
public static String[] SurNames = {"l'Escroc","le Grippe-sou","le Radin","le Rapiat","l'Avare","l'Avide","le Cupide","le Rapace"};

public Shop(Player p)
	{
	double levelD = p.niveau_boutique_base() + p.niveau_boutique();
	level = (int) levelD;
    double fPart = levelD - level;
	if(Math.random() < fPart) {level++;}
	
	int nbitem =  StaticItem.getRandomInt(p.taille_boutique());
	name = StaticItem.nameGenerator.getName() + " " + SurNames[(int)(Math.random()*SurNames.length)];
	Item it;
	inventory = new ArrayList<Item>();
	for(int i=0; i<nbitem; i++)
		{
			it = new Item(level);
		    inventory.add(it);
		}
	}

}