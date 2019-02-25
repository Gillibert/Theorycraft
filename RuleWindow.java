import javax.swing.table.AbstractTableModel;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.*;

public class RuleWindow extends javax.swing.JDialog  {
	private Player Joueur;
	private javax.swing.JPanel ivjJFrameContentPane = null;
	
	private JButton addRtl;
	private JButton delRtl;
	
	private JLabel prompt;
	private JLabel warning;
	private JList liste_r;
	private JButton add;
	private JButton edit;
	private JButton del;
	private JButton export;
	private JButton importer;
	private JScrollPane scroll;
	private JCheckBox is_sell_rule;
	private JCheckBox is_buy_rule;	
	private JCheckBox is_inventory_rule;
	private JCheckBox is_pickup_rule;
	private JCheckBox is_avoid_rule;
	private JCheckBox is_flee_rule;
	private JCheckBox is_shopping_rule;
	private JCheckBox is_forge_rule;
	private JCheckBox is_dist_rule;
	private JFileChooser fc;

	private DefaultComboBoxModel type;
	private DefaultListModel rule_list;

	private DefaultComboBoxModel symbol_list;
	private DefaultComboBoxModel option1_list;
	private DefaultComboBoxModel option2_list;

	private DefaultComboBoxModel rule_combo1;
	private DefaultComboBoxModel rule_combo2;
	private DefaultComboBoxModel logic_combo;

	private JComboBox rtype;
	private JComboBox logic;
	private JComboBox rl1;

	private JComboBox symbol;
	private JComboBox opt1;
	private JComboBox opt2;
	private JTextField opt2txt;
	private JTextField rule_name;

	private JScrollPane scroll2;
	private DefaultListModel rule_list2;
	private JList liste2;

    private JTable table;
	
	private ObjectRule current_rule;
	private ObjectRule to_update;

	public boolean listener_off;
	
    public RuleWindow(Player J) {
	super();
	Joueur = J;
	initialize();
    }

	public void SetPlayer(Player J)
	{
		Joueur = J;
		refreshRulesList();
	}

	
   public void montre()
    {
	refreshRulesList();
	int defaultRule = rule_list.indexOf(Local.ALL);
	if (defaultRule == -1) defaultRule = 0;
	liste_r.setSelectedIndex(defaultRule);
	refreshRule();
	this.setVisible(true);
    }


	// To call only when the rules list is changes
	public void refreshRulesList()
	{
		listener_off = true;
		int idx_tmp = liste_r.getSelectedIndex();
		rule_list.clear();
		rule_combo1.removeAllElements();
	    rule_combo2.removeAllElements();
		for(ObjectRule the_rule : Joueur.rules)
		    {
		    rule_list.addElement(the_rule.name);
			rule_combo1.addElement(the_rule.name);
			rule_combo2.addElement(the_rule.name);
		    }
		int x = rule_list.size()-1;
		if(x > -1) liste_r.setSelectedIndex(Math.min(idx_tmp,x));
		listener_off = false;
	}

    public void refreshAll()
    {
	refreshRulesList();
	refreshRule();
    }

	// To call when metatype is changed
	public void refreshOpt1()
	{
	  	option1_list.removeAllElements();
		int count = 0;
	  	if(current_rule.meta_type==ObjectRule.ITEM_RULE)
			for(int i=0; i<ObjectRule.RuleItemTypeName.length; i++)
				{option1_list.addElement(ObjectRule.RuleItemTypeName[i]); count++;}
		else if(current_rule.meta_type==ObjectRule.MONSTER_RULE)
			for(int i=0; i<ObjectRule.RuleMonsterTypeName.length; i++)
				{option1_list.addElement(ObjectRule.RuleMonsterTypeName[i]); count++;}
		else if(current_rule.meta_type==ObjectRule.PLAYER_RULE)
			for(int i=0; i<ObjectRule.RulePlayerTypeName.length; i++)
				{option1_list.addElement(ObjectRule.RulePlayerTypeName[i]); count++;}
				
		opt1.setVisible(true);	
		current_rule.type = Math.min((int)(current_rule.type),count-1);
		opt1.setSelectedIndex((int)(current_rule.type));
	}


	// To call when opt1 or opt2 is changed
	public void refreshOpt2()
	{
		int si = opt1.getSelectedIndex();

	  	String[] possibility = ObjectRule.possibility(current_rule.meta_type,si);
	  	if(possibility.length != 0)
	  	  {
	  	  opt2txt.setVisible(false);
	  	  opt2.setVisible(true);
	  	  option2_list.removeAllElements();
	  	  for(int i=0; i< possibility.length; i++)
	  	  	option2_list.addElement(possibility[i]);
		  if(current_rule.param >= 0 && current_rule.param < possibility.length)
	  	  	opt2.setSelectedIndex((int)(current_rule.param));
		  else
			opt2.setSelectedIndex(0);
	  	  }
	  	else
	  	  {
	  	  opt2.setVisible(false);
	  	  opt2txt.setVisible(true);
	  	  opt2txt.setText(String.valueOf(current_rule.param));
	  	  }
	}
	
	public void displayRule()
	{
		listener_off = true;
		rule_name.setText(current_rule.name);
		int metaType = current_rule.metaType();
		rtype.setSelectedIndex(metaType);

		if(metaType == ObjectRule.ITEM_RULE || metaType == ObjectRule.MONSTER_RULE || metaType == ObjectRule.PLAYER_RULE)
		{
			symbol.setSelectedIndex((int)(current_rule.operator));
			symbol.setVisible(true);
			refreshOpt1();
			refreshOpt2();
			rl1.setVisible(false);
			logic.setVisible(false);
			scroll2.setVisible(false);
			addRtl.setVisible(false);
			delRtl.setVisible(false);
		}
		
		if(metaType == ObjectRule.NEGATION_RULE)
		{
			addRtl.setVisible(false);
			delRtl.setVisible(false);
		}
		
		boolean ir = (metaType == ObjectRule.ITEM_RULE);
		boolean mop = (metaType == ObjectRule.MONSTER_RULE || metaType == ObjectRule.PLAYER_RULE);
		boolean comp = (metaType == ObjectRule.COMPOSED_RULE || metaType == ObjectRule.NEGATION_RULE);
		
		is_avoid_rule.setVisible(mop || comp);
		is_flee_rule.setVisible(mop || comp);
		is_shopping_rule.setVisible(mop || comp);
		is_forge_rule.setVisible(mop || comp);
		is_dist_rule.setVisible(mop || comp);
		is_sell_rule.setVisible(ir || comp);
	    is_buy_rule.setVisible(ir || comp);
        is_inventory_rule.setVisible(ir || comp);
        is_pickup_rule.setVisible(ir || comp);
		
		if(metaType == ObjectRule.COMPOSED_RULE || metaType == ObjectRule.CRAFT_RULE)
		{
		rule_list2.clear();
		if(current_rule.ruleList != null)
			for (int i=0; i< current_rule.ruleList.size() ; i++)
				{rule_list2.addElement(current_rule.ruleList.get(i).name);}
		}
		
		if(metaType == ObjectRule.COMPOSED_RULE || metaType == ObjectRule.NEGATION_RULE || metaType == ObjectRule.CRAFT_RULE)
		{
			opt2txt.setVisible(false);
			opt1.setVisible(false);
			opt2.setVisible(false);
			symbol.setVisible(false);	
			rl1.setVisible(true);
			
			int rl1_idx = Joueur.rules.indexOf(current_rule.ruleA);
			rl1.setSelectedIndex(rl1_idx);
		}
		if(metaType == ObjectRule.COMPOSED_RULE || metaType == ObjectRule.CRAFT_RULE)
		{
			scroll2.setVisible(true);
			addRtl.setVisible(true);
			delRtl.setVisible(true);
			
			logic.setSelectedIndex(Math.min(current_rule.type,logic_combo.getSize()-1));
			logic.setVisible(metaType == ObjectRule.COMPOSED_RULE);
		}
		if(metaType == ObjectRule.NEGATION_RULE)
		{
			scroll2.setVisible(false);
			logic.setVisible(false);
		}
	
	rule_name.setEnabled(!current_rule.system_rule);
	logic.setEnabled(!current_rule.system_rule);
	symbol.setEnabled(!current_rule.system_rule);
	rl1.setEnabled(!current_rule.system_rule);
	opt1.setEnabled(!current_rule.system_rule);
	opt2.setEnabled(!current_rule.system_rule);
	opt2txt.setEnabled(!current_rule.system_rule);
	rtype.setEnabled(!current_rule.system_rule);
	
	displayCheck();
	refreshConsistency();
	listener_off = false;
	}
	
	public boolean nameTaken(ObjectRule cur)
	{
		for(ObjectRule r : Joueur.rules)
		{
			if (r.name.equals(cur.name)) return true;
		}
		return (cur.name==null || cur.name.length()==0);
	}
	
	public boolean nameOk(ObjectRule cur)
	{
		for(ObjectRule r : Joueur.rules)
		{
			if (r.name.equals(cur.name) && r != to_update) return false;
		}
		return !(cur.name==null || cur.name.length()==0);
	}
	
	public void refreshConsistency()
	{
		boolean isOk = false;
		if(current_rule!= null && current_rule.param >= 0 &&  
			current_rule.name!= null && current_rule.name.length() > 0)
		{
			ObjectRule backup = new ObjectRule(to_update);
			to_update.update(current_rule);
			isOk = !current_rule.badConsistency();
			to_update.update(backup);
		}

		if(current_rule==null)
			warning.setText("");
		else if(current_rule.param < 0)
			warning.setText(Local.ERROR_INVALID_NUMBER);
		else if(current_rule.name==null || current_rule.name.length()==0)
			warning.setText(Local.ERROR_EMPTY_NAME);
		else if(!isOk)
			warning.setText(Local.ERROR_SELF_REFERENCE);
		else
			warning.setText("");

		edit.setEnabled(liste_r.getSelectedIndex()>=0 && isOk && nameOk(current_rule));
		//add.setEnabled(!nameTaken(current_rule));
		del.setEnabled(liste_r.getSelectedIndex()>=0 && !current_rule.system_rule);
	}
	
	public void updateName()
	{
	current_rule.name = rule_name.getText();
	refreshConsistency();
	}
	
	public void updateTxtInput()
	{
	if (opt2txt.isVisible())
		{
		try
			{current_rule.param = Double.parseDouble(opt2txt.getText());}
		catch(Exception e)
			{current_rule.param = -1;}
		refreshConsistency();
		}
	}
	
	
	// To call when the selected rule is changed
    public void refreshRule()
    {
		if(liste_r.getSelectedIndex()>=0)
			{
			to_update = Joueur.rules.get(liste_r.getSelectedIndex());
			current_rule = new ObjectRule(to_update);
			}
		displayRule();
    }

	public void updateCheck()
	{
		current_rule.buy_rule = is_buy_rule.isSelected();
		current_rule.sell_rule = is_sell_rule.isSelected();
		current_rule.filter_rule = is_inventory_rule.isSelected();
		current_rule.pickup_rule = is_pickup_rule.isSelected();	
		current_rule.flee_rule = is_flee_rule.isSelected();
		current_rule.avoid_rule = is_avoid_rule.isSelected();
		current_rule.shopping_rule = is_shopping_rule.isSelected();
		current_rule.forge_rule = is_forge_rule.isSelected();
		current_rule.dist_rule = is_dist_rule.isSelected();
	}
	
	public void displayCheck()
	{
		is_buy_rule.setSelected(current_rule.buy_rule);
		is_sell_rule.setSelected(current_rule.sell_rule);
		is_inventory_rule.setSelected(current_rule.filter_rule);
		is_pickup_rule.setSelected(current_rule.pickup_rule);
		is_flee_rule.setSelected(current_rule.flee_rule);
		is_avoid_rule.setSelected(current_rule.avoid_rule);
		is_shopping_rule.setSelected(current_rule.shopping_rule);
		is_forge_rule.setSelected(current_rule.forge_rule);
		is_dist_rule.setSelected(current_rule.dist_rule);
		}
	
	private void update_current_rule() 
	{
		current_rule.name = rule_name.getText();
		updateCheck();
		int metaType = rtype.getSelectedIndex();
		current_rule.meta_type = metaType;
		if(metaType == 0 || metaType == 1 || metaType == 2)
			{
				current_rule.type = Math.max(0,opt1.getSelectedIndex());
				current_rule.operator = Math.max(0,symbol.getSelectedIndex());
				String[] possibility = ObjectRule.possibility(current_rule.meta_type,current_rule.type);
				if (possibility.length != 0)
					{
					if(opt2.getSelectedIndex()>=0 && opt2.getSelectedIndex() < possibility.length)
						current_rule.param = opt2.getSelectedIndex();
					else
						current_rule.param = 0;
					}
				else
					{
					try
					{current_rule.param = Float.parseFloat(opt2txt.getText());}
					catch(Exception e)
					{current_rule.param = 0;}	
					}			
			}
		if(metaType == ObjectRule.CRAFT_RULE || metaType == ObjectRule.COMPOSED_RULE || metaType == ObjectRule.NEGATION_RULE)
			{
				int idx1 = rl1.getSelectedIndex();
				if(idx1 >= 0 && idx1 < Joueur.rules.size())
					current_rule.ruleA = Joueur.rules.get(idx1);
			}
		if(metaType == ObjectRule.COMPOSED_RULE)
			{
				current_rule.type = Math.max(0,logic.getSelectedIndex());
			}
		if(metaType == ObjectRule.NEGATION_RULE)
			{
				current_rule.type = Math.max(0,logic.getSelectedIndex());
			}
		displayRule();
	}
	
	private void removeRuleFromList()
	{
		int idx = liste2.getSelectedIndex();
		if (current_rule.ruleList == null || idx < 0) return;
		current_rule.ruleList.remove(idx);
		displayRule();
		liste2.setSelectedIndex(Math.min(idx,current_rule.ruleList.size()-1));
	}
	
	private void addRuleToList()
	{
		if (current_rule.ruleList == null) current_rule.ruleList = new ArrayList<ObjectRule>();
		int idx1 = rl1.getSelectedIndex();
		if(idx1 >= 0 && idx1 < Joueur.rules.size())
			current_rule.ruleList.add(Joueur.rules.get(idx1));
		displayRule();
	}
	
    private javax.swing.JPanel getJFrameContentPane() {
	if (ivjJFrameContentPane == null) {
		to_update = new ObjectRule();
		listener_off = false;
		current_rule = null;
	    prompt = new JLabel();
	    prompt.setText(Local.RULES);
	    prompt.setBounds(new Rectangle(10, 3, 600, 23));
	    prompt.setFont(new Font(Local.FONT_DIALOG, Font.BOLD, 14));
		
	    rule_list = new DefaultListModel();
	    rule_list2 = new DefaultListModel();
		
	    option1_list = new DefaultComboBoxModel();
	    option2_list = new DefaultComboBoxModel();	
	    symbol_list = new DefaultComboBoxModel();
			
		for(int i=0; i<ObjectRule.RuleOperator.length; i++)
			symbol_list.addElement(ObjectRule.RuleOperator[i]);
			
	    rule_combo1 = new DefaultComboBoxModel();
	    rule_combo2 = new DefaultComboBoxModel();
	    logic_combo = new DefaultComboBoxModel();
	    type = new DefaultComboBoxModel();

		type.addElement(Local.RULE_ON_AN_OBJECT);
		type.addElement(Local.RULE_ON_A_MONSTER);
		type.addElement(Local.RULE_ON_THE_PLAYER);
		type.addElement(Local.COMPOUND_RULE);
		type.addElement(Local.NEGATION);
		type.addElement(Local.CRAFT_RULE);
		
	    logic_combo.addElement(Local.AND);
	    logic_combo.addElement(Local.OR);

	    rule_name = new JTextField("");
	    rule_name.setBounds(new Rectangle(150, 28, 230, 25));
	
	    rtype = new JComboBox(type);
	    rtype.setBounds(new Rectangle(150, 28+30, 230, 25));

	    rl1 = new JComboBox(rule_combo1);
	    rl1.setBounds(new Rectangle(150, 28+30+30, 230, 25));

	    logic = new JComboBox(logic_combo);
	    logic.setBounds(new Rectangle(385, 28+30, 70, 25));
		
		addRtl = new  JButton(Local.ADD);
	    addRtl.setBounds(new Rectangle(385, 28+30+30, 110, 25));
		addRtl.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {addRuleToList();}
		});
		
		delRtl = new  JButton(Local.REMOVE);
	    delRtl.setBounds(new Rectangle(385+110+5, 28+30+30, 110, 25));
		delRtl.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {removeRuleFromList();}
		});
		
		is_sell_rule = new JCheckBox(Local.SELL_RULE);
		is_sell_rule.setBounds(new Rectangle(150, 270, 190, 25));
			
		is_buy_rule = new JCheckBox(Local.PURCHASE_RULE);
		is_buy_rule.setBounds(new Rectangle(150, 270+24, 190, 25));
		
		is_pickup_rule = new JCheckBox(Local.PICKUP_RULE);
		is_pickup_rule.setBounds(new Rectangle(150, 270+2*24, 190, 25));
		
		is_inventory_rule = new JCheckBox(Local.INVENTORY_FILTER);
		is_inventory_rule.setBounds(new Rectangle(150, 270+3*24, 190, 25));
		
		is_flee_rule = new JCheckBox(Local.FLEE_RULE);
		is_flee_rule.setBounds(new Rectangle(150+190+10, 270, 220, 25));

		is_avoid_rule = new JCheckBox(Local.NON_INVOLVEMENT_RULE);
		is_avoid_rule.setBounds(new Rectangle(150+190+10, 270+24, 220, 25));

		is_shopping_rule = new JCheckBox(Local.MERCHANT_SEARCH);
		is_shopping_rule.setBounds(new Rectangle(150+190+10, 270+2*24, 220, 25));
		
		is_forge_rule = new JCheckBox(Local.FORGE_SEARCH);
		is_forge_rule.setBounds(new Rectangle(150+190+10, 270+3*24, 220, 25));
		
		is_dist_rule = new JCheckBox(Local.AUTO_DISTRIBUTION);
		is_dist_rule.setBounds(new Rectangle(150+190+10, 270+4*24, 120, 25));
		
	    opt1 = new JComboBox(option1_list);
	    opt1.setBounds(new Rectangle(150, 88, 230, 25));

	    opt2txt = new JTextField("");
	    opt2txt.setBounds(new Rectangle(460, 88, 150, 25));
	
	    symbol = new JComboBox(symbol_list);
	    symbol.setBounds(new Rectangle(385, 88, 70, 25));

	    opt2 = new JComboBox(option2_list);
	    opt2.setBounds(new Rectangle(460, 88, 150, 25));
	
	    java.awt.event.ItemListener sl_listener = new java.awt.event.ItemListener() {
		    public void itemStateChanged(java.awt.event.ItemEvent e) 
				{if(!listener_off) update_current_rule();}};
		
		javax.swing.event.CaretListener name_listener = new javax.swing.event.CaretListener() {
		    public void caretUpdate(javax.swing.event.CaretEvent e) 
				{if(!listener_off) updateName();}};
		
		javax.swing.event.CaretListener txt2_listener = new javax.swing.event.CaretListener() {
		    public void caretUpdate(javax.swing.event.CaretEvent e) 
				{if(!listener_off) updateTxtInput();}};

		javax.swing.event.ChangeListener check_listener = new javax.swing.event.ChangeListener() {
      		public void stateChanged(javax.swing.event.ChangeEvent e) 
				{if(!listener_off) updateCheck();}};
		
	    rtype.addItemListener(sl_listener);
	    opt1.addItemListener(sl_listener);
		rule_name.addCaretListener(name_listener);
		opt2txt.addCaretListener(txt2_listener);
		opt2.addItemListener(sl_listener);
		rl1.addItemListener(sl_listener);
		logic.addItemListener(sl_listener);
		symbol.addItemListener(sl_listener);
		is_buy_rule.addChangeListener(check_listener);
		is_sell_rule.addChangeListener(check_listener);
		is_inventory_rule.addChangeListener(check_listener);
		is_pickup_rule.addChangeListener(check_listener);
		is_avoid_rule.addChangeListener(check_listener);
		is_flee_rule.addChangeListener(check_listener);
		is_shopping_rule.addChangeListener(check_listener);
		is_forge_rule.addChangeListener(check_listener);
		is_dist_rule.addChangeListener(check_listener);
		
	    javax.swing.event.ListSelectionListener list_ref = new javax.swing.event.ListSelectionListener() {
		    public void valueChanged(javax.swing.event.ListSelectionEvent e) {if(!listener_off && !e.getValueIsAdjusting()) refreshRule();}};


	    liste_r = new JList(rule_list);
	    liste_r.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	    liste_r.addListSelectionListener(list_ref);

	    scroll = new JScrollPane(liste_r);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setBounds(new Rectangle(5, 28, 140, 310-24));
		
	    scroll2 = new JScrollPane(table);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll2.setBounds(new Rectangle(150, 88+30, 465, 150));
		
		
		liste2 = new JList(rule_list2);
	    liste2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	    //liste2.addListSelectionListener(list_ref);

		scroll2 = new JScrollPane(liste2);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll2.setBounds(new Rectangle(150, 88+30, 465, 150));
		

	    warning = new JLabel();
	    warning.setText("");
	    warning.setFont(new Font(Local.FONT_DIALOG, Font.BOLD, 14));
		warning.setForeground(Color.red);
	    warning.setBounds(new Rectangle(230+150+5, 28, 200, 21));
	
	    edit = new JButton();
	    edit.setBounds(new Rectangle(500, 365+24, 110, 21));
	    edit.setText(Local.SAVE);
	    edit.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {editRule();
		    }
		});
	    edit.setMnemonic('s');
	
	    add = new JButton();
	    add.setBounds(new Rectangle(5, 317, 140, 21));
	    add.setText(Local.NEW_RULE);
	    add.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {addRule();
		    }
		});
	    add.setMnemonic('n');

	    del = new JButton();
	    del.setBounds(new Rectangle(5, 341, 140, 21));
	    del.setText(Local.REMOVE);
	    del.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {deleteRule();
		    }
		});
	    del.setMnemonic('r');
	
	    export = new JButton();
	    export.setBounds(new Rectangle(5, 365, 140, 21));
	    export.setText(Local.EXPORT);
	    export.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {exportRules();
		    }
		});
	    export.setMnemonic('x');
	
	    importer = new JButton();
	    importer.setBounds(new Rectangle(5, 365+24, 140, 21));
	    importer.setText(Local.IMPORT);
	    importer.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {importRules();
		    }
		});
	    importer.setMnemonic('i');
	

	    prompt.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 16));
	    liste_r.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
	    liste2.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
	    rl1.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
		logic.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
	    opt1.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
		opt2txt.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
	    opt2.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
		symbol.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
		
	
	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

	    ivjJFrameContentPane.add(prompt);
	    ivjJFrameContentPane.add(scroll);
	    ivjJFrameContentPane.add(add);
	    ivjJFrameContentPane.add(edit);
	    ivjJFrameContentPane.add(del);
		ivjJFrameContentPane.add(export);
		ivjJFrameContentPane.add(importer);
	    ivjJFrameContentPane.add(rl1);
	    ivjJFrameContentPane.add(logic);
	    ivjJFrameContentPane.add(addRtl);
	    ivjJFrameContentPane.add(delRtl);		
	    ivjJFrameContentPane.add(rtype);
	    ivjJFrameContentPane.add(warning);
		ivjJFrameContentPane.add(opt2txt);
		ivjJFrameContentPane.add(rule_name);
		ivjJFrameContentPane.add(opt1);
		ivjJFrameContentPane.add(opt2);
		ivjJFrameContentPane.add(symbol);
	    ivjJFrameContentPane.add(scroll2);
		
		ivjJFrameContentPane.add(is_sell_rule);
		ivjJFrameContentPane.add(is_buy_rule);
		ivjJFrameContentPane.add(is_inventory_rule);	
		ivjJFrameContentPane.add(is_pickup_rule);
		ivjJFrameContentPane.add(is_avoid_rule);	
		ivjJFrameContentPane.add(is_flee_rule);
		ivjJFrameContentPane.add(is_shopping_rule);	
		ivjJFrameContentPane.add(is_forge_rule);
		ivjJFrameContentPane.add(is_dist_rule);	
	}
	return ivjJFrameContentPane;
    }

    private void editRule()
    {
	to_update.update(current_rule);
	refreshAll();
    }

    private void addRule()
    {
	ObjectRule tmp = new ObjectRule(0,0,0,ObjectRule.ITEM_RULE);
	int num = (int)(999999*Math.random());
	tmp.name = Local.RULE +  String.valueOf( num );
	current_rule = tmp;
	Joueur.rules.add(tmp);
	refreshRulesList();
	liste_r.setSelectedIndex(rule_list.indexOf(current_rule.name));
	refreshRule();
    }

    private void exportRules()
    {
	int returnVal = fc.showSaveDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
		try{
		    //use buffering
		    OutputStream file = new FileOutputStream( fc.getSelectedFile() );
		    OutputStream buffer = new BufferedOutputStream( file );
		    ObjectOutput output = new ObjectOutputStream( buffer );
		    output.writeObject(Joueur.rules);
		    output.close();
		} 
		catch(Exception ex){
		    System.out.println(ex);
		}
		}
    }

	private void importRules()
	{
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try{
				//use buffering
				InputStream file = new FileInputStream(fc.getSelectedFile());
				InputStream buffer = new BufferedInputStream( file );
				ObjectInput input = new ObjectInputStream ( buffer );
				Joueur.rules = (ArrayList<ObjectRule>)input.readObject();
				input.close();
				refreshAll();
			} 
			catch(Exception ex){
				System.out.println(ex);
			}
		}
	}

    private void deleteRule()
    {
	Joueur.rules.remove(liste_r.getSelectedIndex());
	refreshAll();
    }

    private void initialize() {
	this.setTitle(Local.PROGRAMMING);
	this.setLocation(new Point(15, 15));
	this.setSize(new Dimension(625, 442));
	this.setResizable(false);
	this.setModal(true);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setContentPane(getJFrameContentPane());
	
	fc = new JFileChooser();
	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
	fc.setCurrentDirectory(new File("./heroes"));
	
	getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
    
	getRootPane().getActionMap().put("Cancel", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {setVisible(false);}});
    }

} 
