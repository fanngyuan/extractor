package com.snowcat.job;

import com.snowcat.config.Job;
import com.snowcat.config.Jobs;
import com.snowcat.config.Qjob;

/**
 * Created by fanngyuan on 11/6/14.
 */
public class JobsHandler {

    public static void handleJobs(Jobs jobs){
        for(Job job:jobs.getJob()){

        }
        for(Qjob qjob:jobs.getQjob()){
            
        }
    }
}
