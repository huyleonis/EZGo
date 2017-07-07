/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.BLO;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import project.ezgo.Entity.Favorite;
import project.ezgo.Entity.Tour;
import project.ezgo.Entity.ViewHistory;

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
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean removeTour(int tourID) {
        Tour tour = em.find(Tour.class, tourID);

        if (tour != null) {
            em.getTransaction().begin();
            em.remove(tour);
            em.getTransaction().commit();
            return false;
        }
        return true;
    }

    public boolean deleteTour(Tour t, String realPath) {

        String path = t.getPicture();

        //delete ViewHistory of this Tour
        String sql = "SELECT v FROM ViewHistory v WHERE v.tourID = :tour";
        Query q = em.createQuery(sql);
        q.setParameter("tour", t);

        String sql2 = "SELECT v FROM Favorite v WHERE v.tourID = :tour";
        Query q2 = em.createQuery(sql);
        q2.setParameter("tour", t);

        em.getTransaction().begin();
        try {
            
            //delete all ViewHistory of this tour
            List<ViewHistory> lstVH = (List<ViewHistory>) q.getResultList();
            for (ViewHistory viewHistory : lstVH) {
                em.remove(viewHistory);
            }
            
            //delete all Favorite of this tour
            List<Favorite> lstFav = (List<Favorite>) q2.getResultList();
            for (Favorite favorite : lstFav) {
                em.remove(favorite);
            }
            
            //delete this tour
            em.remove(t);

            em.getTransaction().commit();
                        
        } catch (Exception e) {
            System.out.println("SQL Exception when get List View History");
            if (em.getTransaction() != null) {
                em.getTransaction().rollback();
            }            
            return false;
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

    public List<Tour> getPromotion() {
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

    public List<Tour> getToursByAgenda(int agendaId) {
        String sql = "SELECT t FROM Tour t WHERE t.agendaID = :id";
        Query q = em.createQuery(sql);
        q.setParameter("id", agendaId);

        List<Tour> result = q.getResultList();
        return result;
    }

}
