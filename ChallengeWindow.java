import javax.swing.*;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

public class ChallengeWindow extends javax.swing.JDialog  {

    private javax.swing.JPanel ivjJFrameContentPane;
    private JLabel titre;
    private JTextArea desc;
    private JButton choisir;
	private JButton set_seed;
    private DefaultListModel defi_list;
	private JList challenges;
	private JScrollPane scroll;
	public Universe universe;
	
    public ChallengeWindow() {
	super();
	this.setContentPane(getJFrameContentPane());
	this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	this.setLocation(new Point(0, 0));
	this.setSize(new Dimension(565, 345));
	this.setResizable(false);
	this.setModal(true);
	this.setTitle("Choix du défi");
    }
	
	public void set_seed()
	{
		String s = JOptionPane.showInputDialog(null, "Choisissez une graine (un entier naturel)\n 0 pour les règles classiques", "Création d'un univers", 1);
		int sd = 0;
		try { sd = Integer.parseInt(s);}
		catch (Exception e) {}
		String backupName = universe.joueur.name;
		universe = new Universe(sd);
		universe.joueur.name = backupName;
		refresh();
	}
	
    public void refresh()
    {
	if (challenges.getSelectedIndex() != -1)
		{
			Challenge chal = ChallengeList.list.get(challenges.getSelectedIndex());
			desc.setText(chal.desc() + "\nHiscores :\n" + Game.HI.bestScoresString(chal.name));
		}
	choisir.setEnabled(challenges.getSelectedIndex() != -1);
	set_seed.setText("Graine : " + universe.seed);
    }


    public void montre()
    {
	if (challenges.getSelectedIndex() == -1)
		challenges.setSelectedIndex(0);
	refresh();
	setVisible(true);
    }

    public void choisir()
    {
	if (challenges.getSelectedIndex() == -1)
		universe.joueur.defi = ChallengeList.list.get(0);
	else
		universe.joueur.defi = ChallengeList.list.get(challenges.getSelectedIndex());
	
	this.setVisible(false);
    }


    private javax.swing.JPanel getJFrameContentPane() {			
			
	titre = new JLabel();
	titre.setText(" Choisissez un défi");
	titre.setBounds(new Rectangle(5, 5, 290, 30));
	titre.setFont(new Font("Times New Roman", Font.PLAIN, 20));

	defi_list = new DefaultListModel();
	
	for(Challenge c : ChallengeList.list)
		defi_list.addElement(c.name);	
		
	challenges = new JList(defi_list);
	challenges.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

    scroll = new JScrollPane(challenges);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scroll.setBounds(new Rectangle(5, 40, 275, 240));

	javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		public void valueChanged(javax.swing.event.ListSelectionEvent e) {refresh();}};

	challenges.addListSelectionListener(refresher);

	desc = new JTextArea();
	desc.setBounds(new Rectangle(285, 40, 265, 240));
	desc.setFont(new Font("Times New Roman", Font.PLAIN, 12));
	desc.setEditable(false);
	
	set_seed = new JButton();
	set_seed.setBounds(new Rectangle(5, 240+45, 275, 25));
	set_seed.setText("Graine ...");
	set_seed.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    set_seed();
		}
	    });
	set_seed.setMnemonic('g');
	
	choisir = new JButton();
	choisir.setBounds(new Rectangle(285, 240+45, 265, 25));
	choisir.setText("Créer");
	choisir.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    choisir(); refresh();
		}
	    });
	choisir.setMnemonic('c');


	ivjJFrameContentPane = new javax.swing.JPanel();
	ivjJFrameContentPane.setLayout(null);
	ivjJFrameContentPane.add(titre);
	ivjJFrameContentPane.add(scroll);
	ivjJFrameContentPane.add(choisir);
	ivjJFrameContentPane.add(set_seed);
	ivjJFrameContentPane.add(desc);
	
    this.addWindowListener(new java.awt.event.WindowAdapter() {
	    public void windowClosing(java.awt.event.WindowEvent e) {choisir();} });
	
	return ivjJFrameContentPane;
    }
}
