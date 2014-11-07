package com.snowcat.dao;


import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by fanngyuan on 11/6/14.
 */
public class BaseDao {
    JdbcTemplate template;
    private static final Logger logger = Logger.getLogger(BaseDao.class);

    private String format(String sql,Object... params){
        StringBuilder sb = new StringBuilder();
        sb.append("sql is ");
        sb.append(sql);
        sb.append(" params is :");
        for(int i=0;i<params.length;i++){
            sb.append(" param ");
            sb.append(i);
            sb.append(" is:");
            sb.append(params[i]);
        }
        return sb.toString();
    }

    public List<Object[]> query(String sql,Object... params){
        logger.info(format(sql, params));
        try{
            List<Object[]> result=this.template.query(sql,new ObjectArrayRowMapper(),params);
            return result;
        }catch (Exception e){
            logger.warn("execute sql err",e);
            return null;
        }
    }

    public boolean execute(String sql,List<Object[]> params){
        for(Object[] param:params){
            this.execute(sql,params);
        }
        return true;
    }

    public boolean execute(String sql,Object... params){
        logger.info(format(sql, params));
        try{
            this.template.update(sql,params);
            return true;
        }catch (Exception e){
            logger.warn("execute sql err",e);
            return false;
        }
    }
}

class ObjectArrayRowMapper implements RowMapper<Object[]>{

    @Override
    public Object[] mapRow(ResultSet resultSet, int i) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        Object[] result = new Object[columnsNumber];
        for(int j=1;i<columnsNumber;j++){
            result[j]=resultSet.getObject(j);
        }
        return result;
    }
}