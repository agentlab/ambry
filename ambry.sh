#!/bin/bash

AMBRY_SERVER_PID=0
AMBRY_FRONTEND_PID=0

# see https://medium.com/@gchudnov/trapping-signals-in-docker-containers-7a57fdda7d86#.bh35ir4u5
term_handler() {
  echo 'Stopping Ambry Server....'
  if [ $AMRBY_SERVER_PID -ne 0 ]; then
    kill -s TERM "$AMBRY_SERVER_PID"
    wait "$AMBRY_SERVER_PID"
  fi
  echo 'Stopping Ambry Frontend....'
  if [ $AMRBY_FRONTEND_PID -ne 0 ]; then
    kill -s TERM "$AMBRY_FRONTEND_PID"
    wait "$AMBRY_FRONTEND_PID"
  fi
  echo 'Ambry stopped.'
  exit
}

echo 'folder contents:'

echo `ls`

# Capture kill requests to stop properly
trap "term_handler" SIGHUP SIGINT SIGTERM

# check if file exists

if [ ! -d /ambry/files ]; then
  echo 'File does not exist, creating new'
  mkdir /ambry/files/0
  touch /ambry/files/0/log_current
fi

# start ambry
cd ambry-server
./eclipse > logs-server.log &
AMBRY_SERVER_PID=$!

cd ../ambry-frontend
./eclipse > logs-frontend.log &
AMBRY_FRONTEND_PID=$!

sleep 10

curl http://localhost:1174/healthCheck

wait
