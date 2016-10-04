/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.view;

import java.io.Serializable;
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
@Table(name = "Report_Month")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportMonth.findAll", query = "SELECT r FROM ReportMonth r ORDER BY r.viti"),
    @NamedQuery(name = "ReportMonth.findByViti", query = "SELECT r FROM ReportMonth r WHERE r.viti = :viti"),
    @NamedQuery(name = "ReportMonth.findByMuaji", query = "SELECT r FROM ReportMonth r WHERE r.muaji = :muaji"),
    @NamedQuery(name = "ReportMonth.findByNumriiTrainimeve", query = "SELECT r FROM ReportMonth r WHERE r.numriiTrainimeve = :numriiTrainimeve"),
    @NamedQuery(name = "ReportMonth.findByNumriiVleresimeveteTrainereve", query = "SELECT r FROM ReportMonth r WHERE r.numriiVleresimeveteTrainereve = :numriiVleresimeveteTrainereve"),
    @NamedQuery(name = "ReportMonth.findByNumriiPjesemarresve", query = "SELECT r FROM ReportMonth r WHERE r.numriiPjesemarresve = :numriiPjesemarresve")})
public class ReportMonth extends AbstractReportEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Viti")
    private Integer viti;
    @Id
    @Column(name = "Muaji")
    
    private Integer muaji;
    @Column(name = "Numri_i_Trainimeve")
    private Integer numriiTrainimeve;
    @Column(name = "Numri_i_Vleresimeve_te_Trainereve")
    private Integer numriiVleresimeveteTrainereve;
    @Column(name = "Numri_i_Pjesemarresve")
    private Integer numriiPjesemarresve;

    public ReportMonth() {
    }

    public Integer getViti() {
        return viti;
    }

    public void setViti(Integer viti) {
        this.viti = viti;
    }

    public Integer getMuaji() {
        return muaji;
    }

    public void setMuaji(Integer muaji) {
        this.muaji = muaji;
    }

    public Integer getNumriiTrainimeve() {
        return numriiTrainimeve;
    }

    public void setNumriiTrainimeve(Integer numriiTrainimeve) {
        this.numriiTrainimeve = numriiTrainimeve;
    }

    public Integer getNumriiVleresimeveteTrainereve() {
        return numriiVleresimeveteTrainereve;
    }

    public void setNumriiVleresimeveteTrainereve(Integer numriiVleresimeveteTrainereve) {
        this.numriiVleresimeveteTrainereve = numriiVleresimeveteTrainereve;
    }

    public Integer getNumriiPjesemarresve() {
        return numriiPjesemarresve;
    }

    public void setNumriiPjesemarresve(Integer numriiPjesemarresve) {
        this.numriiPjesemarresve = numriiPjesemarresve;
    }
    
}
