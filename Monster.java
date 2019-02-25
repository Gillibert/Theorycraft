import java.util.ArrayList;

class Monster extends Player {
//************* Table des monstres ***************
// Statistiques principales des monstres
    // IAS(0) DMG(1) REDUC(2) ABS(3) ESQ(4) PRC(5) LCK(6) CRT(7)
    // VDV(8) VITA(9) CON(10) REGEN(11) RESUR(12) LOAD(13) RUN(14) RESF()
    // RESF(15) MF(16) RF(17) QALF(18) QTYF(19) POWF(20) GF(21)
    // ED_MV(22) ED_ANI(23) ED_HUM(24) ED_PV(25) ED_DEM(26) ED_CHAMP(27)
    // ESTI(28) FLEE(29) FLEE_SPD(30) INIT(31)

public static double coeff_std[] = {1.3,2.1,0.9,0.2,0.7,0.7,0.5,0.5,0.0,2.2,0.9}; // 10 "points" sont distribués (+ ED_HUM et INIT)

public boolean masculin;
public int prefered_level;
public int[] prefered_stats;
public String nom_base;

// Boss
public Monster(String n, int p_level, int t_tag, int[] p_stats)
{
	super(false);
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
	
	double pts = ptsForLevel(level);
	for (int i=0; i<11; i++)
	    {stats[i]= pts*coeff_std[i]*0.08;}
  	stats[p_stats[0]]+=pts*0.80;
  	stats[p_stats[1]]+=pts*0.80;
  	stats[p_stats[2]]+=pts*0.80;
	
	stats[24]+=pts*0.12; //ED_HUM(24)
	stats[31]+=pts*0.15; //INIT(31)

	for(int i=0; i<nb_stats; i++)
		stats_with_bonus[i]=stats[i];
}

// Zoo monster
public Monster(String n, int p_level, int t_tag, int[] p_stats, boolean g)
	{
	super(false);
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

public static String[] adj_m = {"le déloyal", "le fourbe", "l'hypocrite", "l'insidieux", "le perfide", "le sourd", "le tortueux", "le traître", "le sadique", "le sournois", "le barbare", "le cruel", "le pervers", "le tortionnaire", "le vicieux", "le pestiféré", "le maudit", "le damné"};

public static MonsterSet[] ZooByLevel;
public static String[] MonsterNames;
public static ArrayList<String> MonsterNamesList;

public static Monster[] zoo = 
{
new Monster("Puce",          1, 1, new int[]{0,0,9},false),
new Monster("Mouche",        1, 1, new int[]{0,0,9},false),
new Monster("Souris",        2, 1, new int[]{4,0,6},false),
new Monster("Gerbille",      2, 1, new int[]{0,1,0},false),
new Monster("Grenouille",    3, 1, new int[]{0,1,0},false),
new Monster("Gerboise",      3, 1, new int[]{4,0,6},false),
new Monster("Moineau",       4, 1, new int[]{0,4,5},true),
new Monster("Pigeon",        4, 1, new int[]{0,4,5},true),
new Monster("Hamster",       5, 1, new int[]{0,4,6},true),
new Monster("Lapin",         5, 1, new int[]{0,4,5},true),
new Monster("Rat",           6, 1, new int[]{0,4,5},true),
new Monster("Chat",          6, 1, new int[]{0,0,4},true),
new Monster("Renard",        7, 1, new int[]{0,0,4},true),
new Monster("Chien",         7, 1, new int[]{1,0,9},true),
new Monster("Coyote",        8, 1, new int[]{1,0,9},true),
new Monster("Chauve-souris", 9, 1, new int[]{0,1,4},false),
new Monster("Chien-loup",    10+5*0, 1, new int[]{0,9,1},true),
new Monster("Dingo",         10+5*1, 1, new int[]{0,9,1},true),
new Monster("Loup",          10+5*2, 1, new int[]{0,9,1},true),
new Monster("Lynx",          10+5*3, 1, new int[]{0,1,4},true),
new Monster("Ours",          10+5*4, 1, new int[]{0,9,1},true),
new Monster("Grizzli",       10+5*5, 1, new int[]{0,9,1},true),
new Monster("Jaguar",        10+5*6, 1, new int[]{0,0,1},true),
new Monster("Puma",          10+5*7, 1, new int[]{0,0,1},true),
new Monster("Guépard",       10+5*8, 1, new int[]{0,0,1},true),
new Monster("Tigre",         10+5*9, 1, new int[]{0,0,1},true),
new Monster("Léopard",       10+5*10, 1, new int[]{0,0,1},true),
new Monster("Lion",          10+5*11, 1, new int[]{0,0,1},true),
new Monster("Rhinocéros",    10+5*12, 1, new int[]{1,9,0},true),
new Monster("Utahraptor",    10+5*13, 1, new int[]{0,9,1},true),
new Monster("Vélociraptor",  10+5*14, 1, new int[]{0,9,1},true),
new Monster("Diplodocus",    10+5*15, 1, new int[]{9,9,1},true),
new Monster("Gorgosaurus",   10+5*16, 1, new int[]{0,9,1},true),
new Monster("Allosaure",     10+5*17, 1, new int[]{1,9,1},true),
new Monster("Tyrannosaure",  10+5*18, 1, new int[]{1,9,0},true),
new Monster("Spinosaure",    10+5*19, 1, new int[]{1,9,0},true),
new Monster("Giganotosaure", 10+5*20, 1, new int[]{9,9,1},true),
new Monster("Harpie",        10+5*21, 1, new int[]{9,9,1},false),
new Monster("Griffon",       10+5*22, 1, new int[]{9,9,1},true),
new Monster("Sphinx",        10+5*23, 1, new int[]{1,9,1},true),
new Monster("Licorne",       10+5*24, 1, new int[]{1,0,9},false),
new Monster("Alicorne",      10+5*25, 1, new int[]{1,0,9},false),
new Monster("Manticore",     10+5*26, 1, new int[]{1,9,1},false),
new Monster("Kraken",        10+5*27, 1, new int[]{9,9,1},true),
new Monster("Léviathan",     10+5*28, 1, new int[]{9,1,0},true),
new Monster("Béhémoth",      10+5*29, 1, new int[]{1,1,9},true),
new Monster("Tarasque",      10+5*30, 1, new int[]{1,1,9},false),
new Monster("Oxydeur",       10+5*31, 1, new int[]{1,9,0},false),
new Monster("Demi-dragon",   10+5*32, 1, new int[]{9,1,0},true),
new Monster("Dragon",        10+5*33, 1, new int[]{9,1,0},true),
new Monster("Dracosire",     10+5*34, 1, new int[]{1,0,9},true),

new Monster("Sauvageon",    12+4*0, 2, new int[]{1,9,10},true),
new Monster("Voyou",        12+4*1, 2, new int[]{1,9,10},true),
new Monster("Cleptomane",   12+4*2, 2, new int[]{0,1,9},true),
new Monster("Délinquant",   12+4*3, 2, new int[]{0,1,9},true),
new Monster("Bagarreur",    12+4*4, 2, new int[]{0,1,9},true),
new Monster("Fripon",       12+4*5, 2, new int[]{0,1,9},true),
new Monster("Voleur",       12+4*6, 2, new int[]{1,1,1},true),
new Monster("Pillard",      12+4*7, 2, new int[]{1,9,0},true),
new Monster("Bandit",       12+4*8, 2, new int[]{1,0,9},true),
new Monster("Dévaliseur",   12+4*9, 2, new int[]{0,1,9},true),
new Monster("Malandrin",    12+4*10, 2, new int[]{1,0,7},true),
new Monster("Pirate",       12+4*11, 2, new int[]{1,0,7},true),
new Monster("Corsair",      12+4*12, 2, new int[]{1,6,7},true),
new Monster("Boucanier",    12+4*13, 2, new int[]{1,6,7},true),
new Monster("Flibustier",   12+4*14, 2, new int[]{1,0,4},true),
new Monster("Maraudeur",    12+4*15, 2, new int[]{1,6,7},true),
new Monster("Détrousseur",  12+4*16, 2, new int[]{1,9,0},true),
new Monster("Gangster",     12+4*17, 2, new int[]{1,9,0},true),
new Monster("Brigand",      12+4*18, 2, new int[]{1,0,4},true),
new Monster("Ninja",        12+4*19, 2, new int[]{1,6,7},true),
new Monster("Shinobi",      12+4*20, 2, new int[]{1,6,7},true),
new Monster("Inquisiteur",  12+4*21, 2, new int[]{1,1,0},true),
new Monster("Assassin",     12+4*22, 2, new int[]{1,1,0},true),
new Monster("Fanatique",    12+4*23, 2, new int[]{1,6,7},true),
new Monster("Démonologue",  12+4*24, 2, new int[]{0,1,9},true),
new Monster("Sataniste",    12+4*25, 2, new int[]{1,0,4},true),
new Monster("Sorcier",      12+4*26, 2, new int[]{1,0,4},true),
new Monster("Assassin",     12+4*27, 2, new int[]{1,0,5},true),
new Monster("Nizârien",     12+4*28, 2, new int[]{0,1,0},true),
new Monster("Hérétique",    12+4*29, 2, new int[]{9,0,1},true),
new Monster("Ensorceleur",  12+4*30, 2, new int[]{9,9,1},true),
new Monster("Magicien",     12+4*31, 2, new int[]{0,1,9},true),
new Monster("Géomancien",   12+4*32, 2, new int[]{0,1,9},true),
new Monster("Théurge",      12+4*33, 2, new int[]{9,1,9},true),
new Monster("Thaumaturge",  12+4*34, 2, new int[]{1,9,9},true),
new Monster("Barbare",      12+4*35, 2, new int[]{1,9,10},true),
new Monster("Rôdeur",       12+4*36, 2, new int[]{1,0,9},true),
new Monster("Guerrier",     12+4*37, 2, new int[]{1,0,9},true),
new Monster("Roublard",     12+4*38, 2, new int[]{1,1,0},true),
new Monster("Yakuza",       12+4*39, 2, new int[]{1,1,0},true),
new Monster("Nécromancien", 12+4*40, 2, new int[]{1,0,5},true),
new Monster("Élémentaliste",12+4*41, 2, new int[]{1,0,5},true),
new Monster("Mutant",       12+4*42, 2, new int[]{1,0,9},true),

new Monster("Snotlings",    10+10*0, 3, new int[]{0,6,7},true),
new Monster("Kobold",       10+10*1, 3, new int[]{1,6,7},true),
new Monster("Gobelin",      10+10*2, 3, new int[]{0,6,7},true),
new Monster("Subterranéen", 10+10*3, 3, new int[]{1,6,7},true),
new Monster("Hobgobelins",  10+10*4, 3, new int[]{0,6,7},true),
new Monster("Orc",          10+10*5, 3, new int[]{0,1,2},true),
new Monster("Uruk-hai",     10+10*6, 3, new int[]{0,1,2},true),
new Monster("Ork",          10+10*7, 3, new int[]{0,1,2},true),
new Monster("Demi-orc",     10+10*8, 3, new int[]{0,1,2},true),
new Monster("Gobelours",    10+10*9, 3, new int[]{1,2,3},true),
new Monster("Trolloïde",    10+10*10, 3, new int[]{1,9,10},true),
new Monster("Troll",        10+10*11, 3, new int[]{1,9,10},true),
new Monster("Gnoblars",     10+10*12, 3, new int[]{1,6,0},true),
new Monster("Géant vert",   10+10*13, 3, new int[]{1,0,9},true),
new Monster("Ogre",         10+10*14, 3, new int[]{1,9,0},true),
new Monster("Ent",          10+10*15, 3, new int[]{1,9,0},true),
new Monster("Reptilien",    10+10*16, 3, new int[]{1,6,0},true),
new Monster("Draconien",    10+10*17, 3, new int[]{1,6,0},true),
new Monster("Hulk",         10+10*18, 3, new int[]{1,6,0},true),

new Monster("Diablotin",      30+7*1,  4, new int[]{1,0,5},true),
new Monster("Cacodémon",      30+7*2,  4, new int[]{0,1,4},true),
new Monster("Mancubus",       30+7*3,  4, new int[]{0,1,4},true),
new Monster("Succube",        30+7*4,  4, new int[]{1,0,5},false),
new Monster("Yokai",          30+7*5,  4, new int[]{1,0,5},true),
new Monster("Ayakashi",       30+7*6,  4, new int[]{0,1,4},true),
new Monster("Oni",            30+7*7,  4, new int[]{1,0,5},true),
new Monster("Diable",         30+7*8,  4, new int[]{0,1,4},true),
new Monster("Archidiable",    30+7*9,  4, new int[]{1,0,5},true),
new Monster("Baal",           30+7*10, 4, new int[]{0,1,4},true),
new Monster("Asmodée",        30+7*11, 4, new int[]{1,0,5},true),
new Monster("Méphistophélès", 30+7*12, 4, new int[]{1,0,5},true),
new Monster("Bélial",         30+7*13, 4, new int[]{1,0,5},true),
new Monster("Belphégor",      30+7*14, 4, new int[]{0,1,4},true),
new Monster("Baphomet",       30+7*15, 4, new int[]{1,0,5},true),
new Monster("Satan",          30+7*16, 4, new int[]{1,0,5},true),
new Monster("Kreegan",        30+7*17, 4, new int[]{1,0,5},true),
new Monster("Stryge",         30+7*18, 4, new int[]{1,0,5},false),
new Monster("Diablo",         30+7*19, 4, new int[]{1,0,5},true),
new Monster("Balrog",         30+7*20, 4, new int[]{0,1,4},true),
new Monster("Azazel",         30+7*21, 4, new int[]{0,1,5},true),
new Monster("Moloch",         30+7*22, 4, new int[]{1,0,5},true),
new Monster("Sheitan",        30+7*23, 4, new int[]{1,0,5},true),
new Monster("Lucifer",        30+7*24, 4, new int[]{1,0,5},true),

new Monster("Squelette",   20+1*5, 0, new int[]{9,10,1},true),
new Monster("Décomposé",   20+2*5, 0, new int[]{9,10,1},true),
new Monster("Zombi",       20+3*5, 0, new int[]{9,10,1},true),
new Monster("Maccabé",     20+3*5, 0, new int[]{9,10,1},true),
new Monster("Cadavre",     20+5*5, 0, new int[]{9,10,1},true),
new Monster("Putréfié",    20+6*5, 0, new int[]{9,10,1},true),
new Monster("Fantôme",     20+7*5, 0, new int[]{0,2,4},true),
new Monster("Spectre",     20+8*5, 0, new int[]{0,2,4},true),
new Monster("Mort-né",     20+9*5, 0, new int[]{0,2,4},true),
new Monster("Poltergeist", 20+10*5, 0, new int[]{1,2,4},true),
new Monster("Ectoplasme",  20+11*5, 0, new int[]{1,2,4},true),
new Monster("Yurei",       20+12*5, 0, new int[]{1,2,4},true),
new Monster("Revenant",    20+13*5, 0, new int[]{1,2,4},true),
new Monster("Esprit ",     20+14*5, 0, new int[]{1,2,4},true),
new Monster("Vampire",     20+15*5, 0, new int[]{0,1,9},true),
new Monster("Nosferatu",   20+16*5, 0, new int[]{0,1,9},true),
new Monster("Loogaroo",    20+17*5, 0, new int[]{0,1,9},true),
new Monster("Nukekubi",    20+18*5, 0, new int[]{0,1,9},true),
new Monster("Âme perdue",  20+19*5, 0, new int[]{0,2,4},false),
new Monster("Mandurugo",   20+20*5, 0, new int[]{0,1,9},true),
new Monster("Âme en peine",20+21*5, 0, new int[]{0,2,4},false),
new Monster("Goule",       20+22*5, 0, new int[]{0,1,9},false),
new Monster("Âme errante", 20+23*5, 0, new int[]{0,2,4},false),
new Monster("Momie",       20+24*5, 0, new int[]{1,9,10},false),
new Monster("Moroaică",    20+25*5, 0, new int[]{0,1,9},false),
new Monster("Moroï",       20+26*5, 0, new int[]{0,1,9},true),
new Monster("Ubume",       20+27*5, 0, new int[]{1,0,10},false),
new Monster("Strigoi",     20+28*5, 0, new int[]{1,0,10},true),
new Monster("Âme damnée",  20+29*5, 0, new int[]{0,2,4},false),
new Monster("Liche",       20+30*5, 0, new int[]{1,9,10},false),
new Monster("Ombre",       20+31*5, 0, new int[]{1,9,10},false),
new Monster("Illusion",    20+32*5, 0, new int[]{0,0,0},false),
new Monster("Deadite",     20+33*5, 0, new int[]{0,0,0},true),
new Monster("Nazgûl",      20+34*5, 0, new int[]{1,0,10},true)
};

public static double ptsForLevel(int lvl) { return Math.pow(lvl,1.9)*0.15 + 4.0*lvl + 10.0; }
//(12+3 x+0.15 x^1.9)

public ArrayList<Item> drop(Player p)
{
  ArrayList<Item> res = new ArrayList<Item>();
  int count = StaticItem.getRandomInt(p.quantite_drop());
  int drplev = Math.min(StaticItem.max_level-1,level);
  
  if (tags[5]) count = 2*count; // Champion
  for(int i=0; i<count; i++)
	{
	    res.add(new Item(drplev,p));
	}
return res;
}

 public static void initZoo()
    {
	int ZooByLevelSize = 200;
	ZooByLevel = new MonsterSet[ZooByLevelSize];
	for (int i=0; i< ZooByLevel.length; i++)
	    ZooByLevel[i] = new MonsterSet();

	int mlv;
	for (int i=0; i<zoo.length; i++)
	    {
		mlv = zoo[i].prefered_level;
		for(int j=-2; j < 3; j++)
		    if(mlv+j > 0 && mlv+j < ZooByLevelSize)
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
	    }*/
    }

// Crée un monstre de niveau lev au hazard
public Monster(int lev)
	{
	super(false);
	stats_with_bonus = new double[nb_stats];
	stats = new double[nb_stats];

	// Sélectionne les monstres de bon niveau
	ArrayList<Monster> t_list = ZooByLevel[lev].list;
	Monster m = t_list.get((int)(Math.random()*t_list.size()));
	
	// Crée le monstre
	name = m.name;
	nom_base = m.name;
	if (m.prefered_level > lev)
		name += " " + faible(m.masculin);
	else if (m.prefered_level < lev)
		name += " " + fort(m.masculin);

	level = lev;
	tags = new boolean[nb_tags];
	for(int t=0; t<nb_tags; t++) tags[t]=m.tags[t];

	double pts;

	if(Math.random()>0.1) // Normal
		{
		pts = ptsForLevel(level);
		tags[5]=false;
		for (int i=0; i<11; i++)
		    {stats[i]= pts*coeff_std[i]*(Math.random()+3.0)*0.025;}

	  	stats[m.prefered_stats[0]]+=pts*0.25;
	  	stats[m.prefered_stats[1]]+=pts*0.20;
	  	stats[m.prefered_stats[2]]+=pts*0.15;

	  	stats[24]+=pts*0.12; //ED_HUM(24)
	  	stats[31]+=pts*0.15; //INIT(31)
		}
	else // Champion
		{
		tags[5]=true;
		name = StaticItem.nameGenerator.getName() + " " + adj_m[(int)(Math.random()*adj_m.length)];

		level = (int)(Math.pow(level,1.1)+1.0);
		pts = ptsForLevel(level);
		for (int i=0; i<11; i++)
		    {stats[i]= pts*coeff_std[i]*(Math.random()+5.0)*0.025;}
	  	stats[m.prefered_stats[0]]+=pts*0.35;
	  	stats[m.prefered_stats[1]]+=pts*0.34;
	  	stats[m.prefered_stats[2]]+=pts*0.33;
	
	  	stats[24]+=pts*0.20; //ED_HUM(24)
	  	stats[31]+=pts*0.20; //INIT(31)
		}
		
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

}
