#!/usr/bin/env bash
#abort on errors
set -e
docker-compose -f src/test/resources/docker/datio/docker-compose.yml down
docker rmi datio_hadoop
docker rmi datio_master
docker rmi datio_worker
rm -rf src/test/resources/docker/datio/hadoop/hdfsTestFiles