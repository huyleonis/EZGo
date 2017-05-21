/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Agenda", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a")
    , @NamedQuery(name = "Agenda.findByAgendaID", query = "SELECT a FROM Agenda a WHERE a.agendaID = :agendaID")
    , @NamedQuery(name = "Agenda.findByName", query = "SELECT a FROM Agenda a WHERE a.name = :name")
    , @NamedQuery(name = "Agenda.findByPhone1", query = "SELECT a FROM Agenda a WHERE a.phone1 = :phone1")
    , @NamedQuery(name = "Agenda.findByPhone2", query = "SELECT a FROM Agenda a WHERE a.phone2 = :phone2")
    , @NamedQuery(name = "Agenda.findByEmail", query = "SELECT a FROM Agenda a WHERE a.email = :email")
    , @NamedQuery(name = "Agenda.findByDescription", query = "SELECT a FROM Agenda a WHERE a.description = :description")
    , @NamedQuery(name = "Agenda.findByWebsite", query = "SELECT a FROM Agenda a WHERE a.website = :website")})
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "agendaID", nullable = false)
    private Integer agendaID;
    @Column(name = "name", length = 200)
    private String name;
    @Column(name = "phone1", length = 15)
    private String phone1;
    @Column(name = "phone2", length = 15)
    private String phone2;
    @Column(name = "email", length = 150)
    private String email;
    @Column(name = "description", length = 500)
    private String description;
    @Column(name = "website", length = 100)
    private String website;
    @OneToMany(mappedBy = "agendaID")
    private Collection<Tour> tourCollection;

    public Agenda() {
    }

    public Agenda(Integer agendaID) {
        this.agendaID = agendaID;
    }

    public Integer getAgendaID() {
        return agendaID;
    }

    public void setAgendaID(Integer agendaID) {
        this.agendaID = agendaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @XmlTransient
    public Collection<Tour> getTourCollection() {
        return tourCollection;
    }

    public void setTourCollection(Collection<Tour> tourCollection) {
        this.tourCollection = tourCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agendaID != null ? agendaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.agendaID == null && other.agendaID != null) || (this.agendaID != null && !this.agendaID.equals(other.agendaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.Agenda[ agendaID=" + agendaID + " ]";
    }
    
}
