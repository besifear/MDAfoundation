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
@Table(name = "Report_Trainer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportTrainer.findAll", query = "SELECT r FROM ReportTrainer r"),
    @NamedQuery(name = "ReportTrainer.findByTrainer", query = "SELECT r FROM ReportTrainer r WHERE r.trainer = :trainer"),
    @NamedQuery(name = "ReportTrainer.findByMesatarja", query = "SELECT r FROM ReportTrainer r WHERE r.mesatarja = :mesatarja"),
    @NamedQuery(name = "ReportTrainer.findByPrezantimi", query = "SELECT r FROM ReportTrainer r WHERE r.prezantimi = :prezantimi"),
    @NamedQuery(name = "ReportTrainer.findByDiskutimi", query = "SELECT r FROM ReportTrainer r WHERE r.diskutimi = :diskutimi"),
    @NamedQuery(name = "ReportTrainer.findByPergaditja", query = "SELECT r FROM ReportTrainer r WHERE r.pergaditja = :pergaditja"),
    @NamedQuery(name = "ReportTrainer.findByNumriiTrainimevetembajtura", query = "SELECT r FROM ReportTrainer r WHERE r.numriiTrainimevetembajtura = :numriiTrainimevetembajtura"),
    @NamedQuery(name = "ReportTrainer.findByNumriiPersonavetetrainuar", query = "SELECT r FROM ReportTrainer r WHERE r.numriiPersonavetetrainuar = :numriiPersonavetetrainuar"),
    @NamedQuery(name = "ReportTrainer.findByNumriVlersimeve", query = "SELECT r FROM ReportTrainer r WHERE r.numriVlersimeve = :numriVlersimeve")})
public class ReportTrainer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Trainer ")
    @Id
    private String trainer;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Mesatarja")
    private Double mesatarja;
    @Column(name = "Prezantimi")
    private Double prezantimi;
    @Column(name = "Diskutimi")
    private Double diskutimi;
    @Column(name = "Pergaditja")
    private Double pergaditja;
    @Column(name = "Numri_i_Trainimeve_te_mbajtura")
    private Integer numriiTrainimevetembajtura;
    @Column(name = "Numri_i_Personave_te_trainuar")
    private Integer numriiPersonavetetrainuar;
    @Column(name = "Numri_Vlersimeve")
    private Integer numriVlersimeve;

    public ReportTrainer() {
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public Double getMesatarja() {
        return mesatarja;
    }

    public void setMesatarja(Double mesatarja) {
        this.mesatarja = mesatarja;
    }

    public Double getPrezantimi() {
        return prezantimi;
    }

    public void setPrezantimi(Double prezantimi) {
        this.prezantimi = prezantimi;
    }

    public Double getDiskutimi() {
        return diskutimi;
    }

    public void setDiskutimi(Double diskutimi) {
        this.diskutimi = diskutimi;
    }

    public Double getPergaditja() {
        return pergaditja;
    }

    public void setPergaditja(Double pergaditja) {
        this.pergaditja = pergaditja;
    }

    public Integer getNumriiTrainimevetembajtura() {
        return numriiTrainimevetembajtura;
    }

    public void setNumriiTrainimevetembajtura(Integer numriiTrainimevetembajtura) {
        this.numriiTrainimevetembajtura = numriiTrainimevetembajtura;
    }

    public Integer getNumriiPersonavetetrainuar() {
        return numriiPersonavetetrainuar;
    }

    public void setNumriiPersonavetetrainuar(Integer numriiPersonavetetrainuar) {
        this.numriiPersonavetetrainuar = numriiPersonavetetrainuar;
    }

    public Integer getNumriVlersimeve() {
        return numriVlersimeve;
    }

    public void setNumriVlersimeve(Integer numriVlersimeve) {
        this.numriVlersimeve = numriVlersimeve;
    }
    
}
