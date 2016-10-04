
package gui.view;

import Utility.Variables;
import bl.AppException;
import bl.TrainingInterface;
import bl.TrainingProcessRepository;
import bl.TrainingRepository;
import ejb.Users;
import gui.model.OpcioneTblTableModel;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;



public class CreateReport extends javax.swing.JInternalFrame {
    Users useri;
    EntityManager em;
    
    OpcioneTblTableModel vartm=new OpcioneTblTableModel();
    
    
    public CreateReport(Users useri,EntityManager emm) {
        this.useri=useri;
        em=emm;
        initComponents();
        OpcioneTblTabelaLoad(opcionetbl);
        addItemCombo();
    }
    
public void addItemCombo(){
    for(int i=2025;i>1980;i--){
    startYearcombo.addItem((Integer.toString(i)));
    endYearcombo.addItem((Integer.toString(i)));
    }
    startYearcombo.setSelectedIndex(44);
    endYearcombo.setSelectedIndex(0);
}
    
    public void OpcioneTblTabelaLoad(JTable table){
    vartm.add(shtoVariablat());
    table.setModel(vartm);
    vartm.fireTableDataChanged();
    }
    
    public ArrayList<Variables> shtoVariablat(){
        ArrayList<Variables> variablat=new ArrayList<Variables>();
        
        switch(groupbycombo.getSelectedItem().toString()){
            
            case "Muajve":
            case "Viteve":
            case "Trainerëve":
            case  "Place":
                variablat.add(new Variables("Numri i Trainimeve",false));
                variablat.add(new Variables("Numri i Pjesëmarrësve",false));
                variablat.add(new Variables("Numri i Vlerësimeve Të trainerëve",false));
                break;
            case "Vlerësimit të trajerëve"://Trainer Evaluation
                variablat.add(new Variables("Numri i Trainimeve",false));
                variablat.add(new Variables("Numri i Pjesëmarrësve",false));
                variablat.add(new Variables("Vlerësimet e Trainerëve Individualisht ",false));
                break;
            case "Training Code"://Training-Process
                variablat.add(new Variables("Trainimet Individualisht ",false));
                variablat.add(new Variables("Numri i Pjesëmarrësve",false));
                variablat.add(new Variables("Numri i Vlerësimeve Të trainerëve",false));
                variablat.add(new Variables(" Trainimet Individualisht ",false));
                break;
            default:
                 return null;
        }
        return variablat;
    }
    
    public ArrayList<String> getTableColumns()throws AppException{
    ArrayList<String> variablat=new ArrayList<String>();
    for(Variables var : vartm.getData()){
        if(var.isChecked()==true)
            variablat.add(var.getName());
    }
    if (variablat.size()==0)
        throw new AppException ("Duhet te zgjidhet se paku nje kolone");
    return variablat;
    }
    
    public void fshehComboBox(){
        if(groupbycombo.getSelectedItem().toString().equals("Muajve")){
            startMonthcombo.setVisible(true);
            startYearcombo.setVisible(true);
            endMonthcombo.setVisible(true);
            endYearcombo.setVisible(true);
        }
        else if (groupbycombo.getSelectedItem().toString().equals("Viteve")) {
            startMonthcombo.setVisible(false);
            startYearcombo.setVisible(true);
            endMonthcombo.setVisible(false);
            endYearcombo.setVisible(true);
        }
        else{
            startMonthcombo.setVisible(false);
            startYearcombo.setVisible(false);
            endMonthcombo.setVisible(false);
            endYearcombo.setVisible(false);
        }
    }
    
    public int ktheMuajin(String s){
        int i;
        switch(s){
            case "Janar":
                i=1;
                break;
            case "Shkurt":
                i=2;
                break;
            case "Mars":
                i=3;
                break;
            case "Prill":
                i=4;
                break;
            case "Maj":
                i=5;
                break;
            case "Qershor":
                i=6;
                break;
            case "Korrik":
                i=7;
                break;
            case "Gusht":
                i=8;
                break;
            case "Shtator":
                i=9;
                break;
            case "Tetorë":
                i=10;
                break;
            case "Nëntorë":
                i=11;
                break;
            case "Dhjetorë":
                i=12;
                break;
            default:
                i=0;
                break;
        }
        return i;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        opcionetbl = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        groupbycombo = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        orderbycombo = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        startYearcombo = new javax.swing.JComboBox<>();
        endYearcombo = new javax.swing.JComboBox<>();
        startMonthcombo = new javax.swing.JComboBox<>();
        endMonthcombo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);

        jLabel1.setText("Variablat e Volitshme");

        opcionetbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Variabla", "Check"
            }
        ));
        opcionetbl.setShowHorizontalLines(false);
        opcionetbl.setShowVerticalLines(false);
        jScrollPane1.setViewportView(opcionetbl);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
        );

        jLabel3.setText("Grupo Sipas");

        groupbycombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Muajve", "Viteve", "Vlerësimit të trajerëve", "Trainerëve", "Training Code", "Place" }));
        groupbycombo.setToolTipText("");
        groupbycombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groupbycomboItemStateChanged(evt);
            }
        });

        jButton2.setText("Krijo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Rendit sipas");

        orderbycombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Max", "Min" }));

        jButton4.setText("Mbylle");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        startMonthcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janar", "Shkurt", "Mars", "Prill", "Maj", "Qershor", "Korrik", "Gusht", "Shtator", "Tetorë", "Nëntorë", "Dhjetorë" }));

        endMonthcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janar", "Shkurt", "Mars", "Prill", "Maj", "Qershor", "Korrik", "Gusht", "Shtator", "Tetorë", "Nëntorë", "Dhjetorë" }));

        jLabel2.setText("Data Fillimit:");

        jLabel5.setText("Data Mbarimit:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(groupbycombo, 0, 181, Short.MAX_VALUE)
                            .addComponent(orderbycombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(startMonthcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(startYearcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(endMonthcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(endYearcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(groupbycombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endMonthcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endYearcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderbycombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startMonthcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startYearcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try{
    SeeReport sr;
    String GroupingVariable =groupbycombo.getSelectedItem().toString();
    ArrayList<String>variablat=getTableColumns();
    
    int startMonth=ktheMuajin(startMonthcombo.getSelectedItem().toString());
    int startYear=Integer.parseInt(startYearcombo.getSelectedItem().toString());
    int endMonth =ktheMuajin(endMonthcombo.getSelectedItem().toString());
    int endYear =Integer.parseInt(endYearcombo.getSelectedItem().toString());
    
    JOptionPane.showMessageDialog(null,GroupingVariable);
    JOptionPane.showMessageDialog(null,"Prej :"+ startMonth+" - "+startYear);
    JOptionPane.showMessageDialog(null,"Deri :"+ endMonth+" - "+endYear);
    for(String s : variablat){
        JOptionPane.showMessageDialog(null, s);
    }
    
    
    if (groupbycombo.getSelectedItem().toString().equals("Muajve"))
        sr=new SeeReport(GroupingVariable,variablat,startMonth,startYear,endMonth,endYear,useri,em);
    else if (groupbycombo.getSelectedItem().toString().equals("Viteve"))
        sr=new SeeReport(GroupingVariable,variablat,startYear,endYear);
    else
        sr= new SeeReport(GroupingVariable,variablat);
    
    this.getParent().add(sr);
    this.dispose();
    sr.show();
    }catch (AppException ae){JOptionPane.showMessageDialog(this,ae.getMessage());}
    
    
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void groupbycomboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groupbycomboItemStateChanged
        OpcioneTblTabelaLoad(opcionetbl);
        fshehComboBox();
    }//GEN-LAST:event_groupbycomboItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> endMonthcombo;
    private javax.swing.JComboBox<String> endYearcombo;
    private javax.swing.JComboBox<String> groupbycombo;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable opcionetbl;
    private javax.swing.JComboBox<String> orderbycombo;
    private javax.swing.JComboBox<String> startMonthcombo;
    private javax.swing.JComboBox<String> startYearcombo;
    // End of variables declaration//GEN-END:variables
}
