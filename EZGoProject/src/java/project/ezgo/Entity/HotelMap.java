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
@Table(name = "HotelMap", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HotelMap.findAll", query = "SELECT h FROM HotelMap h")
    , @NamedQuery(name = "HotelMap.findByHotelMapID", query = "SELECT h FROM HotelMap h WHERE h.hotelMapID = :hotelMapID")
    , @NamedQuery(name = "HotelMap.findByDescription", query = "SELECT h FROM HotelMap h WHERE h.description = :description")})
public class HotelMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hotelMapID", nullable = false)
    private Integer hotelMapID;
    @Column(name = "description", length = 100)
    private String description;
    @JoinColumn(name = "hotelID", referencedColumnName = "hotelID")
    @ManyToOne
    private Hotel hotelID;
    @JoinColumn(name = "tourID", referencedColumnName = "tourID")
    @ManyToOne
    private Tour tourID;

    public HotelMap() {
    }

    public HotelMap(Integer hotelMapID) {
        this.hotelMapID = hotelMapID;
    }

    public Integer getHotelMapID() {
        return hotelMapID;
    }

    public void setHotelMapID(Integer hotelMapID) {
        this.hotelMapID = hotelMapID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Hotel getHotelID() {
        return hotelID;
    }

    public void setHotelID(Hotel hotelID) {
        this.hotelID = hotelID;
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
        hash += (hotelMapID != null ? hotelMapID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HotelMap)) {
            return false;
        }
        HotelMap other = (HotelMap) object;
        if ((this.hotelMapID == null && other.hotelMapID != null) || (this.hotelMapID != null && !this.hotelMapID.equals(other.hotelMapID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.HotelMap[ hotelMapID=" + hotelMapID + " ]";
    }
    
}
