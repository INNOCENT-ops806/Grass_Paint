#!/bin/bash
#
# This script builds and runs the Maven project: Grass_Paint
#
# Usage: ./run.sh
#

echo "Building and running Maven project: Grass_Paint..."

# Clean (deletes target directory), package (builds the JAR),
# and then executes the main class using exec-maven-plugin.
mvn clean package exec:java

if [ $? -ne 0 ]; then
  echo "Maven build/run failed. Check the output for details."
  exit 1
fi
