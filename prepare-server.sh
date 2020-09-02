#!/bin/bash

####################################################
# DO NOT CHANGE THE GRADLE OPTIONS IN THE BLOCK    #
# BELOW, IT WILL HAVE IMPACT ON THE PERFORMANCE    #
# OF YOUR APPLICATION                              #
####################################################
GRADLE_OPTS="-Dgradle.user.home=~/gradle_cache"    #
####################################################

#crio-solution
#start
cp ./initial_data_load.json ./buildout/src/test/resources/initial_data_load.json
#end

./gradlew clean bootrun &

while ! netstat -tna | grep 'LISTEN\>' | grep -q ':8081\>'; do
  echo "waiting for spring application to start"
  sleep 2 # time in seconds, tune it as needed
done

# If you have any script to load the data make sure that its part of this bash script.
#mongoexport -d initial_data_load -c form

#crio-solution
#start
./gradlew loaddata
#end