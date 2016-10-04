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
@Table(name = "Team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
    @NamedQuery(name = "Team.findByTeamID", query = "SELECT t FROM Team t WHERE t.teamID = :teamID"),
    @NamedQuery(name = "Team.findByStatusi", query = "SELECT t FROM Team t WHERE t.statusi = :statusi")})
public class Team implements Serializable {

    @OneToMany(mappedBy = "teamID")
    private Collection<ParticipantTeam> participantTeamCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teamID")
    private Collection<TrainingProcess> trainingProcessCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Team_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer teamID;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi= "Active";

    public Team() {
    }

    public Team(Integer teamID) {
        this.teamID = teamID;
    }

    public Team(Integer teamID, String statusi) {
        this.teamID = teamID;
        this.statusi = statusi;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
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
        hash += (teamID != null ? teamID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.teamID == null && other.teamID != null) || (this.teamID != null && !this.teamID.equals(other.teamID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Team[ teamID=" + teamID + " ]";
    }

    @XmlTransient
    public Collection<TrainingProcess> getTrainingProcessCollection() {
        return trainingProcessCollection;
    }

    public void setTrainingProcessCollection(Collection<TrainingProcess> trainingProcessCollection) {
        this.trainingProcessCollection = trainingProcessCollection;
    }

    @XmlTransient
    public Collection<ParticipantTeam> getParticipantTeamCollection() {
        return participantTeamCollection;
    }

    public void setParticipantTeamCollection(Collection<ParticipantTeam> participantTeamCollection) {
        this.participantTeamCollection = participantTeamCollection;
    }
    
}
