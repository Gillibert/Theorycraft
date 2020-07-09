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
    private String[][] rowData;
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
	
	private String infoHTML()
	{
		String res=Local.HTML_BODY;
		int idx = table.getSelectedRow();
		
		if (idx >= 0)
		{
			ClassRPG theClass = Joueur.universe.classList.get(idx);
			res+= "<h1>"+theClass.name+"</h1>";
			res+= "Augmenté : ";
			res+= "<ul>";
			for(int i=0; i<Joueur.nb_stats; i++)
				if(theClass.bonus[i] > 0)
					res+= "<li>" + Local.SKILLS_NAME[i];
			res+= "</ul>";
			res+= "Réduit : ";
			res+= "<ul>";
			for(int i=0; i<Joueur.nb_stats; i++)
				if(theClass.malus[i] > 0)
					res+= "<li>" + Local.SKILLS_NAME[i];
			res+= "</ul>";
		}
		return res+Local.BODY_HTML_END;
	}
	
    public void refresh()
    {
		if(mustRefreshInfos == true)
		{
			try{infos.setText(infoHTML());}
			catch (Exception e){System.out.println("Refresh problem in ClassWindow: " + e);}
		}
		table.repaint();
    }
	
	private void updateClassTable()
	{
		int idx = 0;
	    for (ClassRPG cls : Joueur.universe.classList)
		{
			if (Joueur.current_class == idx)
				rowData[idx][0] = "<html><font color=\"blue\">(" + cls.name + ")</font></html>";
			else
				rowData[idx][0] =  cls.name;
			idx++;
		}
	}
	
    private javax.swing.JPanel getJFrameContentPane() {
	if (ivjJFrameContentPane == null) {
			
	    rowData = new String[Joueur.universe.classList.size()][2];
		
		updateClassTable();
		
		String[] colums = {Local.CLASS};
	    AbstractTableModel mytm = new MyTableModel(rowData,colums);

	    table = new JTable(mytm);
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
	    DefaultCaret caret = (DefaultCaret)infos.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		infos.setBackground(scroll.getBackground());
		infos.setSelectionColor(infos.getBackground());
		infos.setBounds(new Rectangle(250+10+10, 5, 510, 520));
		
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
		
	    table.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 11));

	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

		ivjJFrameContentPane.add(infos);
	    ivjJFrameContentPane.add(scroll);
	    ivjJFrameContentPane.add(choisir);
	}
	return ivjJFrameContentPane;
    }
	
	private void selectClass(int idx)
	{
		if(idx < 0) return;
		ClassRPG cls = Joueur.universe.classList.get(idx);
		Joueur.current_class = idx;
		updateClassTable();
		refresh();
		Joueur.refresh_stats_with_bonus();
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
