/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARDITI
 */
@Entity
@Table(name = "Training_View")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainingView.findAll", query = "SELECT t FROM TrainingView t"),
    @NamedQuery(name = "TrainingView.findByTProcessID", query = "SELECT t FROM TrainingView t WHERE t.tProcessID = :tProcessID"),
    @NamedQuery(name = "TrainingView.findByEmriKlientit", query = "SELECT t FROM TrainingView t WHERE t.emriKlientit = :emriKlientit"),
    @NamedQuery(name = "TrainingView.findByEmriProjektit", query = "SELECT t FROM TrainingView t WHERE t.emriProjektit = :emriProjektit"),
    @NamedQuery(name = "TrainingView.findByTitleOfTrainingAlbanian", query = "SELECT t FROM TrainingView t WHERE t.titleOfTrainingAlbanian = :titleOfTrainingAlbanian"),
    @NamedQuery(name = "TrainingView.findByTematShqip", query = "SELECT t FROM TrainingView t WHERE t.tematShqip = :tematShqip"),
    @NamedQuery(name = "TrainingView.findByTematAnglisht", query = "SELECT t FROM TrainingView t WHERE t.tematAnglisht = :tematAnglisht"),
    @NamedQuery(name = "TrainingView.findByTematSerbisht", query = "SELECT t FROM TrainingView t WHERE t.tematSerbisht = :tematSerbisht"),
    @NamedQuery(name = "TrainingView.findByPlace", query = "SELECT t FROM TrainingView t WHERE t.place = :place"),
    @NamedQuery(name = "TrainingView.findByStartDate", query = "SELECT t FROM TrainingView t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "TrainingView.findByEndDate", query = "SELECT t FROM TrainingView t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "TrainingView.findByTrainerat", query = "SELECT t FROM TrainingView t WHERE t.trainerat = :trainerat"),
    @NamedQuery(name = "TrainingView.findByTitleOfTrainingEnglish", query = "SELECT t FROM TrainingView t WHERE t.titleOfTrainingEnglish = :titleOfTrainingEnglish"),
    @NamedQuery(name = "TrainingView.findByTitleOfTrainingSerbian", query = "SELECT t FROM TrainingView t WHERE t.titleOfTrainingSerbian = :titleOfTrainingSerbian")})
public class TrainingView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "TProcess_ID")
    private String tProcessID;
    @Basic(optional = false)
    @Column(name = "Emri_Klientit")
    private String emriKlientit;
    @Basic(optional = false)
    @Column(name = "Emri_Projektit")
    private String emriProjektit;
    @Column(name = "TitleOfTraining_Albanian")
    private String titleOfTrainingAlbanian;
    @Column(name = "Temat_Shqip")
    private String tematShqip;
    @Column(name = "Temat_Anglisht")
    private String tematAnglisht;
    @Column(name = "Temat_Serbisht")
    private String tematSerbisht;
    @Basic(optional = false)
    @Column(name = "Place")
    private String place;
    @Basic(optional = false)
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "Trainerat")
    private String trainerat;
    @Column(name = "TitleOfTraining_English")
    private String titleOfTrainingEnglish;
    @Column(name = "TitleOfTraining_Serbian")
    private String titleOfTrainingSerbian;

    public TrainingView() {
    }

    public String getTProcessID() {
        return tProcessID;
    }

    public void setTProcessID(String tProcessID) {
        this.tProcessID = tProcessID;
    }

    public String getEmriKlientit() {
        return emriKlientit;
    }

    public void setEmriKlientit(String emriKlientit) {
        this.emriKlientit = emriKlientit;
    }

    public String getEmriProjektit() {
        return emriProjektit;
    }

    public void setEmriProjektit(String emriProjektit) {
        this.emriProjektit = emriProjektit;
    }

    public String getTitleOfTrainingAlbanian() {
        return titleOfTrainingAlbanian;
    }

    public void setTitleOfTrainingAlbanian(String titleOfTrainingAlbanian) {
        this.titleOfTrainingAlbanian = titleOfTrainingAlbanian;
    }

    public String getTematShqip() {
        return tematShqip;
    }

    public void setTematShqip(String tematShqip) {
        this.tematShqip = tematShqip;
    }

    public String getTematAnglisht() {
        return tematAnglisht;
    }

    public void setTematAnglisht(String tematAnglisht) {
        this.tematAnglisht = tematAnglisht;
    }

    public String getTematSerbisht() {
        return tematSerbisht;
    }

    public void setTematSerbisht(String tematSerbisht) {
        this.tematSerbisht = tematSerbisht;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTrainerat() {
        return trainerat;
    }

    public void setTrainerat(String trainerat) {
        this.trainerat = trainerat;
    }

    public String getTitleOfTrainingEnglish() {
        return titleOfTrainingEnglish;
    }

    public void setTitleOfTrainingEnglish(String titleOfTrainingEnglish) {
        this.titleOfTrainingEnglish = titleOfTrainingEnglish;
    }

    public String getTitleOfTrainingSerbian() {
        return titleOfTrainingSerbian;
    }

    public void setTitleOfTrainingSerbian(String titleOfTrainingSerbian) {
        this.titleOfTrainingSerbian = titleOfTrainingSerbian;
    }
    
}
