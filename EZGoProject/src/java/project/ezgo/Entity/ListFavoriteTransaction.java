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
public class ListFavoriteTransaction {
    
    @XmlElement(name = "account-id", required = true)
    Integer accountId;

    @XmlElement(name = "favorite")
    List<FavoriteTransaction> list;

    public ListFavoriteTransaction() {
    }

    public ListFavoriteTransaction(Integer accountId, List<FavoriteTransaction> list) {
        this.accountId = accountId;
        this.list = list;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public List<FavoriteTransaction> getList() {
        return list;
    }

    public void setList(List<FavoriteTransaction> list) {
        this.list = list;
    }

}
