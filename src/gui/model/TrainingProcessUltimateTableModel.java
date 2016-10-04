
package gui.model;

import bl.TopicsCoveredInterface;
import bl.TopicsCoveredRepository;
import bl.CompanyInterface;
import bl.CompanyRepository;
import bl.TrainingInterface;
import bl.TrainingRepository;
import bl.ProjectInterface;
import bl.ProjectRepository;
import bl.TrainingProjectInterface;
import bl.TrainingProjectRepository;
import ejb.TrainingProcess;
import ejb.Company;
import ejb.TopicsCovered;
import ejb.TrainingProject;
import ejb.Training;
import ejb.Project;
import ejb.CTPcombo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.table.AbstractTableModel;


public class TrainingProcessUltimateTableModel extends AbstractTableModel {
    private String [] columnNames= {"TProcessID","Project","TitleOfTraining(ALB)","TopicsCovered(ALB)","Place","StartDate","EndDate"};
    private List <TrainingProcess> data;
    private List<Training> titles;
    TopicsCoveredInterface topicsir;
    CompanyInterface companyir;
    TrainingInterface trainingir;
    ProjectInterface projectir;


    public TrainingProcessUltimateTableModel(EntityManager em){
    topicsir=new TopicsCoveredRepository(em);
    companyir=new CompanyRepository(em);
    trainingir=new TrainingRepository(em);
    projectir=new ProjectRepository(em);
    }
    
    public void resetData(){
        data=new ArrayList<TrainingProcess>();
    }
    
    public TrainingProcessUltimateTableModel(List<TrainingProcess> data ){
        this.data=data;
    }
    
    @Override
    public int getRowCount() {
     return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public TrainingProcess getTrainingProcess(int x){
        return data.get(x);
    }
    
    public String ktheTopics(List <TopicsCovered> tc){
        String s="";
        for (int i=0;i<tc.size();i++)
        {
            s+=tc.get(i).getTopicCovered()+" ; ";
        }
        return s;
    }
     
     public String ktheProjectName(List<Project> pl){
        
        String pn="";
        for(int i=0;i<pl.size();i++){
            
            pn+=pl.get(i).getEmri();
        }
        return pn;
     }
     
    
     
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrainingProcess tp=(TrainingProcess)data.get(rowIndex);
        List <TopicsCovered> tc=topicsir.findByTpIdAndLang(tp.getTProcessID(),"Albanian");
        List <Company> cl=companyir.findByTpId(tp.getTProcessID());
        List<Project> pl=projectir.findByTpId(tp.getTProcessID());
        Training t=trainingir.findByTpId(tp.getTProcessID());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        switch (columnIndex){
            case 0:
                return tp.getTProcessID();
            case 1:
                return ktheProjectName(pl);
            case 2:
                return t.getTitleOfTrainingAlbanian();
            case 3:
                return ktheTopics(tc);
            case 4:
                return tp.getPlace();
            case 5:
                return dateFormat.format(tp.getStartDate());
            case 6:
                return dateFormat.format(tp.getEndDate());
            
                
            default:
                return null;
        }
    }
    
    public void add(List<TrainingProcess> data){
        this.data=data;
    }
         
      public void addTitle(List<Training> titles){
        this.titles=titles;
    }
    public void remove (int row){
        data.remove(row);
    }
    
}