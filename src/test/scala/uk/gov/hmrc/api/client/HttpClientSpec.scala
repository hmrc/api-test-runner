package uk.gov.hmrc.api.client

import com.github.tomakehurst.wiremock.WireMockServer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import uk.gov.hmrc.api.test.WireMockSupport
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.http.RequestMethod
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.SpanSugar.convertIntToGrainOfTime

import scala.concurrent.Await

class HttpClientSpec extends AnyWordSpec with Matchers with ScalaFutures with WireMockSupport {

  "HttpClient#get" should {
    "be able to make a request successfully" in {
      val client = new HttpClient {} // TODO look up how this is used, why is it a trait?

      stubFor(
        get(urlEqualTo("/hello"))
          .willReturn(
            aResponse()
              .withBody("Hello world!")
              .withStatus(200)
          )
      )

      client.get(s"$wireMockUrl/hello").futureValue.body shouldBe "Hello world!"
    }

    "should send requests via zap proxy when enabled in system properties" in {
      val fakeZapProxy = new WireMockServer(WireMockConfiguration.wireMockConfig().port(11000))
      try {
        fakeZapProxy.start()
        System.setProperty("security.assessment", "true")
        val client         = new HttpClient {}
        Await.result(client.get(s"$wireMockUrl/hello"), 2.seconds)
        val proxiedRequest = fakeZapProxy.findUnmatchedRequests().getRequests.get(0)
        proxiedRequest.getAbsoluteUrl shouldBe s"$wireMockUrl/hello"
        proxiedRequest.getMethod      shouldBe RequestMethod.GET
      } finally {
        System.clearProperty("security.assessment")
        fakeZapProxy.stop()
      }
    }

    "should not send requests via zap proxy when disabled in system properties" in {
      val fakeZapProxy = new WireMockServer(WireMockConfiguration.wireMockConfig().port(11000))
      try {
        fakeZapProxy.start()
        System.setProperty("security.assessment", "false")
        val client          = new HttpClient {}
        Await.result(client.get(s"$wireMockUrl/hello"), 2.seconds)
        val proxiedRequests = fakeZapProxy.findUnmatchedRequests().getRequests
        proxiedRequests.size shouldBe 0
      } finally {
        System.clearProperty("security.assessment")
        fakeZapProxy.stop()
      }
    }

    "should not send requests via zap proxy when not set in system properties" in {
      val fakeZapProxy = new WireMockServer(WireMockConfiguration.wireMockConfig().port(11000))
      try {
        fakeZapProxy.start()
        System.clearProperty("security.assessment")
        val client          = new HttpClient {}
        Await.result(client.get(s"$wireMockUrl/hello"), 2.seconds)
        val proxiedRequests = fakeZapProxy.findUnmatchedRequests().getRequests
        proxiedRequests.size shouldBe 0
      } finally
        fakeZapProxy.stop()
    }
  }

}
