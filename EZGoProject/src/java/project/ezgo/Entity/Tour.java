/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "Tour", catalog = "EZGo", schema = "dbo")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tour", propOrder = {    
    "tourID",
    "name",
    "picture",
    "duration",
    "price",
    "oldPrice",
    "currency",
    "departure",
    "agenda",
    "agendaId",
    "link",
    "popularity",
    "regionType",
})
@NamedQueries({
    @NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t")
    , @NamedQuery(name = "Tour.findByTourID", query = "SELECT t FROM Tour t WHERE t.tourID = :tourID")
    , @NamedQuery(name = "Tour.findByName", query = "SELECT t FROM Tour t WHERE t.name = :name")
    , @NamedQuery(name = "Tour.findByDuration", query = "SELECT t FROM Tour t WHERE t.duration = :duration")
    , @NamedQuery(name = "Tour.findByPrice", query = "SELECT t FROM Tour t WHERE t.price = :price")
    , @NamedQuery(name = "Tour.findByRating", query = "SELECT t FROM Tour t WHERE t.rating = :rating")
    , @NamedQuery(name = "Tour.findByLink", query = "SELECT t FROM Tour t WHERE t.link = :link")})
public class Tour implements Serializable {

    @JoinColumn(name = "region", referencedColumnName = "regionID")
    @ManyToOne    
    @XmlTransient
    private Region region;

    @Column(name = "popularity")
    @XmlElement(name = "popularity")
    private Integer popularity;

    @Column(name = "oldPrice")
    @XmlElement(name = "old-price")
    private BigInteger oldPrice;        

    @Column(name = "picture", length = 200)
    @XmlElement(name = "img-link", required = true)
    private String picture;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tourID", nullable = false, length = 50)
    @XmlElement(name = "id")
    private String tourID;

    @Column(name = "name", length = 200)
    @XmlElement(required = true)
    private String name;
    
    @Column(name = "departure", length = 100)
    @XmlElement(name = "departure", required = true, defaultValue = "Tp. H\u1ed3 Ch\u00ed Minh")
    private String departure;

    @Column(name = "duration", length = 20)
    @XmlElement(required = true)
    private String duration;

    @Column(name = "price")
    @XmlElement(name = "current-price", required = true)
    private BigInteger price;

    @Column(name = "rating")
    @XmlTransient
    private Integer rating;

    @Column(name = "schedule", length = 4000)
    @XmlTransient
    private String schedule;
    
    @Column(name = "policy", length = 4000)
    @XmlTransient
    private String policy;

    @Column(name = "departureDay")
    @Temporal(TemporalType.DATE)
    @XmlTransient
    private Date departureDay;
    
    @Column(name = "link", length = 500)
    @XmlElement(required = true)
    private String link;

    @OneToMany(mappedBy = "tourID")
    @XmlTransient
    private Collection<Favorite> favoriteCollection;

    @OneToMany(mappedBy = "tourID")
    @XmlTransient
    private Collection<Comment> commentCollection;

    @OneToMany(mappedBy = "tourID")
    @XmlTransient
    private Collection<ViewHistory> viewHistoryCollection;

    @JoinColumn(name = "agendaID", referencedColumnName = "agendaID")
    @ManyToOne
    @XmlTransient
    private Agenda agendaID;

    public Tour() {
    }
    
    @XmlElement(name = "region", required = true, type = Integer.class)
    public int getRegionType() {
        return region.getRegionID();
    }

    public Tour(String tourID) {
        this.tourID = tourID;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
        
    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }        

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public Date getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(Date departureDay) {
        this.departureDay = departureDay;
    }        
    
    @XmlElement(name ="agenda", required = true)
    public String getAgenda() {
        return agendaID.getName();
    }
    
    @XmlElement(name ="agendaId", required = true)
    public Integer getAgendaId() {
        return agendaID.getAgendaID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @XmlTransient
    public Collection<Favorite> getFavoriteCollection() {
        return favoriteCollection;
    }

    public void setFavoriteCollection(Collection<Favorite> favoriteCollection) {
        this.favoriteCollection = favoriteCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }
    
    @XmlTransient
    public Collection<ViewHistory> getViewHistoryCollection() {
        return viewHistoryCollection;
    }

    public void setViewHistoryCollection(Collection<ViewHistory> viewHistoryCollection) {
        this.viewHistoryCollection = viewHistoryCollection;
    }

    public Agenda getAgendaID() {
        return agendaID;
    }

    public void setAgendaID(Agenda agendaID) {
        this.agendaID = agendaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tourID != null ? tourID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tour)) {
            return false;
        }
        Tour other = (Tour) object;
        if ((this.tourID == null && other.tourID != null) || (this.tourID != null && !this.tourID.equals(other.tourID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.Tour[ tourID=" + tourID + " ]";
    }        

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BigInteger getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigInteger oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
    
    @XmlElement(name = "currency")
    public String getCurrency() {
        if (price.longValue() > 1000000) {
            return "VND";
        } else {
            return "USD";
        }
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
