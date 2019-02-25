import java.util.ArrayList;
import java.io.Serializable;

// Classe idiote parceque java est idiot et ne veut pas manipuler un ArrayList<Item> comme un objet normal
class ItemSet implements Serializable {
    public ArrayList<Item> list;
    public ItemSet()
    {list= new ArrayList<Item>();}
}