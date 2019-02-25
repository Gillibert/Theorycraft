import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LevelUp extends javax.swing.JDialog  {
    private Player Joueur;
    private javax.swing.JPanel ivjJFrameContentPane = null;

	private JButton plus;
    private JButton moins;
	private JButton plus2;
    private JButton moins2;
	private JButton equi;
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
		rowData[i][2] = String.format("%g",Joueur.item_bonus[i]);
		rowData[i][3] = String.format("%g",Joueur.stats[i]+Joueur.item_bonus[i]);
		rowData[i][4] = String.format("%d",Joueur.auto_dist_coeff[i]);
	    }
	table.repaint();
	plus.setEnabled(Joueur.points_a_distribuer() >= 1);
	moins.setEnabled(stats_tmp[table.getSelectedRow()] < Joueur.stats[table.getSelectedRow()]);
	moins2.setEnabled(Joueur.auto_dist_coeff[table.getSelectedRow()]>0);
	infos.setText(Joueur.infos());
    }
	
	
    private javax.swing.JPanel getJFrameContentPane() {
	if (ivjJFrameContentPane == null) {
			
	    rowData = new String[Joueur.nb_stats][5];
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
	    table.getColumnModel().getColumn(1).setPreferredWidth(90);
	    table.getColumnModel().getColumn(2).setPreferredWidth(90);
	    table.getColumnModel().getColumn(3).setPreferredWidth(90);
	    table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getTableHeader().setReorderingAllowed(false);
		
	    javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		    public void valueChanged(javax.swing.event.ListSelectionEvent e) {refresh();}};
				
	    table.getSelectionModel().addListSelectionListener(refresher);
			
	    prompt = new JLabel();
	    prompt.setBounds(new Rectangle(10, 3, 400, 23));

	    scroll = new JScrollPane(table);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setBounds(new Rectangle(10, 32, 770, 300));

	    infos = new JTextArea();
	    infos.setEditable(false);
	    DefaultCaret caret = (DefaultCaret)infos.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

	    scroll2 = new JScrollPane(infos);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll2.setBounds(new Rectangle(10, 332+8+21+8, 770, 235));

	    plus = new JButton();
	    plus.setBounds(new Rectangle(245+15, 332+8, 44, 21));
	    plus.setText(Local.PLUS);
	    plus.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) 
				{
					plus(step(Joueur.points_a_distribuer()));
				}
				else plus(1);
		    }
		});
	    plus.setMnemonic(KeyEvent.VK_ADD);

	    moins = new JButton();
	    moins.setBounds(new Rectangle(294+15, 332+8, 44, 21));
	    moins.setText(Local.MINUS);
	    moins.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)
				{
					moins(step(Joueur.points_a_distribuer()));
				}
				else moins(1);
		    }
		});
	    moins.setMnemonic(KeyEvent.VK_SUBTRACT);
		
		plus2 = new JButton();
	    plus2.setBounds(new Rectangle(650, 332+8, 44, 21));
	    plus2.setText(Local.PLUS);
	    plus2.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) 
				{
					plus2(step(Joueur.auto_dist_coeff[table.getSelectedRow()]));
				}
				else plus2(1);
		    }
		});

	    moins2 = new JButton();
	    moins2.setBounds(new Rectangle(650+49, 332+8, 44, 21));
	    moins2.setText(Local.MINUS);
	    moins2.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)
				{
					moins2(step(Joueur.auto_dist_coeff[table.getSelectedRow()]));
				}
				else moins2(1);
		    }
		});
		
		equi = new JButton();
	    equi.setBounds(new Rectangle(10, 332+8, 150, 21));
	    equi.setText(Local.AUTO_DISTRIBUTION);
	    equi.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				Joueur.auto_dist(); refresh();
		    }
		});

	    ok = new JButton();
	    ok.setBounds(new Rectangle(670, 611, 110, 21));
	    ok.setText(Local.VALIDATE);
	    ok.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {valider();
		    }
		});

	    anu = new JButton();
	    anu.setBounds(new Rectangle(550, 611, 110, 21));
	    anu.setText(Local.CANCEL);
	    anu.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {cancel();
		    }
		});

	    prompt.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 14));
	    table.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 11));
	    infos.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 12));

	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

	    ivjJFrameContentPane.add(scroll);
	    ivjJFrameContentPane.add(prompt);
	    ivjJFrameContentPane.add(ok);
	    ivjJFrameContentPane.add(anu);
	    ivjJFrameContentPane.add(plus);
	    ivjJFrameContentPane.add(moins);
	    ivjJFrameContentPane.add(plus2);
	    ivjJFrameContentPane.add(moins2);
		ivjJFrameContentPane.add(equi);
	    ivjJFrameContentPane.add(scroll2);

	    this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {cancel();} });
	}
	return ivjJFrameContentPane;
    }
	
    private void initialize() {

	this.setLocation(new Point(15, 15));
	this.setSize(new Dimension(670+70+50, 660));
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
	double toAdd = Math.min(Math.floor(Joueur.points_a_distribuer()),x);
	Joueur.stats[table.getSelectedRow()] += toAdd; 
	refresh();
    }
	
	
	private void moins2(double x)
    {
	Joueur.auto_dist_coeff[table.getSelectedRow()] -= x; 
	if(Joueur.auto_dist_coeff[table.getSelectedRow()] <0) Joueur.auto_dist_coeff[table.getSelectedRow()] = 0;
	refresh();
    }
	
	private void plus2(double x)
    {
	Joueur.auto_dist_coeff[table.getSelectedRow()] += x; 
	refresh();
    }
	
	double step(double x)
	{
		return Math.max(Math.pow(10.0,Math.floor(Math.log10(x)))*0.5,5.0);
	}
	
    private void moins(double x)
    {
	int idx = table.getSelectedRow();
	double toRem = Math.min(Math.floor(Joueur.stats[idx] - stats_tmp[idx]),x);
	Joueur.stats[idx] -= toRem; 
	refresh();
    }

} 
