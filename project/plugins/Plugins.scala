import sbt._

class Plugins(info:ProjectInfo) extends PluginDefinition(info) {
	// Repository location for sbt-plugins
	val bryanjswift = "Bryan J Swift" at "http://repos.bryanjswift.com/maven2/"
	// sbt-plugins
	val plugins = "bryanjswift" % "sbt-plugins" % "0.3"
}

