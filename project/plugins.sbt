//用来打包的插件
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")
//用来打包成zip文件的插件
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "0.7.4")

//查看依赖的插件
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")