package util

import java.sql.{Connection, DriverManager}

/**
  * Created by GuanYu on 2017-3-2.
  * 链接mysql数据库
  */
object ConnMysql {
  def getConnection(url: String, sqlUser: String, sqlPwd: String): Connection = {
    Class.forName("com.mysql.jdbc.Driver")
    val con = DriverManager.getConnection(url, sqlUser, sqlPwd)
    con
  }

  def main(args: Array[String]) {
    //例子
    val url = "jdbc:mysql://172.21.64.84:3306/apptaginfo"
    val user = "ling.tang"
    val pw = "TangLing@016"
    val table = "appinfo"
    val con = ConnMysql.getConnection(url, user, pw)
    val sql = s"select appname,hash from `$table` where appname in ('神州租车'," +
      s" '一嗨租车', '首汽租车','凹凸租车','宝驾出行','悟空租车','PP租车'," +
      s"'宜维租车','闪电租车','绿狗租车','TOGO'," +
      s"'Gofun','Car2share','惠租车','京腾租车','微租车')"
    val stmt = con.createStatement()
    val arrApp = scala.collection.mutable.ArrayBuffer[(String, Long)]()
    try {
      val rs = stmt.executeQuery(sql)
      while (rs.next())
        arrApp += rs.getString(1) -> rs.getLong(2)
    } finally {
      stmt.close()
      con.close()
    }

  }
}
