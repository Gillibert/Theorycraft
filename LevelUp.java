import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LevelUp extends javax.swing.JDialog  {
    private Player Joueur;
    private javax.swing.JPanel ivjJFrameContentPane = null;

    private JButton moins;
	private JButton equi;
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

    public LevelUp(Player J) {
	super();
	Joueur = J;
	stats_tmp = new double[Joueur.nb_stats];
	initialize();
    }

	public void SetPlayer(Player J)
	{
		Joueur = J;
		refresh();
	}

    public void refresh()
    {
	Joueur.refresh_stats_with_bonus();
	prompt.setText(String.format(Local.DISTRIBUTION_OF_SKILL_POINTS,Joueur.points_a_distribuer()));
	for (int i=0; i< Joueur.nb_stats ; i++)
	    {
		rowData[i][1] = String.format("%g",Joueur.stats[i]);
		rowData[i][2] = String.format("%.2f",Joueur.item_bonus[i]);
		rowData[i][3] = String.format("%g",Joueur.stats[i]+Joueur.item_bonus[i]);
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

	    AbstractTableModel mytm = new MyTableModel(rowData,Local.LEVEL_UP_COLUMN_NAMES);

	    table = new JTable(mytm);
	    //table.setEnabled(false);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setRowSelectionInterval(0,0);
	    table.getColumnModel().getColumn(0).setPreferredWidth(200);
	    table.getColumnModel().getColumn(1).setPreferredWidth(75);
	    table.getColumnModel().getColumn(3).setPreferredWidth(75);

	    javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		    public void valueChanged(javax.swing.event.ListSelectionEvent e) {refresh();}};
				
	    table.getSelectionModel().addListSelectionListener(refresher);
			
	    prompt = new JLabel();
	    prompt.setBounds(new Rectangle(10, 3, 400, 23));

	    scroll = new JScrollPane(table);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setBounds(new Rectangle(10, 32, 370+50, 320));

	    infos = new JTextArea();
	    infos.setEditable(false);
	    DefaultCaret caret = (DefaultCaret)infos.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

	    scroll2 = new JScrollPane(infos);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll2.setBounds(new Rectangle(385+50, 32, 275+70, 320));

	    plus = new JButton();
	    plus.setBounds(new Rectangle(245, 365, 44, 21));
	    plus.setText(Local.PLUS);
	    plus.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) 
				{
					double step = Math.max(Math.pow(10.0,Math.floor(Math.log10(Joueur.points_a_distribuer())))*0.5,5.0);
					plus(step);
				}
				else plus(1);
		    }
		});
	    plus.setMnemonic(KeyEvent.VK_ADD);

	    moins = new JButton();
	    moins.setBounds(new Rectangle(294, 365, 44, 21));
	    moins.setText(Local.MINUS);
	    moins.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)
				{
					double step = Math.max(Math.pow(10.0,Math.floor(Math.log10(Joueur.points_a_distribuer())))*0.5,5.0);
					moins(step);
				}
				else moins(1);
		    }
		});
	    moins.setMnemonic(KeyEvent.VK_SUBTRACT);
		
		equi = new JButton();
	    equi.setBounds(new Rectangle(294+49, 365, 150, 21));
	    equi.setText(Local.EQUAL_DISTRIBUTION);
	    equi.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				equirepartir();
		    }
		});

	    ok = new JButton();
	    ok.setBounds(new Rectangle(10, 365, 110, 21));
	    ok.setText(Local.VALIDATE);
	    ok.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {valider();
		    }
		});

	    anu = new JButton();
	    anu.setBounds(new Rectangle(125, 365, 110, 21));
	    anu.setText(Local.CANCEL);
	    anu.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {cancel();
		    }
		});

	    prompt.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 14));
	    table.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 11));
	    infos.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));

	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

	    ivjJFrameContentPane.add(scroll);
	    ivjJFrameContentPane.add(prompt);
	    ivjJFrameContentPane.add(ok);
	    ivjJFrameContentPane.add(anu);
	    ivjJFrameContentPane.add(plus);
	    ivjJFrameContentPane.add(moins);
		ivjJFrameContentPane.add(equi);
	    ivjJFrameContentPane.add(scroll2);

	    this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {cancel();} });
	}
	return ivjJFrameContentPane;
    }
	
    private void initialize() {

	this.setLocation(new Point(15, 15));
	this.setSize(new Dimension(670+70+50, 420));
	this.setResizable(false);
	this.setModal(true);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setTitle(Local.SKILL_POINTS_DISTRIBUTION);
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
	Joueur.refresh_stats_with_bonus();
	Joueur.refresh();
	this.setVisible(false);
    }

    private void valider()
    {
	Joueur.refresh_stats_with_bonus();
	Joueur.refresh();
	this.setVisible(false);
    }
	
    private void plus(double x)
    {
	/*System.out.println("points_totaux="+Joueur.points_totaux());
	System.out.println("points_a_distribuer="+Joueur.points_a_distribuer());
	System.out.println("points_distribues="+Joueur.points_distribues());*/
	
	double toAdd = Math.min(Math.floor(Joueur.points_a_distribuer()),x);
	Joueur.stats[table.getSelectedRow()] += toAdd; 
	refresh();
    }
	
    private void equirepartir()
	{
		double toAdd = Math.floor(Joueur.points_a_distribuer()/Joueur.nb_stats);
		for(int i=0; i<Joueur.nb_stats; i++)
		{
			Joueur.stats[i] += toAdd; 
		}
		refresh();
	}
	
    private void moins(double x)
    {
	int idx = table.getSelectedRow();
	double toRem = Math.min(Math.floor(Joueur.stats[idx] - stats_tmp[idx]),x);
	Joueur.stats[idx] -= toRem; 
	refresh();
    }

} 
