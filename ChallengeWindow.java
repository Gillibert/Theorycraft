import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ChallengeWindow extends javax.swing.JDialog  {

    private javax.swing.JPanel ivjJFrameContentPane;
    private JLabel titre;
    private JTextArea desc;
    private JButton choisir;
    private DefaultListModel defi_list;
	private JList challenges;
	private JScrollPane scroll;
	public Player Joueur;
	
    public ChallengeWindow() {
	super();
	this.setContentPane(getJFrameContentPane());
	this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	this.setLocation(new Point(0, 0));
	this.setSize(new Dimension(300, 400));
	this.setResizable(false);
	this.setModal(true);
	this.setTitle("Choix du défi");
    }


    public void refresh()
    {
	if (challenges.getSelectedIndex() != -1)
		{
			Challenge chal = ChallengeList.list.get(challenges.getSelectedIndex());
			desc.setText(chal.desc() + "\nHiscores :\n" + Game.HI.bestScoresString(chal.name));
		}
	choisir.setEnabled(challenges.getSelectedIndex() != -1);
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
		Joueur.defi = ChallengeList.list.get(0);
	else
		Joueur.defi = ChallengeList.list.get(challenges.getSelectedIndex());
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
    scroll.setBounds(new Rectangle(10, 40, 280, 195));

	javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		public void valueChanged(javax.swing.event.ListSelectionEvent e) {refresh();}};

	challenges.addListSelectionListener(refresher);

	desc = new JTextArea();
	desc.setBounds(new Rectangle(10, 240, 280, 100));
	desc.setEditable(false);
	
	choisir = new JButton();
	choisir.setBounds(new Rectangle(10, 345, 280, 30));
	choisir.setText("Valider");
	choisir.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent e) {
		    choisir(); refresh();
		}
	    });
	choisir.setMnemonic('v');


	ivjJFrameContentPane = new javax.swing.JPanel();
	ivjJFrameContentPane.setLayout(null);
	ivjJFrameContentPane.add(titre);
	ivjJFrameContentPane.add(scroll);
	ivjJFrameContentPane.add(choisir);
	ivjJFrameContentPane.add(desc);
	
    this.addWindowListener(new java.awt.event.WindowAdapter() {
	    public void windowClosing(java.awt.event.WindowEvent e) {choisir();} });
	
	return ivjJFrameContentPane;
    }
}
