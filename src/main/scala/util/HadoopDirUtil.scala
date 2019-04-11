package util

object HadoopDirUtil {
  // HDFS File and Dir operations


  import org.apache.hadoop.conf.Configuration
  import org.apache.hadoop.fs.{
    Path => HadoopPath,
    FileSystem => HadoopFileSystem,
    LocatedFileStatus,
    FileAlreadyExistsException => HadoopFileAlreadyExistsException,
    FileUtil => HadoopFileUtil
  }

  import java.io.{BufferedInputStream, OutputStreamWriter}


  def _toGB(num_bytes: Long) = {
    num_bytes / math.pow(1024, 3)
  }

  def _toMB(num_bytes: Long) = {
    num_bytes / math.pow(1024, 2)
  }

  var cluster_name = "new"

  def setClusterName(name: String) = {
    this.cluster_name = name
  }

  def getClusterName(): String = {
    this.cluster_name
  }

  def createHDFS(): HadoopFileSystem = {
    val cluster_name = this.getClusterName
    val conf = new Configuration()
    if (cluster_name == "old") {
      conf.set("fs.defaultFS", "hdfs://172.18.0.1:8020");
    }
    else {
      conf.set("fs.defaultFS", "hdfs://172.17.128.1:9820");
    }

    val hdfs = HadoopFileSystem.get(conf)
    return hdfs
  }

  def createHDFSWithConf(cluster_name: String): (HadoopFileSystem, Configuration) = {
    val conf = new Configuration()
    if (cluster_name == "old") {
      conf.set("fs.defaultFS", "hdfs://172.18.0.1:8020");
    }
    else {
      conf.set("fs.defaultFS", "hdfs://172.17.128.1:9820");
    }

    val hdfs = HadoopFileSystem.get(conf)
    return (hdfs, conf)
  }

  def ls(dir: String) = {
    val hdfs = this.createHDFS()

    val stats = hdfs.listStatus(new HadoopPath(dir));
    for (i <- Range(0, stats.length)) {
      val stat = stats(i)
      var fp = ""
      if (stat.isFile()) {
        // regular file
        fp = stat.getPath().toString();
      }
      else if (stats(i).isDirectory()) {
        // dir
        fp = stats(i).getPath().toString();
        // listAll(stats(i).getPath().toString())
      }
      else if (stats(i).isSymlink()) {
        // is s symlink in linux
        fp = stats(i).getPath().toString();
      }
      val size = hdfs.getContentSummary(new HadoopPath(fp)).getLength()
      println(fp, _toMB(size).toString + " mb")
    }

    hdfs.close();
  }

  def rm(path: String): Unit = {
    val hdfs = this.createHDFS()

    val isExist = hdfs.exists(new HadoopPath(path))
    if (isExist) {
      //        println("welll")
      hdfs.delete(new HadoopPath(path), true) //true: delete files recursively
    }

    hdfs.close()
  }

  def cp(src: String, dst: String) = {
    val hdfs_src = this.createHDFS()
    // val hdfs_dst = this.createHDFS()

    HadoopFileUtil.copy(hdfs_src, new HadoopPath(src), hdfs_src, new HadoopPath(dst), false, false, hdfs_src.getConf) // deleteSource false, overwrite false

    // hdfs_dst.close()
    hdfs_src.close()
  }

  def put(path: String, content: String) = {
    val hdfs = this.createHDFS()
    val out = new OutputStreamWriter(hdfs.create(new HadoopPath(path), false))
    out.write(content)
    out.flush()
    out.close()
  }


}
