/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "accountId",
    "list"
})
@XmlRootElement(name = "favorites")
public class ListFavorite {

    @XmlElement
    Integer accountId;

    @XmlElement(name = "favorite")
    List<Favorite> list;

    public ListFavorite() {
    }

    public ListFavorite(Integer accountId, List<Favorite> list) {
        this.accountId = accountId;
        this.list = list;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccount(Integer accountId) {
        this.accountId = accountId;
    }

    public List<Favorite> getList() {
        return list;
    }

    public void setList(List<Favorite> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        String s = "";
        for (Favorite favorite : list) {
            if (favorite.getTourID() != null) {
                s += favorite.getTourID().getTourID() + ";";
            }
            
        }
        return s;
    }
    
    

}
