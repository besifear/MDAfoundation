package PersonalJComponents;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

// original from http://stackoverflow.com/questions/21347647/how-to-combine-two-column-headers-in-jtable-in-swings
//Shembull
public class GroupableHeaderExample extends JFrame {

      GroupableHeaderExample() {
        super( "Groupable Header Example" );

        DefaultTableModel dm = new DefaultTableModel();
        dm.setDataVector(new Object[][]{
          {"1","a","b","c","d","e"},
          {"2","f","g","h","i","j"},
          {"3","k","l","m","n","o"},
          {"4","p","q","r","s","t"}
          },
        new Object[]{"SNo.","1","2","Native","2","3"});

        JTable table = new JTable( dm ) {
          protected JTableHeader createDefaultTableHeader() {
              return new GroupableTableHeader(columnModel);
          }
        };


        TableColumnModel cm = table.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("Name");
        g_name.add(cm.getColumn(1));
        g_name.add(cm.getColumn(2));
        ColumnGroup g_lang = new ColumnGroup("Language");
        g_lang.add(cm.getColumn(3));
        ColumnGroup g_other = new ColumnGroup("Others");
        g_other.add(cm.getColumn(4));
        g_other.add(cm.getColumn(5));
        g_lang.add(g_other);

        GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
        header.addColumnGroup(g_name);
        header.addColumnGroup(g_lang);
        JScrollPane scroll = new JScrollPane( table );
        getContentPane().add( scroll );
        setSize( 400, 120 );   

        // allow sorting
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dm);
        table.setRowSorter(sorter);

      }

      public static void main(String[] args) {
        GroupableHeaderExample frame = new GroupableHeaderExample();
        frame.setSize(1024,768);
        frame.addWindowListener( new WindowAdapter() {
          public void windowClosing( WindowEvent e ) {
      System.exit(0);
          }
        });
        frame.setVisible(true);
      }
    }