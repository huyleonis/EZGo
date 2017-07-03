/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Dells
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "list"
})
@XmlRootElement(name = "accounts")
public class ListAccount {
    @XmlElement(name = "account", required = true)
    private List<Account> list;

    public ListAccount(List<Account> list) {
        this.list = list;
    }

    public ListAccount() {
    }

    public List<Account> getList() {
        if(list == null){
            list = new ArrayList<>();
        }
        return list;
    }

    public void setList(List<Account> list) {
        this.list = list;
    }
    
    
}
