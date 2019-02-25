import java.io.Serializable;

class BaseItem implements Serializable {

public String name;
public String genre; // MS, MP, FS, FP
public int mat; // 0 rien, 1 tissu/cuir, 2 métal, 3 pierre précieuse
public int pos; // l'emplacement sur le corps
public int base_bonus; // le numéro du bonus par défaut de l'objet
public int pref_level; // le niveau de l'objet
public double pref_poids; // le poids de base de l'objet

public BaseItem(String n, String g, int m, int p, int b, int level, double poid)
{
name = n;
genre = g;
mat = m;
pos = p;
base_bonus = b;
pref_level = level;
pref_poids = poid;
}

}
