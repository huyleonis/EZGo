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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import project.ezgo.Entity.Account;

/**
 *
 * @author hp
 */
public class AccountMng implements Serializable {

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

    public boolean checkLogin(String username, String password) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT a.password FROM Account a WHERE a.username = :username OR a.email = :email";
        Query query = em.createQuery(jpql);

        query.setParameter("email", username);
        query.setParameter("username", username);

        try {
            String pwd = (String) query.getSingleResult();
            return pwd.equals(password);
        } catch (NoResultException e) {
            System.out.println("No result match email/username " + username
                    + " - Error: " + e);
            return false;
        }
    }

    public Account findAccount(String username) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT a FROM Account a WHERE a.username = :username OR a.email = :email";
        Query query = em.createQuery(jpql);

        query.setParameter("email", username);
        query.setParameter("username", username);

        try {
            Account account = (Account) query.getSingleResult();
            return account;
        } catch (NoResultException e) {
            System.out.println("No result match email/username " + username
                    + " - Error: " + e);
            return null;
        }

    }

    public Account getAccountById(String id) {
        EntityManager em = emf.createEntityManager();

        Account result = em.find(Account.class, Integer.parseInt(id));

        return result;
    }

    public boolean createNewAccount(Account account) {
        try {
            persist(account);
            return true;
        } catch (Exception e) {
            System.out.println("Cannot create new account - Error: " + e);
            return false;
        }
    }

    public boolean removeAccount(String accountID) {
        try {
            EntityManager em = emf.createEntityManager();
            Account account = em.find(Account.class, accountID);
            if (account != null) {
                em.getTransaction();
                em.remove(account);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Cannot remove account - Error: " + ex);
            return false;
        }
    }
    
    public List findAccountByUsername(String username){
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT a FROM Account a WHERE a.username LIKE :username";
        Query query = em.createQuery(jpql);

        query.setParameter("username", "%" + username + "%");
        List result = query.getResultList();
        return result;
    }
    
    public List findAccountByEmail(String email){
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT a FROM Account a WHERE a.email = :email";
        Query query = em.createQuery(jpql);

        query.setParameter("username", "%" + email + "%");
        List result = query.getResultList();
        return result;
    }

    public List findAccountByFullname(String fullname){
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT a FROM Account a WHERE a.fullname = :fullname";
        Query query = em.createQuery(jpql);

        query.setParameter("username", "%" + fullname + "%");
        List result = query.getResultList();
        return result;
    }
    
    public boolean updatePassword(int accountID, String newPassword){
        try{
            EntityManager em = emf.createEntityManager();
            Account account = em.find(Account.class, accountID);
            account.setPassword(newPassword);
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
            return true;
        }  catch(Exception ex){
            System.out.println(ex);
            return false;
        }  
    }
}
