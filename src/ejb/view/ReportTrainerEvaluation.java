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
@Table(name = "Report_Trainer_Evaluation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportTrainerEvaluation.findAll", query = "SELECT r FROM ReportTrainerEvaluation r"),
    @NamedQuery(name = "ReportTrainerEvaluation.findByTrainer", query = "SELECT r FROM ReportTrainerEvaluation r WHERE r.trainer = :trainer"),
    @NamedQuery(name = "ReportTrainerEvaluation.findByKodiTrainimit", query = "SELECT r FROM ReportTrainerEvaluation r WHERE r.kodiTrainimit = :kodiTrainimit"),
    @NamedQuery(name = "ReportTrainerEvaluation.findByPjesemaresitetrajnuar", query = "SELECT r FROM ReportTrainerEvaluation r WHERE r.pjesemaresitetrajnuar = :pjesemaresitetrajnuar"),
    @NamedQuery(name = "ReportTrainerEvaluation.findByPrezantimi", query = "SELECT r FROM ReportTrainerEvaluation r WHERE r.prezantimi = :prezantimi"),
    @NamedQuery(name = "ReportTrainerEvaluation.findByDiskutimi", query = "SELECT r FROM ReportTrainerEvaluation r WHERE r.diskutimi = :diskutimi"),
    @NamedQuery(name = "ReportTrainerEvaluation.findByPergaditja", query = "SELECT r FROM ReportTrainerEvaluation r WHERE r.pergaditja = :pergaditja")})
public class ReportTrainerEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Trainer ")
    private String trainer;
    @Column(name = "Kodi_Trainimit")
    private String kodiTrainimit;
    @Basic(optional = false)
    @Id
    @Column(name = "Pjesemaresit_e_trajnuar")
    private String pjesemaresitetrajnuar;
    @Id
    @Column(name = "Prezantimi")
    private Integer prezantimi;
    @Id
    @Column(name = "Diskutimi")
    private Integer diskutimi;
    @Id
    @Column(name = "Pergaditja")
    private Integer pergaditja;

    public ReportTrainerEvaluation() {
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getKodiTrainimit() {
        return kodiTrainimit;
    }

    public void setKodiTrainimit(String kodiTrainimit) {
        this.kodiTrainimit = kodiTrainimit;
    }

    public String getPjesemaresitetrajnuar() {
        return pjesemaresitetrajnuar;
    }

    public void setPjesemaresitetrajnuar(String pjesemaresitetrajnuar) {
        this.pjesemaresitetrajnuar = pjesemaresitetrajnuar;
    }

    public Integer getPrezantimi() {
        return prezantimi;
    }

    public void setPrezantimi(Integer prezantimi) {
        this.prezantimi = prezantimi;
    }

    public Integer getDiskutimi() {
        return diskutimi;
    }

    public void setDiskutimi(Integer diskutimi) {
        this.diskutimi = diskutimi;
    }

    public Integer getPergaditja() {
        return pergaditja;
    }

    public void setPergaditja(Integer pergaditja) {
        this.pergaditja = pergaditja;
    }
    
}
