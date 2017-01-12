package com.websystique.springsecurity.tasks;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;


public class Task implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        /*JobKey key = jec.getJobDetail().getKey();
        JobDataMap dataMap = jec.getJobDetail().getJobDataMap();
        String userLogin = dataMap.getString("login");*/
        createNewCriteriaTask(); //каждый интервал времени создает новый теск критерия
        System.out.println("TASK ЗАПУСКА КРИТЕРИЕВ ЗАПУЩЕН");    
    }
    
    private void createNewCriteriaTask(){
        Long prefix = System.currentTimeMillis();
        try {
            SchedulerFactory schedFactory = new org.quartz.impl.StdSchedulerFactory();
            Scheduler scheduler = schedFactory.getScheduler();
            
            JobDetail details = JobBuilder.newJob(TaskCriteria.class)
                    .usingJobData("taskName", "vkJobTask" + prefix)
                     //еще отправить сам строковый критерий
                    .withIdentity("vkJobTask" + prefix, "criteriaTask")
                    .storeDurably(true).        
                    build();
            Trigger trigger = newTrigger()
            .withIdentity("vkJobTrigger" + System.currentTimeMillis(), "criteriaTrigger")
            .startNow()
            .withSchedule(simpleSchedule()
            .withIntervalInMilliseconds(340)
            .repeatForever())            
            .build();
             scheduler.scheduleJob(details, trigger); 
        } catch (SchedulerException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
