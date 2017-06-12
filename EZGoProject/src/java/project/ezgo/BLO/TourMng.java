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
    EntityManager em;

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
        return false;
    }
    
    public void LoadDataFromDBtoXML(String xmlFileName, String realPath){
        List<Tour> listTours = getAllTours();
        if(listTours!=null){
            Tour tour = null;
            for(int i=0; i<listTours.size(); i++){
                tour = new Tour();
                tour.setTourID(listTours.get(i).getTourID());
                tour.setName(listTours.get(i).getName());
                tour.setDuration(listTours.get(i).getDuration());
                tour.setPrice(listTours.get(i).getPrice());
                tour.setRating(listTours.get(i).getRating());
                tour.setDiscountID(listTours.get(i).getDiscountID());
                tour.setDescription(listTours.get(i).getDescription());
                tour.setAgendaID(listTours.get(i).getAgendaID());
                tour.setLink(listTours.get(i).getLink());
                // generate object
            }
        }
    }
    
    
    
}
