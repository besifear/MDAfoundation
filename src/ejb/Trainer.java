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
@Table(name = "Trainer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trainer.findAll", query = "SELECT t FROM Trainer t"),
    @NamedQuery(name = "Trainer.findByTrainerID", query = "SELECT t FROM Trainer t WHERE t.trainerID = :trainerID"),
    @NamedQuery(name = "Trainer.findByName", query = "SELECT t FROM Trainer t WHERE t.name = :name"),
    @NamedQuery(name = "Trainer.findBySurname", query = "SELECT t FROM Trainer t WHERE t.surname = :surname"),
    @NamedQuery(name = "Trainer.findByStatusi", query = "SELECT t FROM Trainer t WHERE t.statusi = :statusi")})
public class Trainer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Trainer_ID")
    private Integer trainerID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi= "Active";
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainerID")
    private Collection<TrainerContact> trainerContactCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainerID")
    private Collection<TrainerTeamCombo> trainerTeamComboCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainerID")
    private Collection<TTrainerEvaluation> tTrainerEvaluationCollection;

    public Trainer() {
    }

    public Trainer(Integer trainerID) {
        this.trainerID = trainerID;
    }

    public Trainer(Integer trainerID, String name, String surname, String statusi) {
        this.trainerID = trainerID;
        this.name = name;
        this.surname = surname;
        this.statusi = statusi;
    }

    public Integer getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(Integer trainerID) {
        this.trainerID = trainerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    @XmlTransient
    public Collection<TrainerContact> getTrainerContactCollection() {
        return trainerContactCollection;
    }

    public void setTrainerContactCollection(Collection<TrainerContact> trainerContactCollection) {
        this.trainerContactCollection = trainerContactCollection;
    }

    @XmlTransient
    public Collection<TrainerTeamCombo> getTrainerTeamComboCollection() {
        return trainerTeamComboCollection;
    }

    public void setTrainerTeamComboCollection(Collection<TrainerTeamCombo> trainerTeamComboCollection) {
        this.trainerTeamComboCollection = trainerTeamComboCollection;
    }

    @XmlTransient
    public Collection<TTrainerEvaluation> getTTrainerEvaluationCollection() {
        return tTrainerEvaluationCollection;
    }

    public void setTTrainerEvaluationCollection(Collection<TTrainerEvaluation> tTrainerEvaluationCollection) {
        this.tTrainerEvaluationCollection = tTrainerEvaluationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trainerID != null ? trainerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trainer)) {
            return false;
        }
        Trainer other = (Trainer) object;
        if ((this.trainerID == null && other.trainerID != null) || (this.trainerID != null && !this.trainerID.equals(other.trainerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Trainer[ trainerID=" + trainerID + " ]";
    }
    
}
