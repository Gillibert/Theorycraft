import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.text.DefaultCaret;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainWindow extends javax.swing.JDialog {
	public JMenuBar menuBar;
	public JMenu menu;
	
	public boolean mustRefreshCurves=true;
	public boolean mustRefreshCharge=true;
	public boolean mustRefreshMonsters=true;
	public boolean mustRefreshWeather=true;
	
    static private final long serialVersionUID = 1338162012;
	private ScheduledExecutorService refreshService;
    public Player Joueur;
    public LevelUp DistWindow;
	public EditUniverse EditUniverseWindow;
    public Courbes CurvWindow;
    public InventoryWindow InvWindow;
    public WorldMap WorldMapWindow;
	public InfoWindow InWindow;
    public RuleWindow RuleWin;
    private String[] lines;
    private static int max_lines=Game.LOG_WINDOW_MAX_LINES;
    private int index_line;

    public boolean infight=false;
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
    private JButton shop_cmd;
    private JButton craft_cmd;
    private JButton inventaire_cmd;
    private JButton prog_cmd;
	private JButton univers_cmd;
    private JScrollPane scroll;
    public JTextArea log;
    private BufferedWriter outputLog;
    private JButton plus;
    private JButton moins;
    private JButton toggle;
    private JTable conditionTable;
    private JScrollPane tableScroll;
    private String[][] rowData;
	private int refCounter = 0;
	
	public void SetPlayer(Player J)
	{
		Joueur = J;
		DistWindow.SetPlayer(J);
		EditUniverseWindow.SetPlayer(J);;
		CurvWindow.SetPlayer(J);
		InvWindow.SetPlayer(J);
		WorldMapWindow.SetPlayer(J);
		RuleWin.SetPlayer(J);
		InWindow.SetPlayer(J);
		this.setTitle(String.format(Local.THEORYCRAFT_TITLE,Joueur.name,Joueur.defi.name));
	}
	
    public MainWindow(Player J) {
	super();
	Joueur = J;
	outputLog = null;
	
	if(Game.LOG_IN_FILE)
	{
		try {outputLog = new BufferedWriter(new FileWriter("heroes/" + J.name+ ".log",true));}
		catch(Exception ex) {System.out.println(Local.CANT_WRITE_IN_LOG);}
	}
		
	lines = new String[max_lines];
	index_line=0;

	EditUniverseWindow = new EditUniverse(Joueur);
	DistWindow = new LevelUp(Joueur);
	CurvWindow = new Courbes(Joueur);
	InvWindow = new InventoryWindow(Joueur);
	WorldMapWindow = new WorldMap(Joueur);
	RuleWin = new RuleWindow(Joueur);
	InWindow = new InfoWindow(Joueur);

	this.setContentPane(getJFrameContentPane());
	this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	this.setLocation(new Point(0, 0));
	this.setSize(new Dimension(795, 680));		
	this.setResizable(false);

	this.setTitle(String.format(Local.THEORYCRAFT_TITLE,Joueur.name,Joueur.defi.name));
	this.refresh();
	this.refreshTable();
	
	refreshService = Executors.newSingleThreadScheduledExecutor();
    refreshService.scheduleWithFixedDelay(new Runnable()
      {
        @Override
        public void run()
        {
          refreshSlow();
        }
      }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void refreshHpBars() // Refresh hp bars only
    {
	if(Joueur.mob != null) 
	    {
		hp_bar2.setString(String.format(Local.HEALTH_POINTS_LEFT,Joueur.mob.vie,Joueur.mob.vie_max()));
		hp_bar2.setValue((int)(100.0*Joueur.mob.vie/Joueur.mob.vie_max()));
	    }
	else
	    {
		hp_bar2.setValue(0);
		hp_bar2.setString(String.format(Local.HEALTH_POINTS_LEFT,0.0,0.0));
	    }
	hp_bar1.setString(String.format(Local.HEALTH_POINTS_LEFT,Joueur.vie,Joueur.vie_max()));
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
	    toggle.setText(Local.DISABLE);
	else
	    toggle.setText(Local.ENABLE);
    }

	public void refreshButtons()
	{
	if(infight)
	    combat_cmd.setText(Local.STOP_FIGHT);
	else
	    combat_cmd.setText(Local.FIGHT);

	voyager.setEnabled(!infight);
	craft_cmd.setEnabled(!infight);
	inventaire_cmd.setEnabled(!infight);
	shop_cmd.setEnabled(!infight);
	univers_cmd.setEnabled(!infight);
	distrib.setEnabled(!infight);
	prog_cmd.setEnabled(!infight);
	}
	
    public void refresh() // Refresh all except the condition table and the buttons
    {
	SwingUtilities.invokeLater(new Runnable() {
     public void run() {
	if(Joueur.level < Joueur.MAX_LEVEL)
	{
		double current = Joueur.xp_pt-Joueur.xp_level(Joueur.level);
		double next = Joueur.next_level()-Joueur.xp_level(Joueur.level);
		xp_bar.setString(String.format(Local.NEXT_LEVEL,current,next));
		double progress=current/next;
		xp_bar.setValue((int)((100.0*progress)));
	}
	else
	{
		xp_bar.setString(Local.MAX_LEVEL);
		xp_bar.setValue(100);
	}

	//mySetText(stats,Joueur.short_infos());
	stats.setText(Joueur.short_infos());
	if(Joueur.mob != null) 
	    {
		stats3.setText(Joueur.mob.short_stats());
		//mySetText(stats3,Joueur.mob.short_stats());
		if(Joueur.mob.tags[5])
		{
			stats3.setForeground(Color.blue);
			stats3.setSelectedTextColor(Color.blue);
		}
		else
		{
		    stats3.setForeground(Color.black);
			stats3.setSelectedTextColor(Color.black);
		}
	    }
	refreshLog();
	refreshHpBars();
      }
    });
	}
	
	private void refreshSlow()
	{
		refCounter++;
				
		if(refCounter%5==0) {Achievements.refreshAchievements(Joueur,false);}
		
		if(mustRefreshCharge) {Joueur.refresh_charge(); mustRefreshCharge=false;}
		if(mustRefreshWeather) {Joueur.refresh_weather_penalties(); mustRefreshWeather=false;}
		
		if (EditUniverseWindow.isVisible()) EditUniverseWindow.refresh();
		if (InWindow.isVisible() && refCounter%2==0) 
		{
		if(InWindow.displayType==3 && mustRefreshMonsters) 
			{InWindow.refresh(); mustRefreshMonsters=false;}
		if(InWindow.displayType==4 && refCounter%10==0) 
			{InWindow.refresh();}
		if(InWindow.displayType!=3 && InWindow.displayType!=4) 
			{InWindow.refresh();}
		}
		if (CurvWindow.isVisible() && refCounter%2==0 && mustRefreshCurves==true)
		{
			CurvWindow.refresh(); 
			mustRefreshCurves=false;
		}
		if (WorldMapWindow.isVisible()) WorldMapWindow.refresh();
		if(Game.LOG_IN_FILE && refCounter%5==0) {try{outputLog.flush();} catch(Exception ex){}}
		refresh();
	}

	private void save()
	{
		if(infight) {stopfight = true;}
		 Joueur.universe.save();
	}
    private javax.swing.JPanel getJFrameContentPane() {

	this.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
		    save();
			if(Game.LOG_IN_FILE) {
				try{outputLog.flush(); outputLog.close();}
				catch(Exception ex){}
			}
			System.exit(0);
		}});
	 
	menuBar = new JMenuBar();
	menu = new JMenu(Local.GAME);
	//menu.setMnemonic(KeyEvent.VK_A);
	menuBar.add(menu);
	
	JMenuItem menuItem = new JMenuItem(Local.NEW_GAME);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			 save();
			 Game.MW.setVisible(false);
			 Game.MENU.creer();
		}
	    });
	menu.add(menuItem);
	
	menuItem = new JMenuItem(Local.LOAD_A_GAME);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		     save();
			 Game.MW.setVisible(false);
			 Game.MENU.montre_charger();
		}
	    });
	menu.add(menuItem);
	
	menuItem = new JMenuItem(Local.SAVE);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    save();
		}
	    });
	menu.add(menuItem);
	menu.addSeparator();
	
	menuItem = new JMenuItem(Local.EXIT);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    save();
			System.exit(0);
		}
	    });
	menu.add(menuItem);
	
	menu = new JMenu(Local.CHALLENGE);
	menuBar.add(menu);
	menuItem = new JMenuItem(Local.CHANGE_THE_CHALLENGE);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			 Challenge defiBack = Joueur.defi;
		     Game.CW.universe = Joueur.universe;
			 Game.CW.montre_choix_defi();
			 Game.MW.setTitle(String.format(Local.THEORYCRAFT_TITLE,Joueur.name,Joueur.defi.name));
			 if(defiBack != Joueur.defi) Joueur.changer_defi();
		}
	    });
	menu.add(menuItem);
	
	menuItem = new JMenuItem(Local.COMPLETE_THE_CHALLENGE);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		     Joueur.end_game();
		}
	    });
	menu.add(menuItem);
	menu.addSeparator();
	
	menuItem = new JMenuItem(Local.HISCORES);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			Game.CW.universe = Joueur.universe;
		    Game.CW.montre_hiscores();
		}
	    });
	menu.add(menuItem);
	
	menu = new JMenu(Local.INFORMATIONS);
	menuBar.add(menu);
	
	menuItem = new JMenuItem(Local.CHARTS);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			CurvWindow.montre();
		}
	    });
	menu.add(menuItem);
	
	menuItem = new JMenuItem(Local.CRAFTING);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			InWindow.montreInfoCraft();
		}
	    });
	menu.add(menuItem);
	
	menuItem = new JMenuItem(Local.PLAYER_STATISTICS);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			InWindow.montrePlayerStats();
		}
	    });
	menu.add(menuItem);
	
	menuItem = new JMenuItem(Local.UNIVERSE_INFO);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			InWindow.montreInfoUniverse();
		}
	    });
	menu.add(menuItem);
	
	menuItem = new JMenuItem(Local.MONSTERS);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			InWindow.montreMonstres();
		}
	    });
	menu.add(menuItem);
	
	menuItem = new JMenuItem(Local.ACHIEVEMENTS);
	menuItem.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
			InWindow.montreAchievements();
		}
	    });
	menu.add(menuItem);
	

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
	stats.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 12));
	stats.setSelectionColor(stats.getBackground());
	((DefaultCaret)stats.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
	
	stats3 = new JTextArea();
	stats3.setBounds(new Rectangle(215, 5, 205, 235));
	stats3.setEditable(false);
	stats3.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 12));
	stats3.setSelectionColor(stats3.getBackground());
	((DefaultCaret)stats3.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
	plus = new JButton();
	plus.setBounds(new Rectangle(425, 325, 60, 15));
	plus.setText(Local.PLUS);
	plus.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {plus();
		}
	    });
	plus.setMnemonic(KeyEvent.VK_ADD);

	moins = new JButton();
	moins.setBounds(new Rectangle(490, 325, 60, 15));
	moins.setText(Local.MINUS);
	moins.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {moins();
		}
	    });
	moins.setMnemonic(KeyEvent.VK_SUBTRACT);

	toggle = new JButton();
	toggle.setBounds(new Rectangle(650, 325, 135, 15));
	toggle.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {toggle();
		}
	    });

	voyager = new JButton();
	voyager.setBounds(new Rectangle(5, 285, 135, 15));
	voyager.setText(Local.TRAVEL);
	voyager.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    WorldMapWindow.voyager();
		}
	    });
	voyager.setMnemonic('v');

	combat_cmd = new JButton();
	combat_cmd.setBounds(new Rectangle(5, 285+20, 135, 15));
	combat_cmd.setText(Local.FIGHT);
	combat_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    fight();
		}
	    });
	combat_cmd.setMnemonic('c');

	inventaire_cmd = new JButton();
	inventaire_cmd.setBounds(new Rectangle(145, 285, 135, 15));
	inventaire_cmd.setText(Local.INVENTORY);
	inventaire_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    InvWindow.montre_inv();
		}
	    });
	inventaire_cmd.setMnemonic('i');
	
	prog_cmd = new JButton();
	prog_cmd.setBounds(new Rectangle(5, 285+40, 135, 15));
	prog_cmd.setText(Local.PROGRAMMING);
	prog_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    RuleWin.montre();
		}
	    });
	prog_cmd.setMnemonic('p');


	shop_cmd = new JButton();
	shop_cmd.setBounds(new Rectangle(145, 285+20, 135, 15));
	shop_cmd.setText(Local.SHOPPING);
	shop_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    venteAuto();
		    achatAuto();
		    InvWindow.montre_shop();
		}
	    });
	shop_cmd.setMnemonic('s');

	craft_cmd = new JButton();
	craft_cmd.setBounds(new Rectangle(145, 285+40, 135, 15));
	craft_cmd.setText(Local.CRAFTING);
	craft_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    Joueur.get_forge();
		    InvWindow.montre_craft();
		}
	    });
	craft_cmd.setMnemonic('r');

	distrib = new JButton();
	distrib.setBounds(new Rectangle(285, 285, 135, 15));
	distrib.setText(Local.SKILLS);
	distrib.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    DistWindow.dist(); 
		}
	    });
	distrib.setMnemonic('t');
	
	univers_cmd = new JButton();
	univers_cmd.setBounds(new Rectangle(285, 285+40, 135, 15));
	univers_cmd.setText(Local.UNIVERSE_EDITION);
	univers_cmd.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		   EditUniverseWindow.dist(); 
		}
	});
	univers_cmd.setMnemonic('u');
	
	log = new JTextArea();
	log.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 12));
	log.setEditable(false);
	log.setLineWrap(true);
	log.setWrapStyleWord(true);
	log.setBackground(Color.lightGray);
	log.setSelectionColor(Color.lightGray);
	((DefaultCaret)log.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
	
	scroll = new JScrollPane(log);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setBounds(new Rectangle(5, 345, 785, 285));

	rowData = new String[5][4];

	rowData[0][0] = rowData[1][0] = rowData[2][0] = rowData[3][0] = rowData[4][0] = Local.STOP_TO_FIGHT;

	rowData[0][1] = Local.DEFEATS;
	rowData[1][1] = Local.DEFEATED_ENEMIES;		
	rowData[2][1] = Local.LEVELS_WON;
	rowData[3][1] = Local.LETHAL_TRAPS;
	rowData[4][1] = Local.SURVIVED_TRAPS;
	
	String[] columnNames = Local.STOP_RULES_COLUMN_NAMES;
	AbstractTableModel mytm = new MyTableModel(rowData,columnNames);

	conditionTable = new JTable(mytm);
	//table.setEnabled(false);
	conditionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	conditionTable.setRowSelectionInterval(0,0);
	conditionTable.getColumnModel().getColumn(0).setPreferredWidth(180);
	conditionTable.getColumnModel().getColumn(1).setPreferredWidth(210);
	conditionTable.getColumnModel().getColumn(2).setPreferredWidth(60);
	conditionTable.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 11));

	javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		public void valueChanged(javax.swing.event.ListSelectionEvent e) {refreshTable();}};
				
	conditionTable.getSelectionModel().addListSelectionListener(refresher);

	tableScroll = new JScrollPane(conditionTable);
	tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	tableScroll.setBounds(new Rectangle(425, 5, 365, 315));

	this.setJMenuBar(menuBar);
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
	ivjJFrameContentPane.add(shop_cmd);
	ivjJFrameContentPane.add(craft_cmd);
	ivjJFrameContentPane.add(inventaire_cmd);
	ivjJFrameContentPane.add(prog_cmd);
	ivjJFrameContentPane.add(tableScroll);
	ivjJFrameContentPane.add(plus);
	ivjJFrameContentPane.add(moins);
	ivjJFrameContentPane.add(toggle);
	ivjJFrameContentPane.add(scroll);
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

	
	

    private boolean combatAuto(int defaite_count, int victory_count, int trap_count, int trap_death_count, double lvl_start)
    {
	int CS = 0;
	if(Joueur.conditionToggle[CS+0] && defaite_count >= Joueur.conditionValues[CS+0]) return false; // "Défaites ="
	if(Joueur.conditionToggle[CS+1] && victory_count >= Joueur.conditionValues[CS+1]) return false; // "Ennemis vaincus ="
	if(Joueur.conditionToggle[CS+2] && Math.floor(Joueur.level - lvl_start) >= Joueur.conditionValues[CS+2]) return false; // "Niveaux gagnés ="
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

	private void autoVenteForgeDist()
	{
		for (ObjectRule r: Joueur.rules)
		{
			if(r.shopping_rule && r.IsTrue(Joueur,null,Joueur.mob)) {venteAuto(); achatAuto();}
			if(r.forge_rule && r.IsTrue(Joueur,null,Joueur.mob)) {Joueur.get_forge(); Joueur.craftAuto();}
			if(r.dist_rule && r.IsTrue(Joueur,null,Joueur.mob)) Joueur.auto_dist();
		}
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
	Joueur.sell(tmplst);
    }

    private void achatAuto()
    {
	Joueur.get_shop();
	ArrayList<Item> tmplst = new ArrayList<Item>();
	for(Item the_object : Joueur.shop.inventory)
	    if(achatAutoCond(the_object))
			tmplst.add(the_object);
	Joueur.buy(tmplst);
    }

    public void addLog(String S)
    {
	lines[index_line]=S;
	index_line = (index_line +1) % max_lines;

	if(Game.LOG_IN_FILE)
		{
		try {outputLog.write(S+"\n");}
		catch(Exception ex) {System.out.println(Local.CANT_WRITE_IN_LOG);}
		}
	}
	
	public void refreshLog()
	{
	String out="";
	for (int i=index_line; i<index_line+max_lines; i++)
	    {
		if(lines[i%max_lines]!=null)
		    out=out+lines[i%max_lines]+"\n";
	    }

	log.setText(out);
	//mySetText(log,out);
    }

	// Obsolete, plus utilisé
	public void mySetText(JTextArea txtArea, String str)
	{
		int selectionStart = 0;
		int selectionEnd = 0;
		boolean restoreSelection = false;
		if (this.getFocusOwner() == txtArea)
		{
			selectionStart = txtArea.getSelectionStart();
			selectionEnd = txtArea.getSelectionEnd();
			restoreSelection = (selectionStart != selectionEnd);
		}
		txtArea.setText(str);
		if(restoreSelection)
		{
			txtArea.requestFocus();
			txtArea.select(selectionStart, selectionEnd);
		}
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
		refreshButtons();
		Thread thread = new Thread() {
			public void run() {
			    double lvl_start = Joueur.level;
			    int defaite_count = 0;
			    int victory_count = 0;
				int trap_death_count = 0;
			    int trap_count = 0;
			    do {
					Joueur.update_encounters();
					if(Math.random()< Joueur.proba_rencontrer_piege())
						{
						Trap theTrap = Trap.getTrap(Joueur);
						if (theTrap.trapEncounter(Joueur)) trap_death_count++;
						else trap_count++;
						}
					else
					{
					Joueur.get_mob();
					if(!eviteAuto())
						{
						if(Joueur.combat(true)) defaite_count++;
						else victory_count++;
						}
					else
						{
						addLog(String.format(Local.AVOIDS_MONSTER,Joueur.name,Joueur.mob.name,Joueur.mob.level));
						}
					}
					Joueur.heal();
					autoVenteForgeDist();
					}
			    while(combatAuto(defaite_count,victory_count,trap_count,trap_death_count,lvl_start) && !stopfight);
			    infight = false;
			    stopfight = false;
			    refreshButtons();
			}};
		thread.start();
	    }
    }
}
