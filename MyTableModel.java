import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;

class MyTableModel extends AbstractTableModel {

	private ArrayList<String[]> rowData;
	private String[] columnNames;
	
	public MyTableModel(String[][] rd, String[] cn)
	{
	super();
	rowData = new  ArrayList<String[]>(Arrays.asList(rd));
	columnNames = cn;
	}
	
	public void clear() {rowData.clear();}
	
	public String getColumnName(int col) {return columnNames[col].toString();}

	public int getRowCount() { return rowData.size(); }

	public int getColumnCount() { return columnNames.length; }

	public Object getValueAt(int row, int col) {return rowData.get(row)[col];}

	public boolean isCellEditable(int row, int col) { return false; }
	
	public void addRow(String[] row) {rowData.add(row);}	

}
