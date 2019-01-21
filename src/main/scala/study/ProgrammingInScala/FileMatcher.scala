package study.ProgrammingInScala

import java.io.File

object FileMatcher {
  private def filesHere = new File(".").listFiles()

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

  def conainsNeg(list: List[Int]) = list.exists(_ < 0)
}
