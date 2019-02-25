import java.io.Serializable;

class Material implements Serializable {

    public String name;
    public String nameOf;
    public int type;  // "déchet","tissu/cuir","métal","pierre précieuse","conceptuel","alliage"
    public double coeffPoids; // Plus lourd
    public double coeffPrix; // Plus cher
    public double coeffSolidite; // Plus solide
    public double coeffEfficacite; // Plus efficace (sur l'enchantement de base)
    public double coeffPuissance; // Plus haut ilvl (affecte les enchantements secondaires)

    public Material(String n, String no, int ty, double cpo, double cpr, double sol, double eff, double ilvl)
    {
	name=n;
	nameOf=no;
	type = ty;
	coeffPoids=cpo;
	coeffPrix=cpr;
	coeffSolidite=sol;
	coeffEfficacite=eff;
	coeffPuissance=ilvl;
    }

}