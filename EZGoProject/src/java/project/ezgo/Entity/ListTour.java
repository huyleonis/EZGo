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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "list"
})
@XmlRootElement(name = "tours")
public class ListTour {
    @XmlElement(name = "tour", required = true)
    private List<Tour> list;

    public ListTour(List<Tour> list) {
        this.list = list;
    }

    public ListTour() {
    }

    public List<Tour> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public void setList(List<Tour> list) {
        this.list = list;
    }
    
    
}
