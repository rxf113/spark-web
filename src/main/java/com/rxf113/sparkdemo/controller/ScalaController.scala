package com.rxf113.sparkdemo.controller

import com.rxf113.sparkdemo.pojo.Test
import javax.annotation.Resource
import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.web.bind.annotation._
import org.apache.spark.sql.SparkSession

@RestController
class ScalaController {

  @Resource
  var sparkContext: SparkContext = _

  @Resource
  var sparkConf: SparkConf = _

  @Resource
  var javaController: JavaController = _

  /**
   * @param id
   * @return
   */
  @GetMapping(value = Array("/scala"))
  @ResponseBody
  def findOne(@RequestParam(value = "id") id: Integer): Any = {
    val textFileRdd = sparkContext.textFile("hdfs://139.9.137.157:9000/data/mysqlToHdfs.txt__bb3500dc_90e9_4d8b_b016_5c659b6a72d7")
    val total = textFileRdd.count()
    val strings = textFileRdd.map(_.split("\n")).map(_ (0).split("\t")).filter(_ (1).toInt.equals(1)).collect()
    val eqOne = strings.length
   val ratio = (eqOne/total * 100).toDouble + "%"
    f"eqOne: $eqOne -- total: $total -- ratio: $ratio"
  }

  /**
   */
  @GetMapping(value = Array("/executeSql"))
  @ResponseBody
  def sql(): Any = {
    val sparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import sparkSession.implicits._
    val deptRDD = sparkSession.read.textFile("hdfs://139.9.137.157:9000/data/mysqlToHdfs.txt__bb3500dc_90e9_4d8b_b016_5c659b6a72d7")
      .map(_.split("\n")).map(line=>line(0).split("\t")).map(line=>Test(line(0).toInt,line(1).toInt,line(2)))
    val df = deptRDD.toDF()
    df.createOrReplaceTempView("dept")
    // 使用SQL语句进行查询
    sparkSession.sql("select * from dept").show()
    df.drop()
    ""
  }
}