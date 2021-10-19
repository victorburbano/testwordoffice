package com.wordoffice.config;

import com.wordoffice.batch.JobCompletionNotificationListener;
import com.wordoffice.batch.ProductoItemProcessor;
import com.wordoffice.dto.ProductoDto;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<ProductoDto> reader() {
        return new FlatFileItemReaderBuilder<ProductoDto>()
                .name("productoDtoItemReader")
                .resource(new ClassPathResource("productos.csv"))
                .delimited()
                .names(new String[]{"nombre", "marca", "precio", "cantidad", "estado", "descuento"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ProductoDto>() {{
                    setTargetType(ProductoDto.class);
                }})
                .linesToSkip(1)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<ProductoDto> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ProductoDto>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO producto (nombre, marca, precio, cantidad, estado, descuento) VALUES (:nombre, :marca, :precio, :cantidad, :estado, :descuento)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public ProductoItemProcessor processor() {
        return new ProductoItemProcessor();
    }

    @Bean
    public Job createProductoDtoJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("createProductoDtoJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<ProductoDto> writer) {
        return stepBuilderFactory.get("step1")
                .<ProductoDto, ProductoDto> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
