import java.util.*;
import java.io.Serializable;

class Item implements Serializable {
public String name;
public String white_name;
public int rare; // rareté
public int pos; // position sur le corps, compris entre 0 et 13
				// (14 pour les ressources)
				
public double bonus[]; // bonus
public int ilvl; // niveau de l'objet
public int elvl; // niveau des enchantements
public double quality; //qualité de l'objet
public double poids;
public boolean equiped;
public BaseItem baseItem;
public Material material;
public boolean stackable;
public double qty;

public String item_description(Player p)
  {
  String res = name;
  res +=  String.format("\nType : %s (%s)\n",StaticItem.Rarete[rare],rare);
  if (rare>0 && !stackable) res += "\nBase : " + NameGenerator.firstCharLowercase(white_name);
  res += String.format("\nNiveau effectif : %d",effectiveIlvl());
  if (!stackable) 
	{
	int nb_ench = nb_ench();
	res += String.format("\nEmplacement : %s",NameGenerator.firstCharLowercase(StaticItem.Emplacement[pos]));
	if(Math.abs(quality)>0.0001) res += String.format("\nQualité : %.2f%%",quality*100);
	res += String.format("\nEfficacité : %.1f (%d×%.2f)",ilvl*material.coeffEfficacite,ilvl,material.coeffEfficacite);
	res += String.format("\nEnchantement : %.1f (%d×%.2f)",elvl*material.coeffLevel,elvl,material.coeffLevel);
	if(nb_ench > 0) res += String.format("\nNombre d'enchantements : %s",nb_ench);
	if(nb_ench > 1) res += String.format("\nBonus compétence : %.1f points",nb_pts());
	}
  if (stackable)  res +=  String.format("\nQuantité : %.4f",qty);
  res += String.format("\nPoids : %.3f kilogrammes",poids);
  res += String.format("\nPrix réel : %.2f écus",prix());
  res += String.format("\nPrix de vente : %.2f écus",prix()*p.coeff_vente());
  res += String.format("\nPrix d'achat : %.2f écus\n",prix()*p.coeff_achat());

 if(rare == 4 || rare == 5)
      {
	  res+=String.format("\nFamille : %s",StaticItem.MaterialFamily[material.type]);
	  res+=String.format("\nCoefficient de poids : %.2f",material.coeffPoids);
	  res+=String.format("\nCoefficient de prix : %.2f",material.coeffPrix);
	  res+=String.format("\nCoefficient d'efficacité : %.2f",material.coeffEfficacite);
	  res+=String.format("\nCoefficient magique : %.2f",material.coeffLevel);
      }

  if(bonus != null)
      for (int i=0; i<Player.nb_stats; i++)
	  if (bonus(i)>0)
	      {
			if(bonus(i) >= 100)
		  		res += String.format("\n%s +%.0f",Player.stats_name[i],bonus(i));
			else
				res += String.format("\n%s +%.2f",Player.stats_name[i],bonus(i));
	      }
  return res;
  }

    public int effectiveIlvl() // used for the price and for the drops
    {
	return Math.max(1,(int)(ilvl*0.80*(material.coeffEfficacite+material.coeffLevel)));
    }

    public int effectiveElvl() // used for the price 
    {
	return (int)(elvl*material.coeffLevel);
    }

    // Fabrique une ressource
    public Item(Material m, int MODE)
    {
	// Création de base
	elvl = 0;
	stackable = true;
	quality = 0;
	pos = 15;
	material = m;
    ilvl = 10;
	bonus = null;

	// Création à partir du craft ou pour les ressources statiques
	int r = 4;
	if (MODE == StaticItem.RESSOURCE_PRIM) {r=5; ilvl=3*ilvl;}
	if(m.type == 5) {r=6; ilvl+=20;}
	rare = r;
	
	if(MODE == StaticItem.RESSOURCE_CRAFT) qty = 0.5*Math.random()+0.1;
	else qty = 1.0;
      
	update();
    }

    public void set_qty(double a)
    {
	qty = a;
	update();	
    }

    public void multiply_qty(double a)
    {
	qty = a * qty;
	update();
    }

    public void add_qty(double a)
    {
	qty = a + qty;
	update();
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
			name = name + " alchimique";
	    }
	else
	    {
		white_name = NameGenerator.firstCharUppercase(baseItem.name) + " " + material.nameOf;
		if(rare == 0)
		    name = white_name;
		else if (rare == 1)  
		    name = NameGenerator.firstCharUppercase(baseItem.name) + " " + StaticItem.nameGenerator.getNameOf();
		else if (rare == 2 || rare == 3) 
		    name = NameGenerator.firstCharUppercase(StaticItem.rare_name()) + " " + StaticItem.nameGenerator.getNameOf();
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

static public Item get_ressouce_base(int il)
{
	if(Math.random() < il/1000.0 && il >= 50)
	{
		return new Item(StaticItem.getRandomOrb(),StaticItem.RESSOURCE_ELEM);
	}
	else
	{
	ArrayList<Item> totallist = new ArrayList<Item>();
	int totalsize = 0;
	
    int i = il;
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
	double res = bonus[i] * material.coeffLevel;
	if(i == baseItem.base_bonus)
	    res += (0.60 * ilvl * material.coeffEfficacite);
	return res * (1.0+quality);
    }


public Item get_normal_base(int il)
{
    ItemSet its = StaticItem.WhiteItemByLevel[il];
    int i = il;
    while(its.list.size() == 0)
	{
	    i--;
	    its = StaticItem.WhiteItemByLevel[i];
	}
    return its.list.get((int)(Math.random()*its.list.size()));
}

    // sert à remplir l'inventaire d'un marchand
    public Item(int niveau)
    {
	int drop_lvl = Math.max(niveau - (int)(Math.random()*6),1);
	if(Math.random()  < StaticItem.PROBA_RES_BASE) // ressource
	    {
		this.copy(get_ressouce_base(drop_lvl));
		this.qty = 0.2 + Math.random() + Math.pow((double)(niveau),1.4)/200.0;
		this.update();
	    }
	else
	    {
		this.copy(get_normal_base(drop_lvl));
	    }
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

    public static double randomInInterval(Random rg, double i, double j)
    {
	double start = Math.min(i,j);
	double end = Math.max(i,j);
	return rg.nextDouble()*(end-start)+start;
    }


   public static Material materialFusion(ArrayList<Material> LI)
    {
	Collections.sort(LI, new ComparateurMaterial());
	Material a = LI.get(0);
	Material b = LI.get(1);
	Material c = LI.get(2);

	int hash = (a.name + b.name + c.name).hashCode();
	
    Random rg = new Random(hash);
	String an = StaticItem.matNameGenerator.getMatName(hash);
	String n =  "Alliage "+ an;
	String no = "d'alliage " + an;
	int ty = 4;
	double cpo = randomInInterval(rg,a.coeffPoids,b.coeffPoids);
	double cpr = randomInInterval(rg,a.coeffPrix,b.coeffPrix);
	double sol = randomInInterval(rg,a.coeffSolidite,b.coeffSolidite);
	double eff = randomInInterval(rg,a.coeffEfficacite,b.coeffEfficacite);
	double clvl = randomInInterval(rg,a.coeffLevel,b.coeffLevel);

	Material res = new Material(n, no, ty, cpo, cpr, sol, eff, clvl);
	return res;
	}

	public void aligner_qty(Player p, double prix)
	{
		double mul = prix/prix();
		qty = qty*mul*p.rendement();
		update();
	}

	public static ArrayList<Item> CraftItem(ArrayList<Item> LI, Player p)
	{
		Collections.sort(LI, new ComparateurItem());
		ArrayList<Item> rlist = new ArrayList<Item>();
		double orb_unit = p.economie_orbe();
		int lisize = LI.size();
		
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
					tmp = new Item(StaticItem.ORB[4],StaticItem.RESSOURCE_CRAFT); // "orbe d'augmentation"
					tmp.aligner_qty(p, a.prix());
					rlist.add(tmp);
				}
				else
				{
					tmp = new Item(a.material,StaticItem.RESSOURCE_CRAFT);
					tmp.aligner_qty(p, a.prix());
					rlist.add(tmp);
				}
				Game.MW.addLog(String.format("Vous obtenez %f %s",tmp.qty,tmp.name));
			}
			else if (a.rare == 2 || a.rare == 3)
			{
				// recyclage d'un rare ou d'un légendaire 
				// (donne des orbes)
				tmp = new Item(StaticItem.getRandomOrb(),StaticItem.RESSOURCE_CRAFT);
				tmp.aligner_qty(p, a.prix());
				rlist.add(tmp);
				Game.MW.addLog(String.format("Vous obtenez %f %s",tmp.qty,tmp.name));
			}
			else if (a.rare == 4 || a.rare == 5)
			{
				// recyclage d'une ressource élémentaire ou d'une ressource primordiale 
				// (donne des ressources)
				tmp = new Item(get_ressouce_base(a.effectiveIlvl()));
				tmp.aligner_qty(p, a.prix());
				Game.MW.addLog(String.format("Vous obtenez %f %s",tmp.qty,tmp.name));
				rlist.add(tmp);
			}
			else if (a.rare == 6 && a.qty >= orb_unit)
			{
				// recyclage d'un orbe
				if (a.name.equals("Orbe d'évolution") || a.name.equals("Orbe d'augmentation"))
				{
					int xp = (int)(5000 * p.rendement());
					Game.MW.addLog(String.format("Vous obtenez %d points d'expérience",xp));	
					p.gain_xp(xp);
				}
				else if (a.name.equals("Orbe de transfert") || a.name.equals("Orbe de transmutation"))
				{
					p.reset_build();
				}
				a.set_qty(a.qty-orb_unit);
				if(a.qty>0.01) rlist.add(a);
			}
			else
			{
				rlist.add(a);
				Game.MW.addLog("Rien ne se produit...");	
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
				mat = StaticItem.getRandomOrb();
				res = new Item(mat,StaticItem.RESSOURCE_CRAFT);
				res.aligner_qty(p, a.prix()+b.prix());
				rlist.add(res);
			}
			else if ((a.rare == 6 && a.qty >= orb_unit) || (b.rare == 6 && b.qty >= orb_unit)) // au moins un orbe
			{
				Item comp, other;	
				if (a.rare == 6 && a.qty >= orb_unit) {comp = a; other = b;}
				else {comp = b; other = a;}

				// orbe de transmutation + ressource quelconque
				// ressource aléatoire (conservation du prix)
				if((other.rare == 4 || other.rare == 5) && comp.name.equals("Orbe de transmutation")) 
				{
					mat = StaticItem.getRandomMat(); // tirer un mat non-orbe
					res = new Item(mat,StaticItem.RESSOURCE_CRAFT);
					res.rare = other.rare;
					comp.set_qty(comp.qty-orb_unit);
					res.aligner_qty(p, other.prix()+comp.prix_unitaire()*orb_unit);
					rlist.add(res);
					if(comp.qty>0.01) rlist.add(comp);			  
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
					if(comp.qty>0.01) rlist.add(comp);			  
				}
				// orbe d'augmentation + ressource quelconque
				// augmente la quantité de ressource (conservation du prix)
				else if((other.rare == 4 || other.rare == 5) && comp.name.equals("Orbe d'augmentation")) 
				{
					res = other;
					res.aligner_qty(p, other.prix()+comp.prix_unitaire()*orb_unit);
					comp.set_qty(comp.qty-orb_unit);
					rlist.add(res);
					if(comp.qty>0.01) rlist.add(comp);			  
				}
				// orbe d'augmentation + objet quelconque
				// augmente la qualité de l'objet de 5% (limité à 50%)
				else if(other.rare < 4 && comp.name.equals("Orbe d'augmentation")) 
				{
					res = other;
					res.quality = Math.min(res.quality + 0.05*p.rendement(),0.50*p.rendement());
					comp.set_qty(comp.qty-orb_unit);
					rlist.add(res);
					if(comp.qty>0.01) rlist.add(comp);			  
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
					if(comp.qty>0.01) rlist.add(comp);	  
				}
				// orbe d'évolution + objet normal, magique ou rare
				// ajoute un enchantement à l'objet (dans la limite des 5 enchantements)
				else if(other.rare < 3 && comp.name.equals("Orbe d'évolution")) 
				{
					res = other;
					res.add_ench(p);
					comp.set_qty(comp.qty-orb_unit);
					rlist.add(res);
					if(comp.qty>0.01) rlist.add(comp);			  
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
					if(comp.qty>0.01) rlist.add(comp);			  
				}
				else
				{
					rlist.add(a);
					rlist.add(b);
				}
			}
			// deux objets normaux, magiques ou rares
			// un ojet aléatoire
			else if(a.rare < 3 && b.rare < 3)
			{
				res = new Item(ba, mat);
				res.elvl = elv;
				if (rarity == 1)
					res.transform_magic(p);
				else if (rarity == 2)
					res.transform_rare(p);

				if ( res.material.type != res.baseItem.mat)
					res.quality = -(0.20*Math.random()+0.40); // penality for bad material

				res.update();
				rlist.add(res);
			}
			// une ressource primordiale et un objet
			// change la nature du matérieau de l'objet
			else if(a.rare < 4 &&  b.rare == 5 && b.qty >= 1.0)
			{
				res = a;
				res.material = b.material;
				if ( res.material.type != res.baseItem.mat)
					res.quality = -(0.20*Math.random()+0.20); // penality for bad material
				res.update();

				b.set_qty(b.qty-1.0);
				rlist.add(res);
				if(b.qty>0.01) rlist.add(b);
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
				Item rit = new Item(materialFusion(mats),StaticItem.RESSOURCE_CRAFT);
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
					Game.MW.addLog(String.format("Fusion de deux objets (un objet est détruit, %.2f%% des enchantements sont transférés vers l'autre)", 100*seuil));
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
						b.ilvl = (int)(p.rendement()*a.ilvl);
					else 
						a.ilvl = (int)(p.rendement()*b.ilvl);
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
				if(c.qty>0.01) rlist.add(c);
			}
			else
			{
				rlist.add(a);
				rlist.add(b);
				rlist.add(c);
			}
		}
		// Fusion de N objets avec N orbes de fusion
		if(lisize > 3)
		{
			Item last = LI.get(lisize - 1);
			boolean allOk = true;
			int totalNbEnch = 0;
			for (int idx = 0; idx < lisize-1; idx++)
			{
				Item theItem = LI.get(idx);
				totalNbEnch += theItem.nb_ench();
				if(theItem.rare < 1 || theItem.rare > 3)
				{
					allOk = false;
					break;
				}
			}
			double orbcost = lisize*orb_unit;
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
				if(last.qty>0.01) rlist.add(last);
			}
			else
			{
				return LI;
			}
		}
		return rlist;
	}

    // Drop sur un monstre
    public Item(int niveau, Player p)
    {
	int drop_lvl = Math.max(1,(int)(niveau*0.7+Math.random()*niveau*0.3));
	if(Math.random() < StaticItem.PROBA_RES_BASE) // ressource
	    {
		this.copy(get_ressouce_base(drop_lvl));
		qty = Math.random()*p.multiplicateur_res()*2.0;
	    }
	else // objet de base ou magique ou rare
	    {
		this.copy(get_normal_base(drop_lvl));
		if (Math.random() < p.chance_magique())
		    {
			elvl = niveau;
			if (Math.random() <= p.chance_rare())
			    transform_rare(p);	      
			else
			    transform_magic(p);
		    }
		if (Math.random()< p.chance_qualite())
		    quality = Math.random()*0.40;
	    }
	update();
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
	private double nb_pts()
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
public Item(BaseItem ba, Material mat)
    {
	stackable = false;
	material = mat;
	baseItem = ba;
	equiped = false;
	rare = 0;
	elvl = 0;
	ilvl = ba.pref_level;
	pos = ba.pos;
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
