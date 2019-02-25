import java.util.*;
import java.io.Serializable;


public class Player implements Serializable {
    static private final long serialVersionUID = 100;
	static private int dsp_target[] = {0,1,2,3,4,5,6,7,9,10,24,31,53};
	static public int MAX_LEVEL = 2000000000;
		
    //***********************
	public boolean jeu_fini = false;
	public int nb_hits = 0;
    public int crit_taken = 0;	
	public int nb_encounters = 0;	
	 
    public static String[] TagsName = Local.TAGS_NAME; // Tags names
	public static int nb_tags = TagsName.length; // Number of tags
    public boolean[] tags; // Player's tags
    public String name; // Player's name
	
	public Challenge defi;
    public double[] stats; // répartition des points de compétences
    public double[] stats_with_bonus; // For fast access
    public double[] item_bonus; // For fast access, used only in LevlUp
    public int[] auto_dist_coeff; // For auto_dist

	public double temperature_diff = 0.0;
	public double precipitation_strength = 0.0;

    public static String[] stats_name =  Local.SKILLS_NAME; // nom des compétences 

    public boolean disp;
    public boolean[] conditionToggle;
    public int[] conditionValues;   

    public static int nb_stats = stats_name.length; // nombre de statistiques

    public double temps; // temps écoulé depuis le début du combat
    public double temps_total; // temps écoulé depuis le début du jeu
	
	public double orbes_investits_en_points_divins;
	public double orbes_investits_en_points_competence;
    public double vie; // vie
    public ArrayList<Item> inventory;// inventraire
    public ArrayList<Item> craftInventory; //inventaire de la forge mystique
    public ArrayList<ObjectRule> rules; // régles et filtres d'inventaires

    public double charge;
    public double level;  // niveau
    public double money; // argent
    public int zone; // zone
    public double xp_pt; // exp
    public Shop shop; // the shop
    public Monster mob; // the mob
	public Universe universe; // the universe
    public TimeStats t_stats ;
	public double points_haut_faits = 0.0;
	
	static class ComparateurStats implements Comparator<Item> {
	public int compare(Item s1, Item s2){
	    if (s1.nb_pts() > s2.nb_pts()) return -1;
	    if (s1.nb_pts() < s2.nb_pts()) return 1;
	    else return 0;
	} 
}
	
	public double heat_penalty()
	{
			return  Math.min(1.0,100.0/(100+temperature_diff*heat_resistance()));
	}
	
	public double cold_penalty()
	{
			return Math.min(1.0,100.0/(100-temperature_diff*cold_resistance()));
	}
	
	public double overload_penalty()
	{
		double cm = charge_max();
		if (charge <= cm) return 1.0;
		else return universe.base_overload_penalty((charge/cm-1.0)*overload_resistance());
	}
	
	public double underload_bonus() // Base load ratio is limited to 10000
	{
		double cm = charge_max();
		if (charge >= cm) return 1.0;
		double cha = Math.max(charge,0.0001);
		return universe.base_underload_bonus(Math.min(cm/cha,10000))*underload_affinity();
	}
	
	public double precipitation_penalty()
	{
		return 10.0/(10+precipitation_strength*precipitation_resistance());
	}
	
	public double heat_bonus()
	{
			return  Math.max(1.0,(100+temperature_diff*heat_affinity())/100.0);
	}
	
	public double cold_bonus()
	{
			return Math.max(1.0,(100-temperature_diff*cold_affinity())/100.0);
	}
	
	public double precipitation_bonus()
	{
		return (10.0+precipitation_strength*precipitation_affinity())/10.0;
	}
	
	public void refresh_weather_penalties()
	{
		temperature_diff = universe.get_current_temperature(zone) - universe.temperature_ideale();
	}

	public void voyage(int zn)
	{
		if (zn != zone)
		{
			zone = zn;
			nb_encounters = 0;
			double bp = universe.base_penalty_for_travel();
			double pr = penalty_reduction();
			personal_wait(bp*pr,TimeStats.ACTIVITY_PENALTY);
			if(disp) Game.MW.addLog(String.format(Local.TRAVEL_TO,name,universe.map.zonesName.get(zone),bp*pr,pr,bp));
			update_weather();
		}
	}
	
	public void refresh_charge()
	{
		charge = 0.0;
		for(Item the_object : inventory)
	    {
		charge += the_object.poids_final(this);
	    }
	}
	
	public double points_distribues()
	{
		double res = 0;
		for(int i=0; i<nb_stats; i++) res += stats[i];
		return res;
	}
	
	public double points_divins_distribues()
	{
		double res = 0;
		for(int i=0; i<universe.nb_universe_stats+universe.nb_universe_equations; i++) res += Math.abs(universe.points_divins[i]);
		return res;
	}
	
    public void remove_item(Item i)
    {
	i.equiped=false;
	refresh_stats_with_bonus();
    }

    public void put_item(Item i)
    {
	for(Item the_object : inventory)
	    {
		if(the_object.equiped && (the_object.pos == i.pos))
		    {the_object.equiped=false; break;}
	    }
	i.equiped=true;
	refresh_stats_with_bonus();
    }

	public double prix_achat(Item b)
	{
		if (b.discount) return discount_multiplier()*coeff_achat()*b.prix();
		else return coeff_achat()*b.prix();
	}
	
	public double prix_vente(Item b)
	{
		if (b.discount) return discount_multiplier()*coeff_vente()*b.prix();
		else return coeff_vente()*b.prix();
	}
	
    public boolean can_buy(Item b)
    {
	return (money - prix_achat(b) >= 0 || (b.stackable && money > 0.01));
    }
	
	public void buy(Item it)
	{
		if (money<0.01) return;
		ArrayList<Item> tmp =  new ArrayList<Item>();
		tmp.add(it);
		buy(tmp);
	}

    public void buy(ArrayList<Item> slist)
    {
	if (slist.size()==0 || money<0.01) return;

	double perte_base = 0.0;
	double perte_mat = 0.0;
	double perte_orb = 0.0;
	double perte_other = 0.0;
	double total_prix = 0.0;
	
	ArrayList<Item> toAdd = new ArrayList<Item>();
	ArrayList<Item> toRemove = new ArrayList<Item>();
	
	String partial;
	for(Item the_object : slist)
	{
	double prix = prix_achat(the_object);
	if(total_prix+prix <= money)
	{
		toAdd.add(the_object);
		toRemove.add(the_object);
		total_prix += prix;
	}
	else if (the_object.stackable)
	{
		double qty_to_buy = ((money-total_prix)/prix)*the_object.qty;
		the_object.set_qty(the_object.qty - qty_to_buy);
		Item rit = new Item();
		rit.copy(the_object);
		rit.set_qty(qty_to_buy);
		total_prix = money;
		toAdd.add(rit);
		break;
	}
	}
	
	for(Item the_object : toAdd)
	{
	double prix = prix_achat(the_object);
	if(the_object.rare == 0) perte_base += prix;
	else if(the_object.rare == 4 || the_object.rare == 5) perte_mat += prix;
	else if(the_object.rare == 6) perte_orb += prix;
	else perte_other += prix;
	charge += the_object.poids_final(this);
	}
	
	inventory.addAll(toAdd);
	shop.inventory.removeAll(toRemove);
	
	int nbitem = toAdd.size();
	String buyStr="";
	if(nbitem < 10)
	{
	for(Item the_object : toAdd)
	{
		if(buyStr != "") buyStr += ", ";
		buyStr += NameGenerator.firstCharLowercase(the_object.name);
	}
	}
	else
	{
		buyStr = String.format(Local.N_OBJECTS,nbitem);
	}

	if(perte_base > 0.0)
		money_loss(perte_base,TimeStats.LOSS_BUY_BASE);
	if(perte_mat > 0.0)
		money_loss(perte_mat,TimeStats.LOSS_BUY_MAT);
	if(perte_orb > 0.0)
		money_loss(perte_orb,TimeStats.LOSS_BUY_ORB);
	if(perte_other > 0.0)
		money_loss(perte_other,TimeStats.LOSS_BUY_OTHER);

	clean_list(inventory);
	if(disp) Game.MW.addLog(String.format(Local.BUYING_OBJECTS,buyStr,shop.name,total_prix));
    }

	public void money_gain(double gain, int type_gain)
	{
		t_stats.addRevenue(gain, type_gain);
		money += gain;
	}
	
	public void money_loss(double loss, int type_loss)
	{
		t_stats.addRevenue(loss, type_loss);
		money -= loss;
		if (money < 0) money = 0.0;
	}
	
	public void sell(ArrayList<Item> slist)
    {
	int nbitem = slist.size();
	if(nbitem == 0) return;
	get_shop();
	
	double gain_base = 0.0;
	double gain_magique = 0.0;
	double gain_rare = 0.0;
	double gain_mat = 0.0;
	double gain_other = 0.0;
	double gain_total = 0.0;
	boolean equiped = false;
	
	String sellStr = "";
	if(nbitem < 10)
	{
	for(Item the_object : slist)
	{
	if(sellStr != "") sellStr += ", ";
	sellStr += NameGenerator.firstCharLowercase(the_object.name);
	}
	}
	else
	{
		sellStr = String.format(Local.N_OBJECTS,nbitem);
	}
	
	for(Item the_object : slist)
	{
	double prix = prix_vente(the_object);
	//inventory.remove(the_object);
	charge -= the_object.poids_final(this);
	if(the_object.equiped) {equiped = true; the_object.equiped = false;}
	//shop.inventory.add(the_object);
	
	if(the_object.rare == 0) gain_base += prix;
	else if(the_object.rare == 1) gain_magique += prix;
	else if(the_object.rare == 2) gain_rare += prix;
	else if(the_object.rare == 4 || the_object.rare == 5) gain_mat += prix;
	else gain_other += prix;
	gain_total += prix;
	}
	inventory.removeAll(slist);
	shop.inventory.addAll(slist);
	
	if(equiped) refresh_stats_with_bonus();
	
	if(gain_base > 0.0)
		money_gain(gain_base,TimeStats.GAIN_SELL_BASE);
	if(gain_magique > 0.0)
		money_gain(gain_magique,TimeStats.GAIN_SELL_MAGIC);
	if(gain_rare > 0.0)
		money_gain(gain_rare,TimeStats.GAIN_SELL_RARE);
	if(gain_mat > 0.0)
		money_gain(gain_mat,TimeStats.GAIN_SELL_MAT);
	if(gain_other > 0.0)
		money_gain(gain_other,TimeStats.GAIN_SELL_OTHER);	
	
	if(disp) Game.MW.addLog(String.format(Local.SELLING_OBJECTS,sellStr,shop.name,gain_total));
	if (charge<0.0) charge = 0.0;
    }
	
    public void sell(Item it)
    {
		ArrayList<Item> tmp =  new ArrayList<Item>();
		tmp.add(it);
		sell(tmp);
	}

    public Item get_similar(ArrayList<Item> inventory, Item i)
    {
	for(Item the_object : inventory)
	    if (the_object.name.equals(i.name) && the_object.stackable)
		return the_object;
	return null;
    }

	public static void clean_list(ArrayList<Item> slist)
	{
		try{
		ArrayList<Item> slist2;
		Collections.sort(slist, new Comparator<Item>() 
		{public int compare(Item i1, Item i2) {return  i1.name.compareTo(i2.name);}});

		Iterator it = slist.iterator();
		Item previous = (Item)it.next();
		while(it.hasNext()) {
			Item current = (Item)it.next();
			if (current.name.equals(previous.name) && previous.stackable)
			{
				previous.add_qty(current.qty);
				if(current.discount) previous.discount = true;
				it.remove();
			}
			else
			{
				previous = current;
			}
		}
		}
		catch (Exception e)
		{System.out.println("clean_list fail");}
	}
	
	public void craftAuto()
	{
		// Vide l'inventaire de la forge
		int crs = craftInventory.size();
		for(int i=0; i< crs; i++)
			get_craft(craftInventory.get(0));

		clean_list(inventory);

		// Sélectionne les règles de craft
		ArrayList<ObjectRule> crrules = new ArrayList<ObjectRule> ();
		for (ObjectRule r: rules)
			if(r.meta_type == ObjectRule.CRAFT_RULE)
				crrules.add(r);
		
		while(!crrules.isEmpty())
		for (ObjectRule r: crrules)
			{
				boolean rule_fail = false;
				ArrayList<Item> crlist = new ArrayList<Item> ();
				for (ObjectRule r2: r.ruleList)
				{
				// On peut avoir une règle sur le joueur pour une formule de craft
				// Elle doit être vraie pour que la règle s'applique
				if (r2.meta_type == ObjectRule.PLAYER_RULE) 
				{
					rule_fail = r2.IsTrue(this,null,null) ; break;
				}
				boolean item_found = false;
				for(Item the_object : inventory)
					if(r2.IsTrue(this,the_object,null) && !crlist.contains(the_object))
					{
						item_found = true;
						crlist.add(the_object);
						break;
					}
				if(item_found == false) {rule_fail = true; break;}
				}
				/*System.out.println("rule "+ r.name + "(fail="+ rule_fail +") crrules="+crrules.size());
				for(Item the_object : crlist)
					System.out.println(the_object.name);*/

				if(!rule_fail)
				{
					put_craft(crlist);
					craft();
					crs = craftInventory.size();
					if(craftInventory.size() == crlist.size()) rule_fail = true;
					for(int i=0; i< crs; i++)
						get_craft(craftInventory.get(0));
				}
				if(rule_fail) {crrules.remove(r); break;}
			}
	}
	
	
	public void put_craft(ArrayList<Item> slist)
    {
	int nbitem = slist.size();
	String putStr = "";
	boolean equiped=false;
	
	if(nbitem < 10)
	{
	for(Item the_object : slist)
		{
		if(putStr != "") putStr += ", ";
		putStr += NameGenerator.firstCharLowercase(the_object.name);
		}
	}
	else
	{
		putStr = String.format(Local.N_OBJECTS,nbitem);
	}
	
	for(Item the_object : slist)
	{
	charge -= the_object.poids_final(this);
	if(the_object.equiped) {equiped = true; the_object.equiped = false;}
	}
	
	inventory.removeAll(slist);
	craftInventory.addAll(slist);
	
	clean_list(craftInventory);
	
	if(equiped) refresh_stats_with_bonus();
	
	if(disp) Game.MW.addLog(String.format(Local.DROPPING_OBJECTS,name,putStr));
	if (charge<0.0) charge = 0.0;
    }
	
    public void put_craft(Item b)
    {
	deposit_item(b,craftInventory);
	if(disp) Game.MW.addLog(String.format(Local.DROPPING_OBJECTS,name,b.name));
    }

    public void destroy_item(Item b)
    {
	inventory.remove(b);
	charge -= b.poids_final(this);
	if(b.equiped) refresh_stats_with_bonus();
	if(disp) Game.MW.addLog(String.format(Local.THROW_AWAY_OBJECTS,name,b.name));
    }

	public void invToConsole()
	{
		System.out.print("(");
		for(Item the_object : inventory)
			System.out.print(the_object.name + " ");
		System.out.println(")");
	}
	
    public void get_item(Item b)
    {
	Item tmp;
	b.equiped = false;
	if(b.stackable) // Ressource
	    {
		tmp = get_similar(inventory,b);
		if(tmp != null)		    
		    tmp.add_qty(b.qty);
		else 
		    inventory.add(b);
	    }
	else  // Autres objets
	    inventory.add(b);
	charge += b.poids_final(this);
    }

    public void deposit_item(Item b, ArrayList<Item> dest)
    {
	inventory.remove(b);
	charge -= b.poids_final(this);
	if(b.equiped) refresh_stats_with_bonus();

	Item tmp;
	b.equiped = false;
	if(b.stackable) // Ressource
	    {
		tmp = get_similar(dest,b);
		if(tmp != null)
		    tmp.add_qty(b.qty);		
		else 
		    dest.add(b);
	    }
	else  // Autres objets
	    dest.add(b);
    }

    public void get_craft(Item b)
    {
	craftInventory.remove(b);
	get_item(b);
	if(disp) Game.MW.addLog(String.format(Local.PICKING_OBJECTS,name,b.name));
    }

    public void refresh_stats_with_bonus()
    {
	for(int i=0; i<nb_stats; i++) 
		{
		item_bonus[i]=0;
		if (stats[i] != stats[i]) stats[i] = 0.0; // NaN != NaN
	    stats_with_bonus[i]=stats[i];
		}
		
	for(Item the_object : inventory)
	    {
		if(the_object.equiped)
		    for(int i=0; i < nb_stats; i++) 
			{
				stats_with_bonus[i]+=the_object.bonus(i);
				item_bonus[i]+=the_object.bonus(i);
			}
	    }
	refresh_charge();
	vie=vie_max();
    }
	
	public String short_infos()
	{
		String res = String.format(Local.SHORT_INFOS,
		level,temps_total,money,charge,charge_max());
		return res;
	}
	
	public String infos()
	{
		double dmpa = dmpa();
		double levListShort[] = {1,3,5,10};
		double levList[] = {universe.get_zone_level(zone), universe.get_zone_max_level(zone),level,level*0.75};
		Arrays.sort(levList);
		if (level < 10) levList = levListShort;
		double lvl0=Math.floor(levList[0]);
		double lvl1=Math.floor(levList[1]);
		double lvl2=Math.floor(levList[2]);
		double lvl3=Math.floor(levList[3]); 
		if(lvl3==lvl2) lvl3*=1.25;
		
		return String.format(Local.PLAYER_INFOS,
			att_per_sec(),dmg_base(),100.0*crit_proba(),multi_crit(),
			dmpa,att_per_sec()*dmpa,
			ed_versus_tag(0),ed_versus_tag(1),ed_versus_tag(2),ed_versus_tag(3),ed_versus_tag(4),ed_versus_tag(5),
			lvl1,100.0-universe.esquive_proba(universe.monster_points_for_level(lvl1)*Monster.coeff_std[4]*0.5,PRC())*100.0,
			lvl2,100.0-universe.esquive_proba(universe.monster_points_for_level(lvl2)*Monster.coeff_std[4]*0.5,PRC())*100.0,
			lvl3,100.0-universe.esquive_proba(universe.monster_points_for_level(lvl3)*Monster.coeff_std[4]*0.5,PRC())*100.0,
			lvl1,universe.esquive_proba(ESQ(),universe.monster_points_for_level(lvl1)*Monster.coeff_std[5]*0.5)*100.0,
			lvl2,universe.esquive_proba(ESQ(),universe.monster_points_for_level(lvl2)*Monster.coeff_std[5]*0.5)*100.0,
			lvl3,universe.esquive_proba(ESQ(),universe.monster_points_for_level(lvl3)*Monster.coeff_std[5]*0.5)*100.0,
			100.0-(100.0*reduc()),bonus_resistance_vs_piege(),100.0-(100.0*resistance_vs_piege()),absorption(),
			pv_per_vita(),vie_max(),vie_max()/reduc(),
			regen(),vie_max()/regen(),100*vol_de_vie(),
			initiative(),bonus_initiative_piege(),initiative_piege(),
			temps_traque(),temps_shop(),temps_forge(),
			temps_res(),charge_max(),coeff_vente(),coeff_achat(),100*chance_fuite(),temps_fuite(),
			chance_magique()*100,chance_magique()*chance_rare()*100,chance_qualite()*100,multiplicateur_or(),
			multiplicateur_res(),quantite_drop(),
			quantite_drop()*universe.proba_ressource(), // Nombre de ressources
			quantite_drop()*(1-universe.proba_ressource()), // Nombre d'objets hors ressources
			quantite_drop()*universe.proba_ressource()*multiplicateur_res()*universe.quantite_ressource_base_drops(), // Quantité totale de ressources
			quantite_drop()*(1-universe.proba_ressource())*chance_magique(), // Nombre d'objets magiques
			quantite_drop()*(1-universe.proba_ressource())*chance_magique()*chance_rare(), // Nombre d'objets rares
			puissance_ench_inf(),
			puissance_ench_sup(),
			100.0*proba_immunite_final(),
			facteur_temps(),epines(),
			100*represailles(),
			100*necrophagie(),
			temps_craft(),100*rendement(),100*economie_orbe(),
			multiplicateur_niveau_boutique(),niveau_boutique_base()*multiplicateur_niveau_boutique(),
			taille_boutique(),
			lvl1,100.0*proba_trouver_piege(lvl1),
			lvl2,100.0*proba_trouver_piege(lvl2),
			lvl3,100.0*proba_trouver_piege(lvl3),
			rente_par_rencontre(),points_par_niveau(),
			100.0*(bonus_xp()-1),
			lvl0,100.0*modif_exp_lvl(lvl0-level)-100.0,
			lvl1,100.0*modif_exp_lvl(lvl1-level)-100.0,
			lvl2,100.0*modif_exp_lvl(lvl2-level)-100.0,
			lvl3,100.0*modif_exp_lvl(lvl3-level)-100.0,
			100*(1.0-penalty_reduction()),
			max_zone_level(),
			universe.points_divins_multiplier(DIEU()),
			multi_premier_coup(),
			divine_cap_eq(),divine_cap_const(),
			resources_weight_multiplier(),equipment_weight_multiplier(),
			cold_resistance(), heat_resistance(), precipitation_resistance(), overload_resistance(),
			100*(1-cold_penalty()), 100*(1-heat_penalty()), 100*(1-precipitation_penalty()), 100*(1-overload_penalty()),
			cold_affinity(), heat_affinity(), precipitation_affinity(), underload_affinity(),achievements_affinity(),
			100*(cold_bonus()-1.0), 100*(heat_bonus()-1.0), 100*(precipitation_bonus()-1.0),
			100*(underload_bonus()-1.0),100*(bonus_haut_faits()-1.0),
			clearance_sale_inventory_multiplier(),discount_multiplier()
			);
	}

    public double dmpa()
    {return dmg_base() + dmg_base()*(multi_crit()-1.0)*crit_proba();}
    
    public double IAS()
    {return stats_with_bonus[Universe.IAS]*cold_bonus();}

    public double DMG()
    {return stats_with_bonus[Universe.DMG];}

    public double REDUC()
    {return stats_with_bonus[Universe.REDUC]*heat_bonus();}

    public double ABS()
    {return stats_with_bonus[Universe.ABS];}

    public double ESQ()
    {return stats_with_bonus[Universe.ESQ]*precipitation_bonus();}

    public double PRC()
    {return stats_with_bonus[Universe.PRC]*precipitation_penalty();}

    public double LCK()
    {return stats_with_bonus[Universe.LCK]*precipitation_penalty();}

    public double CRT()
    {return stats_with_bonus[Universe.CRT];}

    public double VDV()
    {return stats_with_bonus[Universe.VDV];}

    public double VITA()
    {return stats_with_bonus[Universe.VITA]*cold_penalty();}

    public double CON()
    {return stats_with_bonus[Universe.CON]*cold_penalty();}

    public double REGEN()
    {return stats_with_bonus[Universe.REGEN];}

    public double RESUR()
    {return stats_with_bonus[Universe.RESUR]*heat_bonus();}

    public double LOAD()
    {return stats_with_bonus[Universe.LOAD];}

    public double RUN()
    {return stats_with_bonus[Universe.RUN]*overload_penalty();}

    public double RESF()
    {return stats_with_bonus[Universe.RESF]*underload_bonus();}

    public double MF()
    {return stats_with_bonus[Universe.MF];}

    public double RF()
    {return stats_with_bonus[Universe.RF];}

    public double QALF()
    {return stats_with_bonus[Universe.QALF];}

    public double QTYF()
    {return stats_with_bonus[Universe.QTYF];}

    public double POWF()
    {return stats_with_bonus[Universe.POWF];}

    public double GF()
    {return stats_with_bonus[Universe.GF]*bonus_haut_faits();}

    public double ED_MV()
    {return stats_with_bonus[Universe.ED_MV];}

    public double ED_ANI()
    {return stats_with_bonus[Universe.ED_ANI];}

    public double ED_HUM()
    {return stats_with_bonus[Universe.ED_HUM];}

    public double ED_PV()
    {return stats_with_bonus[Universe.ED_PV];}

    public double ED_DEM()
    {return stats_with_bonus[Universe.ED_DEM];}

    public double ED_CHAMP()
    {return stats_with_bonus[Universe.ED_CHAMP];}

    public double ESTI()
    {return stats_with_bonus[Universe.ESTI];}

    public double FLEE()
    {return stats_with_bonus[Universe.FLEE];}

    public double FLEE_SPD()
    {return stats_with_bonus[Universe.FLEE_SPD];}

    public double INIT()
    {return stats_with_bonus[Universe.INIT]*overload_penalty();}

	public double IMUN_FINAL()
	{return stats_with_bonus[Universe.IMUN_FINAL]*precipitation_bonus();}

    public double TIME_SPD()
    {return stats_with_bonus[Universe.TIME_SPD]*cold_bonus();}

    public double EPIN()
    {return stats_with_bonus[Universe.EPIN];}

	public double REP()
	{return stats_with_bonus[Universe.REP];}

	public double NECRO()
	{return stats_with_bonus[Universe.NECRO];}

	public double CRAFT_SPD()
	{return stats_with_bonus[Universe.CRAFT_SPD];}
	
	public double CRAFT_REND()
	{return stats_with_bonus[Universe.CRAFT_REND];}
	
	public double ECO_ORB()
	{return stats_with_bonus[Universe.ECO_ORB];}
	
	public double SHOP_LEVEL()
	{return stats_with_bonus[Universe.SHOP_LEVEL];}

	public double SHOP_SIZE()
	{return stats_with_bonus[Universe.SHOP_SIZE];}	

	public double TRAP_DET()
	{return stats_with_bonus[Universe.TRAP_DET];}

	public double TRAP_INIT()
	{return stats_with_bonus[Universe.TRAP_INIT];}

	public double TRAP_RES()
	{return stats_with_bonus[Universe.TRAP_RES];}
	
	public double RENTE()
	{return stats_with_bonus[Universe.RENTE]*bonus_haut_faits();}
	
	public double EDUC()
	{return stats_with_bonus[Universe.EDUC];}
	
    public double BONUS_XP()
    {return stats_with_bonus[Universe.BONUS_XP]*heat_penalty();}
	
    public double BONUS_LOWLEV()
    {return stats_with_bonus[Universe.BONUS_LOWLEV];}
	
    public double BONUS_HLEV()
    {return stats_with_bonus[Universe.BONUS_HLEV];}
		
	public double REDUC_PEN()
    {return stats_with_bonus[Universe.REDUC_PEN];}
	
	public double ZONE_ACCESS()
    {return stats_with_bonus[Universe.ZONE_ACCESS];}
	
	public double DIEU()
    {return stats_with_bonus[Universe.DIEU];}
	
	public double FIRST_STRIKE()
    {return stats_with_bonus[Universe.FIRST_STRIKE];}
	
	public double EQ_MASTER()
    {return stats_with_bonus[Universe.EQ_MASTER];}

	public double CONST_MASTER()
    {return stats_with_bonus[Universe.CONST_MASTER];}
	
	public double LIGHTER_RES()
    {return stats_with_bonus[Universe.LIGHTER_RES];}
	
	public double LIGHTER_EQP()
	{return stats_with_bonus[Universe.LIGHTER_EQP];}
	
	public double COLD_RES()
	{return stats_with_bonus[Universe.COLD_RES];}

	public double HOT_RES()
	{return stats_with_bonus[Universe.HOT_RES];}
	
	public double PRECI_RES()
	{return stats_with_bonus[Universe.PRECI_RES];}

	public double COLD_BONUS()
	{return stats_with_bonus[Universe.COLD_BONUS];}

	public double HOT_BONUS()
	{return stats_with_bonus[Universe.HOT_BONUS];}
	
	public double PRECI_BONUS()
	{return stats_with_bonus[Universe.PRECI_BONUS];}
	
	public double OVERLOAD_RES()
	{return stats_with_bonus[Universe.OVERLOAD_RES];}
	
	public double UNDERLOAD_BONUS()
	{return stats_with_bonus[Universe.UNDERLOAD_BONUS];}
	
	public double ACHI_BONUS()
	{return stats_with_bonus[Universe.ACHI_BONUS];}
	
	public double SHOPPING_ADDICT()
	{return stats_with_bonus[Universe.SHOPPING_ADDICT];}

	public double DISCOUNT_SPEC()
	{return stats_with_bonus[Universe.DISCOUNT_SPEC];}
	
	public double total_skill_points()
	{
		double res=0;
		for(int i=0; i<nb_stats; i++) res+=stats_with_bonus[i];
		return res+points_a_distribuer();
	}

	public double proba_rencontrer_piege() {return universe.proba_rencontrer_piege()*universe.map.trap_coeff.get(zone);}
    public double vie() {return vie;}
    public double vie_max() {return universe.vie_max(VITA(),CON());}
    public double att_per_sec() {return universe.att_per_sec(IAS());}
    public double pv_per_vita() {return universe.pv_per_vita(CON());}
    public double crit_proba() {return universe.crit_proba(LCK());}
    public double multi_crit() {return universe.multi_crit(CRT());}
    public double dmg_base() {return universe.dmg_base(DMG());}
    public double reduc() {return universe.reduc(REDUC());}
    public double absorption() {return universe.absorption(ABS());}
    public double vol_de_vie() {return universe.vol_de_vie(VDV());}
    public double regen() {return universe.regen(REGEN());}
    public double charge_max() {if(Game.DEBUG_MODE_MAX_LOAD) return 10000000; else return universe.charge_max(LOAD());}
    public double temps_traque() {return universe.temps_traque(RUN());}
    public double temps_shop() {return universe.temps_shop(RUN());}
    public double temps_forge() {return universe.temps_forge(RUN());}
    public double temps_res() {return universe.temps_res(RESUR());}
    public double coeff_achat() {return universe.coeff_achat(ESTI());}
    public double coeff_vente() {return universe.coeff_vente(ESTI());}
    public double multiplicateur_res() {return universe.multiplicateur_res(RESF());}
    public double chance_magique() {return universe.chance_magique(MF());}
    public double chance_rare() {return universe.chance_rare(RF());}
    public double chance_qualite() {return universe.chance_qualite(QALF());}
    public double quantite_drop() {return universe.quantite_drop(QTYF());}
    public double multiplicateur_or() {return universe.multiplicateur_or(GF());}
    public double puissance_ench_inf() {return universe.puissance_ench_inf(POWF());}
    public double puissance_ench_sup() {return universe.puissance_ench_sup();}
    public double ed_versus_tag(int tag) {return universe.ed_specific_monster(stats_with_bonus[22+tag],tag);}
    public double chance_fuite() {return universe.chance_fuite(FLEE());}
    public double temps_fuite() {return universe.temps_fuite(FLEE_SPD());}
    public double temps_craft() {return universe.temps_craft(CRAFT_SPD());}
    public double initiative() {return universe.initiative(INIT());}
	public double proba_immunite_final() {return universe.proba_immunite_final(IMUN_FINAL());}
    public double facteur_temps() {return universe.facteur_temps(TIME_SPD());}
    public double epines() {return universe.epines(EPIN());}
    public double represailles() {return universe.represailles(REP());}
    public double necrophagie() {return universe.necrophagie(NECRO());}
    public double rendement() {return universe.rendement(CRAFT_REND());}
    public double economie_orbe() {return universe.economie_orbe(ECO_ORB());}
    public double multiplicateur_niveau_boutique() {return universe.multiplicateur_niveau_boutique(SHOP_LEVEL());}
    public double taille_boutique() {return universe.taille_boutique(SHOP_SIZE());}
	public double detection_piege() {return universe.detection_piege(TRAP_DET());}
	public double bonus_initiative_piege() {return universe.bonus_initiative_piege(TRAP_INIT());}
	public double initiative_piege() {return universe.initiative(INIT()*universe.bonus_initiative_piege(TRAP_INIT()));}
	public double bonus_resistance_vs_piege() {return universe.bonus_resistance_vs_piege(TRAP_RES());}
	public double resistance_vs_piege() {return universe.reduc(REDUC()*universe.bonus_resistance_vs_piege(TRAP_RES()));}
	public double rente_par_rencontre() {return universe.rente_par_rencontre(RENTE());}
	public double points_par_niveau() {return universe.points_par_niveau(EDUC());}	
	public double points_totaux() {return points_par_niveau()*level + universe.points_initiaux() + universe.points_competence_orbes(orbes_investits_en_points_competence);}
	public double points_a_distribuer() {return points_totaux()-points_distribues();}
	public double points_divins_a_distribuer() {return points_divins_totaux()-points_divins_distribues();}
	public double bonus_xp() {return universe.bonus_xp(BONUS_XP());}
	public double modif_exp_lvl(double levdiff) {return universe.modif_exp_lvl(BONUS_LOWLEV(), BONUS_HLEV(), levdiff);}
	public double proba_trouver_piege(double hidden_lvl) {return universe.proba_trouver_piege(TRAP_DET(), hidden_lvl);}
    public double penalty_reduction() {return universe.penalty_reduction(REDUC_PEN());}
	public double max_zone_level() {return level*universe.zone_multiplier(ZONE_ACCESS());}
	public double points_divins_totaux() {return universe.points_divins_totaux(DIEU(),orbes_investits_en_points_divins,level);}
	public double penalty_for_bad_material() {return -penalty_reduction()*universe.penalty_for_bad_material();}
	public double multi_premier_coup() {return universe.multi_premier_coup(FIRST_STRIKE());}
	public double divine_cap_eq() {return universe.divine_cap_eq(EQ_MASTER());}
	public double divine_cap_const() {return universe.divine_cap_const(CONST_MASTER());}
	public double resources_weight_multiplier() {return universe.resources_weight_multiplier(LIGHTER_RES());}
	public double equipment_weight_multiplier() {return universe.equipment_weight_multiplier(LIGHTER_EQP());}
	public double cold_resistance() {return universe.resistance_froid(COLD_RES());}
	public double heat_resistance() {return universe.resistance_chaud(HOT_RES());}
	public double precipitation_resistance() {return universe.resistance_precipitations(PRECI_RES());}
	public double cold_affinity() {return universe.affinite_froid(COLD_BONUS());}
	public double heat_affinity() {return universe.affinite_chaud(HOT_BONUS());}
	public double precipitation_affinity() {return universe.affinite_precipitations(PRECI_BONUS());}
	public double overload_resistance() {return universe.resistance_surcharge(OVERLOAD_RES());}
	public double underload_affinity() {return universe.affinite_souscharge(UNDERLOAD_BONUS());}
	public double achievements_affinity() {return universe.affinite_hautfaits(ACHI_BONUS());}
	public double clearance_sale_inventory_multiplier() {return universe.clearance_sale_inventory_multiplier(SHOPPING_ADDICT());}
	public double discount_multiplier() {return universe.discount_multiplier(DISCOUNT_SPEC());}
	public double bonus_haut_faits() {return universe.bonus_haut_faits_base(points_haut_faits*achievements_affinity());}
	
	public double divine_cap(int i){
		if(i < universe.nb_universe_stats) return universe.divine_cap[i] * universe.divine_cap_const(CONST_MASTER());
		else return universe.divine_cap[i] * universe.divine_cap_eq(EQ_MASTER());
	}
	
	public void auto_dist()
	{
		double sum = 0.0;
		for(int i=0; i<nb_stats; i++)
			{sum += auto_dist_coeff[i];}
		if (sum<=0.0001) return;
		
		double toAdd = points_a_distribuer();	
		for(int i=0; i<nb_stats; i++)
		{
			stats[i] += Math.floor(toAdd*auto_dist_coeff[i]/sum); 
		}
		refresh_stats_with_bonus();
	}
	
	public int niveau_boutique_base() 
	{ if(zone >= 2) 
		return (int)(universe.get_zone_max_level(zone)*universe.niveau_boutique_pour_niveau_zone());
	  else
		return 50*(zone+1); // Marchands peu puissants dans les arènes
	}
		
	
	public void auto_equip()
	{
		ArrayList<ArrayList<Item>> ai = new ArrayList<ArrayList<Item>>();
		for (int i=0; i< StaticItem.nb_pos-1; i++)
		{
			ai.add(new ArrayList<Item>());
		}
	
		for (Item it: inventory)
		{
			if(it.pos < StaticItem.nb_pos-1)
			{
			it.equiped = false;
			ai.get(it.pos).add(it);
			}
		}
		
		for (int i=0; i< StaticItem.nb_pos-1; i++)
		{
			if(ai.get(i).size() > 0)
			{
				Collections.sort(ai.get(i), new ComparateurStats());
				ai.get(i).get(0).equiped = true;
			}
		}
	}
	
    public void personal_wait(double time_w, int ACT)
    {
	try
	    {
		if (Game.MW != null) Game.MW.mustRefreshCurves = true;
		double adjusted_time = time_w/facteur_temps();
		temps_total += adjusted_time;
		t_stats.addActivity(adjusted_time,ACT);
		if(Game.REAL_TIME) Thread.currentThread().sleep((int)(adjusted_time*1000));
	    }
	catch (Exception e)
	    {
	    }
	//Game.MW.refreshInFight();
    }

	public void addDefaultRules()
	{
		// Inventory filter rules
		ObjectRule tmp;
		tmp = new ObjectRule(4,2,0.0,ObjectRule.ITEM_RULE);
		tmp.name = Local.ALL;
		tmp.filter_rule = true;
		tmp.pickup_rule = true;
		tmp.system_rule = true;
		rules.add(tmp);
		tmp = new ObjectRule(17,0,1,ObjectRule.ITEM_RULE);
		tmp.name = Local.EQUIPPED;
		tmp.filter_rule = true;
		rules.add(tmp);

		tmp = new ObjectRule(tmp, false);
		tmp.name = Local.NOT_EQUIPPED;
		tmp.filter_rule = true;
		rules.add(tmp);

		// Monster rules
		tmp = new ObjectRule(0,2,5,ObjectRule.MONSTER_RULE);
		//tmp.avoid_rule = true;
		tmp.name = Local.MONSTER_TOO_STRONG; rules.add(tmp);
		tmp = new ObjectRule(0,1,1,ObjectRule.MONSTER_RULE);
		tmp.avoid_rule = true;
		tmp.name = Local.MONSTER_TOO_WEAK; rules.add(tmp);

		// Shopping and sell rules
	/*	tmp = new ObjectRule(2,1,2,ObjectRule.PLAYER_RULE);
		tmp.shopping_rule = true;
		tmp.name = "Inventaire plein"; rules.add(tmp);*/
	/*	tmp = new ObjectRule(1,0,0,ObjectRule.ITEM_RULE);
		tmp.sell_rule = true;
		tmp.name = "Vente des déchets"; rules.add(tmp);*/

		for (int i=0; i< StaticItem.nb_pos-1 ; i++)
		{
			tmp = new ObjectRule(2,0,i,ObjectRule.ITEM_RULE);
			tmp.name = Local.SLOT_NAME[i];
			tmp.filter_rule = universe.slot_est_disponible(i);
			rules.add(tmp);
		}
		
		for (int i=0; i< Local.RARITY_NAME.length ; i++)
		{
			tmp = new ObjectRule(0,0,i,ObjectRule.ITEM_RULE);
			String name = Local.RARITY_NAME[i];
			tmp.name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
			tmp.filter_rule = true;
			rules.add(tmp);
		}
		
		//	for (int i=0; i< rules.size() ; i++)
		//		System.out.println(rules.get(i).desc());

	}
public Player()
    {}
	
    public Player(Universe u, boolean light)
    {
	universe = u;
	
	if(!light)
	{
	disp=true;
	orbes_investits_en_points_divins = 0;
	orbes_investits_en_points_competence = 0;
	temps_total = 0;
	t_stats = new TimeStats();
	conditionToggle = new boolean[]{true,true,true,true,true};
	conditionValues = new int[]{1,1,1,1,1};
	
	shop = null;
	mob = null;
	craftInventory = new ArrayList<Item>();
	inventory = new ArrayList<Item>();
	rules = new ArrayList<ObjectRule>();
	addDefaultRules();

	zone = 2;
	level = 1;
  	xp_pt = 0;
	refresh_weather_penalties();
	
	tags = new boolean[nb_tags];
	for(int t=0; t<nb_tags; t++) tags[t]=false;
	tags[2]=true; // Humain
	
	name = "Mortadelle";
	stats = new double[nb_stats];
	stats_with_bonus = new double[nb_stats];
	item_bonus = new double[nb_stats];
	auto_dist_coeff = new int[nb_stats];
	
	for(int i=0; i<nb_stats; i++) 
		{stats[i]=0; stats_with_bonus[i]=0; item_bonus[i]=0; auto_dist_coeff[i]=10;}
	refresh();
	}
	else
	{
		shop = null;
		mob = null;
		defi = null;
	}
    }

    public String short_stats()
    {
	String res = String.format("%s\n",name);
	res += String.format(Local.LEVEL_N,level);
	for(int i=0; i<dsp_target.length; i++) 
	{
		double bonus = stats_with_bonus[dsp_target[i]];
		if(bonus > 0.01)
		res += String.format("%s"+ Local.COLON + " %g\n",stats_name[dsp_target[i]],bonus);
	}
	return res;
    }

	// Utilisé si DEBUG
    public void giveStuff()
    {
	Item it = new Item(300,this,Item.ITEM_SHOP);
	
	for (int i=1;i<500;i+=1)
	    {
		it = new Item(i,this,Item.ITEM_DROP);
		if(it.stackable) it.set_qty(5.0);
		inventory.add(it);
		}
	
	for(int j=0; j<StaticItem.ORB.length; j++)	
	    {
		it = new Item(StaticItem.ORB[j],StaticItem.RESSOURCE_ORB);
		it.set_qty(1.0e20);
		inventory.add(it);
	    }
	
	boolean[] posTaken = new boolean[StaticItem.nb_pos];
	
	for(int i=0; i< StaticItem.nb_pos-1; i++)
		if (universe.slot_est_disponible(i)==false) 
			posTaken[i] = true;
	posTaken[StaticItem.nb_pos-1]=true;
	
	for(int j=0; j<20; j++)	
		{
		for(int tr=0; tr<20; tr++)
		{
			it = new Item(300,this,Item.ITEM_SHOP);
			if(posTaken[it.pos]==false) break;
		}
		posTaken[it.pos]=true;
		if (it.rare == 0)
		{
		it.elvl = 300;
		it.transform_magic(this);
		it.material = StaticItem.MA[100];
		it.rare = 3;
		for(int i=0; i<Player.nb_stats; i++)
		    {
			it.bonus[i]=puissance_ench_sup()*it.elvl;
		    }
		it.quality = universe.qualite_max();
		it.update();
		inventory.add(it);
		}
		}
		refresh_charge();
    }
	
	public void update_encounters()
	{
		double tt = universe.plage_random()*temps_traque();
		if(disp) Game.MW.addLog(String.format(Local.LOOKING_FOR_AN_ENNEMY,tt));
		personal_wait(tt,TimeStats.ACTIVITY_CHERCHE_ENNEMI);
		shop = null;
		nb_encounters++;
		if(nb_encounters%20 == 1 ) update_weather();
		if(nb_encounters%50 == 49 ) update_rente();
	}

	public void  update_rente()
	{
		double rente_par_rencontre = rente_par_rencontre();
		double fric = rente_par_rencontre*50;
		if (fric > 0.01)
		{
		money_gain(fric, TimeStats.GAIN_RENTE);
		if(disp) Game.MW.addLog(String.format(Local.LIFE_ANNUITY,name,fric,50.0,rente_par_rencontre));
		}
	}
	
	public void update_weather()
	{
	String zone_name = universe.map.zonesName.get(zone);
	
	double precipitation = universe.get_precipitation(zone);
	double current_precipitation = universe.get_current_precipitation(zone);
	double current_precipitation_modifier = universe.map.current_precipitation_modifier.get(zone);
	
	double temperature = universe.get_temperature(zone);
	double current_temperature = universe.get_current_temperature(zone);
	double current_temperature_modifier = universe.map.current_temperature_modifier.get(zone);

	double mdiff, tdiff, new_temperature;
	if(Math.abs(precipitation)<0.001)
	{
		precipitation_strength = 0.0;
		mdiff = 0.4*Math.random()-0.20;
		
		universe.map.current_temperature_modifier.set(zone,current_temperature_modifier+mdiff);
		new_temperature = universe.get_current_temperature(zone);
		tdiff =  new_temperature - current_temperature;
		if(disp)
		{
			if(tdiff > 0) Game.MW.addLog(String.format(Local.NO_PRECIPITATIONS, zone_name, 100.0, tdiff, new_temperature));
			else Game.MW.addLog(String.format(Local.NO_PRECIPITATIONS_NEG, zone_name, 100.0, -tdiff, new_temperature));
		}
	}
	else if(Math.random()< current_precipitation)
		{
		String type="";
		mdiff = 1.0*Math.random()+0.5;
		universe.map.current_precipitation_modifier.set(zone,current_precipitation_modifier-mdiff);
		universe.map.current_temperature_modifier.set(zone,current_temperature_modifier-mdiff);
		new_temperature = universe.get_current_temperature(zone);
		tdiff =  new_temperature - current_temperature;
		
		if(temperature <=0.0 && Math.random() < 0.5)
			{
			precipitation_strength = 2.0+1.0*Math.random();
			type = Local.SNOW;
			}
		else if (temperature <=0.0)
			{
			precipitation_strength = 2.0+2.0*Math.random();
			type = Local.HAIL;
			}
		else
			{
			precipitation_strength = 1.0+0.5*Math.random();
			type = Local.RAIN;
			}
		if(disp) Game.MW.addLog(String.format(type, zone_name,precipitation_strength, 100*current_precipitation, -tdiff, new_temperature));
		}
	else
		{
			precipitation_strength = 0.0;
			mdiff = 1.0*Math.random()+0.5;
			
			universe.map.current_precipitation_modifier.set(zone,current_precipitation_modifier+mdiff);
			universe.map.current_temperature_modifier.set(zone,current_temperature_modifier+mdiff);
			new_temperature = universe.get_current_temperature(zone);
			tdiff =  new_temperature - current_temperature;
		
			if(disp) Game.MW.addLog(String.format(Local.NO_PRECIPITATIONS, zone_name, 100-100*current_precipitation, tdiff, new_temperature));
		}
		
	/*System.out.println("Start: temperature_modifier="+current_temperature_modifier+" precipitation_modifier="+current_precipitation_modifier);
	System.out.println("current_temperature="+current_temperature);
	System.out.println("mdiff="+mdiff +" new_temperature="+new_temperature);
	System.out.println("new_temperature_modifier="+universe.map.current_temperature_modifier.get(zone));*/
	refresh_weather_penalties();
	}
	
	public double get_mob_level()
	{
		double zl = universe.get_zone_level(zone);
		double diff = universe.get_zone_max_level(zone)-zl+1;
		double zlevel = zl + Math.floor(Math.random() * diff);
		return zlevel;
	}
	
    // rencontre aléatoire
	// peut être précédée par un piège
    public void get_mob()
    {
	double mob_level = get_mob_level();
	t_stats.addEvent(1.0,TimeStats.EVENT_FIND_MONSTER);
	mob = new Monster(mob_level,universe,zone);
    }

    // trouver un marchand
    public void get_shop()
    {
	if(shop == null)
	    {
		double ts = temps_shop();
		double tt = universe.plage_random()*ts;
		if(disp) Game.MW.addLog(String.format(Local.LOOKING_FOR_A_TRADER,tt));
		t_stats.addEvent(1.0,TimeStats.EVENT_FIND_SHOP);
		personal_wait(tt,TimeStats.ACTIVITY_CHERCHE_MARCHAND);
		shop = new Shop(this);
		if(disp) Game.MW.addLog(String.format(Local.ENCOUNTER,name,shop.name,shop.level));
	    }
    }

    // trouver une forge
    public void get_forge()
    {
	double tt = universe.plage_random()*temps_forge();
	if(disp) Game.MW.addLog(String.format(Local.LOOKING_FOR_A_MYSTIC_FORGE,tt));
	t_stats.addEvent(1.0,TimeStats.EVENT_FIND_FORGE);
	personal_wait(tt,TimeStats.ACTIVITY_CHERCHE_FORGE);
    }

    public String cogne(Player p, boolean addStat)
    {
	double t_att = universe.plage_random() * (1.0/att_per_sec()); // Durée de l'attaque
	temps += t_att;
	String res = String.format(Local.A_HITS_B,this.name,p.name,t_att,this.name,temps);

	double esquive, critique, multiplicateur1, multiplicateur2, percant;
	double dmg, dmg_base, dmg_abs, dmg_red;
	double dmg_base2, dmg_red2;
	
	double tmp;
	
	esquive = universe.esquive_proba(p.ESQ(),PRC());

	if (Math.random() < esquive)
	    {
		res+=String.format(Local.DODGES_THE_HIT,p.name,this.name,100.0*esquive);
	    }
	else
	    {
		if(addStat) t_stats.addEvent(1.0,TimeStats.EVENT_ATTACK_SUCCESS);
		multiplicateur1 = 1.0;
		multiplicateur2 = 1.0;

		String cause = "";
		for(int t=0; t<nb_tags; t++)
		{
			if (p.tags[t] && (tmp = ed_versus_tag(t)) > 1.001)
			{
				multiplicateur1 =  multiplicateur1 * tmp;
				res+=String.format(Local.MULTIPLIER,tmp,TagsName[t]);
			}
		}
		if(p.nb_hits == 0 && (tmp=multi_premier_coup()) > 1.001) {
			multiplicateur1 =  multiplicateur1 * tmp;
			res+=String.format(Local.MULTIPLIER,tmp,Local.FIRST_STRIKE);
			}
		critique = crit_proba();
		if (Math.random() < critique)
		    {
			multiplicateur2 = multi_crit();
			res+=String.format(Local.CRITICAL_STRIKE,multiplicateur2,100.0*critique);
			p.crit_taken++;
		    }

		dmg_base = universe.plage_random() * dmg_base() * multiplicateur1 * multiplicateur2;
		dmg_red = p.reduc();
		dmg_abs = p.absorption();
		dmg = Math.max(dmg_base*dmg_red - dmg_abs,0.0); // pas de dégâts négatifs

		res+=String.format(Local.DAMAGE_INFLICTED,this.name,dmg,p.name,dmg_base,100*(1.0-dmg_red),dmg_abs);

		if(dmg >= p.vie && p.vie > 0.1 && Math.random() < (tmp = p.proba_immunite_final()))
		{
			res+=String.format(Local.IMMUNITY_TO_FINAL_BLOW, 
				p.name, tmp*100.0);
			p.vie = 0.1;
		}
		else
		{
			p.vie -= dmg;
		}
		
		dmg_base2 = p.universe.plage_random() * p.epines();
		dmg_red2 = reduc();
		if(dmg_base2*dmg_red2 > 0.01)
		    {
				res+=String.format(Local.THORNS,p.name,dmg_base2*dmg_red2,this.name,dmg_base2,100*(1.0-dmg_red2));
				this.vie -= dmg_base2*dmg_red2;
		    }
		double rep = p.represailles() * dmg;
		if(rep*dmg_red2 > 0.01)
	    	{
				res+=String.format(Local.REPRISALS,p.name,rep*dmg_red2,this.name,rep,100*(1.0-dmg_red2));
				this.vie -= rep*dmg_red2;
	    	}
							
		double vdv = Math.min(this.vie_max()-this.vie(),dmg*vol_de_vie());
		if (vdv > 0.01)
		    {
		    res+=String.format(Local.LIFE_LEECH_EFFECT,this.name,vdv);
		    this.vie += vdv;
		    }
		p.nb_hits++;
	    }
	return res;
    }

    public void refresh()
    {
	vie=vie_max();
	temps = 0;
    }

    public boolean combat(boolean real)
    {
	if(real) t_stats.addEvent(1.0,TimeStats.EVENT_FIGHT_ATTEMPT);
	boolean has_flee = false;
	crit_taken = 0;
	boolean lost;
	Player p2 = mob;
	if(disp) Game.MW.addLog(String.format(Local.A_VERSUS_B, name, p2.name,p2.level));

	String tmp;
	Player t1,t2;
	double i1,i2;
	i1 = universe.plage_random()*initiative();
	i2 = p2.universe.plage_random()*p2.initiative();
	temps += i1;
	p2.temps += i2;

	double tmp_temps = temps;
	if(real) personal_wait(i1,TimeStats.ACTIVITY_INITIATIVE);

	if (i1<i2) {t1=this; t2=p2;}
	else {t1=p2; t2=this;}
	if(disp) Game.MW.addLog(String.format(Local.INITIATIVES,i1,name,i2,p2.name));
	t1.nb_hits = t2.nb_hits = 0;
	
	while (vie() >0 && p2.vie() >0)
	    {
		if(this == t1 && real && must_flee())
		    {
			double t_att = universe.plage_random()*temps_fuite(); // Durée de la fuite
			temps += t_att;
			if(real) 
			{
				personal_wait(t_att,TimeStats.ACTIVITY_FUITE);
				t_stats.addEvent(1.0,TimeStats.EVENT_FLEE_ATTEMPT);
			}
			
			if(disp) Game.MW.addLog(String.format(Local.TRY_TO_FLEE,name,p2.name,t_att));
			if(Math.random() < chance_fuite())
			    {
				if(disp) Game.MW.addLog(String.format(Local.FLEE_SUCCESS,100*chance_fuite()));
				if(real) t_stats.addEvent(1.0,TimeStats.EVENT_FLEE_SUCCESS);
				has_flee = true;
				break;
			    }
			else
			    {
				if(disp) Game.MW.addLog(String.format(Local.FLEE_FAIL,100-100*chance_fuite()));
			    }
		    }
		else
		    {
			tmp = t1.cogne(t2, (real && this == t1));
			if(disp) Game.MW.addLog(tmp);
		    }
		if(real && this == t1) 
		{
			personal_wait(temps-tmp_temps,TimeStats.ACTIVITY_COGNE);
			t_stats.addEvent(1.0,TimeStats.EVENT_ATTACK_ATTEMPT);
		}
		
		tmp_temps = temps;
		if(temps < p2.temps) {t1=this; t2=p2;}
		else {t1=p2; t2=this;}
	    }
	if(vie() >0) // victoire ou fuite
	    {
		if(has_flee){
		    if(disp) Game.MW.addLog(String.format(Local.END_FIGHT_FLEE,name,p2.name,temps,vie()));
		    lost = true;
		}
		else{
		    if(disp) Game.MW.addLog(String.format(Local.END_FIGHT_KILL,name,p2.name,temps,vie()));
			if(real)
			{
				t_stats.addEvent(1.0,TimeStats.EVENT_FIGHT_SUCCESS);
				gain_xp(universe.xp_for_level(p2.level), TimeStats.XP_MONSTER, p2.level);
				if (zone >= 2) loot((Monster)p2); // NO LOOT IN ARENA
			}
		    lost = false;
		}
	    }
	else
	    {
		if(disp) Game.MW.addLog(String.format(Local.END_FIGHT_DEATH,name,p2.name,p2.level,temps));
		if(real) 
		{
			t_stats.addEvent(1.0,TimeStats.EVENT_FIGHT_DEATH);
			meurt();
		}
		lost = true;
	    }
	temps = 0;
	return lost;
    }

    private boolean pickupCond(Item i)
    {
	for (ObjectRule r: rules)
	{
		if(r.pickup_rule && r.IsTrue(this,i,mob))
			return true;
	}
	return false;
    }
		
    public void loot(Monster p2)
    {
	ArrayList<Item> loot = p2.drop(this);
	double fric = universe.plage_random() * universe.gold_drop(p2.level) * multiplicateur_or();
	
	if(fric < 1.0) fric = 0;
	if (loot.isEmpty() && fric <= 0.1) 
		{if(disp) Game.MW.addLog(String.format(Local.NO_LOOT, p2.name));}
	else
	    {
		money_gain(fric, TimeStats.GAIN_DROP);
		if(disp) Game.MW.addLog(String.format(Local.GOLD_LOOT,name, fric, p2.name));
		String lootStr = "";
		String dontLootStr = "";
		for(Item the_object : loot)
		    {
			if(the_object.rare == 0) 
				t_stats.addEvent(1.0,TimeStats.EVENT_DROP_NORMAL);
			else if(the_object.rare == 1) 
				t_stats.addEvent(1.0,TimeStats.EVENT_DROP_MAGIC);
			else if(the_object.rare == 2) 
				t_stats.addEvent(1.0,TimeStats.EVENT_DROP_RARE);
			else if(the_object.rare == 4 || the_object.rare == 5) 
				t_stats.addEvent(the_object.qty,TimeStats.EVENT_DROP_MAT);
			else if(the_object.rare == 6)
				t_stats.addEvent(the_object.qty,TimeStats.EVENT_DROP_ORB);
			
			if (pickupCond(the_object))
			    {
				if(lootStr != "") lootStr += ", ";
				lootStr += NameGenerator.firstCharLowercase(the_object.name);
				charge += the_object.poids_final(this);
				inventory.add(the_object);
			    }
			else
				{
				if(dontLootStr != "") dontLootStr += ", ";
				dontLootStr += NameGenerator.firstCharLowercase(the_object.name);
				}
		    }
		if(lootStr != "" && disp)
			Game.MW.addLog(String.format(Local.ITEMS_LOOT,name,lootStr, p2.name));
		if(dontLootStr != "" && disp)
			Game.MW.addLog(String.format(Local.OBJECTS_LEFT_BEHIND, dontLootStr));
	    clean_list(inventory);
		}
		
	double nec = necrophagie()*p2.vie_max();
	double to_heal = Math.min(vie_max()-vie,nec);
	if(to_heal > 0.01)
		{
		if(disp) Game.MW.addLog(String.format(Local.NECROPHAGY, name, p2.name,to_heal));
		vie = vie+to_heal;
		}
    }

    public void meurt()
    {
	double pen_reduc = penalty_reduction();
	double res = temps_res();
	double perte = money*universe.base_gold_penalty_for_death()*pen_reduc;
	
	if(disp) Game.MW.addLog(String.format(Local.DEATH_GOLD_LOSS, name, pen_reduc, 100*universe.base_gold_penalty_for_death(), perte));
	money_loss(perte, TimeStats.LOSS_DEATH);
	
	double bp = universe.base_penalty_for_death();
	if(disp) Game.MW.addLog(String.format(Local.DEATH_TIME_PENALTY, bp*pen_reduc, pen_reduc,bp));
	personal_wait(bp*pen_reduc,TimeStats.ACTIVITY_PENALTY);
		
	if(disp) Game.MW.addLog(String.format(Local.RESURRECTION_TIME,res));
	vie = vie_max();
	personal_wait(res,TimeStats.ACTIVITY_RESURRECTION);
    }

    public void reset()
	{
	vie = vie_max();
	temps = 0;
	}
	
    public void heal()
    {
	double hp_tg = vie_max()-vie;
	double res = hp_tg / regen();
	if (hp_tg > 0.001)
	    {
		vie = vie_max();
		if(disp) Game.MW.addLog(String.format(Local.FULL_HEAL,hp_tg,res));
		personal_wait(res,TimeStats.ACTIVITY_HEAL);
	    }
    }

    public double next_level()
    {
	return xp_level(level+1);
    }

    public boolean must_flee()
    {
		for (ObjectRule r: rules)
			{
				if(r.flee_rule && r.IsTrue(this,null,mob))
					return true;
			}
		return false;
    }

	
    public double xp_level(double x)
    {
	if (x<=1.1) return 0;
	return 1000.0+((x-1.0)*(x-2.0)*1000.0);
    }

	public double level_for_xp(double x)
    {
	if(x < 1000.0) return 1;
	else return Math.floor(Math.min((1.5 + (0.5/Math.sqrt(250.0)) * Math.sqrt(x - 750)),MAX_LEVEL));
	}
	
    public void gain_xp(double sx,int type, double gainLevel)
    {
	double x = Math.floor(sx*bonus_xp()*modif_exp_lvl(gainLevel-level));
	xp_pt+=x;
	t_stats.addXp((double)x, type);
	if(disp) Game.MW.addLog(String.format(Local.EARN_EXPERIENCE,name,x,sx,bonus_xp(),modif_exp_lvl(gainLevel-level)));

	if (level == MAX_LEVEL) return;
	double levelback=level;
	level = level_for_xp(xp_pt);
	
	if(levelback != level)
		{
		refresh();
	    if(disp) Game.MW.addLog(String.format(Local.LEVEL_UP,name,levelback,level));
		if(disp) Game.MW.DistWindow.refresh();
		}
    }

    public void split(Item i, ArrayList<Item> dest)
    {
	i.set_qty(i.qty/2.0);
	Item rit = new Item();
	rit.copy(i);
	rit.update();
	dest.add(rit);
    }


	public void craft()
	{
		if (craftInventory.size() == 0) return;

		ArrayList<Item> rlist = new ArrayList<Item>();
		double time = Item.CraftItem(craftInventory, rlist, this);
		craftInventory = rlist;
		double tt = time*temps_craft();
		personal_wait(tt,TimeStats.ACTIVITY_CRAFT);
		t_stats.addEvent(1.0,TimeStats.EVENT_CRAFT);
	}

	public void changer_defi()
	{
		
		double bp = universe.base_penalty_for_new_challenge();
		double pen_reduc = penalty_reduction();
		if(disp) Game.MW.addLog(String.format(Local.CHALLENGE_CHANGE, bp*pen_reduc, pen_reduc,bp));
		personal_wait(bp*pen_reduc,TimeStats.ACTIVITY_PENALTY);
		jeu_fini = false;
	}
	
	public void victory()
	{
		jeu_fini = true;
		if(disp) Game.MW.addLog(String.format(Local.TIME_PASSED,temps_total));
		Game.HI = HiScore.loadScore();
		Game.HI.addScore(new Score(defi.name,name,temps_total,universe.seed),disp);
	}
	
	public void end_game()
	{
		if (jeu_fini)
		{
			if(disp) Game.MW.addLog(String.format(Local.YOU_HAVE_ALREADY_FINISHED_THE_GAME,name));
		}
		else if(defi.isCond() && defi.isTrue(this,disp))
		{
			victory();
		}
		else if(!defi.isCond())
		{
			if(disp) Game.MW.infight = true;
			Thread thread = new Thread() {
				public void run() {
					mob = new Monster(defi.boss_name, defi.boss_level, defi.boss_tag, defi.boss_p_stats, universe);
					if(disp) Game.MW.refreshButtons();
					
					if(!combat(true)) 
						{
							if(disp) Game.MW.addLog(String.format(Local.YOU_HAVE_DEFEATED_THE_FINAL_BOSS,name));
							victory();
						}
					heal();
					if(disp) Game.MW.infight = false;
					if(disp) Game.MW.refreshButtons();
				}};
			thread.start();
		}
	}
	
	public void changer_univers()
	{
		Universe new_u = new Universe(universe.seed+1);
		new_u.joueur = this;
		new_u.numberOfTravels = universe.numberOfTravels+1;
		universe = new_u;
		zone = 2;
		nb_encounters = 0;
		refresh_weather_penalties();
		Monster.SetOptimalDistribution(universe);
		StaticItem.init(universe);

		double bp = universe.base_penalty_for_dimensional_travel();
		double pen_reduc = penalty_reduction();
		if(disp) Game.MW.addLog(String.format(Local.UNIVERSE_CHANGE, bp*pen_reduc, pen_reduc,bp));
		personal_wait(bp*pen_reduc,TimeStats.ACTIVITY_PENALTY);
	}
	
public void reset_build()
    {
	Game.MW.addLog(Local.RESET_BUILD);
	for(int i=0; i<nb_stats; i++) stats[i]=0; 
	Game.MW.DistWindow.refresh();
    }

}
