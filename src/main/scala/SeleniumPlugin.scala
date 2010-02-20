package selenium

import org.openqa.selenium.server.{RemoteControlConfiguration,SeleniumServer}
import sbt._

trait SeleniumPlugin {
	lazy val config = new RemoteControlConfiguration()
	lazy val server = new SeleniumServer(config)

	def startSeleniumTask = {
		try {
			server.start
			None
		} catch {
			case e =>
				Some("Could not start Selenium Server because of: " + e.getMessage)
		}
	}
	lazy val startSelenium = startSeleniumTask

	def stopSeleniumTask = {
		try {
			server.stop
			None
		} catch {
			case e =>
				Some("Could not stop Selenium Server because of: " + e.getMessage)
		}
	}
	lazy val stopSelenium = stopSeleniumTask
}

