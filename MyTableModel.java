import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel {

	private String[][] rowData;
	private String[] columnNames;
	
	public MyTableModel(String[][] rd, String[] cn)
	{
	super();
	rowData = rd;
	columnNames = cn;
	}

	public String getColumnName(int col) {return columnNames[col].toString();}

	public int getRowCount() { return rowData.length; }

	public int getColumnCount() { return columnNames.length; }

	public Object getValueAt(int row, int col) {return rowData[row][col];}

	public boolean isCellEditable(int row, int col) { return false; }

}
