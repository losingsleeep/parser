package com.bobby.parser.config;

import com.bobby.parser.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.format.DateTimeFormatter;

/**
 * @author Babak Eghbali (Bob)
 * @since 2018/06/08
 */
@Configuration
@EnableAsync
public class Config {

    @Bean
    public DateTimeFormatter dateTimeFormatter(
        @Value("${"+Constants.DATE_FORMAT_IN_FILE_KEY+":"+ Constants.DATE_FORMAT_IN_FILE +"}") String pattern){
        return DateTimeFormatter.ofPattern(pattern);
    }

    @Bean
    @ConditionalOnProperty(Constants.DB_ASYNC_KEY)
    public ReqRepo reqRepoAsync(RequestRepository repository){
        return new ReqRepoImplAsync(repository);
    }

    @Bean
    @ConditionalOnMissingBean(ReqRepo.class)
    public ReqRepo reqRepo(RequestRepository repository){
        return new ReqRepoImpl(repository);
    }

    @Bean
    @ConditionalOnProperty(Constants.DB_ASYNC_KEY)
    public BlockedRepo blockedRepoAsync(BlockedRepository repository){
        return new BlockedRepoImplAsync(repository);
    }

    @Bean
    @ConditionalOnMissingBean(BlockedRepo.class)
    public BlockedRepo blockedRepo(BlockedRepository repository){
        return new BlockedRepoImpl(repository);
    }

}
