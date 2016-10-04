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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "Training_Process")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainingProcess.findAll", query = "SELECT t FROM TrainingProcess t"),
    @NamedQuery(name = "TrainingProcess.findAllActive",query="SELECT t FROM TrainingProcess t WHERE t.statusi='Active'"),
    @NamedQuery(name = "TrainingProcess.findByTProcessID", query = "SELECT t FROM TrainingProcess t WHERE t.tProcessID = :tProcessID"),
    @NamedQuery(name = "TrainingProcess.findByPlace", query = "SELECT t FROM TrainingProcess t WHERE t.place = :place"),
    @NamedQuery(name = "TrainingProcess.findByStartDate", query = "SELECT t FROM TrainingProcess t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "TrainingProcess.findByEndDate", query = "SELECT t FROM TrainingProcess t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "TrainingProcess.findByStatusi", query = "SELECT t FROM TrainingProcess t WHERE t.statusi = :statusi")})
public class TrainingProcess implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TProcess_ID")
    private String tProcessID;
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
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi="Active";
    @JoinColumn(name = "Klienti_ID", referencedColumnName = "Client_ID")
    @ManyToOne(optional = false)
    private Client klientiID;
    @JoinColumn(name = "Team_ID", referencedColumnName = "Team_ID")
    @ManyToOne
    private Team teamID;
    @JoinColumn(name = "TrainersTeamID", referencedColumnName = "TrainersTeamID")
    @ManyToOne(optional = false)
    private TrainersTeam trainersTeamID;
    @JoinColumn(name = "TrainingProjectID", referencedColumnName = "TP_ID")
    @ManyToOne(optional = false)
    private TrainingProject trainingProjectID;

    public TrainingProcess() {
    }

    public TrainingProcess(String tProcessID) {
        this.tProcessID = tProcessID;
    }

    public TrainingProcess(String tProcessID, String place, Date startDate, Date endDate, String statusi) {
        this.tProcessID = tProcessID;
        this.place = place;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusi = statusi;
    }

    public String getTProcessID() {
        return tProcessID;
    }

    public void setTProcessID(String tProcessID) {
        this.tProcessID = tProcessID;
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

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public Client getKlientiID() {
        return klientiID;
    }

    public void setKlientiID(Client klientiID) {
        this.klientiID = klientiID;
    }

    public Team getTeamID() {
        return teamID;
    }

    public void setTeamID(Team teamID) {
        this.teamID = teamID;
    }

    public TrainersTeam getTrainersTeamID() {
        return trainersTeamID;
    }

    public void setTrainersTeamID(TrainersTeam trainersTeamID) {
        this.trainersTeamID = trainersTeamID;
    }

    public TrainingProject getTrainingProjectID() {
        return trainingProjectID;
    }

    public void setTrainingProjectID(TrainingProject trainingProjectID) {
        this.trainingProjectID = trainingProjectID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tProcessID != null ? tProcessID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrainingProcess)) {
            return false;
        }
        TrainingProcess other = (TrainingProcess) object;
        if ((this.tProcessID == null && other.tProcessID != null) || (this.tProcessID != null && !this.tProcessID.equals(other.tProcessID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TrainingProcess[ tProcessID=" + tProcessID + " ]";
    }
    
}
