import java.util.ArrayList;
import java.io.Serializable;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Player implements Serializable {
    static private final long serialVersionUID = 100;
    //***********************
	public static double points_initiaux = 20;
	public boolean jeu_fini = false;
    public static String[] TagsName = 
		{"Mort-vivant","Animal","Humain","Peau-verte","Démon","Champion","Piège"}; // nom des tags 
	
	public static int nb_tags = TagsName.length; // nombre de statistiques
   
    public boolean[] tags; // Propriétés

    public String name; // Nom du joueur (ou du monstre)
	public Challenge defi;
    public double[] stats; // statistiques
    public double[] stats_with_bonus; // for fast access
    public double[] item_bonus; // for fast access, used only in LevlUp2

    // IAS(0) DMG(1) REDUC(2) ABS(3) ESQ(4) PRC(5) LCK(6) CRT(7)
    // VDV(8) VITA(9) CON(10) REGEN(11) RESUR(12) LOAD(13) RUN(14) 
    // RESF(15) MF(16) RF(17) QALF(18) QTYF(19) POWF(20) GF(21)
    // ED_MV(22) ED_ANI(23) ED_HUM(24) ED_PV(25) ED_DEM(26) ED_CHAMP(27)
    // ESTI(28) FLEE(29) FLEE_SPD(30) INIT(31) BONUS_XP(32) TIME_SPD(33) 
	// EPIN(34) REP(35) NECRO(36) CRAFT_SPD(37) CRAFT_REND(38) ECO_ORB(39) 
	// SHOP_LEVEL(40) SHOP_SIZE(41) TRAP_DET(42) TRAP_INIT(43) TRAP_RES(44)
	// RENTE(45) EDUC(46)

    public static String[] stats_name = {"Vitesse d'attaque", "Dégâts", "Réduction", "Absorption", "Esquive", "Précision", 
		"Chance de coup critique", "Dégâts des coups critiques", "Vol de vie", "Vitalité", "Constitution", "Régénération", 
		"Résurrection", "Encombrement maximal", "Vitesse de marche", "Recherche de ressources","Recherche d'objets magiques",
		"Recherche d'objets rares","Recherche de qualité","Recherche de quantité","Recherche de puissance","Recherche d'or",
		"Dégâts aux morts-vivants","Dégâts aux animaux","Dégâts aux humains","Dégâts aux peaux-vertes","Dégâts aux démons",
		"Dégâts aux champions","Estimation","Chance de fuite","Vitesse de fuite","Initiative","Apprentissage","Vitesse du temps",
		"Épines","Représailles","Nécrophagie", "Vitesse du craft", "Rendement du craft", "Économie d'orbes", 
		"Niveau des marchands", "Inventaire des marchands", 
		"Détection des pièges", "Bonus d'initiative face aux pièges", "Bonus de résistance aux pièges","Rente viagère","Éducation"}; // nom des compétences 


    public boolean[] conditionToggle;
    public int[] conditionValues;   

    public static int nb_stats = stats_name.length; // nombre de statistiques

    public double temps; // temps écoulé depuis le début du combat
    public double temps_total; // temps écoulé depuis le début du jeu
    public double temps_depuis_derniere_rente; // temps écoulé depuis la dernière rente
	
    public double vie; // vie
    public ArrayList<Item> inventory;// inventraire
    public ArrayList<Item> craftInventory; //inventaire de la forge mystique
    public ArrayList<ObjectRule> rules; // régles et filtres d'inventaires

    public double charge;
    public int level;  // niveau
    public double money; // argent
    public int zone; // zone
    public int xp_pt; // exp
    public Shop shop; // the shop
    public Monster mob; // the mob
    public int crit_taken;
    public TimeStats t_stats ;

	public void refresh_charge()
	{
		charge = 0.0;
		for(Item the_object : inventory)
	    {
		charge += the_object.poids;
	    }
	}
	
	public double points_distribues()
	{
		double res = 0;
		for(int i=0; i<nb_stats; i++) res += stats[i];
		return res;
	}
	
	public void toucher_rente()
	{
		double temps_ecoule = temps_total - temps_depuis_derniere_rente;
		double rente_par_seconde = rente_par_seconde();
		double fric = rente_par_seconde()*temps_ecoule;
		
		if (fric > 0.01)
		{
		money_gain(fric, TimeStats.GAIN_RENTE);
		Game.MW.addLog(String.format("Vous recevez %.2f écus de rente (%.2f secondes x %.2f écus/secondes).",fric,temps_ecoule,rente_par_seconde));
		temps_depuis_derniere_rente = temps_total;
		}
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

    public boolean can_buy(Item b)
    {
	return (charge + b.poids <= charge_max()) && 
		(money - coeff_achat()*b.prix() >= 0 || (b.stackable && money > 1.0));
    }

    public void buy(Item b)
    {
	double prix = coeff_achat()*b.prix();
	if(prix <= money)
	{
		shop.inventory.remove(b);
		get_item(b);
		money -= prix;
		Game.MW.addLog(String.format("Achat de %s à %s pour %.2f écus",b.name,shop.name,prix));
	}
	else if(b.stackable)
	{
		double qty_to_buy = (money/prix)*b.qty;
		b.set_qty(b.qty - qty_to_buy);
		Item rit = new Item(b.material,StaticItem.RESSOURCE_CRAFT);
		rit.copy(b);
		rit.set_qty(qty_to_buy);
		get_item(rit);
		Game.MW.addLog(String.format("Achat de %s à %s pour %.2f écus",rit.name,shop.name,money));
		money = 0.0;
	}
    }

	public void money_gain(double gain, int type_gain)
	{
		t_stats.addRevenue(gain, type_gain);
		money += gain;
	}
		
    public void sell(Item b)
    {
	get_shop();
	double prix = coeff_vente()*b.prix();
	deposit_item(b,shop.inventory);
	if(b.rare == 0)
		money_gain(prix,TimeStats.GAIN_SELL_BASE);
	else if(b.rare == 1)
		money_gain(prix,TimeStats.GAIN_SELL_MAGIC);
	else if(b.rare == 2)
		money_gain(prix,TimeStats.GAIN_SELL_RARE);
	else if(b.rare == 4 || b.rare == 5)
		money_gain(prix,TimeStats.GAIN_SELL_MAT);
	else
		money_gain(prix,TimeStats.GAIN_SELL_OTHER);		
	Game.MW.addLog(String.format("Vente de %s à %s pour %.2f écus",b.name,shop.name,prix));
    }

    public Item get_similar(ArrayList<Item> inventory, Item i)
    {
	for(Item the_object : inventory)
	    if (the_object.name.equals(i.name) && the_object.stackable)
		return the_object;
	return null;
    }

    public void put_craft(Item b)
    {
	deposit_item(b,craftInventory);
	Game.MW.addLog(String.format("Vous placez %s dans la forge mystique",b.name));
    }

    public void destroy_item(Item b)
    {
	inventory.remove(b);
	charge -= b.poids;
	if(b.equiped) refresh_stats_with_bonus();
	Game.MW.addLog(String.format("Vous jetez %s",b.name));
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
	charge += b.poids;
    }

    public void deposit_item(Item b, ArrayList<Item> dest)
    {
	inventory.remove(b);
	charge -= b.poids;
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
	Game.MW.addLog(String.format("Vous récupérez %s depuis la forge mystique",b.name));
    }


    public boolean can_get(Item b)
    {
	return (charge + b.poids <= charge_max());
    }



    public void refresh_stats_with_bonus()
    {
	for(int i=0; i<nb_stats; i++) 
		{
		item_bonus[i]=0;
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
    }
	
    public static double att_per_sec(double IAS) {return Math.pow(IAS+10,0.50)-2.16227766017;}
    public static double pv_per_vita(double CON) {return Math.pow(CON+10,0.40)-1.51188643151;}
    public static double vie_max(double VITA, double CON) {return 5.0 + pv_per_vita(CON)*VITA;}
    public static double crit_proba(double LCK) {return (2.0+LCK)/(LCK+120.0);}
    public static double multi_crit(double CRT) {return Math.pow(CRT+5.0,0.6)-1.3265278;}
    public static double dmg_base(double DMG) {return 1.2*Math.pow(DMG+0.7,0.7);}
    public static double reduc(double REDUC) {return 1.0/Math.sqrt(1.0+REDUC/4.0);}
    public static double absorption(double ABS) {return Math.pow(ABS+7,0.80)/4.7432763938 -1.0;}
    public static double esquive_proba(double ESQ,double PRC) {return (ESQ+20.0)/(ESQ+PRC+80.0);}
    public static double vol_de_vie(double VDV) {return Math.pow(VDV,0.75)/(Math.pow(VDV,0.75)+200.0);}
    public static double regen(double REGEN, double CON) {return 2.0 + (pv_per_vita(CON) * REGEN)/1.5;}
    public static double charge_max(double LOAD) {return 6.0*Math.pow(LOAD+6.0,0.80)-0.15777627577;}
    public static double temps_traque(double RUN) {return 5.25771612961 / Math.pow(RUN+6.0,0.70);}
    public static double temps_shop(double RUN) {return 11.5912359471 / Math.pow(RUN+8.0,0.65);}
    public static double temps_forge(double RUN) {return 15.4549812628 / Math.pow(RUN+8.0,0.65);}
    public static double temps_res(double RESUR, int niveau) {return 40.0 / (3.0+Math.pow((4.0*RESUR+4.0)/(niveau),0.70));}
    public static double coeff_achat(double ESTI) {return 1.0 + 6.0 / (10.0+ESTI);}
    public static double coeff_vente(double ESTI) {return (10.0+ESTI) / (20.0+ESTI);}
    public static double multiplicateur_res(double RESF) {return (Math.pow(RESF+30.0,0.50) - 4.47722558)/4.0;} 
    public static double chance_magique(double MF) {return (12.0+MF)/(MF+180.0);}
    public static double chance_rare(double RF) {return 1.0 - 6.0/Math.pow(40.0+RF,0.50);}
    public static double chance_qualite(double QALF) {return 1.0 - 3.6/Math.pow(30.0+QALF,0.40);}
    public static double quantite_drop(double QTYF) {return 0.3*Math.pow(QTYF+8.0,0.50);} 
    public static double puissance_ench_inf(double POWF) {return 0.25*(1.0 - 2.0/Math.pow(10.0+POWF,0.65));} 
    public static double puissance_ench_sup(double POWF) {return 0.25;} 
    public static double multiplicateur_or(double GF) {return Math.pow(GF+10.0,0.90)/7.94328234724;} 
    public static double ed_specific_monster(double STAT) {return Math.pow(STAT+10.0,0.40) - 1.51188643151;}
    public static double chance_fuite(double FLEE) {return (30.0+FLEE)/(FLEE+100.0);}
    public static double temps_fuite(double FLEE_SPD)  {return 10.0 / (FLEE_SPD+10.0);}
    public static double initiative(double INIT) {return 20.0 / Math.pow(INIT+10.0,0.80);}
    public static double bonus_xp(double BONUS_XP) {return Math.pow(BONUS_XP+10.0,0.20)-0.58489319;}
    public static double facteur_temps(double TIME_SPD) {return Math.pow(TIME_SPD+20.0,0.20)-0.8205642;}
    public static double epines(double EPIN) {return Math.pow(EPIN+6.0,0.75) - 3.833658625477634855132095;}
    public static double represailles(double REP) {return 2.000001-29.7560651785/Math.pow(90.0+REP,0.60);}
    public static double necrophagie(double NECRO) {return Math.pow(NECRO+20,0.80)/20.0-0.54928027164;}
    public static double temps_craft(double CRAFT_SPD) {return 16.0 / Math.pow(CRAFT_SPD+7.0,0.70);}
    public static double rendement(double CRAFT_REND) {return 1.0 - 4.0/Math.pow(12.0+CRAFT_REND,0.90);}
    public static double economie_orbe(double ECO_ORB) {return 9.14610103855/Math.pow(40.0+ECO_ORB,0.60);}
    public static double niveau_boutique(double SHOP_LEVEL) {return 0.8*Math.pow(SHOP_LEVEL+10.0,0.50);}
    public static double taille_boutique(double SHOP_SIZE) {return Math.pow(SHOP_SIZE+8.0,0.65);}
	public static double detection_piege(double TRAP_DET) {return TRAP_DET*0.4;}
	public static double bonus_initiative_piege(double TRAP_INIT) {return Math.pow(TRAP_INIT+10.0,0.40) - 1.51188643151;}
	public static double bonus_resistance_vs_piege(double TRAP_RES) {return Math.pow(TRAP_RES+12.0,0.40)-1.70192007704;}
	public static double rente_par_seconde(double RENTE) {return Math.pow(RENTE+2.0,1.3)*0.1-0.24622888266;}
	public static double points_par_niveau(double EDUC) {return Math.pow(EDUC+20.0,0.28)*2.161133550289;}	

	public String infos()
	{
		double dmpa = dmpa();
		int lvl1=Math.max(level-5,1);
		int lvl2= lvl1+5;
		int lvl3= lvl2+10;
		
		return String.format("Temps total de jeu : %.2f secondes"+
			"\nAttaques par seconde : %.3f"+
			"\nDégâts moyens d'une attaque basique : %.2f"+
			"\nProbabilité de coup critique : %.3f%%"+
			"\nMultiplicateur des coups critiques : %.3f"+
			"\nDégâts moyens d'une attaque : %.3f"+
			"\nDégâts moyens par seconde : %.3f"+
			"\nMultiplicateur de dégâts :"+
			"\n Versus morts-vivants : %.2f"+
			"\n Versus animaux : %.2f"+
			"\n Versus humains : %.2f"+
			"\n Versus peaux-vertes : %.2f"+
			"\n Versus démons : %.2f"+
			"\n Versus champions : %.2f"+
			"\nProbabilité de toucher un monstre :"+
			"\n  De niveau %d : %.3f%%"+
			"\n  De niveau %d : %.3f%%"+
			"\n  De niveau %d : %.3f%%"+
			"\nProbabilité d'esquiver contre un monstre :"+
			"\n  De niveau %d : %.3f%%"+
			"\n  De niveau %d : %.3f%%"+
			"\n  De niveau %d : %.3f%%"+
			"\nRéduction des dégâts : %.3f%%"+
			"\nRésistance aux pièges : ×%.3f"+
			"\nRéduction des dégâts des pièges : %.3f%%"+
			"\nAbsorption des dégâts : %.3f"+
			"\nPoints de vie par point de vitalité : %.3f"+
			"\nPoints de vie : %.3f"+
			"\nPoints de vie effectifs : %.3f"+
			"\nPoints de vie par seconde : %.3f"+
			"\nRégénération totale : %.3f secondes"+
			"\nVol de vie : %.3f%% des dégâts"+
			"\nTemps avant d'attaquer : %.2f secondes"+
			"\nBonus d'initiative face à un piège : %.3f"+
			"\nTemps d'initiative face à un piège : %.2f secondes"+
			"\nTraque d'un adversaire : %.3f secondes"+
			"\nRecherche d'un marchand : %.3f secondes"+
			"\nRecherche d'une forge : %.3f secondes"+
			"\nRésurrection : %.3f secondes"+
			"\nEncombrement maximal : %.3f kg"+		     
			"\nCoefficient de vente : %.3f"+
			"\nCoefficient d'achat : %.3f" +
			"\nProbabilité de fuite : %.2f%%"+
			"\nTemps de fuite : %.3f secondes"+
			"\nProbabilité qu'un objet soit magique : %.2f%%"+
			"\nProbabilité qu'un objet soit rare : %.2f%%"+
			"\nProbabilité qu'un objet soit de qualité : %.2f%%"+
			"\nMultiplicateur d'or : %.3f"+
			"\nMultiplicateur de ressources : %.3f"+
			"\nButin moyen pour un monstre tué"+		     
			"\n  Nombre total d'objets : %.3f"+
			"\n  Nombre de ressources : %.3f"+
			"\n  Nombre d'objets hors ressources : %.3f"+
			"\n  Quantité totale de ressources : %.3f"+
			"\n  Nombre d'objets magiques : %.3f"+
			"\n  Nombre d'objets rares : %.4f"+
			"\nBonus d'expérience : %.2f%%"+
			"\nMultiplicateur du temps : %.3f"+
			"\nDégats infligés aux attaquants : %.2f"+
			"\nDégats réfléchis sur les attaquants : %.2f%%"+
			"\nUn monstre tué donne %.2f%% de sa vie"+			
			"\nTemps du crafting : %.3f secondes"+
			"\nRendement du crafting : %.3f%%"+
			"\nUn orbe coûte %.2f%% d'orbe"+	
			"\nNiveau moyen des marchands : %.0f+%.2f"+
			"\nNombre d'objets chez les marchands : %.2f"+
			"\nProbabilité de détecter un piège :"+
			"\n  De niveau %d : %.3f%%"+
			"\n  De niveau %d : %.3f%%"+
			"\n  De niveau %d : %.3f%%"+
			"\nRente par seconde : %.3f écus"+
			"\nPoints à distibuer par niveau : %.3f",
			temps_total,att_per_sec(),dmg_base(),100.0*crit_proba(),multi_crit(),
			dmpa,att_per_sec()*dmpa,
			ed_versus_tag(0),ed_versus_tag(1),ed_versus_tag(2),ed_versus_tag(3),ed_versus_tag(4),ed_versus_tag(5),
			lvl1,100.0-esquive_proba(Monster.ptsForLevel(lvl1)*Monster.coeff_std[4]*0.1,PRC())*100.0,
			lvl2,100.0-esquive_proba(Monster.ptsForLevel(lvl2)*Monster.coeff_std[4]*0.1,PRC())*100.0,
			lvl3,100.0-esquive_proba(Monster.ptsForLevel(lvl3)*Monster.coeff_std[4]*0.1,PRC())*100.0,
			lvl1,esquive_proba(ESQ(),Monster.ptsForLevel(lvl1)*Monster.coeff_std[5]*0.1)*100.0,
			lvl2,esquive_proba(ESQ(),Monster.ptsForLevel(lvl2)*Monster.coeff_std[5]*0.1)*100.0,
			lvl3,esquive_proba(ESQ(),Monster.ptsForLevel(lvl3)*Monster.coeff_std[5]*0.1)*100.0,
			100.0-(100.0*reduc()),bonus_resistance_vs_piege(),100.0-(100.0*resistance_vs_piege()),absorption(),
			pv_per_vita(),vie_max(),vie_max()/reduc(),
			regen(),vie_max()/regen(),100*vol_de_vie(),
			initiative(),bonus_initiative_piege(),initiative_piege(),
			temps_traque(),temps_shop(),temps_forge(),
			temps_res(),charge_max(),coeff_vente(),coeff_achat(),100*chance_fuite(),temps_fuite(),
			chance_magique()*100,chance_magique()*chance_rare()*100,chance_qualite()*100,multiplicateur_or(),
			multiplicateur_res(),quantite_drop(),
			quantite_drop()*StaticItem.PROBA_RES_BASE,quantite_drop()*(1-StaticItem.PROBA_RES_BASE),
			quantite_drop()*(1-StaticItem.PROBA_RES_BASE)*multiplicateur_res(),
			quantite_drop()*(1-StaticItem.PROBA_RES_BASE)*chance_magique(), // Nombre d'objets magiques
			quantite_drop()*(1-StaticItem.PROBA_RES_BASE)*chance_magique()*chance_rare(), // Nombre d'objets rares
			100.0*(bonus_xp()-1),facteur_temps(),epines(),100*represailles(),100*necrophagie(),
			temps_craft(),100*rendement(),100*economie_orbe(),10.0*zone,niveau_boutique(),taille_boutique(),
			lvl1,100.0*proba_trouver_piege(lvl1),
			lvl2,100.0*proba_trouver_piege(lvl2),
			lvl3,100.0*proba_trouver_piege(lvl3),
			rente_par_seconde(),points_par_niveau());
	}

    public double dmpa()
    {return dmg_base() + dmg_base()*(multi_crit()-1.0)*crit_proba();}
    
    public double IAS()
    {return stats_with_bonus[0];}

    public double DMG()
    {return stats_with_bonus[1];}

    public double REDUC()
    {return stats_with_bonus[2];}

    public double ABS()
    {return stats_with_bonus[3];}

    public double ESQ()
    {return stats_with_bonus[4];}

    public double PRC()
    {return stats_with_bonus[5];}

    public double LCK()
    {return stats_with_bonus[6];}

    public double CRT()
    {return stats_with_bonus[7];}

    public double VDV()
    {return stats_with_bonus[8];}

    public double VITA()
    {return stats_with_bonus[9];}

    public double CON()
    {return stats_with_bonus[10];}

    public double REGEN()
    {return stats_with_bonus[11];}

    public double RESUR()
    {return stats_with_bonus[12];}

    public double LOAD()
    {return stats_with_bonus[13];}

    public double RUN()
    {return stats_with_bonus[14];}

    public double RESF()
    {return stats_with_bonus[15];}

    public double MF()
    {return stats_with_bonus[16];}

    public double RF()
    {return stats_with_bonus[17];}

    public double QALF()
    {return stats_with_bonus[18];}

    public double QTYF()
    {return stats_with_bonus[19];}

    public double POWF()
    {return stats_with_bonus[20];}

    public double GF()
    {return stats_with_bonus[21];}

    public double ED_MV()
    {return stats_with_bonus[22];}

    public double ED_ANI()
    {return stats_with_bonus[23];}

    public double ED_HUM()
    {return stats_with_bonus[24];}

    public double ED_PV()
    {return stats_with_bonus[25];}

    public double ED_DEM()
    {return stats_with_bonus[26];}

    public double ED_CHAMP()
    {return stats_with_bonus[27];}

    public double ESTI()
    {return stats_with_bonus[28];}

    public double FLEE()
    {return stats_with_bonus[29];}

    public double FLEE_SPD()
    {return stats_with_bonus[30];}

    public double INIT()
    {return stats_with_bonus[31];}

    public double BONUS_XP()
    {return stats_with_bonus[32];}

    public double TIME_SPD()
    {return stats_with_bonus[33];}

    public double EPIN()
    {return stats_with_bonus[34];}

	public double REP()
	{return stats_with_bonus[35];}

	public double NECRO()
	{return stats_with_bonus[36];}

	public double CRAFT_SPD()
	{return stats_with_bonus[37];}
	
	public double CRAFT_REND()
	{return stats_with_bonus[38];}
	
	public double ECO_ORB()
	{return stats_with_bonus[39];}
	
	public double SHOP_LEVEL()
	{return stats_with_bonus[40];}

	public double SHOP_SIZE()
	{return stats_with_bonus[41];}	

	public double TRAP_DET()
	{return stats_with_bonus[42];}

	public double TRAP_INIT()
	{return stats_with_bonus[43];}

	public double TRAP_RES()
	{return stats_with_bonus[44];}
	
	public double RENTE()
	{return stats_with_bonus[45];}
	
	public double EDUC()
	{return stats_with_bonus[46];}
	
	public double proba_rencontrer_piege() {return 0.20;}
    public double vie() {return vie;}
    public double vie_max() {return vie_max(VITA(),CON());}
    public double att_per_sec() {return att_per_sec(IAS());}
    public double pv_per_vita() {return pv_per_vita(CON());}
    public double crit_proba() {return crit_proba(LCK());}
    public double multi_crit() {return multi_crit(CRT());}
    public double dmg_base() {return dmg_base(DMG());}
    public double reduc() {return reduc(REDUC());}
    public double absorption() {return absorption(ABS());}
    public double vol_de_vie() {return vol_de_vie(VDV());}
    public double regen() {return regen(REGEN(),CON());}
    public double charge_max() {if(Game.DEBUG) return 900000; else return charge_max(LOAD());}
    public double temps_traque() {return temps_traque(RUN());}
    public double temps_shop() {return temps_shop(RUN());}
    public double temps_forge() {return temps_forge(RUN());}
    public double temps_res() {return temps_res(RESUR(),level);}
    public double coeff_achat() {return coeff_achat(ESTI());}
    public double coeff_vente() {return coeff_vente(ESTI());}
    public double multiplicateur_res() {return multiplicateur_res(RESF());}
    public double chance_magique() {return chance_magique(MF());}
    public double chance_rare() {return chance_rare(RF());}
    public double chance_qualite() {return chance_qualite(QALF());}
    public double quantite_drop() {return quantite_drop(QTYF());}
    public double multiplicateur_or() {return multiplicateur_or(GF());}
    public double puissance_ench_inf() {return puissance_ench_inf(POWF());}
    public double puissance_ench_sup() {return puissance_ench_sup(POWF());}
    public double ed_versus_tag(int tag) {return ed_specific_monster(stats_with_bonus[22+tag]);}
    public double chance_fuite() {return chance_fuite(FLEE());}
    public double temps_fuite() {return temps_fuite(FLEE_SPD());}
    public double temps_craft() {return temps_craft(CRAFT_SPD());}
    public double initiative() {return initiative(INIT());}
    public double bonus_xp() {return bonus_xp(BONUS_XP());}
    public double facteur_temps() {return facteur_temps(TIME_SPD());}
    public double epines() {return epines(EPIN());}
    public double represailles() {return represailles(REP());}
    public double necrophagie() {return necrophagie(NECRO());}

    public double rendement() {return rendement(CRAFT_REND());}
    public double economie_orbe() {return economie_orbe(ECO_ORB());}
    public double niveau_boutique() {return niveau_boutique(SHOP_LEVEL());}
    public double taille_boutique() {return taille_boutique(SHOP_SIZE());}

	public double detection_piege() {return detection_piege(TRAP_DET());}
	public double bonus_initiative_piege() {return bonus_initiative_piege(TRAP_INIT());}
	public double initiative_piege() {return initiative(INIT()*bonus_initiative_piege(TRAP_INIT()));}
	public double bonus_resistance_vs_piege() {return bonus_resistance_vs_piege(TRAP_RES());}
	public double resistance_vs_piege() {return reduc(REDUC()*bonus_resistance_vs_piege(TRAP_RES()));}
	public double rente_par_seconde() {return rente_par_seconde(RENTE());}
	public double points_par_niveau() {return points_par_niveau(EDUC());}	
	public double points_totaux() {return points_par_niveau()*level + points_initiaux;}
	public double points_a_distribuer() {return points_totaux()-points_distribues();}
	
    public void distribue(int x,double y) {stats[x]+=y;}
	
	public static double proba_trouver_piege(double td, double hidden_lvl)
	{
		double dp = detection_piege(td);
		return dp/(hidden_lvl+dp);
	}
	
	public double proba_trouver_piege(double hidden_lvl)
	{
		return proba_trouver_piege(TRAP_DET(), hidden_lvl);
	}

    public void personal_wait(double time_w, int ACT)
    {
	try
	    {
		double adjusted_time = time_w*facteur_temps();
		temps_total += adjusted_time;
		t_stats.addActivity(time_w,ACT);
		if(!Game.FAST) Thread.currentThread().sleep((int)(adjusted_time*1000));
	    }
	catch (Exception e)
	    {
	    }
	Game.MW.refreshInFight();
    }

    public Player(boolean b) // constructeur presque vide....
    {
	shop = null;
	mob = null;
	defi = null;
    }

	public void addDefaultRules()
	{
		// Inventory filter rules
		ObjectRule tmp;
		tmp = new ObjectRule(4,2,0.0,ObjectRule.ITEM_RULE);
		tmp.name = "Tout";
		tmp.filter_rule = true;
		tmp.pickup_rule = true;
		tmp.system_rule = true;
		rules.add(tmp);
		tmp = new ObjectRule(17,0,1,ObjectRule.ITEM_RULE);
		tmp.name = "Équipés";
		tmp.filter_rule = true;
		rules.add(tmp);

		tmp = new ObjectRule(tmp, false);
		tmp.name = "Non équipés";
		tmp.filter_rule = true;
		rules.add(tmp);

		// Monster rules
		tmp = new ObjectRule(0,2,5,ObjectRule.MONSTER_RULE);
		tmp.avoid_rule = true;
		tmp.name = "Monstre trop fort"; rules.add(tmp);
		tmp = new ObjectRule(0,1,1,ObjectRule.MONSTER_RULE);
		tmp.avoid_rule = true;
		tmp.name = "Monstre trop faible"; rules.add(tmp);

		// Shopping and sell rules
	/*	tmp = new ObjectRule(2,1,2,ObjectRule.PLAYER_RULE);
		tmp.shopping_rule = true;
		tmp.name = "Inventaire plein"; rules.add(tmp);*/
/*		tmp = new ObjectRule(1,0,0,ObjectRule.ITEM_RULE);
		tmp.sell_rule = true;
		tmp.name = "Vente des déchets"; rules.add(tmp);*/

		for (int i=0; i< StaticItem.nb_pos ; i++)
		{
			tmp = new ObjectRule(2,0,i,ObjectRule.ITEM_RULE);
			tmp.name = StaticItem.Emplacement[i];
			tmp.filter_rule = true;
			rules.add(tmp);
		}
		
		//	for (int i=0; i< rules.size() ; i++)
		//		System.out.println(rules.get(i).desc());

	}

    public Player()
    {
	temps_total = 0;
	temps_depuis_derniere_rente = 0;
	t_stats = new TimeStats();
	conditionToggle = new boolean[]{true,true,true,true,true};
	conditionValues = new int[]{1,1,1,1,1};

	shop = null;
	mob = null;
	craftInventory = new ArrayList<Item>();
	inventory = new ArrayList<Item>();
	rules = new ArrayList<ObjectRule>();
	addDefaultRules();

	zone = 0;
	level = 1;
  	xp_pt = 0;
	
	tags = new boolean[nb_tags];
	for(int t=0; t<nb_tags; t++) tags[t]=false;
	tags[2]=true; // Humain
	
	name = "Mortadelle";
	stats = new double[nb_stats];
	stats_with_bonus = new double[nb_stats];
	item_bonus = new double[nb_stats];
	
	for(int i=0; i<nb_stats; i++) 
		{stats[i]=0; stats_with_bonus[i]=0; item_bonus[i]=0;}
	refresh();
    }

    public String short_stats()
    {
	String res = String.format("%s\n",name);
	res += String.format("Niveau : %d\n",level);
	for (int i=0; i<8; i++)
	    res += String.format("%s : %.2f\n",stats_name[i],stats_with_bonus[i]);
	res += String.format("%s : %.2f\n",stats_name[31],stats_with_bonus[31]);
	res += String.format("%s : %.2f\n",stats_name[34],stats_with_bonus[34]);
	res += String.format("%s : %.2f\n",stats_name[35],stats_with_bonus[35]);
	if(money > 0.001)
		res += String.format("Or : %.2f\n",money);
	return res;
    }

	// Utilisé si DEBUG
    public void giveStuff()
    {
	Item it;
		for (int i=0;i<150;i++)
	    {
		it = new Item(i,this);
		if(it.stackable) it.set_qty(5.0);
		inventory.add(it);
		charge += it.poids;
		}
	for(int j=0; j<StaticItem.ORB.length; j++)	
	    {
		it = new Item(StaticItem.ORB[j],StaticItem.RESSOURCE_CRAFT);
		it.qty = 200.0;
		it.update();
		inventory.add(it);
		charge += it.poids;
	    }
	
	for(int j=0; j<8; j++)	
		 {	
		it = new Item(150,this);
		if (it.rare == 0)
		{
		it.elvl = 160;
		it.transform_magic(this);
		it.rare = 3;
		for(int i=0; i<Player.nb_stats; i++)
		    {
			it.bonus[i]=100;
		    }
		inventory.add(it);
		charge += it.poids;
		}
		}
    }

	public int get_mob_level()
	{
		return 10*zone + 1 + (int)(Math.random() * 10);
	}
	
    // rencontre aléatoire
	// peut être précédée par un piège
    public void get_mob()
    {
	shop = null;
	double tt = plage_random()*temps_traque();
	Game.MW.addLog(String.format("Recherche d'un ennemi : %.3f secondes",tt));
	personal_wait(tt,TimeStats.ACTIVITY_CHERCHE_ENNEMI);
	int mob_level = get_mob_level();
	mob = new Monster(mob_level);
    }

    // trouver un marchand
    public void get_shop()
    {
	if(shop == null)
	    {
		double tt = plage_random()*temps_shop();
		Game.MW.addLog(String.format("Recherche d'un marchand : %.3f secondes",tt));
		personal_wait(tt,TimeStats.ACTIVITY_CHERCHE_MARCHAND);
		shop = new Shop(this);
		Game.MW.addLog(String.format("Vous rencontrez %s (niveau %d)",shop.name,shop.level));
		toucher_rente();
	    }
    }

    // trouver une forge
    public void get_forge()
    {
	double tt = plage_random()*temps_forge();
	Game.MW.addLog(String.format("Recherche d'une forge : %.3f secondes",tt));
	personal_wait(tt,TimeStats.ACTIVITY_CHERCHE_FORGE);
    }

    double plage_random()
    {
	return 0.7 + 0.6 * Math.random(); // Compris entre 0.7 et 1.3
    }

    public String cogne(Player p)
    {
	double t_att = plage_random() * (1.0/att_per_sec()); // Durée de l'attaque
	temps += t_att;
	String res = String.format("%s frappe %s en %.3f secondes (temps total pour %s : %.3f)\n",this.name,p.name,t_att,this.name,temps);

	double esquive, critique, multiplicateur1, multiplicateur2, percant;
	double dmg, dmg_base, dmg_abs, dmg_red;
	double dmg_base2, dmg_red2;

	esquive = esquive_proba(p.ESQ(),PRC());

	if (Math.random() < esquive)
	    {
		res+=String.format("   %s esquive le coup de %s (%.2f%% d'esquive)",p.name,this.name,100.0*esquive);
	    }
	else
	    {
		multiplicateur1 = 1.0;
		multiplicateur2 = 1.0;

		String cause = "";
		for(int t=0; t<nb_tags; t++)
			if (p.tags[t] && ed_versus_tag(t) > 1.001) 
			{
				multiplicateur1 =  multiplicateur1 * ed_versus_tag(t);
				res+=String.format("   Multiplicateur x%.2f (%s)\n",ed_versus_tag(t),TagsName[t]);
			}
		
		critique = crit_proba();
		if (Math.random() < critique)
		    {
			multiplicateur2 = multi_crit();
			res+=String.format("   Coup critique x%.2f (%.2f%% de probabilité)\n",multiplicateur2,100.0*critique);
			p.crit_taken++;
		    }

		dmg_base = plage_random() * dmg_base() * multiplicateur1 * multiplicateur2;
		dmg_red = p.reduc();
		dmg_abs = p.absorption();
		dmg = Math.max(dmg_base*dmg_red - dmg_abs,0.0); // pas de dégâts négatifs

		res+=String.format("   %s inflige %.3f points de dégâts à %s (%.2f de base, %.2f%% de réduction, %.2f d'absorption)",this.name,dmg,p.name,dmg_base,100*(1.0-dmg_red),dmg_abs);
		p.vie -= dmg;
		
		dmg_base2 = p.plage_random() * p.epines();
		dmg_red2 = reduc();
		if(dmg_base2*dmg_red2 > 0.01)
		    {
				res+=String.format("\n   Les épines de %s infligent %.3f points de dégâts à %s (%.2f de base, %.2f%% de réduction)",p.name,dmg_base2*dmg_red2,this.name,dmg_base2,100*(1.0-dmg_red2));
				this.vie -= dmg_base2*dmg_red2;
		    }
		double rep = p.represailles() * dmg;
		if(rep*dmg_red2 > 0.01)
	    	{
				res+=String.format("\n   Les représailles de %s causent %.3f points de dégâts à %s (%.2f de base, %.2f%% de réduction)",p.name,rep*dmg_red2,this.name,rep,100*(1.0-dmg_red2));
				this.vie -= rep*dmg_red2;
	    	}
							
		double vdv = Math.min(this.vie_max()-this.vie(),dmg*vol_de_vie(VDV()));
		if (vdv > 0.01)
		    {
		    res+="\n   "+String.format("%s récupère %.3f points de vie (vol de vie)",this.name,vdv);
		    this.vie += vdv;
		    }
	    }
	return res;
    }

    public void refresh()
    {
	vie=vie_max();
	temps = 0;
    }

    public boolean combat(boolean disp)
    {
	boolean has_flee = false;
	crit_taken = 0;
	boolean lost;
	Player p2 = mob;
	if(disp) Game.MW.addLog(name + " versus " + p2.name + " (niveau "+ p2.level + ")");

	String tmp;
	Player t1,t2;
	double i1,i2;
	i1 = plage_random()*initiative();
	i2 = p2.plage_random()*p2.initiative();
	temps += i1;
	p2.temps += i2;

	double tmp_temps = temps;
	personal_wait(i1,TimeStats.ACTIVITY_INITIATIVE);

	if (i1<i2) {t1=this; t2=p2;}
	else {t1=p2; t2=this;}
	if(disp) Game.MW.addLog(String.format("Initiatives : %.3f pour %s, %.3f pour %s",i1,name,i2,p2.name));
  
	while (vie() >0 && p2.vie() >0)
	    {
		if(this == t1 && must_flee())
		    {
			double t_att = plage_random()*temps_fuite(); // Durée de la fuite
			temps += t_att;
			if(disp) Game.MW.addLog(String.format("%s tente de fuire face à %s (%.3f secondes)",name,p2.name,t_att));
			if(Math.random() < chance_fuite())
			    {
				if(disp) Game.MW.addLog(String.format("   Succés de la fuite (%.2f%% de probabilité))",100*chance_fuite()));
				has_flee = true;
				break;
			    }
			else
			    {
				if(disp) Game.MW.addLog(String.format("   Échec de la fuite (%.2f%% de probabilité))",100-100*chance_fuite()));
			    }
		    }
		else
		    {
			tmp = t1.cogne(t2);
			if(disp) Game.MW.addLog(tmp);
		    }
		personal_wait(temps-tmp_temps,TimeStats.ACTIVITY_COGNE);
		tmp_temps = temps;
		if(temps < p2.temps) {t1=this; t2=p2;}
		else {t1=p2; t2=this;}
	    }
	if(vie() >0) // victoire ou fuite
	    {
		if(has_flee){
		    if(disp) Game.MW.addLog(String.format("%s fuit face à %s (temps du combat %.3f secondes, vie restante %.3f)",name,p2.name,temps,vie()));
		    lost = true;
		}
		else{
		    if(disp) Game.MW.addLog(String.format("%s tue %s (temps du combat %.3f secondes, vie restante %.3f)",name,p2.name,temps,vie()));
		    gain_xp_mob(p2);
		    loot((Monster)p2);
		    lost = false;
		}
	    }
	else
	    {
		if(disp) Game.MW.addLog(String.format("%s est tombé face à %s de niveau %d (temps du combat %.3f secondes)",name,p2.name,p2.level,temps));
		meurt();
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
	double fric = Math.random()*(Math.pow((double)(p2.level),1.5) * multiplicateur_or());
	if(fric < 1.0) fric = 0;
	if (loot.isEmpty() && fric <= 0.1) 
	    Game.MW.addLog(String.format("Pas de butin sur %s", p2.name));
	else
	    {
		money_gain(fric, TimeStats.GAIN_DROP);
		Game.MW.addLog(String.format("Vous ramassez %.2f écus sur le cadavre de %s", fric, p2.name));
		String lootStr = "";
		String dontLootStr = "";
		for(Item the_object : loot)
		    {
			if (the_object.poids < charge_max()-charge && pickupCond(the_object))
			    {
				if(lootStr != "") lootStr += ", ";
				lootStr += NameGenerator.firstCharLowercase(the_object.name);
				charge += the_object.poids;
				inventory.add(the_object);
			    }
			else
				{
				if(dontLootStr != "") dontLootStr += ", ";
				dontLootStr += NameGenerator.firstCharLowercase(the_object.name);
				}
		    }
		if(lootStr != "")
			Game.MW.addLog(String.format("Vous ramassez %s sur le cadavre de %s",lootStr, p2.name));
		if(dontLootStr != "")
			Game.MW.addLog(String.format("Vous abandonnez %s", dontLootStr));
	    }
		
	double nec = necrophagie()*p2.vie_max();
	if(nec > 0.05)
		{
		Game.MW.addLog(String.format("Vous dévorez le cadavre de %s et récupérez %f points de vie", p2.name,nec));
		vie = Math.min(vie_max(),vie+nec);
		}
    }

    public void meurt()
    {
	double res = temps_res();
	double perte = money/10.0;
	Game.MW.addLog(String.format("%s perd 10%% de son or (%.3f)", name, perte));
	Game.MW.addLog(String.format("Résurrection : %.3f secondes",res));
	money -= perte;
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
	double res = hp_tg / regen(REGEN(),CON());
	if (hp_tg > 0.001)
	    {
		vie = vie_max();
		Game.MW.addLog(String.format("Récupération de %.3f points de vie en %.3f secondes",hp_tg,res));
		personal_wait(res,TimeStats.ACTIVITY_HEAL);
	    }
    }

    public int next_level()
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

    public int xp_level(int x)
    {
	if (x==1) return 0;
	return 1000+((x-1)*(x-2)*1000);
    }

    public void gain_xp_mob(Player M)
    {
	int gain = (int)Math.pow(140*M.level/Math.max(1,level-M.level),0.85);
	if (M.tags[5]) gain = gain + gain/2; // Champion
	gain_xp(gain);
    }

    public void gain_xp(int sx)
    {
	int x = (int)(bonus_xp()*sx);
	int levelback=level;
	xp_pt+=x;
	Game.MW.addLog(name + " reçoit "+sx+" points d'expérience ("+x+" avec les bonus).");
	while(xp_pt>=next_level())
	    {
		level++;
		Game.MW.addLog(name + " passe au niveau " + level + ".");
		//save();
		//Game.MW.addLog("Joueur sauvegardé...");
		refresh();
	    }
	if(levelback != level)
	    Game.MW.DistWindow.refresh();
    }

    public void split(Item i)
    {
	i.set_qty(i.qty/2);
	Item rit = new Item(i.material,StaticItem.RESSOURCE_CRAFT);
	rit.copy(i);
	rit.update();
	inventory.add(rit);
    }

    public void save()
    {
	try{
	    //use buffering
	    OutputStream file = new FileOutputStream( name+".theory" );
	    OutputStream buffer = new BufferedOutputStream( file );
	    ObjectOutput output = new ObjectOutputStream( buffer );
	    mob = null;
	    output.writeObject(this);
	    output.close();
	} 
	catch(Exception ex){
	    System.out.println(ex);
	    System.out.println("Can't write in \"" +name +".theory\"");
	}
    }

	public void craft()
	{
		double tt = plage_random()*temps_craft();
		personal_wait(tt,TimeStats.ACTIVITY_CRAFT);
		if (craftInventory.size() == 1) 
		{ 
			Game.MW.addLog(String.format("Recyclage de : %s (%.3f secondes)",craftInventory.get(0).name,tt));
		}
		else
		{
			String rstring = "Combinaison de : ";
			for(int i=0; i< craftInventory.size(); i++)
			{
				rstring += craftInventory.get(i).name;
				if (i< craftInventory.size()-1) rstring+= ", ";
			}
			Game.MW.addLog(String.format("%s (%.3f secondes)",rstring,tt));
		}
		if (craftInventory.size() > 0) 		
			craftInventory = Item.CraftItem(craftInventory, this);
	}

public void reset_build()
    {
	Game.MW.addLog("Redistribution des points de compétence");
	for(int i=0; i<nb_stats; i++) stats[i]=0; 
	Game.MW.DistWindow.refresh();
    }

}
