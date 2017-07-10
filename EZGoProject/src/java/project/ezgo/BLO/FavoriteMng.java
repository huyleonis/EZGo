/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.BLO;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import project.ezgo.Entity.Account;
import project.ezgo.Entity.Favorite;
import project.ezgo.Entity.Tour;

/**
 *
 * @author hp
 */
public class FavoriteMng {

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

    public void updateFavorite(String tourId, Integer accountId) {
        EntityManager em = emf.createEntityManager();

        Account acc = em.find(Account.class, accountId);
        Tour tour = em.find(Tour.class, tourId);

        if (tour == null) {
            System.out.println("Tour is null with ID: " + tourId);
        }

        String sql = "SELECT f FROM Favorite f WHERE f.accountID = :acc AND f.tourID = :tour";
        Query q = em.createQuery(sql);

        q.setParameter("acc", acc);
        q.setParameter("tour", tour);

        Date now = new Date();
        try {
            Favorite f = (Favorite) q.getSingleResult();
            f.setFavoriteTime(now);

            em.getTransaction().begin();
            em.merge(f);
            em.getTransaction().commit();
        } catch (NoResultException e) {
            Favorite f = new Favorite();

            f.setAccountID(acc);
            f.setTourID(tour);
            f.setFavoriteTime(now);

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();
        }        
    }

    public void removeFavorite(String tourId, Integer accountId) {
        EntityManager em = emf.createEntityManager();

        Account acc = em.find(Account.class, accountId);
        Tour tour = em.find(Tour.class, tourId);

        String sql = "SELECT f FROM Favorite f WHERE f.accountID = :acc AND f.tourID = :tour";
        Query q = em.createQuery(sql);

        q.setParameter("acc", acc);
        q.setParameter("tour", tour);

        try {
            Favorite f = (Favorite) q.getSingleResult();

            em.getTransaction().begin();
            em.remove(f);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("SQL Exception when remove fav: " + e.getMessage());
        }
    }

    public List<Favorite> getFavoriteByAccount(Integer accountId) {
        EntityManager em = emf.createEntityManager();

        Account acc = em.find(Account.class, accountId);

        String sql = "SELECT f FROM Favorite f WHERE f.accountID = :acc";
        Query q = em.createQuery(sql);
        q.setParameter("acc", acc);

        List<Favorite> result = q.getResultList();

        return result;
    }
}
