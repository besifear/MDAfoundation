package PersonalJComponents;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class UnfocusableRenderer extends DefaultTableCellRenderer{
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
      Component comp = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
      if(hasFocus) table.changeSelection(row,column+1,false,false);
      return comp;
    }
  }