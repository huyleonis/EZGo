/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Favorite", catalog = "EZGo", schema = "dbo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "favorite", propOrder = {
    "tourId"
})
@NamedQueries({
    @NamedQuery(name = "Favorite.findAll", query = "SELECT f FROM Favorite f")
    , @NamedQuery(name = "Favorite.findByFavoriteID", query = "SELECT f FROM Favorite f WHERE f.favoriteID = :favoriteID")
    , @NamedQuery(name = "Favorite.findByFavoriteTime", query = "SELECT f FROM Favorite f WHERE f.favoriteTime = :favoriteTime")})
public class Favorite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "favoriteID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Integer favoriteID;

    @Column(name = "favoriteTime")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlTransient
    private Date favoriteTime;

    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    @ManyToOne
    @XmlTransient
    private Account accountID;

    @JoinColumn(name = "tourID", referencedColumnName = "tourID")
    @ManyToOne
    @XmlTransient
    private Tour tourID;

    public Favorite() {
    }

    public Favorite(Integer favoriteID) {
        this.favoriteID = favoriteID;
    }

    @XmlElement(name = "tour-id")
    public String getTourId() {        
        return this.tourID.getTourID();
    }

    public Integer getFavoriteID() {
        return favoriteID;
    }

    public void setFavoriteID(Integer favoriteID) {
        this.favoriteID = favoriteID;
    }

    public Date getFavoriteTime() {
        return favoriteTime;
    }

    public void setFavoriteTime(Date favoriteTime) {
        this.favoriteTime = favoriteTime;
    }

    public Account getAccountID() {
        return accountID;
    }

    public void setAccountID(Account accountID) {
        this.accountID = accountID;
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
        hash += (favoriteID != null ? favoriteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Favorite)) {
            return false;
        }
        Favorite other = (Favorite) object;
        if ((this.favoriteID == null && other.favoriteID != null) || (this.favoriteID != null && !this.favoriteID.equals(other.favoriteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.Favorite[ favoriteID=" + favoriteID + " ]";
    }

}
