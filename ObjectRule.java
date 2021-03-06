import java.io.Serializable;
import java.util.ArrayList;

// meta_type :
// Règle sur un objet
// Règle sur un monstre
// Règle composée
// Négation
public class ObjectRule implements Serializable {

    public static int ITEM_RULE = 0;
    public static int MONSTER_RULE = 1;	
    public static int PLAYER_RULE = 2;
    public static int COMPOSED_RULE = 3;
    public static int NEGATION_RULE = 4;
    public static int CRAFT_RULE = 5;  
	 
    static private final long serialVersionUID = 500;

    public double param;
    public String name;
    public int meta_type;
    public int type;
    public int operator;

	// item rules
	public boolean buy_rule;
	public boolean sell_rule;
	public boolean filter_rule;
	public boolean pickup_rule;
	
	// monster and player rules
	public boolean flee_rule;
	public boolean avoid_rule;
	public boolean shopping_rule;
	public boolean forge_rule;
	public boolean dist_rule;
	
	public boolean system_rule;
    public ObjectRule ruleA;
	public ArrayList<ObjectRule> ruleList;

    public static String[] possibility(int metatype, int t)
    {
	String[] empty = {};
	if(metatype==ITEM_RULE)
		switch (t)
	    {
		case 0: return Local.RARITY_NAME;
		case 1: return Local.MATERIAL_FAMILY_NAME;
		case 2: return Local.SLOT_NAME;
		case 15: return StaticItem.BaseItemNames;
		case 16: return StaticItem.MaterialNames;
		case 17: return StaticItem.OrbsNames;
		case 18: return Local.RULE_NO_YES;
		case 19: return Local.RULE_NO_YES;
	    default: 
		return empty;
	    }
	else if(metatype==MONSTER_RULE)
		switch (t)
	    {
	    case 1: return Monster.MonsterNames;
	    case 2: return Player.TagsName;
	    default: 
		return empty;
	    }	
	else //if(metatype==PLAYER_RULE)
		return empty;
    }

	public static String[] RuleOperator = Local.RULE_OPERATOR;
    public static String[] RuleItemTypeName = Local.RULE_ITEM_TYPE_NAME;
    public static String[] RuleMonsterTypeName = Local.RULE_MONSTER_TYPE_NAME;
    public static String[] RulePlayerTypeName = Local.RULE_PLAYER_TYPE_NAME;
    
	// return the metaType of the rule (Elementaire, composée ou négation)
	public int metaType()
	{
		return meta_type;
	}

    public boolean recursiveBadConsistency(ArrayList<ObjectRule> A)
    {
		if (meta_type == ITEM_RULE || meta_type == MONSTER_RULE || meta_type == PLAYER_RULE || meta_type == CRAFT_RULE) return false;
		if (A.contains(this)) return true;
		A.add(this);
		if (meta_type == COMPOSED_RULE)
		{
			if (ruleList== null || ruleList.size()==0) return false;
			for(ObjectRule trule : ruleList)
			{
			if (trule.recursiveBadConsistency(A)) return true;
			}
			return false;
		}
		if (meta_type == NEGATION_RULE)
		{
			if(ruleA==null) return true;
			return ruleA.recursiveBadConsistency(A);
			
		}
		return false;
    }

    public boolean badConsistency()
    {
	ArrayList<ObjectRule> A = new ArrayList<ObjectRule>();
	return recursiveBadConsistency(A);
    }

	// Default constructor
    public ObjectRule()
    {}

	// Composition
    public ObjectRule(ArrayList<ObjectRule> rules, int t)
    {
	meta_type = COMPOSED_RULE;
	type = t;
	ruleList = new ArrayList<ObjectRule>(rules);
	set_flags(false);
    }

	// Negation
  	public ObjectRule(ObjectRule A, boolean b)
    {
	meta_type = NEGATION_RULE;
	ruleA = A;
	set_flags(false);
    }

	public void set_flags(boolean b)
	{
		buy_rule = b;
		sell_rule = b;
		filter_rule = b;
		pickup_rule = b;
		system_rule = b;
		flee_rule = b;
		avoid_rule =  b;
		shopping_rule = b;
		forge_rule = b;
		dist_rule = b;
	}

	// Copy
    public ObjectRule(ObjectRule r)
	{
		update(r);
	}
	
	// Simple object rule (item or monster or player)
	public ObjectRule(int t, int op, double par, int RT)
	{
	meta_type = RT;
	type = t;
	operator = op;
	param = par;
	set_flags(false);
	}

    public void update(ObjectRule r)
	{
		meta_type = r.meta_type;
		type = r.type;
		operator = r.operator;
		param = r.param;
		buy_rule = r.buy_rule;
		sell_rule = r.sell_rule;
		filter_rule = r.filter_rule;
		pickup_rule = r.pickup_rule;
		system_rule = r.system_rule;
		flee_rule = r.flee_rule;
		avoid_rule = r.avoid_rule;
		shopping_rule = r.shopping_rule;
		forge_rule = r.forge_rule;
		dist_rule = r.dist_rule;
		ruleA = r.ruleA;
		if(r.ruleList != null) ruleList = new ArrayList<ObjectRule>(r.ruleList);
		name = r.name;
	}
    
	public String desc()
	{
		String res= Local.INVALID_RULE;
		if (badConsistency() || param < 0) return res;
		
		if (meta_type == ITEM_RULE) 
		{
			if (possibility(meta_type,type).length == 0)
				res= RuleItemTypeName[type] + " " + RuleOperator[operator] + " " + param;
			else 
				res= RuleItemTypeName[type] + " " + RuleOperator[operator] + " " + possibility(meta_type,type)[(int)param];
		}
		else if( meta_type == MONSTER_RULE)
		{
			if (possibility(meta_type,type).length == 0)
				res= RuleMonsterTypeName[type] + " " + RuleOperator[operator] + " " + param;
			else 
				res= RuleMonsterTypeName[type] + " " + RuleOperator[operator] + " " + possibility(meta_type,type)[(int)param];
		}
		else if( meta_type == PLAYER_RULE)
		{
			if (possibility(meta_type,type).length == 0)
				res= RulePlayerTypeName[type] + " " + RuleOperator[operator] + " " + param;
			else 
				res= RulePlayerTypeName[type] + " " + RuleOperator[operator] + " " + possibility(meta_type,type)[(int)param];
		}
		else if( meta_type == COMPOSED_RULE || meta_type == NEGATION_RULE )
		{
			if (type==0 || type==1)
			{
			res= "(";
			for(ObjectRule trule : ruleList) res+= trule.desc() + " " + Local.RULE_LOGIC[type];
		    res += ")";
			}
			if (type == 2 && ruleA!=null)
				res= String.format(Local.RULE_NEGATION,ruleA.desc());
		}
		return res;
	}

	public boolean Check(double a)
	{
	switch (operator) //  =, < , > , !=
		{
    	case 0: return Math.abs(a-param) < 0.0001;
    	case 1: return a < param;
    	case 2: return a > param;
    	case 3: return Math.abs(a-param) > 0.0001;
    	}
	return false;
	}
	
	public boolean CheckTag(boolean[] t)
	{
	if(operator==0) // operator =
		return (t[(int)param] == true) ;
	else if(operator==3)
		return (t[(int)param] == false) ;
	return false;
	}
	
   public boolean IsTrue(Player p, Item i, Monster m)
    {
	if (meta_type == ITEM_RULE)
	{
	if(i==null) return false;
	switch (type)
		{
		case 0: return Check(i.rare);
		case 1: return Check(i.material.type);
		case 2: return Check(i.pos);
		case 3: return Check(i.quality);
		case 4: return Check(i.poids);
		case 5: return Check(i.prix());
		case 6: return Check(i.effectiveIlvl());
		case 7: return Check(i.ilvl*i.material.coeffEfficacite*i.base_power);
		case 8: return Check(i.elvl*i.material.coeffPuissance);
		case 9: return Check(i.ilvl);
		case 10: return Check(i.elvl);
		case 11: return Check(i.material.coeffPoids);
		case 12: return Check(i.material.coeffPrix);
		case 13: return Check(i.material.coeffEfficacite);
		case 14: return Check(i.material.coeffPuissance);
		case 15: return !(i.baseItem==null) && Check(StaticItem.BaseItemNamesList.indexOf(i.baseItem.name));
		case 16: return Check(StaticItem.MaterialNamesList.indexOf(i.material.name));
		case 17: return (i.rare==6) && Check(StaticItem.OrbsNamesList.indexOf(i.material.name));
		case 18: return Check((i.equiped) ? 1 : 0); // convert to int
		case 19: return Check((i.discount) ? 1 : 0); // convert to int
		case 20: return Check(i.nb_ench());
		}
	}
	else if (meta_type == MONSTER_RULE)
	{
	if(m==null) return false;
	switch (type)
		{
		case 0: return Check(m.level);
		case 1: return Check(Monster.MonsterNamesList.indexOf(m.nom_base));
		case 2: return CheckTag(m.tags);
		case 3: return Check(m.att_per_sec());
		case 4: return Check(m.dmg_base());
		case 5: return Check(100.0*m.crit_proba());
		case 6: return Check(m.multi_crit());
		case 7: return Check(m.dmpa());
		case 8: return Check(m.att_per_sec()*m.dmpa());
		case 9: return Check(m.ed_versus_tag(2));
		case 10: return Check(100.0-(100.0*m.reduc()));
		case 11: return Check(m.absorption());
		case 12: return Check(m.vie());
		case 13: return Check(m.vie_max());
		case 14: return Check(m.vie_max()/m.reduc());
		case 15: return Check(m.epines());
		case 16: return Check(100*m.represailles());
		}
	}
	else if (meta_type == PLAYER_RULE)
	{
	if(p==null) return false;
	switch (type)
	    {
		case 0: return Check(p.level);
		case 1: return Check(p.charge);
		case 2: return Check(p.charge_max()-p.charge);
		case 3: return Check(p.money);		
		case 4: return Check(p.att_per_sec());
		case 5: return Check(p.dmg_base());
		case 6: return Check(100.0*p.crit_proba());
		case 7: return Check(p.multi_crit());
		case 8: return Check(p.dmpa());
		case 9: return Check(p.att_per_sec()*p.dmpa());
		case 10: return Check(p.ed_versus_tag(2));
		case 11: return Check(100.0-(100.0*p.reduc()));
		case 12: return Check(p.absorption());
		case 13: return Check(p.vie());
		case 14: return Check(p.vie_max());
		case 15: return Check(p.vie_max()/p.reduc());
		case 16: return Check(p.epines());
		case 17: return Check(100*p.represailles());
		case 18: return Check(p.points_totaux());
		case 19: return Check(p.points_a_distribuer());
	    }
	}
	else if (meta_type == COMPOSED_RULE)
		switch (type) // et, ou
		{
	  	case 0: // and
			for(ObjectRule trule : ruleList)
				{if (!trule.IsTrue(p,i,m)) return false;}
			return true;
	  	case 1: // or
			for(ObjectRule trule : ruleList)
				{if (trule.IsTrue(p,i,m)) return true;}
			return false;
		}
	else if (meta_type == NEGATION_RULE)
		return !ruleA.IsTrue(p,i,m);

	return false;
	}
}
