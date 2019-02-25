import java.util.*;
import java.io.Serializable;

class Item implements Serializable {
public static int ITEM_SHOP=1;
public static int ITEM_DROP=2;

public String name;
public String white_name;
public int rare; // rareté
public int pos; // position sur le corps, compris entre 0 et 14
				// (15 pour les ressources)
				
public double bonus[]; // bonus
public int ilvl; // niveau de l'objet
public int elvl; // niveau des enchantements
public double base_power = 0.0; // puissance de l'objet de base
public double quality; //qualité de l'objet
public double poids;
public boolean equiped;
public BaseItem baseItem;
public Material material;
public boolean stackable;
public double qty;

public double poids_final(Player p)
{
	if(pos <= 14) return poids*p.equipment_weight_multiplier();;
	return poids*p.resources_weight_multiplier();
}

public String item_description(Player p)
  {
  String res = name + "\n";
  res +=  String.format(Local.TYPE_VAL,StaticItem.Rarete[rare],rare);
  if (rare>0 && !stackable) res += String.format(Local.BASE_VAL,NameGenerator.firstCharLowercase(white_name));
  res += String.format(Local.EFFECTIVE_LEVEL_VAL,effectiveIlvl());
  if (!stackable) 
	{
	int nb_ench = nb_ench();
	res += String.format(Local.SLOT_VAL,NameGenerator.firstCharLowercase(StaticItem.Emplacement[pos]));
	if(Math.abs(quality)>0.0001) res += String.format(Local.QUALITY_VAL,quality*100);
	res += String.format(Local.EFFICIENCY_VAL,ilvl*material.coeffEfficacite*base_power,ilvl,material.coeffEfficacite,base_power);
	res += String.format(Local.ENCHANTMENT_VAL,elvl*material.coeffPuissance,elvl,material.coeffPuissance);
	if(nb_ench > 0) res += String.format(Local.NUMBER_OF_ENCHANTMENTS_VAL,nb_ench);
	if(nb_ench > 1) res += String.format(Local.SKILL_BONUS_VAL,nb_pts());
	}
  if (stackable)  res +=  String.format(Local.QUANTITY_VAL,qty);
  res += String.format(Local.WEIGHT_VAL,poids,poids_final(p));
  res += String.format(Local.REAL_PRICE_VAL,prix());
  res += String.format(Local.SELLING_PRICE_VAL,prix()*p.coeff_vente());
  res += String.format(Local.PURCHASE_PRICE_VAL,prix()*p.coeff_achat());

 if(rare == 4 || rare == 5)
      {
	  res+=String.format(Local.FAMILY_VAL,StaticItem.MaterialFamily[material.type]);
	  res+=String.format(Local.WEIGHT_COEFFICIENT_VAL,material.coeffPoids);
	  res+=String.format(Local.PRICE_COEFFICIENT_VAL,material.coeffPrix);
	  res+=String.format(Local.EFFICIENCY_COEFFICIENT_VAL,material.coeffEfficacite);
	  res+=String.format(Local.ENCHANTMENT_COEFFICIENT_VAL,material.coeffPuissance);
      }

  if(bonus != null)
      for (int i=0; i<Player.nb_stats; i++)
	  if (bonus(i)>0)
	      {
			if(bonus(i) >= 100)
		  		res += String.format("%s +%.0f\n",Player.stats_name[i],bonus(i));
			else
				res += String.format("%s +%g\n",Player.stats_name[i],bonus(i));
	      }
  return res;
  }

    public int effectiveIlvl() // used for the price and for the drops
    {
	return Math.max(1,(int)(ilvl*0.80*(material.coeffEfficacite+material.coeffPuissance)));
    }

    public int effectiveElvl() // used for the price 
    {
	return (int)(elvl*material.coeffPuissance);
    }

    // Create a ressource
    public Item(Material m, int MODE)
    {
	// Création de base
	elvl = 0;
	stackable = true;
	quality = 0;
	pos = StaticItem.nb_pos-1;
	material = m;
	bonus = null;

	// Création à partir du craft ou pour les ressources statiques
	if (MODE == StaticItem.RESSOURCE_ELEM) {rare = 4; ilvl=10;}
	if (MODE == StaticItem.RESSOURCE_PRIM) {rare = 5; ilvl=30;}
	if (MODE == StaticItem.RESSOURCE_ORB) {rare = 6; ilvl=50;}

	qty = 1.0;
      
	update();
    }

	public void add_qty(double a)
    {
		qty += a;
		update_poids();	
    }
	
    public void set_qty(double a)
    {
		qty = a;
		update_poids();	
    }

	public void aligner_qty(Player p, double prix)
	{
		double mul = prix/prix();
		qty = qty*mul*p.rendement();
		update_poids();	
	}

    public void change_material(Material a)
    {
	material = a;
	update();
    }


    public void update_name()
    {
	if(stackable)
	    {
		name = NameGenerator.firstCharUppercase(material.name);
		if(rare == 5)
			name = String.format(Local.ALCHEMICAL,name);
	    }
	else
	    {
		white_name = NameGenerator.firstCharUppercase(baseItem.name) + " " + material.nameOf;
		if(rare == 0)
		    name = white_name;
		else if (rare == 1)  
		    name = StaticItem.nameGenerator.getNameOf(baseItem.name);
		else if (rare == 2 || rare == 3) 
		    name = StaticItem.nameGenerator.getNameOf(StaticItem.rare_name());
	    }
    }

    public void update_poids()
    {
	if(stackable)
	    poids = qty * material.coeffPoids;
	else
	    poids = baseItem.pref_poids  * material.coeffPoids ;
    }

    double prix()
    {
	if(stackable)
	    return qty*prix_unitaire();
	else
	    return prix_unitaire();
    }

	double prix_unitaire()
    {
	if(stackable)
	    return (5 + Math.max(effectiveElvl(),effectiveIlvl()) * effectiveIlvl() * material.coeffPrix * 0.25);
	else
	    {
		double plvl = 1.0;
		for (int j=0; j<Player.nb_stats; j++)
		    plvl += bonus[j];
		return 5 + Math.max(effectiveElvl(),effectiveIlvl()) * material.coeffPrix * plvl * (1+quality) * 0.5;
	    }
    }	

    public void update()
    {
	if(stackable == false)
	    {
		int count  = nb_ench();
		if(count == 0)
		    rare = 0;	    
		else if(count <= 2)
		    rare = 1;
		else if(count <= 5)
		    rare = 2;
		else
		    rare = 3; 
	    }
	update_poids();
	update_name();
    }

static public Item get_ressouce_base(double il,Player p)
{
	double obj_lev = Math.min((double)StaticItem.max_level-1,il);
	if(Math.random() < p.universe.proba_orbe_niveau_drop(il))
	{
		return new Item(StaticItem.getRandomOrb(),StaticItem.RESSOURCE_ORB);
	}
	else
	{
	ArrayList<Item> totallist = new ArrayList<Item>();
	int totalsize = 0;
	
    int i = (int)obj_lev;
    while(totalsize < 15 && i > 0)
	{
		totalsize += StaticItem.RessourceByLevel[i].list.size();
	    totallist.addAll(StaticItem.RessourceByLevel[i].list);
		i--;
	}
	return totallist.get((int)(Math.random()*totalsize));
	}
}


public double bonus(int i)
    {
	double res = bonus[i] * material.coeffPuissance;
	if(i == baseItem.base_bonus)
	    res += (base_power * ilvl * material.coeffEfficacite);
	return res * (1.0+quality);
    }


public Item get_normal_base(double niveau)
{
	int il = (int)Math.min((double)StaticItem.max_level-1,niveau);
    ItemSet its = StaticItem.WhiteItemByLevel[il];
    int i = il;
    while(its.list.size() == 0)
	{
	    i--;
	    its = StaticItem.WhiteItemByLevel[i];
	}
    Item res = its.list.get((int)(Math.random()*its.list.size()));
	return res;
}

 



static class ComparateurMaterial implements Comparator<Material> {
	public int compare(Material s1, Material s2){
	    return s1.name.compareTo(s2.name);
	} 
} 

static class ComparateurItem implements Comparator<Item> {
	public int compare(Item s1, Item s2){
	    if (s1.rare < s2.rare) return -1;
	    if (s1.rare > s2.rare) return 1;
	    else return 0;
	} 
} 

    public static double randomInInterval(Random rg, double i, double j, double coeff)
    {
	double start = Math.min(i,j);
	double end = Math.max(i,j)*coeff;
	return rg.nextDouble()*(end-start)+start;
    }


   public static Material materialFusion(ArrayList<Material> LI, Player p)
    {
	Collections.sort(LI, new ComparateurMaterial());
	Material a = LI.get(0);
	Material b = LI.get(1);
	Material c = LI.get(2);

	int hash = (a.name + b.name + c.name + p.universe.seed).hashCode();
	
    Random rg = new Random(hash);
	String an = StaticItem.matNameGenerator.getMatName(hash);
	String n =  String.format(Local.ALLOY,an);
	String no = String.format(Local.OF_ALLOY,an);
	int ty = 5;
	double cpo, cpr, sol, eff, clvl;
	double coeff = 1.1;
	if(a.type == 5 || b.type == 5) coeff = 1.0;
	
	cpo = randomInInterval(rg,a.coeffPoids,b.coeffPoids,coeff);
	cpr = randomInInterval(rg,a.coeffPrix,b.coeffPrix,coeff);
	sol = randomInInterval(rg,a.coeffSolidite,b.coeffSolidite,coeff);
	eff = randomInInterval(rg,a.coeffEfficacite,b.coeffEfficacite,coeff);
	clvl = randomInInterval(rg,a.coeffPuissance,b.coeffPuissance,coeff);
	
	Material res = new Material(n, no, ty, cpo, cpr, sol, eff, clvl);
	return res;
	}
	
	public static String CraftDesc(Player p)
	{
	double orb_unit = p.economie_orbe();
	double rendement = p.rendement();
	
	double pipc = p.orbes_investits_en_points_competence;
	double qtypc = Math.max(1.0,pipc*0.00001);
	
	double pipd = p.orbes_investits_en_points_competence;
	double qtypd = Math.max(1.0,pipd*0.00001);
	
	double nextOrbPCGain = (p.universe.points_competence_orbes(p.orbes_investits_en_points_competence+((qtypc/orb_unit) * p.rendement()))-p.universe.points_competence_orbes(p.orbes_investits_en_points_competence))/qtypc;
	
	double nextOrbPDGain = (p.universe.points_divins_totaux(p.DIEU(),p.orbes_investits_en_points_divins+((qtypd/orb_unit) * p.rendement()),p.level)-p.universe.points_divins_totaux(p.DIEU(),p.orbes_investits_en_points_divins,p.level))/qtypd;
	
	String res = "";
	res += "<b>Un objet normal ou magique, seul</b>";
	res += String.format("<br>Recycle un objet sous forme de ressources élémentaires. Un objet de qualité peut parfois produire un orbe d'augmentation. La quantité obtenue est calculée pour qu'il y ait conservation du prix avec une perte de %g%%.",100*(1-rendement));
	
	res += "<br><br><b>Un objet rare ou légendaire, seul</b>";
	res += String.format("<br>Recycle un objet sous forme d'orbes. La quantité obtenue est calculée pour qu'il y ait conservation du prix avec une perte de %g%%.",100*(1-rendement));
	
	res += "<br><br><b>Une ressource élémentaire ou primordiale (quantité quelconque)</b>";
	res += String.format("<br>Produit une nouvelle ressource élémentaire de niveau inférieur à celle fournie. La quantité obtenue est calculée pour qu'il y ait conservation du prix avec une perte de %g%%.",100*(1-rendement));
	
	res += "<br><br><b>Une quantité quelconque d'orbe d'augmentation</b>";
	res += String.format("<br>Donne de l'expérience au joueur proportionnellement au nombre d'orbes sacrifiés. La quantité produite dépend des compétences <i>Rendement du craft</i> et <i>Économie d'orbes</i>. Un orbe donne %g points d'expérience.",p.universe.experience_orbe()*rendement/orb_unit);
	
	res += "<br><br><b>Une quantité quelconque d'orbe d'évolution</b>";
	res += String.format("<br>Crée un nombre de points divins de plus en plus petit en fonction du nombre de points divins déjà créés. La quantité produite dépend aussi des compétences <i>Divinité</i>, <i>Rendement du craft</i> et <i>Économie d'orbes</i>. Le prochain orbe créera %g points divins.",nextOrbPDGain);
	
	res += "<br><br><b>Une quantité quelconque d'orbe de fusion</b>";
	res += String.format("<br>Crée un nombre de points de compétences de plus en plus petit en fonction du nombre de points compétences déjà créés. La quantité produite dépend aussi des compétences <i>Rendement du craft</i> et <i>Économie d'orbes</i>. Le prochain orbe créera %g points de compétences.",nextOrbPCGain);
	
	res += String.format("<br><br><b>%g orbe de transfert ou orbe de transmutation</b>",orb_unit);
	res += "<br>Permet de redistribuer tous les points de compétence.";
	
	res += "<br><br><b>Une quantité quelconque d'orbes de deux types</b>";
	res += String.format("<br>Produit un nouvel orbe aléatoire. La quantité obtenue est calculée pour qu'il y ait conservation du prix avec une perte de %g%%.",100*(1-rendement));
	
	res += String.format("<br><br><b>Chaleur alchimique (quantité quelconque) et %g orbe d'évolution</b>",orb_unit);
	res += String.format("<br>Augmente la température de la zone actuelle. L'augmentation dépend de la température actuelle, du mombre de ressources sacrifiées et de la compétence <i>Rendement du craft</i>. La prochaine ressource sacrifiée augmentera la température de %g°.",p.universe.get_adjusted_temperature(p.zone,p.universe.map.current_temperature_modifier.get(p.zone)+p.rendement()*0.1)-p.universe.get_current_temperature(p.zone));
	
	res += String.format("<br><br><b>Froid alchimique (quantité quelconque) et %g orbe d'évolution</b>",orb_unit);
	res += String.format("<br>Diminue la température de la zone actuelle. La diminution dépend de la température actuelle, du mombre de ressources sacrifiées et de la compétence <i>Rendement du craft</i>. La prochaine ressource sacrifiée diminuera la température de %g°.",p.universe.get_current_temperature(p.zone)-p.universe.get_adjusted_temperature(p.zone,p.universe.map.current_temperature_modifier.get(p.zone)-p.rendement()*0.1));
	
	
	res += String.format("<br><br><b>Humidité alchimique (quantité quelconque) et %g orbe d'évolution</b>",orb_unit);
	res += String.format("<br>Augmente le niveau de précipitation de la zone actuelle. L'augmentation dépend du niveau de précipitation actuel, du mombre de ressources sacrifiées et de la compétence <i>Rendement du craft</i>. La prochaine ressource sacrifiée augmentera le niveau de précipitation de %g%%.",100*(p.universe.get_adjusted_precipitation(p.zone,p.universe.map.current_precipitation_modifier.get(p.zone)+p.rendement()*0.1)-p.universe.get_current_precipitation(p.zone)));
	
	res += String.format("<br><br><b>Sécheresse alchimique (quantité quelconque) et %g orbe d'évolution</b>",orb_unit);
	res += String.format("<br>Diminue le niveau de précipitation de la zone actuelle. La diminution dépend du niveau de précipitation actuel, du mombre de ressources sacrifiées et de la compétence <i>Rendement du craft</i>. La prochaine ressource sacrifiée diminuera le niveau de précipitation de %g%%.",100*(p.universe.get_current_precipitation(p.zone)-p.universe.get_adjusted_precipitation(p.zone,p.universe.map.current_precipitation_modifier.get(p.zone)-p.rendement()*0.1)));
	
	res += String.format("<br><br><b>Une ressource (quantité quelconque) et %g orbe de transmutation</b>",orb_unit);
	res += String.format("<br>Produit une nouvelle ressource élémentaire ou primordiale suivant le type de la ressource de départ. La quantité obtenue est calculée pour qu'il y ait conservation du prix avec une perte de %g%%.",100*(1-rendement));
	
	res += String.format("<br><br><b>Un objet normal, magique, rare ou légendaire et %g orbe de transmutation</b>",orb_unit);
	res += "<br>Tire de nouvelles valeurs aléatoires pour les enchantements en fonction du niveau d'enchantement l'objet.";
	
	res += String.format("<br><br><b>Une ressource (quantité quelconque) et %g orbe d'augmentation</b>",orb_unit);
	res += String.format("<br>Augmente la quantité de ressource. Il y a conservation du prix avec une perte de %g%%.",100*(1-rendement));
	
	res += String.format("<br><br><b>Un objet normal, magique, rare ou légendaire et une quantité quelconque d'orbe d'augmentation</b>",orb_unit);
	res += String.format("<br>Augmente la qualité de l'objet de %g%% par orbe d'augmentation dans la limite des %g%%.",100*0.05*p.rendement()/orb_unit,100*p.universe.qualite_max()*p.rendement());
	
	res += String.format("<br><br><b>Une ressource élémentaire (quantité quelconque) et %g orbe de transfert ou d'évolution</b>",orb_unit);
	res += String.format("<br>Transforme la ressource en ressource primordiale. La quantité obtenue est calculée pour qu'il y ait conservation du prix avec une perte de %g%%.",100*(1-rendement));
	
	res += String.format("<br><br><b>Un objet normal, magique ou rare et %g orbe d'évolution</b>",orb_unit);
	res += "<br>Ajoute un enchantement à l'objet, dans la limite des 5 enchantements.";
	
	res += String.format("<br><br><b>Un objet magique, rare ou légendaire et %g orbe de transfert</b>",orb_unit);
	res += "<br>Change la cible des enchantements de l'objet sans changer leur puissance.";
	
	res += String.format("<br><br><b>Deux objets normaux, magiques ou rares</b>");
	res += String.format("<br>Tire un nouvel objet aléatoire. Le niveau des enchantements est le minimum des niveaux des enchantements des objets placés. Le matériau est tiré au hasard parmi les matériaux des deux objets placés. La base de l'objet est tirée au hasard parmi les bases des deux objets placés. Enfin le type est le minimum des types des objets placés, donc il faut deux objets rares pour obtenir un objet rare. Si la famille de matériau ne colle pas à la base de l'objet, il reçoit une pénalité d'efficacité comprise entre %g%% et %g%%.",
	100 * p.penalty_for_bad_material()*(1.0 - 0.5 * p.universe.static_plage_random()),
	100 * p.penalty_for_bad_material()*(1.0 + 0.5 * p.universe.static_plage_random()));

	res += String.format("<br><br><b>Un objet normal, magique, rare ou légendaire et %g ressource primordiale</b>",orb_unit);
	res += String.format("<br>Change la nature du matériau de l'objet en fonction de la ressource fournie. Si la famille de matériau n'est pas la bonne, l'objet reçoit une pénalité d'efficacité comprise entre %g%% et %g%%.",
	100 * p.penalty_for_bad_material()*(1.0 - 0.5 * p.universe.static_plage_random()),
	100 * p.penalty_for_bad_material()*(1.0 + 0.5 * p.universe.static_plage_random()));

	res += "<br><br><b>Trois matériaux différents</b>";
	res += String.format("<br>Crée un alliage a partir des propriétés de deux des trois matériaux, le troisième servant de catalyseur. Un alliage peut avoir un coefficient de poids, de prix, d'efficacité ou magique jusqu'à 10%% supérieur au meilleur des deux matériaux pour le coefficient considéré. La même combinaison de matériaux donnera toujours le même alliage dans un univers donné. La quantité d'alliage est calculée pour qu'il y ait conservation du prix avec une perte de %g%%.",100*(1-rendement));

	res += String.format("<br><br><b>Deux objets magiques, rares ou légendaires et %g orbe de transmutation</b>",orb_unit);
	res += "<br>Transfert partiel des enchantements entre les deux objets : 20% des enchantements échangent leur place.";

	res += String.format("<br><br><b>Deux objets magiques, rares ou légendaires et %g orbe de transfert</b>",orb_unit);
	res += "<br>Transfert des enchantements entre les deux objets : 100% des enchantements échangent leur place.";

	res += String.format("<br><br><b>Deux objets magiques, rares ou légendaires et %g orbe de fusion</b>",orb_unit);
	res += String.format("<br>Fusion de deux objets. Un objet est détruit et %g%% de ses enchantements sont transférés vers l'autre.",50*rendement);

	res += String.format("<br><br><b>Deux objets magiques, rares ou légendaires et %g orbe d'évolution</b>",orb_unit);
	res += String.format("<br>Un des deux objets est choisi au hasard comme source, l'autre comme cible. L'efficacité de la cible est alignée sur %g%% de l'efficacité de la source.",100*rendement);

	res += String.format("<br><br><b>Deux objets magiques, rares ou légendaires et %g orbe d'augmentation</b>",orb_unit);
	res += String.format("<br>Un des deux objets est choisi au hasard comme source, l'autre comme cible. Le niveau d'enchantement de la cible est alignée sur %g%% du niveau d'enchantement de la source.",100*rendement);
	
	res += String.format("<br><br><b>%g orbes de chaque type</b>",p.universe.travel_cost()*orb_unit);
	res += "<br>Permet au joueur de changer d'univers.";
	
	res += String.format("<br><br><b>N objets magiques, rares ou légendaires et N×%g orbes de fusion</b>",orb_unit);
	res += String.format("<br>Fusionne tous les objets pour en créer un nouveau. Chaque enchantement a la même probabilité d'être conservé. Après fusion on a en moyenne (%g×E)<sup>0.584</sup> enchantements, où E est le nombre cumulé d'enchantement des objets fusionnés. Si deux enchantements de même cible sont conservés par le tirage aléatoire, c'est le plus puissant des deux qui est retenu.",rendement);
	return res;
	}

	public static double CraftItem(ArrayList<Item> LI, ArrayList<Item> rlist, Player p)
	{
		Collections.sort(LI, new ComparateurItem());
		
		double orb_unit = p.economie_orbe();
		int lisize = LI.size();
		double time = lisize*p.temps_craft()*p.universe.plage_random();
		
		if (lisize == 1) 
		{ 
			Game.MW.addLog(String.format("Recyclage de %g %s (%g secondes)",LI.get(0).qty,LI.get(0).name,time));
		}
		else
		{
			String rstring;
			if (lisize > 5) rstring = String.format("Combinaison de %d objets",lisize);
			else 
			{
				rstring = "Combinaison de : ";
				for(int i=0; i< lisize; i++)
				{
				rstring += LI.get(i).name;
				if (i< LI.size()-1) rstring+= ", ";
				}
			}
			Game.MW.addLog(String.format("%s (%g secondes)",rstring,time));
		}
		
		// ------------------------------------------------------------------
		// Recyclage (1 objet)
		if(lisize == 1)
		{
			Item a = LI.get(0);
			Item tmp;
			if (a.rare == 0 || a.rare == 1)
			{
				// recyclage d'un objet normal ou d'un objet magique 
				// (donne des ressources élémentaires ou un orbe d'augmentation dans le cas d'un objet de qualité)
				if(Math.random() < a.quality)
				{
					tmp = new Item(StaticItem.ORB[1],StaticItem.RESSOURCE_ORB); // "orbe d'augmentation"
					tmp.aligner_qty(p, a.prix());
					rlist.add(tmp);
				}
				else
				{
					tmp = new Item(a.material,StaticItem.RESSOURCE_ELEM);
					tmp.aligner_qty(p, a.prix());
					rlist.add(tmp);
				}
			}
			else if (a.rare == 2 || a.rare == 3)
			{
				// recyclage d'un rare ou d'un légendaire 
				// (donne des orbes)
				tmp = new Item(StaticItem.getRandomOrb(),StaticItem.RESSOURCE_ORB);
				tmp.aligner_qty(p, a.prix());
				rlist.add(tmp);
			}
			else if (a.rare == 4 || a.rare == 5)
			{
				// recyclage d'une ressource élémentaire ou d'une ressource primordiale 
				// (donne des ressources)
				tmp = new Item(get_ressouce_base(a.effectiveIlvl(),p));
				tmp.aligner_qty(p, a.prix());
				rlist.add(tmp);
			}
			else if (a.rare == 6)
			{
				// recyclage d'un orbe
				
				if (a.name.equals("Orbe d'augmentation"))
				{
					double xp = (a.qty/orb_unit) * p.universe.experience_orbe() * p.rendement();
					p.gain_xp(xp, TimeStats.XP_ORB, p.level);
					a.set_qty(0);
				}
				
				if (a.name.equals("Orbe d'évolution"))	
				{
					p.orbes_investits_en_points_divins += (a.qty/orb_unit) * p.rendement();
					a.set_qty(0);
				}

				if (a.name.equals("Orbe de fusion"))	
				{
					p.orbes_investits_en_points_competence += (a.qty/orb_unit) * p.rendement();
					a.set_qty(0);
				}
				
				else if (a.qty >= orb_unit && (a.name.equals("Orbe de transfert") || a.name.equals("Orbe de transmutation")))
				{
					p.reset_build();
					a.set_qty(a.qty-orb_unit);
				}
				if(a.qty>0.001) rlist.add(a);
			}
			else
			{
				rlist.add(a);
			}
		}
		// ------------------------------------------------------------------
		// Deux trucs
		else if(lisize == 2)
		{
			Item a = LI.get(0);
			Item b = LI.get(1);

			Item res=null;
			int rarity = Math.min(a.rare,b.rare);
			int elv = Math.min(a.elvl,b.elvl);

			Material mat;
			if(Math.random()<0.5) mat = a.material;
			else mat = b.material;

			BaseItem ba; 
			if(Math.random()<0.5) ba = a.baseItem;
			else ba = b.baseItem;

			// deux orbes
			// nouvel orbe au hazard (conservation du prix)
			if(a.rare == 6 && b.rare == 6)
			{
				res = new Item(StaticItem.getRandomOrb(),StaticItem.RESSOURCE_ORB);
				res.aligner_qty(p, a.prix()+b.prix());
				rlist.add(res);
			}
			// orbe d'augmentation (quantité quelconque) + objet
			else if ((a.name.equals("Orbe d'augmentation") && b.rare < 4) || (b.name.equals("Orbe d'augmentation") && a.rare < 4))
			{
				Item comp, other;	
				if (a.rare == 6) {comp = a; other = b;}
				else {comp = b; other = a;}
				res = other;
				double added_quality = Math.min(comp.qty*0.05*p.rendement()/orb_unit,p.universe.qualite_max()*p.rendement()-res.quality);
				if (added_quality<0.0) added_quality=0.0;
				res.quality += added_quality;
				comp.set_qty(comp.qty-added_quality/(0.05*p.rendement()/orb_unit));
				rlist.add(res);
				if(comp.qty>0.001) rlist.add(comp);			  
			}
			else if ((a.rare == 6 && a.qty >= orb_unit) || (b.rare == 6 && b.qty >= orb_unit)) // au moins un orbe
			{
				Item comp, other;	
				if (a.rare == 6) {comp = a; other = b;}
				else {comp = b; other = a;}

				// orbe de transmutation + ressource quelconque
				// ressource aléatoire (conservation du prix)
				if((other.rare == 4 || other.rare == 5) && comp.name.equals("Orbe de transmutation")) 
				{
					mat = StaticItem.getRandomMat(); // tirer un mat non-orbe
					if(other.rare == 4) res = new Item(mat,StaticItem.RESSOURCE_ELEM);
					if(other.rare == 5) res = new Item(mat,StaticItem.RESSOURCE_PRIM);
					comp.set_qty(comp.qty-orb_unit);
					res.aligner_qty(p, other.prix()+comp.prix_unitaire()*orb_unit);
					rlist.add(res);
					if(comp.qty>0.001) rlist.add(comp);			  
				}
				// orbe de transmutation + objet magique, rare ou légendaire
				// tire de nouvelles valeurs aléatoires pour les enchantements.
				else if(other.rare > 0 && other.rare < 4 && comp.name.equals("Orbe de transmutation")) 
				{
					res = other;
					for(int i=0; i<Player.nb_stats; i++)
						if(res.bonus[i]>0) 
						res.bonus[i] = power(p,res.elvl);
					comp.set_qty(comp.qty-orb_unit);
					res.update();
					rlist.add(res);
					if(comp.qty>0.001) rlist.add(comp);			  
				}
				// orbe d'augmentation + ressource quelconque
				// augmente la quantité de ressource (conservation du prix)
				else if((other.rare == 4 || other.rare == 5) && comp.name.equals("Orbe d'augmentation")) 
				{
					res = other;
					res.aligner_qty(p, other.prix()+comp.prix_unitaire()*orb_unit);
					comp.set_qty(comp.qty-orb_unit);
					rlist.add(res);
					if(comp.qty>0.001) rlist.add(comp);			  
				}
				// orbe d'évolution (ou de transfert) + ressource élémentaire
				// transforme la ressource en ressource primordiale (conservation du prix)
				else if(other.rare == 4 && 
					(comp.name.equals("Orbe d'évolution") || comp.name.equals("Orbe de transfert"))) 
				{
					res =new Item(other.material,StaticItem.RESSOURCE_PRIM);
					comp.set_qty(comp.qty-orb_unit);
					res.aligner_qty(p, other.prix()+comp.prix_unitaire()*orb_unit);
					rlist.add(res);
					if(comp.qty>0.001) rlist.add(comp);	  
				}
				// orbe d'évolution + ressource primordiale (chaud ou froid)
				// change la température de la zone
				else if(other.rare == 5 && comp.name.equals("Orbe d'évolution") && 
				(other.name.equals("Chaleur alchimique") || other.name.equals("Froid alchimique")))
				{
					double mdiff = other.qty*p.rendement()*0.1;
					if(other.name.equals("Froid alchimique")) mdiff=-mdiff;
					
					p.universe.map.current_temperature_modifier.set(p.zone,p.universe.map.current_temperature_modifier.get(p.zone)+mdiff);
					p.refresh_weather_penalties();
					comp.set_qty(comp.qty-orb_unit);
					if(comp.qty>0.001) rlist.add(comp);	  
				}
				// orbe d'évolution + ressource primordiale (humidité ou sécheresse)
				// change le niveau de précipitation de la zone
				else if(other.rare == 5 && comp.name.equals("Orbe d'évolution") && 
				(other.name.equals("Humidité alchimique") || other.name.equals("Sécheresse alchimique")))
				{
					double mdiff = other.qty*p.rendement()*0.1;
					if(other.name.equals("Sécheresse alchimique")) mdiff=-mdiff;
					
					p.universe.map.current_precipitation_modifier.set(p.zone,p.universe.map.current_precipitation_modifier.get(p.zone)+mdiff);
					p.refresh_weather_penalties();
					comp.set_qty(comp.qty-orb_unit);
					if(comp.qty>0.001) rlist.add(comp);	  
				}
				// orbe d'évolution + objet normal, magique ou rare
				// ajoute un enchantement à l'objet (dans la limite des 5 enchantements)
				else if(other.rare < 3 && comp.name.equals("Orbe d'évolution")) 
				{
					res = other;
					res.add_ench(p);
					comp.set_qty(comp.qty-orb_unit);
					rlist.add(res);
					if(comp.qty>0.001) rlist.add(comp);			  
				}
				// orbe de transfert + objet magique, rare ou légendaire
				// change la cible des enchantements sans changer leur puissance
				else if(other.rare > 0 && other.rare < 4 && comp.name.equals("Orbe de transfert")) 
				{
					res = new Item(other);
					for(int i=0; i<Player.nb_stats; i++)
						res.bonus[i] = 0.0;
					for(int i=0; i<Player.nb_stats; i++)
						if(other.bonus[i]>0)
						res.add_ench(other.bonus[i]);
					comp.set_qty(comp.qty-orb_unit);
					rlist.add(res);
					if(comp.qty>0.001) rlist.add(comp);			  
				}
				else
				{
					rlist.add(a);
					rlist.add(b);
				}
			}
			// deux objets normaux, magiques ou rares
			// un objet aléatoire
			else if(a.rare < 3 && b.rare < 3)
			{
				res = new Item(ba, mat, p.universe);
				res.elvl = elv;
				if (rarity == 1)
					res.transform_magic(p);
				else if (rarity == 2)
					res.transform_rare(p);

				if ( res.material.type != res.baseItem.mat)
					res.quality = p.penalty_for_bad_material() * p.universe.plage_random();
			
				res.update();
				rlist.add(res);
			}
			// une ressource primordiale et un objet
			// change la nature du matérieau de l'objet
			else if(a.rare < 4 &&  b.rare == 5 && b.qty >= orb_unit)
			{
				res = a;
				res.material = b.material;
				if ( res.material.type != res.baseItem.mat)
					res.quality = p.penalty_for_bad_material() * p.universe.plage_random();
				res.update();

				b.set_qty(b.qty-orb_unit);
				rlist.add(res);
				if(b.qty>0.001) rlist.add(b);
			}
			else 
			{
				rlist.add(a);
				rlist.add(b);
			}
		}
		else if(lisize == 3)
		{
			Item a = LI.get(0);
			Item b = LI.get(1);
			Item c = LI.get(2);
			double tmp;

			// Trois ressources
			// Crée un alliage a partir des propriétés de deux des trois matériaux, le troisième servant de catalyseur
			if(a.rare >= 4 && c.rare <= 5)
			{
				ArrayList<Material> mats = new ArrayList<Material>();
				mats.add(a.material); mats.add(b.material); mats.add(c.material);
				Item rit;
				if(a.rare == 5) // les trois resources sont primordiales
				{
					rit = new Item(materialFusion(mats,p),StaticItem.RESSOURCE_PRIM);
				}
				else
				{
					rit = new Item(materialFusion(mats,p),StaticItem.RESSOURCE_ELEM);
				}
				double prix = a.prix()+b.prix()+c.prix();
				rit.aligner_qty(p, prix); 
				rlist.add(rit);
			}
			
			// Deux objets et un orbe
			else if(a.rare > 0 && b.rare < 4 && c.rare == 6 && c.qty >= orb_unit)
			{
				boolean delA=false;
				boolean delB=false;
				// 20% des enchantements échangent leur place
				// (en fait, chaque enchantement a une probabilité P d'être transféré avec P=0.2)
				if(c.name.equals("Orbe de transmutation"))
				{
					Game.MW.addLog("Transmutation de deux objets (20% des enchantements échangent leur place)");
					for(int i=0; i<Player.nb_stats; i++)
						if(a.bonus[i]>0 || b.bonus[i]>0) 
						if(Math.random() < 0.2)
					{
						tmp = a.bonus[i];
						a.bonus[i] = b.bonus[i];
						b.bonus[i] = tmp;
					}
				}
				// 100% des enchantements échangent leur place
				if(c.name.equals("Orbe de transfert"))
				{
					Game.MW.addLog("Transfert de deux objets (100% des enchantements échangent leur place)");
					for(int i=0; i<Player.nb_stats; i++)
						if(a.bonus[i]>0 || b.bonus[i]>0) 
					{
						tmp = a.bonus[i];
						a.bonus[i] = b.bonus[i];
						b.bonus[i] = tmp;
					}
				}
				// Fusion de deux objets :
				// Un objet est détruit, 50% des enchantements sont transférés vers l'autre.
				// (en fait, chaque enchantement a une probabilité P d'être transféré avec P=0.5*R)
				if(c.name.equals("Orbe de fusion"))
				{
					double seuil = 0.5*p.rendement();
					Game.MW.addLog(String.format("Fusion de deux objets (un objet est détruit, %g%% des enchantements sont transférés vers l'autre)", 100*seuil));
					Item winner, loser;
					if(Math.random() < 0.5)
						{winner = a; loser = b; delB=true;}
					else 
						{winner = b; loser = a; delA=true;}
					for(int i=0; i<Player.nb_stats; i++)
					{
						if(loser.bonus[i] > winner.bonus[i] && Math.random() < seuil) 
							winner.bonus[i] = loser.bonus[i];
					}
				}
				// Aligne les ilvl (en tenant compte du rendement).
				if(c.name.equals("Orbe d'évolution"))
				{
					Game.MW.addLog("Évolution de deux objets (aligne le niveau d'efficacité des objets)");
					if(Math.random() < 0.5)
					{
						b.ilvl = (int)(p.rendement()*a.ilvl);
						b.base_power = a.base_power;
					}
					else
					{
						a.ilvl = (int)(p.rendement()*b.ilvl);
						a.base_power = b.base_power;
					}
				}
				// Aligne les elvl (en tenant compte du rendement).
				if(c.name.equals("Orbe d'augmentation"))
				{
					Game.MW.addLog("Augmentation de deux objets (aligne le niveau d'enchantement des objets)");
					if(Math.random() < 0.5)
						b.elvl = (int)(p.rendement()*a.elvl);
					else 
						a.elvl = (int)(p.rendement()*b.elvl);
				}
				
				if(!delA)
				{a.update(); rlist.add(a);}
			
				if(!delB)
				{b.update(); rlist.add(b);}
			
				c.set_qty(c.qty-orb_unit);
				if(c.qty>0.001) rlist.add(c);
			}
			else
			{
				rlist.add(a);
				rlist.add(b);
				rlist.add(c);
			}
		}
		// 5 orbes, tous différents
		if(lisize == 5)
		{
			if(LI.get(0).rare == 6 && LI.get(4).rare == 6)
			{
				double orb_cost = p.universe.travel_cost();
				boolean allOk = true;
				for (int idx = 0; idx < lisize; idx++)
				{
				if(LI.get(idx).qty < orb_unit*orb_cost)
					{
					allOk = false;
					break;
					}
				}
				if(allOk)
				{
					Game.MW.addLog(String.format("Changement d'univers, coûte %g orbes (%g × %g)",orb_unit*orb_cost*5,orb_unit,orb_cost*5));
					for (int idx = 0; idx < lisize; idx++)
					{
					LI.get(idx).set_qty(LI.get(idx).qty-orb_unit*orb_cost);
					}
					p.changer_univers();
				}
			}
			rlist.addAll(LI);
		}
		// Fusion de N objets avec N orbes de fusion
		else if(lisize > 3)
		{
			Item last = LI.get(lisize - 1);
			boolean allOk = true;
			int totalNbEnch = 0;
			for (int idx = 0; idx < lisize-1; idx++)
			{
				Item theItem = LI.get(idx);
				if(theItem.rare < 1 || theItem.rare > 3)
				{
					allOk = false;
					break;
				}
				totalNbEnch += theItem.nb_ench();
			}
			double orbcost = (lisize-1)*orb_unit;
			if (allOk && last.name.equals("Orbe de fusion") &&  last.qty >= orbcost)
			{
				double seuil = Math.pow(totalNbEnch*p.rendement(),0.584) / totalNbEnch;
				int resIdx =  (int)(Math.random()*(lisize-1));
				Item winner = LI.get(resIdx);
				
				for (int idx = 0; idx < lisize-1; idx++)
				{
					if (idx == resIdx) {continue;}
					Item loser = LI.get(idx);
					for(int i=0; i<Player.nb_stats; i++)
						if(loser.bonus[i] > winner.bonus[i] && Math.random() < seuil)
						{
							winner.bonus[i] = loser.bonus[i];
						}
				}
				winner.update();
				rlist.add(winner);
				last.set_qty(last.qty-orbcost);
				if(last.qty>0.001) rlist.add(last);
			}
			else
			{
				rlist.addAll(LI);
			}
		}
		return time;
	}

  
    public Item(double niveau, Player p, int iType)
    {
	double drop_lvl = Math.max(1,niveau*0.7+Math.random()*niveau*0.3);
	
	// sert à remplir l'inventaire d'un marchand
    if(iType==ITEM_SHOP)
    {
	if(Math.random()  < p.universe.proba_ressource()) // ressource
	    {
		this.copy(get_ressouce_base(drop_lvl,p));
		qty = p.universe.plage_random();
		update_poids();
	    }
	else
	    {
		this.copy(get_normal_base(drop_lvl));
		base_power = p.universe.efficacite_base();
	    }
    }
	
	// Drop sur un monstre
	else if(iType==ITEM_DROP)
	{
	if(Math.random() < p.universe.proba_ressource()) // ressource
	    {
		this.copy(get_ressouce_base(drop_lvl,p));
		qty = p.universe.quantite_ressource_base_drops()*p.multiplicateur_res()*p.universe.plage_random();
		update_poids();
	    }
	else // objet de base ou magique ou rare
	    {
		this.copy(get_normal_base(drop_lvl));
		base_power = p.universe.efficacite_base();
		if (Math.random() < p.chance_magique())
		    {
			elvl = (int)Math.min((double)StaticItem.max_level-1,niveau);
			if (Math.random() <= p.chance_rare())
			    transform_rare(p);	      
			else
			    transform_magic(p);
		    }
		if (Math.random()< p.chance_qualite())
		    quality = Math.random()*p.universe.qualite_max();
		update();
	    }
    }
}

public void transform_rare(Player p)
  {
      rare = 2;
      int ench, count;
      double r = Math.random();

      if (r < 0.10)
	  count = 5;
      else if (r < 0.40)
	  count = 4;
      else
	  count = 3;

      int a=0;
      while (a<count)
	  {
	      ench = (int)(Math.random()*Player.nb_stats);
	      if(bonus[ench]==0) 
		  {
		      bonus[ench] += power(p,elvl);
		      a++;
		  }
	  }
  }

    public void transform_magic(Player p)
    {
	// 40% de chance d'améliorer la stat principale de l'objet (compte comme un enchantement)
	// 40% de chance de chopper 2 enchantements
	rare = 1;
	int ench;
	double count = 1.0;
	if (Math.random()<0.40)
	    {
		bonus[baseItem.base_bonus] += power(p, elvl);
		count -=0.60;
	    }
	while (Math.random()<count)
	    {
		ench = (int)(Math.random()*Player.nb_stats);
		if(bonus[ench]==0) 
		    {
			bonus[ench] += power(p,elvl);
			count -=0.60;
		    }
	    }
    }
	
	public double nb_pts()
	{
	double count = 0;
	for(int i=0; i<Player.nb_stats; i++)
	    {
		if(bonus(i)>0)
		    count += bonus(i);
	    }
	return count;
	}
	
	private int nb_ench()
	{
	int count = 0;
	for(int i=0; i<Player.nb_stats; i++)
	    {
		if(bonus[i]>0)
		    count++;
	    }
	return count;
	}
	
    public void add_ench(double pow)
    {
	int ench;
	if (nb_ench() < 5)
	    {
		while(true)
		    {
			ench = (int)(Math.random()*Player.nb_stats);
			if(bonus[ench]==0) 
			    {
				bonus[ench] = pow;
				break;
			    }
		    }
		update();
	    }
    }

    public void add_ench(Player p)
    {
		elvl = Math.max(ilvl,elvl);
		add_ench(power(p,elvl));
    }

    // Crée les items statiques à partir des BaseItem
public Item(BaseItem ba, Material mat, Universe universe)
    {
	stackable = false;
	material = mat;
	baseItem = ba;
	equiped = false;
	rare = 0;
	elvl = 0;
	ilvl = ba.pref_level;
	pos = ba.pos;
	base_power = universe.efficacite_base();
	quality = 0;
	bonus = new double[Player.nb_stats];
	for (int i=0; i<Player.nb_stats; i++)
	    bonus[i]=0;
	update();
    }

public Item()
{
	bonus = new double[Player.nb_stats];
	for (int i=0; i<Player.nb_stats; i++)
	    bonus[i]=0;
}

public Item(Item i)
{
	this.copy(i);
}
 
 public void copy(Item i)
    {
	stackable = i.stackable;
	qty = i.qty;
	name = i.name;
	white_name = i.white_name;
	rare = i.rare;
	pos = i.pos;
	ilvl = i.ilvl;
	elvl = i.elvl;
	base_power = i.base_power;
	quality = i.quality;
	poids = i.poids;
	equiped = i.equiped;
	baseItem = i.baseItem;
	material = i.material;
	if(i.bonus != null)
	    {
	    	bonus = new double[Player.nb_stats];
		for (int j=0; j<Player.nb_stats; j++)
		    bonus[j]=i.bonus[j];
	    }
	else 
	    bonus = null;
    }

    // Puissance d'un enchantement en fonction du niveau de l'item
    public static double power(Player p,int niveau)
    {
	double min = p.puissance_ench_inf();
	double max = p.puissance_ench_sup();
	double gap = (max-min);
	return (min+(Math.random()*gap))*niveau;
    }

    public boolean can_be_equiped()
    {
	return !equiped && !stackable;
    }	
}
