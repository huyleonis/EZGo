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
@Table(name = "Transport", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transport.findAll", query = "SELECT t FROM Transport t")
    , @NamedQuery(name = "Transport.findByTransportID", query = "SELECT t FROM Transport t WHERE t.transportID = :transportID")
    , @NamedQuery(name = "Transport.findByName", query = "SELECT t FROM Transport t WHERE t.name = :name")
    , @NamedQuery(name = "Transport.findByType", query = "SELECT t FROM Transport t WHERE t.type = :type")
    , @NamedQuery(name = "Transport.findByDescription", query = "SELECT t FROM Transport t WHERE t.description = :description")})
public class Transport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "transportID", nullable = false)
    private Integer transportID;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "type", length = 50)
    private String type;
    @Column(name = "description", length = 50)
    private String description;
    @OneToMany(mappedBy = "transportID")
    private Collection<TransportMap> transportMapCollection;

    public Transport() {
    }

    public Transport(Integer transportID) {
        this.transportID = transportID;
    }

    public Integer getTransportID() {
        return transportID;
    }

    public void setTransportID(Integer transportID) {
        this.transportID = transportID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<TransportMap> getTransportMapCollection() {
        return transportMapCollection;
    }

    public void setTransportMapCollection(Collection<TransportMap> transportMapCollection) {
        this.transportMapCollection = transportMapCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transportID != null ? transportID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transport)) {
            return false;
        }
        Transport other = (Transport) object;
        if ((this.transportID == null && other.transportID != null) || (this.transportID != null && !this.transportID.equals(other.transportID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.Transport[ transportID=" + transportID + " ]";
    }
    
}
