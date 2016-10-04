/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.view;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARDITI
 */
@Entity
@Table(name = "Report_Training_Process")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportTrainingProcess.findAll", query = "SELECT r FROM ReportTrainingProcess r"),
    @NamedQuery(name = "ReportTrainingProcess.findByPlace", query = "SELECT r FROM ReportTrainingProcess r WHERE r.place = :place"),
    @NamedQuery(name = "ReportTrainingProcess.findByTrainimi", query = "SELECT r FROM ReportTrainingProcess r WHERE r.trainimi = :trainimi"),
    @NamedQuery(name = "ReportTrainingProcess.findByTitujteTrainimit", query = "SELECT r FROM ReportTrainingProcess r WHERE r.titujteTrainimit = :titujteTrainimit"),
    @NamedQuery(name = "ReportTrainingProcess.findByNumriiTrainereve", query = "SELECT r FROM ReportTrainingProcess r WHERE r.numriiTrainereve = :numriiTrainereve"),
    @NamedQuery(name = "ReportTrainingProcess.findByNumriiPjesmarresve", query = "SELECT r FROM ReportTrainingProcess r WHERE r.numriiPjesmarresve = :numriiPjesmarresve"),
    @NamedQuery(name = "ReportTrainingProcess.findByNumriiVleresimeve", query = "SELECT r FROM ReportTrainingProcess r WHERE r.numriiVleresimeve = :numriiVleresimeve")})
public class ReportTrainingProcess extends AbstractReportEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Place")
    private String place;
    @Basic(optional = false)
    @Column(name = "Trainimi")
    @Id
    private String trainimi;
    @Basic(optional = false)
    @Column(name = "Titujt_e_Trainimit")
    private String titujteTrainimit;
    @Column(name = "Numri_i_Trainereve")
    private Integer numriiTrainereve;
    @Column(name = "Numri_i_Pjesmarresve")
    private Integer numriiPjesmarresve;
    @Column(name = "Numri_i_Vleresimeve")
    private Integer numriiVleresimeve;

    public ReportTrainingProcess() {
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTrainimi() {
        return trainimi;
    }

    public void setTrainimi(String trainimi) {
        this.trainimi = trainimi;
    }

    public String getTitujteTrainimit() {
        return titujteTrainimit;
    }

    public void setTitujteTrainimit(String titujteTrainimit) {
        this.titujteTrainimit = titujteTrainimit;
    }

    public Integer getNumriiTrainereve() {
        return numriiTrainereve;
    }

    public void setNumriiTrainereve(Integer numriiTrainereve) {
        this.numriiTrainereve = numriiTrainereve;
    }

    public Integer getNumriiPjesmarresve() {
        return numriiPjesmarresve;
    }

    public void setNumriiPjesmarresve(Integer numriiPjesmarresve) {
        this.numriiPjesmarresve = numriiPjesmarresve;
    }

    public Integer getNumriiVleresimeve() {
        return numriiVleresimeve;
    }

    public void setNumriiVleresimeve(Integer numriiVleresimeve) {
        this.numriiVleresimeve = numriiVleresimeve;
    }
    
}
