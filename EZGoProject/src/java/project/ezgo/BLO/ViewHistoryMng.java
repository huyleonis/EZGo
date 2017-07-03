/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.BLO;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import project.ezgo.Entity.Account;
import project.ezgo.Entity.Tour;
import project.ezgo.Entity.ViewHistory;

/**
 *
 * @author hp
 */
public class ViewHistoryMng {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EZGoProjectPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public boolean saveHistory(String idTour, int idAccount) {
        EntityManager em = emf.createEntityManager();
        
        ViewHistory history = new ViewHistory();
        Account acc = em.find(Account.class, idAccount);
        Tour tour = em.find(Tour.class, idTour);
        
        history.setRecentViewed(new Date());
        history.setAccountID(acc);
        history.setTourID(tour);
        history.setCount(1);
        
        em.getTransaction().begin();
        em.persist(history);
        em.getTransaction().commit();
        return true;
    }
    
    public boolean updateHistory(String idTour, int idAccount) {
        EntityManager em = emf.createEntityManager();
        
        Account acc = em.find(Account.class, idAccount);
        Tour tour = em.find(Tour.class, idTour);
        
        String sql = "SELECT v FROM ViewHistory v WHERE v.accountID = :acc AND v.tourID = :tour";
        Query q = em.createQuery(sql);
        q.setParameter("acc", acc);
        q.setParameter("tour", tour);
        
        try {
            ViewHistory history = (ViewHistory) q.getSingleResult();
            
            int count = history.getCount();
            
            history.setCount(count + 1);
            
            em.getTransaction().begin();
            em.merge(history);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            e.printStackTrace();
            saveHistory(idTour, idAccount);
        }                        
        return true;
    }
}
