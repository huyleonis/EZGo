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
@Table(name = "Comment", catalog = "EZGo", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
    , @NamedQuery(name = "Comment.findByCommentID", query = "SELECT c FROM Comment c WHERE c.commentID = :commentID")
    , @NamedQuery(name = "Comment.findByRating", query = "SELECT c FROM Comment c WHERE c.rating = :rating")
    , @NamedQuery(name = "Comment.findByContent", query = "SELECT c FROM Comment c WHERE c.content = :content")})
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "commentID", nullable = false)
    private Integer commentID;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "content", length = 500)
    private String content;
    @JoinColumn(name = "accountID", referencedColumnName = "accountID")
    @ManyToOne
    private Account accountID;
    @JoinColumn(name = "tourID", referencedColumnName = "tourID")
    @ManyToOne
    private Tour tourID;

    public Comment() {
    }

    public Comment(Integer commentID) {
        this.commentID = commentID;
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        hash += (commentID != null ? commentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.commentID == null && other.commentID != null) || (this.commentID != null && !this.commentID.equals(other.commentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.ezgo.Entity.Comment[ commentID=" + commentID + " ]";
    }
    
}
