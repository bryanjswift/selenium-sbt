package selenium

import org.openqa.selenium.server.{RemoteControlConfiguration,SeleniumServer}
import sbt._

trait SeleniumPlugin {
	self: Project =>
	lazy val config = new RemoteControlConfiguration()
	lazy val server = new SeleniumServer(config)

	lazy val seleniumStart = task {
		try {
			server.start
			None
		} catch {
			case e =>
				Some("Could not start Selenium Server because of: " + e.getMessage)
		}
	}

	def stopSeleniumTask = {
		try {
			server.stop
			None
		} catch {
			case e =>
				Some("Could not stop Selenium Server because of: " + e.getMessage)
		}
	}
	lazy val seleniumStop = task {
		try {
			server.stop
			None
		} catch {
			case e =>
				Some("Could not stop Selenium Server because of: " + e.getMessage)
		}
	}
}

