package com.cstor.spark.mllib.thirteen

import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by Administrator on 2016/5/13.
 * 计算相关系数
 * ***皮尔森相关系数
 * ***斯皮尔森秩相关系数
 */
object IrisCorrect {


  def irisCorrect(rdd: RDD[String], rdd2: RDD[String], message: String) = {
    val dataX = rdd.flatMap(_.trim.split(" ").map(_.toDouble))
    val dataY = rdd2.flatMap(_.trim.split(" ").map(_.toDouble))
    val correlation = Statistics.corr(dataX, dataY, "spearman")
    // 默认使用皮尔森相关系数(pearsion): setosa和versicolor中的sepal.length的相关系数为：-0.08084972701755869
    // 使用斯皮尔森秩相关系数(spearman): setosa和versicolor中的sepal.length的相关系数为：-0.10163684956357018
    println(message + correlation)
  }

  def main(args: Array[String]) {
    val sc = new SparkContext(new SparkConf().setMaster("local").setAppName("iriscorrect"))

    // setosa和versicolor中的sepal.length的相关系数为：0.9999999999999999
    irisCorrect(sc.textFile("d://data/iris/setosa_sepal_length.txt")
      , sc.textFile("d://data/iris/setosa_sepal_length.txt")
      , "setosa和setosa中的sepal.length的相关系数为：")


    irisCorrect(sc.textFile("d://data/iris/setosa_sepal_length.txt")
      , sc.textFile("d://data/iris/versicolor_sepal_length.txt")
      , "setosa和versicolor中的sepal.length的相关系数为：")


  }


}
