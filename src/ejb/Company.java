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
@Table(name = "Company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByCompanyID", query = "SELECT c FROM Company c WHERE c.companyID = :companyID"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
    @NamedQuery(name = "Company.findByCompanytype", query = "SELECT c FROM Company c WHERE c.companytype = :companytype"),
    @NamedQuery(name = "Company.findByStatusi", query = "SELECT c FROM Company c WHERE c.statusi = :statusi")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Company_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer companyID;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Column(name = "Companytype")
    private String companytype;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi= "Active";
    @OneToMany(mappedBy = "companyID")
    private Collection<CTPcombo> cTPcomboCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyID")
    private Collection<ParticipatingCompanyMember> participatingCompanyMemberCollection;

    public Company() {
    }

    public Company(Integer companyID) {
        this.companyID = companyID;
    }

    public Company(Integer companyID, String name, String statusi) {
        this.companyID = companyID;
        this.name = name;
        this.statusi = statusi;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    @XmlTransient
    public Collection<CTPcombo> getCTPcomboCollection() {
        return cTPcomboCollection;
    }

    public void setCTPcomboCollection(Collection<CTPcombo> cTPcomboCollection) {
        this.cTPcomboCollection = cTPcomboCollection;
    }

    @XmlTransient
    public Collection<ParticipatingCompanyMember> getParticipatingCompanyMemberCollection() {
        return participatingCompanyMemberCollection;
    }

    public void setParticipatingCompanyMemberCollection(Collection<ParticipatingCompanyMember> participatingCompanyMemberCollection) {
        this.participatingCompanyMemberCollection = participatingCompanyMemberCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyID != null ? companyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyID == null && other.companyID != null) || (this.companyID != null && !this.companyID.equals(other.companyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Company[ companyID=" + companyID + " ]";
    }
    
}
