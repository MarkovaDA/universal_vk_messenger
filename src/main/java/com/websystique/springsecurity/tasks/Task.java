package com.websystique.springsecurity.tasks;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;


public class Task implements Job {
    //условно рассчитать каждый класс под свой тип пользователей
    //прогружаем все критерии пользователя
    //по каждому критерию отсылать двадцать сообщений
    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        //JobKey key = jec.getJobDetail().getKey();
        JobDataMap dataMap = jec.getJobDetail().getJobDataMap();
        String userLogin = dataMap.getString("login");
        System.out.println("TASK RUNNING =" + userLogin);    
    }   
}
