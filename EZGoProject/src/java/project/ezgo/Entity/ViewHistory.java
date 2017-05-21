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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "ViewHistory", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ViewHistory.findAll", query = "SELECT v FROM ViewHistory v")
    , @NamedQuery(name = "ViewHistory.findByHistoryID", query = "SELECT v FROM ViewHistory v WHERE v.historyID = :historyID")
    , @NamedQuery(name = "ViewHistory.findByRecentViewed", query = "SELECT v FROM ViewHistory v WHERE v.recentViewed = :recentViewed")
    , @NamedQuery(name = "ViewHistory.findByCount", query = "SELECT v FROM ViewHistory v WHERE v.count = :count")})
public class ViewHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "historyID", nullable = false)
    private Integer historyID;
    @Column(name = "recentViewed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recentViewed;
    @Column(name = "count")
    private Integer count;
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    @ManyToOne
    private Account accountID;
    @JoinColumn(name = "tourID", referencedColumnName = "tourID")
    @ManyToOne
    private Tour tourID;

    public ViewHistory() {
    }

    public ViewHistory(Integer historyID) {
        this.historyID = historyID;
    }

    public Integer getHistoryID() {
        return historyID;
    }

    public void setHistoryID(Integer historyID) {
        this.historyID = historyID;
    }

    public Date getRecentViewed() {
        return recentViewed;
    }

    public void setRecentViewed(Date recentViewed) {
        this.recentViewed = recentViewed;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
        hash += (historyID != null ? historyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ViewHistory)) {
            return false;
        }
        ViewHistory other = (ViewHistory) object;
        if ((this.historyID == null && other.historyID != null) || (this.historyID != null && !this.historyID.equals(other.historyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.ViewHistory[ historyID=" + historyID + " ]";
    }
    
}
