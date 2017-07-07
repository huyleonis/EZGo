/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import project.ezgo.Parser.SaigonToursParser;
import project.ezgo.Parser.VietSunTravel;

/**
 *
 * @author hp
 */
public class EzgoContextListener implements ServletContextListener {
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/");
        
        scheduler = Executors.newSingleThreadScheduledExecutor();
        //Thread will begin after 2-min delay, after that it will repeat every day (60 min * 24 hour)
        scheduler.scheduleAtFixedRate(new SaigonToursParser(realPath), 0, 1, TimeUnit.DAYS);
//        scheduler.scheduleAtFixedRate(new VietSunTravel(realPath), 0, 30, TimeUnit.DAYS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {             
        scheduler.shutdownNow();
    }
    
}
