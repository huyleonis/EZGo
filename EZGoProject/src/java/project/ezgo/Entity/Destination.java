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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Destination", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d")
    , @NamedQuery(name = "Destination.findByDestinationID", query = "SELECT d FROM Destination d WHERE d.destinationID = :destinationID")
    , @NamedQuery(name = "Destination.findByName", query = "SELECT d FROM Destination d WHERE d.name = :name")
    , @NamedQuery(name = "Destination.findByCity", query = "SELECT d FROM Destination d WHERE d.city = :city")
    , @NamedQuery(name = "Destination.findByDescription", query = "SELECT d FROM Destination d WHERE d.description = :description")})
public class Destination implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "destinationID", nullable = false)
    private Integer destinationID;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "description", length = 500)
    private String description;
    @JoinColumn(name = "country", referencedColumnName = "countryID")
    @ManyToOne
    private Country country;
    @OneToMany(mappedBy = "destinationID")
    private Collection<DestinationMap> destinationMapCollection;

    public Destination() {
    }

    public Destination(Integer destinationID) {
        this.destinationID = destinationID;
    }

    public Integer getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(Integer destinationID) {
        this.destinationID = destinationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @XmlTransient
    public Collection<DestinationMap> getDestinationMapCollection() {
        return destinationMapCollection;
    }

    public void setDestinationMapCollection(Collection<DestinationMap> destinationMapCollection) {
        this.destinationMapCollection = destinationMapCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (destinationID != null ? destinationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destination)) {
            return false;
        }
        Destination other = (Destination) object;
        if ((this.destinationID == null && other.destinationID != null) || (this.destinationID != null && !this.destinationID.equals(other.destinationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.Destination[ destinationID=" + destinationID + " ]";
    }
    
}
