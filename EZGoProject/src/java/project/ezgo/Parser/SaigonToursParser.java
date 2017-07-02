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
 * @author hp
 */
public class SaigonToursParser implements Runnable {

    private final String[] pages = new String[]{
        "http://saigontours.asia/danh-muc-tour/du-lich-ngoai-nuoc.html",
        "http://saigontours.asia/danh-muc-tour/du-lich-trong-nuoc.html"
    };

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EZGoProjectPU");
    Agenda agenda;
    String realPath;

    public SaigonToursParser(String realPath) {
        this.realPath = realPath;

        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Agenda.findByName");
        q.setParameter("name", "Saigon Tours");

        Object result = q.getSingleResult();
        if (result != null) {
            agenda = (Agenda) result;
        }
    }

    private String getHtml(String urlPath)
            throws MalformedURLException, IOException {

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

    private List<String> getListTourLink(String url) throws IOException, XMLStreamException {
        List<String> result = new ArrayList<>();
        String html = getHtml(url).replace(" & ", " và ");
        if (!html.contains("<table>")) {
            return result;
        }
        html = html.substring(html.indexOf("<table>") - 1, html.indexOf("</table>") + 10);
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
                    if (tag.getName().getLocalPart().equals("tr")) {
                        rowFlag = true;
                    } else if (rowFlag && tag.getName().getLocalPart().equals("a")) {
                        String href = tag.getAttributeByName(new QName("href")).getValue();
                        result.add(href);
                        rowFlag = false;
                    }
                }//end start element 
            } catch (XMLStreamException e) {
                System.out.println("Stream Error: " + e.getMessage() + " <== " + e.getCause());
            }

        }//end while

        return result;
    }// end method

    private Tour getTourFromUrl(String url) throws IOException, XMLStreamException, ParseException {
        String html = getHtml(url).replace(" & ", " và ");
        while (html.contains("  ")) {
            html = html.replace("  ", " ");
        }
        while (html.contains("\n\n")) {
            html = html.replace("\n\n", "\n");
        }

        String imgArea = html.substring(html.indexOf("<div class=\"owlSyncWidget\">") - 1, html.indexOf("<div class=\"infoProduct\">") - 1);
        String info = html.substring(html.indexOf("<div class=\"infoProduct\">") - 1, html.indexOf("<div id=\"bookPage\"") - 1);
        String schedule = html.substring(html.indexOf("id=\"detailTab-1\">") - 1, html.indexOf("id=\"detailTab-2\">") + 10);
        schedule = schedule.replace("\n", "").replace("\t", " ");
        String policy = html.substring(html.indexOf("id=\"detailTab-3\">") - 1, html.indexOf("id=\"detailTab-4\">") + 10);
        policy = policy.replace("\n", "").replace("\t", " ");

        XMLInputFactory fac = XMLInputFactory.newFactory();
        fac.setProperty(XMLInputFactory.IS_VALIDATING, false);
        fac.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);

        XMLEventReader reader = fac.createXMLEventReader(new StringReader(info));

        boolean infoFlag = false;
        boolean imgFlag = false;
        int strongTag = 1;

        Tour tour = new Tour();
        tour.setAgendaID(agenda);
        tour.setLink(url);
        tour.setSchedule(schedule.substring(schedule.indexOf("<body>") + 6, schedule.indexOf("</body>")));
        tour.setPolicy(policy.substring(policy.indexOf("<body>") + 6, policy.indexOf("</body>")));

        while (reader.hasNext()) {
            try {
                XMLEvent event = reader.nextEvent();

                if (event.isStartElement()) {
                    StartElement tag = (StartElement) event;
                    if (tag.getName().getLocalPart().equals("div") && tag.getAttributeByName(new QName("class")) != null
                            && tag.getAttributeByName(new QName("class")).getValue().equals("infoProduct")) {

                        //inside <div class="infoProduct">                        
                        infoFlag = true;
                        reader.nextTag(); //<h1>                        
                        Characters h1 = (Characters) reader.nextEvent(); //Characters inside <h1>
                        tour.setName(h1.getData());
                    } else if (infoFlag && tag.getName().getLocalPart().equals("strong")) {
                        Characters ch = (Characters) reader.nextEvent(); //inside strong tag
                        String text = ch.getData().replace("\n", "").replace("\t", "").trim();
                        switch (strongTag++) {
                            case 1:
                                tour.setTourID(text.replace(" ", "").replace("?", "-").replace("*", ""));
                                break;
                            case 2:
                                tour.setDuration(text);
                                break;
                            case 3:
                                DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
                                Date d = f.parse(text);
                                tour.setDepartureDay(d);
                                break;
                            case 4:
                                tour.setDeparture(text);
                                break;
                            case 6:
                                String price = text.replace(".", "");
                                if (text.indexOf(" ") > 0) {
                                    price = price.substring(0, text.indexOf(" ")).trim();
                                }
                                tour.setPrice(BigInteger.valueOf(Long.parseLong(price)));
                                infoFlag = false;
                                break;
                            default:
                                break;
                        }//end switch
                    } //end <strong>
                    else if (infoFlag && tag.getName().getLocalPart().equals("del")) {
                        Characters text = (Characters) reader.nextEvent(); //Characters inside <del>
                        tour.setOldPrice(BigInteger.valueOf(Long.parseLong(text.getData().replace(".", ""))));
                    }

                }//end start element
                else if (event.isEndElement() && !infoFlag) {
                    break;
                }
            } catch (XMLStreamException e) {
                System.out.println("Pasrer Error: " + e.getMessage());
            }

        }// end while

        String imgUrl = parseHtmlGetImgLink(imgArea);
        if (imgUrl != null) {
            String id = tour.getTourID();
            while (id.charAt(id.length() - 1) == '-') {
                id = id.substring(0, id.length() - 1);
            }

            String extension = imgUrl.substring(imgUrl.lastIndexOf("."));
            String path = "img/tour/" + id + extension;
            this.downloadImage(imgUrl, realPath + path);
            tour.setPicture(path);
        }
        return tour;
    }

    private List<Tour> parseHtml() {

        List<Tour> result = new ArrayList<>();
        List<String> urls = new ArrayList<>();

        System.out.println("Begin get tours' urls");
        for (String page : pages) {
            for (int j = 1; j <= 1; j++) {
                try {
                    urls.addAll(getListTourLink(page + "?curPg=" + j));
                } catch (IOException | XMLStreamException e) {
                    System.out.println("Parse Error: ");
                    System.out.println("-- Link: " + page + "?curPg=" + j);
                    System.out.println("-- Message: " + e.getMessage());
                    System.out.println("-- Cause: " + e.getCause());
                    e.printStackTrace();
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
                    result.add(t);
                }
                
            } catch (IOException | ParseException | XMLStreamException | StringIndexOutOfBoundsException e) {
                System.out.println("Parse Error: ");
                System.out.println("-- Link: " + url);
                System.out.println("-- Message: " + e.getMessage());
                System.out.println("-- Cause: " + e.getCause());
            }
        }

        return result;
    }

    private void downloadImage(String imgUrl, String filePath) throws MalformedURLException, FileNotFoundException, IOException {
        URL url = new URL(imgUrl.replace(" ", "%20"));
        InputStream is = url.openStream();
        File f = new File(filePath);
        if (f.exists()) {
            return;
        } else {
            f.createNewFile();
        }
        OutputStream os = new FileOutputStream(f);

        byte[] b = new byte[2048];
        int length;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

    @Override
    public void run() {
        EntityManager em = emf.createEntityManager();

        //delete expired tours
        String sql = "SELECT t FROM Tour t WHERE t.agendaID = :id";
        Query q = em.createQuery(sql);
        q.setParameter("id", agenda);

        List<Tour> result = q.getResultList();
        Date now = new Date();
        em.getTransaction().begin();
        for (Tour tour : result) {
            if (tour.getDepartureDay().before(now)) {
                em.remove(tour);
            }
        }
        em.getTransaction().commit();

        //parse new tours
        System.out.println("Begin Parse tour");
        List<Tour> list = parseHtml();
        System.out.println("Parse tour finished! There are " + list.size() + " tours parsed");

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

    private String parseHtmlGetImgLink(String imgArea) throws XMLStreamException {
        XMLInputFactory fac = XMLInputFactory.newInstance();

        fac.setProperty(XMLInputFactory.IS_VALIDATING, false);
        fac.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);

        XMLEventReader reader = fac.createXMLEventReader(new StringReader(imgArea));

        String result = null;
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                StartElement tag = (StartElement) event;
                if (tag.getName().getLocalPart().equals("img")) {
                    result = tag.getAttributeByName(new QName("src")).getValue();

                    break;
                }
            }
        }

        return result;
    }
}
