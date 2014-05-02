/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intactille.persistance;

/**
 *
 * @author Mojdeh
 */
public class PersistanceFactory {
    
    public enum PersistanceType{
        TDB,
        SDB,
    }
    public static IPersistance getCurrentPersistance(PersistanceType t){
        switch(t){
            case TDB:
                return null;
            case SDB:
                return new SDBUtils();
        }
        return null;
    }
    
    
    
}
