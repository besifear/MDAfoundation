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
 * @author Besnik
 */
@Entity
@Table(name = "TrainersTeam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainersTeam.findAll", query = "SELECT t FROM TrainersTeam t"),
    @NamedQuery(name = "TrainersTeam.findByTrainersTeamID", query = "SELECT t FROM TrainersTeam t WHERE t.trainersTeamID = :trainersTeamID"),
    @NamedQuery(name = "TrainersTeam.findByStatusi", query = "SELECT t FROM TrainersTeam t WHERE t.statusi = :statusi")})
public class TrainersTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TrainersTeamID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer trainersTeamID;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi= "Active";
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainersTeamID")
    private Collection<TrainingProcess> trainingProcessCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainersTeamID")
    private Collection<TrainerTeamCombo> trainerTeamComboCollection;

    public TrainersTeam() {
    }

    public TrainersTeam(Integer trainersTeamID) {
        this.trainersTeamID = trainersTeamID;
    }

    public TrainersTeam(Integer trainersTeamID, String statusi) {
        this.trainersTeamID = trainersTeamID;
        this.statusi = statusi;
    }

    public Integer getTrainersTeamID() {
        return trainersTeamID;
    }

    public void setTrainersTeamID(Integer trainersTeamID) {
        this.trainersTeamID = trainersTeamID;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    @XmlTransient
    public Collection<TrainingProcess> getTrainingProcessCollection() {
        return trainingProcessCollection;
    }

    public void setTrainingProcessCollection(Collection<TrainingProcess> trainingProcessCollection) {
        this.trainingProcessCollection = trainingProcessCollection;
    }

    @XmlTransient
    public Collection<TrainerTeamCombo> getTrainerTeamComboCollection() {
        return trainerTeamComboCollection;
    }

    public void setTrainerTeamComboCollection(Collection<TrainerTeamCombo> trainerTeamComboCollection) {
        this.trainerTeamComboCollection = trainerTeamComboCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trainersTeamID != null ? trainersTeamID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrainersTeam)) {
            return false;
        }
        TrainersTeam other = (TrainersTeam) object;
        if ((this.trainersTeamID == null && other.trainersTeamID != null) || (this.trainersTeamID != null && !this.trainersTeamID.equals(other.trainersTeamID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TrainersTeam[ trainersTeamID=" + trainersTeamID + " ]";
    }
    
}
