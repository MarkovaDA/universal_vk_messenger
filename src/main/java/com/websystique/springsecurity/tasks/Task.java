package com.websystique.springsecurity.tasks;


import com.websystique.springsecurity.mapper.DBMapper;
import com.websystique.springsecurity.service.CriteriaService;
import com.websystique.springsecurity.service.JDBCFactory;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.springframework.beans.factory.annotation.Autowired;


/*этот таск следит за обновлением критериев*/
public class Task implements Job {
  
    CriteriaService criteriaService;
    
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        criteriaService = new CriteriaService();
        int nextCriteriaId = -1;
        System.out.println("start task");
        try {
           nextCriteriaId = criteriaService.notConsideredCriteriaId(JDBCFactory.getConnection());
           criteriaService.updateNotConsidered(JDBCFactory.getConnection(), nextCriteriaId);
        } catch (SQLException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("next criteria task ->" + nextCriteriaId);
        //createNewCriteriaTask(nextCriteriaId); //каждый интервал времени создает новый теск критерия
        System.out.println("TASK ЗАПУСКА КРИТЕРИЕВ ЗАПУЩЕН"); 
    }
    
    private void createNewCriteriaTask(Integer criteriaId){
        Long prefix = System.currentTimeMillis();
        try {
            SchedulerFactory schedFactory = new org.quartz.impl.StdSchedulerFactory();
            Scheduler scheduler = schedFactory.getScheduler();
            
            JobDetail details = JobBuilder.newJob(TaskCriteria.class)
                    .usingJobData("criteriaId", criteriaId)
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
