import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultCaret;
import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EditUniverse extends javax.swing.JDialog  {
    private Player Joueur;
    private javax.swing.JPanel ivjJFrameContentPane = null;
	private boolean mustRefreshInfos = true;
	private boolean mustRefreshAfterZoom = true;
    private JButton moins;
    private JButton plus;
	private JButton equi;
    private JLabel prompt;
    private JScrollPane scroll;
    private String[][] rowData;
    private JTable table;
	private MyDraw drawSurf;
	private JEditorPane infos;
	private int zoom = 4;
	
	
	public static int[] zoomLevel = {5,10,20,50,100,200,500,1000,2000,5000,10000};

    public EditUniverse(Player J) {
	super();
	Joueur = J;
	initialize();
    }

	private ArrayList<CurveObject> compute_the_curves(int idx)
		{
			//System.out.println("compute_the_curves "+idx);
			ArrayList<CurveObject> res = new ArrayList<CurveObject>();
			double max_pt = zoomLevel[zoom];
			
			if(idx >= Joueur.universe.nb_universe_stats)
			{
			CurveObject curve_to_draw = new CurveObject();
			double sizar = curve_to_draw.arr_x.length;
			curve_to_draw.title =  Local.UNIVERSE_EQUATIONS_NAME[Joueur.universe.sortedEquations[idx-Joueur.universe.nb_universe_stats].id];
			for (int x = 0; x < sizar; x++)
				{
				double tx = ((double)x/(double)(sizar-1))*max_pt;
				
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.sortedEquations[idx-Joueur.universe.nb_universe_stats].eval(tx);
				}
			res.add(curve_to_draw);
			}
			if(idx < Joueur.universe.nb_universe_stats)
			{
				CurveObject curve_to_draw = new CurveObject();
				double sizar = curve_to_draw.arr_x.length;
				curve_to_draw.title =  Local.UNIVERSE_STATS_NAME[idx];
				for (int x = 0; x < sizar; x++)
				{
				double tx = ((double)x/(double)(sizar-1))*max_pt;
				
				curve_to_draw.arr_x[x] = tx;
				curve_to_draw.arr_y[x] = Joueur.universe.adjust_constant(tx,Joueur.universe.constantes[idx],Joueur.universe.constantes_min[idx], Joueur.universe.constantes_max[idx]);
				}
			res.add(curve_to_draw);
			}
			return res;
		}
		
	public void refreshAfterZoom()
	{
		drawSurf.repaint();
		ArrayList<CurveObject> curves_to_draw = compute_the_curves(table.getSelectedRow());
		drawSurf.draw_graph(curves_to_draw);		
	}
	
	public void zoom(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		if (notches < 0) 
		{
			zoom--;
			if(zoom < 0) zoom = 0;
		}
		else 
		{
			zoom++;
			if(zoom >= zoomLevel.length) zoom = zoomLevel.length-1;
		}
		mustRefreshAfterZoom = true;
	}
	
	public void SetPlayer(Player J)
	{
		Joueur = J;
		refresh();
	}

	public void dist()
    {
	mustRefreshInfos = true;
	refresh();
	this.setVisible(true);
    }
	
	private String infoHTML()
	{
		String res=Local.HTML_BODY;
		int idx = table.getSelectedRow();
		
		if (idx < Joueur.universe.nb_universe_stats)
		{
			int i = idx;
			res+=String.format("<p><b>Base : %.3f</b><br>",Joueur.universe.constantes[i]);
			res+=String.format("-10 points divins : %.3f<br>",Joueur.universe.adjust_constant(-10,Joueur.universe.constantes[i],Joueur.universe.constantes_min[i], Joueur.universe.constantes_max[i]));
			res+=String.format("-100 points divins : %.3f<br>",Joueur.universe.adjust_constant(-100,Joueur.universe.constantes[i],Joueur.universe.constantes_min[i], Joueur.universe.constantes_max[i]));
			res+=String.format("-1000 points divins : %.3f<br>",Joueur.universe.adjust_constant(-1000,Joueur.universe.constantes[i],Joueur.universe.constantes_min[i], Joueur.universe.constantes_max[i]));
			res+=String.format("-10000 points divins : %.3f<br>",Joueur.universe.adjust_constant(-10000,Joueur.universe.constantes[i],Joueur.universe.constantes_min[i], Joueur.universe.constantes_max[i]));
			res+=String.format("<p>10 points divins : %.3f<br>",Joueur.universe.adjust_constant(10,Joueur.universe.constantes[i],Joueur.universe.constantes_min[i], Joueur.universe.constantes_max[i]));
			res+=String.format("100 points divins : %.3f<br>",Joueur.universe.adjust_constant(100,Joueur.universe.constantes[i],Joueur.universe.constantes_min[i], Joueur.universe.constantes_max[i]));
			res+=String.format("1000 points divins : %.3f<br>",Joueur.universe.adjust_constant(1000,Joueur.universe.constantes[i],Joueur.universe.constantes_min[i], Joueur.universe.constantes_max[i]));
			res+=String.format("10000 points divins : %.3f<br>",Joueur.universe.adjust_constant(10000,Joueur.universe.constantes[i],Joueur.universe.constantes_min[i], Joueur.universe.constantes_max[i]));
		}
		else
		{
			int i = idx-Joueur.universe.nb_universe_stats;
			res+="<p><b>Base</b><br> E(x)=" + Joueur.universe.sortedEquations[i].dispHTML(false);
			res+=String.format("<br>E(10)=%.3f<br>E(100)=%.3f<br>E(10000)=%.3f",Joueur.universe.sortedEquations[i].evalStatic(10),Joueur.universe.sortedEquations[i].evalStatic(100),Joueur.universe.sortedEquations[i].evalStatic(10000));
			res+="<p><b>Ajustée</b><br> E(x)=" + Joueur.universe.sortedEquations[i].dispHTMLadj(false);
			res+=String.format("<br>E(10)=%.3f<br>E(100)=%.3f<br>E(10000)=%.3f",Joueur.universe.sortedEquations[i].eval(10),Joueur.universe.sortedEquations[i].eval(100),Joueur.universe.sortedEquations[i].eval(10000));
		}
		return res+Local.BODY_HTML_END;
	}
	
    public void refresh()
    {
	if(mustRefreshAfterZoom || mustRefreshInfos)
	{
		refreshAfterZoom();
		mustRefreshAfterZoom = false;
	}
	if(mustRefreshInfos)
	{
	mustRefreshInfos = false;
	try{infos.setText(infoHTML());}
	catch (Exception e){System.out.println("MustRefreshInfos problem: " + e);}
	
	prompt.setText(String.format(Local.DISTRIBUTION_OF_GOD_POINTS,Joueur.points_divins_a_distribuer()));
	for (int i=0; i< Joueur.universe.nb_universe_stats ; i++)
	    {
		rowData[i][1] = String.format("%.4f",Joueur.universe.constantes[i]);
		rowData[i][2] = String.format("%.4f",Joueur.universe.adjusted_constant(i));
		rowData[i][3] = String.format("%.0f",Joueur.universe.points_divins[i]);
		rowData[i][4] = String.format("%.1f",Joueur.divine_cap(i));
	    }
	for (int i=0; i< Joueur.universe.nb_universe_equations ; i++)
		{
		rowData[Joueur.universe.nb_universe_stats+i][1] = Joueur.universe.sortedEquations[i].dispHTML(true);
		rowData[Joueur.universe.nb_universe_stats+i][2] = Joueur.universe.sortedEquations[i].dispHTMLadj(true);
		rowData[Joueur.universe.nb_universe_stats+i][3] = 
					String.format("%.0f",Joueur.universe.points_divins[Joueur.universe.nb_universe_stats+i]);
		rowData[Joueur.universe.nb_universe_stats+i][4] = 
					String.format("%.1f",Joueur.divine_cap(Joueur.universe.nb_universe_stats+i));
		}
		
	table.repaint();
	double pts = Joueur.universe.points_divins[table.getSelectedRow()];
	double divine_cap = Joueur.divine_cap(table.getSelectedRow());
	plus.setEnabled(pts < 0 || Joueur.points_divins_a_distribuer() >= 1 && pts+1.0 <= divine_cap);
	moins.setEnabled(pts > 0 || Joueur.points_divins_a_distribuer() >= 1  && pts-1.0 >= -divine_cap);
	}
    }
	
	
    private javax.swing.JPanel getJFrameContentPane() {
	if (ivjJFrameContentPane == null) {
			
	    rowData = new String[Joueur.universe.nb_universe_stats+Joueur.universe.nb_universe_equations][5];
	    for (int i=0; i< Joueur.universe.nb_universe_stats ; i++)
		{
		    rowData[i][0] = Local.UNIVERSE_STATS_NAME[i];
		}
		for (int i=0; i< Joueur.universe.nb_universe_equations ; i++)
		{
		    rowData[Joueur.universe.nb_universe_stats+i][0] = Local.UNIVERSE_EQUATIONS_NAME[Joueur.universe.sortedEquations[i].id];
		}

	    AbstractTableModel mytm = new MyTableModel(rowData,Local.EDIT_UNIVERSE_COLUMN_NAMES);

	    table = new JTable(mytm);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setRowSelectionInterval(0,0);
	    table.getColumnModel().getColumn(0).setPreferredWidth(240);
	    table.getColumnModel().getColumn(1).setPreferredWidth(160);
	    table.getColumnModel().getColumn(2).setPreferredWidth(160);
	    table.getColumnModel().getColumn(3).setPreferredWidth(45);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		
	    javax.swing.event.ListSelectionListener refresher = new javax.swing.event.ListSelectionListener() {
		    public void valueChanged(javax.swing.event.ListSelectionEvent evt) 
			{if (!evt.getValueIsAdjusting()) {mustRefreshInfos=true; refresh();}}}; 
				
	    table.getSelectionModel().addListSelectionListener(refresher);
			
	    prompt = new JLabel();
	    prompt.setBounds(new Rectangle(10, 3, 400, 23));

	    scroll = new JScrollPane(table);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll.setBounds(new Rectangle(10, 32, 770, 190));

		drawSurf = new MyDraw();
		drawSurf.setBounds(new Rectangle(10, 256, 450, 300));

		drawSurf.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
				public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {zoom(e);
				}
		});

		infos = new JEditorPane();
	    infos.setEditable(false);
		infos.setContentType("text/html");
	    DefaultCaret caret = (DefaultCaret)infos.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		infos.setBackground(prompt.getBackground());
		infos.setSelectionColor(infos.getBackground());
		infos.setBounds(new Rectangle(465, 231, 312, 300+25));
		
	    plus = new JButton();
	    plus.setBounds(new Rectangle(10, 245-20, 44, 21));
	    plus.setText(Local.PLUS);
	    plus.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0) 
				{
					double step = Math.max(Math.pow(10.0,Math.floor(Math.log10(Joueur.points_divins_a_distribuer())))*0.5,5.0);
					plus(step);
				}
				else plus(1.0);
		    }
		});
	    plus.setMnemonic(KeyEvent.VK_ADD);

	    moins = new JButton();
	    moins.setBounds(new Rectangle(59, 245-20, 44, 21));
	    moins.setText(Local.MINUS);
	    moins.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)
				{
					double step = Math.max(Math.pow(10.0,Math.floor(Math.log10(Joueur.points_divins_a_distribuer())))*0.5,5.0);
					moins(step);
				}
				else moins(1.0);
		    }
		});
	    moins.setMnemonic(KeyEvent.VK_SUBTRACT);

		
		equi = new JButton();
	    equi.setBounds(new Rectangle(59+49, 245-20, 150, 21));
	    equi.setText(Local.AUTO_DISTRIBUTION);
	    equi.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				if((e.getModifiers() & InputEvent.SHIFT_MASK) != 0)
				{
				 equirepartir(true);
				}
				else equirepartir(false);
		    }
		});
		
	    prompt.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 14));
	    table.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 11));

	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

		ivjJFrameContentPane.add(infos);
	    ivjJFrameContentPane.add(scroll);
	    ivjJFrameContentPane.add(prompt);
	    ivjJFrameContentPane.add(plus);
	    ivjJFrameContentPane.add(moins);
		ivjJFrameContentPane.add(equi);
	}
	return ivjJFrameContentPane;
    }
	
    private void initialize() {

	this.setLocation(new Point(5, 5));
	this.setSize(new Dimension(790+Game.ADJUST_SIZE_X, 600+Game.ADJUST_SIZE_Y));
	this.setResizable(false);
	this.setModal(true);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setTitle(Local.UNIVERSE_EDITION);
	this.setContentPane(getJFrameContentPane());
	this.add(drawSurf);
    
	refresh();
    }
	
	private void updateMustRefresh(int idx)
	{
		Game.MW.mustRefreshCurves=true;
		if (idx == 1 || idx == 14 || idx == 21 || idx == 23 || idx == 24) Game.MW.mustRefreshMonsters=true;
		if (idx == 17) Game.MW.mustRefreshWeather=true;
		if(idx >= Joueur.universe.nb_universe_stats)
		{
			int tidx = Joueur.universe.sortedEquations[idx-Joueur.universe.nb_universe_stats].id;
			if(tidx == 65|| tidx == 66) Game.MW.mustRefreshCharge=true;
			if(tidx == 51|| tidx == 53) Game.MW.mustRefreshMonsters=true;
		}
	}
	
    private void plus(double x)
    {
	mustRefreshInfos = true;
	int idx = table.getSelectedRow();
	updateMustRefresh(idx);
	double can_be_dist = Joueur.points_divins_a_distribuer();
	if(Joueur.universe.points_divins[idx]<0) can_be_dist -= Joueur.universe.points_divins[idx];
	double cap = Math.min(Joueur.divine_cap(idx)-Joueur.universe.points_divins[idx],can_be_dist);
    double to_dist = Math.floor(Math.min(cap,x));
	Joueur.universe.points_divins[idx] += to_dist; 
	int eidx = idx-Joueur.universe.nb_universe_stats;
	if (eidx >= 0) Joueur.universe.sortedEquations[eidx].update(Joueur.universe.points_divins[idx]);
    }

    private void moins(double x)
    {
	mustRefreshInfos = true;
	int idx = table.getSelectedRow();
	updateMustRefresh(idx);
	double can_be_dist = Joueur.points_divins_a_distribuer();
	if(Joueur.universe.points_divins[idx]>0) can_be_dist += Joueur.universe.points_divins[idx];
    double cap = Math.min(Joueur.divine_cap(idx)+Joueur.universe.points_divins[idx],can_be_dist);
	double to_dist = Math.floor(Math.min(cap,x));
	Joueur.universe.points_divins[idx] -= to_dist; 
	int eidx = idx-Joueur.universe.nb_universe_stats;
	if (eidx >= 0) Joueur.universe.sortedEquations[eidx].update(Joueur.universe.points_divins[idx]);
    }

	private void equirepartir(boolean fullauto)
	{
		mustRefreshInfos = true;
		Game.MW.mustRefreshCurves=true;
		Game.MW.mustRefreshMonsters=true;
		Game.MW.mustRefreshWeather=true;
		Game.MW.mustRefreshCharge=true;
		
		for (int idx=0; idx< Joueur.universe.nb_universe_stats+Joueur.universe.nb_universe_equations ; idx++)
	    {
			double can_be_dist = Joueur.points_divins_a_distribuer();
			int eidx = idx-Joueur.universe.nb_universe_stats;
			if (Joueur.universe.points_divins[idx] > 0.01)
			{
				double cap = Math.min(Joueur.divine_cap(idx)-Joueur.universe.points_divins[idx],can_be_dist);
				double to_dist = Math.floor(cap);
				Joueur.universe.points_divins[idx] += to_dist; 
			}
			else if (Joueur.universe.points_divins[idx] < -0.01)
			{
				double cap = Math.min(Joueur.divine_cap(idx)+Joueur.universe.points_divins[idx],can_be_dist);
				double to_dist = Math.floor(cap);
				Joueur.universe.points_divins[idx] -= to_dist; 
			}
			if (Math.abs(Joueur.universe.points_divins[idx]) < 0.01 && eidx >=0 && fullauto)
			{
				double cap = Math.min(Joueur.divine_cap(idx)+Joueur.universe.points_divins[idx],can_be_dist);
				double to_dist = Math.floor(cap);
				int auto_dist_strategy = Joueur.universe.sortedEquations[eidx].auto_dist_strategy;
				if(auto_dist_strategy == 1) Joueur.universe.points_divins[idx] += to_dist;
				if(auto_dist_strategy == 0) Joueur.universe.points_divins[idx] -= to_dist;
			}

			if (eidx >= 0) Joueur.universe.sortedEquations[eidx].update(Joueur.universe.points_divins[idx]);
	    }
	}
} 
