import bjs.project.ResolverPlugin
import sbt._

class SeleniumPluginProject(info:ProjectInfo) extends PluginProject(info:ProjectInfo) with ResolverPlugin {
	val seleniumServer = "org.seleniumhq.selenium" % "selenium-server" % "2.0a2"
	val qdox = "com.thoughtworks.qdox" % "qdox" % "1.9.2"
	val jmock = "jmock" % "jmock" % "1.2.0"
	val easymock = "org.easymock" % "easymock" % "2.5.2"

	// publish to a Maven style repository
	override def managedStyle = ManagedStyle.Maven
}

