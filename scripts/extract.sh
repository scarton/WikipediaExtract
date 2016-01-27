#!/bin/bash
###############
# Performs Extraction of Wikipedia Articles, writing the output files to a target directory.
###############
echo "Wikipedia Extract"
source ./classpath.sh
cd ..
mvn -DskipTests clean package

#echo $CLASSPATH

java -Dfile.encoding=UTF-8 -classpath $CLASSPATH cobra.wikipedia_extract.batch.WikiSplit \
/home/Sharing/Wikipedia-Sample/simplewiki-latest-pages-articles.xml \
/home/Sharing/wikipedia-articles