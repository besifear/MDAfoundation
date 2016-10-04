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
@Table(name = "Topics_Combo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TopicsCombo.findAll", query = "SELECT t FROM TopicsCombo t"),
    @NamedQuery(name = "TopicsCombo.findByTopicComboID", query = "SELECT t FROM TopicsCombo t WHERE t.topicComboID = :topicComboID"),
    @NamedQuery(name = "TopicsCombo.findByStatusi", query = "SELECT t FROM TopicsCombo t WHERE t.statusi = :statusi")})
public class TopicsCombo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Topic_Combo_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer topicComboID;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private String statusi= "Active";
    @JoinColumn(name = "Topic_ID", referencedColumnName = "Topic_ID")
    @ManyToOne
    private TopicsCovered topicID;
    @JoinColumn(name = "TProcess_ID", referencedColumnName = "TProcess_ID")
    @ManyToOne
    private TrainingProcess tProcessID;

    public TopicsCombo() {
    }

    public TopicsCombo(Integer topicComboID) {
        this.topicComboID = topicComboID;
    }

    public TopicsCombo(Integer topicComboID, String statusi) {
        this.topicComboID = topicComboID;
        this.statusi = statusi;
    }

    public Integer getTopicComboID() {
        return topicComboID;
    }

    public void setTopicComboID(Integer topicComboID) {
        this.topicComboID = topicComboID;
    }

    public String getStatusi() {
        return statusi;
    }

    public void setStatusi(String statusi) {
        this.statusi = statusi;
    }

    public TopicsCovered getTopicID() {
        return topicID;
    }

    public void setTopicID(TopicsCovered topicID) {
        this.topicID = topicID;
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
        hash += (topicComboID != null ? topicComboID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TopicsCombo)) {
            return false;
        }
        TopicsCombo other = (TopicsCombo) object;
        if ((this.topicComboID == null && other.topicComboID != null) || (this.topicComboID != null && !this.topicComboID.equals(other.topicComboID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.TopicsCombo[ topicComboID=" + topicComboID + " ]";
    }
    
}
