package selenium

import org.openqa.selenium.server.{RemoteControlConfiguration,SeleniumServer}
import sbt._

trait SeleniumPlugin {
	self: Project =>
	val seleniumConfig = new RemoteControlConfiguration()
	private var server:Option[SeleniumServer] = None

	lazy val seleniumStart = task {
		try {
			if (server == None) {
				server = Some(new SeleniumServer(seleniumConfig))
			} else {
				server.get.stop
				server.get.start
			}
			None
		} catch {
			case e =>
				e.printStackTrace
				Some("Could not start Selenium Server because of: " + e.getMessage)
		}
	}

	lazy val seleniumStop = task {
		try {
			if (server.isDefined) { server.get.stop }
			None
		} catch {
			case e =>
				Some("Could not stop Selenium Server because of: " + e.getMessage)
		}
	}
}

