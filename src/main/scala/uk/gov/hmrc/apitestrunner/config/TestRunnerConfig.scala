/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.apitestrunner.config

import com.typesafe.config.{Config, ConfigException, ConfigFactory}

object TestRunnerConfig {

  private val config: Config =
    ConfigFactory.load()

  val securityAssessmentEnabled: Boolean =
    config.getBoolean("security.assessment")

  lazy val (zapProxyHost, zapProxyPort): (String, Int) = {
    val extract = raw"(.+):(\d+)".r
    config.getString("zap.host") match {
      case extract(host, port) => (host, port.toInt)
      case _                   => throw new ConfigException.BadValue("zap.host", "Format must be 'host:port'")
    }
  }
}
