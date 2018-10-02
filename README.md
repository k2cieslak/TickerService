# TickerService

[![MIT licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/mit-license.php)
[![Build Status](https://travis-ci.org/k2cieslak/TickerService.svg?branch=master)](https://travis-ci.org/k2cieslak/TickerService)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=k2cieslak_TickerService&metric=alert_status)
![Coverage](https://sonarcloud.io/api/project_badges/measure?project=k2cieslak_TickerService&metric=coverage)

REST service based on [XChange](https://github.com/knowm/XChange) project.
This service is  providing access to current ticker of almost all exchanges supported by XChange library.

## Usage

There are three ways to run TickerService: build or run it from sources, download from releases section 
and run Spring Boot Fat JAR and download and run Docker image. By default server is listening on the HTTP port 8081,
so you need to use http://localhost:8081 to get main page. You will find further instructions there.

#### Run from sources

This is standard Maven project so it is enough to run *package* target to get Fat JAR that can be run from CLI
with command *java -jar TickerService-VERSION.jar* (assuming that java executable and jar librarry are on path).
Alternatively you can directly run class *io.github.k2cieslak.cryptoticker.tickerservice.TickerService*.

#### Use project release 

[Here](https://github.com/k2cieslak/TickerService/releases) you will find TickerService releases. You just download Fat JAR
and run it like in section above.

#### Use docker image

If you have docker you can just pull the image from Docker Hub with *docker pull k2cieslak/tickerservice* command.
Public repository for the image can be found [here](https://hub.docker.com/r/k2cieslak/tickerservice/).

### Bugs and features 

Please report any bugs or feature requests via [Github issue tracker](https://github.com/k2cieslak/TickerService/issues).

### Contributing

Feel free to submit pull request for any bug fix or feature. I should be able to review it within days.