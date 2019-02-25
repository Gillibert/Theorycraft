import java.util.ArrayList;

public class Local {
	public static int LANG;
	public static String FONT_TIMES = "Times New Roman";
	public static String FONT_DIALOG = "Dialog";
	public static String H2S = "<h2>";
	public static String H2E = "</h2>";
	
	public static String[] TAGS_NAME;
	public static String[] SKILLS_NAME;
	public static String[] MATERIAL_FAMILY_NAME;
	public static String[] RARITY_NAME;
	public static String[] SLOT_NAME;
	public static String[] RARE_NAMES;
	public static String[] RULE_NO_YES;
	public static String[] RULE_LOGIC;
	public static String[] RULE_OPERATOR;
	public static String[] RULE_ITEM_TYPE_NAME;
	public static String[] RULE_MONSTER_TYPE_NAME;
	public static String[] RULE_PLAYER_TYPE_NAME;
	public static String[] LEVEL_UP_COLUMN_NAMES;
	public static String[] SHOP_SURNAMES;
	public static String[] TRAP_NAMES;
	public static String[] STOP_RULES_COLUMN_NAMES;
	public static String[] UNIVERSE_STATS_NAME;
	public static String[] UNIVERSE_EQUATIONS_NAME;
	public static String[] EDIT_UNIVERSE_COLUMN_NAMES;
	
	public static String COLON;
	public static String SECONDS;
	public static String RESET_BUILD;
	public static String UNIVERSE_CHANGE;
	public static String CHALLENGE_CHANGE;
	public static String LEVEL_UP;
	public static String EARN_EXPERIENCE;
	public static String FULL_HEAL;
	public static String DEATH_GOLD_LOSS;
	public static String DEATH_TIME_PENALTY;
	public static String RESURRECTION_TIME;
	public static String NECROPHAGY;
	public static String ALL;
	public static String EQUIPPED;
	public static String NOT_EQUIPPED;
	public static String MONSTER_TOO_STRONG;
	public static String MONSTER_TOO_WEAK;
	public static String LIFE_ANNUITY;
	public static String BUYING_OBJECTS;
	public static String SELLING_OBJECTS;
	public static String DROPPING_OBJECTS;
	public static String THROW_AWAY_OBJECTS;
	public static String PICKING_OBJECTS;
	public static String N_OBJECTS;
	public static String SHORT_INFOS;
	public static String LEVEL_N;
	public static String LOOKING_FOR_AN_ENNEMY;
	public static String LOOKING_FOR_A_TRADER;
	public static String ENCOUNTER;
	public static String LOOKING_FOR_A_MYSTIC_FORGE;
	public static String A_HITS_B;
	public static String MULTIPLIER;
	public static String A_VERSUS_B;
	public static String VERSUS;
	public static String INITIATIVES;
	public static String DODGES_THE_HIT;
	public static String CRITICAL_STRIKE;
	public static String DAMAGE_INFLICTED;
	public static String IMMUNITY_TO_FINAL_BLOW;
	public static String THORNS;
	public static String REPRISALS;
	public static String LIFE_LEECH_EFFECT;
	public static String TRY_TO_FLEE;
	public static String FLEE_SUCCESS;
	public static String FLEE_FAIL;
	public static String END_FIGHT_FLEE;
	public static String END_FIGHT_KILL;
	public static String END_FIGHT_DEATH;
	public static String NO_LOOT;
	public static String GOLD_LOOT;
	public static String ITEMS_LOOT;
	public static String OBJECTS_LEFT_BEHIND;
	
	public static String FIGHT;
	public static String TRAPS;
	public static String RESURRECTION_REGENERATION_PENALTIES;
	public static String SHOPPING_CRAFTING;
	public static String LOOKING_FOR_ENNEMY;
	public static String INITIATIVE;
	public static String ATTACK;
	public static String FLEE;
	public static String RESURRECTION;
	public static String REGENERATION;
	public static String PENALTIES;
	
	public static String LOOKING_FOR_TRADER;
	public static String LOOKING_FOR_MYSTIC_FORGE;
	public static String CRAFTING;
	
	public static String LOSSES_AND_EXPENSES;
	public static String GOLD_FROM_MONSTERS;
	public static String GOLD_FROM_LIFE_ANNUITY;
	public static String GOLD_FROM_SALES;
	public static String GOLD_FROM_SELLING_BASIC_OBJECTS;
	public static String GOLD_FROM_SELLING_MAGIC_OBJECTS;
	public static String GOLD_FROM_SELLING_RARE_OBJECTS;
	public static String GOLD_FROM_SELLING_RESOURCES;
	public static String GOLD_FROM_SELLING_OTHER;
	public static String LOSS_FROM_DEATH;
	public static String LOSS_FROM_SHOPPING;
	public static String EXPERIENCE_FROM_MONSTERS;
	public static String EXPERIENCE_FROM_TRAPS;
	public static String EXPERIENCE_FROM_ORBS;
	public static String MONSTERS_SKILLS_POINTS;
	public static String DIVINE_POINTS;
	public static String DIVINE_POINTS_ORBS;
	public static String DIVINE_POINTS_LEVEL;
	public static String MONSTER_LEVEL;
	public static String CHAMPIONS_LEVEL;
	public static String BASE_MONSTER_LEVEL;
		
	public static String CHAMPIONS_SKILLS_POINTS;
	public static String TRAPS_DAMAGE;
	public static String MONSTER_GOLD_LOOT;
	public static String GOLD_DROP_MIN;
	public static String GOLD_DROP_AVERAGE;
	public static String GOLD_DROP_MAX;
	public static String ATTACKS_PER_SECOND;
	public static String AVERAGE_DAMAGE_OF_A_BASIC_ATTACK;
	public static String CRITICAL_STRIKE_PROBABILITY;
	public static String CRITICAL_STRIKE_MULTIPLIER;
	public static String AVERAGE_DAMAGE_PER_ATTACK;
	public static String AVERAGE_DAMAGE_PER_SECOND;
	public static String DAMAGE_MULTIPLIER;
	public static String OF_LEVEL;
	public static String LOW_OF_LEVEL;
	
	public static String PROBABILITY_TO_HIT_A_MONSTER;
	public static String PROBABILITY_TO_DODGE_AGAINST_A_MONSTER;
	public static String PROBABILITY_TO_DODGE_AGAINST_PRECISION;
	public static String PROBABILITY_TO_HIT_AGAINST_DODGE;
	public static String DAMAGE_REDUCTION;
	public static String DAMAGE_TAKEN;
	public static String TRAPS_REDUCTION_MULTIPLIER;
	public static String TRAPS_DAMAGE_REDUCTION;
	public static String DAMAGE_ABSORPTION;
	public static String HEALTH_POINTS_PER_VITALITY_POINT;
	public static String HEALTH_POINTS;
	public static String EFFECTIVE_HEALTH_POINTS;
	public static String HEALTH_POINTS_PER_SECOND;
	public static String TOTAL_REGENERATION;
	public static String LIFE_LEECH;
	public static String TIME_BEFORE_THE_FIRST_ATTACK;
	public static String INITIATIVE_BONUS_AGAINST_A_TRAP;
	public static String INITIATIVE_TIME_AGAINST_A_TRAP;
	public static String FINDING_A_MONSTER;
	public static String FINDING_A_TRADER;
	public static String FINDING_A_FORGE;

	public static String MAXIMUM_LOAD;
	public static String SALE_PRICE_MULTIPLIER;
	public static String BUY_PRICE_MULTIPLIER;
	public static String PROBABILITY_TO_FLEE;
	public static String TIME_TO_FLEE;
	public static String PROBABILITY_OF_AN_OBJECT_BEING_MAGIC;
	public static String PROBABILITY_OF_AN_OBJECT_BEING_RARE;
	public static String PROBABILITY_OF_AN_OBJECT_BEING_HIGH_QUALITY;
	public static String GOLD_MULTIPLIER;
	public static String RESOURCES_MULTIPLIER;
	public static String AVERAGE_MONSTER_LOOT;
	public static String TOTAL_NUMBER_OF_ITEMS;
	public static String NUMBER_OF_RESOURCES;
	public static String NUMBER_OF_NONRESOURCE_OBJECTS;
	public static String TOTAL_QUANTITY_OF_RESOURCES;
	public static String NUMBER_OF_MAGIC_OBJECTS;
	public static String NUMBER_OF_RARE_OBJECTS;
	public static String PROBABILITY_OF_DODGING_A_FINAL_BLOW;
	public static String TIME_MULTIPLIER;
	public static String DAMAGE_INFLICTED_ON_ATTACKERS;
	public static String DAMAGE_REFLECTED_ON_ATTACKERS;
	public static String A_KILLED_MONSTER_GIVES;
	public static String CRAFTING_TIME;
	public static String CRAFTING_EFFICIENCY;
	public static String ORB_COST;
	public static String AVERAGE_LEVEL_OF_MERCHANTS;
	public static String NUMBER_OF_MERCHANTS_ITEMS;
	public static String MIN_NUMBER_OF_MERCHANTS_ITEMS;
	public static String MAX_NUMBER_OF_MERCHANTS_ITEMS;
	
	public static String PROBABILITY_OF_DETECTING_A_TRAP;
	public static String INCOME_PER_SECOND;
	public static String SKILL_POINTS_PER_LEVEL;
	public static String GENERAL_EXPERIENCE_BONUS;
	public static String EXPERIENCE_BONUS_ON_A_MONSTER;
	public static String PENALTIES_REDUCTION;
	public static String MULTIPLIER_VERSUS;
	public static String TIME_GLOBAL;
	public static String TIME_FIGHT_TRAPS;
	public static String TIME_LIFE_MANAGEMENT;
	public static String TIME_SHOPPING_CRAFTING;
	public static String INCOME_GLOBAL;
	public static String INCOME_SALES;
	public static String EXPERIENCE;
		
	public static String HEALTH_POINTS_FOR_A_CONSTITUTION_OF;
	public static String RESURRECTION_TIME_CURV;
	public static String PROBABILITY_THAT_A_BASIC_OBJECT_BECOMES_MAGIC;
	public static String PROBABILITY_THAT_A_MAGIC_OBJECT_BECOMES_RARE;
	public static String AVERAGE_NUMBER_OF_ITEMS_ON_A_MONSTER;
	public static String MINIMUM_NUMBER_OF_ITEMS_ON_A_MONSTER;
	public static String MAXIMUM_NUMBER_OF_ITEMS_ON_A_MONSTER;
	public static String MINIMUM_ENCHANTMENT_POWER;
	public static String MAXIMUM_ENCHANTMENT_POWER;
	public static String PROPORTION_OF_THE_CADAVERS_HEALTH_POINTS_ABSORBED;
	public static String COST_IN_ORBS_OF_AN_ORB;
	public static String AVERAGE_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL;
	public static String MINIMUM_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL;
	public static String MAXIMUM_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL;
	public static String EXPERIENCE_SPECIFIC_MULTIPLIER;
	public static String PENALTY_COEFFICIENT;
	
	public static String PLAYER_INFOS;

	
	public static String WINDOW_CURVES;
	public static String WINDOW_INVENTORY;
	public static String WINDOW_SHOP;
	public static String WINDOW_FORGE;
	public static String WINDOW_INVENTORY_PROMPT;
	public static String EQUIP;
	public static String UNEQUIP;
	public static String SPLIT;
	public static String DISCARD;
	public static String BUY;
	public static String SALE;
	public static String DEPOSIT;
	public static String PICK_UP;
	public static String COMBINE;
	
	public static String TYPE_VAL;
	public static String BASE_VAL;
	public static String EFFECTIVE_LEVEL_VAL;
	public static String SLOT_VAL;
	public static String QUALITY_VAL;
	public static String EFFICIENCY_VAL;
	public static String ENCHANTMENT_VAL;
	public static String NUMBER_OF_ENCHANTMENTS_VAL;
	public static String SKILL_BONUS_VAL;
	public static String QUANTITY_VAL;
	public static String WEIGHT_VAL;
	public static String REAL_PRICE_VAL;
	public static String SELLING_PRICE_VAL;
	public static String PURCHASE_PRICE_VAL;
	public static String FAMILY_VAL;
	public static String WEIGHT_COEFFICIENT_VAL;
	public static String PRICE_COEFFICIENT_VAL;
	public static String EFFICIENCY_COEFFICIENT_VAL;
	public static String ENCHANTMENT_COEFFICIENT_VAL;

	
	public static String WE_START_GENTLY;
	public static String DUNGEONS_AND_DRAGONS;
	public static String INFERNO;
	public static String SKY_IS_THE_LIMIT;
	public static String THIS_IS_SPARTA;
	public static String BEGGAR;
	public static String GROCER;
	public static String TRADER;
	public static String BANKER;
	public static String BILL_GATES;
	public static String WIRT_THE_PEG_LEG;
	public static String OVER_9000;
	public static String THE_BUNNY_MUST_DIE;
	public static String BUNNY_BOSS;
	public static String A_PORCUPINE_THAT_STINGS;
	public static String THE_PORCUPINE_BOSS;
	public static String GHOSTBUSTERS;
	public static String CASPER_BOSS;
	public static String KILL_BILL;
	public static String BILL_BOSS;
	public static String GOAL;
	public static String REACH_LEVEL;
	public static String WIN_GOLD_COINS;
	public static String DEFEAT_BOSS;
	public static String LEVEL_REACHED_VICTORY;
	public static String YOU_MUST_BE_AT_LEAST_LEVEL;
	public static String YOU_POSSESS_GOLD_COINS_VICTORY;
	public static String YOU_MUST_POSSESS_AT_LEAST;
	
	public static String CHOOSE_A_SEED;
	public static String CREATE_A_UNIVERSE;
	public static String HISCORES_CR;
	public static String SEED;
	public static String SEED_DOTS;
	public static String CHOOSE_A_CHALLENGE;
	public static String CHOICE_OF_THE_CHALLENGE;
	public static String CHOOSE;
	public static String BEST_TIMES;
	public static String HISCORES;
	public static String CREATE;
	public static String CANT_WRITE_IN_HISCORES;
	public static String ERROR_LOADING_HISCORES;
	public static String CANT_WRITE_IN_LOG;
	public static String CONGRATULATIONS_HISCORES;
	public static String HISCORES_LINE;
	public static String RULE_NEGATION;
	public static String INVALID_RULE;
	
	public static String BAY;
	public static String ISLAND;
	public static String ISLANDS;
	public static String LAKE;
	public static String FORTRESS;
	public static String TREE_CITY;
	public static String MASSIF ;
	public static String FOREST;
	public static String CAPE;
	public static String CAVES;
	public static String MOUNTAINS;
	public static String ROADSTEAD;
	public static String WOOD;
	public static String ARENA;
	public static String MOUTH;
	public static String JUNCTION;
	public static String RIFT;
	public static String RAVINE;
	public static String SWAMP;
	public static String LAGOON;
	public static String BRIDGE;
	public static String BASE_EXPERIENCE;
	public static String BASE_EXPERIENCE_TRAP_LEVEL;
	public static String BASE_EXPERIENCE_MONSTER_LEVEL;
	
	public static String DISTRIBUTION_OF_GOD_POINTS;
	public static String DISTRIBUTION_OF_SKILL_POINTS;
	public static String PLUS;
	public static String MINUS;
	public static String VALIDATE;
	public static String CANCEL;
	public static String SKILL_POINTS_DISTRIBUTION;
	public static String UNIVERSE_EDITION;
	
	public static String ERROR_INVALID_NUMBER;
	public static String ERROR_EMPTY_NAME;
	public static String ERROR_SELF_REFERENCE;
	public static String RULES;
	public static String RULE_ON_AN_OBJECT;
	public static String RULE_ON_A_MONSTER;
	public static String RULE_ON_THE_PLAYER;
	public static String COMPOUND_RULE;
	public static String NEGATION;
	public static String AND;
	public static String OR;
	public static String SELL_RULE;
	public static String PURCHASE_RULE;
	public static String PICKUP_RULE;
	public static String INVENTORY_FILTER;
	public static String FLEE_RULE;
	public static String NON_INVOLVEMENT_RULE;
	public static String MERCHANT_SEARCH;
	public static String SAVE;
	public static String NEW_RULE;
	public static String REMOVE;
	public static String EXPORT;
	public static String IMPORT;
	public static String RULE;
	public static String PROGRAMMING;
	public static String SELECT_A_ZONE;
	public static String WORLD_MAP;
	
	public static String SAVING_GAME;
	public static String CANT_WRITE;
	public static String THEORYCRAFT;
	
	public static String CHOOSE_A_NAME;
	public static String CREATION_OF_A_HERO;
	public static String HERO_SELECTION;
	public static String LOAD_A_HERO;
	public static String CREATE_A_HERO;
	public static String DELETE_A_HERO;
	public static String SEED_TIME;
	
	public static String YOU_COME_ACROSS;
	public static String SUCCESSFUL_TRAP_DETECTION;
	public static String COMPLETELY_AVOIDS;
	public static String FAILED_DETECTION;
	public static String TRAP_RESISTANCE_DOUBLED;
	public static String IS_DEAD;

	public static String THEORYCRAFT_TITLE;
	public static String HEALTH_POINTS_LEFT;
	public static String DISABLE;
	public static String ENABLE;
	public static String STOP_FIGHT;
	public static String NEXT_LEVEL;
	public static String GAME;
	public static String LOAD_A_GAME;
	public static String EXIT;
	public static String CHALLENGE;
	public static String CHANGE_THE_CHALLENGE;
	public static String COMPLETE_THE_CHALLENGE;
	public static String TRAVEL;
	public static String TRAVEL_TO;
	public static String INVENTORY;
	public static String SHOPPING;
	public static String SKILLS;
	public static String CHARTS;
	public static String INFORMATIONS;
	public static String UNIVERSE_INFO;
	public static String NEW_GAME;
	public static String STOP_TO_FIGHT;
	public static String DEFEATS;
	public static String DEFEATED_ENEMIES;
	public static String LEVELS_WON;
	public static String LETHAL_TRAPS;
	public static String SURVIVED_TRAPS;
	public static String YOU_HAVE_ALREADY_FINISHED_THE_GAME;
	public static String YOU_HAVE_DEFEATED_THE_FINAL_BOSS;
	public static String TIME_PASSED;
	public static String AVOIDS_MONSTER;
	
	public static String HTML_BODY;
	public static String PLAYER_STATISTICS;
	public static String PLAYER_STATISTICS_LIST;
	public static String UNIVERSE_INFORMATION_LIST;
	public static String H3_AVAILABLE_OBJECTS_H3;
	public static String H3_UNAVAILABLE_OBJECTS_H3;
	public static String BASE_DISTRIBUTION_OF_MONSTER_SKILL_POINTS;
	public static String LI_SKILL_POINTS;
	public static String LI_END;
	public static String H3_ZONE_INFORMATION_H3;
	public static String NUMBER_OF_ZONES;
	public static String DL;
	public static String DT_ZONE;
	public static String NO_LOOT_Z;
	public static String TRAPS_AND_CHAMPION_PROBABILITY;
	public static String MONSTER_LEVEL_MINIMUM_AVERAGE_MAXIMUM;
	public static String CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM;
	public static String DL_END;
	public static String H2_CRAFTING_H2;
	public static String BODY_HTML_END;
	public static String INFORMATION_AND_STATISTICS;
	public static String FIGHT_STATS;
	public static String FIGHT_STATS_LIST;
	public static String UL_END;
	public static String ALCHEMICAL;
	public static String MAX_ZONE_LEVEL;
	public static String ZONE_LEVEL_MULTIPLIER;
	public static String DIVINE_POINTS_MULTIPLIER;
	public static String FIRST_STRIKE_MULTIPLIER;
	public static String EQUATION_CAP_MULTIPLIER;
	public static String CONST_CAP_MULTIPLIER;
	public static String FIRST_STRIKE;
	public static String ALLOY;
	public static String OF_ALLOY;
	public static String ZONE_LEVEL;
	public static String RESOURCES_WEIGHT_MULTIPLIER;
	public static String EQUIPMENT_WEIGHT_MULTIPLIER;
	public static String MAX_LEVEL;
	public static void init(int lang)
	{
	if(lang == 0)
	{
		LANG = lang;
		String[] tmp1 = {"Mort-vivant","Animal","Humain","Peau-verte","Démon","Champion"};
		TAGS_NAME = tmp1;
		String[] tmp2 = {"Vitesse d'attaque", "Dégâts", "Réduction", "Absorption", "Esquive", "Précision",
		"Chance de coup critique", "Dégâts des coups critiques", "Vol de vie", "Vitalité", "Constitution", "Régénération", 
		"Résurrection", "Charge maximale", "Vitesse de marche", "Recherche de ressources","Recherche d'objets magiques",
		"Recherche d'objets rares","Recherche de qualité","Recherche de quantité","Recherche de puissance","Recherche d'or",
		"Dégâts aux morts-vivants","Dégâts aux animaux","Dégâts aux humains","Dégâts aux peaux-vertes","Dégâts aux démons",
		"Dégâts aux champions","Commerce","Chance de fuite","Vitesse de fuite","Initiative","Immunité au coup final",
		"Vitesse du temps","Épines","Représailles","Nécrophagie",
		"Vitesse du craft", "Rendement du craft", "Économie d'orbes", 
		"Niveau des marchands", "Inventaire des marchands", 
		"Détection des pièges", "Bonus d'initiative face aux pièges", "Bonus de résistance aux pièges","Rente viagère","Éducation", "Apprentissage", "Overkilling", "Hardiesse", "Réduction des pénalités", "Accès étendu", "Divinité", "Premier coup", "Maîtrise des équations", "Maîtrise des constantes", "Allégement des ressources", "Allégement de l'équipement"};
		SKILLS_NAME = tmp2;
		
		String[] tmp3 = {"déchet","tissu/cuir","métal","pierre précieuse","conceptuel","alliage"};
		MATERIAL_FAMILY_NAME = tmp3;
		
		String[] tmp4 = {"objet normal","objet magique","objet rare","objet légendaire","ressource élémentaire","ressource primordiale","orbe"};
		RARITY_NAME = tmp4;
		
		String[] tmp5 = {"Casque","Boucles d'oreilles","Plastron", "Cape", "Ceinture", "Jambières", "Bottes", "Chaussettes", "Amulette", "Bague", "Brassards", "Gantelets", "Arme", "Fourreau","Lunettes","Ressources"};
		SLOT_NAME = tmp5;
		
		String[] tmp6 = {"légende","héritage","relique","trophée","souvenir","réminiscence","vestige","trésor","richesse","prodige","idéal","miracle","fortune"};
		RARE_NAMES = tmp6;
		
		String[] tmp7 = {"non","oui"};
		RULE_NO_YES = tmp7;
		
		String[] tmp8 = {"et","ou","non"};
		RULE_LOGIC = tmp8;
		
		String[] tmp9 = {"=","<",">","≠"};
		RULE_OPERATOR = tmp9;
		
		String[] tmp10 = 
		{
		"Type d'objet", // objet.rare
		"Type de matériau", // objet.material.type
		"Emplacement", // objet.pos 
		"Qualité", // objet.quality 
		"Poids", //objet.poids
		"Prix réel", //objet.prix 
		"Niveau effectif", // objet.effectiveIlvl()
		"Efficacité",  // ilvl*material.coeffEfficacite*base_power
		"Enchantement", // elvl*material.coeffPuissance
		"Efficacité de base", // objet.ilvl
		"Enchantement de base",  // objet.elvl
		"Coefficient de poids",  // material.coeffPoids
		"Coefficient de prix",  // material.coeffPrix
		"Coefficient d'efficacité",  // material.coeffEfficacite
		"Coefficient magique",  // material.coeffPuissance
		"Nom de l'objet de base", // objet.baseItem.name
		"Nom du matériau", // objet.material.name
		"Equipé" // objet.equiped
		};
		RULE_ITEM_TYPE_NAME = tmp10;

		String[] tmp11 = 
		{
		"Niveau", // level
		"Type de monstre", // nom_base
		"Famille", // type
		"Attaques par seconde", // att_per_sec()
		"Dégâts moyens d'une attaque basique", // dmg_base()
		"Probabilité de coup critique (%)", // 100.0*crit_proba()
		"Multiplicateur des coups critiques", // multi_crit(),
		"Dégâts moyens d'une attaque", // dmpa
		"Dégâts moyens par seconde", // att_per_sec()*dmpa
		"Multiplicateur versus humains", // ed_versus_tag(2)
		"Réduction des dégâts (%)", // 100.0-(100.0*reduc())
		"Absorption des dégâts", //absorption()
		"Points de vie actuels", // vie()
		"Points de vie", // vie_max()
		"Points de vie effectifs", // vie_max()/reduc()
		"Dégats infligés aux attaquants", //epines()
		"Dégats réfléchis sur les attaquants (%)" // 100*represailles()
		};
		RULE_MONSTER_TYPE_NAME = tmp11;
		
		String[] tmp12 = 
		{
		"Niveau", // level
		"Charge actuelle", // charge
		"Charge restante", // charge_max()-charge
		"Or transporté", // money
		"Attaques par seconde", // att_per_sec()
		"Dégâts moyens d'une attaque basique", // dmg_base()
		"Probabilité de coup critique (%)", // 100.0*crit_proba()
		"Multiplicateur des coups critiques", // multi_crit(),
		"Dégâts moyens d'une attaque", // dmpa
		"Dégâts moyens par seconde", // att_per_sec()*dmpa
		"Multiplicateur versus humains", // ed_versus_tag(2)
		"Réduction des dégâts (%)", // 100.0-(100.0*reduc())
		"Absorption des dégâts", //absorption()
		"Points de vie actuels", // vie()
		"Points de vie", // vie_max()
		"Points de vie effectifs", // vie_max()/reduc()
		"Dégats infligés aux attaquants", //epines()
		"Dégats réfléchis sur les attaquants (%)" // 100*represailles()
		};
		RULE_PLAYER_TYPE_NAME = tmp12;
	
		String[] tmp13 = {"Compétence","Points","Equipement","Total"};
		LEVEL_UP_COLUMN_NAMES = tmp13;
		
		String[] tmp14 = {"l'Escroc","le Grippe-sou","le Radin","le Rapiat","l'Avare","l'Avide","le Cupide","le Rapace"};
		SHOP_SURNAMES = tmp14;
		
		String[] tmp15 = {"Toile d'araignée", "Papier tue mouche", "Pic anti-pigeons", "Piège à souris", "Caillou pointu", "Collet à lapins", "Piège à taupe", "Pierre d'achoppement", "Piège à rats", "Pluie acide", "Cactus", "Fil barbelé", "Makibishi", "Crevasse", "Glissement de terrain", "Feu de brousse", "Petit séisme", "Incendie de forêt", "Chausse-trape", "Fosse profonde", "Fosse piégée", "Fil barbelé concertina", "Fléchette empoisonnée", "Flèche empoisonnée", "Fosse d'acide", "Note explosive", "Scie circulaire", "Toile d'araignée géante", "Liane étrangleuse", "Piège à loup", "Piège à ours", "Piège à lion", "Colis piégé", "Arbalète automatique", "Fosse de lave", "Sable mouvant", "Tempête de glace", "Mine antipersonnel", "Mine antichar", "Pluie de feu", "Lac de lave", "Avalanche", "Tornade", "Avalanche de rochers", "Tempête de sable", "Tornade de sable", "Tempête de feu", "Geyser de lave", "Éruption volcanique", "Chute de météorite", "Chute de comète", "Tremblement de terre", "Tsunami", "Mine atomique"};
		TRAP_NAMES = tmp15;
		
		String[] tmp16 = {"Action","Condition","Valeur","Activé"};
		STOP_RULES_COLUMN_NAMES = tmp16;
		
		String[] tmp18 = {"Constante de l'univers","Valeur de base","Valeur","Modificateur","Limite"};
		EDIT_UNIVERSE_COLUMN_NAMES = tmp18;
		
		String[] tmp19 = {"Vie de départ",
		"Probabilité de tomber sur un piège",
		"Puissance maximale des enchantements",
		"Points de compétences initiaux",
		"Pénalité d'or à chaque mort",
		"Probabilité de tomber sur un champion",
		"Coût d'un voyage dimensionnel",
		"Plage aléatoire",
		"Pénalité pour les matériaux inappropriés",
		"Pénalité de temps pour changer de défi",
		"Pénalité de temps d'un voyage",
		"Pénalité de temps d'un voyage dimensionnel",
		"Pénalité de temps à chaque mort",
		"Coefficient d'efficacité de base",
		"Probabilité qu'un objet soit une ressource",
		"Qualité maximale des objets"};
		UNIVERSE_STATS_NAME = tmp19;
		
		COLON = " :";
		SECONDS = "secondes";
		ALL = "Tout";
		EQUIPPED = "Équipés";
		NOT_EQUIPPED = "Non équipés";
		MONSTER_TOO_STRONG = "Monstre trop fort";
		MONSTER_TOO_WEAK = "Monstre trop faible";
		LIFE_ANNUITY = "%s recoit %.2f écus de rente (%.2f secondes x %.2f écus/secondes)";
		BUYING_OBJECTS = "Achat de %s à %s pour %.2f écus";
		SELLING_OBJECTS = "Vente de %s à %s pour %.2f écus";
		DROPPING_OBJECTS = "%s place %s dans la forge mystique";
		THROW_AWAY_OBJECTS = "%s jette %s";
		PICKING_OBJECTS = "%s récupère %s depuis la forge mystique";
		N_OBJECTS = "%d objets";
		SHORT_INFOS = "Niveau %d\nTemps total de jeu : %.2f secondes\nOr : %.2f écus\nCharge : %.2f / %.2f\n";
		LEVEL_N = "Niveau %d\n";
		
		LOOKING_FOR_AN_ENNEMY = "Recherche d'un ennemi : %.3f secondes";
		LOOKING_FOR_A_TRADER = "Recherche d'un marchand : %.3f secondes";
		ENCOUNTER = "%s rencontre %s (niveau %d)";
		LOOKING_FOR_A_MYSTIC_FORGE = "Recherche d'une forge : %.3f secondes";
		A_HITS_B = "%s frappe %s en %.3f secondes (temps total pour %s : %.3f)\n";
		A_VERSUS_B = "%s versus %s (niveau %d)";
		VERSUS = "Versus";
		INITIATIVES = "Initiatives : %.3f pour %s, %.3f pour %s";
		MULTIPLIER = "   Multiplicateur ×%.2f (%s)\n";
		DODGES_THE_HIT = "   %s esquive le coup de %s (%.2f%% de probabilité)";
		CRITICAL_STRIKE = "   Coup critique ×%.2f (%.2f%% de probabilité)\n";
		DAMAGE_INFLICTED = "   %s inflige %.3f points de dégâts à %s (%.2f de base, %.2f%% de réduction, %.2f d'absorption)";
		IMMUNITY_TO_FINAL_BLOW = "\n   L'immunité au coup final sauve %s, il conserve 0.1 point de vie (probabilité : %.2f%%)";
		THORNS = "\n   Les épines de %s infligent %.3f points de dégâts à %s (%.2f de base, %.2f%% de réduction)";
		REPRISALS = "\n   Les représailles de %s causent %.3f points de dégâts à %s (%.2f de base, %.2f%% de réduction)";
		LIFE_LEECH_EFFECT = "\n   %s récupère %.3f points de vie (vol de vie)";
		
		TRY_TO_FLEE = "%s tente de fuire face à %s (%.3f secondes)";
		FLEE_SUCCESS = "   Succés de la fuite (%.2f%% de probabilité)";
		FLEE_FAIL = "   Échec de la fuite (%.2f%% de probabilité)";
		END_FIGHT_FLEE = "%s fuit face à %s (temps du combat %.3f secondes, vie restante %.3f)";
		END_FIGHT_KILL = "%s tue %s (temps du combat %.3f secondes, vie restante %.3f)";
		END_FIGHT_DEATH = "%s est tombé face à %s de niveau %d (temps du combat %.3f secondes)";
		
		NO_LOOT = "Pas de butin sur %s";
		GOLD_LOOT = "%s ramasse %.2f écus sur le cadavre de %s";
		ITEMS_LOOT = "%s ramasse %s sur le cadavre de %s";
		OBJECTS_LEFT_BEHIND = "Objets abandonnés : %s";
		
		RESET_BUILD = "Redistribution des points de compétence";
		FULL_HEAL = "Récupération de %.3f points de vie en %.3f secondes";
		EARN_EXPERIENCE = "%s reçoit %.0f points d'expérience (%.0f × %.2f × %.2f)";
		LEVEL_UP = "%s passe du niveau %d au niveau %d";
		UNIVERSE_CHANGE = "Pénalité de temps liée au changement d'univers : %.3f secondes (%.3f × %.2f)";
		CHALLENGE_CHANGE = "Pénalité de temps liée au changement de défi : %.3f secondes (%.3f × %.2f)";
		DEATH_GOLD_LOSS = "%s perd %.2f × %.1f%% de son or (%.3f écus)";
		DEATH_TIME_PENALTY = "Pénalité de temps liée à la mort : %.3f secondes (%.3f × %.2f)";
		RESURRECTION_TIME = "Résurrection : %.3f secondes";
		NECROPHAGY = "%s dévore le cadavre de %s et récupère %f points de vie";
		
		FIGHT = "Combat";
		TRAPS = "Pièges";
		RESURRECTION_REGENERATION_PENALTIES = "Résurrection/régénération/pénalités";
		RESURRECTION = "Résurrection";
		REGENERATION = "Régénération";
		PENALTIES = "Pénalités";
		SHOPPING_CRAFTING = "Shopping/crafting";
		LOOKING_FOR_ENNEMY = "Recherche d'ennemi";
		INITIATIVE = "Initiative";
		ATTACK = "Attaque";
		FLEE = "Fuite";
		LOOKING_FOR_TRADER = "Recherche de marchand";
		LOOKING_FOR_MYSTIC_FORGE = "Recherche de forge";
		CRAFTING = "Crafting";
		LOSSES_AND_EXPENSES = "Pertes et dépenses";
		GOLD_FROM_MONSTERS = "Or sur les monstres";
		GOLD_FROM_LIFE_ANNUITY = "Rente viagère";
		GOLD_FROM_SALES = "Vente d'objets";
		GOLD_FROM_SELLING_BASIC_OBJECTS = "Vente d'objets normaux";
		GOLD_FROM_SELLING_MAGIC_OBJECTS = "Vente d'objets magiques";
		GOLD_FROM_SELLING_RARE_OBJECTS = "Vente d'objets rares";
		GOLD_FROM_SELLING_RESOURCES = "Vente de ressources";
		GOLD_FROM_SELLING_OTHER = "Autres ventes";
		LOSS_FROM_DEATH = "Or perdu à cause de la mort";
		LOSS_FROM_SHOPPING = "Or dépensé en achats";
		
		EXPERIENCE_FROM_MONSTERS = "Expérience des monstres";
		EXPERIENCE_FROM_TRAPS = "Expérience des pièges";
		EXPERIENCE_FROM_ORBS = "Expérience des orbes";
		BASE_EXPERIENCE = "Expérience de base";
		BASE_EXPERIENCE_TRAP_LEVEL = "Expérience de base d'un piège (en fonction du niveau)";
		BASE_EXPERIENCE_MONSTER_LEVEL = "Expérience de base d'un monstre (en fonction du niveau)";
		MONSTERS_SKILLS_POINTS = "Points à distibuer des monstres";
		DIVINE_POINTS = "Points divins";
		DIVINE_POINTS_ORBS = "Points divins (en fonction des orbes sacrifiés)";
		DIVINE_POINTS_LEVEL = "Points divins (en fonction du niveau)";
		MONSTER_LEVEL = "Niveau des monstres";
		CHAMPIONS_LEVEL = "Niveau des champions (relatif)";
		BASE_MONSTER_LEVEL = "Niveau des monstres de base (relatif)";
		CHAMPIONS_SKILLS_POINTS = "Points à distibuer des champions";
		TRAPS_DAMAGE = "Dégâts des pièges";
		MONSTER_GOLD_LOOT = "Butin d'or (base)";
		GOLD_DROP_MIN = "Or de base sur les monstres (minimum)";
		GOLD_DROP_AVERAGE = "Or de base sur les monstres (moyenne)";
		GOLD_DROP_MAX = "Or de base sur les monstres (maximum)";
		
		ATTACKS_PER_SECOND = "Attaques par seconde"; 
		AVERAGE_DAMAGE_OF_A_BASIC_ATTACK = "Dégâts moyens d'une attaque basique";
		CRITICAL_STRIKE_PROBABILITY= "Probabilité de coup critique";
		CRITICAL_STRIKE_MULTIPLIER = "Multiplicateur des coups critique";
		AVERAGE_DAMAGE_PER_ATTACK = "Dégâts moyens d'une attaque";
		AVERAGE_DAMAGE_PER_SECOND = "Dégâts moyens par seconde";
		DAMAGE_MULTIPLIER = "Multiplicateur de dégâts";
		OF_LEVEL = "De niveau";
		LOW_OF_LEVEL = " de niveau ";
		
		PROBABILITY_TO_HIT_A_MONSTER = "Probabilité de toucher un monstre";
		PROBABILITY_TO_DODGE_AGAINST_A_MONSTER = "Probabilité d'esquiver contre un monstre";
		PROBABILITY_TO_DODGE_AGAINST_PRECISION = "Probabilité d'esquiver contre une précision de ";
		PROBABILITY_TO_HIT_AGAINST_DODGE = "Probabilité de toucher contre une esquive de ";
		
		DAMAGE_REDUCTION = "Réduction des dégâts";
		DAMAGE_TAKEN = "Dégâts encaissés";
		TRAPS_REDUCTION_MULTIPLIER = "Multiplicateur de réduction des pièges";
		TRAPS_DAMAGE_REDUCTION = "Réduction des dégâts des pièges";
		DAMAGE_ABSORPTION = "Absorption des dégâts";
		HEALTH_POINTS_PER_VITALITY_POINT = "Points de vie par point de vitalité";
		HEALTH_POINTS = "Points de vie";
		EFFECTIVE_HEALTH_POINTS = "Points de vie effectifs";
		HEALTH_POINTS_PER_SECOND = "Points de vie par seconde";
		TOTAL_REGENERATION = "Régénération totale";
		LIFE_LEECH = "Fraction des dégâts convertis en vie";
		TIME_BEFORE_THE_FIRST_ATTACK = "Temps avant d'attaquer";
		INITIATIVE_BONUS_AGAINST_A_TRAP = "Multiplicateur d'initiative face à un piège";
		INITIATIVE_TIME_AGAINST_A_TRAP = "Temps d'initiative face à un piège";
		FINDING_A_MONSTER = "Traque d'un adversaire";
		FINDING_A_TRADER = "Recherche d'un marchand";
		FINDING_A_FORGE = "Recherche d'une forge";
		
		
		MAXIMUM_LOAD = "Charge maximale";
		SALE_PRICE_MULTIPLIER = "Coefficient de vente";
		BUY_PRICE_MULTIPLIER = "Coefficient d'achat";
		PROBABILITY_TO_FLEE = "Probabilité de fuite";
		TIME_TO_FLEE = "Temps de fuite";
		PROBABILITY_OF_AN_OBJECT_BEING_MAGIC = "Probabilité qu'un objet soit magique";
		PROBABILITY_OF_AN_OBJECT_BEING_RARE = "Probabilité qu'un objet soit rare";
		PROBABILITY_OF_AN_OBJECT_BEING_HIGH_QUALITY = "Probabilité qu'un objet soit de qualité";
		GOLD_MULTIPLIER = "Multiplicateur d'or";
		RESOURCES_MULTIPLIER = "Multiplicateur de ressources";
		AVERAGE_MONSTER_LOOT = "Butin moyen pour un monstre tué";
		TOTAL_NUMBER_OF_ITEMS = "Nombre total d'objets";
		NUMBER_OF_RESOURCES = "Nombre de ressources";
		NUMBER_OF_NONRESOURCE_OBJECTS = "Nombre d'objets hors ressources";
		TOTAL_QUANTITY_OF_RESOURCES = "Quantité totale de ressources";
		NUMBER_OF_MAGIC_OBJECTS = "Nombre d'objets magiques";
		NUMBER_OF_RARE_OBJECTS = "Nombre d'objets rares";
		PROBABILITY_OF_DODGING_A_FINAL_BLOW = "Probabilité d'esquiver un coup final";
		TIME_MULTIPLIER = "Multiplicateur du temps";
		DAMAGE_INFLICTED_ON_ATTACKERS = "Dégats infligés aux attaquants";
		DAMAGE_REFLECTED_ON_ATTACKERS = "Proportion des dégats réfléchis";
		A_KILLED_MONSTER_GIVES = "Un monstre tué donne %.2f%% de sa vie";
		CRAFTING_TIME = "Temps du crafting";
		CRAFTING_EFFICIENCY = "Rendement du crafting";
		ORB_COST = "Un orbe coûte %.2f%% d'orbe";
		AVERAGE_LEVEL_OF_MERCHANTS = "Niveau moyen des marchands";
		NUMBER_OF_MERCHANTS_ITEMS = "Nombre moyen d'objets chez les marchands";
		MIN_NUMBER_OF_MERCHANTS_ITEMS = "Nombre minimal d'objets chez les marchands";
		MAX_NUMBER_OF_MERCHANTS_ITEMS = "Nombre maximal d'objets chez les marchands";
					
		PROBABILITY_OF_DETECTING_A_TRAP = "Probabilité de détecter un piège";
		INCOME_PER_SECOND = "Rente en écus par seconde";
		SKILL_POINTS_PER_LEVEL = "Points à distibuer par niveau";
		GENERAL_EXPERIENCE_BONUS = "Bonus général d'expérience";
		EXPERIENCE_BONUS_ON_A_MONSTER = "Bonus d'expérience sur un monstre";
		PENALTIES_REDUCTION = "Réduction des pénalités";
		MULTIPLIER_VERSUS = "Multiplicateur versus ";
		TIME_GLOBAL = "Temps (global)";
		TIME_FIGHT_TRAPS = "Temps (combat/pièges)";
		TIME_LIFE_MANAGEMENT = "Temps (gestion de la vie)";
		TIME_SHOPPING_CRAFTING = "Temps (shopping/crafting)";
		INCOME_GLOBAL = "Revenu (global)";
		INCOME_SALES = "Revenu (vente)";
		EXPERIENCE = "Expérience (sources)";
		
		HEALTH_POINTS_FOR_A_CONSTITUTION_OF = "Points de vie pour une constitution de ";
		RESURRECTION_TIME_CURV = "Temps de résurrection";
		PROBABILITY_THAT_A_BASIC_OBJECT_BECOMES_MAGIC = "Probabilité qu'un objet normal devienne magique";
		PROBABILITY_THAT_A_MAGIC_OBJECT_BECOMES_RARE = "Probabilité qu'un objet magique devienne rare";
		AVERAGE_NUMBER_OF_ITEMS_ON_A_MONSTER = "Nombre moyen d'objets sur un monstre";
		MINIMUM_NUMBER_OF_ITEMS_ON_A_MONSTER = "Nombre minimal d'objets sur un monstre";
		MAXIMUM_NUMBER_OF_ITEMS_ON_A_MONSTER = "Nombre maximal d'objets sur un monstre";
		MINIMUM_ENCHANTMENT_POWER = "Puissance minimale des enchantements";
		MAXIMUM_ENCHANTMENT_POWER = "Puissance maximale des enchantements";
		PROPORTION_OF_THE_CADAVERS_HEALTH_POINTS_ABSORBED = "Proportion de la vie des cadavres récupérée";
		COST_IN_ORBS_OF_AN_ORB = "Coût en orbes d'un orbe";
		AVERAGE_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL = "Niveau moyen des marchands (ajouté au niveau de la zone)";
		MINIMUM_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL = "Niveau minimal des marchands (ajouté au niveau de la zone)";
		MAXIMUM_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL = "Niveau maximal des marchands (ajouté au niveau de la zone)";
		EXPERIENCE_SPECIFIC_MULTIPLIER = "Muliplicateur spécifique d'expérience (écart de niveau : %d)";
		PENALTY_COEFFICIENT = "Coefficient de pénalité";
	
		WINDOW_CURVES = "Courbes sur les mécanismes du jeu";
		WINDOW_INVENTORY = "Inventaire";
		WINDOW_SHOP = "Boutique de %s (niveau %d)";
		WINDOW_FORGE = "Forge mystique";
		WINDOW_INVENTORY_PROMPT = "Or : %.2f écus, charge : %.2f kg / %.2f kg";
		EQUIP = "Équiper";
		UNEQUIP = "Déséquiper";
		SPLIT = "Séparer";
		DISCARD = "Jeter";
		BUY = "Acheter";
		SALE = "Vendre";
		DEPOSIT = "Déposer";
		PICK_UP = "Ramasser";
		COMBINE = "Combiner";

		TYPE_VAL = "Type : %s (%s)\n\n";
		BASE_VAL = "Base : %s\n";
		EFFECTIVE_LEVEL_VAL = "Niveau effectif : %d\n";
		SLOT_VAL = "Emplacement : %s\n";
		QUALITY_VAL = "Qualité : %.2f%%\n";
		EFFICIENCY_VAL = "Efficacité : %.1f (%d×%.2f×%.2f)\n";
		ENCHANTMENT_VAL = "Enchantement : %.1f (%d×%.2f)\n";
		NUMBER_OF_ENCHANTMENTS_VAL = "Nombre d'enchantements : %d\n";
		SKILL_BONUS_VAL = "Bonus compétence : %.1f points\n";
		QUANTITY_VAL = "Quantité : %.4f\n";
		WEIGHT_VAL = "Poids : %.3f kg (%.3f allégé)\n";
		REAL_PRICE_VAL = "Prix réel : %.2f écus\n";
		SELLING_PRICE_VAL = "Prix de vente : %.2f écus\n";
		PURCHASE_PRICE_VAL = "Prix d'achat : %.2f écus\n\n";
		FAMILY_VAL = "Famille : %s\n";
		WEIGHT_COEFFICIENT_VAL = "Coefficient de poids : %.2f\n";
		PRICE_COEFFICIENT_VAL = "Coefficient de prix : %.2f\n";
		EFFICIENCY_COEFFICIENT_VAL = "Coefficient d'efficacité : %.2f\n";
		ENCHANTMENT_COEFFICIENT_VAL = "Coefficient d'enchantement : %.2f\n";
		
		WE_START_GENTLY = "On commence en douceur";
		DUNGEONS_AND_DRAGONS = "Donjons et dragons";
		INFERNO = "Inferno";
		SKY_IS_THE_LIMIT = "Le ciel est la limite";
		THIS_IS_SPARTA = "This Is Sparta";
		BEGGAR = "Mendiant";
		GROCER = "Épicier";
		TRADER = "Trader";
		BANKER = "Banquier";
		BILL_GATES = "Bill Gates";
		WIRT_THE_PEG_LEG = "Wirt The Peg-Leg";
		OVER_9000 = "Over 9000";
		THE_BUNNY_MUST_DIE = "Il faut tuer le lapin";
		BUNNY_BOSS = "Lapinou";
		A_PORCUPINE_THAT_STINGS = "Un porc-épic qui pique";
		THE_PORCUPINE_BOSS = "Le porc-épic";
		GHOSTBUSTERS = "SOS Fantômes";
		CASPER_BOSS = "Casper";
		KILL_BILL = "Kill Bill";
		BILL_BOSS = "Bill";
		GOAL = "\nObjectif : ";
		REACH_LEVEL = "atteindre le niveau %d";
		WIN_GOLD_COINS = "gagner %d pièces d'or";
		DEFEAT_BOSS = "vaincre %s";
		LEVEL_REACHED_VICTORY = "Niveau %d atteint, victoire !";
		YOU_MUST_BE_AT_LEAST_LEVEL = "%s doit être au moins niveau %d pour gagner.";
		YOU_POSSESS_GOLD_COINS_VICTORY = "%s possède %d pièces d'or, victoire !";
		YOU_MUST_POSSESS_AT_LEAST = "%s doit posséder au moins %d pièces d'or pour gagner.";

		CHOOSE_A_SEED = "Choisissez une graine (un entier naturel)\n 0 pour les règles classiques";
		CREATE_A_UNIVERSE = "Création d'un univers";
		HISCORES_CR = "\nHiscores :\n";
		SEED = "Graine : ";
		SEED_DOTS = "Graine...";
		CHOOSE_A_CHALLENGE = " Choisissez un défi";
		CHOICE_OF_THE_CHALLENGE = "Choix du défi";
		CHOOSE = "Choisir";
		BEST_TIMES = " Meilleurs temps";
		HISCORES = "Hiscores";
		CREATE = "Créer";
		CANT_WRITE_IN_HISCORES = "Erreur d'écriture du hiscore (fichier hiscores.data).";
		ERROR_LOADING_HISCORES = "Erreur lors du chargement des hiscores (fichier hiscores.data).";
		CANT_WRITE_IN_LOG = "Erreur d'écriture du log (fichier %s).";
		CONGRATULATIONS_HISCORES = "Félicitations, %s entre dans les hiscores !";
		HISCORES_LINE = "%s (%d secondes, graine %d)\n";
		RULE_NEGATION = "non (%s)";
		INVALID_RULE = "règle invalide";
		
		BAY = "Baie";
		ISLAND = "Île";
		ISLANDS = "Îles";
		LAKE = "Lac";
		FORTRESS = "Forteresse";
		TREE_CITY = "Citée-arbre";
		MASSIF  = "Massif";
		FOREST = "Forêt";
		CAPE = "Cap";
		CAVES = "Cavernes";
		MOUNTAINS = "Montagnes";
		ROADSTEAD = "Rade";
		WOOD = "Bois";
		ARENA = "Arène";
		MOUTH = "Bouche";
		JUNCTION = "Confluent";
		RIFT = "Faille";
		RAVINE = "Ravin";
		SWAMP = "Marais";
		LAGOON = "Lagune";
		BRIDGE = "Pont";
		
		DISTRIBUTION_OF_GOD_POINTS = "Distribution de %.2f points divins";
		DISTRIBUTION_OF_SKILL_POINTS = "Distribution de %.2f points de compétences";
		PLUS = "+";
		MINUS = "-";
		VALIDATE = "Valider";
		CANCEL = "Annuler";
		SKILL_POINTS_DISTRIBUTION = "Répartition des points de compétences";
		UNIVERSE_EDITION = "Modifier l'univers";
				
		ERROR_INVALID_NUMBER = "Erreur : nombre invalide";
		ERROR_EMPTY_NAME = "Erreur : nom vide";
		ERROR_SELF_REFERENCE = "Erreur : autoréférence";
		RULES = "Règles";
		RULE_ON_AN_OBJECT = "Règle sur un objet";
		RULE_ON_A_MONSTER = "Règle sur un monstre";
		RULE_ON_THE_PLAYER = "Règle sur le joueur";
		COMPOUND_RULE = "Règle composée";
		NEGATION = "Négation";
		AND = "et";
		OR = "ou";
		SELL_RULE = "Règle de vente";
		PURCHASE_RULE = "Règle d'achat";
		PICKUP_RULE = "Règle de ramassage";
		INVENTORY_FILTER = "Filtre d'inventaire";
		FLEE_RULE = "Règle de fuite";
		NON_INVOLVEMENT_RULE = "Règle de non-engagement";
		MERCHANT_SEARCH = "Recherche de marchand";
		SAVE = "Enregister";
		NEW_RULE = "Nouvelle règle";
		REMOVE = "Supprimer";
		EXPORT = "Exporter";
		IMPORT = "Importer";
		RULE = "Règle ";
		PROGRAMMING = "Programmation";

		SELECT_A_ZONE = "Sélectionnez une zone";
		WORLD_MAP = "Carte du monde";
		SAVING_GAME = "Sauvegarde du jeu";
		CANT_WRITE = "Ne peut pas écrire dans \"%s.theory\"";
		THEORYCRAFT = "Theorycraft";
		CHOOSE_A_NAME = "Choisissez un nom";
		CREATION_OF_A_HERO = "Création d'un héros";
		HERO_SELECTION = "Sélection du héros";
		LOAD_A_HERO = "Charger un héros";
		CREATE_A_HERO = "Créer un héros";
		DELETE_A_HERO = "Supprimer un héros";
		SEED_TIME = "%s (%d)\nSeed : %d\tTemps : %f";
		
		YOU_COME_ACROSS = "%s tombe sur : %s (niveau %d).";
		SUCCESSFUL_TRAP_DETECTION = "Réussite de la détection (score du piège : %.3f, probabilité de détection : %.2f%%).";
		COMPLETELY_AVOIDS = "%s évite entièrement %s.";
		FAILED_DETECTION = "Échec de la détection (score du piège : %.3f, probabilité de détection : %.2f%%).";
		TRAP_RESISTANCE_DOUBLED = "Résistance aux pièges doublée.";
		IS_DEAD = "%s est mort.";
		
		THEORYCRAFT_TITLE = "Theorycraft - %s - %s";
		HEALTH_POINTS_LEFT = "Vie : %.2f/%.2f";
		DISABLE = "Désactiver";
		ENABLE = "Activer";
		STOP_FIGHT = "Arrêt combat";
		NEXT_LEVEL = "Niveau suivant : %.2f/%.2f";
		GAME = "Jeu";
		LOAD_A_GAME = "Charger une partie";
		EXIT = "Quitter";
		CHALLENGE = "Défi";
		CHANGE_THE_CHALLENGE = "Changer de défi";
		COMPLETE_THE_CHALLENGE = "Finir le défi";
		TRAVEL = "Voyager";
		TRAVEL_TO = "%s voyage vers %s en %.3f secondes (%.3f × %.2f)";
		INVENTORY = "Inventaire";
		SHOPPING = "Shopping";
		SKILLS = "Compétences";
		CHARTS = "Courbes";
		UNIVERSE_INFO = "Informations sur l'univers";
		INFORMATIONS = "Informations";
		NEW_GAME = "Nouvelle partie";
		STOP_TO_FIGHT = "Arrêter de combattre";
		DEFEATS = "Défaites =";
		DEFEATED_ENEMIES = "Ennemis vaincus =";	
		LEVELS_WON = "Niveaux gagnés =";
		LETHAL_TRAPS = "Pièges léthaux =";
		SURVIVED_TRAPS = "Pièges défaits =";
		YOU_HAVE_ALREADY_FINISHED_THE_GAME = "%s a déjà fini le jeu !";
		YOU_HAVE_DEFEATED_THE_FINAL_BOSS = "%s a vaincu le boss final !";
		TIME_PASSED = "Temps passé : %.2f secondes !";
		AVOIDS_MONSTER = "%s évite %s (niveau %d)";

		HTML_BODY = "<html><body>";
		PLAYER_STATISTICS = "Statistiques sur le joueur";
		PLAYER_STATISTICS_LIST = "Nom : %s<br>\nNiveau : %d<br>\nDéfi : %s<br>\nTemps total de jeu : %.2f secondes<br>\nOr : %.2f<br>\nCharge : %.2f / %.2f<br>\nTotal des points de compétences : %.2f<br>\nTotal des points divins : %.2f (%.2f orbes sacrifiés)<br>";
		UNIVERSE_INFORMATION_LIST = "<h2>Informations sur l'univers</h2>\nUnivers : %d\n<br>Nombre de voyages dimensionnels : %d\n<br>Niveau maximal du joueur : %d\n<br>Constantes de l'univers :<ul>\n<li>Vie de départ : %.0f\n<li>Points de compétences initiaux : %.0f\n<li>Coefficient d'efficacité de base : %.3f\n<li>Puissance maximale des enchantements : %.3f\n<li>Qualité maximale des objets : %.2f%%\n<li>Pénalité pour les matériaux inappropriés : de %.2f%% à %.2f%% (%.2f%% de moyenne)\n<li>Plage aléatoire (dégâts, temps, or) : de %.3f à %.3f (1,000 de moyenne)\n<li>Pénalité d'or à chaque mort (base) : %.3f%%\n<li>Pénalité de temps à chaque mort (base) : %.0f secondes\n<li>Probabilité de tomber sur un piège (base) : %.2f%%\n<li>Probabilité de tomber sur un champion (base) : %.2f%%\n<li>Pénalité de temps pour changer de défi (base) : %.0f secondes\n<li>Coût d'un voyage dimensionnel (base) : %.0f orbes de chaque type\n<li>Pénalité de temps pour voyager (base) : %.0f secondes<li>Pénalité de temps pour changer d'univers (base) : %.0f secondes<li>Probabilité qu'un objet soit une ressource : %.2f%%</ul>";
		H3_AVAILABLE_OBJECTS_H3 = "<h3>Objets disponibles :</h3>";
		H3_UNAVAILABLE_OBJECTS_H3 = "<h3>Objets indisponibles :</h3>";
		BASE_DISTRIBUTION_OF_MONSTER_SKILL_POINTS = "<h3>Répartition de base des points des monstres</h3><ul>";
		LI_SKILL_POINTS = "<li>%s : %.2f%% des points";
		LI_END = "</li>";
		H3_ZONE_INFORMATION_H3 = "<h3>Informations sur les zones</h3>";
		NUMBER_OF_ZONES = "Nombre de zones : ";
		DL = "<dl>";
		DT_ZONE = "<dt>%s (%d-%d) %s</dt>";
		NO_LOOT_Z = "(pas de butin)";
		TRAPS_AND_CHAMPION_PROBABILITY = "<dd>Probabilité de tomber sur un piège : %.3f%%<br>Probabilité  de tomber sur un champion : %.3f%%";
		MONSTER_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Niveau des monstres : minimum %d (%.0f points), moyen %.2f (%.0f points), maximum %d (%.0f points)";
		CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Niveau des champions : minimum %d (%.0f points), moyen %.2f (%.0f points), maximum %d (%.0f points)</dd>";
		DL_END = "</dl>";
		H2_CRAFTING_H2 = "<h2>Crafting</h2>";
		BODY_HTML_END = "</body></html>";
		INFORMATION_AND_STATISTICS = "Informations et statistiques";

		FIGHT_STATS = "<h3>Combat</h3><ul>";
		FIGHT_STATS_LIST = "<li>Ennemis combattus : %.0f / %.0f (%.2f%%)\n<li>Combats gagnés : %.0f / %.0f (%.2f%%)\n<li>Combats fuis : %.0f / %.0f (%.2f%%)\n<li>Mort au combat: %.0f / %.0f (%.2f%%)\n<li>Coups ayant touché : %.0f / %.0f (%.2f%%)\n<li>Tentatives de fuite réussies : %.0f / %.0f (%.2f%%)\n<li>Pièges survécus : %.0f / %.0f (%.2f%%)\n<li>Pièges évités : %.0f / %.0f (%.2f%%)</ul>\n<h3>Économie</h3>\nMarchands rencontrés : %.0f\n<br>Forges mystiques visitées : %.0f\n<br>Utilisations de la forge : %.0f\n<br>Objets trouvés : %.0f normaux, %.0f magiques, %.0f rares, %.2f ressources, %.2f orbes\n<br>Or total gagné : %.2f\n<br>Or total dépensé : %.2f\n<ul><li>Ramassé sur les monstres : %.2f (%.2f%%)\n<li>Rente viagère : %.2f (%.2f%%)\n<li>Vente d'objets normaux : %.2f (%.2f%%)\n<li>Vente d'objets magiques : %.2f (%.2f%%)\n<li>Vente d'objets rares : %.2f (%.2f%%)\n<li>Vente de ressources : %.2f (%.2f%%)\n<li>Vente d'autres objets : %.2f (%.2f%%)\n<li>Pertes liées à la mort : %.2f (%.2f%%)\n<li>Achat d'objets normaux : %.2f (%.2f%%)\n<li>Achat de ressources : %.2f (%.2f%%)\n<li>Achat d'orbes : %.2f (%.2f%%)\n<li>Achat d'autres objets : %.2f (%.2f%%)";
		UL_END = "</ul>";
		
		ALCHEMICAL = "%s alchimique";
		MAX_ZONE_LEVEL = "Accès aux zone de niveau %.2f";
		ZONE_LEVEL_MULTIPLIER = "Multiplicateur du niveau de zone accessible";
		DIVINE_POINTS_MULTIPLIER = "Multiplicateur du nombre de points divins";
		FIRST_STRIKE_MULTIPLIER = "Multiplicateur de dégâts sur le premier coup";
		
		EQUATION_CAP_MULTIPLIER = "Limite des équations (multiplicateur)";
		CONST_CAP_MULTIPLIER =  "Limite des constantes (multiplicateur)";
		
		FIRST_STRIKE = "Premier coup";
		ALLOY = "Alliage %s";
		OF_ALLOY = "d'alliage %s";
		ZONE_LEVEL = "Niveau de la zone";
		RESOURCES_WEIGHT_MULTIPLIER="Muliplicateur du poids des ressources";
		EQUIPMENT_WEIGHT_MULTIPLIER="Muliplicateur du poids de l'équipement";	
		MAX_LEVEL="Niveau maximal";
		
		PLAYER_INFOS = getPlayerInfos();
		UNIVERSE_EQUATIONS_NAME = getEquationName();
	}
	else if (lang == 1)
	{
		LANG = lang;
		String[] tmp1 = {"Undead","Animal","Human","Green-skin","Demon","Champion"};
		TAGS_NAME = tmp1;
		
		String[] tmp2 = {"Attack speed", "Damage", "Reduction", "Absorption", "Dodging skill", "Precision",
		"Critical strike chance", "Critical strike damage", "Life leech", "Vitality", "Constitution", "Regeneration", 
		"Resurrection", "Maximum load", "Walking speed", "Resource find","Magic items find",
		"Rare items find","Quality find","Quantity find","Power find","Gold find",
		"Damage to undeads","Damage to animals","Damage to humans","Damage to green-skins","Damage to demons",
		"Damage to champions","Trading","Chance of flee","Speed of flee","Initiative","Immunity to final blow",
		"Speed of time","Thorns","Reprisals","Necrophagy",
		"Speed of craft", "Craft performance", "Saving orbs", 
		"Merchant level", "Merchant inventory", 
		"Trap detection", "Initiative bonus against traps", "Resistance to traps","Life annuity","Education", "Learning", "Overkilling", "Boldness", "Penalties reduction","Extended access", "Deity", "First strike","Mastery of equations", "Mastery of constants", "Lighter resources", "Lighter equipment"};
		SKILLS_NAME = tmp2;
		
		String[] tmp3 = {"junk","fabric/leather","metal","gemstone","conceptual","alloy"};
		MATERIAL_FAMILY_NAME = tmp3;
		
		String[] tmp4 = {"basic object", "magic object", "rare object", "legendary object", "elementary resource", "primordial resource", "orb"};
		RARITY_NAME = tmp4;
		
		String[] tmp5 = {"Helmet", "Earrings", "Chestpiece", "Cloak", "Belt", "Leggings", "Boots", "Socks", "Amulet", "Ring", "Bracers", "Gauntlets", "Weapon", "Sheath", "Eyewear", "Resources"};
		SLOT_NAME = tmp5;
		
		String[] tmp6 = {"legend","legacy","relic","trophy","souvenir","reminiscence","vestige","treasure","wealth","miracle","fortune"};
		RARE_NAMES = tmp6;
		
		String[] tmp7 = {"no","yes"};
		RULE_NO_YES = tmp7;
		
		String[] tmp8 = {"and","or","not"};
		RULE_LOGIC = tmp8;
		
		String[] tmp9 = {"=","<",">","≠"};
		RULE_OPERATOR = tmp9;
		
		String[] tmp10 = 
		{
		"Object type", // object.rare
		"Material type", // object.material.type
		"Slot", // object.pos
		"Quality", // object.quality
		"Weight", //object.weight
		"Real price", //object.price
		"Effective level", // object.effectiveIlvl ()
		"Efficiency", // ilvl * material.coeffEfficiency * base_power
		"Enchantment", // elvl * material.coeffPuissance
		"Basic efficiency", // object.ilvl
		"Basic enchantment", // object.elvl
		"Weight coefficient", // material.coeffWeight
		"Price coefficient", // material.coeffPrice
		"Efficiency coefficient", // material.coeffEfficiency
		"Enchantment coefficient", // material.coeffPuissance
		"Name of the base object", // object.baseItem.name
		"Material name", // object.material.name
		"Equipped" // object.equiped
		};
		RULE_ITEM_TYPE_NAME = tmp10;

		String[] tmp11 = 
		{
		"Level", // level
		"Monster base", // nom_base
		"Monster type", // type
		"Attacks per second", // att_per_sec()
		"Average damage of a basic attack", // dmg_base()
		"Critical strike probability (%)", // 100.0*crit_proba()
		"Critical strike multiplier", // multi_crit(),
		"Average damage per attack", // dmpa
		"Average damage per second", // att_per_sec()*dmpa
		"Damage multipler versus humans", // ed_versus_tag(2)
		"Damage reduction (%)", // 100.0-(100.0*reduc())
		"Damage absorption", //absorption()
		"Current health points", // vie()
		"Health points", // vie_max()
		"Effective health points", // vie_max()/reduc()
		"Damage inflicted on attackers", //epines()
		"Damage reflected on attackers (%)" // 100*represailles()
		};
		RULE_MONSTER_TYPE_NAME = tmp11;
		
		String[] tmp12 = 
		{
		"Level", // level
		"Actual load", // charge
		"Free load", // charge_max()-charge
		"Money load", // money
		"Attacks per second", // att_per_sec()
		"Average damage of a basic attack", // dmg_base()
		"Critical strike probability (%)", // 100.0*crit_proba()
		"Critical strike multiplier", // multi_crit(),
		"Average damage per attack", // dmpa
		"Average damage per second", // att_per_sec()*dmpa
		"Damage multipler versus humans", // ed_versus_tag(2)
		"Damage reduction (%)", // 100.0-(100.0*reduc())
		"Damage absorption", //absorption()
		"Current health points", // vie()
		"Health points", // vie_max()
		"Effective health points", // vie_max()/reduc()
		"Damage inflicted on attackers", //epines()
		"Damage reflected on attackers (%)" // 100*represailles()
		};
		RULE_PLAYER_TYPE_NAME = tmp12;
		
		String[] tmp13 = {"Skill","Points","Equipement","Total"};
		LEVEL_UP_COLUMN_NAMES = tmp13;
		
		String[] tmp14 = {"the Tight-fisted", "the Capitalist","the Stingy","the Scrimpy","the Avaricious","the Crook","the Racketeer","the Scoundrel"};
		SHOP_SURNAMES = tmp14;
		
		String[] tmp15 = {"Spider web", "Flypaper", "Bird control spike", "Mouse trap", "Sharp rock", "Rabbit collar", "Mole trap", "Stumbling stone", "Rat trap", "Acid rain", "Cactus", "Barbed wire", "Makibishi", "Crevasse", "Landslide", "Bush fire", "Small earthquake", "Forest Fire", "Caltrop", "Deep Pit", "Deep pit with spikes", "Concertina wire", "Poisoned dart", "Poisoned arrow", "Acid pit", "Explosive note", "Circular saw", "Giant spider web", "Strangling liana", "Wolf trap", "Bear trap", "Lion trap", "Trapped parcel", "Automatic crossbow", "Lava pit", "Moving sand", "Ice storm", "Anti-personnel mine", "Anti-tank mine", "Rain of fire", "Lava lake", "Avalanche", "Tornado", "Rock avalanche", "Sandstorm", "Sand twister ", "Fire storm", "Lava Geyser", "Volcanic Eruption", "Meteorite Fall", "Comet Fall", "Earthquake", "Tsunami", "Atomic mine"};
		TRAP_NAMES = tmp15;
		
		String[] tmp16 = {"Action","Condition","Value","Enabled"};
		STOP_RULES_COLUMN_NAMES = tmp16;
		
		String[] tmp18 = {"Constant of the universe","Base value","Value","Modifier","Cap"};
		EDIT_UNIVERSE_COLUMN_NAMES = tmp18;
		
		String[] tmp19 = {"Base health points",
		"Trap probability",
		"Maximum enchantment power",
		"Initial skill points",
		"Gold penalty at each death ",
		"Champion probability",
		"Cost of a dimensional trip",
		"Random range",
		"Penalty for inappropriate materials",
		"Time penalty to change challenge",
		"Time penalty to travel",
		"Time penalty to change universe",
		"Time penalty at each death",
		"Basic efficiency coefficient",
		"Probability of an object being a ressource",
		"Objects maximal quality"};
		UNIVERSE_STATS_NAME = tmp19;
		
		COLON = ":";
		SECONDS = "seconds";
		ALL = "All";
		EQUIPPED = "Equipped";
		NOT_EQUIPPED = "Not equipped";
		MONSTER_TOO_STRONG = "Monster too strong";
		MONSTER_TOO_WEAK = "Monster too weak";
		LIFE_ANNUITY = "%s receives %.2f gold coins of income (%.2f seconds x %.2f gold coins/seconds)";
		BUYING_OBJECTS = "Purchase of %s to %s for %.2f gold coins";
		SELLING_OBJECTS = "Sale of %s to %s for %.2f gold coins";
		DROPPING_OBJECTS = "%s drops %s in the mystic forge";
		PICKING_OBJECTS = "%s picks %s from the mystic forge";
		THROW_AWAY_OBJECTS = "You throw away %s";
		N_OBJECTS = "%d objects";
		SHORT_INFOS = "Level %d\nTotal time: %.2f seconds\nMoney: %.2f gold coins\nLoad: %.2f / %.2f\n";
		LEVEL_N = "Level %d\n";
		
		LOOKING_FOR_AN_ENNEMY = "Looking for an ennemy: %.3f seconds";
		LOOKING_FOR_A_TRADER = "Looking for a trader: %.3f seconds";
		ENCOUNTER = "%s encounters %s (level %d)";
		LOOKING_FOR_A_MYSTIC_FORGE = "Looking for a mystic forge: %.3f seconds";
		A_HITS_B = "%s hits %s in %.3f seconds (total time for %s: %.3f)\n";
		A_VERSUS_B = "%s versus %s (level %d)";
		VERSUS = "Versus";
		INITIATIVES = "Initiatives: %.3f for %s, %.3f for %s";
		MULTIPLIER = "   Multiplier ×%.2f (%s)\n";
		DODGES_THE_HIT = "   %s dodges the hit of %s (%.2f%% probability)";
		CRITICAL_STRIKE = "   Critical strike ×%.2f (%.2f%% probability)\n";
		DAMAGE_INFLICTED = "   %s inflicts %.3f damage points to %s (base %.2f, reduction %.2f%%, absorption %.2f)";
		IMMUNITY_TO_FINAL_BLOW = "\n   Immunity to final_blow saves %s, he keeps 0.1 health points (%.2f%% probability)";
		THORNS = "\n   Thorns of %s inflict %.3f damage points to %s (base %.2f, reduction %.2f%%)";
		REPRISALS = "\n   Reprisals of %s inflict %.3f damage points to %s (base %.2f, reduction %.2f%%)";
		LIFE_LEECH_EFFECT = "\n   %s recovers %.3f health points (life leech)";
		
		TRY_TO_FLEE = "%s tries to flee %s (%.3f seconds)";
		FLEE_SUCCESS = "   Flee success (%.2f%% probability)";
		FLEE_FAIL = "   Flee fail (%.2f%% probability)";
		END_FIGHT_FLEE = "%s flees %s (fight duration %.3f seconds, health points left %.3f)";
		END_FIGHT_KILL = "%s kills %s (fight duration %.3f seconds, health points left %.3f)";
		END_FIGHT_DEATH = "%s looses against %s of level %d (fight duration %.3f seconds)";
		
		NO_LOOT = "No loot on %s";
		GOLD_LOOT = "%s loots %.2f gold coins on the corpse of %s";
		ITEMS_LOOT = "%s loots %s on the corpse of %s";
		OBJECTS_LEFT_BEHIND = "Objects left behind: %s";
		
		RESET_BUILD = "Redistribution of skills points";
		FULL_HEAL = "Recovery of %.3f health points in %.3f seconds";
		EARN_EXPERIENCE = "%s earns %.0f experience points (%.0f × %.2f × %.2f)";
		LEVEL_UP = "%s rises from level %d to level %d";
		UNIVERSE_CHANGE = "Time penalty for universe change: %.3f seconds (%.3f × %.2f)";
		CHALLENGE_CHANGE = "Time penalty for challenge change: %.3f seconds (%.3f × %.2f)";
		DEATH_GOLD_LOSS = "%s loses %.2f × %.1f%% of his gold (%.3f gold coins)";
		DEATH_TIME_PENALTY = "Time penalty for death: %.3f seconds (%.3f × %.2f)";
		RESURRECTION_TIME = "Resurrection: %.3f seconds";
		NECROPHAGY = "%s devours the corpse of %s and recover %f points of life";
		
		FIGHT = "Fight";
		TRAPS = "Traps";
		RESURRECTION_REGENERATION_PENALTIES = "Resurrection/regeneration/penalties";
		RESURRECTION = "Resurrection";
		REGENERATION = "Regeneration";
		PENALTIES = "Penalties";
		SHOPPING_CRAFTING = "Shopping/crafting";
		LOOKING_FOR_ENNEMY = "Looking for ennemy";
		INITIATIVE = "Initiative";
		ATTACK = "Attack";
		FLEE = "Flee";
		LOOKING_FOR_TRADER = "Looking for trader";
		LOOKING_FOR_MYSTIC_FORGE = "Looking for forge";
		CRAFTING = "Crafting";
		LOSSES_AND_EXPENSES = "Losses and expenses";
		GOLD_FROM_MONSTERS = "Gold from monsters";
		GOLD_FROM_LIFE_ANNUITY = "Life annuity";
		GOLD_FROM_SALES = "Gold from sales";
		GOLD_FROM_SELLING_BASIC_OBJECTS = "Selling basic objects";
		GOLD_FROM_SELLING_MAGIC_OBJECTS = "Selling magic objects";
		GOLD_FROM_SELLING_RARE_OBJECTS = "Selling rare objects";
		GOLD_FROM_SELLING_RESOURCES = "Selling resources";
		GOLD_FROM_SELLING_OTHER = "Other sales";
		LOSS_FROM_DEATH = "Gold lost due to death";
		LOSS_FROM_SHOPPING = "Gold spent on purchases";
		
		EXPERIENCE_FROM_MONSTERS = "Experience from monsters";
		EXPERIENCE_FROM_TRAPS = "Experience from traps";
		EXPERIENCE_FROM_ORBS = "Experience from orbs";
		BASE_EXPERIENCE = "Base experience";
		BASE_EXPERIENCE_TRAP_LEVEL = "Base trap experience (for a given level)";
		BASE_EXPERIENCE_MONSTER_LEVEL = "Base monster experience (for a given level)";
		MONSTERS_SKILLS_POINTS = "Monsters skills points";
		MONSTER_LEVEL = "Monsters level";
		CHAMPIONS_LEVEL = "Champions level (relative";
		BASE_MONSTER_LEVEL = "Base monsters level (relative)";
		
		DIVINE_POINTS = "Divine points";
		DIVINE_POINTS_ORBS = "Divine points (for a given number of sacrificed orbs)";
		DIVINE_POINTS_LEVEL = "Divine points (for a given level)";
		
		CHAMPIONS_LEVEL = "Champions relative level";
		CHAMPIONS_SKILLS_POINTS = "Champions skills points";
		TRAPS_DAMAGE = "Traps damage";
		MONSTER_GOLD_LOOT = "Gold loot (base)";
		GOLD_DROP_MIN = "Base gold loot (minimum)";
		GOLD_DROP_AVERAGE =  "Base gold loot (average)";
		GOLD_DROP_MAX =  "Base gold loot (maximum)";
		
		ATTACKS_PER_SECOND = "Attacks per second";
		AVERAGE_DAMAGE_OF_A_BASIC_ATTACK = "Average damage of a basic attack";
		CRITICAL_STRIKE_PROBABILITY = "Critical strike probability";
		CRITICAL_STRIKE_MULTIPLIER = "Critical strike multiplier";
		AVERAGE_DAMAGE_PER_ATTACK = "Average damage per attack";
		AVERAGE_DAMAGE_PER_SECOND = "Average damage per second";
		DAMAGE_MULTIPLIER = "Damage multiplier";
		OF_LEVEL = "Of level";
		LOW_OF_LEVEL = " of level ";
		
		PROBABILITY_TO_HIT_A_MONSTER = "Probability to hit a monster";
		PROBABILITY_TO_DODGE_AGAINST_A_MONSTER = "Probability to dodge against a monster";
		PROBABILITY_TO_DODGE_AGAINST_PRECISION = "Probability to dodge against a precision of ";
		PROBABILITY_TO_HIT_AGAINST_DODGE = "Probability to hit against a dodging skill of ";
				
		DAMAGE_REDUCTION = "Damage reduction";
		DAMAGE_TAKEN = "Damage taken";
		TRAPS_REDUCTION_MULTIPLIER = "Traps reduction multiplier";
		TRAPS_DAMAGE_REDUCTION = "Traps damage reduction";
		DAMAGE_ABSORPTION = "Damage absorption";
		HEALTH_POINTS_PER_VITALITY_POINT = "Health points per vitality point";
		HEALTH_POINTS = "Health points";
		EFFECTIVE_HEALTH_POINTS = "Effective health points";
		HEALTH_POINTS_PER_SECOND = "Health points per second";
		TOTAL_REGENERATION = "Total regeneration";
		LIFE_LEECH = "Fraction of damage converted to life";
		TIME_BEFORE_THE_FIRST_ATTACK = "Time before first attack";
		INITIATIVE_BONUS_AGAINST_A_TRAP = "Initiative multiplier against a trap";
		INITIATIVE_TIME_AGAINST_A_TRAP = "Initiative time against a trap";
		FINDING_A_MONSTER = "Finding a monster";
		FINDING_A_TRADER = "Finding a trader";
		FINDING_A_FORGE = "Finding a forge";

		MAXIMUM_LOAD = "Maximum load";
		SALE_PRICE_MULTIPLIER = "Sale price multiplier";
		BUY_PRICE_MULTIPLIER = "Buy price multiplier";
		PROBABILITY_TO_FLEE = "Probability to flee";
		TIME_TO_FLEE = "Time to flee";
		PROBABILITY_OF_AN_OBJECT_BEING_MAGIC = "Probability of an object being magic";
		PROBABILITY_OF_AN_OBJECT_BEING_RARE = "Probability of an object being rare";
		PROBABILITY_OF_AN_OBJECT_BEING_HIGH_QUALITY = "Probability of an object being high quality";
		GOLD_MULTIPLIER = "Gold multiplier";
		RESOURCES_MULTIPLIER = "Resources multiplier";
		AVERAGE_MONSTER_LOOT = "Average monster loot";
		TOTAL_NUMBER_OF_ITEMS = "Total number of items";
		NUMBER_OF_RESOURCES = "Number of resources";
		NUMBER_OF_NONRESOURCE_OBJECTS = "Number of non-resource objects";
		TOTAL_QUANTITY_OF_RESOURCES = "Total quantity of resources";
		NUMBER_OF_MAGIC_OBJECTS = "Number of magic objects";
		NUMBER_OF_RARE_OBJECTS = "Number of rare objects";
		PROBABILITY_OF_DODGING_A_FINAL_BLOW = "Probability of dodging a final blow";
		TIME_MULTIPLIER = "Time multiplier";
		DAMAGE_INFLICTED_ON_ATTACKERS = "Damage inflicted on attackers";
		DAMAGE_REFLECTED_ON_ATTACKERS = "Proportion of damage reflected on attackers";
		A_KILLED_MONSTER_GIVES = "A killed monster gives %.2f%% of his health points";
		CRAFTING_TIME = "Crafting time";
		CRAFTING_EFFICIENCY = "Crafting efficiency";
		ORB_COST = "An orb cost %.2f%% of an orb";
		AVERAGE_LEVEL_OF_MERCHANTS = "Average level of merchants";
		NUMBER_OF_MERCHANTS_ITEMS = "Average number of merchant's items";
		MIN_NUMBER_OF_MERCHANTS_ITEMS = "Minimum number of merchant's items";
		MAX_NUMBER_OF_MERCHANTS_ITEMS = "Maximum number of merchant's items";
		
		PROBABILITY_OF_DETECTING_A_TRAP = "Probability of detecting a trap";
		INCOME_PER_SECOND = "Income in gold coins per second";
		SKILL_POINTS_PER_LEVEL = "Skill points per level";
		GENERAL_EXPERIENCE_BONUS = "General experience bonus";
		EXPERIENCE_BONUS_ON_A_MONSTER = "Experience bonus on a monster";
		PENALTIES_REDUCTION = "Penalties reduction";
		MULTIPLIER_VERSUS = "Multiplier versus ";
		HEALTH_POINTS_FOR_A_CONSTITUTION_OF = "Health points for a constitution of ";
		RESURRECTION_TIME_CURV = "Resurrection time";
		PROBABILITY_THAT_A_BASIC_OBJECT_BECOMES_MAGIC = "Probability that a basic object becomes magic";
		PROBABILITY_THAT_A_MAGIC_OBJECT_BECOMES_RARE = "Probability that a magic object becomes rare";
		AVERAGE_NUMBER_OF_ITEMS_ON_A_MONSTER = "Average number of items on a monster";
		MINIMUM_NUMBER_OF_ITEMS_ON_A_MONSTER = "Minimum number of items on a monster";
		MAXIMUM_NUMBER_OF_ITEMS_ON_A_MONSTER = "Maximum number of items on a monster";
		MINIMUM_ENCHANTMENT_POWER = "Minimum enchantment power";
		MAXIMUM_ENCHANTMENT_POWER = "Maximum enchantment power";
		PROPORTION_OF_THE_CADAVERS_HEALTH_POINTS_ABSORBED = "Proportion of the cadaver's health points absorbed";
		COST_IN_ORBS_OF_AN_ORB = "Cost in orbs of an orb";
		AVERAGE_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL = "Average merchant level (added to zone level)";
		MINIMUM_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL = "Minimum merchant level (added to zone level)";
		MAXIMUM_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL = "Maximum merchant level (added to zone level)";
		EXPERIENCE_SPECIFIC_MULTIPLIER = "Experience-specific multiplier (level gap: %d)";
		PENALTY_COEFFICIENT  = "Penalty coefficient";
		
		TIME_GLOBAL = "Time (global)";
		TIME_FIGHT_TRAPS = "Time (fight/traps)";
		TIME_LIFE_MANAGEMENT = "Time (life management)";
		TIME_SHOPPING_CRAFTING = "Time (shopping/crafting)";
		INCOME_GLOBAL = "Income (global)";
		INCOME_SALES = "Income (sales)";
		EXPERIENCE = "Experience (sources)";
		
		WINDOW_CURVES = "Charts on the game mechanics";
		WINDOW_INVENTORY = "Inventory";
		WINDOW_SHOP = "%s's shop (level %d)";
		WINDOW_FORGE = "Mystic forge";
		WINDOW_INVENTORY_PROMPT = "Money: %.2f gold coins, load: %.2f kg / %.2f kg";
		EQUIP = "Equip";
		UNEQUIP = "Unequip";
		SPLIT = "Split";
		DISCARD = "Discard";
		BUY = "Buy";
		SALE = "Sale";
		DEPOSIT = "Deposit";
		PICK_UP = "Pick up";
		COMBINE = "Combine";
		
		TYPE_VAL = "Type: %s (%s)\n\n";
		BASE_VAL = "Base: %s\n";
		EFFECTIVE_LEVEL_VAL = "Effective level: %d\n";
		SLOT_VAL = "Slot: %s\n";
		QUALITY_VAL = "Quality: %.2f%%\n";
		EFFICIENCY_VAL = "Efficiency: %.1f (%d×%.2f×%.2f)\n";
		ENCHANTMENT_VAL = "Enchantment: %.1f (%d×%.2f)\n";
		NUMBER_OF_ENCHANTMENTS_VAL = "Number of enchantments: %d\n";
		SKILL_BONUS_VAL = "Skill bonus: %.1f points\n";
		QUANTITY_VAL = "Quantity: %.4f\n";
		WEIGHT_VAL = "Weight: %.3f kg (%.3f lightened)\n";
		REAL_PRICE_VAL = "Real price: %.2f gold coins\n";
		SELLING_PRICE_VAL = "Selling price: %.2f gold coins\n";
		PURCHASE_PRICE_VAL = "Purchase price: %.2f gold coins\n\n";
		FAMILY_VAL = "Family: %s\n";
		WEIGHT_COEFFICIENT_VAL = "Weight coefficient: %.2f\n";
		PRICE_COEFFICIENT_VAL = "Price coefficient: %.2f\n";
		EFFICIENCY_COEFFICIENT_VAL = "Efficiency coefficient: %.2f\n";
		ENCHANTMENT_COEFFICIENT_VAL = "Enchantment coefficient: %.2f\n";
		
		WE_START_GENTLY = "We start gently";
		DUNGEONS_AND_DRAGONS = "Dungeons and Dragons";
		INFERNO = "Inferno";
		SKY_IS_THE_LIMIT = "Sky is the limit";
		THIS_IS_SPARTA = "This Is Sparta";
		BEGGAR = "Beggar";
		GROCER = "Grocer";
		TRADER = "Trader";
		BANKER = "Banker";
		BILL_GATES = "Bill Gates";
		WIRT_THE_PEG_LEG = "Wirt The Peg-Leg";
		OVER_9000 = "Over 9000";
		THE_BUNNY_MUST_DIE = "The bunny must die";
		BUNNY_BOSS = "Bunny";
		A_PORCUPINE_THAT_STINGS = "A porcupine that stings";
		THE_PORCUPINE_BOSS = "The Porcupine";
		GHOSTBUSTERS = "Ghostbusters";
		CASPER_BOSS = "Casper";
		KILL_BILL = "Kill Bill";
		BILL_BOSS = "Bill";
		GOAL = "\nGoal: ";
		REACH_LEVEL = "reach level %d";
		WIN_GOLD_COINS = "win %d gold coins";
		DEFEAT_BOSS = "defeat %s";
		LEVEL_REACHED_VICTORY = "Level %d reached, victory!";
		YOU_MUST_BE_AT_LEAST_LEVEL = "%s must be at least level %d to win.";
		YOU_POSSESS_GOLD_COINS_VICTORY = "%s possess %d gold coins, victory!";
		YOU_MUST_POSSESS_AT_LEAST = "%s must possess at least %d gold coins to win.";
		
		
		CHOOSE_A_SEED = "Choose a seed (a natural integer)\n 0 for the classic rules";
		CREATE_A_UNIVERSE = "Create a universe";
		HISCORES_CR = "\nHiscores: \n";
		SEED = "Seed: ";
		SEED_DOTS = "Graine...";
		CHOOSE_A_CHALLENGE = " Choose a challenge";
		CHOICE_OF_THE_CHALLENGE = "Choice of the challenge";
		CHOOSE = "Choose";
		BEST_TIMES = " Best times";
		HISCORES = "Hiscores";
		CREATE = "Create";
		CANT_WRITE_IN_HISCORES = "Can't write in hiscores (hiscores.data file).";
		ERROR_LOADING_HISCORES = "Error loading hiscores (hiscores.data file).";
		CANT_WRITE_IN_LOG = "Can't write in log (%s file).";
		CONGRATULATIONS_HISCORES = "Congratulations, %s get a hiscore!";
		HISCORES_LINE = "%s (%d seconds, seed %d)\n";
		RULE_NEGATION = "not (%s)";
		INVALID_RULE = "invalid rule";
		
		BAY = "Bay";
		ISLAND = "Island";
		ISLANDS = "Islands";
		LAKE = "Lake";
		FORTRESS = "Fortress";
		TREE_CITY = "Tree-city";
		MASSIF  = "Massif";
		FOREST = "Forest";
		CAPE = "Cape";
		CAVES = "Caves";
		MOUNTAINS = "Mountains";
		ROADSTEAD = "Roadstead";
		WOOD = "Wood";
		ARENA = "Arena";
		MOUTH = "Mouth";
		JUNCTION = "Junction";
		RIFT = "Rift";
		RAVINE = "Rift";
		SWAMP = "Swamp";
		LAGOON = "Lagoon";
		BRIDGE = "Bridge";
		
		DISTRIBUTION_OF_GOD_POINTS =  "Distribution of %.2f God's points";
		DISTRIBUTION_OF_SKILL_POINTS = "Distribution of %.2f skill points";
		PLUS = "+";
		MINUS = "-";
		VALIDATE = "Validate";
		CANCEL = "Cancel";
		SKILL_POINTS_DISTRIBUTION = "Skill points distribution";
		UNIVERSE_EDITION = "Edit universe";
		
		ERROR_INVALID_NUMBER = "Error: invalid number";
		ERROR_EMPTY_NAME = "Error: empty name";
		ERROR_SELF_REFERENCE = "Error: self-reference";
		RULES = "Rules";
		RULE_ON_AN_OBJECT = "Rule on an object";
		RULE_ON_A_MONSTER = "Rule on a monster";
		RULE_ON_THE_PLAYER = "Rule on the player";
		COMPOUND_RULE = "Compound rule";
		NEGATION = "Negation";
		AND = "and";
		OR = "or";
		SELL_RULE = "Sell rule";
		PURCHASE_RULE = "Purchase rule";
		PICKUP_RULE = "Pickup rule";
		INVENTORY_FILTER = "Inventory filter";
		FLEE_RULE = "Flee rule";
		NON_INVOLVEMENT_RULE = "Non-involvement rule";
		MERCHANT_SEARCH = "Merchant search";
		SAVE = "Save";
		NEW_RULE = "New rule";
		REMOVE = "Remove";
		EXPORT = "Export";
		IMPORT = "Import";
		RULE = "Rule ";
		PROGRAMMING = "Programming";

		SELECT_A_ZONE = "Select a zone";
		WORLD_MAP = "World map";
		THEORYCRAFT = "Theorycraft";
		SAVING_GAME = "Saving game";
		CANT_WRITE = "Can't write in \"%s.theory\"";
		CHOOSE_A_NAME = "Choose a name";
		CREATION_OF_A_HERO = "Creation of a hero";
		HERO_SELECTION = "Hero selection";
		LOAD_A_HERO = "Load a hero";
		CREATE_A_HERO = "Create a hero";
		DELETE_A_HERO = "Delete a hero";
		SEED_TIME = "%s (%d)\nSeed: %d\tTime: %f";
		
		YOU_COME_ACROSS = "%s comes across: %s (level %d).";
		SUCCESSFUL_TRAP_DETECTION = "Successful trap detection (trap score: %.3f, probability of detection: %.2f%%).";
		COMPLETELY_AVOIDS = "%s completely avoids %s.";
		FAILED_DETECTION = "Failed detection (trap score: %.3f, probability of detection: %.2f%%).";
		TRAP_RESISTANCE_DOUBLED = "Trap resistance doubled.";
		IS_DEAD = "%s is dead.";

		THEORYCRAFT_TITLE = "Theorycraft - %s - %s";
		HEALTH_POINTS_LEFT = "Health points: %.2f/%.2f";
		DISABLE = "Disable";
		ENABLE = "Enable";
		STOP_FIGHT = "Stop fight";
		NEXT_LEVEL = "Next level: %.2f/%.2f";
		GAME = "Game";
		LOAD_A_GAME = "Load a game";
		EXIT = "Exit";
		CHALLENGE = "Challenge";
		CHANGE_THE_CHALLENGE = "Change the challenge";
		COMPLETE_THE_CHALLENGE = "Complete the challenge";
		TRAVEL = "Travel";
		TRAVEL_TO = "%s travel to %s in %.3f seconds (%.3f × %.2f)";
		INVENTORY = "Inventory";
		SHOPPING = "Shopping";
		SKILLS = "Skills";
		CHARTS = "Charts";
		UNIVERSE_INFO = "Universe information";
		INFORMATIONS = "Informations";
		NEW_GAME = "New game";
		STOP_TO_FIGHT = "Stop to fight";
		DEFEATS = "Défaites =";
		DEFEATED_ENEMIES = "Defeated enemies =";
		LEVELS_WON = "Levels won =";
		LETHAL_TRAPS = "Lethal traps =";
		SURVIVED_TRAPS = "Survived traps =";
		YOU_HAVE_ALREADY_FINISHED_THE_GAME = "%s has already finished the game!";
		YOU_HAVE_DEFEATED_THE_FINAL_BOSS = "%s has defeated the final boss!";
		TIME_PASSED = "Time passed: %.2f seconds!";
		AVOIDS_MONSTER = "%s avoids %s (level %d)";
		
		HTML_BODY = "<html><body>";
		PLAYER_STATISTICS = "Player statistics";
		PLAYER_STATISTICS_LIST = "Name: %s <br>\nLevel: %d <br>\nChallenge: %s <br>\nTotal game time: %.2f seconds <br>\nGold: %.2f <br>\nLoad: %.2f / %.2f<br>\nTotal skill points: %.2f<br>Total divine points: %.2f (%.2f sacrificed orbs)";
		UNIVERSE_INFORMATION_LIST = "<h2>Universe information</h2>\nUniverse: %d \n<br>Number of dimensional trips: %d\n<br>Maximum player level: %d\n<br>Universe constants: <ul>\n<li>Base health points: %.0f \n<li>Initial skill points: %.0f \n<li>Basic efficiency coefficient: %.3f \n<li>Maximum enchantment power: %.3f \n<li>Objects maximal quality: %.2f%%\n<li>Penalty for inappropriate materials: from %.2f%% to %.2f%% (%.2f%% of average)\n<li>Random range (damage, time, gold): from %.3f to %.3f (average of 1.000)\n<li>Gold penalty at each death (base): %.3f%%\n<li>Time penalty at each death (base): %.0f seconds \n<li>Trap probability (base): %.2f%%\n<li>Champion probability (base): %.2f%%\n<li>Time penalty to change challenge (base): %.0f seconds\n<li>Cost of a dimensional trip (base): %.0f orbs of each type \n<li>Time penalty to travel (base): %.0f seconds<li>Time penalty to change universe (base): %.0f seconds<li>Probability of an object being a ressource: %.2f%%</ul>";
		H3_AVAILABLE_OBJECTS_H3 = "<h3>Available objects:</h3>";
		H3_AVAILABLE_OBJECTS_H3 = "<h3>Unavailable objects:</h3>";
		BASE_DISTRIBUTION_OF_MONSTER_SKILL_POINTS = "<h3>Base distribution of monster skill points</h3><ul>";
		LI_SKILL_POINTS = "<li>%s: %.2f%% of skill points";
		LI_END = "</li>";
		H3_ZONE_INFORMATION_H3 = "<h3>Zone information</h3>";
		NUMBER_OF_ZONES = "Number of zones:";
		DL = "<dl>";
		DT_ZONE = "<dt>%s (%d-%d) %s</dt>";
		NO_LOOT_Z = "(no loot)";
		TRAPS_AND_CHAMPION_PROBABILITY = "<dd>Trap probability: %.3f%%<br>Champion probability: %.3f%%";
		MONSTER_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Monster level:  minimum %d (%.0f points), average %.2f (%.0f points), maximum %d (%.0f points)";
		CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Champion level: minimum %d (%.0f points), average %.2f (%.0f points), maximum %d (%.0f points) </dd>";
		DL_END = "</dl>";
		H2_CRAFTING_H2 = "<h2>Crafting</h2>";
		BODY_HTML_END = "</body></html>";
		INFORMATION_AND_STATISTICS = "Information and statistics";

		FIGHT_STATS = "<h3>Fight</h3><ul>";
		FIGHT_STATS_LIST = "<li>Enemies fought: %.0f / %.0f (%.2f%%) \n <li>Fights won: %.0f / %.0f (%.2f%%) \n <li>Fights fled: %.0f / %.0f (%.2f%%) \n <li>Death in fight: %.0f / %.0f (%.2f%%) \n <li>Hit: %.0f / %.0f (%.2f%%) \n <li>Fleeing success: %.0f / %.0f (%.2f%%) \n <li>Survived traps: %.0f / %.0f (%.2f%%) \n <li>Traps avoided: %.0f / %.0f (%.2f%%) </ul> \n <h3> Income and trading</h3> \nMerchants encountered: %.0f \n <br> Visited mystic forges: %.0f \n <br> Mystic forge uses: %.0f \n <br> Items found: %.0f normal, %.0f magic, %.0f rare, %.2f resources, %.2f orbs\n <br> Total gold earned: %.2f \n <br> Total gold spent: %.2f \n <ul> <li>Collected on monsters: %.2f (%.2f%%) \n <li>Life annuity: %.2f (%.2f%%) \n <li>Sale of normal objects: %.2f (%.2f%%) \n <li>Sale of magic items: %.2f (%.2f%%) \n <li>Sale of rare items: %.2f (%.2f%%) \n <li>Sale of resources: %.2f (%.2f%%) \n <li>Sale of other objects: %.2f (%.2f%%)\n <li>Gold lost on death: %.2f (%.2f%%)\n <li>Gold spent on normal objects: %.2f (%.2f%%)\n <li>Gold spent on resources: %.2f (%.2f%%)\n <li>Gold spent on orbs: %.2f (%.2f%%)\n <li>Gold spent on other objects: %.2f (%.2f%%)";
		UL_END = "</ul>";
		
		ALCHEMICAL = "alchemical %s";
		MAX_ZONE_LEVEL = "Acces to level %.2f zones";
		ZONE_LEVEL_MULTIPLIER = "Accessible zone level multiplier";
		DIVINE_POINTS_MULTIPLIER = "Divine points multiplier";
		FIRST_STRIKE_MULTIPLIER = "First strike damage multiplier";
		
		EQUATION_CAP_MULTIPLIER = "Equation cap multiplier";
		CONST_CAP_MULTIPLIER = "Constant cap multiplier";
		
		FIRST_STRIKE = "First strike";
		ALLOY = "%s alloy";
		OF_ALLOY = "of %s alloy";
		ZONE_LEVEL = "Zone level";
		RESOURCES_WEIGHT_MULTIPLIER = "Resources weight multiplier";
		EQUIPMENT_WEIGHT_MULTIPLIER = "Equipment weight multiplier";
		MAX_LEVEL="Maximum level";
		
		PLAYER_INFOS = getPlayerInfos();
		UNIVERSE_EQUATIONS_NAME = getEquationName();
	}
	}
	
	static String getPlayerInfos()
	{
		String res = Local.ATTACKS_PER_SECOND + Local.COLON + " %.3f\n"+
		Local.AVERAGE_DAMAGE_OF_A_BASIC_ATTACK + Local.COLON + " %.2f\n"+
		Local.CRITICAL_STRIKE_PROBABILITY + Local.COLON + " %.3f%%\n"+
		Local.CRITICAL_STRIKE_MULTIPLIER + Local.COLON + " %.2f\n"+
		Local.AVERAGE_DAMAGE_PER_ATTACK + Local.COLON + " %.2f\n"+
		Local.AVERAGE_DAMAGE_PER_SECOND + Local.COLON + " %.2f\n"+
		Local.DAMAGE_MULTIPLIER + Local.COLON + "\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[0] + Local.COLON + " %.2f\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[1] + Local.COLON + " %.2f\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[2] + Local.COLON + " %.2f\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[3] + Local.COLON + " %.2f\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[4] + Local.COLON + " %.2f\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[5] + Local.COLON + " %.2f\n"+
		Local.PROBABILITY_TO_HIT_A_MONSTER + Local.COLON + "\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		Local.PROBABILITY_TO_DODGE_AGAINST_A_MONSTER + Local.COLON + "\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		Local.DAMAGE_REDUCTION + Local.COLON + " %.3f%%\n"+
		Local.TRAPS_REDUCTION_MULTIPLIER + Local.COLON + " %.3f\n"+
		Local.TRAPS_DAMAGE_REDUCTION + Local.COLON + " %.3f%%\n"+
		Local.DAMAGE_ABSORPTION + Local.COLON + " %.3f\n"+
		Local.HEALTH_POINTS_PER_VITALITY_POINT + Local.COLON + " %.3f\n"+
		Local.HEALTH_POINTS + Local.COLON + " %.3f\n"+
		Local.EFFECTIVE_HEALTH_POINTS + Local.COLON + " %.3f\n"+
		Local.HEALTH_POINTS_PER_SECOND + Local.COLON + " %.3f\n"+
		Local.TOTAL_REGENERATION + Local.COLON + " %.3f " + Local.SECONDS + "\n"+
		Local.LIFE_LEECH + Local.COLON + " %.3f%%\n"+
		Local.TIME_BEFORE_THE_FIRST_ATTACK + Local.COLON + " %.3f " + Local.SECONDS + "\n"+
		Local.INITIATIVE_BONUS_AGAINST_A_TRAP + Local.COLON + " %.3f\n"+
		Local.INITIATIVE_TIME_AGAINST_A_TRAP + Local.COLON + " %.3f " + Local.SECONDS + "\n"+
		Local.FINDING_A_MONSTER + Local.COLON + " %.3f " + Local.SECONDS + "\n"+
		Local.FINDING_A_TRADER + Local.COLON + " %.3f " + Local.SECONDS + "\n"+
		Local.FINDING_A_FORGE + Local.COLON + " %.3f " + Local.SECONDS + "\n"+
		Local.RESURRECTION + Local.COLON + " %.3f " + Local.SECONDS + "\n"+	
		Local.MAXIMUM_LOAD + Local.COLON + " %.3f\n"+
		Local.SALE_PRICE_MULTIPLIER + Local.COLON + " %.3f\n"+
		Local.BUY_PRICE_MULTIPLIER + Local.COLON + " %.3f\n"+
		Local.PROBABILITY_TO_FLEE + Local.COLON + " %.3f%%\n"+
		Local.TIME_TO_FLEE + Local.COLON + " %.3f " + Local.SECONDS + "\n"+	
		Local.PROBABILITY_OF_AN_OBJECT_BEING_MAGIC + Local.COLON + " %.3f%%\n"+
		Local.PROBABILITY_OF_AN_OBJECT_BEING_RARE + Local.COLON + " %.3f%%\n"+
		Local.PROBABILITY_OF_AN_OBJECT_BEING_HIGH_QUALITY + Local.COLON + " %.3f%%\n"+
		Local.GOLD_MULTIPLIER + Local.COLON + " %.3f\n"+
		Local.RESOURCES_MULTIPLIER + Local.COLON + " %.3f\n"+
		Local.AVERAGE_MONSTER_LOOT + Local.COLON + "\n" +
		"  " + Local.TOTAL_NUMBER_OF_ITEMS + Local.COLON + " %.3f\n"+
		"  " + Local.NUMBER_OF_RESOURCES + Local.COLON + " %.3f\n"+
		"  " + Local.NUMBER_OF_NONRESOURCE_OBJECTS + Local.COLON + " %.3f\n"+
		"  " + Local.TOTAL_QUANTITY_OF_RESOURCES + Local.COLON + " %.3f\n"+
		"  " + Local.NUMBER_OF_MAGIC_OBJECTS + Local.COLON + " %.3f\n"+
		"  " + Local.NUMBER_OF_RARE_OBJECTS + Local.COLON + " %.3f\n"+
		Local.MINIMUM_ENCHANTMENT_POWER + Local.COLON + " %.4f\n"+
		Local.MAXIMUM_ENCHANTMENT_POWER + Local.COLON + " %.4f\n"+
		Local.PROBABILITY_OF_DODGING_A_FINAL_BLOW + Local.COLON + " %.3f%%\n"+
		Local.TIME_MULTIPLIER + Local.COLON + " %.3f\n"+
		Local.DAMAGE_INFLICTED_ON_ATTACKERS + Local.COLON + " %.3f\n"+
		Local.DAMAGE_REFLECTED_ON_ATTACKERS + Local.COLON + " %.3f%%\n"+
		Local.A_KILLED_MONSTER_GIVES + "\n" +
		Local.CRAFTING_TIME + Local.COLON + " %.3f " + Local.SECONDS + "\n"+	
		Local.CRAFTING_EFFICIENCY + Local.COLON + " %.3f%%\n"+
		Local.ORB_COST + "\n" +
		Local.AVERAGE_LEVEL_OF_MERCHANTS + Local.COLON + " %d+%.2f\n"+
		Local.NUMBER_OF_MERCHANTS_ITEMS + Local.COLON + " %.3f\n"+
		Local.PROBABILITY_OF_DETECTING_A_TRAP + Local.COLON + "\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		Local.INCOME_PER_SECOND + Local.COLON + " %.3f\n"+
		Local.SKILL_POINTS_PER_LEVEL + Local.COLON + " %.3f\n"+
		Local.GENERAL_EXPERIENCE_BONUS + Local.COLON + " %.3f%%\n"+
		Local.EXPERIENCE_BONUS_ON_A_MONSTER + Local.COLON + "\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		"  " + Local.OF_LEVEL + " %d"+ Local.COLON + " %.3f%%\n"+
		Local.PENALTIES_REDUCTION + Local.COLON + " %.3f%%\n"+
		Local.MAX_ZONE_LEVEL+"\n"+
		Local.DIVINE_POINTS_MULTIPLIER + Local.COLON + " %.2f\n"+
		Local.FIRST_STRIKE_MULTIPLIER + Local.COLON + " %.2f\n"+
		Local.EQUATION_CAP_MULTIPLIER + Local.COLON + " %.2f\n"+
		Local.CONST_CAP_MULTIPLIER + Local.COLON + " %.2f\n"+
		Local.RESOURCES_WEIGHT_MULTIPLIER + Local.COLON + " %.3f\n"+
		Local.EQUIPMENT_WEIGHT_MULTIPLIER + Local.COLON + " %.3f\n";
		return res;
	}
	
	static String[] getEquationName()
	{
	String[] tmpStr = {ATTACKS_PER_SECOND,
	HEALTH_POINTS_PER_VITALITY_POINT,
	CRITICAL_STRIKE_PROBABILITY,
	CRITICAL_STRIKE_MULTIPLIER,
	AVERAGE_DAMAGE_PER_ATTACK,
	DAMAGE_REDUCTION,
	DAMAGE_ABSORPTION ,
	SKILLS_NAME[4] + " & " + SKILLS_NAME[5],
	LIFE_LEECH,
	HEALTH_POINTS_PER_SECOND,
	MAXIMUM_LOAD,
	FINDING_A_MONSTER,
	FINDING_A_TRADER,
	FINDING_A_FORGE,
	RESURRECTION,
	BUY_PRICE_MULTIPLIER,
	SALE_PRICE_MULTIPLIER,
	RESOURCES_MULTIPLIER,
	PROBABILITY_THAT_A_BASIC_OBJECT_BECOMES_MAGIC,
	PROBABILITY_THAT_A_MAGIC_OBJECT_BECOMES_RARE,
	PROBABILITY_OF_AN_OBJECT_BEING_HIGH_QUALITY,
	TOTAL_NUMBER_OF_ITEMS,
	MINIMUM_ENCHANTMENT_POWER,
	GOLD_MULTIPLIER,
	DAMAGE_MULTIPLIER + " " + VERSUS + " " + TAGS_NAME[0],
	DAMAGE_MULTIPLIER + " " + VERSUS + " " + TAGS_NAME[1],
	DAMAGE_MULTIPLIER + " " + VERSUS + " " + TAGS_NAME[2],
	DAMAGE_MULTIPLIER + " " + VERSUS + " " + TAGS_NAME[3],
	DAMAGE_MULTIPLIER + " " + VERSUS + " " + TAGS_NAME[4],
	DAMAGE_MULTIPLIER + " " + VERSUS + " " + TAGS_NAME[5],
	PROBABILITY_TO_FLEE,
	TIME_TO_FLEE,
	TIME_BEFORE_THE_FIRST_ATTACK,
	PROBABILITY_OF_DODGING_A_FINAL_BLOW,
	TIME_MULTIPLIER,
	DAMAGE_INFLICTED_ON_ATTACKERS,
	DAMAGE_REFLECTED_ON_ATTACKERS,
	PROPORTION_OF_THE_CADAVERS_HEALTH_POINTS_ABSORBED,
	CRAFTING_TIME,
	CRAFTING_EFFICIENCY,
	COST_IN_ORBS_OF_AN_ORB,
	AVERAGE_MERCHANT_LEVEL_ADDED_TO_ZONE_LEVEL,
	NUMBER_OF_MERCHANTS_ITEMS,
	SKILLS_NAME[42],
	INITIATIVE_BONUS_AGAINST_A_TRAP,
	TRAPS_REDUCTION_MULTIPLIER,
	INCOME_PER_SECOND,
	SKILL_POINTS_PER_LEVEL,
	GENERAL_EXPERIENCE_BONUS,
	SKILLS_NAME[49],
	SKILLS_NAME[48],
	ZONE_LEVEL,
	GOLD_DROP_AVERAGE,
	MONSTERS_SKILLS_POINTS,
	CHAMPIONS_LEVEL,
	TRAPS_DAMAGE,
	PENALTY_COEFFICIENT,
	ZONE_LEVEL_MULTIPLIER,
	DIVINE_POINTS_MULTIPLIER,
	DIVINE_POINTS_ORBS,
	FIRST_STRIKE_MULTIPLIER,
	BASE_EXPERIENCE_MONSTER_LEVEL,
	EQUATION_CAP_MULTIPLIER,
	CONST_CAP_MULTIPLIER,
	DIVINE_POINTS_LEVEL,
	RESOURCES_WEIGHT_MULTIPLIER,
	EQUIPMENT_WEIGHT_MULTIPLIER};
	return tmpStr;
	}
}