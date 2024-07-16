
# api-test-runner

A helper library for API testing at HMRC.

## Usage

### Declare dependency

Declare the library as a project dependency as follows:

```scala
"uk.gov.hmrc" %% "api-test-runner" % "x.x.x" % Test
```

See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/project/Dependencies.scala).

### Run your tests against different environments without having to alter your test scripts

> [!NOTE]
> You will need to define the hosts and paths to the services you're referring to in your test repository's configuration to use this functionality

For example, with the following in your test scripts:

```scala
val urlToCreateADeskproTicket: String = TestEnvironment.url("deskpro-ticket-queue") + "/ticket"
```

And these hosts and paths defined in `src/test/resources/application.conf`

```hocon
local {
  services {
    deskpro-ticket-queue {
      host: "http://localhost:8650"
      productionRoute: "/deskpro
    }
  }
}
staging {
  services {
    deskpro-ticket-queue {
      host: "https://deskpro-ticket-queue.protected.mdtp"
      productionRoute: "/deskpro"
    }
  }
}
```

By default (when running against locally running services) your url would have a value of `http://localhost:8650/deskpro/ticket`

Or, when run against the staging environment (for instance via `./run-tests.sh staging` or by passing `-Denvironment=staging` to your sbt test command)

Your url would instead have a value of `https://deskpro-ticket-queue.protected.mdtp/deskpro/ticket`

See [a working example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/src/test/scala/uk/gov/hmrc/api/service/IndividualsMatchingService.scala).


### API Logger

An API logger is available. 

Use the logger as follows:

```scala
logger.info("Log a message")
```

See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/src/test/scala/uk/gov/hmrc/api/client/HttpClient.scala).

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
