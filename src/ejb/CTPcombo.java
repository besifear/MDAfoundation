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
@Table(name = "CTPcombo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CTPcombo.findAll", query = "SELECT c FROM CTPcombo c"),
    @NamedQuery(name = "CTPcombo.findByCtpcomboID", query = "SELECT c FROM CTPcombo c WHERE c.ctpcomboID = :ctpcomboID"),
    @NamedQuery(name = "CTPcombo.findByStatusi", query = "SELECT c FROM CTPcombo c WHERE c.statusi = :statusi")})
public class CTPcombo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ctpcomboID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer ctpcomboID;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi= "Active";
    @JoinColumn(name = "CompanyID", referencedColumnName = "Company_ID")
    @ManyToOne
    private Company companyID;
    @JoinColumn(name = "TProcess_ID", referencedColumnName = "TProcess_ID")
    @ManyToOne
    private TrainingProcess tProcessID;

    public CTPcombo() {
    }

    public CTPcombo(Integer ctpcomboID) {
        this.ctpcomboID = ctpcomboID;
    }

    public CTPcombo(Integer ctpcomboID, String statusi) {
        this.ctpcomboID = ctpcomboID;
        this.statusi = statusi;
    }

    public Integer getCtpcomboID() {
        return ctpcomboID;
    }

    public void setCtpcomboID(Integer ctpcomboID) {
        this.ctpcomboID = ctpcomboID;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
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
        hash += (ctpcomboID != null ? ctpcomboID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CTPcombo)) {
            return false;
        }
        CTPcombo other = (CTPcombo) object;
        if ((this.ctpcomboID == null && other.ctpcomboID != null) || (this.ctpcomboID != null && !this.ctpcomboID.equals(other.ctpcomboID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.CTPcombo[ ctpcomboID=" + ctpcomboID + " ]";
    }
    
}
