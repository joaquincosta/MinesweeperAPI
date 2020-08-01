package com.deviget.minesweeper.facade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

@Configuration
public class MinesweeperFacadeConfiguration {

  @Bean
  public ConversionService conversionService() {
    ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
    factoryBean.setConverters(MinesweeperConverter.all());
    factoryBean.afterPropertiesSet();
    return factoryBean.getObject();
  }
}
