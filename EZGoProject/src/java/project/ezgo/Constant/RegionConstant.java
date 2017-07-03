/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Constant;

/**
 *
 * @author hp
 */
public enum RegionConstant {
    DOMESTIC(1),
    ABROAD(2);
    
    private int region;
    
    RegionConstant(int region) {
        this.region = region;
    }
    
    public int getValue() {
        return region;
    }
}
