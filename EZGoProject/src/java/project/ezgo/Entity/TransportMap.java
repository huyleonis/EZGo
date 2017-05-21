/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "TransportMap", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransportMap.findAll", query = "SELECT t FROM TransportMap t")
    , @NamedQuery(name = "TransportMap.findByTransportMapID", query = "SELECT t FROM TransportMap t WHERE t.transportMapID = :transportMapID")
    , @NamedQuery(name = "TransportMap.findByDescription", query = "SELECT t FROM TransportMap t WHERE t.description = :description")})
public class TransportMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "transportMapID", nullable = false)
    private Integer transportMapID;
    @Column(name = "description", length = 100)
    private String description;
    @JoinColumn(name = "tourID", referencedColumnName = "tourID")
    @ManyToOne
    private Tour tourID;
    @JoinColumn(name = "transportID", referencedColumnName = "transportID")
    @ManyToOne
    private Transport transportID;

    public TransportMap() {
    }

    public TransportMap(Integer transportMapID) {
        this.transportMapID = transportMapID;
    }

    public Integer getTransportMapID() {
        return transportMapID;
    }

    public void setTransportMapID(Integer transportMapID) {
        this.transportMapID = transportMapID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tour getTourID() {
        return tourID;
    }

    public void setTourID(Tour tourID) {
        this.tourID = tourID;
    }

    public Transport getTransportID() {
        return transportID;
    }

    public void setTransportID(Transport transportID) {
        this.transportID = transportID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transportMapID != null ? transportMapID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransportMap)) {
            return false;
        }
        TransportMap other = (TransportMap) object;
        if ((this.transportMapID == null && other.transportMapID != null) || (this.transportMapID != null && !this.transportMapID.equals(other.transportMapID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.TransportMap[ transportMapID=" + transportMapID + " ]";
    }
    
}
