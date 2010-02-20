import bjs.project.ResolverPlugin
import sbt._

class SeleniumPluginProject(info:ProjectInfo) extends PluginProject(info:ProjectInfo) with ResolverPlugin {
	val seleniumServer = "org.seleniumhq.selenium" % "selenium-server" % "2.0a2"

	// publish to a Maven style repository
	override def managedStyle = ManagedStyle.Maven
}

