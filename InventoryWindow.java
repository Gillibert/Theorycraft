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

    public InventoryWindow(Player JJ) {
	super();
	Joueur = JJ;
	initialize();
    }

    public void montre_inv()
    {
	craft_mode = false;
	this.setTitle("Inventaire");
	this.setSize(new Dimension(570+20, 420));
	sell.setVisible(false);
	buy.setVisible(false);
	put.setVisible(false);
	get.setVisible(false);
	forge.setVisible(false);
	scroll2.setVisible(false);
	destroy.setVisible(true);
	buy.repaint();
	sell.repaint();
	scroll2.repaint();
	put.repaint();
	get.repaint();
	forge.repaint();

	idx_tmp = -2;
	refresh_filter_list();
	refresh(1);
	setVisible(true);
    }

    public void montre_shop()
    {
	craft_mode = false;
	this.setTitle("Boutique de " + Joueur.shop.name);
	this.setSize(new Dimension(785+20, 420));
	sell.setVisible(true);
	buy.setVisible(true);
	put.setVisible(false);
	get.setVisible(false);
	scroll2.setVisible(true);
	forge.setVisible(false);
	destroy.setVisible(false);
	buy.repaint();
	sell.repaint();
	scroll2.repaint();
	put.repaint();
	get.repaint();
	forge.repaint();

	idx_tmp = -2;
	refresh_filter_list();
	refresh(1);
	setVisible(true);
    }

    public void montre_craft()
    {
    craft_mode = true;
	this.setTitle("Forge mystique");
	this.setSize(new Dimension(785+20, 420));
	sell.setVisible(false);
	buy.setVisible(false);
	scroll2.setVisible(true);
	put.setVisible(true);
	get.setVisible(true);
	forge.setVisible(true);
	destroy.setVisible(true);
	buy.repaint();
	sell.repaint();
	scroll2.repaint();
	put.repaint();
	get.repaint();
	forge.repaint();

	idx_tmp = -2;
	refresh_filter_list();
	refresh(1);
	setVisible(true);
    }

    public void refresh(int sli)
    {
	prompt.setText(String.format("Or : %.2f écus, charge : %.2f kg (%.2f kg max)",Joueur.money,Joueur.charge,Joueur.charge_max()));
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
						obj_list.addElement("<html><i>"+the_object.name+"</i></html>");
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
		    toggle.setText("Équiper");
		else
		    toggle.setText("Déséquiper");
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
			get.setEnabled(Joueur.can_get(buy_it));
    
		    }
		else 
		    {
			buy_it = Joueur.shop.inventory.get(liste_shop.getSelectedIndex());
			sell.setEnabled(false);
			buy.setEnabled(Joueur.can_buy(buy_it));
		    }
		display_item(buy_it);
		toggle.setEnabled(false);
	    }
    }
	
    private void display_item(Item it)
    {
	infos.setText(it.item_description(Joueur));
	if(it.rare == 0 || it.rare == 4)
	    infos.setForeground(Color.black);	
	else if(it.rare == 1)
	    infos.setForeground(new Color(0, 0, 150));
	else if(it.rare == 2)
	    infos.setForeground(new Color(190, 140, 0));
	else if(it.rare == 3)
	    infos.setForeground(new Color(80, 0, 160));
	else if(it.rare == 5)
	    infos.setForeground(new Color(50, 100, 50));
	else if(it.rare == 6)
	    infos.setForeground(new Color(0, 100, 0));
	else if(it.rare == 7)
	    infos.setForeground(new Color(20, 100, 20));
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
	    prompt.setFont(new Font("Dialog", Font.BOLD, 14));

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
	    scroll1.setBounds(new Rectangle(140, 28, 210, 324));

	    liste_shop = new JList(shop_list);
		liste_shop.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	    liste_shop.addListSelectionListener(list_ref2);

	    scroll2 = new JScrollPane(liste_shop);
	    scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll2.setBounds(new Rectangle(565+20, 28, 210, 324));


	    infos = new JTextArea();
	    infos.setEditable(false);
		DefaultCaret caret = (DefaultCaret)infos.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
	    scroll3 = new JScrollPane(infos);
	    scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scroll3.setBounds(new Rectangle(355, 28, 225, 324));
	
	    toggle = new JButton();
	    toggle.setBounds(new Rectangle(10, 365, 110, 21));
	    toggle.setText("Équiper");
	    toggle.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {toggle();
		    }
		});
	    toggle.setMnemonic('e');

	    split = new JButton();
	    split.setBounds(new Rectangle(10+115, 365, 110, 21));
	    split.setText("Séparer");
	    split.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {separer();
		    }
		});
	    split.setMnemonic('s');
			
		destroy = new JButton();
	    destroy.setBounds(new Rectangle(10+115+115, 365, 110, 21));
		destroy.setBackground(Color.RED);
	    destroy.setText("Jeter");
	    destroy.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {jeter();
		    }
		});
	    destroy.setMnemonic('j');

	    buy = new JButton();
	    buy.setBounds(new Rectangle(360, 365, 110, 21));
	    buy.setText("Acheter");
	    buy.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {acheter();
		    }
		});
	    buy.setMnemonic('a');

	    sell = new JButton();
	    sell.setBounds(new Rectangle(360+115, 365, 110, 21));
	    sell.setText("Vendre");
	    sell.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				vendre((e.getModifiers() & InputEvent.SHIFT_MASK) != 0);
		    }
		});
	    sell.setMnemonic('d');
			
	    put = new JButton();
	    put.setBounds(new Rectangle(360, 365, 110, 21));
	    put.setText("Déposer");
	    put.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				deposer((e.getModifiers() & InputEvent.SHIFT_MASK) != 0);
		    }
		});
	    put.setMnemonic('d');

	    get = new JButton();
	    get.setBounds(new Rectangle(475, 365, 110, 21));
	    get.setText("Ramasser");
	    get.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				prendre((e.getModifiers() & InputEvent.SHIFT_MASK) != 0);
		    }
		});
	    get.setMnemonic('a');

	    forge = new JButton();
	    forge.setBounds(new Rectangle(475+115, 365, 110, 21));
	    forge.setText("Combiner");
	    forge.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent e) {
				combiner();
		    }
		});
	    forge.setMnemonic('c');

	    prompt.setFont(new Font("Times Roman", Font.BOLD, 16));
	    liste_s.setFont(new Font("Times Roman", Font.PLAIN, 11));
	    liste_it.setFont(new Font("Times Roman", Font.BOLD, 11));
	    infos.setFont(new Font("Times Roman", Font.PLAIN, 11));
	    liste_shop.setFont(new Font("Times Roman", Font.BOLD, 11));

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
	refresh(1);
    }

    private void vendre(boolean tout)
    {
	if(tout)
	{
		int size = vlist.size();
		for(int i=0; i< size; i++)
			Joueur.sell(vlist.get(i));
	}
	else
	{
		Joueur.sell(vlist.get(liste_it.getSelectedIndex()));
	}
	idx_tmp = -2; // force le refresh complet
	refresh(1);
    }

    private void acheter()
    {
    Joueur.buy(Joueur.shop.inventory.get(liste_shop.getSelectedIndex()));
	idx_tmp = -2; // force le refresh complet
	refresh(2);
    }

    private void separer()
    {
	Joueur.split(vlist.get(liste_it.getSelectedIndex()));
	idx_tmp = -2; // force le refresh complet
	refresh(1);
    }

	private void jeter()
	{
    Joueur.destroy_item(vlist.get(liste_it.getSelectedIndex()));
	idx_tmp = -2; // force le refresh complet
	refresh(1);
    }

    private void deposer(boolean tout)
    {
	if(tout)
	{
		int size = vlist.size();
		for(int i=0; i< size; i++)
			Joueur.put_craft(vlist.get(i));
	}
	else
	{
		Joueur.put_craft(vlist.get(liste_it.getSelectedIndex()));
	}
	idx_tmp = -2; // force le refresh complet
	refresh(1);
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
	refresh(2);
    }

    private void combiner()
    {
	Joueur.craft();
	idx_tmp = -2; // force le refresh complet
	refresh(2);
    }
} 
