#!/usr/bin/env bash
#abort on errors
set -xe

if [ ! -d src/test/resources/docker/datio/spark/classpath ]; then
    mkdir -p src/test/resources/docker/datio/spark/classpath
fi

if [ -f src/test/resources/docker/datio/spark/classpath/* ]; then
    rm src/test/resources/docker/datio/spark/classpath/*
fi

find ../jobtest/target/*-jar-with-dependencies.jar -exec cp {} src/test/resources/docker/datio/spark/classpath/ \;

cp -rf src/test/resources/hdfsTestFiles src/test/resources/docker/datio/hadoop/
docker-compose -f src/test/resources/docker/datio/docker-compose.yml up -d

while ! $(docker-compose -f src/test/resources/docker/datio/docker-compose.yml logs hadoop | grep -lq COPIED)
do
    sleep 3
done