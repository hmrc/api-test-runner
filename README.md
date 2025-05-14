
# api-test-runner

A helper library for API testing at HMRC.

## Usage

### Declare dependency

Declare the library as a project dependency as follows:

```scala
"uk.gov.hmrc" %% "api-test-runner" % "x.x.x" % Test
```

See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/project/Dependencies.scala).

### Configuration

- System property `environment` must be set in order to execute tests. Arguments `local`, `dev`, `qa` and `staging` are typically available, but will depend on your project configuration. See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/src/test/resources/application.conf).
- System property `security.assessment` is available to enable or disable the security assessment. Arguments `true` and `false` are available, the default is `false`.

Set system properties when executing tests as follows:

```sbt
sbt clean -Denvironment="<environment>" -Dsecurity.assessment="<security.asessment>" test
```

See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/run-tests.sh).

### Logging configuration

Logging configuration is available via logback. You can add a configuration file to your project as follows:

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%date{ISO8601} level=[%level] logger=[%logger] method=[%M] thread=[%thread] - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="DEBUG">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

in `src/test/resources/logback.xml`.

### Test environment configuration

Test environment configuration is available. A configuration file is required to use it. See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/src/test/resources/application.conf).

Create a url from a configuration file as follows:

```scala
val url: String = TestEnvironment.url("service") + "/path"
```

See an [example](https://github.com/hmrc/platform-example-api-scalatest-tests/blob/main/src/test/scala/uk/gov/hmrc/api/service/IndividualsMatchingService.scala).

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
