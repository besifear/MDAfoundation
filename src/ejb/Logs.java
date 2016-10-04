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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "Logs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logs.findAll", query = "SELECT l FROM Logs l"),
    @NamedQuery(name = "Logs.findByLogsID", query = "SELECT l FROM Logs l WHERE l.logsID = :logsID"),
    @NamedQuery(name = "Logs.findByUseri", query = "SELECT l FROM Logs l WHERE l.useri = :useri"),
    @NamedQuery(name = "Logs.findByDita", query = "SELECT l FROM Logs l WHERE l.dita = :dita"),
    @NamedQuery(name = "Logs.findByKoha", query = "SELECT l FROM Logs l WHERE l.koha = :koha"),
    @NamedQuery(name = "Logs.findByLloji", query = "SELECT l FROM Logs l WHERE l.lloji = :lloji"),
    @NamedQuery(name = "Logs.findByMesazhi", query = "SELECT l FROM Logs l WHERE l.mesazhi = :mesazhi"),
    @NamedQuery(name = "Logs.findByStatusi", query = "SELECT l FROM Logs l WHERE l.statusi = :statusi")})
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LogsID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer logsID;
    @Column(name = "Useri")
    private String useri;
    @Column(name = "Dita")
    @Temporal(TemporalType.DATE)
    private Date dita;
    @Column(name = "Koha")
    @Temporal(TemporalType.TIME)
    private Date koha;
    @Column(name = "Lloji")
    private String lloji;
    @Column(name = "Mesazhi")
    private String mesazhi;
    @Column(name = "Statusi")
    private String statusi="Active";
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne
    private Users username;

    public Logs() {
    }

    public Logs(Integer logsID) {
        this.logsID = logsID;
    }

    public Integer getLogsID() {
        return logsID;
    }

    public void setLogsID(Integer logsID) {
        this.logsID = logsID;
    }

    public String getUseri() {
        return useri;
    }

    public void setUseri(String useri) {
        this.useri = useri;
    }

    public Date getDita() {
        return dita;
    }

    public void setDita(Date dita) {
        this.dita = dita;
    }

    public Date getKoha() {
        return koha;
    }

    public void setKoha(Date koha) {
        this.koha = koha;
    }

    public String getLloji() {
        return lloji;
    }

    public void setLloji(String lloji) {
        this.lloji = lloji;
    }

    public String getMesazhi() {
        return mesazhi;
    }

    public void setMesazhi(String mesazhi) {
        this.mesazhi = mesazhi;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logsID != null ? logsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logs)) {
            return false;
        }
        Logs other = (Logs) object;
        if ((this.logsID == null && other.logsID != null) || (this.logsID != null && !this.logsID.equals(other.logsID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Logs[ logsID=" + logsID + " ]";
    }
    
}
