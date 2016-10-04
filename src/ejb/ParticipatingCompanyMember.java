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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ParticipatingCompanyMember")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipatingCompanyMember.findAll", query = "SELECT p FROM ParticipatingCompanyMember p"),
    @NamedQuery(name = "ParticipatingCompanyMember.findByPcmId", query = "SELECT p FROM ParticipatingCompanyMember p WHERE p.pcmId = :pcmId"),
    @NamedQuery(name = "ParticipatingCompanyMember.findByStatusi", query = "SELECT p FROM ParticipatingCompanyMember p WHERE p.statusi = :statusi")})
public class ParticipatingCompanyMember implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pcmId")
    private Collection<TTrainerEvaluation> tTrainerEvaluationCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PCM_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer pcmId;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi= "Active";
    @OneToMany(mappedBy = "pcmId")
    private Collection<ParticipantTeam> participantTeamCollection;
    @JoinColumn(name = "Company_ID", referencedColumnName = "Company_ID")
    @ManyToOne(optional = false)
    private Company companyID;
    @JoinColumn(name = "Participant_ID", referencedColumnName = "Participant_ID")
    @ManyToOne(optional = false)
    private Participant participantID;

    public ParticipatingCompanyMember() {
    }

    public ParticipatingCompanyMember(Integer pcmId) {
        this.pcmId = pcmId;
    }

    public ParticipatingCompanyMember(Integer pcmId, String statusi) {
        this.pcmId = pcmId;
        this.statusi = statusi;
    }

    public Integer getPcmId() {
        return pcmId;
    }

    public void setPcmId(Integer pcmId) {
        this.pcmId = pcmId;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    @XmlTransient
    public Collection<ParticipantTeam> getParticipantTeamCollection() {
        return participantTeamCollection;
    }

    public void setParticipantTeamCollection(Collection<ParticipantTeam> participantTeamCollection) {
        this.participantTeamCollection = participantTeamCollection;
    }

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    public Participant getParticipantID() {
        return participantID;
    }

    public void setParticipantID(Participant participantID) {
        this.participantID = participantID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pcmId != null ? pcmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParticipatingCompanyMember)) {
            return false;
        }
        ParticipatingCompanyMember other = (ParticipatingCompanyMember) object;
        if ((this.pcmId == null && other.pcmId != null) || (this.pcmId != null && !this.pcmId.equals(other.pcmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.ParticipatingCompanyMember[ pcmId=" + pcmId + " ]";
    }

    @XmlTransient
    public Collection<TTrainerEvaluation> getTTrainerEvaluationCollection() {
        return tTrainerEvaluationCollection;
    }

    public void setTTrainerEvaluationCollection(Collection<TTrainerEvaluation> tTrainerEvaluationCollection) {
        this.tTrainerEvaluationCollection = tTrainerEvaluationCollection;
    }
    
}
