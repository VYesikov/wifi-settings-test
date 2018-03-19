#!/usr/bin/env bash

platform="android"
deviceName="Nexus 6 API 27"

echo "********export environment variable platform=${platform}********"
export platform=$platform

echo "********export environment variable deviceName=${deviceName}********"
export deviceName=$deviceName

echo "********run Functional tests by junitPlatformTest task********"
gradle clean junitPlatformTest --no-daemon
