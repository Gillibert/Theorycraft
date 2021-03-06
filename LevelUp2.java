import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LevelUp2 extends javax.swing.JDialog  {
    private Player Joueur;
    private javax.swing.JPanel ivjJFrameContentPane = null;

    private JButton moins;
    private JButton plus;
    private JLabel prompt;
    private JButton ok;
    private JButton anu;
    private JTextArea infos;
    private double[] stats_tmp;
    private JScrollPane scroll;
    private JScrollPane scroll2;
    private String[][] rowData;
    private JTable table;

    public LevelUp2(Player JJ) {
	super();
	Joueur = JJ;
	stats_tmp = new double[Joueur.nb_stats];
	initialize();
    }


    public void refresh()
    {
	Joueur.refresh_stats_with_bonus();
	prompt.setText(String.format("Distribution de %.2f points de compétences",Joueur.points_a_distribuer()));
	for (int i=0; i< Joueur.nb_stats ; i++)
	    {
		rowData[i][1] = String.format("%.1f",Joueur.stats[i]);
		rowData[i][2] = String.format("%.2f",Joueur.item_bonus[i]);
		rowData[i][3] = String.format("%.2f",Joueur.stats[i]+Joueur.item_bonus[i]);
	    }
	table.repaint();
	plus.setEnabled(Joueur.points_a_distribuer() >= 1);
	moins.setEnabled(stats_tmp[table.getSelectedRow()] < Joueur.stats[table.getSelectedRow()]);
	infos.setText(Joueur.infos());
    }
	
	
    private javax.swing.JPanel getJFrameContentPane() {
	if (ivjJFrameContentPane == null) {
			
	    rowData = new String[Joueur.nb_stats][4];
	    for (int i=0; i< Joueur.nb_stats ; i++)
		{
		    rowData[i][0] = Joueur.stats_name[i];
		}

	    String[] columnNames = {"Compétence","Points","Equipement","Total"};
	    AbstractTableModel mytm = new MyTableModel(rowData,columnNames);

	    table = new JTable(mytm);
	    //table.setEnabled(false);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setRowSelectionInterval(0,0);
	    table.getColumnModel().getColumn(0).setPreferredWidth(200);
	    table.getColumnModel().getColumn(1).setPreferredWidth(50);
	    table.getColumnModel().getColumn(3).setPreferredWidth(50);

	    javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		    public void valueChanged(javax.swing.event.ListSelectionEvent e) {refresh();}};
				
	    table.getSelectionModel().addListSelectionListener(refresher);
			
	    prompt = new JLabel();
	    prompt.setBounds(new Rectangle(10, 3, 400, 23));

	    scroll = new JScrollPane(table);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setBounds(new Rectangle(10, 32, 370, 320));

	    infos = new JTextArea();
	    infos.setEditable(false);
	    DefaultCaret caret = (DefaultCaret)infos.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

	    scroll2 = new JScrollPane(infos);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll2.setBounds(new Rectangle(385, 32, 275, 320));

	    plus = new JButton();
	    plus.setBounds(new Rectangle(245, 365, 44, 21));
	    plus.setText("+");
	    plus.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				 if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) plus(5);
				 else plus(1);
		    }
		});
	    plus.setMnemonic(KeyEvent.VK_ADD);

	    moins = new JButton();
	    moins.setBounds(new Rectangle(294, 365, 44, 21));
	    moins.setText("-");
	    moins.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) moins(5);
				 else moins(1);
		    }
		});
	    moins.setMnemonic(KeyEvent.VK_SUBTRACT);

	    ok = new JButton();
	    ok.setBounds(new Rectangle(10, 365, 110, 21));
	    ok.setText("Valider");
	    ok.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {valider();
		    }
		});

	    anu = new JButton();
	    anu.setBounds(new Rectangle(125, 365, 110, 21));
	    anu.setText("Annuler");
	    anu.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {cancel();
		    }
		});

	    prompt.setFont(new Font("Times Roman", Font.BOLD, 14));
	    table.setFont(new Font("Times Roman", Font.BOLD, 11));
	    infos.setFont(new Font("Times Roman", Font.PLAIN, 11));

	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

	    ivjJFrameContentPane.add(scroll);
	    ivjJFrameContentPane.add(prompt);
	    ivjJFrameContentPane.add(ok);
	    ivjJFrameContentPane.add(anu);
	    ivjJFrameContentPane.add(plus);
	    ivjJFrameContentPane.add(moins);
	    ivjJFrameContentPane.add(scroll2);

	    this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {cancel();} });
	}
	return ivjJFrameContentPane;
    }
	
    private void initialize() {

	this.setLocation(new Point(15, 15));
	this.setSize(new Dimension(670, 420));
	this.setResizable(false);
	this.setModal(true);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setTitle("Répartition des points de compétences");
	this.setContentPane(getJFrameContentPane());

    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
    
	getRootPane().getActionMap().put("Cancel", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {cancel();}});

	refresh();
    }

    public void dist()
    {
	// BACKUP
	for(int i=0; i<Joueur.nb_stats; i++) 
	    stats_tmp[i] = Joueur.stats[i];
	refresh();
	this.setVisible(true);
    }
	
    private void cancel()
    {
	// RESTORE BACKUP
	for(int i=0; i<Joueur.nb_stats; i++) 
	    Joueur.stats[i] = stats_tmp[i];
	this.setVisible(false);
    }

    private void valider()
    {
	Joueur.refresh();
	this.setVisible(false);
    }
	
    private void plus(int x)
    {
	int toAdd = Math.min((int)(Joueur.points_a_distribuer()),x);
	Joueur.stats[table.getSelectedRow()] += toAdd; 
	refresh();
    }

    private void moins(int x)
    {
	int idx = table.getSelectedRow();
	int toRem = Math.min((int)(Joueur.stats[idx] - stats_tmp[idx]),x);
	Joueur.stats[idx] -= toRem; 
	refresh();
    }

} 
