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
@Table(name = "Topics_Covered")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TopicsCovered.findAll", query = "SELECT t FROM TopicsCovered t"),
    @NamedQuery(name = "TopicsCovered.findByTopicID", query = "SELECT t FROM TopicsCovered t WHERE t.topicID = :topicID"),
    @NamedQuery(name = "TopicsCovered.findByTopicCovered", query = "SELECT t FROM TopicsCovered t WHERE t.topicCovered = :topicCovered"),
    @NamedQuery(name = "TopicsCovered.findByLanguage", query = "SELECT t FROM TopicsCovered t WHERE t.language = :language"),
    @NamedQuery(name = "TopicsCovered.findByStatusi", query = "SELECT t FROM TopicsCovered t WHERE t.statusi = :statusi")})
public class TopicsCovered implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Topic_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer topicID;
    @Basic(optional = false)
    @Column(name = "Topic_Covered")
    private String topicCovered;
    @Basic(optional = false)
    @Column(name = "Language")
    private String language;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi="Active";

    public TopicsCovered() {
    }

    public TopicsCovered(Integer topicID) {
        this.topicID = topicID;
    }

    public TopicsCovered(String topicCovered, String language) {
        this.topicCovered = topicCovered;
        this.language = language;
    }

    public Integer getTopicID() {
        return topicID;
    }

    public void setTopicID(Integer topicID) {
        this.topicID = topicID;
    }

    public String getTopicCovered() {
        return topicCovered;
    }

    public void setTopicCovered(String topicCovered) {
        this.topicCovered = topicCovered;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        hash += (topicID != null ? topicID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TopicsCovered)) {
            return false;
        }
        TopicsCovered other = (TopicsCovered) object;
        if ((this.topicID == null && other.topicID != null) || (this.topicID != null && !this.topicID.equals(other.topicID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return topicCovered;
    }
    
}
