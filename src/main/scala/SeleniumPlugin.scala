package bryanjswift.selenium

import org.openqa.selenium.server.{RemoteControlConfiguration,SeleniumServer}
import sbt._

trait SeleniumPlugin {
	self: BasicScalaProject =>
	private val seleniumConfig = new RemoteControlConfiguration()
	private val server = new SeleniumServer(seleniumConfig)
	private val seleniumTestOptions = testOptions ++ List(TestCleanup(stopServer _))

	private def startServer =
		try {
			server.stop
			server.start
			None
		} catch {
			case mex:org.openqa.jetty.util.MultiException =>
				/* this is thrown even if the server starts up correctly, not sure why
					MultiException usually consists of a BindException (port in use) and
					a class not found exception (org.openqa.selenium.remote.server.DriverServlet) */
				None
			case e =>
				Some("Could not start Selenium Server because of: " + e.getMessage)
		}
	lazy val seleniumStart = task { startServer }

	private def stopServer =
		try {
			server.stop
			None
		} catch {
			case e => Some("Could not stop Selenium Server because of: " + e.getMessage)
		}
	lazy val seleniumStop = task { stopServer }

	lazy val seleniumTest =
		testTask(testFrameworks, testClasspath, testCompileConditional.analysis, seleniumTestOptions)
			.dependsOn(testCompile, copyResources, copyTestResources, seleniumStart) describedAs BasicScalaProject.TestDescription

}

