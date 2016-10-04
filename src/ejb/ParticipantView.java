package ejb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ARDITI
 */
@Entity
@Table(name = "Participant_View")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParticipantView.findAll", query = "SELECT p FROM ParticipantView p ORDER BY p.participantID"),
    @NamedQuery(name = "ParticipantView.findByTProcessID", query = "SELECT p FROM ParticipantView p WHERE p.tProcessID = :tProcessID"),
    @NamedQuery(name = "ParticipantView.findByParticipantID", query = "SELECT p FROM ParticipantView p WHERE p.participantID = :participantID"),
    @NamedQuery(name = "ParticipantView.findByPjesmarresi", query = "SELECT p FROM ParticipantView p WHERE p.pjesmarresi = :pjesmarresi"),
    @NamedQuery(name = "ParticipantView.findByGjinia", query = "SELECT p FROM ParticipantView p WHERE p.gjinia = :gjinia"),
    @NamedQuery(name = "ParticipantView.findByDataeLindj\u00ebs", query = "SELECT p FROM ParticipantView p WHERE p.dataeLindj\u00ebs = :dataeLindj\u00ebs"),
    @NamedQuery(name = "ParticipantView.findByIDNumber", query = "SELECT p FROM ParticipantView p WHERE p.iDNumber = :iDNumber"),
    @NamedQuery(name = "ParticipantView.findByEmail", query = "SELECT p FROM ParticipantView p WHERE p.email = :email"),
    @NamedQuery(name = "ParticipantView.findByPhone", query = "SELECT p FROM ParticipantView p WHERE p.phone = :phone"),
    @NamedQuery(name = "ParticipantView.findByParticipantState", query = "SELECT p FROM ParticipantView p WHERE p.participantState = :participantState"),
    @NamedQuery(name = "ParticipantView.findByCity", query = "SELECT p FROM ParticipantView p WHERE p.city = :city"),
    @NamedQuery(name = "ParticipantView.findByZipCode", query = "SELECT p FROM ParticipantView p WHERE p.zipCode = :zipCode"),
    @NamedQuery(name = "ParticipantView.findByParticipantAddress", query = "SELECT p FROM ParticipantView p WHERE p.participantAddress = :participantAddress"),
    @NamedQuery(name = "ParticipantView.findByTraineri", query = "SELECT p FROM ParticipantView p WHERE p.traineri = :traineri"),
    @NamedQuery(name = "ParticipantView.findByPresentation", query = "SELECT p FROM ParticipantView p WHERE p.presentation = :presentation"),
    @NamedQuery(name = "ParticipantView.findByTrainerDiscussion", query = "SELECT p FROM ParticipantView p WHERE p.trainerDiscussion = :trainerDiscussion"),
    @NamedQuery(name = "ParticipantView.findByTrainerPreperation", query = "SELECT p FROM ParticipantView p WHERE p.trainerPreperation = :trainerPreperation")})
public class ParticipantView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "TProcess_ID")
    
    private String tProcessID;
    @Basic(optional = false)
    @Column(name = "Participant_ID")
    private int participantID;
    @Basic(optional = false)
    @Id
    @Column(name = "Pjesmarresi")
    private String pjesmarresi;
    @Basic(optional = false)
    @Column(name = "Gjinia")
    private String gjinia;
    @Basic(optional = false)
    @Column(name = "Data_e_Lindj\u00ebs")
    @Temporal(TemporalType.DATE)
    private Date dataeLindjës;
    @Basic(optional = false)
    @Column(name = "ID_Number")
    private long iDNumber;
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "Participant_State")
    private String participantState;
    @Basic(optional = false)
    @Column(name = "City")
    private String city;
    @Column(name = "ZipCode")
    private String zipCode;
    @Basic(optional = false)
    @Column(name = "Participant_Address")
    private String participantAddress;
    @Basic(optional = false)
    @Id
    @Column(name = "Traineri")
    private String traineri;
    @Basic(optional = false)
    @Id
    @Column(name = "Presentation")
    private int presentation;
    @Basic(optional = false)
    @Id
    @Column(name = "Trainer_Discussion")
    private int trainerDiscussion;
    @Basic(optional = false)
    @Id
    @Column(name = "Trainer_Preperation")
    private int trainerPreperation;

    public ParticipantView() {
    }

    public String getTProcessID() {
        return tProcessID;
    }

    public void setTProcessID(String tProcessID) {
        this.tProcessID = tProcessID;
    }

    public int getParticipantID() {
        return participantID;
    }

    public void setParticipantID(int participantID) {
        this.participantID = participantID;
    }

    public String getPjesmarresi() {
        return pjesmarresi;
    }

    public void setPjesmarresi(String pjesmarresi) {
        this.pjesmarresi = pjesmarresi;
    }

    public String getGjinia() {
        return gjinia;
    }

    public void setGjinia(String gjinia) {
        this.gjinia = gjinia;
    }

    public Date getDataeLindjës() {
        return dataeLindjës;
    }

    public void setDataeLindjës(Date dataeLindjës) {
        this.dataeLindjës = dataeLindjës;
    }

    public long getIDNumber() {
        return iDNumber;
    }

    public void setIDNumber(long iDNumber) {
        this.iDNumber = iDNumber;
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

    public String getParticipantState() {
        return participantState;
    }

    public void setParticipantState(String participantState) {
        this.participantState = participantState;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getParticipantAddress() {
        return participantAddress;
    }

    public void setParticipantAddress(String participantAddress) {
        this.participantAddress = participantAddress;
    }

    public String getTraineri() {
        return traineri;
    }

    public void setTraineri(String traineri) {
        this.traineri = traineri;
    }

    public int getPresentation() {
        return presentation;
    }

    public void setPresentation(int presentation) {
        this.presentation = presentation;
    }

    public int getTrainerDiscussion() {
        return trainerDiscussion;
    }

    public void setTrainerDiscussion(int trainerDiscussion) {
        this.trainerDiscussion = trainerDiscussion;
    }

    public int getTrainerPreperation() {
        return trainerPreperation;
    }

    public void setTrainerPreperation(int trainerPreperation) {
        this.trainerPreperation = trainerPreperation;
    }
    
}
