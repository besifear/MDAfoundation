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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Besnik
 */
@Entity
@Table(name = "Training_Project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainingProject.findAll", query = "SELECT t FROM TrainingProject t"),
    @NamedQuery(name = "TrainingProject.findByTpId", query = "SELECT t FROM TrainingProject t WHERE t.tpId = :tpId"),
    @NamedQuery(name = "TrainingProject.findByStatusi", query = "SELECT t FROM TrainingProject t WHERE t.statusi = :statusi")})
public class TrainingProject implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainingProjectID")
    private Collection<TrainingProcess> trainingProcessCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TP_ID")
    private Integer tpId;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi="Active";
    @JoinColumn(name = "Project_ID", referencedColumnName = "Project_ID")
    @ManyToOne(optional = false)
    private Project projectID;
    @JoinColumn(name = "Training_ID", referencedColumnName = "Training_ID")
    @ManyToOne(optional = false)
    private Training trainingID;

    public TrainingProject() {
    }

    public TrainingProject(Integer tpId) {
        this.tpId = tpId;
    }

    public TrainingProject(Integer tpId, String statusi) {
        this.tpId = tpId;
        this.statusi = statusi;
    }

    public TrainingProject(int tpId, Training trainingID, Project projectID) {
        this.tpId=tpId;
        this.trainingID=trainingID;
        this.projectID=projectID;
    }

    public Integer getTpId() {
        return tpId;
    }

    public void setTpId(Integer tpId) {
        this.tpId = tpId;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public Project getProjectID() {
        return projectID;
    }

    public void setProjectID(Project projectID) {
        this.projectID = projectID;
    }

    public Training getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(Training trainingID) {
        this.trainingID = trainingID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpId != null ? tpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrainingProject)) {
            return false;
        }
        TrainingProject other = (TrainingProject) object;
        if ((this.tpId == null && other.tpId != null) || (this.tpId != null && !this.tpId.equals(other.tpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TrainingProject[ tpId=" + tpId + " ]";
    }

    @XmlTransient
    public Collection<TrainingProcess> getTrainingProcessCollection() {
        return trainingProcessCollection;
    }

    public void setTrainingProcessCollection(Collection<TrainingProcess> trainingProcessCollection) {
        this.trainingProcessCollection = trainingProcessCollection;
    }
    
}
