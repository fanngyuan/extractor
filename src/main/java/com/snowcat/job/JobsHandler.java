package com.snowcat.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.snowcat.config.*;
import com.snowcat.dao.BaseDao;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by fanngyuan on 11/6/14.
 */
public class JobsHandler {

    private static Logger logger = Logger.getLogger(JobsHandler.class);

    public static void handleJobs(ApplicationContext context, Jobs jobs) {
        for (Job job : jobs.getJob()) {
            List<Action> actions = job.getAction();
            for (Action action : actions)
                runAction(context, action);
        }
        for (Qjob qjob : jobs.getQjob()) {

        }
    }

    private static void runAction(final ApplicationContext context, final Action action) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String dataSource = action.getSource().getDatasource();
                    BaseDao baseDao = context.getBean(dataSource, BaseDao.class);
                    Query query = action.getSource().getQuery();
                    String sql = query.getSql();
                    Params params = query.getParams();
                    List<String> ps = params.getParam();
                    Object[] parameters = new Object[ps.size()];
                    for (int i = 0; i < ps.size(); i++) {
                        parameters[i] = ps.get(i);
                    }
                    List<Object[]> result = baseDao.query(sql, parameters);
                    baseDao.execute(sql, result);
                } catch (Exception ex) {
                    logger.warn(action);
                    logger.warn("execute action failed", ex);
                }
            }
        });
        thread.start();
    }

    private static void runQAction(final ApplicationContext context, final Qaction action) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Qsource qsource=action.getQsource();
                String host=qsource.getHost();
                int port = qsource.getPort().intValue();
                String queue=qsource.getQueue();
                Jedis redis=new Jedis(host,port);
                List<String> values=redis.brpop(0,queue);
                if(values.size()==2){
                    String value=values.get(1);
                    JSONObject jsonObject=JSON.parseObject(value);
                }
            }
        });
        thread.start();
        ;
    }
}
