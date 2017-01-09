package com.websystique.springsecurity.tasks;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;


public class Task implements Job {
    //придется пернести не в вебку а в консольное 
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        //JobKey key = jec.getJobDetail().getKey();
        JobDataMap dataMap = jec.getJobDetail().getJobDataMap();
        String userLogin = dataMap.getString("login");
        System.out.println("TASK RUNNING =" + userLogin);    
    }   
}
