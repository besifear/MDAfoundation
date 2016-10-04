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
@Table(name = "TrainerContact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainerContact.findAll", query = "SELECT t FROM TrainerContact t"),
    @NamedQuery(name = "TrainerContact.findByContactID", query = "SELECT t FROM TrainerContact t WHERE t.contactID = :contactID"),
    @NamedQuery(name = "TrainerContact.findByContactType", query = "SELECT t FROM TrainerContact t WHERE t.contactType = :contactType"),
    @NamedQuery(name = "TrainerContact.findByContactValue", query = "SELECT t FROM TrainerContact t WHERE t.contactValue = :contactValue"),
    @NamedQuery(name = "TrainerContact.findByStatusi", query = "SELECT t FROM TrainerContact t WHERE t.statusi = :statusi")})
public class TrainerContact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Contact_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer contactID;
    @Basic(optional = false)
    @Column(name = "Contact_Type")
    private String contactType;
    @Basic(optional = false)
    @Column(name = "Contact_Value")
    private String contactValue;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi= "Active";
    @JoinColumn(name = "Trainer_ID", referencedColumnName = "Trainer_ID")
    @ManyToOne(optional = false)
    private Trainer trainerID;

    public TrainerContact() {
    }

    public TrainerContact(Integer contactID) {
        this.contactID = contactID;
    }

    public TrainerContact(Integer contactID, String contactType, String contactValue, String statusi) {
        this.contactID = contactID;
        this.contactType = contactType;
        this.contactValue = contactValue;
        this.statusi = statusi;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String contactValue) {
        this.contactValue = contactValue;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactID != null ? contactID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrainerContact)) {
            return false;
        }
        TrainerContact other = (TrainerContact) object;
        if ((this.contactID == null && other.contactID != null) || (this.contactID != null && !this.contactID.equals(other.contactID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TrainerContact[ contactID=" + contactID + " ]";
    }
    
}
