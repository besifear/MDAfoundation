/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "Participant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participant.findAllActive", query = "SELECT p FROM Participant p WHERE p.statusi='Active'"),
    @NamedQuery(name = "Participant.findAll", query = "SELECT p FROM Participant p WHERE p.statusi='Active' "),
    @NamedQuery(name = "Participant.findByParticipantID", query = "SELECT p FROM Participant p WHERE p.participantID = :participantID"),
    @NamedQuery(name = "Participant.findByIDNumber", query = "SELECT p FROM Participant p WHERE p.iDNumber = :iDNumber"),
    @NamedQuery(name = "Participant.findByName", query = "SELECT p FROM Participant p WHERE p.name = :name"),
    @NamedQuery(name = "Participant.findBySurname", query = "SELECT p FROM Participant p WHERE p.surname = :surname"),
    @NamedQuery(name = "Participant.findByParticipantAddress", query = "SELECT p FROM Participant p WHERE p.participantAddress = :participantAddress"),
    @NamedQuery(name = "Participant.findByCity", query = "SELECT p FROM Participant p WHERE p.city = :city"),
    @NamedQuery(name = "Participant.findByParticipantState", query = "SELECT p FROM Participant p WHERE p.participantState = :participantState"),
    @NamedQuery(name = "Participant.findByEmail", query = "SELECT p FROM Participant p WHERE p.email = :email"),
    @NamedQuery(name = "Participant.findByPhone", query = "SELECT p FROM Participant p WHERE p.phone = :phone"),
    @NamedQuery(name = "Participant.findBySex", query = "SELECT p FROM Participant p WHERE p.sex = :sex"),
    @NamedQuery(name = "Participant.findByZipCode", query = "SELECT p FROM Participant p WHERE p.zipCode = :zipCode"),
    @NamedQuery(name = "Participant.findByDob", query = "SELECT p FROM Participant p WHERE p.dob = :dob"),
    @NamedQuery(name = "Participant.findByPozita", query = "SELECT p FROM Participant p WHERE p.pozita = :pozita"),
    @NamedQuery(name = "Participant.findByStatusi", query = "SELECT p FROM Participant p WHERE p.statusi = :statusi")})
public class Participant implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participantID")
    private Collection<ParticipatingCompanyMember> participatingCompanyMemberCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Participant_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer participantID;
    @Basic(optional = false)
    @Column(name = "ID_Number")
    private long iDNumber;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "Surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "Participant_Address")
    private String participantAddress;
    @Basic(optional = false)
    @Column(name = "City")
    private String city;
    @Basic(optional = false)
    @Column(name = "Participant_State")
    private String participantState;
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "Sex")
    private String sex;
    @Column(name = "ZipCode")
    private String zipCode;
    @Basic(optional = false)
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @Column(name = "Pozita")
    private String pozita;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi="Active";

    public Participant() {
    }

    public Participant(Integer participantID) {
        this.participantID = participantID;
    }

    public Participant(Integer participantID, long iDNumber, String name, String surname, String participantAddress, String city, String participantState, String sex, Date dob, String pozita, String statusi) {
        this.participantID = participantID;
        this.iDNumber = iDNumber;
        this.name = name;
        this.surname = surname;
        this.participantAddress = participantAddress;
        this.city = city;
        this.participantState = participantState;
        this.sex = sex;
        this.dob = dob;
        this.pozita = pozita;
        this.statusi = statusi;
    }

    public Integer getParticipantID() {
        return participantID;
    }

    public void setParticipantID(Integer participantID) {
        this.participantID = participantID;
    }

    public long getIDNumber() {
        return iDNumber;
    }

    public void setIDNumber(long iDNumber) {
        this.iDNumber = iDNumber;
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

    public String getParticipantAddress() {
        return participantAddress;
    }

    public void setParticipantAddress(String participantAddress) {
        this.participantAddress = participantAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParticipantState() {
        return participantState;
    }

    public void setParticipantState(String participantState) {
        this.participantState = participantState;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPozita() {
        return pozita;
    }

    public void setPozita(String pozita) {
        this.pozita = pozita;
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
        hash += (participantID != null ? participantID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participant)) {
            return false;
        }
        Participant other = (Participant) object;
        if ((this.participantID == null && other.participantID != null) || (this.participantID != null && !this.participantID.equals(other.participantID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Participant[ participantID=" + participantID + " ]";
    }

    @XmlTransient
    public Collection<ParticipatingCompanyMember> getParticipatingCompanyMemberCollection() {
        return participatingCompanyMemberCollection;
    }

    public void setParticipatingCompanyMemberCollection(Collection<ParticipatingCompanyMember> participatingCompanyMemberCollection) {
        this.participatingCompanyMemberCollection = participatingCompanyMemberCollection;
    }
    
}
