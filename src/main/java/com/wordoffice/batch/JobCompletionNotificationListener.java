package com.wordoffice.batch;

import com.wordoffice.model.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Executing job id " + jobExecution.getId());
    }

    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            List<Producto> result = jdbcTemplate.query("SELECT id, nombre, marca, precio, cantidad, estado, descuento FROM producto",
                    new RowMapper<Producto>() {
                        @Override
                        public Producto mapRow(ResultSet rs, int row) throws SQLException {
                            return new Producto(rs.getLong(1), rs.getString(2), rs.getString(3));
                        }
                    });
            System.out.println("Number of Records:"+result.size());
        }
    }
}
