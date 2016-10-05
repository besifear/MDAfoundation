
package gui.view;

import java.io.FileWriter;
import ejb.Participant;
import ejb.TrainingView;
import ejb.ParticipantView;
import ejb.view.ReportMonth;
import ejb.view.ReportTrainerEvaluation;
import ejb.view.ReportTrainer;
import ejb.view.ReportYear;
import ejb.view.ReportTrainingProcess;
import ejb.TrainingProcess;

import bl.ParticipantInterface;
import bl.ParticipantRepository;
import bl.TrainingViewInterface;
import bl.TrainingViewRepository;
import bl.ParticipantViewInterface;
import bl.ParticipantViewRepository;
import bl.ReportMonthViewInterface;
import bl.ReportMonthViewRepository;
import bl.ReportTrainerEvaluationViewInterface;
import bl.ReportTrainerEvaluationViewRepository;
import bl.ReportYearInterface;
import bl.ReportYearRepository;
import bl.ReportTrainerInterface;
import bl.ReportTrainerRepository;
import bl.ReportTrainingProcessInterface;
import bl.ReportTrainingProcessRepository;
import bl.TrainingProcessInterface;
import bl.TrainingProcessRepository;
import bl.TopicsCoveredInterface;
import bl.TopicsComboRepository;
import bl.TopicsCoveredRepository;
import ejb.TopicsCovered;
import ejb.Users;

import gui.model.ParticipantTableModelMini;
import gui.model.ParticipantViewTableModel;
import gui.model.TrainingViewTableModel;
import gui.model.ReportMonthViewTableModel;
import gui.model.ReportTrainerEvaluationViewTableModel;
import gui.model.ReportYearTableModel;
import gui.model.ReportTrainerTableModel;
import gui.model.ReportTrainingProcessTableModel;
import java.awt.Color;
import static java.awt.Color.RED;
import java.awt.Font;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportEx extends javax.swing.JInternalFrame {
EntityManager em;
Users useri;

    ParticipantInterface partir;
    ParticipantViewInterface partviewir;
    TrainingViewInterface trainviewir;
    ReportMonthViewInterface rmonthviewir;
    ReportTrainerEvaluationViewInterface rtevviewir;
    ReportYearInterface ryearviewir;
    ReportTrainerInterface rtrainerviewir;
    ReportTrainingProcessInterface rtpviewir;
    TrainingProcessInterface tpir;
    TopicsCoveredInterface topicsir;
    
    ParticipantTableModelMini ptm=new ParticipantTableModelMini();
    ParticipantViewTableModel pvtm=new ParticipantViewTableModel();
    TrainingViewTableModel tvtm=new TrainingViewTableModel();
    ReportMonthViewTableModel rmvtm=new ReportMonthViewTableModel();
    ReportTrainerEvaluationViewTableModel rtevvtm=new ReportTrainerEvaluationViewTableModel();
    ReportYearTableModel ryvtm=new ReportYearTableModel();
    ReportTrainerTableModel rtvtm=new ReportTrainerTableModel();
    ReportTrainingProcessTableModel rtpvtm=new ReportTrainingProcessTableModel();
    
    
    
    DefaultTableModel dm=new DefaultTableModel();
    
    
    public ExportEx(Users useri,EntityManager emm) {
        em=emm;
        this.useri=useri;
        partir=new ParticipantRepository(em);
        partviewir=new ParticipantViewRepository(em);
        trainviewir=new TrainingViewRepository(em);
        rmonthviewir=new ReportMonthViewRepository(em);
        rtevviewir=new ReportTrainerEvaluationViewRepository(em);
        ryearviewir=new ReportYearRepository(em);
        rtrainerviewir=new ReportTrainerRepository(em);
        rtpviewir=new ReportTrainingProcessRepository(em);
        tpir=new TrainingProcessRepository(em);
        topicsir=new TopicsCoveredRepository(em);
        
        this.setLocation(15, 147);
        for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        
        initComponents();
    }

    private void trainingViewTabelaLoad(){
        List<TrainingView> list=trainviewir.findAll();
        tvtm.add(list);
        tbl.setModel(tvtm);
        tvtm.fireTableDataChanged();
        for(int i=0;i<tvtm.getRowCount();i++){
        }
    }
    
    private void participantViewTabelaLoad(){
    List<ParticipantView> list=partviewir.findAll();
    pvtm.add(list);
    tbl.setModel(pvtm);
    pvtm.fireTableDataChanged();
    
    }
    
    private void reportMonthViewTabelaLoad(){
    List<ReportMonth> list=rmonthviewir.findAll();
    rmvtm.add(list);
    tbl.setModel(rmvtm);
    rmvtm.fireTableDataChanged();
    
    }
    
    private void reportTrainerEvaluationViewTabelaLoad(){
    List<ReportTrainerEvaluation> list=rtevviewir.findAll();
    rtevvtm.add(list);
    tbl.setModel(rtevvtm);
    rtevvtm.fireTableDataChanged();
    
    }
    
    private void reportYearViewTabelaLoad(){
    List<ReportYear> list=ryearviewir.findAll();
    ryvtm.add(list);
    tbl.setModel(ryvtm);
    ryvtm.fireTableDataChanged();
    
    }
    
    private void reportTrainerViewTabelaLoad(){
    List<ReportTrainer> list=rtrainerviewir.findAll();
    rtvtm.add(list);
    tbl.setModel(rtvtm);
    rtvtm.fireTableDataChanged();
    
    }
    private void reportTrainingProcessViewTabelaLoad(){
    List<ReportTrainingProcess> list=rtpviewir.findAll();
    rtpvtm.add(list);
    tbl.setModel(rtpvtm);
    rtpvtm.fireTableDataChanged();
    
    }
    
    
    private void exportExcel(String directory) throws IOException{
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet ws=wb.createSheet();
        TableModel tm=tbl.getModel();
        int rows=tm.getRowCount();
        int columns=tm.getColumnCount();
        
        
        TreeMap<String ,Object[]> data=new TreeMap<>();
        
        String[] strs=new String [columns];
        
        
        
        
        
        for(int i=0;i<columns;i++){
            strs[i]=tm.getColumnName(i);
        }
        
        data.put("-1", strs);
        
        Object[] o;
        for(int i=0;i<rows;i++){
            o=new Object[columns];
            for(int j=0;j<columns;j++){
                o[j]= tm.getValueAt(i, j);
            }
         data.put(Integer.toString(i),o);
         
        }
        
        
        XSSFRow row;
        int rowID=0;
        
        int [] keyat=new int[rows+1];
        
        for(int i=0;i<rows+1;i++){
            keyat[i]=i-1;
        }
        
        
        
        for(int key: keyat){
            row=ws.createRow(rowID++);
            Object[] values=data.get(Integer.toString(key));
            int cellID=0;
            for(Object obj: values){
                Cell cell=row.createCell(cellID++);
                cell.setCellValue(obj.toString());
                
            }
        }
        
        for(int i=0;i<columns;i++){
            ws.autoSizeColumn(i);
        }
        
        
        XSSFCellStyle style =wb.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(53,100,57)));
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.RED.getIndex());
        XSSFFont font = wb.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);
        
        XSSFCellStyle style2 =wb.createCellStyle();
        style2.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);
        style2.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
        style2.setBorderLeft(HSSFCellStyle.BORDER_NONE);
        style2.setBorderRight(HSSFCellStyle.BORDER_NONE);
        style2.setBottomBorderColor(IndexedColors.RED.getIndex());
        style2.setTopBorderColor(IndexedColors.RED.getIndex());
        for(int i=0;i<tm.getColumnCount();i++){
            ws.getRow(0).getCell(i).setCellStyle(style);
            
        }
        
        for(int i=0;i<tm.getColumnCount();i++){
            for(int j=0;j<tm.getRowCount();j++){
                if(j!=0){
                    ws.getRow(j).getCell(i).setCellStyle(style2);
                } 
            }     
        }
        String r=rows+"";
        String r2=getCol(columns);
        
        String range="A1:"+r2+r;
        
        ws.setAutoFilter(CellRangeAddress.valueOf(range));
        ws.setDisplayGridlines(false);
        
        
        /*if(tbl.getModel()==tvtm)
            exportTrainingView(tm,ws);
        else if(tbl.getModel()==pvtm)
            exportParticipantView(tm,ws);  
        else if(tbl.getModel()==rtevvtm){
            exportEvaluationView(tm,ws);
        }*/
        try{
            FileOutputStream fos;
            fos = new FileOutputStream(new File(directory+emertoFilin(tbl)+".xlsx"));
             
            wb.write(fos);
            fos.close();
            JOptionPane.showMessageDialog(null,"U ruajt me sukses");
        }catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"File është i hapur.Mbylleni file-in per ta ruajtur");
        }
        
    }
    
    
    
    public String getCol(int i){
        switch(i){
            case 0:
                return "A";
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            case 9:
                return "I";
            case 10:
                return "J";
            case 11:
                return "K";
            case 12:
                return "L";
            case 13:
                return "M";
            case 14:
                return "N";
            case 15:
                return "O";
            case 16:
                return "P";
            case 17:
                return "Q";
            case 18:
                return "R";
            case 19:
                return "S";
            case 20:
                return "T";    
            case 21:
                return "U";
            case 22:
                return "V";
            case 23:
                return "W";
            case 24:
                return "X";
            case 25:
                return "Y";
            case 26:
                return "Z";
            default:
                return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        typecombo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ));
        tbl.setShowHorizontalLines(false);
        tbl.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tbl);

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Zgjidh llojin e tabelës ");

        typecombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trajnimet", "Pjesmarrësit", "Raport Mujor", "Raport i Vlerësimeve", "Raport i Viteve", "Raport i Trajnerave", "Raport i Trajnimeve" }));

        jButton1.setText("Gjenero Tabelën");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Export to Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(typecombo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 444, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(typecombo, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(typecombo.getSelectedItem().toString().equals("Trajnimet")){
            trainingViewTabelaLoad();
        }
        else if(typecombo.getSelectedItem().toString().equals("Pjesmarrësit")){
            participantViewTabelaLoad();
        }
        else if(typecombo.getSelectedItem().toString().equals("Raport i Vlerësimeve")){
            reportTrainerEvaluationViewTabelaLoad();
        }
        else if(typecombo.getSelectedItem().toString().equals("Raport Mujor")){
            reportMonthViewTabelaLoad();
        }
        else if(typecombo.getSelectedItem().toString().equals("Raport i Viteve")){
            reportYearViewTabelaLoad();
        }
        else if(typecombo.getSelectedItem().toString().equals("Raport i Trajnerave")){
            reportTrainerViewTabelaLoad();
        }
        else if(typecombo.getSelectedItem().toString().equals("Raport i Trajnimeve")){
            reportTrainingProcessViewTabelaLoad();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Chooser");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String directory="";
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            
            directory=chooser.getSelectedFile().toString()+"\\";
            
            try {
             if(tbl.getModel()==pvtm||tbl.getModel()==tvtm||tbl.getModel()==rmvtm||tbl.getModel()==rtevvtm||tbl.getModel()==ryvtm||tbl.getModel()==rtvtm||tbl.getModel()==rtpvtm){
            exportExcel(directory);
             }
             else{
                 JOptionPane.showMessageDialog(null,"Gjeneroni njeren nga tabelat!!");
             }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,"IOException");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Duhet te zgjedhni lokacionin se ku deshironi te ruani file !");
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl;
    private javax.swing.JComboBox<String> typecombo;
    // End of variables declaration//GEN-END:variables

    private void exportTrainingView(TableModel tm, XSSFSheet ws) {
    List<TrainingView> list=trainviewir.findAll();
            List<TrainingProcess> trajnimet=tpir.findAll();
            List<TopicsCovered> temat;
            int start=2;
            int end=1;
            
            for(int i=0;i<trajnimet.size();i++){
                
            }
            
            for(int i=0;i<trajnimet.size();i++)
            {

                end+=topicsir.findByTpId(trajnimet.get(i).getTProcessID()).size();


                for(int j=1;j<tm.getColumnCount()+1;j++){
                   if(!((TrainingViewTableModel)tm).getTitle(j-1).equals("Topic_Covered")&&!((TrainingViewTableModel)tm).getTitle(j-1).equals("Language")){
                    //JOptionPane.showMessageDialog(null,start + " - "+ end);
                    ws.addMergedRegion(CellRangeAddress.valueOf(getCol(j)+(start)+":"+getCol(j)+(end)));
                    
                   }
                   
                }
                start=end+1;
            }  
    }

    private void exportParticipantView(TableModel tm, XSSFSheet ws) {
    List<ParticipantView> list=partviewir.findAll();
            List<Participant>pjesat=partir.findAll();
            
            List<TopicsCovered> temat;
            long start=2;
            long end=1;
            
            for(int i=0;i<pjesat.size();i++){
            //JOptionPane.showMessageDialog(null, ""+partir.findByEvaluation(list.get(i).getParticipantID(),list.get(i).getTProcessID()).size());
            }
            
            
            
            for(int i=0;i<pjesat.size();i++)
            {

                end+=partir.findByEvaluation(pjesat.get(i).getParticipantID());
                    

                for(int j=1;j<tm.getColumnCount()+1;j++){
                   if(!((ParticipantViewTableModel)tm).getTitle(j-1).equals("Trajneri")&&!((ParticipantViewTableModel)tm).getTitle(j-1).equals("Prezantimi")&&!((ParticipantViewTableModel)tm).getTitle(j-1).equals("Diskutimi")&&!((ParticipantViewTableModel)tm).getTitle(j-1).equals("Përgaditja")&&!((ParticipantViewTableModel)tm).getTitle(j-1).equals("TrainingID")){
                    //JOptionPane.showMessageDialog(null,(getCol(j)+(start)+":"+getCol(j)+(end)));
                    if(end!=start){
                        ws.addMergedRegion(CellRangeAddress.valueOf(getCol(j)+(start)+":"+getCol(j)+(end)));
                    }
                    
                   }
                   
                }
                start=end+1;
            }  
        }
    
    private void exportEvaluationView(TableModel tm, XSSFSheet ws) {
        
            List<Participant>pjesat=partir.findAll();
            
            
            long start=2;
            long end=1;
            
            for(int i=0;i<pjesat.size();i++){
            //JOptionPane.showMessageDialog(null, ""+partir.findByEvaluation(list.get(i).getParticipantID(),list.get(i).getTProcessID()).size());
            }
            
            for(int i=0;i<pjesat.size();i++)
            {

                end+=partir.findByEvaluation(pjesat.get(i).getParticipantID());
                    

                for(int j=1;j<tm.getColumnCount()+1;j++){
                   if(!((ReportTrainerEvaluationViewTableModel)tm).getTitle(j-1).equals("Trajneri")&&!((ReportTrainerEvaluationViewTableModel)tm).getTitle(j-1).equals("Prezantimi")&&!((ReportTrainerEvaluationViewTableModel)tm).getTitle(j-1).equals("Diskutimi")&&!((ReportTrainerEvaluationViewTableModel)tm).getTitle(j-1).equals("Përgaditja")&&!((ReportTrainerEvaluationViewTableModel)tm).getTitle(j-1).equals("Kodi i Trajnimit")){
                    //JOptionPane.showMessageDialog(null,(getCol(j)+(start)+":"+getCol(j)+(end)));
                    if(end!=start){
                        ws.addMergedRegion(CellRangeAddress.valueOf(getCol(j)+(start)+":"+getCol(j)+(end)));
                    }
                    
                   }
                   
                }
                start=end+1;
            }  
        }
    
    private String emertoFilin(JTable tbl) {
     String t="Raport";
     
                
                if(tbl.getModel()==tvtm){
                    t="Trajnimet";
                }
                else if(tbl.getModel()==pvtm){
                    t="Pjesmarrësit";
                }
                else if(tbl.getModel()==rmvtm){
                    t=" Raport Mujor";
                }
                else if(tbl.getModel()==rtevvtm){
                    t="Raport i Vlerësimeve";
                }
                else if(tbl.getModel()==ryvtm){
                    t="Raport për Vite";
                }
                else if(tbl.getModel()==rtvtm){
                    t="Raport i Trajnerave";
                }
                else if(tbl.getModel()==rtpvtm){
                    t="Raport i Trajnimeve";
                }
        return t;
    }
}
