package talkingdata

object Code {
  def findXiaowenjian() = {
    import org.apache.hadoop.fs.{FileSystem, Path}
    import scala.collection.mutable

    val path = "/data/datacenter/label/apps"
    def getLastPath(path: String, fs: FileSystem): mutable.Map[String, (Double, Long, Double)] = {
      val arr = mutable.Map[String, (Double, Long, Double)]()
      val status = fs.globStatus(new Path(path))
      status.foreach{
        p =>
          if(p.isDirectory) {
            arr ++= getLastPath(s"${p.getPath.toString}/*", fs)
          } else {
            val parentPath = p.getPath.getParent
            val contentSummary = fs.getContentSummary(parentPath)
            val len = contentSummary.getLength
            val fileCount = contentSummary.getFileCount
            val fileAvg = len.toDouble/fileCount/1024/1024
            val fileDir = parentPath.toString
            if(!arr.contains(fileDir)) {
              arr += fileDir -> (len.toDouble / 1024 / 1024, fileCount, fileAvg)
            }
          }
      }
      arr
    }
//    val fs = FileSystem.get(sc.hadoopConfiguration)
//    val paths = getLastPath(path, fs)
//    paths.foreach(println)
  }
}
