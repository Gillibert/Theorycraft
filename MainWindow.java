import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.ListSelectionModel;

import java.awt.event.*;

public class MainWindow extends javax.swing.JFrame {
    static private final long serialVersionUID = 1338162012;
    public Player Joueur;
    public World Monde;
    public LevelUp DistWindow;
    public Courbes CurvWindow;
    public InventoryWindow InvWindow;
    public WorldMap WorldMapWindow;
	public UniversWindow UnivWindow;
    public RuleWindow RuleWin;
    private String[] lines;
    private static int max_lines=50;
    private int index_line;

    private boolean infight=false;
    private boolean stopfight=false;

    private javax.swing.JPanel ivjJFrameContentPane;
    private JProgressBar xp_bar;
    private JProgressBar hp_bar1;
    private JProgressBar hp_bar2;
    private JButton distrib;
    private JButton voyager;
    private JButton combat_cmd;
    private JTextArea stats;
    private JTextArea stats3;
    private JButton courbes;
    private JButton shop_cmd;
    private JButton craft_cmd;
    private JButton inventaire_cmd;
    private JButton prog_cmd;
    private JButton finir;
	private JButton univers_cmd;
    private JScrollPane scroll;
    public JTextArea log;

    private JButton plus;
    private JButton moins;
    private JButton toggle;
    private JTable conditionTable;
    private JScrollPane tableScroll;
    private String[][] rowData;

    public MainWindow(World W,Player J) {
	super();
	Joueur = J;
	Monde = W;
	lines = new String[max_lines];
	index_line=0;

	DistWindow = new LevelUp(Joueur);
	CurvWindow = new Courbes(Joueur);
	InvWindow = new InventoryWindow(Joueur);
	WorldMapWindow = new WorldMap(Monde,Joueur);
	RuleWin = new RuleWindow(Joueur);
	UnivWindow = new UniversWindow(Joueur, Monde);

	this.setContentPane(getJFrameContentPane());
	this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	this.setLocation(new Point(0, 0));
	this.setSize(new Dimension(795, 680));		
	this.setResizable(false);

	this.setTitle("Theorycraft - " + Joueur.name + " - " + Joueur.defi.name);
	this.refresh();
	this.refreshTable();
    }

    public void refreshInFight() // Refresh hp bars only
    {
	if(Joueur.mob != null) 
	    {
		hp_bar2.setString(String.format("Vie : %.2f/%.2f",Joueur.mob.vie,Joueur.mob.vie_max()));
		hp_bar2.setValue((int)(100.0*Joueur.mob.vie/Joueur.mob.vie_max()));
	    }
	else
	    {
		hp_bar2.setValue(0);
		hp_bar2.setString("Vie : 0/0");
	    }
	hp_bar1.setString(String.format("Vie : %.2f/%.2f",Joueur.vie,Joueur.vie_max()));
	hp_bar1.setValue((int)(100.0*Joueur.vie/Joueur.vie_max()));	
    }

    public void refreshTable() // Refresh the condition table
    {
	for(int i=0; i< Joueur.conditionValues.length ; i++)
	    {
		rowData[i][2]=String.valueOf(Joueur.conditionValues[i]);
		rowData[i][3]=String.valueOf(Joueur.conditionToggle[i]);
	    }

	conditionTable.repaint();
	moins.setEnabled(Joueur.conditionValues[conditionTable.getSelectedRow()]>0);
	if(Joueur.conditionToggle[conditionTable.getSelectedRow()])
	    toggle.setText("Désactiver");
	else
	    toggle.setText("Activer");
    }

    public void refresh() // Refresh all except the condition table
    {
	double progress;
	xp_bar.setString("Niveau suivant : "+(Joueur.xp_pt-Joueur.xp_level(Joueur.level))+ "/" + (Joueur.next_level()-Joueur.xp_level(Joueur.level)));
	progress=1.0*(Joueur.xp_pt-Joueur.xp_level(Joueur.level))/(Joueur.next_level()-Joueur.xp_level(Joueur.level));
	xp_bar.setValue((int)((100.0*progress)));

	stats.setText(Joueur.short_stats());
	if(Joueur.mob != null) 
	    {
		stats3.setText(Joueur.mob.short_stats());
		if(Joueur.mob.tags[5])
			stats3.setForeground(Color.blue);	
		else
		    stats3.setForeground(Color.black);
	    }

	if(infight)
	    combat_cmd.setText("Arrêt combat");
	else
	    combat_cmd.setText("Combat");

	voyager.setEnabled(!infight);
	craft_cmd.setEnabled(!infight);
	inventaire_cmd.setEnabled(!infight);
	shop_cmd.setEnabled(!infight);
	finir.setEnabled(!infight);

	//   int height = (int)log.getHeight();
	//   scroll.getVerticalScrollBar().setValue(height);
	refreshInFight();
    }
	

    private javax.swing.JPanel getJFrameContentPane() {

	this.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.out.println("Sauvegarde");
		    Joueur.universe.save();
		}});
	  	
	xp_bar = new JProgressBar();
	xp_bar.setStringPainted(true);
	xp_bar.setBounds(new Rectangle(5, 265, 415, 15));

	hp_bar1 = new JProgressBar();
	hp_bar1.setStringPainted(true);
	hp_bar1.setBounds(new Rectangle(5, 245, 205, 15));

	hp_bar2 = new JProgressBar();
	hp_bar2.setStringPainted(true);
	hp_bar2.setBounds(new Rectangle(215, 245, 205, 15));

	stats = new JTextArea();
	stats.setBounds(new Rectangle(5, 5, 205, 235));
	stats.setEditable(false);
	stats.setFont(new Font("Times New Roman", Font.PLAIN, 12));

	stats3 = new JTextArea();
	stats3.setBounds(new Rectangle(215, 5, 205, 235));
	stats3.setEditable(false);
	stats3.setFont(new Font("Times New Roman", Font.PLAIN, 12));

	plus = new JButton();
	plus.setBounds(new Rectangle(425, 325, 60, 15));
	plus.setText("+");
	plus.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {plus();
		}
	    });
	plus.setMnemonic(KeyEvent.VK_ADD);

	moins = new JButton();
	moins.setBounds(new Rectangle(490, 325, 60, 15));
	moins.setText("-");
	moins.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {moins();
		}
	    });
	moins.setMnemonic(KeyEvent.VK_SUBTRACT);

	toggle = new JButton();
	toggle.setBounds(new Rectangle(555, 325, 100, 15));
	toggle.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {toggle();
		}
	    });

	voyager = new JButton();
	voyager.setBounds(new Rectangle(5, 285, 135, 15));
	voyager.setText("Voyager");
	voyager.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    WorldMapWindow.voyager();
		}
	    });
	voyager.setMnemonic('v');

	combat_cmd = new JButton();
	combat_cmd.setBounds(new Rectangle(5, 285+20, 135, 15));
	combat_cmd.setText("Combat");
	combat_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    fight();
		}
	    });
	combat_cmd.setMnemonic('c');

	inventaire_cmd = new JButton();
	inventaire_cmd.setBounds(new Rectangle(145, 285, 135, 15));
	inventaire_cmd.setText("Inventaire");
	inventaire_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    InvWindow.montre_inv();
		    refresh();
		}
	    });
	inventaire_cmd.setMnemonic('i');
	
	prog_cmd = new JButton();
	prog_cmd.setBounds(new Rectangle(5, 285+40, 135, 15));
	prog_cmd.setText("Programmation");
	prog_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    RuleWin.montre();
		}
	    });
	prog_cmd.setMnemonic('p');


	shop_cmd = new JButton();
	shop_cmd.setBounds(new Rectangle(145, 285+20, 135, 15));
	shop_cmd.setText("Shopping");
	shop_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    venteAuto();
		    achatAuto();
		    InvWindow.montre_shop();
		    refresh();	
		}
	    });
	shop_cmd.setMnemonic('s');

	craft_cmd = new JButton();
	craft_cmd.setBounds(new Rectangle(145, 285+40, 135, 15));
	craft_cmd.setText("Crafting");
	craft_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    Joueur.get_forge();
		    InvWindow.montre_craft();
		    refresh();	
		}
	    });
	craft_cmd.setMnemonic('r');

	distrib = new JButton();
	distrib.setBounds(new Rectangle(285, 285, 135, 15));
	distrib.setText("Compétences");
	distrib.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    DistWindow.dist(); refresh();
		}
	    });
	distrib.setMnemonic('t');

	courbes = new JButton();
	courbes.setBounds(new Rectangle(285, 285+20, 135, 15));
	courbes.setText("Courbes");
	courbes.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    CurvWindow.montre();
		}
	    });
	courbes.setMnemonic('b');
	
	univers_cmd = new JButton();
	univers_cmd.setBounds(new Rectangle(285, 285+40, 135, 15));
	univers_cmd.setText("Univers");
	univers_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		   UnivWindow.montre();
		}
	});
	univers_cmd.setMnemonic('u');
	
	finir = new JButton();
	finir.setBounds(new Rectangle(705, 325, 80, 15));
	finir.setText("Finir jeu");
	finir.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    end_game();
		}
	});
	finir.setMnemonic('f');
	
	log = new JTextArea();
	log.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	log.setEditable(false);
	log.setLineWrap(true);
	log.setWrapStyleWord(true);
	log.setBackground(Color.gray);

	scroll = new JScrollPane(log);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setBounds(new Rectangle(5, 365-20, 785, 285+20));

	rowData = new String[5][4];

	rowData[0][0] = rowData[1][0] = rowData[2][0] = rowData[3][0] = rowData[4][0] = "Arrêter les combats";

	rowData[0][1] = "Défaites =";
	rowData[1][1] = "Ennemis vaincus =";		
	rowData[2][1] = "Niveaux gagnés =";
	rowData[3][1] = "Pièges léthaux =";
	rowData[4][1] = "Pièges défaits =";
	
	String[] columnNames = {"Action","Condition","Valeur","Activé"};
	AbstractTableModel mytm = new MyTableModel(rowData,columnNames);

	conditionTable = new JTable(mytm);
	//table.setEnabled(false);
	conditionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	conditionTable.setRowSelectionInterval(0,0);
	conditionTable.getColumnModel().getColumn(0).setPreferredWidth(205);
	conditionTable.getColumnModel().getColumn(1).setPreferredWidth(205);
	conditionTable.getColumnModel().getColumn(2).setPreferredWidth(60);
	conditionTable.setFont(new Font("Times Roman", Font.BOLD, 11));

	javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		public void valueChanged(javax.swing.event.ListSelectionEvent e) {refreshTable();}};
				
	conditionTable.getSelectionModel().addListSelectionListener(refresher);

	tableScroll = new JScrollPane(conditionTable);
	tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	tableScroll.setBounds(new Rectangle(425, 5, 365, 315));

	ivjJFrameContentPane = new javax.swing.JPanel();
	ivjJFrameContentPane.setLayout(null);
	ivjJFrameContentPane.add(xp_bar);
	ivjJFrameContentPane.add(hp_bar1);
	ivjJFrameContentPane.add(hp_bar2);
	ivjJFrameContentPane.add(distrib);
	ivjJFrameContentPane.add(stats);
	ivjJFrameContentPane.add(stats3);
	ivjJFrameContentPane.add(voyager);
	ivjJFrameContentPane.add(combat_cmd);
	ivjJFrameContentPane.add(courbes);
	ivjJFrameContentPane.add(shop_cmd);
	ivjJFrameContentPane.add(craft_cmd);
	ivjJFrameContentPane.add(inventaire_cmd);
	ivjJFrameContentPane.add(prog_cmd);
	ivjJFrameContentPane.add(tableScroll);
	ivjJFrameContentPane.add(plus);
	ivjJFrameContentPane.add(moins);
	ivjJFrameContentPane.add(toggle);
	ivjJFrameContentPane.add(scroll);
	ivjJFrameContentPane.add(finir);
	ivjJFrameContentPane.add(univers_cmd);
	return ivjJFrameContentPane;
    }
	
    private void plus()
    {
	Joueur.conditionValues[conditionTable.getSelectedRow()]++;
	refreshTable();
    }

    private void moins()
    {
    	Joueur.conditionValues[conditionTable.getSelectedRow()]--;
	refreshTable();
    }

    private void toggle()
    {
    	Joueur.conditionToggle[conditionTable.getSelectedRow()] = !Joueur.conditionToggle[conditionTable.getSelectedRow()];
	refreshTable();
    }

	private void end_game()
	{
		if (Joueur.jeu_fini)
		{
			Game.MW.addLog("Vous avez déjà fini le jeu !");
		}
		else if(Joueur.defi.isCond() && Joueur.defi.isTrue(Joueur))
		{
			victory();
		}
		else if(!Joueur.defi.isCond())
		{
			infight = true;
			Thread thread = new Thread() {
				public void run() {
					Joueur.mob = new Monster(Joueur.defi.boss_name, Joueur.defi.boss_level, Joueur.defi.boss_tag, Joueur.defi.boss_p_stats, Joueur.universe);
					refresh();
					
					if(!Joueur.combat(true,true)) 
						{
							Game.MW.addLog("Vous avez vaincu le boss final !");
							victory();
						}
					Joueur.heal();
					infight = false;
					refresh();
				}};
			thread.start();
		}
	}
	
	private void victory()
	{
		Joueur.jeu_fini = true;
		Game.MW.addLog(String.format("Temps passé : %.2f secondes !",Joueur.temps_total));
		Game.HI = HiScore.loadScore();
		Game.HI.addScore(new Score(Joueur.defi.name,Joueur.name,Joueur.temps_total,Joueur.universe.seed));
	}

    private boolean combatAuto(int defaite_count, int victory_count, int trap_count, int trap_death_count, int lvl_start)
    {
	int CS = 0;
	if(Joueur.conditionToggle[CS+0] && defaite_count >= Joueur.conditionValues[CS+0]) return false; // "Défaites ="
	if(Joueur.conditionToggle[CS+1] && victory_count >= Joueur.conditionValues[CS+1]) return false; // "Ennemis vaincus ="
	if(Joueur.conditionToggle[CS+2] && (Joueur.level - lvl_start) >= Joueur.conditionValues[CS+2]) return false; // "Niveaux gagnés ="
	if(Joueur.conditionToggle[CS+3] && trap_death_count >= Joueur.conditionValues[CS+3]) return false; // "Morts sur des pièges ="
	if(Joueur.conditionToggle[CS+4] && trap_count >= Joueur.conditionValues[CS+4]) return false; // "Victoires sur des pièges ="
	return true;
    }

	private boolean eviteAuto()
	{
		for (ObjectRule r: Joueur.rules)
			if(r.avoid_rule && r.IsTrue(Joueur,null,Joueur.mob))
				return true;
		return false;
	}

	private boolean shoppingAuto()
	{
		for (ObjectRule r: Joueur.rules)
			if(r.shopping_rule && r.IsTrue(Joueur,null,Joueur.mob))
				return true;
		return false;
	}

	private boolean venteAutoCond(Item i)
	{
		for (ObjectRule r: Joueur.rules)
			if(r.sell_rule && r.IsTrue(Joueur,i,null))
				return true;
		return false;
	}

	private boolean achatAutoCond(Item i)
	{
		for (ObjectRule r: Joueur.rules)
			if(r.buy_rule && r.IsTrue(Joueur,i,null))
				return true;
		return false;
	}

    private void venteAuto()
    {
	Joueur.get_shop();
	ArrayList<Item> tmplst = new ArrayList<Item>();
	for(Item the_object : Joueur.inventory)
	    if(venteAutoCond(the_object) && !the_object.equiped)
			tmplst.add(the_object);
	for(Item the_object : tmplst)
	    Joueur.sell(the_object);
    }

    private void achatAuto()
    {
	Joueur.get_shop();
	ArrayList<Item> tmplst = new ArrayList<Item>();
	for(Item the_object : Joueur.shop.inventory)
	    if(achatAutoCond(the_object))
			tmplst.add(the_object);
	for(Item the_object : tmplst)
	    if(Joueur.can_buy(the_object))
		Joueur.buy(the_object);
    }

    public void addLog(String S)
    {
	String out="";
	lines[index_line]=S;
	
	for (int i=index_line+1; i<=index_line+max_lines; i++)
	    {
		if(lines[i%max_lines]!=null)
		    out=out+lines[i%max_lines]+"\n";
	    }

	index_line = (index_line +1) % max_lines;
	log.setText(out);
    }


    public void fight()
    {
	if(infight)
	    {
		stopfight = true;
	    }
	else
	    {
		infight = true;
		refresh();
		Thread thread = new Thread() {
			public void run() {
			    int lvl_start = Joueur.level;
			    int defaite_count = 0;
			    int victory_count = 0;
				int trap_death_count = 0;
			    int trap_count = 0;
			    do {
					if(Math.random()< Joueur.proba_rencontrer_piege())
						{
						Trap theTrap = Trap.getTrap(Joueur.get_mob_level());
						if (theTrap.trapEncounter(Joueur,true)) trap_death_count++;
						else trap_count++;
						Joueur.heal();
						}
					else
					{
				Joueur.get_mob();
				if(!eviteAuto())
				    {
					refresh();
					if(Joueur.combat(true,true)) defaite_count++;
					else victory_count++;
				    }
				else
				{
					addLog(Joueur.name + " évite " + Joueur.mob.name + " ("+ Joueur.mob.level + ")");
				}
				Joueur.heal();
				if(shoppingAuto())
				    {venteAuto(); achatAuto();}
			    }
				}
			    while(combatAuto(defaite_count,victory_count,trap_count,trap_death_count,lvl_start) && !stopfight);
			    infight = false;
			    stopfight = false;
			    refresh();
			}};
		thread.start();
	    }
    }
}
