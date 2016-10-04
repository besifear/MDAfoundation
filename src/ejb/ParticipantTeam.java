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
 * @author Besniku
 */
@Entity
@Table(name = "Participant_Team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipantTeam.findAll", query = "SELECT p FROM ParticipantTeam p"),
    @NamedQuery(name = "ParticipantTeam.findByPartTeamID", query = "SELECT p FROM ParticipantTeam p WHERE p.partTeamID = :partTeamID"),
    @NamedQuery(name = "ParticipantTeam.findByStatusi", query = "SELECT p FROM ParticipantTeam p WHERE p.statusi = :statusi")})
public class ParticipantTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PartTeam_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer partTeamID;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi="Active";
    @JoinColumn(name = "PCM_ID", referencedColumnName = "PCM_ID")
    @ManyToOne
    private ParticipatingCompanyMember pcmId;
    @JoinColumn(name = "Team_ID", referencedColumnName = "Team_ID")
    @ManyToOne
    private Team teamID;

    public ParticipantTeam() {
    }

    public ParticipantTeam(Integer partTeamID) {
        this.partTeamID = partTeamID;
    }

    public ParticipantTeam(Integer partTeamID, String statusi) {
        this.partTeamID = partTeamID;
        this.statusi = statusi;
    }

    public Integer getPartTeamID() {
        return partTeamID;
    }

    public void setPartTeamID(Integer partTeamID) {
        this.partTeamID = partTeamID;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public ParticipatingCompanyMember getPcmId() {
        return pcmId;
    }

    public void setPcmId(ParticipatingCompanyMember pcmId) {
        this.pcmId = pcmId;
    }

    public Team getTeamID() {
        return teamID;
    }

    public void setTeamID(Team teamID) {
        this.teamID = teamID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partTeamID != null ? partTeamID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipantTeam)) {
            return false;
        }
        ParticipantTeam other = (ParticipantTeam) object;
        return other.pcmId.getPcmId()==pcmId.getPcmId() && other.teamID.getTeamID()==teamID.getTeamID();
    }

    @Override
    public String toString() {
        return "ejb.ParticipantTeam[ partTeamID=" + partTeamID + " ]";
    }
    
}
