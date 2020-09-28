import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultCaret;
import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ClassWindow extends javax.swing.JDialog  {
    private Player Joueur;
    private javax.swing.JPanel ivjJFrameContentPane = null;
	private boolean mustRefreshInfos = true;
    private JButton choisir;
    private JScrollPane scroll;
	private JScrollPane scroll2;
    private MyTableModel tableModel;
    private JTable table;
	private JEditorPane infos;


    public ClassWindow(Player J) {
	super();
	Joueur = J;
	initialize();
    }
	
	public void SetPlayer(Player J)
	{
		Joueur = J;
		refresh();
	}

	public void montre()
    {
	mustRefreshInfos = true;
	refresh();
	this.setVisible(true);
    }
	
	private void refreshInfos()
	{
		String res=Local.HTML_BODY;
		int idx = table.getSelectedRow();
		if(idx >  Joueur.universe.classList.size() || idx < 0) 
			{idx = 0;}
		int nbline = 3;
		if (idx >= 0)
		{
			ClassRPG theClass = Joueur.universe.classList.get(idx);
			res+= "<h1>"+theClass.name+"</h1>";
			res+= "Augmenté : ";
			res+= "<ul>";
			for(int i=0; i<Joueur.nb_stats; i++)
				if(theClass.bonus[i] > 0)
				{res+= "<li>" + Local.SKILLS_NAME[i]; nbline++;}
			res+= "</ul>";
			res+= "Réduit : ";
			res+= "<ul>";
			for(int i=0; i<Joueur.nb_stats; i++)
				if(theClass.malus[i] > 0)
				{res+= "<li>" + Local.SKILLS_NAME[i]; nbline++;}
			res+= "</ul>";
		}
		infos.setText(res+Local.BODY_HTML_END);
	}
	
    public void refresh()
    {
		if(mustRefreshInfos == true)
		{
			try{refreshInfos();}
			catch (Exception e){System.out.println("Refresh problem in ClassWindow: " + e);}
		}
		table.repaint();
    }
	
	public void updateClassTable()
	{
		int idx = 0;
		tableModel.clear();
	    for (ClassRPG cls : Joueur.universe.classList)
		{
			String[] line= new String[1];
			if (Joueur.current_class == idx)
				line[0] = "<html><font color=\"blue\">(" + cls.name + ")</font></html>";
			else
				line[0] = cls.name;
			tableModel.addRow(line);
			idx++;
		}
	}
	
    private javax.swing.JPanel getJFrameContentPane() 
	{
	if (ivjJFrameContentPane == null) {
		String[] colums = {Local.CLASS};
	    tableModel = new MyTableModel(new String[0][0],colums);
		updateClassTable();

	    table = new JTable(tableModel);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setRowSelectionInterval(0,0);
		
	    javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		    public void valueChanged(javax.swing.event.ListSelectionEvent evt) 
			{if (!evt.getValueIsAdjusting()) {mustRefreshInfos=true; refresh();}}}; 
				
	    table.getSelectionModel().addListSelectionListener(refresher);

	    scroll = new JScrollPane(table);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setBounds(new Rectangle(10, 5, 250, 495));

		infos = new JEditorPane();
	    infos.setEditable(false);
		infos.setContentType("text/html");
		infos.setBackground(scroll.getBackground());
		infos.setSelectionColor(infos.getBackground());
		
	    scroll2 = new JScrollPane(infos);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll2.setBounds(new Rectangle(250+10+10, 5, 510, 520));

	    DefaultCaret caret = (DefaultCaret)infos.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
	    choisir = new JButton();
	    choisir.setBounds(new Rectangle(10, 495+10, 80, 21));
	    choisir.setText(Local.CHOOSE);
	    choisir.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				int idx = table.getSelectedRow();
				selectClass(idx);
		    }
		});
	    choisir.setMnemonic('c');
		
	    table.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 12));

	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

		ivjJFrameContentPane.add(scroll2);
	    ivjJFrameContentPane.add(scroll);
	    ivjJFrameContentPane.add(choisir);
	}
	return ivjJFrameContentPane;
    }
	
	private void selectClass(int idx)
	{
		if(idx < 0) return;
		Joueur.change_class(idx);
		updateClassTable();
		refresh();
		Game.MW.mustRefreshCharge=true;
	}
	
    private void initialize() {

	this.setLocation(new Point(5, 5));
	this.setSize(new Dimension(790+Game.ADJUST_SIZE_X, 560+Game.ADJUST_SIZE_Y));
	this.setResizable(false);
	this.setModal(true);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setTitle(Local.CHOICE_OF_CLASS);
	this.setContentPane(getJFrameContentPane());
    
	refresh();
    }
} 
