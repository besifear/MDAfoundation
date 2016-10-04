/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "Training")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Training.findAll", query = "SELECT t FROM Training t"),
    @NamedQuery(name = "Training.findByTrainingID", query = "SELECT t FROM Training t WHERE t.trainingID = :trainingID"),
    @NamedQuery(name = "Training.findByTitleOfTrainingAlbanian", query = "SELECT t FROM Training t WHERE t.titleOfTrainingAlbanian = :titleOfTrainingAlbanian"),
    @NamedQuery(name = "Training.findByTitleOfTrainingEnglish", query = "SELECT t FROM Training t WHERE t.titleOfTrainingEnglish = :titleOfTrainingEnglish"),
    @NamedQuery(name = "Training.findByTitleOfTrainingSerbian", query = "SELECT t FROM Training t WHERE t.titleOfTrainingSerbian = :titleOfTrainingSerbian"),
    @NamedQuery(name = "Training.findByStatusi", query = "SELECT t FROM Training t WHERE t.statusi = :statusi")})
public class Training implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingID")
    private Collection<TrainingProject> trainingProjectCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Training_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer trainingID;
    @Column(name = "TitleOfTraining_Albanian")
    private String titleOfTrainingAlbanian;
    @Column(name = "TitleOfTraining_English")
    private String titleOfTrainingEnglish;
    @Column(name = "TitleOfTraining_Serbian")
    private String titleOfTrainingSerbian;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi="Active";

    public Training() {
    }

    public Training(Integer trainingID) {
        this.trainingID = trainingID;
    }

    public Training(Integer trainingID, String statusi) {
        this.trainingID = trainingID;
        this.statusi = statusi;
    }

    public Training(String titleOfTrainingAlbanian, String titleOfTrainingEnglish, String titleOfTrainingSerbian) {
        this.titleOfTrainingAlbanian=titleOfTrainingAlbanian;
        this.titleOfTrainingEnglish=titleOfTrainingEnglish;
        this.titleOfTrainingSerbian=titleOfTrainingSerbian;
    }

    public Integer getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(Integer trainingID) {
        this.trainingID = trainingID;
    }

    public String getTitleOfTrainingAlbanian() {
        return titleOfTrainingAlbanian;
    }

    public void setTitleOfTrainingAlbanian(String titleOfTrainingAlbanian) {
        this.titleOfTrainingAlbanian = titleOfTrainingAlbanian;
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

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trainingID != null ? trainingID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Training)) {
            return false;
        }
        Training other = (Training) object;
        if ((this.trainingID == null && other.trainingID != null) || (this.trainingID != null && !this.trainingID.equals(other.trainingID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Training[ trainingID=" + trainingID + " ]";
    }

    @XmlTransient
    public Collection<TrainingProject> getTrainingProjectCollection() {
        return trainingProjectCollection;
    }

    public void setTrainingProjectCollection(Collection<TrainingProject> trainingProjectCollection) {
        this.trainingProjectCollection = trainingProjectCollection;
    }
    
}
