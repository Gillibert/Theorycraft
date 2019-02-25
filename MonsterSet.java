import java.util.ArrayList;
import java.io.Serializable;

// Classe idiote parceque java est idiot et ne veut pas manipuler un ArrayList<Monster> comme un objet normal
class MonsterSet implements Serializable {
    public ArrayList<Monster> list;
    public MonsterSet()
    {list= new ArrayList<Monster>();}
}