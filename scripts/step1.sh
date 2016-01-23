#!/bin/bash
###############
# Performs Extraction of Wikipedia Articles, writing the output files to a target directory.
###############
echo "Wikipedia Extract"
source ./classpath.sh
cd ..
mvn -DskipTests clean package

#echo "$(cygpath -pw "$CLASSPATH")"

java -Dfile.encoding=UTF-8 -classpath "$(cygpath -pw "$CLASSPATH")" cobra.wikipedia_extract.batch.WikiSplit \
   H:/Vocabularies/WikipediaMiner/Wikipedia-20150805/enwiki-20150805-pages-articles.xml \
   H:/data/wikipedia/extracted-articles