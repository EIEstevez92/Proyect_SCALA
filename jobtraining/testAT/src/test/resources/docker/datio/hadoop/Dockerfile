FROM sequenceiq/hadoop-docker:2.7.1

ADD hdfsTestFiles/ /filesToUpHDFS
ADD bootstrap.sh /etc/bootstrap.sh

CMD ["sh", "/etc/bootstrap.sh", "-d"]
