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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Besniku
 */
@Entity
@Table(name = "TrainersByTp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrainersByTp.findAll", query = "SELECT t FROM TrainersByTp t"),
    @NamedQuery(name = "TrainersByTp.findByTProcessID", query = "SELECT t FROM TrainersByTp t WHERE t.tProcessID = :tProcessID"),
    @NamedQuery(name = "TrainersByTp.findByTrainers", query = "SELECT t FROM TrainersByTp t WHERE t.trainers = :trainers")})
public class TrainersByTp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "TProcess_ID")
    @Id
    private String tProcessID;
    @Column(name = "Trainers")
    private String trainers;

    public TrainersByTp() {
    }

    public String getTProcessID() {
        return tProcessID;
    }

    public void setTProcessID(String tProcessID) {
        this.tProcessID = tProcessID;
    }

    public String getTrainers() {
        return trainers;
    }

    public void setTrainers(String trainers) {
        this.trainers = trainers;
    }
    
}
