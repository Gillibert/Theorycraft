import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.util.ArrayList;
import java.awt.event.*;

public class InventoryWindow extends javax.swing.JDialog  {
    private boolean craft_mode;
    private Player Joueur;
    private javax.swing.JPanel ivjJFrameContentPane = null;

    private JLabel prompt;
    private JList liste_s;
    private JList liste_it;
    private JList liste_shop;
    private	JButton	toggle;
    private JButton	sell;
    private	JButton	buy;
    private	JButton	put;
    private	JButton	get;
    private	JButton	forge;
    private	JButton autoc;
    private	JButton	split;
    private	JButton	destroy;
    private JTextArea infos;
    private JScrollPane scroll0;
    private JScrollPane scroll1;
    private JScrollPane scroll2;
    private JScrollPane scroll3;

    private DefaultListModel filter_list;
    private DefaultListModel obj_list;
    private DefaultListModel shop_list;
    public ArrayList<ObjectRule> rules;

    private int idx_tmp;
    private int idx_tmp2;
    private int idx_tmp3;
    private ArrayList<Item> vlist;
	private boolean refreshing = false;
	
    public InventoryWindow(Player J) {
	super();
	Joueur = J;
	initialize();
    }

	public void SetPlayer(Player J)
	{
		Joueur = J;
		idx_tmp = -2; // force le refresh complet
		refresh(0);
	}
	
    public void montre_inv()
    {
	craft_mode = false;
	this.setTitle(Local.WINDOW_INVENTORY);
	this.setSize(new Dimension(600+10, 420));
	sell.setVisible(false);
	buy.setVisible(false);
	put.setVisible(false);
	get.setVisible(false);
	forge.setVisible(false);
	autoc.setVisible(false);
	scroll2.setVisible(false);
	destroy.setVisible(true);
	buy.repaint();
	sell.repaint();
	scroll2.repaint();
	put.repaint();
	get.repaint();
	forge.repaint();
	autoc.repaint();
	
	idx_tmp = -2;
	refresh_filter_list();
	refresh(0);
	setVisible(true);
    }

    public void montre_shop()
    {
	craft_mode = false;
	this.setTitle(String.format(Local.WINDOW_SHOP, Joueur.shop.name, Joueur.shop.level));
	this.setSize(new Dimension(805, 420));
	sell.setVisible(true);
	buy.setVisible(true);
	put.setVisible(false);
	get.setVisible(false);
	scroll2.setVisible(true);
	forge.setVisible(false);
	autoc.setVisible(false);
	destroy.setVisible(false);
	buy.repaint();
	sell.repaint();
	scroll2.repaint();
	put.repaint();
	get.repaint();
	forge.repaint();
	autoc.repaint();
	
	idx_tmp = -2;
	refresh_filter_list();
	refresh(0);
	setVisible(true);
    }

    public void montre_craft()
    {
    craft_mode = true;
	this.setTitle(Local.WINDOW_FORGE);
	this.setSize(new Dimension(805, 420));
	sell.setVisible(false);
	buy.setVisible(false);
	scroll2.setVisible(true);
	put.setVisible(true);
	get.setVisible(true);
	forge.setVisible(true);
	autoc.setVisible(true);
	destroy.setVisible(true);
	buy.repaint();
	sell.repaint();
	scroll2.repaint();
	put.repaint();
	get.repaint();
	forge.repaint();
	autoc.repaint();

	idx_tmp = -2;
	refresh_filter_list();
	refresh(0);
	setVisible(true);
    }

    public void refresh(int sli)
    {
	if(refreshing==true) return;
	refreshing=true;
	if((liste_it.getSelectedIndex() != -1) && sli==0)
	{sli=1;}
	if((liste_shop.getSelectedIndex() != -1) && sli==0)
	{sli=2;}
	
	prompt.setText(String.format(Local.WINDOW_INVENTORY_PROMPT,Joueur.money,Joueur.charge,Joueur.charge_max()));
	toggle.setEnabled(false);
	sell.setEnabled(false);
	buy.setEnabled(false);
	split.setEnabled(false);
	put.setEnabled(false);
	get.setEnabled(false);
	destroy.setEnabled(false);
	
	infos.setText("");
	forge.setEnabled(!Joueur.craftInventory.isEmpty());

	if(idx_tmp != liste_s.getSelectedIndex()) // Pour éviter de tout recalculer
	    {
		idx_tmp = liste_s.getSelectedIndex();
		idx_tmp2 = liste_it.getSelectedIndex();
		idx_tmp3 = liste_shop.getSelectedIndex();
		obj_list.clear();
		shop_list.clear();
		vlist.clear();
		if(craft_mode)
		    {
			for(Item the_object : Joueur.craftInventory)
			    shop_list.addElement(the_object.name);
			int x = Joueur.craftInventory.size()-1;
			if(x > -1)
			    liste_shop.setSelectedIndex(Math.min(idx_tmp3,x));
		    }		    
		else if(Joueur.shop != null)
		    {
			for(Item the_object : Joueur.shop.inventory)
			    shop_list.addElement(the_object.name);
			int x = Joueur.shop.inventory.size()-1;
			if(x > -1)
			    liste_shop.setSelectedIndex(Math.min(idx_tmp3,x));
		    }
		ObjectRule therule = null;
		if (liste_s.getSelectedIndex() > 0)
			therule = rules.get(liste_s.getSelectedIndex());
		for(Item the_object : Joueur.inventory)
			{
		    	if(liste_s.getSelectedIndex()<=0 || therule.IsTrue(Joueur,the_object,null))
				{
			    	if(the_object.equiped)
						obj_list.addElement("<html><font color=\"#0000ee\">"+the_object.name+"</font></html>");
			    	else
						obj_list.addElement(the_object.name);
			    	vlist.add(the_object);
				}
			}
		int x = vlist.size()-1;
		if(x > -1)
		    liste_it.setSelectedIndex(Math.min(idx_tmp2,x));
	    
		}
	if(liste_it.getSelectedIndex() != -1)
	    split.setEnabled(vlist.get(liste_it.getSelectedIndex()).stackable);
	if((liste_it.getSelectedIndex() != -1) && sli == 1)
	    {
		liste_shop.clearSelection();
		Item sel_it = vlist.get(liste_it.getSelectedIndex());
		display_item(sel_it);
		if(!sel_it.equiped)
		    toggle.setText(Local.EQUIP);
		else
		    toggle.setText(Local.UNEQUIP);
		toggle.setEnabled(sel_it.can_be_equiped() || sel_it.equiped);

		if(craft_mode)
		    {
			put.setEnabled(true);
			get.setEnabled(false);
		    }
		else
		    {
			sell.setEnabled(true);
			buy.setEnabled(false);
		    }
		destroy.setEnabled(true);
	    }

	if((liste_shop.getSelectedIndex() != -1) && sli == 2)
	    {
		liste_it.clearSelection();
		Item buy_it;
		if(craft_mode)
		    {
			buy_it = Joueur.craftInventory.get(liste_shop.getSelectedIndex());
			put.setEnabled(false);
			get.setEnabled(true);
    
		    }
		else 
		    {
			buy_it = Joueur.shop.inventory.get(liste_shop.getSelectedIndex());
			sell.setEnabled(false);
			buy.setEnabled(Joueur.can_buy(buy_it));
		    }
		display_item(buy_it);
		toggle.setEnabled(false);
		destroy.setEnabled(true);
	    }
		
	if(Joueur.shop != null && !craft_mode && liste_shop.getSelectedIndex() != -1)
	{
	    split.setEnabled(Joueur.shop.inventory.get(liste_shop.getSelectedIndex()).stackable);
	}
	refreshing=false;
    }
	
    private void display_item(Item it)
    {
	infos.setText(it.item_description(Joueur));
	if(it.rare == 0 || it.rare == 4)
	    infos.setForeground(Color.black);
	else if(it.rare == 1)
	    infos.setForeground(new Color(0, 0, 150));
	else if(it.rare == 2)
	    infos.setForeground(new Color(160, 80, 0));
	else if(it.rare == 3)
	    infos.setForeground(new Color(80, 0, 160));
	else if(it.rare == 5)
	    infos.setForeground(new Color(0, 100, 0));
	else if(it.rare == 6)
	    infos.setForeground(new Color(130, 0, 0));
	else
	    infos.setForeground(Color.black);
    }

	private void refresh_filter_list()
	{
		rules.clear();
		filter_list.clear();
	    for (ObjectRule r: Joueur.rules)
			if(r.filter_rule)
				{
				rules.add(r);
				filter_list.addElement(r.name);
				}
	}
	
    private javax.swing.JPanel getJFrameContentPane() {
	if (ivjJFrameContentPane == null) {
	    idx_tmp = 0;
	    craft_mode = false;
	    prompt = new JLabel();
	    prompt.setBounds(new Rectangle(10, 3, 600, 23));
	    prompt.setFont(new Font(Local.FONT_DIALOG, Font.BOLD, 14));

	    vlist = new ArrayList<Item>();
	    filter_list = new DefaultListModel();
	    obj_list = new DefaultListModel();
	    shop_list = new DefaultListModel();

		rules = new ArrayList<ObjectRule>();
		refresh_filter_list();
		
	    if(Joueur.shop != null)
		for(Item the_object : Joueur.shop.inventory)
		    shop_list.addElement(the_object.name);

	    javax.swing.event.ListSelectionListener list_ref = new javax.swing.event.ListSelectionListener() {
		    public void valueChanged(javax.swing.event.ListSelectionEvent e) {if(!e.getValueIsAdjusting()) refresh(1);}};

	    javax.swing.event.ListSelectionListener list_ref2 = new javax.swing.event.ListSelectionListener() {
		    public void valueChanged(javax.swing.event.ListSelectionEvent e) {if(!e.getValueIsAdjusting()) refresh(2);}};


	    liste_s = new JList(filter_list);
	    liste_s.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	    liste_s.addListSelectionListener(list_ref);

	    scroll0 = new JScrollPane(liste_s);
	    scroll0.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll0.setBounds(new Rectangle(10, 28, 125, 324));

	    liste_it = new JList(obj_list);
		liste_it.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	    liste_it.addListSelectionListener(list_ref);

	    scroll1 = new JScrollPane(liste_it);
	    scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll1.setBounds(new Rectangle(140, 28, 200-10, 324));

	    liste_shop = new JList(shop_list);
		liste_shop.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	    liste_shop.addListSelectionListener(list_ref2);

	    scroll2 = new JScrollPane(liste_shop);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll2.setBounds(new Rectangle(595+10, 28, 200-10, 324));


	    infos = new JTextArea();
	    infos.setEditable(false);
		DefaultCaret caret = (DefaultCaret)infos.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
	    scroll3 = new JScrollPane(infos);
	    scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll3.setBounds(new Rectangle(345-10, 28, 245+20, 324));
	
	    toggle = new JButton();
	    toggle.setBounds(new Rectangle(10, 365, 100, 21));
	    toggle.setText(Local.EQUIP);
	    toggle.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {toggle();
		    }
		});
	    toggle.setMnemonic('e');

	    split = new JButton();
	    split.setBounds(new Rectangle(10+105, 365, 100, 21));
	    split.setText(Local.SPLIT);
	    split.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {separer();
		    }
		});
	    split.setMnemonic('s');
			
		destroy = new JButton();
	    destroy.setBounds(new Rectangle(10+2*105, 365, 100, 21));
		destroy.setBackground(Color.RED);
	    destroy.setText(Local.DISCARD);
	    destroy.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {jeter();
		    }
		});
	    destroy.setMnemonic('j');

	    buy = new JButton();
	    buy.setBounds(new Rectangle(10+3*105, 365, 100, 21));
	    buy.setText(Local.BUY);
	    buy.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {acheter();
		    }
		});
	    buy.setMnemonic('a');
			
	    put = new JButton();
	    put.setBounds(new Rectangle(10+3*105, 365, 100, 21));
	    put.setText(Local.DEPOSIT);
	    put.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				deposer((e.getModifiers() & InputEvent.SHIFT_MASK) != 0);
		    }
		});
	    put.setMnemonic('d');

		
	    sell = new JButton();
	    sell.setBounds(new Rectangle(10+4*105, 365, 100, 21));
	    sell.setText(Local.SALE);
	    sell.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				vendre((e.getModifiers() & InputEvent.SHIFT_MASK) != 0);
		    }
		});
	    sell.setMnemonic('d');
		
	    get = new JButton();
	    get.setBounds(new Rectangle(10+4*105, 365, 100, 21));
	    get.setText(Local.PICK_UP);
	    get.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				prendre((e.getModifiers() & InputEvent.SHIFT_MASK) != 0);
		    }
		});
	    get.setMnemonic('a');

		autoc = new JButton();
	    autoc.setBounds(new Rectangle(535+50, 365, 100, 21));
	    autoc.setText(Local.AUTO_CRAFT);
	    autoc.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				Joueur.craftAuto();
				idx_tmp = -2; // force le refresh complet
				refresh(0);
		    }
		});
		
	    forge = new JButton();
	    forge.setBounds(new Rectangle(535+50+105, 365, 100, 21));
	    forge.setText(Local.COMBINE);
	    forge.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				combiner();
		    }
		});
	    forge.setMnemonic('c');
		

	    prompt.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 16));
	    liste_s.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
	    liste_it.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 11));
	    infos.setFont(new Font(Local.FONT_TIMES, Font.PLAIN, 11));
	    liste_shop.setFont(new Font(Local.FONT_TIMES, Font.BOLD, 11));

	    ivjJFrameContentPane = new javax.swing.JPanel();
	    ivjJFrameContentPane.setLayout(null);

	    ivjJFrameContentPane.add(scroll0);
	    ivjJFrameContentPane.add(scroll1);
	    ivjJFrameContentPane.add(scroll2);
	    ivjJFrameContentPane.add(scroll3);
	    ivjJFrameContentPane.add(prompt);
	    ivjJFrameContentPane.add(split);
	    ivjJFrameContentPane.add(toggle);
	    ivjJFrameContentPane.add(sell);
	    ivjJFrameContentPane.add(buy);
	    ivjJFrameContentPane.add(put);
	    ivjJFrameContentPane.add(get);
	    ivjJFrameContentPane.add(forge);
	    ivjJFrameContentPane.add(autoc);
	    ivjJFrameContentPane.add(destroy);
	}
	return ivjJFrameContentPane;
    }

	private void close()
		{this.setVisible(false);}

    private void initialize() {

	this.setLocation(new Point(15, 15));
	this.setResizable(false);
	this.setModal(true);
	this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.setContentPane(getJFrameContentPane());

    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
    
	getRootPane().getActionMap().put("Cancel", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {close();}});

    }

    public void toggle()
    {
	Item it = vlist.get(liste_it.getSelectedIndex());
	if(it.equiped)
	    Joueur.remove_item(vlist.get(liste_it.getSelectedIndex()));
	else
	    Joueur.put_item(vlist.get(liste_it.getSelectedIndex()));
	idx_tmp = -2; // force le refresh complet
	refresh(0);
    }

    private void vendre(boolean tout)
    {
	if(tout)
	{
		Joueur.sell(vlist);
	}
	else
	{
		Joueur.sell(vlist.get(liste_it.getSelectedIndex()));
	}
	idx_tmp = -2; // force le refresh complet
	refresh(0);
    }

    private void acheter()
    {
    Joueur.buy(Joueur.shop.inventory.get(liste_shop.getSelectedIndex()));
	idx_tmp = -2; // force le refresh complet
	refresh(0);
    }

    private void separer()
    {
	idx_tmp = -2; // force le refresh complet
	if(liste_it.getSelectedIndex() != -1)
	{
		Joueur.split(vlist.get(liste_it.getSelectedIndex()),Joueur.inventory);
		refresh(0);
	}
	if(Joueur.shop != null && !craft_mode && liste_shop.getSelectedIndex() != -1)
	{
		Joueur.split(Joueur.shop.inventory.get(liste_shop.getSelectedIndex()),Joueur.shop.inventory);
		refresh(0);
	}
    }

	private void jeter()
	{
	if(liste_it.getSelectedIndex() != -1)
	{
		Joueur.destroy_item(vlist.get(liste_it.getSelectedIndex()));
	}
	else
	{
		Joueur.craftInventory.remove(Joueur.craftInventory.get(liste_shop.getSelectedIndex()));
	}
	idx_tmp = -2; // force le refresh complet
	refresh(0);
    }

    private void deposer(boolean tout)
    {
	if(tout)
	{
		Joueur.put_craft(vlist);
	}
	else
	{
		Joueur.put_craft(vlist.get(liste_it.getSelectedIndex()));
	}
	idx_tmp = -2; // force le refresh complet
	refresh(0);
    }

    private void prendre(boolean tout)
    {
	if(tout)
	{
		int size = Joueur.craftInventory.size();
		for(int i=0; i< size; i++)
			Joueur.get_craft(Joueur.craftInventory.get(0));
	}
	else
	{
		Joueur.get_craft(Joueur.craftInventory.get(liste_shop.getSelectedIndex()));
	}
	idx_tmp = -2; // force le refresh complet
	refresh(0);
    }

    private void combiner()
    {
	Joueur.craft();
	idx_tmp = -2; // force le refresh complet
	refresh(0);
    }
} 
