/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Entity;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "Tour", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t")
    , @NamedQuery(name = "Tour.findByTourID", query = "SELECT t FROM Tour t WHERE t.tourID = :tourID")
    , @NamedQuery(name = "Tour.findByName", query = "SELECT t FROM Tour t WHERE t.name = :name")
    , @NamedQuery(name = "Tour.findByDuration", query = "SELECT t FROM Tour t WHERE t.duration = :duration")
    , @NamedQuery(name = "Tour.findByPrice", query = "SELECT t FROM Tour t WHERE t.price = :price")
    , @NamedQuery(name = "Tour.findByRating", query = "SELECT t FROM Tour t WHERE t.rating = :rating")
    , @NamedQuery(name = "Tour.findByDescription", query = "SELECT t FROM Tour t WHERE t.description = :description")
    , @NamedQuery(name = "Tour.findByLink", query = "SELECT t FROM Tour t WHERE t.link = :link")})
public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tourID", nullable = false)
    private Integer tourID;
    @Column(name = "name", length = 20)
    private String name;
    @Column(name = "duration", length = 20)
    private String duration;
    @Column(name = "price")
    private BigInteger price;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "description", length = 100)
    private String description;
    @Column(name = "link", length = 500)
    private String link;
    @OneToMany(mappedBy = "tourID")
    private Collection<Favorite> favoriteCollection;
    @OneToMany(mappedBy = "tourID")
    private Collection<Comment> commentCollection;
    @OneToMany(mappedBy = "tourID")
    private Collection<HotelMap> hotelMapCollection;
    @OneToMany(mappedBy = "tourID")
    private Collection<TransportMap> transportMapCollection;
    @OneToMany(mappedBy = "tourID")
    private Collection<ViewHistory> viewHistoryCollection;
    @OneToMany(mappedBy = "tourID")
    private Collection<DestinationMap> destinationMapCollection;
    @JoinColumn(name = "agendaID", referencedColumnName = "agendaID")
    @ManyToOne
    private Agenda agendaID;
    @JoinColumn(name = "discountID", referencedColumnName = "discountID")
    @ManyToOne
    private Discount discountID;

    public Tour() {
    }

    public Tour(Integer tourID) {
        this.tourID = tourID;
    }

    public Integer getTourID() {
        return tourID;
    }

    public void setTourID(Integer tourID) {
        this.tourID = tourID;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public Collection<HotelMap> getHotelMapCollection() {
        return hotelMapCollection;
    }

    public void setHotelMapCollection(Collection<HotelMap> hotelMapCollection) {
        this.hotelMapCollection = hotelMapCollection;
    }

    @XmlTransient
    public Collection<TransportMap> getTransportMapCollection() {
        return transportMapCollection;
    }

    public void setTransportMapCollection(Collection<TransportMap> transportMapCollection) {
        this.transportMapCollection = transportMapCollection;
    }

    @XmlTransient
    public Collection<ViewHistory> getViewHistoryCollection() {
        return viewHistoryCollection;
    }

    public void setViewHistoryCollection(Collection<ViewHistory> viewHistoryCollection) {
        this.viewHistoryCollection = viewHistoryCollection;
    }

    @XmlTransient
    public Collection<DestinationMap> getDestinationMapCollection() {
        return destinationMapCollection;
    }

    public void setDestinationMapCollection(Collection<DestinationMap> destinationMapCollection) {
        this.destinationMapCollection = destinationMapCollection;
    }

    public Agenda getAgendaID() {
        return agendaID;
    }

    public void setAgendaID(Agenda agendaID) {
        this.agendaID = agendaID;
    }

    public Discount getDiscountID() {
        return discountID;
    }

    public void setDiscountID(Discount discountID) {
        this.discountID = discountID;
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
    
}
