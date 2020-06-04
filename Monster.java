import java.util.ArrayList;

class Monster extends Player {
//************* Table des monstres ***************
// Statistiques principales des monstres
    // IAS(0) DMG(1) REDUC(2) ABS(3) ESQ(4) PRC(5) LCK(6) CRT(7)
    // VDV(8) VITA(9) CON(10) REGEN(11) RESUR(12)
    // RESF(15) MF(16) RF(17) QALF(18) QTYF(19) POWF(20) GF(21)
    // ED_MV(22) ED_ANI(23) ED_HUM(24) ED_PV(25) ED_DEM(26) ED_CHAMP(27)
    // ESTI(28) FLEE(29) FLEE_SPD(30) INIT(31)

public static double[] coeff_std; // la somme fait 1

public boolean masculin;
public double prefered_level;
public int[] prefered_stats;
public String nom_base;

// Boss
public Monster(String n, double p_level, int t_tag, int[] p_stats, Universe u)
{
	super(u,true);
	tags = new boolean[nb_tags];
	for(int t=0; t<nb_tags; t++) tags[t]=false;
	tags[t_tag]=true;
	tags[5] = true; // champion
	
	stats_with_bonus = new double[nb_stats];
	stats = new double[nb_stats];
	
	name = n;
	nom_base = n;
	
	prefered_level = p_level;
	level = p_level;
	
	prefered_stats = p_stats;
	masculin = true;
	
	double pts = u.monster_points_for_level(level)*2.0;
	
	// 0.5 points distribués avec coeff_std 
	for (int i=0; i<nb_stats; i++)
		{stats[i]= pts*coeff_std[i]*(0.7+Math.random()*0.6)*0.5;}

	// 0.5 points distribués avec prefered_stats 
	stats[p_stats[0]]+=pts*0.22;
	stats[p_stats[1]]+=pts*0.18;
	stats[p_stats[2]]+=pts*0.10;

	for(int i=0; i<nb_stats; i++)
		stats_with_bonus[i]=stats[i];
	refresh();
}

// Zoo monster
public Monster(String n, double p_level, int t_tag, int[] p_stats, boolean g)
	{
	super(null,true);
	name = n;
	prefered_level = p_level;
	prefered_stats = p_stats;
	tags = new boolean[nb_tags];	
	for(int t=0; t<nb_tags; t++) tags[t]=false;
	tags[t_tag]=true;
	masculin = g;
	}

public static String[] fa_m = {"faiblard","chétif","rabougri","rachitique","souffreteux","maigre", "maladif", "difforme"};
public static String[] fa_f = {"faiblarde","chétive","rabougrie","rachitique","souffreteuse","maigre", "maladive", "difforme"};
public static String[] fo_m = {"sanguinaire","féroce","meurtrier","tueur","cruel","géant"};
public static String[] fo_f = {"sanguinaire","féroce","meurtrière","tueuse","cruelle","géante"};

public static String[] adj_m = {"l'abominable", "le déloyal", "le fourbe", "l'hypocrite", "l'insidieux", "le perfide", "le tortueux", "le traître", "le sadique", "le sournois", "le barbare", "le cruel", "le pervers", "le dépravé", "l'exécuteur", "le tortionnaire", "le vicieux", "le pestiféré", "le maudit", "le damné", "l'odieux", "le tourmenteur", "le tyrannique", "le sanguinaire"};

public static MonsterSet[] ZooByLevel;
public static String[] MonsterNames;
public static ArrayList<String> MonsterNamesList;

public static Monster[] zoo = 
{
new Monster("Puce",          1.0, 1, new int[]{0,0,9},false),
new Monster("Mouche",        1.0, 1, new int[]{0,0,9},false),
new Monster("Souris",        2.0, 1, new int[]{4,0,6},false),
new Monster("Gerbille",      2.0, 1, new int[]{0,1,0},false),
new Monster("Grenouille",    3.0, 1, new int[]{0,1,0},false),
new Monster("Gerboise",      3.0, 1, new int[]{4,0,6},false),
new Monster("Moineau",       4.0, 1, new int[]{0,4,5},true),
new Monster("Pigeon",        4.0, 1, new int[]{0,4,5},true),
new Monster("Hamster",       5.0, 1, new int[]{0,4,6},true),
new Monster("Lapin",         5.0, 1, new int[]{0,4,5},true),
new Monster("Rat",           6.0, 1, new int[]{0,4,5},true),
new Monster("Chat",          6.0, 1, new int[]{0,0,4},true),
new Monster("Renard",        7.0, 1, new int[]{0,0,4},true),
new Monster("Chien",         7.0, 1, new int[]{1,0,9},true),
new Monster("Coyote",        8.0, 1, new int[]{1,0,9},true),
new Monster("Chauve-souris", 9.0, 1, new int[]{0,1,4},false),
new Monster("Chien-loup",    10+2*0.0, 1, new int[]{0,9,1},true),
new Monster("Dingo",         10+2*1.0, 1, new int[]{0,9,1},true),
new Monster("Loup",          10+2*2.0, 1, new int[]{0,9,1},true),
new Monster("Lynx",          10+2*3.0, 1, new int[]{0,1,4},true),
new Monster("Ours",          10+2*4.0, 1, new int[]{0,9,1},true),
new Monster("Grizzli",       10+2*5.0, 1, new int[]{0,9,1},true),
new Monster("Jaguar",        10+2*6.0, 1, new int[]{0,0,1},true),
new Monster("Puma",          10+2*7.0, 1, new int[]{0,0,1},true),
new Monster("Guépard",       10+2*8.0, 1, new int[]{0,0,1},true),
new Monster("Tigre",         10+2*9.0, 1, new int[]{0,0,1},true),
new Monster("Léopard",       10+2*10.0, 1, new int[]{0,0,1},true),
new Monster("Lion",          10+2*11.0, 1, new int[]{0,0,1},true),
new Monster("Rhinocéros",    10+2*12.0, 1, new int[]{1,9,0},true),
new Monster("Utahraptor",    10+2*13.0, 1, new int[]{0,9,1},true),
new Monster("Vélociraptor",  10+2*14.0, 1, new int[]{0,9,1},true),
new Monster("Diplodocus",    10+2*15.0, 1, new int[]{9,9,1},true),
new Monster("Gorgosaurus",   10+2*16.0, 1, new int[]{0,9,1},true),
new Monster("Allosaure",     10+2*17.0, 1, new int[]{1,9,1},true),
new Monster("Tyrannosaure",  10+2*18.0, 1, new int[]{1,9,0},true),
new Monster("Spinosaure",    10+2*19.0, 1, new int[]{1,9,0},true),
new Monster("Giganotosaure", 10+2*20.0, 1, new int[]{9,9,1},true),
new Monster("Harpie",        10+2*21.0, 1, new int[]{9,9,1},false),
new Monster("Griffon",       10+2*22.0, 1, new int[]{9,9,1},true),
new Monster("Sphinx",        10+2*23.0, 1, new int[]{1,9,1},true),
new Monster("Licorne",       10+2*24.0, 1, new int[]{1,0,9},false),
new Monster("Alicorne",      10+2*25.0, 1, new int[]{1,0,9},false),
new Monster("Manticore",     10+2*26.0, 1, new int[]{1,9,1},false),
new Monster("Kraken",        10+2*27.0, 1, new int[]{9,9,1},true),
new Monster("Léviathan",     10+2*28.0, 1, new int[]{9,1,0},true),
new Monster("Béhémoth",      10+2*29.0, 1, new int[]{1,1,9},true),
new Monster("Tarasque",      10+2*30.0, 1, new int[]{1,1,9},false),
new Monster("Oxydeur",       10+2*31.0, 1, new int[]{1,9,0},false),
new Monster("Demi-dragon",   10+2*32.0, 1, new int[]{9,1,0},true),
new Monster("Dragon",        10+2*33.0, 1, new int[]{9,1,0},true),
new Monster("Dracosire",     10+2*34.0, 1, new int[]{1,0,9},true),

new Monster("Sauvageon",    12+2*0.0, 2, new int[]{1,9,10},true),
new Monster("Voyou",        12+2*1.0, 2, new int[]{1,9,10},true),
new Monster("Cleptomane",   12+2*2.0, 2, new int[]{0,1,9},true),
new Monster("Délinquant",   12+2*3.0, 2, new int[]{0,1,9},true),
new Monster("Bagarreur",    12+2*4.0, 2, new int[]{0,1,9},true),
new Monster("Fripon",       12+2*5.0, 2, new int[]{0,1,9},true),
new Monster("Voleur",       12+2*6.0, 2, new int[]{1,1,1},true),
new Monster("Pillard",      12+2*7.0, 2, new int[]{1,9,0},true),
new Monster("Bandit",       12+2*8.0, 2, new int[]{1,0,9},true),
new Monster("Dévaliseur",   12+2*9.0, 2, new int[]{0,1,9},true),
new Monster("Malandrin",    12+2*10.0, 2, new int[]{1,0,7},true),
new Monster("Pirate",       12+2*11.0, 2, new int[]{1,0,7},true),
new Monster("Corsair",      12+2*12.0, 2, new int[]{1,6,7},true),
new Monster("Boucanier",    12+2*13.0, 2, new int[]{1,6,7},true),
new Monster("Flibustier",   12+2*14.0, 2, new int[]{1,0,4},true),
new Monster("Maraudeur",    12+2*15.0, 2, new int[]{1,6,7},true),
new Monster("Détrousseur",  12+2*16.0, 2, new int[]{1,9,0},true),
new Monster("Gangster",     12+2*17.0, 2, new int[]{1,9,0},true),
new Monster("Brigand",      12+2*18.0, 2, new int[]{1,0,4},true),
new Monster("Ninja",        12+2*19.0, 2, new int[]{1,6,7},true),
new Monster("Shinobi",      12+2*20.0, 2, new int[]{1,6,7},true),
new Monster("Inquisiteur",  12+2*21.0, 2, new int[]{1,1,0},true),
new Monster("Assassin",     12+2*22.0, 2, new int[]{1,1,0},true),
new Monster("Fanatique",    12+2*23.0, 2, new int[]{1,6,7},true),
new Monster("Démonologue",  12+2*24.0, 2, new int[]{0,1,9},true),
new Monster("Sataniste",    12+2*25.0, 2, new int[]{1,0,4},true),
new Monster("Sorcier",      12+2*26.0, 2, new int[]{1,0,4},true),
new Monster("Assassin",     12+2*27.0, 2, new int[]{1,0,5},true),
new Monster("Nizârien",     12+2*28.0, 2, new int[]{0,1,0},true),
new Monster("Hérétique",    12+2*29.0, 2, new int[]{9,0,1},true),
new Monster("Ensorceleur",  12+2*30.0, 2, new int[]{9,9,1},true),
new Monster("Magicien",     12+2*31.0, 2, new int[]{0,1,9},true),
new Monster("Géomancien",   12+2*32.0, 2, new int[]{0,1,9},true),
new Monster("Théurge",      12+2*33.0, 2, new int[]{9,1,9},true),
new Monster("Thaumaturge",  12+2*34.0, 2, new int[]{1,9,9},true),
new Monster("Barbare",      12+2*35.0, 2, new int[]{1,9,10},true),
new Monster("Rôdeur",       12+2*36.0, 2, new int[]{1,0,9},true),
new Monster("Guerrier",     12+2*37.0, 2, new int[]{1,0,9},true),
new Monster("Roublard",     12+2*38.0, 2, new int[]{1,1,0},true),
new Monster("Yakuza",       12+2*39.0, 2, new int[]{1,1,0},true),
new Monster("Nécromancien", 12+2*40.0, 2, new int[]{1,0,5},true),
new Monster("Élémentaliste",12+2*41.0, 2, new int[]{1,0,5},true),
new Monster("Mutant",       12+2*42.0, 2, new int[]{1,0,9},true),

new Monster("Snotlings",    9+5*0.0, 3, new int[]{0,6,7},true),
new Monster("Kobold",       9+5*1.0, 3, new int[]{1,6,7},true),
new Monster("Gobelin",      9+5*2.0, 3, new int[]{0,6,7},true),
new Monster("Subterranéen", 9+5*3.0, 3, new int[]{1,6,7},true),
new Monster("Hobgobelins",  9+5*4.0, 3, new int[]{0,6,7},true),
new Monster("Orc",          9+5*5.0, 3, new int[]{0,1,2},true),
new Monster("Uruk-hai",     9+5*6.0, 3, new int[]{0,1,2},true),
new Monster("Ork",          9+5*7.0, 3, new int[]{0,1,2},true),
new Monster("Demi-orc",     9+5*8.0, 3, new int[]{0,1,2},true),
new Monster("Gobelours",    9+5*9.0, 3, new int[]{1,2,3},true),
new Monster("Trolloïde",    9+5*10.0, 3, new int[]{1,9,10},true),
new Monster("Troll",        9+5*11.0, 3, new int[]{1,9,10},true),
new Monster("Gnoblars",     9+5*12.0, 3, new int[]{1,6,0},true),
new Monster("Géant vert",   9+5*13.0, 3, new int[]{1,0,9},true),
new Monster("Ogre",         9+5*14.0, 3, new int[]{1,9,0},true),
new Monster("Ent",          9+5*15.0, 3, new int[]{1,9,0},true),
new Monster("Reptilien",    9+5*16.0, 3, new int[]{1,6,0},true),
new Monster("Draconien",    9+5*17.0, 3, new int[]{1,6,0},true),
new Monster("Hulk",         9+5*18.0, 3, new int[]{1,6,0},true),

new Monster("Diablotin",      	25+2*1.0,4, new int[]{1,0,5},true),
new Monster("Cacodémon",      	25+2*2.0,4, new int[]{0,1,4},true),
new Monster("Mancubus",       	25+2*3.0,4, new int[]{0,1,4},true),
new Monster("Incube",         	25+2*4.0,4, new int[]{0,1,4},true),
new Monster("Succube",        	25+2*5.0,4, new int[]{1,0,5},false),
new Monster("Yokai",          	25+2*6.0,4, new int[]{1,0,5},true),
new Monster("Ayakashi",       	25+2*7.0,4, new int[]{0,1,4},true),
new Monster("Eligos",         	25+2*8.0,4, new int[]{1,0,5},true),
new Monster("Oni",            	25+2*9.0,4, new int[]{1,0,5},true),
new Monster("Diable",         	25+2*10.0,4, new int[]{0,1,4},true),
new Monster("Archidiable",    	25+2*11.0,4, new int[]{1,0,5},true),
new Monster("Baal",           	25+2*12.0,4, new int[]{0,1,4},true),
new Monster("Balaam",         	25+2*13.0,4, new int[]{0,1,4},true),
new Monster("Lilith",         	25+2*14.0,4, new int[]{0,1,4},false),
new Monster("Asmodée",        	25+2*15.0,4, new int[]{1,0,5},true),
new Monster("Méphistophélès", 	25+2*16.0,4, new int[]{1,0,5},true),
new Monster("Bélial",         	25+2*17.0,4, new int[]{1,0,5},true),
new Monster("Paimon",         	25+2*18.0,4, new int[]{1,0,5},true),
new Monster("Belphégor",      	25+2*19.0,4, new int[]{0,1,4},true),
new Monster("Belzébuth",      	25+2*20.0,4, new int[]{0,1,4},true),
new Monster("Baphomet",       	25+2*21.0,4, new int[]{1,0,5},true),
new Monster("Satanachia",     	25+2*22.0,4, new int[]{1,0,5},true),
new Monster("Satan",          	25+2*23.0,4, new int[]{1,0,5},true),
new Monster("Berith",         	25+2*24.0,4, new int[]{1,0,5},false),
new Monster("Kreegan",        	25+2*25.0,4, new int[]{1,0,5},true),
new Monster("Stryge",         	25+2*26.0,4, new int[]{1,0,5},false),
new Monster("Diablo",         	25+2*27.0,4, new int[]{1,0,5},true),
new Monster("Balrog",         	25+2*28.0,4, new int[]{0,1,4},true),
new Monster("Azazel",         	25+2*29.0,4, new int[]{0,1,5},true),
new Monster("Moloch",         	25+2*30.0,4, new int[]{1,0,5},true),
new Monster("Nergal",         	25+2*31.0,4, new int[]{1,0,5},true),
new Monster("Valefor",        	25+2*32.0,4, new int[]{0,1,4},true),
new Monster("Zagan",          	25+2*33.0,4, new int[]{0,1,4},true),
new Monster("Sheitan",        	25+2*34.0,4, new int[]{1,0,5},true),
new Monster("Astaroth",       	25+2*35.0,4, new int[]{1,0,5},false),
new Monster("Abaddon",        	25+2*36.0,4, new int[]{1,0,5},true),
new Monster("Lucifer",        	25+2*37.0,4, new int[]{1,0,5},true),

new Monster("Squelette",   31+2*1.0, 0, new int[]{9,10,1},true),
new Monster("Décomposé",   31+2*2.0, 0, new int[]{9,10,1},true),
new Monster("Zombi",       31+2*3.0, 0, new int[]{9,10,1},true),
new Monster("Maccabé",     31+2*3.0, 0, new int[]{9,10,1},true),
new Monster("Cadavre",     31+2*5.0, 0, new int[]{9,10,1},true),
new Monster("Putréfié",    31+2*6.0, 0, new int[]{9,10,1},true),
new Monster("Fantôme",     31+2*7.0, 0, new int[]{0,2,4},true),
new Monster("Spectre",     31+2*8.0, 0, new int[]{0,2,4},true),
new Monster("Mort-né",     31+2*9.0, 0, new int[]{0,2,4},true),
new Monster("Poltergeist", 31+2*10.0, 0, new int[]{1,2,4},true),
new Monster("Ectoplasme",  31+2*11.0, 0, new int[]{1,2,4},true),
new Monster("Yurei",       31+2*12.0, 0, new int[]{1,2,4},true),
new Monster("Revenant",    31+2*13.0, 0, new int[]{1,2,4},true),
new Monster("Esprit ",     31+2*14.0, 0, new int[]{1,2,4},true),
new Monster("Vampire",     31+2*15.0, 0, new int[]{0,1,9},true),
new Monster("Nosferatu",   31+2*16.0, 0, new int[]{0,1,9},true),
new Monster("Loogaroo",    31+2*17.0, 0, new int[]{0,1,9},true),
new Monster("Nukekubi",    31+2*18.0, 0, new int[]{0,1,9},true),
new Monster("Âme perdue",  31+2*19.0, 0, new int[]{0,2,4},false),
new Monster("Mandurugo",   31+2*20.0, 0, new int[]{0,1,9},true),
new Monster("Âme en peine",31+2*21.0, 0, new int[]{0,2,4},false),
new Monster("Goule",       31+2*22.0, 0, new int[]{0,1,9},false),
new Monster("Âme errante", 31+2*23.0, 0, new int[]{0,2,4},false),
new Monster("Momie",       31+2*24.0, 0, new int[]{1,9,10},false),
new Monster("Moroaică",    31+2*25.0, 0, new int[]{0,1,9},false),
new Monster("Moroï",       31+2*26.0, 0, new int[]{0,1,9},true),
new Monster("Ubume",       31+2*27.0, 0, new int[]{1,0,10},false),
new Monster("Strigoi",     31+2*28.0, 0, new int[]{1,0,10},true),
new Monster("Âme damnée",  31+2*29.0, 0, new int[]{0,2,4},false),
new Monster("Liche",       31+2*30.0, 0, new int[]{1,9,10},false),
new Monster("Ombre",       31+2*31.0, 0, new int[]{1,9,10},false),
new Monster("Illusion",    31+2*32.0, 0, new int[]{0,0,0},false),
new Monster("Deadite",     31+2*33.0, 0, new int[]{0,0,0},true),
new Monster("Nazgûl",      31+2*34.0, 0, new int[]{1,0,10},true)
};


public ArrayList<Item> drop(Player p)
{
  ArrayList<Item> res = new ArrayList<Item>();
  int count = StaticItem.getRandomInt(p.quantite_drop());
  
  if (tags[5]) count = 2*count; // Champion
  for(int i=0; i<count; i++)
	{
	    res.add(new Item(level,p,Item.ITEM_DROP));
	}
return res;
}

// Affiche les monstres de la zone active
public static String infoZooHTML(Player p)
{
	double minL = p.universe.get_zone_level(p.zone);
	double maxL = p.universe.get_zone_max_level(p.zone);
	double max_thorical_level = p.universe.get_zone_max_level((int)p.universe.nombre_zones()-2);
	
	int minNL = Math.min((int)(100*(minL/max_thorical_level)),99);
	int maxNL = Math.min((int)(100*(maxL/max_thorical_level)),99);

	String res = "";
	int inc = 1;
	if(maxNL-minNL>1) inc=2;
	for (int normalized_lev=minNL; normalized_lev <= maxNL; normalized_lev+=inc)
	    {
		if(inc==2 && normalized_lev == maxNL-1) normalized_lev = maxNL;
		
		double lev = Math.floor(max_thorical_level * (normalized_lev/100.0));
		
		if(normalized_lev==minNL)
			{lev = minL;}
		else if (normalized_lev==maxNL)
			{lev = maxL;}

		double pts = p.universe.monster_points_for_level(lev);
	
		res += String.format(Local.H2_LEVEL_H2,lev);
	    for(Monster it : ZooByLevel[normalized_lev].list)
			{
			res += "<b>" + it.name + "</b><br>";
			for (int i=0; i<nb_stats; i++)
			if(coeff_std[i] > 0.001 || it.prefered_stats[0]==i || it.prefered_stats[1]==i || it.prefered_stats[2]==i)
			{
				boolean col = false;
				double stat = pts*coeff_std[i]*0.5;
				double min_stat = pts*coeff_std[i]*0.5*(1.0 - 0.5 * p.universe.static_plage_random());
				double max_stat = pts*coeff_std[i]*0.5*(1.0 + 0.5 * p.universe.static_plage_random());
				
				if (it.prefered_stats[0]==i) {col = true; stat += pts*0.22; min_stat += pts*0.22; max_stat += pts*0.22;}
				if (it.prefered_stats[1]==i) {col = true; stat += pts*0.18; min_stat += pts*0.18; max_stat += pts*0.18;}
				if (it.prefered_stats[2]==i) {col = true; stat += pts*0.10; min_stat += pts*0.10; max_stat += pts*0.10;}
				if(col)
					res += String.format("<font color=\"#ff0000\">%s %g [%g %g]</font><br>",Local.SKILLS_NAME[i], stat, min_stat, max_stat);
				else
					res += String.format("%s %g [%g %g]<br>",Local.SKILLS_NAME[i], stat, min_stat, max_stat);
			}
			res += "<br>";
			}
	    }
	return res;
}
public static void initZoo()
    {
	coeff_std = new double[nb_stats];
	
	int ZooByLevelSize = 100;
	ZooByLevel = new MonsterSet[ZooByLevelSize];
	for (int i=0; i< ZooByLevel.length; i++)
	    ZooByLevel[i] = new MonsterSet();

	int mlv;
	for (int i=0; i<zoo.length; i++)
	    {
		mlv = (int)zoo[i].prefered_level;
		for(int j=-1; j < 2; j++)
		    if(mlv+j >= 0 && mlv+j < ZooByLevelSize)
			ZooByLevel[mlv+j].list.add(zoo[i]);
	    }
	MonsterNames = new String[zoo.length];
	MonsterNamesList = new ArrayList<String>();
	for (int i=0; i<zoo.length; i++)
		{
		MonsterNames[i]=zoo[i].name;
		MonsterNamesList.add(zoo[i].name);
		}

	/*
	for (int i=0; i< ZooByLevel.length; i++)
	    {
	    System.out.print(i+" ");
	    for(Monster it : ZooByLevel[i].list)
		{
		System.out.print(it.name + " ");
		}
	    System.out.println("");
	    }
	*/
    }

public static double MonsterLevelMultiplier(Player p, boolean[] tags)
{
	double res = 1.0;
	if(Game.HOLIDAY == 1 || Game.HOLIDAY == 2 || (tags[0] && Game.HOLIDAY == 3) 
		|| (tags[4] && Game.HOLIDAY == 4) || (tags[1] && Game.HOLIDAY == 5))
	{
		if(Game.HOLIDAY == 2 || Game.HOLIDAY == 1) {res = 1.0/(2*p.bonus_vacances());}
		else {res = 2*p.bonus_vacances();}
	}
	return res;
}
	
// Crée un monstre de niveau lev au hazard
public Monster(double lev, Universe u, int zone)
	{
	super(u,true);
	stats_with_bonus = new double[nb_stats];
	stats = new double[nb_stats];

	// Sélectionne les monstres de bon niveau
	double max_thorical_level = u.get_zone_max_level((int)u.nombre_zones()-2);
	int normalized_lev = (int)(100*(lev/max_thorical_level));
	if(normalized_lev>=99) normalized_lev = 99;
	ArrayList<Monster> t_list = ZooByLevel[normalized_lev].list;
	//System.out.println("wanted_lev=" + lev + " max_thorical_level="+max_thorical_level + " normalized_lev=" + normalized_lev);
		
	Monster m = t_list.get((int)(Math.random()*t_list.size()));
	
	// Crée le monstre
	name = m.name;
	nom_base = m.name;
	if (m.prefered_level > normalized_lev)
		name += " " + faible(m.masculin);
	else if (m.prefered_level < normalized_lev)
		name += " " + fort(m.masculin);

	tags = new boolean[nb_tags];
	for(int t=0; t<nb_tags; t++) tags[t]=m.tags[t];
	
	// Adjust level for holiday (Christmas, Halloween, International Workers' Day, Spring equinox)
	double mul = MonsterLevelMultiplier(u.joueur,tags);
	// Adjust name for holiday (Christmas, Halloween, International Workers' Day, Spring equinox)
	name = String.format(Local.HOLIDAY_FORMAT[Game.HOLIDAY],name); 

	level = lev*mul;

	double pts;

	if(Math.random() > u.proba_champion() * u.map.boss_coeff.get(zone))
		{
		tags[5]=false;
		pts = u.monster_points_for_level(level);
		}
	else // Champion
		{
		tags[5]=true;
		name = StaticItem.nameGenerator.getName() + " " + adj_m[(int)(Math.random()*adj_m.length)];

		if(Math.random() < u.proba_super_champion()) // Super-champion
		{
			name = "Uber-" + name;
			level = u.niveau_super_champion(level);
			pts = u.monster_points_for_level(level)*u.mul_points_competences_super_champions();
		}
		else
		{
			level = u.niveau_champion(level);
			pts = u.monster_points_for_level(level)*u.mul_points_competences_champions();
		}
		}
	
	// 0.5 points distribués avec coeff_std 
	for (int i=0; i<nb_stats; i++)
		{stats[i]= pts*coeff_std[i]*u.plage_random()*0.5;}

	// 0.5 points distribués avec prefered_stats 
	stats[m.prefered_stats[0]]+=pts*0.22;
	stats[m.prefered_stats[1]]+=pts*0.18;
	stats[m.prefered_stats[2]]+=pts*0.10;

	// pas de bonus d'équipement
	for(int i=0; i<nb_stats; i++)
		stats_with_bonus[i]=stats[i];
	
	refresh();
	}

public String faible(boolean m)
	{
	if(m) return fa_m[(int)(Math.random()*fa_m.length)];
	else return fa_f[(int)(Math.random()*fa_f.length)];
	}

public String fort(boolean m)
	{
	if(m) return fo_m[(int)(Math.random()*fo_m.length)];
	else return fo_f[(int)(Math.random()*fo_f.length)];
	}

	
/////////////////////////////////////////////////////////////////////////
// Optimal build tools


public static double EstimateMonsterVictory(Player p, double nbsample)
{
	int mob_victory = 0;
	for(int i=0; i<nbsample;i++)
		{
		p.refresh();
		p.mob.refresh();
		p.disp = false;
		if(p.combat(false)) mob_victory++;
		}
	return mob_victory/nbsample;
}

public static double ImproveMonster2(Player p,double best_mob_victory,double finesse)
{
	double best_mob_victory_backup = best_mob_victory;
	int source[] = new int[]{Universe.IAS,Universe.DMG,Universe.REDUC,Universe.ABS,Universe.ESQ,Universe.PRC,Universe.LCK,Universe.CRT,Universe.VDV,Universe.VITA,Universe.CON,Universe.ED_HUM,Universe.INIT,Universe.IMUN_FINAL,Universe.EPIN,Universe.FIRST_STRIKE};
	int target[] = new int[]{Universe.VDV,Universe.INIT,Universe.EPIN,Universe.REP};

	double pts;
	double backup[] = new double[nb_stats];
	double best[] = new double[nb_stats];
	
	System.arraycopy(p.mob.stats, 0, backup, 0,  nb_stats); // backup
	System.arraycopy(p.mob.stats, 0, best, 0,  nb_stats); // best
	
	int best_idx=-1;
	int stat_dec, stat_inc;
	for(int idc=0; idc<source.length; idc++)
	{
		stat_dec=source[idc];
		if (p.mob.stats[stat_dec] < 0.01)  continue;
		pts = p.mob.stats[stat_dec]*finesse;
		
		for(int idx=0; idx<target.length+2; idx++)
		{
			p.mob.stats[stat_dec] -= pts;
			p.mob.stats_with_bonus[stat_dec] -= pts;
			
			if (idx==target.length)
			{
				//IAS DMG PRC LCK CRT ED_HUM INIT FIRST_STRIKE
				for(int i=0; i<50; i++) DistToDPS(p, pts/50.0);
			}
			else if (idx==target.length+1)
			{
				//VITA, REDUC, CON, ABS, ESQ, IMUN_FINAL
				for(int i=0; i<50; i++) DistToHP(p, pts/50.0, true);
			}
			else if (idx < target.length)
			{
				p.mob.stats[target[idx]] += pts;
			}
			//double mob_victory = 0.0;
			double mob_victory = EstimateMonsterVictory(p,1000);
			
			if (mob_victory > best_mob_victory) 
			{
				best_idx = idx;
				best_mob_victory = mob_victory;
				System.arraycopy(p.mob.stats, 0, best, 0,  nb_stats);
			}
			System.arraycopy(backup, 0, p.mob.stats, 0,  nb_stats); // restore backup
			System.arraycopy(backup, 0, p.mob.stats_with_bonus, 0,  nb_stats);
		}
	}
	
	// Apply change
	System.arraycopy(best, 0, p.mob.stats, 0,  nb_stats);
	System.arraycopy(best, 0, p.mob.stats_with_bonus, 0,  nb_stats);
	
	System.out.println("\nBest mob victory = " + 100*best_mob_victory + "%" + "(" + best_idx + ")");
	for(int i=0; i<nb_stats; i++)
		if( p.mob.stats[i] > 0.1)
		{
			System.out.println(Player.stats_name[i] + " [" + i + "]=" + p.mob.stats[i]);
		}
	dispTot("Best", p);
	
	if(best_mob_victory_backup == best_mob_victory) return -1.0;
	return best_mob_victory;
}

// IAS(0) DMG(1) REDUC(2) ABS(3) ESQ(4) PRC(5) LCK(6) CRT(7) VDV(8) VITA(9) CON(10)
// ED_HUM(24) INIT(31) IMUN_FINAL(32) EPIN(34) REP(35)
public static double ImproveMonster(Player p,double best_mob_victory,double finesse)
{
	int to_dec=0;
	int to_inc=0;
	double pts, backup1, backup2;
	int target[] = new int[]{0,1,2,3,4,5,6,7,8,9,10,24,31,32,34,35};
	
	int stat_dec, stat_inc;
	for(int idc=0; idc<target.length; idc++)
	{
		stat_dec=target[idc];
		if (p.mob.stats[stat_dec] < 0.01)  continue;
		for(int idi=0; idi<target.length; idi++)
		{
			stat_inc = target[idi];
			if (stat_inc == stat_dec) continue;
			
			pts = p.mob.stats[stat_dec]*finesse;
			backup1=p.mob.stats[stat_dec];
			backup2=p.mob.stats[stat_inc];
			
			p.mob.stats[stat_dec] -= pts;
			p.mob.stats[stat_inc] += pts;
			p.mob.stats_with_bonus[stat_dec]-= pts;
			p.mob.stats_with_bonus[stat_inc]+= pts;
			
			double mob_victory = EstimateMonsterVictory(p,1000);
			if (mob_victory > best_mob_victory) 
			{
			best_mob_victory = mob_victory;
			to_dec = stat_dec;
			to_inc = stat_inc;
			}
			
			p.mob.stats[stat_dec] = backup1;
			p.mob.stats[stat_inc] = backup2;
			p.mob.stats_with_bonus[stat_dec] = backup1;
			p.mob.stats_with_bonus[stat_inc] = backup2;
		}
	}
	
	// Apply change
	pts = p.mob.stats[to_dec]*finesse;
	p.mob.stats[to_dec] -= pts;
	p.mob.stats[to_inc] += pts;
	p.mob.stats_with_bonus[to_dec]-= pts;
	p.mob.stats_with_bonus[to_inc]+= pts;
	
	System.out.println("\nBest mob victory = " + 100*best_mob_victory + "% (" + Player.stats_name[to_dec] + "->" + Player.stats_name[to_inc] + ")");
	for(int i=0; i<nb_stats; i++)
		if( p.mob.stats[i] > 0.1)
		{
			System.out.println(Player.stats_name[i] + " [" + i + "]=" + p.mob.stats[i]);
		}
	
	if(to_dec==0 && to_inc==0) return -1.0;
	return best_mob_victory;
}

public static double AverageDPH(Player p1, Player p2)
{
	double p2_vie = p2.vie_max();
	double crit_proba = p1.crit_proba();
	double defender_reduc = p2.reduc();
	double defender_absorption = p2.absorption();
	double dm_bas = p1.dmg_base()*p1.ed_versus_tag(2);
	double dm_crit = dm_bas*p1.multi_crit();
	double dm_per_att = Math.min(Math.max(dm_bas*defender_reduc-defender_absorption,0.0),p2_vie)*(1.0-crit_proba) + 
		Math.min(Math.max(dm_crit*defender_reduc-defender_absorption,0.0),p2_vie)*(crit_proba);
	if(dm_per_att == 0.0) dm_per_att = Math.min(dm_bas*defender_reduc-defender_absorption,p2_vie)*(1.0-crit_proba) + 
		Math.min(dm_crit*defender_reduc-defender_absorption,p2_vie)*(crit_proba);
	return dm_per_att;
}

public static double NbCoupsMoyens(Player p1, Player p2)
{
	double dph = AverageDPH(p1, p2);
	double defender_esquive = p2.universe.esquive_proba(p2.ESQ(),p1.PRC());
	return Math.max(p2.vie_max() / (dph*(1.0-defender_esquive)),1.0);
}

// Approximation pour les combats courts
public static double AttPerSec(Player p1, Player p2)
{
	return 1.0/(1.0/p1.att_per_sec()+ p1.initiative()/NbCoupsMoyens(p1,p2));
}

// Prends en compte (p1) : IAS(0) DMG(1) PRC(5) LCK(6) CRT(7) ED_HUM(24) INIT(31) FIRST_STRIKE(53)
// Prends en compte (p2) : REDUC(2) ABS(3) ESQ(4)
public static double AverageDPS(Player p1, Player p2)
{
	double nbcm = NbCoupsMoyens(p1,p2);
	double dph = AverageDPH(p1, p2) * (1.0 + (p1.multi_premier_coup()-1.0) / nbcm);
	double defender_esquive = p2.universe.esquive_proba(p2.ESQ(),p1.PRC());
	return dph*(1.0-defender_esquive)*AttPerSec(p1,p2);
}

// Prends en compte (p1) : REDUC(2) ABS(3) ESQ(4) VITA(9) CON(10) IMUN_FINAL(32)
// Prends en compte (p2) : DMG(1) PRC(5) LCK(6) CRT(7) ED_HUM(24)
// TODO : proba_immunite_final devrait etre géré
public static double AverageTimeAlive(Player p1, Player p2)
{
	double vie = p1.vie_max();
	//System.out.println(String.format("vie_max = %f (VITA=%f CON=%f)",vie,p1.VITA(),p1.CON()));
	
	// Coups ennemis
	double esquive = p1.universe.esquive_proba(p1.ESQ(),p2.PRC());
	double average_dmg_per_attack = (AverageDPH(p2, p1)*(1.0-esquive));
	//average_hits_e = average_hits_e + p1.proba_immunite_final(); 
	
	// Epines et représailles 
	double esquive_p2 = p1.universe.esquive_proba(p1.ESQ(),p2.PRC());
	double reduc = p1.reduc();
	double represailles = Math.min((p2.represailles() * AverageDPH(p1,p2)) * reduc + p2.epines()*reduc,vie);
	double average_dmg_per_given_attack = (represailles*(1.0-esquive_p2));

	/*System.out.println("represailles = " + represailles);
	System.out.println("average_dmg_per_hit = " + AverageDPH(p2, p1));
	System.out.println("average_dmg_per_given_attack = " + average_dmg_per_given_attack);
	System.out.println("average_dmg_per_attack = " + average_dmg_per_attack);*/
	
	return vie / (average_dmg_per_attack*AttPerSec(p2,p1) + average_dmg_per_given_attack*AttPerSec(p1,p2));
}

public static void DistToDPS(Player p, double pts)
{
	//IAS DMG PRC LCK CRT ED_HUM INIT FIRST_STRIKE
	int target[] = new int[]{0,1,5,6,7,24,31,53};
	
	int index;
	double a_score;
	double best_score = -Double.MAX_VALUE;
	int best_index = 1;
	for(int i=0; i<target.length; i++)
	{
		index = target[i];
		p.mob.stats[index] += pts;
		p.mob.stats_with_bonus[index] += pts;
		a_score = AverageDPS(p.mob,p);
		if (a_score > best_score)
		{
			best_index = index;
			best_score = a_score;
		}
		p.mob.stats[index] -= pts;
		p.mob.stats_with_bonus[index] -= pts;
		//System.out.println(" ["+ Player.stats_name[index] + "] score="+a_score);
	}
	//System.out.println("best: "+ Player.stats_name[best_index] + " best_score="+best_score);
	p.mob.stats[best_index] += pts;
	p.mob.stats_with_bonus[best_index] += pts;
}

public static void dispTot(String str, Player p)
{
	int dps_target[] = new int[]{0,1,5,6,7,24};
	int hp_target[] = new int[]{2,3,4,9,10,32};
	
	double dps = 0.0;
	double hp = 0.0;
	double tot = 0.0;
	for(int i=0; i<nb_stats; i++) tot+=p.mob.stats[i];
	for(int i=0; i<hp_target.length; i++) hp+=p.mob.stats[hp_target[i]];
	for(int i=0; i<dps_target.length; i++) dps+=p.mob.stats[dps_target[i]];
	
	System.out.println(str + " DPS:" + dps + " HP:" + hp + " total:" + tot);
}

public static void DistToHP(Player p, double pts, boolean full)
{
	//VITA, REDUC, CON, ABS, ESQ, IMUN_FINAL
	int target[];
	int best_index;
	if(full)
	{
		target = new int[]{Universe.VITA, Universe.REDUC, Universe.CON, Universe.ABS, Universe.ESQ, Universe.IMUN_FINAL};
	}
	else
	{
		target = new int[]{Universe.VITA, Universe.REDUC, Universe.CON};
	}
	
	int index;
	double a_score;
	double best_score=0.0;
	best_index = 0;
	for(int i=0; i<target.length; i++)
	{
		index = target[i];
		p.mob.stats[index] += pts;
		p.mob.stats_with_bonus[index] += pts;
		a_score = AverageTimeAlive(p.mob,p);
		if (a_score > best_score + best_score/1000.0)
		{
			best_index = index;
			best_score = a_score;
		}
		p.mob.stats[index] -= pts;
		p.mob.stats_with_bonus[index] -= pts;
		
		//System.out.println(" (" + Player.stats_name[index] +"), AverageTimeAlive = " + a_score);
	}
	
	p.mob.stats[best_index] += pts;
	p.mob.stats_with_bonus[best_index] += pts;
	//System.out.println("Final (" + Player.stats_name[best_index] +"), AverageTimeAlive = " + best_score);
}


public static void SetOptimalDistribution(Universe u)
{
	Player pof = new Player(u,true);
	pof.stats = new double[nb_stats];
	pof.stats_with_bonus = new double[nb_stats];
	pof.tags= new boolean[nb_tags];
		for(int t=0; t<nb_tags; t++) pof.tags[t]=false;
	pof.tags[2]=true; // Humain
	
	for(int i=0; i<nb_stats; i++) 
		{pof.stats[i]=100; pof.stats_with_bonus[i]=100;}
	GetOptimumKiller(pof);
}

public static void GetOptimumKiller(Player p)
{
	Monster mob = new Monster("NEMESIS",p.level,4, new int[]{1,1,1},true);
	mob.level = p.level;
	mob.stats_with_bonus = new double[nb_stats];
	mob.stats = new double[nb_stats];
	mob.universe = p.universe;
	p.mob = mob;
	
	double nb_pts_ref = 0;
	for(int i=0; i<nb_stats; i++)
		nb_pts_ref += p.stats_with_bonus[i];
	
	nb_pts_ref = nb_pts_ref*0.25;

	// Optimised distribution
	double c1 = 1.0/3.0;
	double c2 = 2.0/3.0;
	double hp_pts = c1*nb_pts_ref;
	double dps_pts = c2*nb_pts_ref;
	
	for(int i=0; i<50; i++) {DistToHP(p, hp_pts/100.0, false);}
	for(int i=0; i<100; i++) {DistToDPS(p, dps_pts/100.0);}
	for(int i=0; i<50; i++) {DistToHP(p, hp_pts/100.0, true);}
	
	for(int i=0; i<nb_stats; i++)
		coeff_std[i] = mob.stats[i] / nb_pts_ref;
	
	//double best_mob_victory = EstimateMonsterVictory(p,1000);
	//System.out.println("\nInitial mob victory = " + 100*best_mob_victory + "%");
	
	/*for(int i=0; i<nb_stats; i++)
	if( p.mob.stats[i] > 0.1)
		{
			System.out.println(Player.stats_name[i] + " [" + i + "]=" + p.mob.stats[i]);
		}
	*/
/*
	int count_bad = 0;
	double step = 1.0;
	while (step > 0.1)
	{
		System.out.println("Step = " + step);
		if (count_bad > 5) {step = step/1.2; count_bad = 0;}
		double tmp_result = ImproveMonster2(p,best_mob_victory,step);
		if(tmp_result < 0) count_bad++;
		else best_mob_victory = tmp_result;
	}
	System.out.println("BEST BUILD END");
	
	for(int i=0; i<nb_stats; i++)
		if( p.mob.stats[i] > 0.1 || p.mob.stats[i] <  nb_pts_ref*coeff_std[i] -1.0)
		{
			String str = " constant";
			if(p.mob.stats[i] <  nb_pts_ref*coeff_std[i] -1.0) str = " diminué (" + nb_pts_ref*coeff_std[i] + ")";
			if(p.mob.stats[i] >  nb_pts_ref*coeff_std[i] +1.0) str = " augmenté (" + nb_pts_ref*coeff_std[i] + ")";
			System.out.println(Player.stats_name[i] + " [" + i + "]=" + p.mob.stats[i] + str);
		}
		*/
}

}
