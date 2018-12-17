import com.typesafe.sbt.SbtNativePackager.NativePackagerHelper._
import com.typesafe.sbt.SbtNativePackager.{packageArchetype, _}

packageArchetype.java_application

//项目名称根据自己需求修改
val projectName = "work_space2"
val projectVersion = "1.0"
//如果scala版本有变动时修改
val projectScalaVersion = "2.11.8"


name := projectName

version := projectVersion

scalaVersion := projectScalaVersion

//设置maven仓库地址
resolvers += "Aliyun repository Manager" at "http://maven.aliyun.com/nexus/content/groups/public/"
resolvers += "Maven Central Repository" at "http://repo1.maven.org/maven2/"
resolvers += "Sonatype Nexus Repository Manager" at "https://maven.tenddata.com/nexus/content/groups/public/"
//resolvers += "Spark Packages Repo" at "http://dl.bintray.com/spark-packages/maven"

resolvers += Resolver.mavenLocal

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
//import de.johoop.findbugs4sbt.FindBugs._
//
//findbugsSettings
//
//import de.johoop.cpd4sbt.CopyPasteDetector._
//
//cpdSettings
//添加项目依赖
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.0" withSources() exclude("org.slf4j", "slf4j-api"),
  "org.apache.spark" %% "spark-sql" % "2.3.0",
  "org.apache.spark" %% "spark-mllib" % "2.3.0",
  "com.talkingdata.dmp" % "common-lib_2.10" % "1.0.16",
  "com.talkingdata.dmp" % "data-api_2.10" % "1.0.7" withSources() excludeAll (ExclusionRule(organization = "org.apache.spark")),
  "com.talkingdata.dmp" % "lib-iplocation" % "0.0.3",
  "com.talkingdata.dmp" % "td-platform-sql_2.10" % "1.0.3" withSources() excludeAll (ExclusionRule(organization = "org.apache.spark")), // % "provided"
  "com.talkingdata.dmp" % "platform-spark" % "0.0.1" exclude("org.slf4j", "slf4j-api") withSources() excludeAll (ExclusionRule(organization = "org.apache.spark")), //% "provided"
  "com.talkingdata.dmp" % "bitmap-ext_2.10" % "1.0.3",
  "com.talkingdata.dmp" % "ip-location" % "1.0.7",
  "com.talkingdata.analytics" % "msgpack" % "0.0.121.analytics-collector",
  "org.msgpack" % "msgpack-scala_2.10" % "0.6.11",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "com.talkingdata.commons" % "commons" % "0.0.2",
  "com.alibaba" % "fastjson" % "1.2.3",
  "org.javassist" % "javassist" % "3.18.1-GA",
  "com.talkingdata.analytics" % "analytics-eventpackage" % "0.0.7",
  "net.sf.jsi" % "jsi" % "1.1.0",
  "net.sf.trove4j" % "trove4j" % "3.0.3"
)

dependencyOverrides ++= Set(
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.7"
)

//---------------generate zip----------------------
mappings in Universal := {
  val universalMappings = (mappings in Universal).value
  val maps = universalMappings map {
    case (file, name) => {
      (file, "libs/" + file.getName)
    }
  }
  //  maps
  //添加第三方jar，就是需要打包到zip包里的jar包，根据自己需要修改
  val filtered = maps filter {
    case (file, name) => {
      name.contains("common-lib") ||
        name.contains("lib-iplocation") ||
        name.contains("data-api") ||
        name.contains("ip-location") ||
        name.contains("JavaEWAH") ||
        name.contains("bitmap-ext") ||
        name.contains("compute-job") ||
        name.contains("jackson-core") ||
        name.contains("jackson-databind") ||
        name.contains("msgpack") ||
        name.contains("javassist") ||
        name.contains("mysql-connector-java") ||
        name.contains("commons") ||
        name.contains("fastjson") ||
        name.contains("analytics-eventpackage") ||
        name.contains("msgpack-scala") ||
        name.contains("jsi") ||
        name.contains("trove4j")
    }
  }
  filtered
}

val packageName = projectName + "_" + projectScalaVersion.substring(0, projectScalaVersion.lastIndexOf(".")) + "-" + projectVersion + ".jar"
val packagePath = "target/scala-" + projectScalaVersion.substring(0, projectScalaVersion.lastIndexOf(".")) + "/" + packageName

mappings in Universal += file(packagePath) -> packageName

mappings in Universal ++= directory("src/main/resources/shell/")

//rename zip
/*packageBin in Universal := {
  val originalFileName = (packageBin in Universal).value
  val (base, ext) = originalFileName.baseAndExt
  val old_name = originalFileName.getName
  val base_name = old_name.substring(0, old_name.lastIndexOf('-'))
  val newFileName = file(originalFileName.getParent) / (base_name + "." + ext)
  IO.move(originalFileName, newFileName)
  newFileName
}*/
    