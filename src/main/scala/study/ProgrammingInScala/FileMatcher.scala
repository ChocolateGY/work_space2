package study.ProgrammingInScala

import java.io.File

object FileMatcher {
  /*private def filesHere = new File(".").listFiles()

  //  def filesMatching(query: String, matcher: (String, String) => Boolean) = {
  //    for(file<-filesHere;if matcher(file.getName,query))yield file
  //  }
  //  def filesEnding (query:String) = filesMatching(query,_.endsWith(_))
  //  def filesContains (query:String) = filesMatching(query,_.contains(_))
  //  def filesRegex (query:String) = filesMatching(query,_.matches(_))
  private def filesMatching(matcher: String => Boolean) = {
    for (file <- filesHere; if matcher(file.getName)) yield file
  }

  def filesEnding(query: String) = filesMatching(_.endsWith(query))

  def filesContaining(query: String) = filesMatching(_.contains(query))

  def conainsNeg(list: List[Int]) = list.exists(_ < 0)*/
  private def fileHere = (new java.io.File(".")).listFiles

  def fileEnding(query: String) = {
    for (file <- fileHere if file.getName.endsWith(query)) yield file
  }

  def fileContaining(query: String) =
    fileHere.filter(file => file.getName.contains(query))

  def fileMatching(query: String, matcher: (String, String) => Boolean) =
    fileHere.filter(f => matcher(f.getName, query))

  def fileStart(query: String) =
    fileMatching(query, _.startsWith(_))

  def fileMatching2(matcher: (String) => Boolean) =
    fileHere.filter(f => matcher(f.getName))

  def fileRegex(query: String) =
    fileMatching2(_.matches(query))

  def main(args: Array[String]): Unit = {
    fileEnding(".sbt").foreach(println)
    fileContaining("build").foreach(println)
    fileStart("b").foreach(f => println(f.getName))
    fileRegex(".*\\..*").foreach(f=>println(f.getName))
  }
}
