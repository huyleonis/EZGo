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
@Table(name = "DestinationMap", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DestinationMap.findAll", query = "SELECT d FROM DestinationMap d")
    , @NamedQuery(name = "DestinationMap.findByDestinationMapID", query = "SELECT d FROM DestinationMap d WHERE d.destinationMapID = :destinationMapID")
    , @NamedQuery(name = "DestinationMap.findByIsDeparture", query = "SELECT d FROM DestinationMap d WHERE d.isDeparture = :isDeparture")})
public class DestinationMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "destinationMapID", nullable = false)
    private Integer destinationMapID;
    @Column(name = "isDeparture")
    private Boolean isDeparture;
    @JoinColumn(name = "destinationID", referencedColumnName = "destinationID")
    @ManyToOne
    private Destination destinationID;
    @JoinColumn(name = "tourID", referencedColumnName = "tourID")
    @ManyToOne
    private Tour tourID;

    public DestinationMap() {
    }

    public DestinationMap(Integer destinationMapID) {
        this.destinationMapID = destinationMapID;
    }

    public Integer getDestinationMapID() {
        return destinationMapID;
    }

    public void setDestinationMapID(Integer destinationMapID) {
        this.destinationMapID = destinationMapID;
    }

    public Boolean getIsDeparture() {
        return isDeparture;
    }

    public void setIsDeparture(Boolean isDeparture) {
        this.isDeparture = isDeparture;
    }

    public Destination getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(Destination destinationID) {
        this.destinationID = destinationID;
    }

    public Tour getTourID() {
        return tourID;
    }

    public void setTourID(Tour tourID) {
        this.tourID = tourID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (destinationMapID != null ? destinationMapID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DestinationMap)) {
            return false;
        }
        DestinationMap other = (DestinationMap) object;
        if ((this.destinationMapID == null && other.destinationMapID != null) || (this.destinationMapID != null && !this.destinationMapID.equals(other.destinationMapID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.DestinationMap[ destinationMapID=" + destinationMapID + " ]";
    }
    
}
