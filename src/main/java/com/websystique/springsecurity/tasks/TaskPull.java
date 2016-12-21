
package com.websystique.springsecurity.tasks;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

/*
*рассылка сообщений: каждый критерий - в своем потоке
http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-03
**/
@Component
public class TaskPull {
    
    public TaskPull(){
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler;
        try{
            //назначить несколько тесков по числу юзеров динамически
            scheduler = schedFact.getScheduler();
            JobDetail details;
            details = JobBuilder.newJob(Task.class)
                      .usingJobData("login", "markova_d_a")
                    //.withDescription("something")
                    //.withIdentity("mejob","myGroup")
                    .storeDurably(true).build();
            CronTriggerImpl trigger = new CronTriggerImpl();
            trigger.setName("T1");
            trigger.setCronExpression("0 0/1 * 1/1 * ?");
            scheduler.scheduleJob(details, trigger);
            //scheduler.start();   - остановка тесков,чтобы не мешали работать         
        }
        catch(SchedulerException e){
            System.out.println("SCHEDULER EXCEPTION");
            e.printStackTrace();
        } catch (ParseException ex) {
            System.out.println("CRON FORMAT EXCEPTION");
            Logger.getLogger(TaskPull.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
