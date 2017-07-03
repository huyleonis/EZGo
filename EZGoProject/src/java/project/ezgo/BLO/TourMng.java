/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.BLO;


import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import project.ezgo.Entity.Tour;

/**
 *
 * @author ThuLNN
 */
public class TourMng implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EZGoProjectPU");
    EntityManager em = emf.createEntityManager();

    public void persist(Object object) {
        em = emf.createEntityManager();
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

    public List getAllTours() {
        Query query = em.createNamedQuery("Tour.findAll");
        List listTour = query.getResultList();
        return listTour;
    }

    public boolean addNewTour(Tour tour) {
        try {
            persist(tour);
            return true;
        } catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean removeTour(int tourID){
        Tour tour = em.find(Tour.class, tourID);                
                
        if(tour!=null){
            em.getTransaction().begin();
            em.remove(tour);
            em.getTransaction().commit();
            return false;
        }
        return true;
    }
    
    public boolean deleteTour(Tour t) {
        
        String path = t.getPicture();
        
        try {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            
            //implement delete img here
            
        } catch (Exception e) {
        }
        
        return true;
    }  
    
    public List<Tour> getPopularTour() {
        String sql = "SELECT t FROM Tour t ORDER BY t.popularity DESC";
        Query q = em.createQuery(sql);
        q.setMaxResults(70);
        List<Tour> result = q.getResultList();
        return result;
    }
    
    public List<Tour> getPromotion(){
        String sql = "SELECT t FROM Tour t ORDER BY ((t.oldPrice-t.price)/t.oldPrice) DESC";
        Query q = em.createQuery(sql);
        q.setMaxResults(50);
        List<Tour> result = q.getResultList();
        return result;
    }

    public Tour getTourById(String id) {
        Tour t = em.find(Tour.class, id);
        
        return t;
    }
    
    
}
