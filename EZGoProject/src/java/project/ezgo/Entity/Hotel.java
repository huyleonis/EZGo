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
@Table(name = "Hotel", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hotel.findAll", query = "SELECT h FROM Hotel h")
    , @NamedQuery(name = "Hotel.findByHotelID", query = "SELECT h FROM Hotel h WHERE h.hotelID = :hotelID")
    , @NamedQuery(name = "Hotel.findByName", query = "SELECT h FROM Hotel h WHERE h.name = :name")
    , @NamedQuery(name = "Hotel.findByStar", query = "SELECT h FROM Hotel h WHERE h.star = :star")
    , @NamedQuery(name = "Hotel.findByAddress", query = "SELECT h FROM Hotel h WHERE h.address = :address")
    , @NamedQuery(name = "Hotel.findByDescription", query = "SELECT h FROM Hotel h WHERE h.description = :description")})
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "hotelID", nullable = false)
    private Integer hotelID;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "star")
    private Integer star;
    @Column(name = "address", length = 100)
    private String address;
    @Column(name = "description", length = 100)
    private String description;
    @OneToMany(mappedBy = "hotelID")
    private Collection<HotelMap> hotelMapCollection;

    public Hotel() {
    }

    public Hotel(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<HotelMap> getHotelMapCollection() {
        return hotelMapCollection;
    }

    public void setHotelMapCollection(Collection<HotelMap> hotelMapCollection) {
        this.hotelMapCollection = hotelMapCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hotelID != null ? hotelID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.hotelID == null && other.hotelID != null) || (this.hotelID != null && !this.hotelID.equals(other.hotelID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.Hotel[ hotelID=" + hotelID + " ]";
    }
    
}
