/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.view;

import java.io.Serializable;
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
@Table(name = "Test_Admin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestAdmin.findAll", query = "SELECT t FROM TestAdmin t"),
    @NamedQuery(name = "TestAdmin.findByNumriUserave", query = "SELECT t FROM TestAdmin t WHERE t.numriUserave = :numriUserave")})
public class TestAdmin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "Numri_Userave")
    @Id
    private Integer numriUserave;

    public TestAdmin() {
    }

    public Integer getNumriUserave() {
        return numriUserave;
    }

    public void setNumriUserave(Integer numriUserave) {
        this.numriUserave = numriUserave;
    }
    
}
