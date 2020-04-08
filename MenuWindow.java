import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.File;

public class MenuWindow extends javax.swing.JDialog  {

	
    private javax.swing.JPanel ivjJFrameContentPane;
    private JLabel titre;
	private JTextArea save_desc;
    private JButton creer;
    private JButton charger;
    private JButton effacer;
	//private JButton hiscores;
    private JList sauvegardes;
    private DefaultListModel save_list;
    private JScrollPane scroll;
	
    public MenuWindow() {
	super();
	this.setContentPane(getJFrameContentPane());
	this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	this.setLocation(new Point(0, 0));
	this.setSize(new Dimension(300+Game.ADJUST_SIZE_X, 370+Game.ADJUST_SIZE_Y));
	this.setResizable(false);
	this.setTitle(Local.THEORYCRAFT);

	refresh();
    }

	public void montre_menu()
	{
		creer.setVisible(true);
		effacer.setVisible(true);
		this.setSize(new Dimension(300+Game.ADJUST_SIZE_X, 370+Game.ADJUST_SIZE_Y));
		this.setVisible(true);
	}
	
	public void montre_charger()
	{
		refresh();
		creer.setVisible(false);
		effacer.setVisible(false);
		this.setSize(new Dimension(300+Game.ADJUST_SIZE_X, 310+Game.ADJUST_SIZE_Y));
		this.setVisible(true);
	}
	
    public void refresh_buttons()
    {
	charger.setEnabled(sauvegardes.getSelectedIndex() != -1);
	effacer.setEnabled(sauvegardes.getSelectedIndex() != -1);
	set_desc();    
    }

    public void refresh()
    {
	// Directory path here
	String path = "./heroes/"; 
	String files;
	File folder = new File(path);
	File[] listOfFiles = folder.listFiles(); 
 
	save_list.clear();
	for (int i = 0; i < listOfFiles.length; i++) 
	    {
		if (listOfFiles[i].isFile())
		    {
			files = listOfFiles[i].getName();
			if (files.endsWith(".theory"))
			    {
				save_list.addElement(files);
			    }
		    }
	    }
	if(sauvegardes.getSelectedIndex() == -1 && listOfFiles.length > 0)
		sauvegardes.setSelectedIndex(0);
	refresh_buttons();
    }
	
    public void effacer()
    {
	String fn = (String)save_list.get(sauvegardes.getSelectedIndex());
	File f = new File("heroes/" + fn);
	f.delete();
	
	int dotPos = fn.lastIndexOf(".");
	String strFilename = fn.substring(0, dotPos);
	String strNewFileName = strFilename + ".log";
	File f2 = new File("heroes/" + strNewFileName);
	f2.delete();
    }

    public void creer()
    {
	Universe universe = new Universe(0);
	universe.joueur.name = choix_nom();
	if(universe.joueur.name != null)
		{
		Game.CW.universe = universe;
		Game.CW.montre();
		
	    this.setVisible(false);
		universe = Game.CW.universe;
		universe.save();
		
		StaticItem.init(universe);
		Monster.SetOptimalDistribution(universe);
		if(Game.DEBUG_MODE_GIFT) universe.joueur.giveStuff();
		
		if (Game.MW == null)
		{
			Game.MW = new MainWindow(universe.joueur);
		}
		else
		{
			Game.MW.SetPlayer(universe.joueur);
		}
		
	    Game.MW.setVisible(true);		
		}
    }

	public void set_desc()
	{
	InputStream file = null;
	try{
		Universe universe;
	    file = new FileInputStream("heroes/" + (String)save_list.get(sauvegardes.getSelectedIndex()));
	    InputStream buffer = new BufferedInputStream( file );
		ObjectInput input = new ObjectInputStream ( buffer );
	    universe = (Universe)input.readObject();
		save_desc.setText(String.format(Local.SEED_TIME,universe.joueur.name,universe.joueur.level,universe.seed,universe.joueur.temps_total));
		file.close();
		}
	catch(Exception ex)
	    {
		save_desc.setText("...");
	    }
	try {if (file != null) file.close();}
	catch(Exception ex) {System.out.println(ex);}
	}
	
    public void charger()
    {
	InputStream file = null;
	try{
		Universe universe;
	    file = new FileInputStream("heroes/" + (String)save_list.get(sauvegardes.getSelectedIndex()));
	    InputStream buffer = new BufferedInputStream( file );
	    ObjectInput input = new ObjectInputStream ( buffer );
	    universe = (Universe)input.readObject();
		StaticItem.init(universe);
		Monster.SetOptimalDistribution(universe);
		
	    this.setVisible(false);
		if (Game.MW == null)
		{
			Game.MW = new MainWindow(universe.joueur);
		}
		else
		{
			Game.MW.SetPlayer(universe.joueur);
		}
	    Game.MW.setVisible(true);
	}
	catch(Exception ex)
	    {
		refresh();
		System.out.println(ex);
	    }
	try {if (file != null) file.close();}
	catch(Exception ex) {System.out.println(ex);}
    }

    private String choix_nom()
    {
 	return JOptionPane.showInputDialog(null, Local.CHOOSE_A_NAME, Local.CREATION_OF_A_HERO, 1);
    }
	

    private javax.swing.JPanel getJFrameContentPane() {
	
	titre = new JLabel();
	titre.setText(Local.HERO_SELECTION);
	titre.setBounds(new Rectangle(5, 5, 290, 30));
	titre.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 20));

	save_list = new DefaultListModel();
	sauvegardes = new JList(save_list);
	sauvegardes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

	scroll = new JScrollPane(sauvegardes);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setBounds(new Rectangle(5, 40, 280, 155));
	    
	javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		public void valueChanged(javax.swing.event.ListSelectionEvent evt) 
		{if (!evt.getValueIsAdjusting()) {refresh_buttons();}}}; // Prevent double refresh
		
	sauvegardes.addListSelectionListener(refresher);
	
	MouseListener mouseListener = new MouseAdapter(){
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
			charger();
         }
		}
	};
	sauvegardes.addMouseListener(mouseListener);

	save_desc = new JTextArea();
	save_desc.setBounds(new Rectangle(5, 200, 280, 40));
	save_desc.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 12));
	

	charger = new JButton();
	charger.setBounds(new Rectangle(5, 245, 280, 28));
	charger.setText(Local.LOAD_A_HERO);
	charger.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    charger();
		}
	    });
	charger.setMnemonic('r');

	creer = new JButton();
	creer.setBounds(new Rectangle(5, 275, 280, 28));
	creer.setText(Local.CREATE_A_HERO);
	creer.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    creer();
		}
	    });
	creer.setMnemonic('c');
	
	effacer = new JButton();
	effacer.setBounds(new Rectangle(5, 305, 280, 28));
	effacer.setText(Local.DELETE_A_HERO);
	effacer.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    effacer(); refresh();
		}
	    });
		
	ivjJFrameContentPane = new javax.swing.JPanel();
	ivjJFrameContentPane.setLayout(null);
	ivjJFrameContentPane.add(titre);
	ivjJFrameContentPane.add(scroll);
	ivjJFrameContentPane.add(creer);
	ivjJFrameContentPane.add(charger);
	ivjJFrameContentPane.add(effacer);
	//ivjJFrameContentPane.add(hiscores);
	ivjJFrameContentPane.add(save_desc);
	
	return ivjJFrameContentPane;
    }
}
