
# api-test-runner

A helper library for API testing at HMRC.

## Usage

### Declare dependency

Declare the library as a project dependency as follows:

```scala
"uk.gov.hmrc" %% "api-test-runner" % "x.x.x" % Test
```

See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/project/Dependencies.scala).

### Test environment configuration

Test environment configuration is available. A configuration file is required to use it. See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/src/test/resources/application.conf).

Create a url from a configuration file as follows:

```scala
val url: String = TestEnvironment.url("service") + "/path"
```

See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/src/test/scala/uk/gov/hmrc/api/service/IndividualsMatchingService.scala).


### Log info to help debug test failures

```scala
import uk.gov.hmrc.api.utils.ApiLogger.logger

logger.info("Log a message")
```

## Development

### Tests

Run tests as follows:

```bash
sbt clean test
```

### Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
