/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.BLO;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import project.ezgo.Entity.Account;
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

    public List<Tour> getPopularTour(int maxSize) {
        String sql = "SELECT t FROM Tour t WHERE t.region.regionID = 1 ORDER BY t.popularity DESC ";
        Query q = em.createQuery(sql);
        q.setMaxResults(maxSize/2);
        List<Tour> list1 = q.getResultList();
        
        String sql2 = "SELECT t FROM Tour t WHERE t.region.regionID = 2 ORDER BY t.popularity DESC ";
        Query q2 = em.createQuery(sql2);
        q2.setMaxResults(maxSize/2);
        
        List<Tour> list2 = q2.getResultList();
        
        List<Tour> result = new ArrayList<>();        
        int i = 0, j = 0;
        int n = list1.size(), m = list2.size();
        
        while (i < n || j < m) {
            if (i >= n) {
                result.add(list2.get(j++));
            } else if (j >= m) {
                result.add(list1.get(i++));
            } else {
                if (list1.get(i).getPopularity() < list2.get(j).getPopularity()) {
                    result.add(list2.get(j++));
                } else {
                    result.add(list1.get(i++));
                }
            }
        }
                
        return result;
    }
    
    public List<Tour> getPopularTour() {
        return getPopularTour(100);
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

    public List<Tour> getFavoriteTours(Integer accId) {
        Account acc = em.find(Account.class, accId);
        
        String sql = "SELECT t FROM Tour t WHERE t.tourID IN " +
                        "(SELECT f.tourID.tourID FROM Favorite f WHERE f.accountID = :acc)";
        
        Query q = em.createQuery(sql);
        q.setParameter("acc", acc);
        q.setMaxResults(100);
        
        List<Tour> result = (List<Tour>) q.getResultList();
        
        String sql2 = "SELECT t FROM Tour t WHERE t.tourID NOT IN " +
                        "(SELECT f.tourID.tourID FROM Favorite f WHERE f.accountID = :acc) AND t.tourID IN " +
                        "(SELECT v.tourID.tourID FROM ViewHistory v WHERE v.accountID = :acc)";
        
        Query q2 = em.createQuery(sql2);
        q2.setParameter("acc", acc);
        q2.setMaxResults(100);
        
        result.addAll(q2.getResultList());
        int n = 100 - result.size();
        
        if (n > 30) {
            String sql3 = "SELECT t FROM Tour t WHERE t.tourID NOT IN " +
                        "(SELECT f.tourID.tourID FROM Favorite f WHERE f.accountID = :acc) AND t.tourID NOT IN " +
                        "(SELECT v.tourID.tourID FROM ViewHistory v WHERE v.accountID = :acc) ORDER BY t.popularity DESC";
            Query q3 = em.createQuery(sql3);
            q3.setParameter("acc", acc);
            q3.setMaxResults(n);
            
            result.addAll(q3.getResultList());
        }
        
        return result;
    }
}
