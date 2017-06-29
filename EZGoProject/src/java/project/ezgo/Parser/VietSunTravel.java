/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import project.ezgo.Entity.Agenda;
import project.ezgo.Entity.Tour;

/**
 *
 * @author Dells
 */
public class VietSunTravel implements Runnable {

    private final String[] pages = new String[]{
        "http://vietsuntravel.com/du-lich-nuoc-ngoai.html",
        "http://vietsuntravel.com/du-lich-trong-nuoc.html"
    };

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EZGoProjectPU");
    Agenda agenda;
    String realPath;

    public VietSunTravel(String realPath) {
        this.realPath = realPath;
    }
    
    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            //Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private String getHtml(String urlPath) 
            throws MalformedURLException, IOException{
        URL url = new URL(urlPath);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        Scanner sc = new Scanner(is, "utf-8");

        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            sb.append(s);
            sb.append('\n');
        }

        return sb.toString().trim().replace("\t", "");
    }
    
    private List<String> getListTourLink(String url)
            throws IOException, XMLStreamException {
        List<String> result = new ArrayList<>();
        String html = getHtml(url).replace(" & ", " và ");
        if (!html.contains("<ul id=\"products\"")) {
            return result;
        }
        int pos = html.indexOf("<ul id=\"products\"");
        int pos2 = html.indexOf("<div class=\"view_past_tour\">");
        html = html.substring(html.indexOf("<ul id=\"products\""), html.indexOf("<div class=\"view_past_tour\">") - 60);
        while (html.contains("  ")) {
            html = html.replace("  ", " ");
        }
        while (html.contains("\n\n")) {
            html = html.replace("\n\n", "\n");
        }
        
        XMLInputFactory fac = XMLInputFactory.newInstance();

        fac.setProperty(XMLInputFactory.IS_VALIDATING, false);
        fac.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        fac.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);

        XMLEventReader iterator = fac.createXMLEventReader(new StringReader(html));

        boolean rowFlag = false;        
        while (iterator.hasNext()) {

            try {
                XMLEvent event = iterator.nextEvent();

                if (event.isStartElement()) {
                    StartElement tag = (StartElement) event;
                    if (tag.getName().getLocalPart().equals("<div class=\"each_product\">")) {
                        rowFlag = true;
                    } else if (rowFlag && tag.getName().getLocalPart().equals("a")) {
                        String href = tag.getAttributeByName(new QName("href")).getValue();
                        result.add(href);
                        rowFlag = false;
                        
                        // Test
                        System.out.println("aaaaaaaaaaa " + href);
                    }
                }//end start element 
            } catch (XMLStreamException e) {
                System.out.println("Stream Error: " + e.getMessage() + " <== " + e.getCause());
            }

        }//end while

        return result;
    } // end getListTourLink method

    private Tour getTourFromUrl(String url) throws IOException,
            ParseException, XMLStreamException, StringIndexOutOfBoundsException {
        return null;
    }

    private List<Tour> parseHtml() {

        List<Tour> result = new ArrayList<>();
        List<String> urls = new ArrayList<>();

        System.out.println("Begin get tours' urls");
        for (String page : pages) {
            if (page.equals("http://vietsuntravel.com/du-lich-nuoc-ngoai.html")) {
                for (int j = 1; j <= 4; j++) {
                    try {
                        urls.addAll(getListTourLink(page + "?page=" + j));
                    } catch (IOException | XMLStreamException e) {
                        System.out.println("Parse Error: ");
                        System.out.println("-- Link: " + page + "?page=" + j);
                        System.out.println("-- Message: " + e.getMessage());
                        System.out.println("-- Cause: " + e.getCause());
                        e.printStackTrace();
                    }
                }
            } else {
                for (int j = 1; j <= 2; j++) {
                    try {
                        urls.addAll(getListTourLink(page + "?page=" + j));
                    } catch (IOException | XMLStreamException e) {
                        System.out.println("Parse Error: ");
                        System.out.println("-- Link: " + page + "?page=" + j);
                        System.out.println("-- Message: " + e.getMessage());
                        System.out.println("-- Cause: " + e.getCause());
                        e.printStackTrace();
                    }
                }
            }

        }
        System.out.println("Done get tours' url, there are " + urls.size() + " urls");

        System.out.println("Begin get Tour");
        int k = 1;
        for (String url : urls) {
            try {
                Tour t = getTourFromUrl(url);
                if (t != null) {
                    System.out.println("Parse successfully tour #" + k);
                    k++;
                }
                result.add(t);
            } catch (IOException | ParseException | XMLStreamException | StringIndexOutOfBoundsException e) {
                System.out.println("Parse Error: ");
                System.out.println("-- Link: " + url);
                System.out.println("-- Message: " + e.getMessage());
                System.out.println("-- Cause: " + e.getCause());
            }
        }
        return result;
    }

    @Override
    public void run() {
        EntityManager em = emf.createEntityManager();

        //Delete expired tours
        String sql = "SELECT t FROM Tour t WHERE t.agendaID = :id";
        Query query = em.createQuery(sql);
        query.setParameter("id", agenda);

        List<Tour> result = query.getResultList();
        Date now = new Date();
        em.getTransaction().begin();
        for (Tour tour : result) {
            if (tour.getDepartureDay().before(now)) {
                em.remove(tour);
            }
        }
        em.getTransaction().commit();

        //Parse new tours
        System.out.println("Begin Parse tour");
        List<Tour> list = parseHtml();
        System.out.println("Parse tour finished! There are " + list.size() + " tours parsed");

        //Save tour
        System.out.println("Begin save tour");
        int k = 1;
        for (Tour tour : list) {
            String id = tour.getTourID();
            Tour t = (Tour) em.find(Tour.class, id);
            if (t != null) {
                System.out.println("Existing ID: " + id);
            } else {
                try {
                    em.getTransaction().begin();
                    em.persist(tour);
                    em.getTransaction().commit();
                    System.out.println("Save successfully tour #" + k++);
                } catch (Exception e) {
                    System.out.println("SQL Error: ");
                    System.out.println("-- Tour: " + tour.getLink());
                    System.out.println("-- Message: " + e.getMessage());
                }
            }
        }
        System.out.println("Save tour finished! There are " + (k - 1) + " tours saved");
        try {
            em.close();
        } catch (Exception e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }

}
