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
@Table(name = "TTrainer_Evaluation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TTrainerEvaluation.findAllActive", query = "SELECT t FROM TTrainerEvaluation t WHERE t.statusi='Active'"),
    @NamedQuery(name = "TTrainerEvaluation.findAll", query = "SELECT t FROM TTrainerEvaluation t"),
    @NamedQuery(name = "TTrainerEvaluation.findByEvaluationID", query = "SELECT t FROM TTrainerEvaluation t WHERE t.evaluationID = :evaluationID"),
    @NamedQuery(name = "TTrainerEvaluation.findByTrainerPreperation", query = "SELECT t FROM TTrainerEvaluation t WHERE t.trainerPreperation = :trainerPreperation"),
    @NamedQuery(name = "TTrainerEvaluation.findByTrainerDiscussion", query = "SELECT t FROM TTrainerEvaluation t WHERE t.trainerDiscussion = :trainerDiscussion"),
    @NamedQuery(name = "TTrainerEvaluation.findByPresentation", query = "SELECT t FROM TTrainerEvaluation t WHERE t.presentation = :presentation"),
    @NamedQuery(name = "TTrainerEvaluation.findByStatusi", query = "SELECT t FROM TTrainerEvaluation t WHERE t.statusi = :statusi")})
public class TTrainerEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Evaluation_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer evaluationID;
    @Basic(optional = false)
    @Column(name = "Trainer_Preperation")
    private int trainerPreperation;
    @Basic(optional = false)
    @Column(name = "Trainer_Discussion")
    private int trainerDiscussion;
    @Basic(optional = false)
    @Column(name = "Presentation")
    private int presentation;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi="Active";
    @JoinColumn(name = "PCM_ID", referencedColumnName = "PCM_ID")
    @ManyToOne(optional = false)
    private ParticipatingCompanyMember pcmId;
    @JoinColumn(name = "Trainer_ID", referencedColumnName = "Trainer_ID")
    @ManyToOne(optional = false)
    private Trainer trainerID;
    @JoinColumn(name = "TProcess_ID", referencedColumnName = "TProcess_ID")
    @ManyToOne(optional = false)
    private TrainingProcess tProcessID;

    public TTrainerEvaluation() {
    }

    public TTrainerEvaluation(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public TTrainerEvaluation(Trainer trainerID) {
        this.trainerID=trainerID;
    }

    public Integer getEvaluationID() {
        return evaluationID;
    }

    public void setEvaluationID(Integer evaluationID) {
        this.evaluationID = evaluationID;
    }

    public int getTrainerPreperation() {
        return trainerPreperation;
    }

    public void setTrainerPreperation(int trainerPreperation) {
        this.trainerPreperation = trainerPreperation;
    }

    public int getTrainerDiscussion() {
        return trainerDiscussion;
    }

    public void setTrainerDiscussion(int trainerDiscussion) {
        this.trainerDiscussion = trainerDiscussion;
    }

    public int getPresentation() {
        return presentation;
    }

    public void setPresentation(int presentation) {
        this.presentation = presentation;
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

    public Trainer getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(Trainer trainerID) {
        this.trainerID = trainerID;
    }

    public TrainingProcess getTProcessID() {
        return tProcessID;
    }

    public void setTProcessID(TrainingProcess tProcessID) {
        this.tProcessID = tProcessID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evaluationID != null ? evaluationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TTrainerEvaluation)) {
            return false;
        }
        TTrainerEvaluation other = (TTrainerEvaluation) object;
        return (other.getTrainerID().getTrainerID()==getTrainerID().getTrainerID() && 
                other.getTProcessID().getTProcessID().equals(getTProcessID().getTProcessID()));
    }

    @Override
    public String toString() {
        return "ejb.TTrainerEvaluation[ evaluationID=" + evaluationID + " ]";
    }

    public Object getPartTeamID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
