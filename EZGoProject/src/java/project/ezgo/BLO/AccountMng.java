/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.BLO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
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

/**
 *
 * @author hp
 */
public class AccountMng implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EZGoProjectPU");
    EntityManager em = emf.createEntityManager();

    public void persist(Object object) {
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

    public List findAccountByUsername(String username) {
        String jpql = "SELECT a FROM Account a WHERE a.username LIKE :username";
        Query query = em.createQuery(jpql);

        query.setParameter("username", "%" + username + "%");
        List result = query.getResultList();
        return result;
    }

    public List findAccountByEmail(String email) {
        String jpql = "SELECT a FROM Account a WHERE a.email = :email";
        Query query = em.createQuery(jpql);

        query.setParameter("email", "%" + email + "%");
        List result = query.getResultList();
        return result;
    }

    public List findAccountByFullname(String fullname) {
        String jpql = "SELECT a FROM Account a WHERE a.fullname = :fullname";
        Query query = em.createQuery(jpql);

        query.setParameter("fullname", "%" + fullname + "%");
        List result = query.getResultList();
        return result;
    }

    public boolean updatePassword(String accountID, String newPassword) {
        try {
            Account account = em.find(Account.class, Integer.parseInt(accountID));
            account.setPassword(newPassword);
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean updateinfo(String accountID, String birthday, String phone, String email) {
        try {
            Account account = em.find(Account.class, Integer.parseInt(accountID));
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
            account.setBirthday(formatter.parse(birthday));
            account.setPhone(phone);
            account.setEmail(email);
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public List getAllAccounts(){
        Query query = em.createNamedQuery("Account.findAll");
        List listAccounts = query.getResultList();
        return listAccounts;
    }
}
