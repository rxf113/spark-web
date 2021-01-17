package com.rxf113.sparkdemo.controller

import javax.annotation.Resource
import org.apache.spark.SparkContext
import org.springframework.web.bind.annotation._

@RestController
class ScalaController {

  @Resource
  var sparkContext: SparkContext = _

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

  //  def test() Unit = {
  //    val textFileRdd = sparkContext.textFile("hdfs://139.9.137.157:9000/data/mysqlToHdfs.txt__bb3500dc_90e9_4d8b_b016_5c659b6a72d7")
  //    textFileRdd.foreach(println(_)
  //  }
}