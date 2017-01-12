package com.websystique.springsecurity.tasks;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class TaskCriteria implements Job{
    //этот Task будет выбирать людей, соответсвующих переданному ему критерию
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        //разобраться и выключить задачу, после того,как все люди будут вытащены
        JobDataMap dataMap = jec.getJobDetail().getJobDataMap();
        System.out.println(dataMap.getString("taskName") + ": активен");
    }
    
}
