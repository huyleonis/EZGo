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
@Table(name = "Discount", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discount.findAll", query = "SELECT d FROM Discount d")
    , @NamedQuery(name = "Discount.findByDiscountID", query = "SELECT d FROM Discount d WHERE d.discountID = :discountID")
    , @NamedQuery(name = "Discount.findByTitle", query = "SELECT d FROM Discount d WHERE d.title = :title")
    , @NamedQuery(name = "Discount.findByAmount", query = "SELECT d FROM Discount d WHERE d.amount = :amount")
    , @NamedQuery(name = "Discount.findByDiscription", query = "SELECT d FROM Discount d WHERE d.discription = :discription")})
public class Discount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "discountID", nullable = false)
    private Integer discountID;
    @Column(name = "title", length = 50)
    private String title;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount", precision = 53)
    private Double amount;
    @Column(name = "discription", length = 100)
    private String discription;
    @OneToMany(mappedBy = "discountID")
    private Collection<Tour> tourCollection;

    public Discount() {
    }

    public Discount(Integer discountID) {
        this.discountID = discountID;
    }

    public Integer getDiscountID() {
        return discountID;
    }

    public void setDiscountID(Integer discountID) {
        this.discountID = discountID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @XmlTransient
    public Collection<Tour> getTourCollection() {
        return tourCollection;
    }

    public void setTourCollection(Collection<Tour> tourCollection) {
        this.tourCollection = tourCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (discountID != null ? discountID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discount)) {
            return false;
        }
        Discount other = (Discount) object;
        if ((this.discountID == null && other.discountID != null) || (this.discountID != null && !this.discountID.equals(other.discountID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.Discount[ discountID=" + discountID + " ]";
    }
    
}
