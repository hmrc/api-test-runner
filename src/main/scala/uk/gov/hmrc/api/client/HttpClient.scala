/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.api.client

import akka.actor.ActorSystem
import play.api.libs.ws.DefaultBodyWritables._
import play.api.libs.ws.{DefaultWSProxyServer, StandaloneWSRequest, StandaloneWSResponse}
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.{ExecutionContext, Future}

trait HttpClient {

  implicit val actorSystem: ActorSystem = ActorSystem()
  val wsClient: StandaloneAhcWSClient   = StandaloneAhcWSClient()
  implicit val ec: ExecutionContext     = ExecutionContext.global
  lazy val shouldProxyForZap: Boolean   = sys.props.get("security.assessment").exists(_.toBoolean)

  private def standAloneWsRequestWithProxyIfConfigSet(standAloneWsRequest: StandaloneWSRequest): StandaloneWSRequest =
    if (shouldProxyForZap) {
      standAloneWsRequest.withProxyServer(
        DefaultWSProxyServer(protocol = Some("http"), host = "localhost", port = 11000)
      )
    } else {
      standAloneWsRequest
    }

  def get(url: String, headers: (String, String)*): Future[StandaloneWSResponse] =
    standAloneWsRequestWithProxyIfConfigSet(wsClient.url(url))
      .withHttpHeaders(headers: _*)
      .get()

  def post(url: String, bodyAsJson: String, headers: (String, String)*): Future[StandaloneWSResponse] =
    standAloneWsRequestWithProxyIfConfigSet(wsClient.url(url))
      .withHttpHeaders(headers: _*)
      .post(bodyAsJson)

  def delete(url: String, headers: (String, String)*): Future[StandaloneWSResponse] =
    standAloneWsRequestWithProxyIfConfigSet(wsClient.url(url))
      .withHttpHeaders(headers: _*)
      .delete()

}
