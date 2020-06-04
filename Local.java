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
	public static String[] HOLIDAY_NAME;
	public static String[] HOLIDAY_FORMAT;
	
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
	public static String SKILL_POINTS_ORBS;
	public static String MONSTER_LEVEL;
	public static String CHAMPIONS_LEVEL;
	public static String SUPER_CHAMPIONS_LEVEL;
	public static String BASE_MONSTER_LEVEL;
	public static String TRAP_LEVEL;
	
	public static String CHAMPIONS_SKILLS_POINTS;
	public static String SUPER_CHAMPIONS_SKILLS_POINTS;
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
	public static String RESOURCES_MULTIPLIER_MERCHANT;
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
	public static String MERCHANT_LEVEL_MULTIPLIER;
	public static String EXPERIENCE_SPECIFIC_MULTIPLIER;
	public static String PENALTY_MULTIPLIER;
	
	public static String PLAYER_INFOS;
	public static String TOWER;
	public static String FLOOR;
	
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
	public static String AUTOSHOP;
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
	public static String SELLING_PRICE_VAL_DISCOUNT;
	public static String PURCHASE_PRICE_VAL;
	public static String PURCHASE_PRICE_VAL_DISCOUNT;
	public static String FAMILY_VAL;
	public static String WEIGHT_COEFFICIENT_VAL;
	public static String PRICE_COEFFICIENT_VAL;
	public static String EFFICIENCY_COEFFICIENT_VAL;
	public static String ENCHANTMENT_COEFFICIENT_VAL;
	public static String EFFICIENCY_LEVEL_DROP;
	public static String ENCHANTMENT_LEVEL_DROP;
	
	
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
	public static String CRAFT_RULE;
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
	public static String FORGE_SEARCH;
	public static String SAVE;
	public static String NEW_RULE;
	public static String REMOVE;
	public static String ADD;
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
	public static String TRAP_DAMAGE_HALVED;
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
	public static String UNIVERSE_CONST;
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
	public static String PRECIPITATION_AND_TEMPERATURE;
	public static String PRECIPITATION_AND_TEMPERATURE_NOW;
	public static String TRAPS_AND_CHAMPION_PROBABILITY;
	public static String MONSTER_LEVEL_MINIMUM_AVERAGE_MAXIMUM;
	public static String CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM;
	public static String SUPER_CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM;
	public static String DL_END;
	public static String H2_CRAFTING_H2;
	public static String H2_ACHIEVEMENTS_H2;
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
	public static String ORB_PROBABILITY_FOR_LEVEL;
	public static String MONSTERS;
	public static String H2_LEVEL_H2;
	public static String DD;
	public static String DT;
	public static String COLD_RESISTANCE;
	public static String HEAT_RESISTANCE;
	public static String PRECIPITATION_RESISTANCE;
	public static String COLD_AFFINITY;
	public static String HEAT_AFFINITY;
	public static String PRECIPITATION_AFFINITY;
	public static String CURRENT_MALUS;
	public static String CURRENT_BONUS;
	
	public static String SNOW;
	public static String RAIN;
	public static String HAIL;
	public static String NO_PRECIPITATIONS;
	public static String NO_PRECIPITATIONS_NEG;
	public static String COLD;
	public static String HEAT;
	public static String PRECIPITATION;
		
	public static String TEMPERATURE_NORMALIZATION;
	public static String PRECIPITATION_NORMALIZATION;	
	public static String AUTO_DISTRIBUTION;
	public static String AUTO_CRAFT;
	public static String OVERLOAD;
	public static String UNDERLOAD;
	public static String OVERLOAD_RESISTANCE;
	public static String UNDERLOAD_AFFINITY;
	public static String ACHIEVEMENTS_AFFINITY;
	public static String OVERLOAD_PENALTY;
	public static String UNDERLOAD_BONUS;
	public static String CLEARANCE_SALE_INVENTORY_MULTIPLIER;
	public static String DISCOUNT_PRICE_MULTIPLIER;
	public static String CLEARANCE_SALE;
	public static String ACHIEVEMENTS;
	public static String ACHIEVEMENT_BONUS;
	public static String HOLIDAY_EFFECT_MULTIPLIER;
	public static String H3_HOLIDAYS_H3;
	public static String HOLIDAY_LVL_MULT;
	public static String HOLIDAY_DROP_MULT;
	public static String HOLIDAY_PRECIPITATION_MULT;
	public static String HOLIDAY_TEMPERATURE_MULT;
	public static String MUTUAL_FLEE;
	
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
		"Détection des pièges", "Bonus d'initiative face aux pièges", "Bonus de résistance aux pièges","Rente viagère","Éducation", "Apprentissage", "Overkilling", "Hardiesse", "Réduction des pénalités", "Accès étendu", "Divinité", "Premier coup", "Maîtrise des équations", "Maîtrise des constantes", "Allégement des ressources", "Allégement de l'équipement", "Résistance au froid", "Résistance à la chaleur", "Résistance aux précipitations",  "Affinité au froid", "Affinité à la chaleur", "Affinité aux précipitations","Résistance à la surcharge", "Affinité à la sous-charge", "Affinité au haut-faits", "Affinité aux vacances", "Accro du shopping", "Promophile"};
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
		"Nom de l'orbe", // objet.material.name
		"Equipé", // objet.equiped
		"Soldé", // objet.discount
		"Nombre d'enchantements" // object.nb_ench()
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
		"Dégats réfléchis sur les attaquants (%)", // 100*represailles()
		"Points de compétences",
		"Points de compétences à distribuer"
		};
		RULE_PLAYER_TYPE_NAME = tmp12;
	
		String[] tmp13 = {"Compétence","Points","Equipement","Total","Priorité"};
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
		"Points de compétences initiaux",
		"Probabilité de tomber sur un piège",
		"Probabilité qu'un objet soit soldé",
		"Probabilité de déstockage",
		"Probabilité qu'un monstre soit un champion",
		"Probabilité qu'un champion soit un super-champion",
		"Probabilité qu'un objet soit une ressource",
		"Pénalité pour les matériaux inappropriés",
		"Pénalité de temps pour changer de défi",
		"Pénalité de temps d'un voyage",
		"Pénalité de temps d'un voyage dimensionnel",
		"Pénalité de temps à chaque mort",
		"Pénalité d'or à chaque mort",
		"Plage aléatoire",
		"Qualité maximale des objets",
		"Expérience pour le sacrifice d'un orbe",
		"Température idéale",
		"Niveau des marchands (pourcentage du niveau de la zone)",
		"Quantité de ressources de base (butin)",
		"Quantité de ressources de base (marchands)",
		"Nombre de zones",
		"Coût d'un voyage dimensionnel",
		"Muliplicateur des points de compétences (champions)",
		"Muliplicateur des points de compétences (super-champions)",
		"Coefficient d'efficacité de base",
		"Puissance maximale des enchantements",
		"Nombre maximal de coups échangés dans un combat"};
		UNIVERSE_STATS_NAME = tmp19;
		
		String[] tmp20 = {"Pas de vacances","Nouvel an","Fête du travail","Halloween","Noël","Équinoxe du printemps"};
		HOLIDAY_NAME = tmp20;
		
		String[] tmp21 = {"%s","%s ivre","%s en grève","%s d'Halloween","%s de Noël","%s printanier"};
		HOLIDAY_FORMAT = tmp21;
		
		COLON = " :";
		SECONDS = "secondes";
		ALL = "Tout";
		EQUIPPED = "Équipés";
		NOT_EQUIPPED = "Non équipés";
		MONSTER_TOO_STRONG = "Monstre trop fort";
		MONSTER_TOO_WEAK = "Monstre trop faible";
		LIFE_ANNUITY = "%s recoit %g écus de rente (%.0f rencontres × %g écus/rencontre)";
		BUYING_OBJECTS = "Achat de %s à %s pour %g écus";
		SELLING_OBJECTS = "Vente de %s à %s pour %g écus";
		DROPPING_OBJECTS = "%s place %s dans la forge mystique";
		THROW_AWAY_OBJECTS = "%s jette %s";
		PICKING_OBJECTS = "%s récupère %s depuis la forge mystique";
		N_OBJECTS = "%d objets";
		SHORT_INFOS = "Niveau %.0f\nTemps total de jeu : %g s\nOr : %g écus\nCharge : %g / %g\n";
		LEVEL_N = "Niveau %.0f\n";
		
		LOOKING_FOR_AN_ENNEMY = "Recherche d'un ennemi : %g secondes";
		LOOKING_FOR_A_TRADER = "Recherche d'un marchand : %g secondes";
		ENCOUNTER = "%s rencontre %s (niveau %g)";
		LOOKING_FOR_A_MYSTIC_FORGE = "Recherche d'une forge : %g secondes";
		A_HITS_B = "%s frappe %s en %g secondes (temps total pour %s : %g)\n";
		A_VERSUS_B = "%s versus %s (niveau %.0f)";
		VERSUS = "Versus";
		INITIATIVES = "Initiatives : %g pour %s, %g pour %s";
		MULTIPLIER = "   Multiplicateur ×%g (%s)\n";
		DODGES_THE_HIT = "   %s esquive le coup de %s (%g%% de probabilité)";
		CRITICAL_STRIKE = "   Coup critique ×%g (%g%% de probabilité)\n";
		DAMAGE_INFLICTED = "   %s inflige %g points de dégâts à %s (%g de base, %g%% de réduction, %g d'absorption)";
		IMMUNITY_TO_FINAL_BLOW = "\n   L'immunité au coup final sauve %s, il conserve 0.1 point de vie (probabilité : %g%%)";
		THORNS = "\n   Les épines de %s infligent %g points de dégâts à %s (%g de base, %g%% de réduction)";
		REPRISALS = "\n   Les représailles de %s causent %g points de dégâts à %s (%g de base, %g%% de réduction)";
		LIFE_LEECH_EFFECT = "\n   %s récupère %g points de vie (vol de vie)";
		
		TRY_TO_FLEE = "%s tente de fuire face à %s (%g secondes)";
		FLEE_SUCCESS = "   Succés de la fuite (%g%% de probabilité)";
		FLEE_FAIL = "   Échec de la fuite (%g%% de probabilité)";
		END_FIGHT_FLEE = "%s fuit face à %s (temps du combat %g secondes, vie restante %g)";
		END_FIGHT_KILL = "%s tue %s (temps du combat %g secondes, vie restante %g)";
		END_FIGHT_DEATH = "%s est tombé face à %s de niveau %.0f (temps du combat %g secondes)";
		
		NO_LOOT = "Pas de butin sur %s";
		GOLD_LOOT = "%s ramasse %g écus sur le cadavre de %s";
		ITEMS_LOOT = "%s ramasse %s sur le cadavre de %s";
		OBJECTS_LEFT_BEHIND = "Objets abandonnés : %s";
		
		RESET_BUILD = "Redistribution des points de compétence";
		FULL_HEAL = "Récupération de %g points de vie en %g secondes";
		EARN_EXPERIENCE = "%s reçoit %g points d'expérience (%g × %g × %g)";
		LEVEL_UP = "%s passe du niveau %.0f au niveau %.0f";
		UNIVERSE_CHANGE = "Pénalité de temps liée au changement d'univers : %g secondes (%g × %g)";
		CHALLENGE_CHANGE = "Pénalité de temps liée au changement de défi : %g secondes (%g × %g)";
		DEATH_GOLD_LOSS = "%s perd %g × %g%% de son or (%g écus)";
		DEATH_TIME_PENALTY = "Pénalité de temps liée à la mort : %g secondes (%g × %g)";
		RESURRECTION_TIME = "Résurrection : %g secondes";
		NECROPHAGY = "%s dévore le cadavre de %s et récupère %g points de vie";
		
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
		MONSTERS_SKILLS_POINTS = "Points de compétences des monstres";
		DIVINE_POINTS = "Points divins";
		DIVINE_POINTS_ORBS = "Points divins (en fonction des orbes sacrifiés)";
		DIVINE_POINTS_LEVEL = "Points divins (en fonction du niveau)";
		SKILL_POINTS_ORBS = "Points de compétences (en fonction des orbes sacrifiés)";
		MONSTER_LEVEL = "Niveau des monstres";
		CHAMPIONS_LEVEL = "Niveau des champions (relatif aux monstres)";
		SUPER_CHAMPIONS_LEVEL = "Niveau des super-champions (relatif aux monstres)";
		TRAP_LEVEL = "Niveau des pièges (relatif au niveau de la zone)";
		BASE_MONSTER_LEVEL = "Niveau des monstres de base (relatif)";
		CHAMPIONS_SKILLS_POINTS = "Points de compétences des champions";
		SUPER_CHAMPIONS_SKILLS_POINTS = "Points de compétences des super-champions";
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
		RESOURCES_MULTIPLIER_MERCHANT = "Multiplicateur de ressources des marchands";
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
		A_KILLED_MONSTER_GIVES = "Un monstre tué donne %g%% de sa vie";
		CRAFTING_TIME = "Temps du crafting";
		CRAFTING_EFFICIENCY = "Rendement du crafting";
		ORB_COST = "Un orbe coûte %g%% d'orbe";
		AVERAGE_LEVEL_OF_MERCHANTS = "Niveau moyen des marchands";
		NUMBER_OF_MERCHANTS_ITEMS = "Nombre moyen d'objets chez les marchands";
		MIN_NUMBER_OF_MERCHANTS_ITEMS = "Nombre minimal d'objets chez les marchands";
		MAX_NUMBER_OF_MERCHANTS_ITEMS = "Nombre maximal d'objets chez les marchands";
					
		PROBABILITY_OF_DETECTING_A_TRAP = "Probabilité de détecter un piège";
		INCOME_PER_SECOND = "Rente en écus par rencontre";
		SKILL_POINTS_PER_LEVEL = "Points de compétences par niveau";
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
		MERCHANT_LEVEL_MULTIPLIER = "Muliplicateur du niveau des marchands";
		EXPERIENCE_SPECIFIC_MULTIPLIER = "Muliplicateur spécifique d'expérience (écart de niveau : %.0f)";
		PENALTY_MULTIPLIER = "Mutiplicateur des pénalités";
	
		WINDOW_CURVES = "Courbes sur les mécanismes du jeu";
		WINDOW_INVENTORY = "Inventaire";
		WINDOW_SHOP = "Boutique de %s (niveau %g)";
		WINDOW_FORGE = "Forge mystique";
		WINDOW_INVENTORY_PROMPT = "Or : %g écus, charge : %g kg / %g kg";
		EQUIP = "Équiper";
		UNEQUIP = "Déséquiper";
		SPLIT = "Séparer";
		DISCARD = "Jeter";
		BUY = "Acheter";
		SALE = "Vendre";
		AUTOSHOP = "Auto-shopping";
		DEPOSIT = "Déposer";
		PICK_UP = "Ramasser";
		COMBINE = "Combiner";

		TYPE_VAL = "Type : %s (%s)\n\n";
		BASE_VAL = "Base : %s\n";
		EFFECTIVE_LEVEL_VAL = "Niveau effectif : %g\n";
		SLOT_VAL = "Emplacement : %s\n";
		QUALITY_VAL = "Qualité : %g%%\n";
		EFFICIENCY_VAL = "Efficacité : %g\n     (%g×%.3f×%.3f)\n";
		ENCHANTMENT_VAL = "Enchantement : %g\n     (%g×%.3f)\n";
		NUMBER_OF_ENCHANTMENTS_VAL = "Nombre d'enchantements : %d\n";
		SKILL_BONUS_VAL = "Bonus compétence : %g points\n";
		QUANTITY_VAL = "Quantité : %g\n";
		WEIGHT_VAL = "Poids (base) : %g kg\nPoids (allégé) : %g kg\n";
		REAL_PRICE_VAL = "Prix réel : %g écus\n";
		SELLING_PRICE_VAL = "Prix de vente : %g\n\n";
		SELLING_PRICE_VAL_DISCOUNT = "Prix de vente : %g (soldé ×%g)\n\n";
		PURCHASE_PRICE_VAL = "Prix d'achat : %g\n";
		PURCHASE_PRICE_VAL_DISCOUNT = "Prix d'achat : %g (soldé ×%g)\n";
		FAMILY_VAL = "Famille : %s\n";
		WEIGHT_COEFFICIENT_VAL = "Coefficient de poids : %g\n";
		PRICE_COEFFICIENT_VAL = "Coefficient de prix : %g\n";
		
		EFFICIENCY_COEFFICIENT_VAL = "Coefficient d'efficacité : %g\n";
		ENCHANTMENT_COEFFICIENT_VAL = "Coefficient d'enchantement : %g\n";
		EFFICIENCY_LEVEL_DROP = "Niveau d'efficacité en fonction du niveau du butin";
		ENCHANTMENT_LEVEL_DROP = "Niveau d'enchantement en fonction du niveau du butin";
		
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
		RAVINE = "Ravin";
		SWAMP = "Marais";
		LAGOON = "Lagune";
		BRIDGE = "Pont";
		
		DISTRIBUTION_OF_GOD_POINTS = "Distribution de %g points divins";
		DISTRIBUTION_OF_SKILL_POINTS = "Distribution de %g points de compétences";
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
		CRAFT_RULE = "Règle de craft";
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
		FORGE_SEARCH = "Recherche de forge mystique";
		SAVE = "Enregister";
		NEW_RULE = "Nouvelle règle";
		REMOVE = "Supprimer";
		ADD = "Ajouter";
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
		SEED_TIME = "%s (%.0f)\nSeed : %d\tTemps : %f";
		
		YOU_COME_ACROSS = "%s tombe sur : %s (niveau %.0f).";
		SUCCESSFUL_TRAP_DETECTION = "Réussite de la détection (niveau du piège : %g, probabilité de détection : %g%%).";
		COMPLETELY_AVOIDS = "%s évite entièrement %s.";
		FAILED_DETECTION = "Échec de la détection (niveau du piège : %g, probabilité de détection : %g%%).";
		TRAP_DAMAGE_HALVED = "Le piège inflige des demi-dégats.";
		IS_DEAD = "%s est mort.";
		
		THEORYCRAFT_TITLE = "Theorycraft - %s - %s";
		HEALTH_POINTS_LEFT = "Vie : %g/%g";
		DISABLE = "Désactiver";
		ENABLE = "Activer";
		STOP_FIGHT = "Arrêt combat";
		NEXT_LEVEL = "Niveau suivant : %g/%g";
		GAME = "Jeu";
		LOAD_A_GAME = "Charger une partie";
		EXIT = "Quitter";
		CHALLENGE = "Défi";
		CHANGE_THE_CHALLENGE = "Changer de défi";
		COMPLETE_THE_CHALLENGE = "Finir le défi";
		TRAVEL = "Voyager";
		TRAVEL_TO = "%s voyage vers %s en %g secondes (%g × %g)";
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
		TIME_PASSED = "Temps passé : %g secondes !";
		AVOIDS_MONSTER = "%s évite %s (niveau %.0f)";

		HTML_BODY = "<html><body>";
		PLAYER_STATISTICS = "Statistiques sur le joueur";
		PLAYER_STATISTICS_LIST = "Nom : %s<br>\nNiveau : %.0f<br>\nDéfi : %s<br>\nTemps total de jeu : %g secondes<br>\nOr : %g<br>\nCharge : %g / %g<br>\nExpérience : %g<br>\nTotal des points de compétences : %g (%g orbes sacrifiés)<br>\nTotal des points divins : %g (%g orbes sacrifiés)<br>";
		UNIVERSE_CONST = "<br>Constantes de l'univers :<ul>";
		UNIVERSE_INFORMATION_LIST = "<h2>Informations sur l'univers</h2>Univers : %d<br>Nombre de voyages dimensionnels : %d<br>Vacances : %s<br>Niveau maximal du joueur : %d";
		H3_AVAILABLE_OBJECTS_H3 = "<h3>Objets disponibles :</h3>";
		H3_UNAVAILABLE_OBJECTS_H3 = "<h3>Objets indisponibles :</h3>";
		BASE_DISTRIBUTION_OF_MONSTER_SKILL_POINTS = "<h3>Répartition de base des points des monstres</h3><ul>";
		LI_SKILL_POINTS = "<li>%s : %g%% des points";
		LI_END = "</li>";
		H3_ZONE_INFORMATION_H3 = "<h3>Informations sur les zones</h3>";
		NUMBER_OF_ZONES = "Nombre de zones : ";
		DL = "<dl>";
		DT_ZONE = "<dt>%s (%.0f-%.0f) %s</dt>";
		NO_LOOT_Z = "(pas de butin)";
		PRECIPITATION_AND_TEMPERATURE = "<dd>Température moyenne : %g°, précipitations : %g%%";
		PRECIPITATION_AND_TEMPERATURE_NOW = "<dd>Température actuelle : %g°, précipitations : %g%%";
		TRAPS_AND_CHAMPION_PROBABILITY = "<dd>Probabilité de tomber sur un piège : %g%%<br>Probabilité de tomber sur un champion : %g%%<br>Probabilité de tomber sur un super-champion : %g%%";
		MONSTER_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Niveau des monstres : %.0f à %.0f (%g à %g points), moyen %g (%g points)";
		CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Niveau des champions : %.0f à %.0f (%g à %g points), moyen %g (%g points)";
		SUPER_CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Niveau des super-champions : %.0f à %.0f (%g à %g points), moyen %g (%g points)</dd>";
		DL_END = "</dl>";
		H2_CRAFTING_H2 = "<h2>Crafting</h2>";
		H2_ACHIEVEMENTS_H2 = "<h2>Haut-faits</h2>";
		BODY_HTML_END = "</body></html>";
		INFORMATION_AND_STATISTICS = "Informations et statistiques";

		FIGHT_STATS = "<h3>Combat</h3><ul>";
		FIGHT_STATS_LIST = "<li>Ennemis combattus : %g / %g (%g%%)\n<li>Combats gagnés : %g / %g (%g%%)\n<li>Combats fuis : %g / %g (%g%%)\n<li>Mort au combat: %g / %g (%g%%)\n<li>Coups ayant touché : %g / %g (%g%%)\n<li>Tentatives de fuite réussies : %g / %g (%g%%)\n<li>Pièges survécus : %g / %g (%g%%)\n<li>Pièges évités : %g / %g (%g%%)</ul>\n<h3>Économie</h3>\nMarchands rencontrés : %g\n<br>Forges mystiques visitées : %g\n<br>Utilisations de la forge : %g\n<br>Objets trouvés : %.0f normaux, %.0f magiques, %.0f rares, %g matériaux, %g orbes\n<br>Or total gagné : %g\n<br>Or total dépensé : %g\n<ul><li>Ramassé sur les monstres : %g (%g%%)\n<li>Rente viagère : %g (%g%%)\n<li>Vente d'objets normaux : %g (%g%%)\n<li>Vente d'objets magiques : %g (%g%%)\n<li>Vente d'objets rares : %g (%g%%)\n<li>Vente de matériaux : %g (%g%%)\n<li>Vente d'autres objets : %g (%g%%)\n<li>Pertes liées à la mort : %g (%g%%)\n<li>Achat d'objets normaux : %g (%g%%)\n<li>Achat de matériaux : %g (%g%%)\n<li>Achat d'orbes : %g (%g%%)\n<li>Achat d'autres objets : %g (%g%%)";
		UL_END = "</ul>";
		
		ALCHEMICAL = "%s alchimique";
		MAX_ZONE_LEVEL = "Accès aux zone de niveau %g";
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
		ORB_PROBABILITY_FOR_LEVEL = "Probabilité qu'une ressource soit un orbe en fonction du niveau du butin";
		MONSTERS = "Monstres";
		H2_LEVEL_H2 = "<h2>Niveau %.0f</h2>";
		DD = "<dd>";
		DT = "<dt>";
		
		COLD_RESISTANCE = "Muliplicateur du malus liée au froid";
		HEAT_RESISTANCE = "Muliplicateur du malus liée à la chaleur";
		PRECIPITATION_RESISTANCE = "Muliplicateur du malus liée aux précipitations";
		
		COLD_AFFINITY = "Muliplicateur du bonus liée au froid";
		HEAT_AFFINITY = "Muliplicateur du bonus liée à la chaleur";
		PRECIPITATION_AFFINITY = "Muliplicateur du bonus liée aux précipitations";
		
		CURRENT_MALUS = "Malus actuels :";
		CURRENT_BONUS = "Bonus actuels :";
		HAIL = "La grêle tombe sur %s (%g centimètres, %g%% de probabilité)\nLa température chute de %g° (%g°)";
		SNOW = "La neige tombe sur %s (%g centimètres, %g%% de probabilité)\nLa température chute de %g° (%g°)";
		RAIN = "La pluie tombe sur %s (%g centimètres, %g%% de probabilité)\nLa température chute de %g° (%g°)";
		COLD = "Froid";
		HEAT = "Chaleur";
		PRECIPITATION = "Précipitations";
		NO_PRECIPITATIONS = "Pas de précipitations sur %s (%g%% de probabilité)\nLa température augmente de %g° (%g°)";
		NO_PRECIPITATIONS_NEG = "Pas de précipitations sur %s (%g%% de probabilité)\nLa température chute de %g° (%g°)";
		
		TEMPERATURE_NORMALIZATION = "Normalisation de la température";
		PRECIPITATION_NORMALIZATION = "Normalisation des précipitations";
		AUTO_DISTRIBUTION = "Auto-distribution";
		AUTO_CRAFT = "Auto-craft";
		OVERLOAD = "Surcharge";
		UNDERLOAD = "Sous-charge";
		OVERLOAD_RESISTANCE  = "Muliplicateur du malus liée à la surcharge";
		UNDERLOAD_AFFINITY  = "Muliplicateur du bonus liée à la sous-charge";
		ACHIEVEMENTS_AFFINITY = "Muliplicateur du bonus de haut-faits";
		OVERLOAD_PENALTY  = "Malus liée à la surcharge";
		UNDERLOAD_BONUS  = "Bonus liée à la sous-charge";
		TOWER = "Tour";
		FLOOR = " étage %d";
		CLEARANCE_SALE_INVENTORY_MULTIPLIER = "Muliplicateur d'inventaire lors des déstockages";
		DISCOUNT_PRICE_MULTIPLIER = "Muliplicateur du prix des objets soldés";
		CLEARANCE_SALE = "Déstockage";
		ACHIEVEMENTS = "Haut-faits";
		ACHIEVEMENT_BONUS = "Bonus de haut-faits";
		HOLIDAY_EFFECT_MULTIPLIER = "Muliplicateur de l'effet des vacances";
		H3_HOLIDAYS_H3 = "<h3>Effet des vacances (%s) :</h3>";
		HOLIDAY_LVL_MULT = "<li>Muliplicateur de niveau pour %s : %g";
		HOLIDAY_DROP_MULT = "<li>Muliplicateur de ressources : %g";
		HOLIDAY_PRECIPITATION_MULT = "<li>Muliplicateur de précipitations : %g";
		HOLIDAY_TEMPERATURE_MULT = "<li>Muliplicateur de température : %g";
		
		MUTUAL_FLEE = "Le combat s'éternise. Après %d coups échangés, %s et %s abandonnent tous les deux.";
		
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
		"Trap detection", "Initiative bonus against traps", "Resistance to traps","Life annuity","Education", "Learning", "Overkilling", "Boldness", "Penalties reduction","Extended access", "Deity", "First strike","Mastery of equations", "Mastery of constants", "Lighter resources", "Lighter equipment", "Cold resistance", "Heat resistance", "Precipitation resistance", "Cold affinity", "Heat affinity", "Precipitation affinity", "Overload resistance","Underload affinity","Achievements affinity","Holiday affinity","Shopping addict","Discount specialist"};
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
		"Orb name", // object.material.name
		"Equipped", // object.equiped
		"Discounted", // object.discount
		"Number of enchantments" // object.nb_ench()
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
		"Damage multiplier versus humans", // ed_versus_tag(2)
		"Damage reduction (%)", // 100.0-(100.0*reduc())
		"Damage absorption", //absorption()
		"Current health points", // vie()
		"Health points", // vie_max()
		"Effective health points", // vie_max()/reduc()
		"Damage inflicted on attackers", //epines()
		"Damage reflected on attackers (%)"// 100*represailles()
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
		"Damage multiplier versus humans", // ed_versus_tag(2)
		"Damage reduction (%)", // 100.0-(100.0*reduc())
		"Damage absorption", //absorption()
		"Current health points", // vie()
		"Health points", // vie_max()
		"Effective health points", // vie_max()/reduc()
		"Damage inflicted on attackers", //epines()
		"Damage reflected on attackers (%)", // 100*represailles()
		"Skill points",
		"Unallocated skill points"
		};
		RULE_PLAYER_TYPE_NAME = tmp12;
		
		String[] tmp13 = {"Skill","Points","Equipement","Total","Priority"};
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
		"Initial skill points",
		"Trap probability",
		"Discount probability",
		"Clearance sale probability",
		"Champion probability",
		"Super-champion probability",
		"Probability of an object being a ressource",
		"Penalty for inappropriate materials",
		"Time penalty to change challenge",
		"Time penalty to travel",
		"Time penalty to change universe",
		"Time penalty at each death",
		"Gold penalty at each death ",
		"Random range",
		"Objects maximal quality",
		"Experience for the sacrifice of an orb",
		"Ideal temperature",
		"Merchant level (percentage of zone level)",
		"Base quantity of resources (loot)",
		"Base quantity of resources (merchants)",
		"Number of zones",
		"Cost of a dimensional trip",
		"Champions skill points multiplier",
		"Super-champions skill points multiplier",
		"Basic efficiency coefficient",
		"Maximum enchantment power",
		"Maximal length of a fight (number of attacks)"};
		UNIVERSE_STATS_NAME = tmp19;
		
		String[] tmp20 = {"No holiday","New year","International Workers' Day","Halloween","Christmas","Spring equinox"};
		HOLIDAY_NAME = tmp20;
		
		String[] tmp21 = {"%","Drunken %s","%s on strike","Halloween %s","Christmas %s","Spring %s"};
		HOLIDAY_FORMAT = tmp21;
		
		COLON = ":";
		SECONDS = "seconds";
		ALL = "All";
		EQUIPPED = "Equipped";
		NOT_EQUIPPED = "Not equipped";
		MONSTER_TOO_STRONG = "Monster too strong";
		MONSTER_TOO_WEAK = "Monster too weak";
		LIFE_ANNUITY = "%s receives %g gold coins of income (%.0f encounters × %g gold coins/encounter)";
		BUYING_OBJECTS = "Purchase of %s to %s for %g gold coins";
		SELLING_OBJECTS = "Sale of %s to %s for %g gold coins";
		DROPPING_OBJECTS = "%s drops %s in the mystic forge";
		PICKING_OBJECTS = "%s picks %s from the mystic forge";
		THROW_AWAY_OBJECTS = "You throw away %s";
		N_OBJECTS = "%d objects";
		SHORT_INFOS = "Level %.0f\nTotal time: %g s\nMoney: %g gold coins\nLoad: %g / %g\n";
		LEVEL_N = "Level %.0f\n";
		
		LOOKING_FOR_AN_ENNEMY = "Looking for an ennemy: %g seconds";
		LOOKING_FOR_A_TRADER = "Looking for a trader: %g seconds";
		ENCOUNTER = "%s encounters %s (level %g)";
		LOOKING_FOR_A_MYSTIC_FORGE = "Looking for a mystic forge: %g seconds";
		A_HITS_B = "%s hits %s in %g seconds (total time for %s: %g)\n";
		A_VERSUS_B = "%s versus %s (level %.0f)";
		VERSUS = "Versus";
		INITIATIVES = "Initiatives: %g for %s, %g for %s";
		MULTIPLIER = "   Multiplier ×%g (%s)\n";
		DODGES_THE_HIT = "   %s dodges the hit of %s (%g%% probability)";
		CRITICAL_STRIKE = "   Critical strike ×%g (%g%% probability)\n";
		DAMAGE_INFLICTED = "   %s inflicts %g damage points to %s (base %g, reduction %g%%, absorption %g)";
		IMMUNITY_TO_FINAL_BLOW = "\n   Immunity to final_blow saves %s, he keeps 0.1 health points (%g%% probability)";
		THORNS = "\n   Thorns of %s inflict %g damage points to %s (base %g, reduction %g%%)";
		REPRISALS = "\n   Reprisals of %s inflict %g damage points to %s (base %g, reduction %g%%)";
		LIFE_LEECH_EFFECT = "\n   %s recovers %g health points (life leech)";
		
		TRY_TO_FLEE = "%s tries to flee %s (%g seconds)";
		FLEE_SUCCESS = "   Flee success (%g%% probability)";
		FLEE_FAIL = "   Flee fail (%g%% probability)";
		END_FIGHT_FLEE = "%s flees %s (fight duration %g seconds, health points left %g)";
		END_FIGHT_KILL = "%s kills %s (fight duration %g seconds, health points left %g)";
		END_FIGHT_DEATH = "%s looses against %s of level %.0f (fight duration %g seconds)";
		
		NO_LOOT = "No loot on %s";
		GOLD_LOOT = "%s loots %g gold coins on the corpse of %s";
		ITEMS_LOOT = "%s loots %s on the corpse of %s";
		OBJECTS_LEFT_BEHIND = "Objects left behind: %s";
		
		RESET_BUILD = "Redistribution of skills points";
		FULL_HEAL = "Recovery of %g health points in %g seconds";
		EARN_EXPERIENCE = "%s earns %g experience points (%g × %g × %g)";
		LEVEL_UP = "%s rises from level %.0f to level %.0f";
		UNIVERSE_CHANGE = "Time penalty for universe change: %g seconds (%g × %g)";
		CHALLENGE_CHANGE = "Time penalty for challenge change: %g seconds (%g × %g)";
		DEATH_GOLD_LOSS = "%s loses %g × %g%% of his gold (%g gold coins)";
		DEATH_TIME_PENALTY = "Time penalty for death: %g seconds (%g × %g)";
		RESURRECTION_TIME = "Resurrection: %g seconds";
		NECROPHAGY = "%s devours the corpse of %s and recover %g points of life";
		
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
		CHAMPIONS_LEVEL = "Champions level (relative to monsters)";
		SUPER_CHAMPIONS_LEVEL = "Super-champions level (relative to monsters)";
		BASE_MONSTER_LEVEL = "Base monsters level (relative)";
		TRAP_LEVEL = "Trap level (relative to zone level)";
		
		DIVINE_POINTS = "Divine points";
		DIVINE_POINTS_ORBS = "Divine points (for a given number of sacrificed orbs)";
		DIVINE_POINTS_LEVEL = "Divine points (for a given level)";
		SKILL_POINTS_ORBS = "Skill points (for a given number of sacrificed orbs)";
		
		CHAMPIONS_SKILLS_POINTS = "Champions skills points";
		SUPER_CHAMPIONS_SKILLS_POINTS = "Super-champions skills points";
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
		RESOURCES_MULTIPLIER_MERCHANT = "Merchant resources multiplier";
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
		A_KILLED_MONSTER_GIVES = "A killed monster gives %g%% of his health points";
		CRAFTING_TIME = "Crafting time";
		CRAFTING_EFFICIENCY = "Crafting efficiency";
		ORB_COST = "An orb cost %g%% of an orb";
		AVERAGE_LEVEL_OF_MERCHANTS = "Average level of merchants";
		NUMBER_OF_MERCHANTS_ITEMS = "Average number of merchant's items";
		MIN_NUMBER_OF_MERCHANTS_ITEMS = "Minimum number of merchant's items";
		MAX_NUMBER_OF_MERCHANTS_ITEMS = "Maximum number of merchant's items";
		
		PROBABILITY_OF_DETECTING_A_TRAP = "Probability of detecting a trap";
		INCOME_PER_SECOND = "Income in gold coins per encounter";
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
		MERCHANT_LEVEL_MULTIPLIER = "Merchant level multiplier";
		EXPERIENCE_SPECIFIC_MULTIPLIER = "Experience-specific multiplier (level gap: %.0f)";
		PENALTY_MULTIPLIER  = "Penalties multiplier";
		
		TIME_GLOBAL = "Time (global)";
		TIME_FIGHT_TRAPS = "Time (fight/traps)";
		TIME_LIFE_MANAGEMENT = "Time (life management)";
		TIME_SHOPPING_CRAFTING = "Time (shopping/crafting)";
		INCOME_GLOBAL = "Income (global)";
		INCOME_SALES = "Income (sales)";
		EXPERIENCE = "Experience (sources)";
		
		WINDOW_CURVES = "Charts on the game mechanics";
		WINDOW_INVENTORY = "Inventory";
		WINDOW_SHOP = "%s's shop (level %g)";
		WINDOW_FORGE = "Mystic forge";
		WINDOW_INVENTORY_PROMPT = "Money: %g gold coins, load: %g kg / %g kg";
		EQUIP = "Equip";
		UNEQUIP = "Unequip";
		SPLIT = "Split";
		DISCARD = "Discard";
		BUY = "Buy";
		SALE = "Sale";
		AUTOSHOP = "Auto-shopping";
		DEPOSIT = "Deposit";
		PICK_UP = "Pick up";
		COMBINE = "Combine";
		
		TYPE_VAL = "Type: %s (%s)\n\n";
		BASE_VAL = "Base: %s\n";
		EFFECTIVE_LEVEL_VAL = "Effective level: %g\n";
		SLOT_VAL = "Slot: %s\n";
		QUALITY_VAL = "Quality: %g%%\n";
		EFFICIENCY_VAL = "Efficiency: %g\n     (%g×%.3f×%.3f)\n";
		ENCHANTMENT_VAL = "Enchantment: %g\n     (%g×%.3f)\n";
		NUMBER_OF_ENCHANTMENTS_VAL = "Number of enchantments: %d\n";
		SKILL_BONUS_VAL = "Skill bonus: %g points\n";
		QUANTITY_VAL = "Quantity: %g\n";
		WEIGHT_VAL = "Weight (base): %g kg\nWeight (lightened): %g kg\n";
		REAL_PRICE_VAL = "Real price: %g gold coins\n";
		SELLING_PRICE_VAL = "Sale price: %g\n\n";
		SELLING_PRICE_VAL_DISCOUNT = "Sale price: %g (discounted ×g)\n\n";
		PURCHASE_PRICE_VAL = "Purchase price: %g\n";
		PURCHASE_PRICE_VAL_DISCOUNT = "Purchase price: %g (discounted ×g)\n";
		FAMILY_VAL = "Family: %s\n";
		WEIGHT_COEFFICIENT_VAL = "Weight coefficient: %g\n";
		PRICE_COEFFICIENT_VAL = "Price coefficient: %g\n";
		EFFICIENCY_COEFFICIENT_VAL = "Efficiency coefficient: %g\n";
		ENCHANTMENT_COEFFICIENT_VAL = "Enchantment coefficient: %g\n";
		EFFICIENCY_LEVEL_DROP = "Efficiency level according to the loot level";
		ENCHANTMENT_LEVEL_DROP = "Enchantment level according to the loot level";
		
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
		RAVINE = "Rift";
		SWAMP = "Swamp";
		LAGOON = "Lagoon";
		BRIDGE = "Bridge";
		
		DISTRIBUTION_OF_GOD_POINTS =  "Distribution of %g God's points";
		DISTRIBUTION_OF_SKILL_POINTS = "Distribution of %g skill points";
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
		CRAFT_RULE = "Crafting rule";
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
		FORGE_SEARCH = "Mystic forge search";
		SAVE = "Save";
		NEW_RULE = "New rule";
		REMOVE = "Remove";
		ADD = "Add";
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
		SEED_TIME = "%s (%.0f)\nSeed: %d\tTime: %f";
		
		YOU_COME_ACROSS = "%s comes across: %s (level %.0f).";
		SUCCESSFUL_TRAP_DETECTION = "Successful trap detection (trap score: %g, probability of detection: %g%%).";
		COMPLETELY_AVOIDS = "%s completely avoids %s.";
		FAILED_DETECTION = "Failed detection (trap score: %g, probability of detection: %g%%).";
		TRAP_DAMAGE_HALVED = "Trap inflic half the damage.";
		IS_DEAD = "%s is dead.";

		THEORYCRAFT_TITLE = "Theorycraft - %s - %s";
		HEALTH_POINTS_LEFT = "Health points: %g/%g";
		DISABLE = "Disable";
		ENABLE = "Enable";
		STOP_FIGHT = "Stop fight";
		NEXT_LEVEL = "Next level: %g/%g";
		GAME = "Game";
		LOAD_A_GAME = "Load a game";
		EXIT = "Exit";
		CHALLENGE = "Challenge";
		CHANGE_THE_CHALLENGE = "Change the challenge";
		COMPLETE_THE_CHALLENGE = "Complete the challenge";
		TRAVEL = "Travel";
		TRAVEL_TO = "%s travel to %s in %g seconds (%g × %g)";
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
		TIME_PASSED = "Time passed: %g seconds!";
		AVOIDS_MONSTER = "%s avoids %s (level %.0f)";
		
		HTML_BODY = "<html><body>";
		PLAYER_STATISTICS = "Player statistics";
		PLAYER_STATISTICS_LIST = "Name: %s <br>\nLevel: %.0f <br>\nChallenge: %s <br>\nTotal game time: %g seconds <br>\nGold: %g <br>\nLoad: %g / %g<br>\nExperience: %g<br>\nTotal skill points: %g (%g sacrificed orbs)<br>Total divine points: %g (%g sacrificed orbs)";
		
		UNIVERSE_CONST = "<br>Universe constants: <ul>";
		UNIVERSE_INFORMATION_LIST = "<h2>Universe information</h2>Universe: %d<br>Number of dimensional trips: %d<br>Holiday: %s<br>Maximum player level: %d";
		H3_AVAILABLE_OBJECTS_H3 = "<h3>Unavailable objects:</h3>";
		BASE_DISTRIBUTION_OF_MONSTER_SKILL_POINTS = "<h3>Base distribution of monster skill points</h3><ul>";
		LI_SKILL_POINTS = "<li>%s: %g%% of skill points";
		LI_END = "</li>";
		H3_ZONE_INFORMATION_H3 = "<h3>Zone information</h3>";
		NUMBER_OF_ZONES = "Number of zones:";
		DL = "<dl>";
		DT_ZONE = "<dt>%s (%.0f-%.0f) %s</dt>";
		NO_LOOT_Z = "(no loot)";
		PRECIPITATION_AND_TEMPERATURE = "<dd>Average temperature: %g°, precipitation: %g%%";
		PRECIPITATION_AND_TEMPERATURE_NOW = "<dd>Current temperature: %g°, precipitation: %g%%";
		TRAPS_AND_CHAMPION_PROBABILITY = "<dd>Trap probability: %g%%<br>Champion probability: %g%%<br>Super-champion probability: %g%%";
		MONSTER_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Monster level: %.0f to %.0f (%g to %g points), average %g (%g points)";
		CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Champion level: %.0f to %.0f (%g to %g points), average %g (%g points)";
		SUPER_CHAMPION_LEVEL_MINIMUM_AVERAGE_MAXIMUM = "<br>Super-champion level: %.0f to %.0f (%g to %g points), average %g (%g points)</dd>";
		DL_END = "</dl>";
		H2_CRAFTING_H2 = "<h2>Crafting</h2>";
		H2_ACHIEVEMENTS_H2 = "<h2>Achievements</h2>";
		BODY_HTML_END = "</body></html>";
		INFORMATION_AND_STATISTICS = "Information and statistics";

		FIGHT_STATS = "<h3>Fight</h3><ul>";
		FIGHT_STATS_LIST = "<li>Enemies fought: %g / %g (%g%%) \n <li>Fights won: %g / %g (%g%%) \n <li>Fights fled: %g / %g (%g%%) \n <li>Death in fight: %g / %g (%g%%) \n <li>Hit: %g / %g (%g%%) \n <li>Fleeing success: %g / %g (%g%%) \n <li>Survived traps: %g / %g (%g%%) \n <li>Traps avoided: %g / %g (%g%%) </ul> \n <h3> Income and trading</h3> \nMerchants encountered: %g \n <br> Visited mystic forges: %g \n <br> Mystic forge uses: %g \n <br> Items found: %.0f normal, %.0f magic, %.0f rare, %g materials, %g orbs\n <br> Total gold earned: %g \n <br> Total gold spent: %g \n <ul> <li>Collected on monsters: %g (%g%%) \n <li>Life annuity: %g (%g%%) \n <li>Sale of normal objects: %g (%g%%) \n <li>Sale of magic items: %g (%g%%) \n <li>Sale of rare items: %g (%g%%) \n <li>Sale of materials: %g (%g%%) \n <li>Sale of other objects: %g (%g%%)\n <li>Gold lost on death: %g (%g%%)\n <li>Gold spent on normal objects: %g (%g%%)\n <li>Gold spent on materials: %g (%g%%)\n <li>Gold spent on orbs: %g (%g%%)\n <li>Gold spent on other objects: %g (%g%%)";
		UL_END = "</ul>";
		
		ALCHEMICAL = "alchemical %s";
		MAX_ZONE_LEVEL = "Acces to level %g zones";
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
		ORB_PROBABILITY_FOR_LEVEL = "Probability that a resource is an orb according to the loot level";
		MONSTERS = "Monsters";
		H2_LEVEL_H2 = "<h2>Level %.0f</h2>";
		DD = "<dd>";
		DT = "<dt>";
		
		COLD_RESISTANCE = "Cold malus multiplier";
		HEAT_RESISTANCE = "Heat malus multiplier";
		PRECIPITATION_RESISTANCE = "Precipitation malus multiplier";
		COLD_AFFINITY  = "Cold bonus multiplier";
		HEAT_AFFINITY = "Heat bonus multiplier";
		PRECIPITATION_AFFINITY = "Precipitation bonus multiplier";
		
		CURRENT_MALUS = "Current maluses:";
		CURRENT_BONUS = "Current bonuses:";
		HAIL = "Hail is falling over %s (%g centimeters, %g%% probability)\nThe temperature drops by %g° (%g°)";
		SNOW = "Snow is falling over %s (%g centimeters, %g%% probability)\nThe temperature drops by %g° (%g°)";
		RAIN = "Rain is falling over %s (%g centimeters, %g%% probability)\nThe temperature drops by %g° (%g°)";
		COLD = "Cold";
		HEAT = "Heat";
		PRECIPITATION = "Precipitation";
		NO_PRECIPITATIONS = "No precipitation over %s (%g%% probability)\nThe temperature rises by %g° (%g°)";
		NO_PRECIPITATIONS_NEG = "No precipitation over %s (%g%% probability)\nThe temperature drops by %g° (%g°)";
		
		TEMPERATURE_NORMALIZATION = "Temperature normalization";
		PRECIPITATION_NORMALIZATION = "Precipitation normalization";
		AUTO_DISTRIBUTION = "Auto-distribution";
		AUTO_CRAFT = "Auto-craft";
		OVERLOAD = "Overload";
		UNDERLOAD = "Underload";
		OVERLOAD_RESISTANCE = "Overload malus multiplier";
		UNDERLOAD_AFFINITY = "Underload bonus multiplier";
		ACHIEVEMENTS_AFFINITY = "Achievements bonus multiplier";
		OVERLOAD_PENALTY = "Overload malus";
		UNDERLOAD_BONUS = "Underload bonus";
		TOWER = "Tower";
		FLOOR = " floor %d";
		CLEARANCE_SALE_INVENTORY_MULTIPLIER = "Clearance sale inventory multiplier";
		DISCOUNT_PRICE_MULTIPLIER = "Discount price multiplier";
		CLEARANCE_SALE = "Clearance sale";
		ACHIEVEMENTS = "Achievements";
		ACHIEVEMENT_BONUS = "Achievement bonus";
		HOLIDAY_EFFECT_MULTIPLIER = "Holiday effect multiplier";
		H3_HOLIDAYS_H3 = "<h3>Holiday effects (%s):</h3>";
		HOLIDAY_LVL_MULT = "<li>%s level multiplier: %g";
		HOLIDAY_DROP_MULT = "<li>Resources multiplier: %g";
		HOLIDAY_PRECIPITATION_MULT = "<li>Precipitation multiplier: %g";
		HOLIDAY_TEMPERATURE_MULT = "<li>Temperature multiplier: %g";
		MUTUAL_FLEE = "The fight is taking forever. After %d attacks, %s and %s both give up.";
		
		PLAYER_INFOS = getPlayerInfos();
		UNIVERSE_EQUATIONS_NAME = getEquationName();
	}
	}
	
	static String getPlayerInfos()
	{
		String res = Local.ATTACKS_PER_SECOND + Local.COLON + " %g\n"+
		Local.AVERAGE_DAMAGE_OF_A_BASIC_ATTACK + Local.COLON + " %g\n"+
		Local.CRITICAL_STRIKE_PROBABILITY + Local.COLON + " %g%%\n"+
		Local.CRITICAL_STRIKE_MULTIPLIER + Local.COLON + " %g\n"+
		Local.AVERAGE_DAMAGE_PER_ATTACK + Local.COLON + " %g\n"+
		Local.AVERAGE_DAMAGE_PER_SECOND + Local.COLON + " %g\n"+
		Local.DAMAGE_MULTIPLIER + Local.COLON + "\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[0] + Local.COLON + " %g\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[1] + Local.COLON + " %g\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[2] + Local.COLON + " %g\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[3] + Local.COLON + " %g\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[4] + Local.COLON + " %g\n"+
		"  " + Local.VERSUS + " " + Local.TAGS_NAME[5] + Local.COLON + " %g\n"+
		Local.PROBABILITY_TO_HIT_A_MONSTER + Local.COLON + "\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		Local.PROBABILITY_TO_DODGE_AGAINST_A_MONSTER + Local.COLON + "\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		Local.DAMAGE_REDUCTION + Local.COLON + " %g%%\n"+
		Local.TRAPS_REDUCTION_MULTIPLIER + Local.COLON + " %g\n"+
		Local.TRAPS_DAMAGE_REDUCTION + Local.COLON + " %g%%\n"+
		Local.DAMAGE_ABSORPTION + Local.COLON + " %g\n"+
		Local.HEALTH_POINTS_PER_VITALITY_POINT + Local.COLON + " %g\n"+
		Local.HEALTH_POINTS + Local.COLON + " %g\n"+
		Local.EFFECTIVE_HEALTH_POINTS + Local.COLON + " %g\n"+
		Local.HEALTH_POINTS_PER_SECOND + Local.COLON + " %g\n"+
		Local.TOTAL_REGENERATION + Local.COLON + " %g " + Local.SECONDS + "\n"+
		Local.LIFE_LEECH + Local.COLON + " %g%%\n"+
		Local.TIME_BEFORE_THE_FIRST_ATTACK + Local.COLON + " %g " + Local.SECONDS + "\n"+
		Local.INITIATIVE_BONUS_AGAINST_A_TRAP + Local.COLON + " %g\n"+
		Local.INITIATIVE_TIME_AGAINST_A_TRAP + Local.COLON + " %g " + Local.SECONDS + "\n"+
		Local.FINDING_A_MONSTER + Local.COLON + " %g " + Local.SECONDS + "\n"+
		Local.FINDING_A_TRADER + Local.COLON + " %g " + Local.SECONDS + "\n"+
		Local.FINDING_A_FORGE + Local.COLON + " %g " + Local.SECONDS + "\n"+
		Local.RESURRECTION + Local.COLON + " %g " + Local.SECONDS + "\n"+	
		Local.MAXIMUM_LOAD + Local.COLON + " %g\n"+
		Local.SALE_PRICE_MULTIPLIER + Local.COLON + " %g\n"+
		Local.BUY_PRICE_MULTIPLIER + Local.COLON + " %g\n"+
		Local.PROBABILITY_TO_FLEE + Local.COLON + " %g%%\n"+
		Local.TIME_TO_FLEE + Local.COLON + " %g " + Local.SECONDS + "\n"+	
		Local.PROBABILITY_OF_AN_OBJECT_BEING_MAGIC + Local.COLON + " %g%%\n"+
		Local.PROBABILITY_OF_AN_OBJECT_BEING_RARE + Local.COLON + " %g%%\n"+
		Local.PROBABILITY_OF_AN_OBJECT_BEING_HIGH_QUALITY + Local.COLON + " %g%%\n"+
		Local.GOLD_MULTIPLIER + Local.COLON + " %g\n"+
		Local.RESOURCES_MULTIPLIER + Local.COLON + " %g\n"+
		Local.AVERAGE_MONSTER_LOOT + Local.COLON + "\n" +
		"  " + Local.TOTAL_NUMBER_OF_ITEMS + Local.COLON + " %g\n"+
		"  " + Local.NUMBER_OF_RESOURCES + Local.COLON + " %g\n"+
		"  " + Local.NUMBER_OF_NONRESOURCE_OBJECTS + Local.COLON + " %g\n"+
		"  " + Local.TOTAL_QUANTITY_OF_RESOURCES + Local.COLON + " %g\n"+
		"  " + Local.NUMBER_OF_MAGIC_OBJECTS + Local.COLON + " %g\n"+
		"  " + Local.NUMBER_OF_RARE_OBJECTS + Local.COLON + " %g\n"+
		Local.MINIMUM_ENCHANTMENT_POWER + Local.COLON + " %g\n"+
		Local.MAXIMUM_ENCHANTMENT_POWER + Local.COLON + " %g\n"+
		Local.PROBABILITY_OF_DODGING_A_FINAL_BLOW + Local.COLON + " %g%%\n"+
		Local.TIME_MULTIPLIER + Local.COLON + " %g\n"+
		Local.DAMAGE_INFLICTED_ON_ATTACKERS + Local.COLON + " %g\n"+
		Local.DAMAGE_REFLECTED_ON_ATTACKERS + Local.COLON + " %g%%\n"+
		Local.A_KILLED_MONSTER_GIVES + "\n" +
		Local.CRAFTING_TIME + Local.COLON + " %g " + Local.SECONDS + "\n"+	
		Local.CRAFTING_EFFICIENCY + Local.COLON + " %g%%\n"+
		Local.ORB_COST + "\n" +
		Local.MERCHANT_LEVEL_MULTIPLIER + Local.COLON + " %g\n"+
		Local.AVERAGE_LEVEL_OF_MERCHANTS + Local.COLON + " %g\n"+
		Local.NUMBER_OF_MERCHANTS_ITEMS + Local.COLON + " %g\n"+
		Local.PROBABILITY_OF_DETECTING_A_TRAP + Local.COLON + "\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		Local.INCOME_PER_SECOND + Local.COLON + " %g\n"+
		Local.SKILL_POINTS_PER_LEVEL + Local.COLON + " %g\n"+
		Local.GENERAL_EXPERIENCE_BONUS + Local.COLON + " %g%%\n"+
		Local.EXPERIENCE_BONUS_ON_A_MONSTER + Local.COLON + "\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		"  " + Local.OF_LEVEL + " %.0f"+ Local.COLON + " %g%%\n"+
		Local.PENALTIES_REDUCTION + Local.COLON + " %g%%\n"+
		Local.MAX_ZONE_LEVEL+"\n"+
		Local.DIVINE_POINTS_MULTIPLIER + Local.COLON + " %g\n"+
		Local.FIRST_STRIKE_MULTIPLIER + Local.COLON + " %g\n"+
		Local.EQUATION_CAP_MULTIPLIER + Local.COLON + " %g\n"+
		Local.CONST_CAP_MULTIPLIER + Local.COLON + " %g\n"+
		Local.RESOURCES_WEIGHT_MULTIPLIER + Local.COLON + " %g\n"+
		Local.EQUIPMENT_WEIGHT_MULTIPLIER + Local.COLON + " %g\n"+
		Local.COLD_RESISTANCE + Local.COLON + " %g\n"+
		Local.HEAT_RESISTANCE + Local.COLON + " %g\n"+
		Local.PRECIPITATION_RESISTANCE + Local.COLON + " %g\n"+
		Local.OVERLOAD_RESISTANCE + Local.COLON + " %g\n"+
		Local.CURRENT_MALUS + "\n"+
		"  (" + COLD + ") "+ SKILLS_NAME[9] + ", " + SKILLS_NAME[10] + Local.COLON + " %g%%\n"+
		"  (" + HEAT + ") "+ SKILLS_NAME[47] + Local.COLON + " %g%%\n"+
		"  (" + PRECIPITATION + ") "+ SKILLS_NAME[5] + ", " + SKILLS_NAME[6] + Local.COLON + " %g%%\n"+
		"  (" + OVERLOAD + ") "+ SKILLS_NAME[14] + ", " + SKILLS_NAME[31] + Local.COLON + " %g%%\n"+
		Local.COLD_AFFINITY + Local.COLON + " %g\n"+
		Local.HEAT_AFFINITY + Local.COLON + " %g\n"+
		Local.PRECIPITATION_AFFINITY + Local.COLON + " %g\n"+
		Local.UNDERLOAD_AFFINITY + Local.COLON + " %g\n"+
		Local.ACHIEVEMENTS_AFFINITY + Local.COLON + " %g\n"+
		Local.HOLIDAY_EFFECT_MULTIPLIER + Local.COLON + " %g\n"+
		Local.CURRENT_BONUS + "\n"+
		"  (" + COLD + ") "+ SKILLS_NAME[0] + ", " + SKILLS_NAME[33] + Local.COLON + " %g%%\n"+
		"  (" + HEAT + ") "+ SKILLS_NAME[2] + ", " + SKILLS_NAME[12] + Local.COLON + " %g%%\n"+
		"  (" + PRECIPITATION + ") "+ SKILLS_NAME[4] + ", " + SKILLS_NAME[32] + Local.COLON + " %g%%\n"+
		"  (" + UNDERLOAD + ") "+ SKILLS_NAME[15] + Local.COLON + " %g%%\n"+
		"  (" + ACHIEVEMENTS + ") "+ SKILLS_NAME[21] + ", " + SKILLS_NAME[45] + Local.COLON + " %g%%\n"+
		CLEARANCE_SALE_INVENTORY_MULTIPLIER + Local.COLON + " %g\n"+
		DISCOUNT_PRICE_MULTIPLIER + Local.COLON + " %g";
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
	MERCHANT_LEVEL_MULTIPLIER,
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
	PENALTY_MULTIPLIER,
	ZONE_LEVEL_MULTIPLIER,
	DIVINE_POINTS_MULTIPLIER,
	DIVINE_POINTS_ORBS,
	FIRST_STRIKE_MULTIPLIER,
	BASE_EXPERIENCE_MONSTER_LEVEL,
	EQUATION_CAP_MULTIPLIER,
	CONST_CAP_MULTIPLIER,
	DIVINE_POINTS_LEVEL,
	RESOURCES_WEIGHT_MULTIPLIER,
	EQUIPMENT_WEIGHT_MULTIPLIER,
	ORB_PROBABILITY_FOR_LEVEL,
	SKILL_POINTS_ORBS,
	COLD_RESISTANCE,
	HEAT_RESISTANCE,
	PRECIPITATION_RESISTANCE,
	COLD_AFFINITY,
	HEAT_AFFINITY,
	PRECIPITATION_AFFINITY,
	TEMPERATURE_NORMALIZATION,
	PRECIPITATION_NORMALIZATION,
	OVERLOAD_PENALTY,
	OVERLOAD_RESISTANCE,
	UNDERLOAD_AFFINITY,
	ACHIEVEMENTS_AFFINITY,
	RESOURCES_MULTIPLIER_MERCHANT,
	SUPER_CHAMPIONS_LEVEL,
	CLEARANCE_SALE_INVENTORY_MULTIPLIER,
	ACHIEVEMENT_BONUS,
	EFFICIENCY_LEVEL_DROP,
	ENCHANTMENT_LEVEL_DROP,
	UNDERLOAD_BONUS,
	DISCOUNT_PRICE_MULTIPLIER,
	TRAP_LEVEL,
	HOLIDAY_EFFECT_MULTIPLIER};
	return tmpStr;
	}
}