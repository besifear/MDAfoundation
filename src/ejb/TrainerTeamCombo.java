/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Besnik
 */
@Entity
@Table(name = "TrainerTeamCombo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainerTeamCombo.findAll", query = "SELECT t FROM TrainerTeamCombo t"),
    @NamedQuery(name = "TrainerTeamCombo.findByTrainerTeamComboID", query = "SELECT t FROM TrainerTeamCombo t WHERE t.trainerTeamComboID = :trainerTeamComboID"),
    @NamedQuery(name = "TrainerTeamCombo.findByStatusi", query = "SELECT t FROM TrainerTeamCombo t WHERE t.statusi = :statusi")})
public class TrainerTeamCombo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TrainerTeamComboID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer trainerTeamComboID;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi= "Active";
    @JoinColumn(name = "Trainer_ID", referencedColumnName = "Trainer_ID")
    @ManyToOne(optional = false)
    private Trainer trainerID;
    @JoinColumn(name = "TrainersTeamID", referencedColumnName = "TrainersTeamID")
    @ManyToOne(optional = false)
    private TrainersTeam trainersTeamID;

    public TrainerTeamCombo() {
    }

    public TrainerTeamCombo(Integer trainerTeamComboID) {
        this.trainerTeamComboID = trainerTeamComboID;
    }

    public TrainerTeamCombo(Integer trainerTeamComboID, String statusi) {
        this.trainerTeamComboID = trainerTeamComboID;
        this.statusi = statusi;
    }

    public Integer getTrainerTeamComboID() {
        return trainerTeamComboID;
    }

    public void setTrainerTeamComboID(Integer trainerTeamComboID) {
        this.trainerTeamComboID = trainerTeamComboID;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public Trainer getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(Trainer trainerID) {
        this.trainerID = trainerID;
    }

    public TrainersTeam getTrainersTeamID() {
        return trainersTeamID;
    }

    public void setTrainersTeamID(TrainersTeam trainersTeamID) {
        this.trainersTeamID = trainersTeamID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trainerTeamComboID != null ? trainerTeamComboID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrainerTeamCombo)) {
            return false;
        }
        TrainerTeamCombo other = (TrainerTeamCombo) object;
        if ((this.trainerTeamComboID == null && other.trainerTeamComboID != null) || (this.trainerTeamComboID != null && !this.trainerTeamComboID.equals(other.trainerTeamComboID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TrainerTeamCombo[ trainerTeamComboID=" + trainerTeamComboID + " ]";
    }
    
}
