import java.util.ArrayList;
import java.util.Collections;

class StaticItem {
    public static int RESSOURCE_ELEM = 0;
    public static int RESSOURCE_PRIM = 1;
    public static int RESSOURCE_ORB = 2;

    // 0 Casque
    // 1 Boucles d'oreilles
    // 2 Plastron
    // 3 Cape
    // 4 Ceinture
    // 5 Jambières
    // 6 Bottes
    // 7 Chaussettes
    // 8 Amulette
    // 9 Bague
    // 10 Brassards
    // 11 Gantelets
    // 12 Arme
    // 13 Fourreau
	// 14 Lunettes
    // 15 Ressources

    public static int nb_pos = Local.SLOT_NAME.length;
    public static int max_level = 301;
    public static NameGenerator nameGenerator = new NameGenerator(false);
    public static NameGenerator matNameGenerator = new NameGenerator(true);

	public static ArrayList<ArrayList<Material>> MaterialByType;
    public static ItemSet[] WhiteItemByPosition;
    public static ItemSet[] RessourceByLevel;
    public static String[] MaterialNames;
    public static String[] BaseItemNames;
    public static String[] OrbsNames;
    public static ArrayList MaterialNamesList;
    public static ArrayList BaseItemNamesList;
    public static ArrayList OrbsNamesList;

    public static double tailleMin(double a) {return Math.max(Math.floor(a)-1,0.001);}
    public static double tailleMax(double a) {return Math.floor(a)+2;}
    
    static int getRandomInt(double d)
    {
        double fpart = d-Math.floor(d);
        int bonus = 0;
        if (Math.random() <= fpart) bonus = 1;
        double plage = tailleMax(d)-tailleMin(d);
        return (int)(tailleMin(d)+bonus+(int)(Math.random() * plage));
    }
    
    public static Material[] MA = new Material[]{
    // type; coeffPoids; coeffPrix;     coeffSolidite; coeffEfficacite; coeffPuissance;
    new Material("ordure","d'ordures",0,          2.0,0.4,     0.2,0.15,0.05),
    new Material("détritus","de détritus",0,      2.0,0.4,     0.2,0.20,0.10),
    new Material("papier","de papier",0,          2.0,0.5,     0.2,0.25,0.15),
    new Material("boue","de boue",0,              2.0,0.5,     0.2,0.30,0.20),
    new Material("carton","de carton",0,          2.0,0.5,     0.2,0.35,0.25),
 
    new Material("feuilles","de feuilles",1,      0.5,0.6,     0.2,0.6,0.5),
    new Material("paille","de paille",1,          0.5,0.6,     0.2,0.7,0.4),
    new Material("lin","de lin",1,                1.5,0.6,     1.0,1.0,0.9),
    new Material("coton","de coton",1,            1.0,0.8,     1.2,1.1,1.0),
    new Material("laine","de laine",1,            1.0,0.8,     1.3,1.2,1.1),
    new Material("plume","de plumes",1,           0.5,1.8,     1.4,1.3,2.0),
    new Material("velours","de velours",1,        1.2,1.8,     1.5,1.4,2.4),
    new Material("résille","de résille",1,        0.4,1.4,     1.5,1.5,2.5),
    new Material("vair","de vair",1,              1.2,2.5,     1.5,1.6,2.6),
    new Material("hermine","d'hermine",1,         1.2,3.0,     1.0,1.7,2.8),
    new Material("chinchilla","de chinchilla",1,  1.3,3.5,     1.0,1.8,2.8),
    new Material("vison","de vison",1,            1.3,3.5,     1.0,1.9,2.9),
    new Material("skaï","de skaï",1,              2.0,1.8,     3.0,2.0,1.0),
    new Material("latex","de latex",1,            1.8,1.8,     3.0,2.5,1.0),
    new Material("cuir","de cuir",1,              2.0,2.5,     3.0,3.0,1.0),
    new Material("soie","de soie",1,              0.3,5.0,     1.2,3.5,1.3),
	
    new Material("cuivre", "de cuivre", 2,         1.0,0.6,     0.6, 0.6, 0.5),
    new Material("étain", "d'étain", 2,            1.0,0.7,     0.7, 0.7, 0.4),
    new Material("plomb", "de plomb", 2,           2.3,0.6,     0.8, 0.8, 0.7),
    new Material("bronze", "de bronze", 2,         1.1,0.6,     0.9, 0.9, 0.8),
    new Material("laiton", "de laiton", 2,         1.1,0.8,     1.0, 1.0, 0.9),
    new Material("zinc", "de zinc", 2,             0.7,0.9,     1.1, 1.1, 1.2),
    new Material("cobalt", "de cobalt", 2,         1.0,1.2,     1.2, 1.2, 1.3),
    new Material("mercure", "de mercure", 2,       2.9,4.1,     1.3, 1.3, 1.8),
    new Material("chrome", "de chrome", 2,         1.0,2.0,     1.4, 1.4, 1.4),
    new Material("argent", "d'argent", 2,          1.3,4.0,     1.5, 1.5, 1.7),
    new Material("or", "d'or", 2,                  2.8,5.0,     1.6, 1.6, 1.9),
    new Material("platine", "de platine", 2,       3.0,5.3,     1.7, 1.7, 2.0),
    new Material("fer", "de fer", 2,               1.0,0.9,     1.8, 1.8, 1.0),
    new Material("titane", "de titane", 2,         0.5,5.4,     1.9, 1.9, 1.0),
    new Material("plutonium", "de plutonium", 2,   3.0,4.5,     2.0, 2.0, 2.1),
    new Material("uranium", "d'uranium", 2,        3.0,4.6,     2.1, 2.1, 2.2),
    new Material("radium", "de radium", 2,         3.0,4.7,     2.2, 2.2, 2.3),
    new Material("iridium", "d'iridium", 2,        3.0,5.0,     2.3, 2.3, 2.4),
    new Material("acier", "d'acier", 2,            1.1,1.0,     2.4, 2.4, 1.2),
    new Material("palladium", "de palladium", 2,   3.0,4.0,     2.5, 2.6, 2.5),
    new Material("orichalque", "d'orichalque", 2,  2.4,5.0,     2.6, 2.7, 2.9),
    new Material("impossiblium", "d'impossiblium",2,1.7,5.0,    2.7, 2.8, 2.4),
    new Material("adamantium", "d'adamantium", 2,  1.0,4.0,     2.8, 2.9, 1.9),
    new Material("mithrol", "de mithrol", 2,       0.6,5.0,     2.9, 3.0, 2.4),
    new Material("mithrul", "de mithrul", 2,       0.6,5.0,     3.0, 3.1, 2.2),
    new Material("unobtainium", "d'unobtainium",2, 0.3,6.0,     3.1, 3.2, 2.2),
    new Material("mithral", "de mithral", 2,       0.5,6.0,     3.2, 3.3, 2.2),
    new Material("mithril", "de mithril",2,        0.5,6.0,     3.3, 3.5, 1.0),
    new Material("sombracier", "de sombracier",2,  1.3,3.0,     4.0, 3.5, 1.3), 

    new Material("plastique","de plastique",3,     2.0,0.5,     0.6,0.6,0.5),
    new Material("pâte à sel","de pâte à sel",3,   2.0,0.5,     0.6,0.7,0.4),
    new Material("verre","de verre",3,             2.0,0.5,     0.4,0.8,0.6),
    new Material("cristal","de cristal",3,         1.8,0.5,     0.4,0.9,0.7),
    new Material("quartz","de quartz",3,           1.7,0.5,     0.6,1.0,0.8),
    new Material("obsidienne","d'obsidienne",3,    2.0,0.5,     0.7,1.1,1.0),
    new Material("zircon","de zircons",3,          1.2,0.6,     0.7,1.2,1.2),
    new Material("jade","de jade",3,               1.6,0.7,     1.0,1.3,1.4),
    new Material("jais","de jais",3,               1.6,0.8,     1.8,1.4,1.6),
    new Material("nacre","de nacre",3,             2.2,0.9,     0.5,1.5,1.7),
    new Material("ambre","d'ambre",3,              2.3,1.0,     0.7,1.6,1.8),
    new Material("agate","d'agates",3,             1.2,1.1,     0.7,1.7,1.9),
    new Material("alexandrite","d'alexandrites",3, 1.2,1.2,     0.9,1.8,2.0),
    new Material("améthyste","d'améthystes",3,     1.2,1.3,     0.9,1.9,2.1),
    new Material("aventurine","d'aventurines",3,   1.2,1.4,     0.9,2.0,2.2),
    new Material("grenat","de grenats",3,          1.2,1.5,     0.9,2.0,2.3),
    new Material("malachite","de malachite",3,     1.2,1.6,     0.9,2.0,2.55),
    new Material("onyx","d'onyx",3,                1.2,1.7,     1.0,2.0,2.60),
    new Material("turquoise","de turquoises",3,    1.2,2.8,     1.0,2.0,2.70),
    new Material("opale","d'opales",3,             1.2,3.0,     1.0,2.0,2.80),
    new Material("saphir","de saphir",3,           1.0,3.6,     2.2,2.0,3.00),
    new Material("émeraude","d'émeraudes",3,       1.0,3.8,     3.4,2.0,3.05),
    new Material("rubis","de rubis",3,             1.0,4.0,     4.6,2.0,3.15),
    new Material("perle","de perles",3,            0.8,3.4,     0.6,2.0,3.20),
    new Material("diamant","de diamants",3,        1.0,5.0,     5.0,3.5,1.30),

    new Material("jour","de jour",4,                   0.02,4.2,    0.2,0.2,3.50),
    new Material("nuit","de nuit",4,                   0.02,4.2,    0.2,0.2,3.60),
    new Material("éther","d'éther",4,                  0.02,4.2,    0.2,0.2,3.70),
    new Material("lumière","de lumière",4,             0.02,4.2,    0.2,0.2,3.80),
    new Material("obscurité","d'obscurité",4,          0.02,4.2,    0.2,0.2,3.90),
    new Material("ombre","d'ombre",4,                  0.02,4.3,    0.2,0.2,4.00),
    new Material("cahos","de cahos",4,                 0.02,4.3,    0.2,0.2,4.05),
    new Material("ordre","d'ordre",4,                  0.02,4.3,    0.2,0.2,4.10),
    new Material("néant","de néant",4,                 0.02,4.4,    0.2,0.2,4.15),
    new Material("loyauté","de loyauté",4,             0.02,4.5,    0.2,0.2,4.20),
    new Material("générosité","de générosité",4,       0.02,4.6,    0.2,0.2,4.25),
    new Material("violence","de violence",4,           0.02,4.7,    0.2,0.2,4.30),
    new Material("éternité","d'éternité",4,            0.02,4.8,    0.2,0.2,4.35),
    new Material("froid","de froid",4,                 0.02,5.0,    0.2,1.3,4.40),
    new Material("chaleur","de chaleur",4,             0.02,5.0,    0.2,1.3,4.45),
    new Material("sécheresse","de sécheresse",4,       0.02,5.2,    0.2,1.3,4.50),
    new Material("humidité","d'humidité",4,            0.02,5.2,    0.2,1.3,4.55),
    new Material("entropie","d'entropie",4,            0.02,5.3,    0.2,0.2,4.60),
    new Material("amour","d'amour",4,                  0.02,5.4,    0.2,0.2,4.65),
    new Material("haine","de haine",4,                 0.02,5.5,    0.2,0.2,4.70),
    new Material("vengeance","de vengeance",4,         0.02,5.6,    0.2,0.2,4.75),
    new Material("désordre","de désordre",4,           0.02,5.7,    0.2,0.2,4.80),
    new Material("excellence","d'excellence",4,        0.02,5.8,    0.2,0.2,4.85),
    new Material("savoir","de savoir",4,               0.02,5.9,    0.2,0.2,4.90),
    new Material("vérité","de vérité",4,               0.02,6.0,    0.2,0.2,4.95),
    new Material("perfection","de perfection",4,       0.02,6.5,    0.4,0.5,5.00),
    new Material("illogisme","d'illogisme",4,          0.02,6.5,    0.4,5.00,0.5)
    };

    public static Material[] ORB = new  Material[]{
	new Material("orbe d'évolution","",5,            0.0002,60.0,     1.0,1.0,0.40),
    new Material("orbe d'augmentation","",5,         0.0002,55.0,     1.0,1.0,0.50),
    new Material("orbe de transmutation","",5,       0.0002,50.0,     1.0,1.0,0.60),
    new Material("orbe de transfert","",5,           0.0002,45.0,     1.0,1.0,0.70),
	new Material("orbe de fusion","",5,              0.0002,40.0,     1.0,1.0,0.80)
    };

    public static BaseItem[] BA = new  BaseItem[]{
    new BaseItem("bonnet","MS",            1,0,4,8,     0.15),
    new BaseItem("chapeau","MS",           1,0,4,11,    0.20),
    new BaseItem("béret","MS",             1,0,4,15,    0.20),
    new BaseItem("bicorne","MS",           1,0,4,21,    0.20),
    new BaseItem("tricorne","MS",          1,0,4,26,    0.20),
    new BaseItem("capuche","FS",           1,0,4,31,    0.15),
    new BaseItem("cagoule","FS",           1,0,4,36,    0.15),
    new BaseItem("casquette","FS",         1,0,4,41,    0.15),
    new BaseItem("képi","MS",              1,0,4,46,    0.20),
    new BaseItem("kippa","FS",             1,0,4,49,    0.15),
    new BaseItem("haut-de-forme","MS",     1,0,4,53,    0.25),
    new BaseItem("gibus","MS",             1,0,4,57,    0.25),    
    new BaseItem("perruque","FS",          1,0,4,60,    0.15),
    new BaseItem("bandeau","MS",           2,0,2,35,    0.75),
    new BaseItem("casque","MS",            2,0,2,40,    1.00),
    new BaseItem("bassinet","MS",          2,0,2,45,    1.50),
    new BaseItem("cabasset","MS",          2,0,2,50,    2.10),
    new BaseItem("barbute","FS",           2,0,2,52,    1.00),
    new BaseItem("tiare","FS",             2,0,2,55,    1.20),
    new BaseItem("couronne","FS",          2,0,2,50,    1.50),
    new BaseItem("cervelière","FS",        2,0,2,60,    2.00),

    new BaseItem("dormeuses","FP",         3,1,9,15,    0.02),
    new BaseItem("créoles","MP",           3,1,9,25,    0.02),
    new BaseItem("boucles","FP",           3,1,9,35,    0.02),
    new BaseItem("jhumka","MP",            3,1,9,45,    0.02),
    new BaseItem("magatama","MP",          3,1,9,60,    0.02),

    new BaseItem("t-shirt","MS",           1,2,4,8,     0.18),
    new BaseItem("chemise","FS",           1,2,4,9,     0.22),
    new BaseItem("robe","FS",              1,2,4,10,    0.30),
    new BaseItem("veste","FS",             1,2,4,12,    0.50),
    new BaseItem("imperméable","MS",       1,2,4,14,    0.70),
    new BaseItem("gilet","MS",             1,2,4,19,    0.75),
    new BaseItem("blouson","MS",           1,2,2,24,    1.20),
    new BaseItem("linothorax","MS",        2,2,2,29,    6.50),
    new BaseItem("cotte de mailles","FS",  2,2,2,39,    7.50),
    new BaseItem("haubert","MS",           2,2,2,44,    8.50),
    new BaseItem("demi-armure","FS",       2,2,2,46,    8.50),
    new BaseItem("broigne","FS",           2,2,2,48,    9.50),
    new BaseItem("gambison","MS",          2,2,2,50,    13.0),
    new BaseItem("brigandine","FS",        2,2,2,52,    12.0),
    new BaseItem("armure","FS",            2,2,2,54,    14.0),
    new BaseItem("harnois","MS",           2,2,2,56,    18.0),
    new BaseItem("armure de plaques","FS", 2,2,2,58,    20.0),
    new BaseItem("exosquelette","MS",      2,2,2,60,    15.0),

    new BaseItem("défroque","FS",          1,3,31,8,    0.20),
    new BaseItem("guenille","FS",          1,3,31,10,    0.30),
    new BaseItem("drap","FS",              1,3,31,16,    0.40),
    new BaseItem("couverture","FS",        1,3,31,24,    0.50),
    new BaseItem("toge","FS",              1,3,31,31,    0.60),
    new BaseItem("pelisse","FS",           1,3,31,41,    0.80),
    new BaseItem("pèlerine","FS",          1,3,31,45,    0.90),
    new BaseItem("manteau","MS",           1,3,31,50,    1.00),
    new BaseItem("pardessus","MS",         1,3,31,55,    1.00),
    new BaseItem("cape","FS",              1,3,31,60,    0.50),

    new BaseItem("ceinture","FS",          1,4,Universe.CON,50,            0.25),
    new BaseItem("ceinturon","MS",         1,4,Universe.CON,50,            0.25),
    new BaseItem("sac banane","FS",        1,4,Universe.LOAD,50,           0.40),
    new BaseItem("maru obi","MS",          1,4,Universe.ESQ,50,            0.12),
    new BaseItem("fukuro obi","MS",        1,4,Universe.OVERLOAD_RES,50,   0.12),
    new BaseItem("nagoya obi","MS",        1,4,Universe.OVERLOAD_RES,50,   0.12),
    new BaseItem("hanhaba obi","MS",       1,4,Universe.LIGHTER_RES,50,    0.12),
    new BaseItem("odori obi","MS",         1,4,Universe.LIGHTER_RES,50,    0.12),
    new BaseItem("tenga obi","MS",         1,4,Universe.LIGHTER_EQP,50,    0.12),
    new BaseItem("heko obi","MS",          1,4,Universe.LOAD,50,           0.12),
    new BaseItem("tsuke obi","MS",         1,4,Universe.LOAD,50,           0.12),

    new BaseItem("guêtres","FP",           1,5,Universe.ESQ,40,     0.20),
    new BaseItem("grèves","FP",            1,5,Universe.REDUC,40,   0.20),
    new BaseItem("kyahan","MP",            1,5,Universe.REDUC,40,   0.20),
    new BaseItem("ocreas","FP",            2,5,Universe.RUN,40,     0.90),
    new BaseItem("cnémides","FP",          2,5,Universe.RUN,40,     1.00),
    new BaseItem("jambières","FP",         2,5,Universe.ABS,40,     1.10),
    new BaseItem("suneate","MP",           2,5,Universe.ABS,40,     1.10),

	new BaseItem("escarpins","MP",         1,6,Universe.ESQ,50,    0.20),
    new BaseItem("chaussons","MP",         1,6,Universe.ESQ,50,    0.20),
    new BaseItem("charentaises","FP",      1,6,Universe.RUN,50,    0.20),
    new BaseItem("pantoufles","FP",        1,6,Universe.RUN,50,    0.20),
    new BaseItem("babouches","FP",         1,6,Universe.RUN,50,    0.20),
    new BaseItem("sandales","FP",          2,6,Universe.RUN,50,    0.40),
    new BaseItem("basquettes","FP",        1,6,Universe.RUN,50,    1.00),
    new BaseItem("souliers","FP",          2,6,Universe.ABS,50,    0.50),
    new BaseItem("galoches","FP",          2,6,Universe.ABS,50,    0.50),
    new BaseItem("sabots","MP",            2,6,Universe.REDUC,50,  1.20),
    new BaseItem("chaussures","FP",        2,6,Universe.REDUC,50,  1.10),
    new BaseItem("bottines","FP",          2,6,Universe.REDUC,50,  1.20),
    new BaseItem("bottes","FP",            2,6,Universe.REDUC,50,  1.30),

    new BaseItem("mini-socquette","FP",    1,7,Universe.ESQ,60,        0.04),
    new BaseItem("socquettes","FP",        1,7,Universe.INIT,60,       0.05),
    new BaseItem("mi-chaussettes","FP",    1,7,Universe.INIT,60,       0.06),
    new BaseItem("bas","MP",               1,7,Universe.TRAP_INIT,60,  0.06),
    new BaseItem("chaussettes","FP",       1,7,Universe.TRAP_INIT,60,  0.06),
    new BaseItem("mi-bas","MP",            1,7,Universe.RUN,60,        0.06),
    new BaseItem("tabis","FP",             1,7,Universe.RUN,60,        0.06),

    new BaseItem("pendentif","MS",         3,8,Universe.RESF,50,    0.09),
    new BaseItem("médaillon","MS",         3,8,Universe.MF,50,      0.09),
    new BaseItem("talisman","FS",          3,8,Universe.RF,50,      0.09),
    new BaseItem("collier","FS",           3,8,Universe.QALF,50,    0.09),
    new BaseItem("chapelet","FS",          3,8,Universe.QTYF,50,    0.10),
    new BaseItem("amulette","FS",          3,8,Universe.POWF,50,    0.10),
    new BaseItem("médaille","FS",          3,8,Universe.GF,50,      0.10),
    new BaseItem("sautoir","MS",           3,8,Universe.RENTE,50,   0.10),
    new BaseItem("solitaire","MS",         3,8,Universe.ECO_ORB,50, 0.10),
    
	new BaseItem("affûtiau","MS",          3,9,16,13,    0.05),
    new BaseItem("colifichet","MS",        3,9,17,13,    0.05),
    new BaseItem("babiole","FS",           3,9,18,13,    0.05),
	new BaseItem("demi-jonc","MS",         3,9,19,13,    0.05),
    new BaseItem("bagouse","FS",           3,9,20,13,    0.05),
    new BaseItem("bague","FS",             3,9,21,13,    0.05),
    new BaseItem("alliance","FS",          3,9,16,60,    0.05),
    new BaseItem("anneau","MS",            3,9,17,60,    0.05),
    new BaseItem("claddagh","MS",          3,9,18,60,    0.05),
    new BaseItem("chevalière","FS",        3,9,19,60,    0.05),

    new BaseItem("manchette","FS",         1,10,28,10,   0.09),
    new BaseItem("shamballa","MS",         3,10,32,30,   0.30),
    new BaseItem("protège-bras","MS",      2,10,4,30,    0.30),
    new BaseItem("semainier","MS",         2,10,0,30,    0.09),
    new BaseItem("montre","FS",            3,10,0,40,    0.06),
    new BaseItem("brassard","MS",          2,10,4,50,    0.50),
    new BaseItem("bracelet","MS",          3,10,0,55,    0.09),
    new BaseItem("smartwatch","FS",        3,10,0,60,    0.06),

    new BaseItem("mitons","MP",            1,11,0,10,    0.10),
    new BaseItem("mitaines","FP",          1,11,0,20,    0.10),
    new BaseItem("moufles","FP",           1,11,0,30,    0.10),
    new BaseItem("gants","MP",             1,11,0,40,    0.10),
    new BaseItem("power-glove","MS",       2,11,4,50,    0.30),
    new BaseItem("gantelets","MP",         2,11,4,60,    0.40),

    new BaseItem("bâton","MS",             2,12,1,6,     1.50),
    new BaseItem("canne","MS",             2,12,1,7,     1.40),
    new BaseItem("nunchaku","MS",          2,12,1,8,     1.00),
    new BaseItem("tomahawk","MS",          2,12,1,9,     1.20),
    new BaseItem("gourdin","MS",           2,12,1,10,    2.00),
    new BaseItem("fléau","MS",             2,12,1,11,    4.00),
    new BaseItem("masse","FS",             2,12,1,12,    4.00),
    new BaseItem("faux","FS",              2,12,1,13,    2.00),
    new BaseItem("kriss","MS",             2,12,1,14,    0.60),
    new BaseItem("glaive","MS",            2,12,1,15,    1.00),
    new BaseItem("cimeterre","MS",         2,12,1,16,    0.90),
    new BaseItem("machette","FS",          2,12,1,17,    1.10),
    new BaseItem("canif","MS",             2,12,1,18,    0.30),
    new BaseItem("faucille","FS",          2,12,1,19,    0.50),
    new BaseItem("serpe","FS",             2,12,1,20,    0.50),
    new BaseItem("couteau","MS",           2,12,1,21,    0.40),
    new BaseItem("bistouri","MS",          2,12,1,22,    0.10),
    new BaseItem("dague","FS",             2,12,1,23,    0.20),
    new BaseItem("baïonnette","FS",        2,12,1,24,    0.30),
    new BaseItem("poignard","MS",          2,12,1,26,    0.30),
    new BaseItem("tantö","MS",             2,12,1,28,    0.30),
    new BaseItem("wakizashi","MS",         2,12,1,30,    0.40),
    new BaseItem("sabre","MS",             2,12,1,32,    1.20),
    new BaseItem("épée","FS",              2,12,1,34,    1.20),
    new BaseItem("fleuret","MS",           2,12,1,36,    0.80),
	new BaseItem("morgenstern","MS",       2,12,1,38,    2.30),	
	new BaseItem("espadon","FS",           2,12,1,40,    1.30),	
	new BaseItem("javeline","FS",          2,12,1,42,    1.40),
	new BaseItem("javelot","MS",           2,12,1,44,    1.20),
	new BaseItem("claymore","FS",          2,12,1,46,    1.80),	
	new BaseItem("flamberge","FS",         2,12,1,48,    1.80),	
    new BaseItem("katana","MS",            2,12,1,50,    1.10),
    new BaseItem("hachette","FS",          2,12,1,52,    1.90),
    new BaseItem("hache","FS",             2,12,1,54,    1.90),
    new BaseItem("bardiche","FS",          2,12,1,56,    2.00),
    new BaseItem("francisque","FS",        2,12,1,58,    2.00),
    new BaseItem("lance","FS",             2,12,1,59,    1.40),
    new BaseItem("hallebarde","FS",        2,12,1,60,    2.50),

    new BaseItem("sangle","FS",            1,13,6,30,    0.10),
    new BaseItem("dragonne","FS",          1,13,7,50,    0.10),
    new BaseItem("étui","MS",              1,13,6,50,    0.20),
    new BaseItem("holster","MS",           1,13,7,60,    0.20),
    new BaseItem("fourreau","MS",          1,13,6,60,    0.30),
    
    new BaseItem("monocle","MS",           3,14,5,30,    0.05),
	new BaseItem("binocle","MS",           3,14,5,35,    0.05),
    new BaseItem("bésicles","FP",          3,14,5,40,    0.05),
    new BaseItem("lentilles","FP",         3,14,5,45,    0.05),
    new BaseItem("lunettes","FP",          3,14,5,50,    0.05),
    new BaseItem("lorgnon","MS",           3,14,5,55,    0.05),
    new BaseItem("lorgnette","FS",         3,14,5,60,    0.05)
    };

    public static Material getRandomOrb()
    {
    return ORB[(int)(Math.random()*ORB.length)];
    }

    public static Material getRandomMat()
    {
    return MA[(int)(Math.random()*MA.length)];
    }

    public static void init(Universe universe)
    {
	// Create MaterialNames, BaseItemNames, MaterialNamesList, BaseItemNamesList and OrbsNamesList array
	for(int i=0; i< MA.length; i++)
    MaterialNames = new String[MA.length];
    BaseItemNames = new String[BA.length];
	OrbsNames = new String[ORB.length];
    MaterialNamesList = new ArrayList<String>();
	OrbsNamesList = new ArrayList<String>();
    BaseItemNamesList = new ArrayList<String>();
        
    for(int i=0; i < BA.length; i++)
        {
        BaseItemNames[i]=BA[i].name;
        BaseItemNamesList.add(BA[i].name);
        }
        
    for(int i=0; i < MA.length; i++)
        {
        MaterialNames[i]=MA[i].name;
        MaterialNamesList.add(MA[i].name);
        }
		
	for(int i=0; i < ORB.length; i++)
        {
		OrbsNames[i]=ORB[i].name;
        OrbsNamesList.add(ORB[i].name);
        }
    
	// Create MaterialByType array
	MaterialByType = new ArrayList<ArrayList<Material>>();
	for(int i=0; i< Local.MATERIAL_FAMILY_NAME.length; i++)
	{
		ArrayList<Material> tmp = new ArrayList<Material>();
		for (int j=0; j< MA.length; j++)
		{
			if(MA[j].type == i)
				tmp.add(MA[j]);
		}
		MaterialByType.add(tmp);
	}
	// Create WhiteItemByPosition and RessourceByLevel
    WhiteItemByPosition = new ItemSet[nb_pos];
    RessourceByLevel = new ItemSet[max_level];

    for(int i=0; i< nb_pos; i++)
        WhiteItemByPosition[i] = new ItemSet();

    for(int i=0; i< max_level; i++)
        {
        RessourceByLevel[i] = new ItemSet();
        }

    for(int i=0; i < BA.length; i++)
	{
		if (universe.slot_est_disponible(BA[i].pos) == false) continue;
        Item it = new Item(BA[i],MA[0],universe);
		WhiteItemByPosition[it.pos].list.add(it);
	}
	
    for(int j=0; j < MA.length; j++)
        {
        Item it = new Item(MA[j],RESSOURCE_ELEM);
        RessourceByLevel[(int)it.effectiveIlvl()].list.add(it);
        it = new Item(MA[j],RESSOURCE_PRIM);
        RessourceByLevel[(int)it.effectiveIlvl()].list.add(it);
        }


   /*            
        for(int i=0; i< max_level; i++)
            {
            if(RessourceByLevel[i].list.size() > 0)
            {
            System.out.println("\nRessource Level " + i);
            for(Item it : RessourceByLevel[i].list)
                System.out.print(it.name + " ");
            }
            }*/
        
	
    }

    public static String rare_name()
    {
    return Local.RARE_NAMES[(int)(Math.random()*Local.RARE_NAMES.length)];
    }

}
