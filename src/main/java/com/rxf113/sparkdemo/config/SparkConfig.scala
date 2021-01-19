package com.rxf113.sparkdemo.config

import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class SparkConfig {

  @Bean
  def SparkConf(): SparkConf = {
    new SparkConf().setAppName("rxf113").setMaster("local")
  }

  @Bean
  def SparkContext = new SparkContext(SparkConf())

  //  def sparkContext(): SparkContext = {
//    new SparkContext(sparkConf())
//  }
}
