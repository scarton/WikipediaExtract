#!/bin/bash
###############
# Performs Extraction of Wikipedia topics.
###############
echo "Wikipedia Topic Extract"
source ./classpath.sh
cd ..
mvn -DskipTests clean package

#echo $CLASSPATH

java -Dfile.encoding=UTF-8 -classpath $CLASSPATH cobra.wikipedia_extract.batch.WikiTopics \
/home/Sharing/Wikipedia-Sample/simplewiki-latest-pages-articles.xml 