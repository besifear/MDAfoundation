
package gui.view;

import PersonalJComponents.ColumnGroup;
import PersonalJComponents.GroupableTableHeader;
import Utility.Variables;
import bl.TrainingProcessInterface;
import bl.TrainingProcessRepository;
import ejb.Users;
import ejb.view.AbstractReportEntity;
import gui.model.ReportTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.EntityManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SeeReport extends javax.swing.JInternalFrame {
    TrainingProcessInterface tpi;
    ArrayList<String> variablat;
    String GroupingVariable;
    int startMonth;
    int startYear;
    int endMonth;
    int endYear;
    Users useri;
    EntityManager em;
    
    public SeeReport(String GroupingVariable,ArrayList<String> variablat,int startMonth,int startYear,int endMonth,int endYear,Users useri,EntityManager emm) {
        this.useri=useri;
        em=emm;
        this.GroupingVariable=GroupingVariable;
        this.variablat=variablat;
        this.startMonth=startMonth;
        this.startYear=startYear;
        this.endMonth=endMonth;
        this.endYear=endYear;
        tpi=new TrainingProcessRepository(em);
        this.setSize(1024,768);
        this.setVisible(true);
        initComponents();
        defineTableHeader();
    }
    
    public SeeReport(String GroupingVariable,ArrayList<String> variablat,int startYear,int endYear) {
        this.GroupingVariable=GroupingVariable;
        this.variablat=variablat;
        this.startMonth=startMonth;
        this.startYear=startYear;
        this.endMonth=endMonth;
        this.endYear=endYear;
        this.setSize(1024,768);
        this.setVisible(true);
        initComponents();
        defineTableHeader();
    }
    
    
    public SeeReport(String GroupingVariable,ArrayList<String> variablat) {
        this.GroupingVariable=GroupingVariable;
        this.variablat=variablat;
        this.setSize(1024,768);
        this.setVisible(true);
        initComponents();
        defineTableHeader();
    }

    public String getGroupingVariable() {
        return GroupingVariable;
    }

    public void setGroupingVariable(String GroupingVariable) {
        this.GroupingVariable = GroupingVariable;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    
    
    
    private void defineTableHeader() {
        
        ArrayList<AbstractReportEntity> lista= tpi.gjeneroQuery(GroupingVariable, variablat, startMonth, startYear, endMonth, endYear);
        for(AbstractReportEntity are : lista){
        JOptionPane.showMessageDialog(this, lista.toString());
        }
        ReportTableModel rtm = new ReportTableModel(lista,variablat);
        
        

        JTable table = new JTable( rtm ) {
          protected JTableHeader createDefaultTableHeader() {
              return new GroupableTableHeader(columnModel);
          }
        };


        
        TableColumnModel cm = table.getColumnModel();
        Iterator<String> iteratori=variablat.iterator();
        ArrayList<ColumnGroup> listaKolonav=new ArrayList<ColumnGroup> ();
        int i=0;
        int j=0;
        if(GroupingVariable.equals("Muajve")){    
        while (iteratori.hasNext()){
            listaKolonav.add(new ColumnGroup(iteratori.next()));
            listaKolonav.get(i++).add(cm.getColumn(j++));
        }

        GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
        for (ColumnGroup cg : listaKolonav){
        header.addColumnGroup(cg);
        }
        JScrollPane scroll = new JScrollPane( table );
        getContentPane().add( scroll );
        setSize( 400, 120 );   

        // allow sorting
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(rtm);
        table.setRowSorter(sorter);
        
        
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        gjeneratedtbl = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        gjeneratedtbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        gjeneratedtbl.setShowHorizontalLines(false);
        gjeneratedtbl.setShowVerticalLines(false);
        jScrollPane1.setViewportView(gjeneratedtbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(536, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable gjeneratedtbl;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    
}
