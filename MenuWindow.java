import javax.swing.*;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.awt.Point;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.io.File;

public class MenuWindow extends javax.swing.JDialog  {

	
    private javax.swing.JPanel ivjJFrameContentPane;
    private JLabel titre;
    private JButton creer;
    private JButton charger;
    private JButton effacer;
    private JList sauvegardes;
    private DefaultListModel save_list;
    private ChallengeWindow theChallengeWindow;
    private JScrollPane scroll;
	
    public MenuWindow() {
	super();

	theChallengeWindow = new ChallengeWindow();
	this.setContentPane(getJFrameContentPane());
	this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	this.setLocation(new Point(0, 0));
	this.setSize(new Dimension(300, 400));
	this.setResizable(false);
	this.setTitle("Theorycraft");

	refresh();
    }

    public void refresh_buttons()
    {
	charger.setEnabled(sauvegardes.getSelectedIndex() != -1);
	effacer.setEnabled(sauvegardes.getSelectedIndex() != -1);	    
    }

    public void refresh()
    {
	// Directory path here
	String path = "."; 
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
	File f = new File((String)save_list.get(sauvegardes.getSelectedIndex()));
	 f.delete();
    }

    public void creer()
    {
	Player Joueur = new Player();
	Joueur.name = choix_nom();
	if(Joueur.name != null)
		{
		theChallengeWindow.Joueur = Joueur;
		theChallengeWindow.montre();
		if(Game.DEBUG) Joueur.giveStuff();
		Joueur.save();

	    this.setVisible(false);
	    World Monde = new World();
	    Game.MW = new MainWindow(Monde,Joueur);
	    Game.MW.setVisible(true);		
		}
    }

    public void charger()
    {
	try{
	    Player Joueur;
	    InputStream file = new FileInputStream((String)save_list.get(sauvegardes.getSelectedIndex()));
	    InputStream buffer = new BufferedInputStream( file );
	    ObjectInput input = new ObjectInputStream ( buffer );
	    Joueur = (Player)input.readObject();
	    input.close();

	    this.setVisible(false);
	    World Monde = new World();
	    Game.MW = new MainWindow(Monde,Joueur);
	    Game.MW.setVisible(true);
	}
	catch(Exception ex)
	    {
		refresh();
		System.out.println(ex);
	    }

    }

    private String choix_nom()
    {
 	return JOptionPane.showInputDialog(null, "Choisissez un nom", "Création d'un héros", 1);
    }
	

    private javax.swing.JPanel getJFrameContentPane() {
	
	titre = new JLabel();
	titre.setText(" Sélection du héros");
	titre.setBounds(new Rectangle(5, 5, 290, 30));
	titre.setFont(new Font("Times New Roman", Font.PLAIN, 20));

	save_list = new DefaultListModel();
	sauvegardes = new JList(save_list);
	sauvegardes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

	scroll = new JScrollPane(sauvegardes);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setBounds(new Rectangle(10, 40, 280, 195));
	    
	javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		public void valueChanged(javax.swing.event.ListSelectionEvent e) {refresh_buttons();}};

	sauvegardes.addListSelectionListener(refresher);

	creer = new JButton();
	creer.setBounds(new Rectangle(5, 240, 280, 30));
	creer.setText("Créer un héros");
	creer.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    creer();
		}
	    });
	creer.setMnemonic('c');

	charger = new JButton();
	charger.setBounds(new Rectangle(5, 275, 280, 30));
	charger.setText("Charger un héros");
	charger.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    charger();
		}
	    });
	charger.setMnemonic('r');

	effacer = new JButton();
	effacer.setBounds(new Rectangle(5, 310, 280, 30));
	effacer.setText("Supprimer un héros");
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

	return ivjJFrameContentPane;
    }
}
