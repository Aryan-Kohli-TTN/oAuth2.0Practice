#!/usr/bin/env bash
# Use Java 17 for this project only. Other projects are unaffected.
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
exec mvn "$@"
