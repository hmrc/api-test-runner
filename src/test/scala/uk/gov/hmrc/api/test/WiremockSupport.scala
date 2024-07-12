package uk.gov.hmrc.api.test

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, Suite}
import com.github.tomakehurst.wiremock._
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.slf4j.{Logger, LoggerFactory}

trait WireMockSupport extends BeforeAndAfterAll with BeforeAndAfterEach {
  this: Suite =>

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  lazy val wireMockHost: String =
    "localhost"

  lazy val wireMockPort: Int =
    wireMockServer.port()

  lazy val wireMockUrl: String =
    s"http://$wireMockHost:$wireMockPort"

  lazy val wireMockServer =
    new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort())

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    wireMockServer.start()
    WireMock.configureFor(wireMockHost, wireMockServer.port())
    logger.info(s"Started WireMock server on host: $wireMockHost, port: ${wireMockServer.port()}")
  }

  override protected def afterAll(): Unit = {
    wireMockServer.stop()
    super.afterAll()
  }

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    wireMockServer.resetMappings()
  }
}
