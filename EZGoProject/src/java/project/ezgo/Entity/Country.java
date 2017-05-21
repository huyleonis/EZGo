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
@Table(name = "Country", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
    , @NamedQuery(name = "Country.findByCountryID", query = "SELECT c FROM Country c WHERE c.countryID = :countryID")
    , @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name")
    , @NamedQuery(name = "Country.findByArea", query = "SELECT c FROM Country c WHERE c.area = :area")
    , @NamedQuery(name = "Country.findByDescription", query = "SELECT c FROM Country c WHERE c.description = :description")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "countryID", nullable = false, length = 50)
    private String countryID;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "area", length = 50)
    private String area;
    @Column(name = "description", length = 200)
    private String description;
    @OneToMany(mappedBy = "country")
    private Collection<Destination> destinationCollection;

    public Country() {
    }

    public Country(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Destination> getDestinationCollection() {
        return destinationCollection;
    }

    public void setDestinationCollection(Collection<Destination> destinationCollection) {
        this.destinationCollection = destinationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countryID != null ? countryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.countryID == null && other.countryID != null) || (this.countryID != null && !this.countryID.equals(other.countryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.Country[ countryID=" + countryID + " ]";
    }
    
}
