CPBASE="/home/steve/.m2/repository"
CLASSPATH="/home/steve/projects/wikipedia-extract/target/classes:\
$CPBASE/org/apache/spark/spark-core_2.10/1.6.0/spark-core_2.10-1.6.0.jar:\
$CPBASE/org/apache/avro/avro-mapred/1.7.7/avro-mapred-1.7.7-hadoop2.jar:\
$CPBASE/org/apache/avro/avro-ipc/1.7.7/avro-ipc-1.7.7.jar:\
$CPBASE/org/apache/avro/avro/1.7.7/avro-1.7.7.jar:\
$CPBASE/org/apache/avro/avro-ipc/1.7.7/avro-ipc-1.7.7-tests.jar:\
$CPBASE/org/codehaus/jackson/jackson-core-asl/1.9.13/jackson-core-asl-1.9.13.jar:\
$CPBASE/org/codehaus/jackson/jackson-mapper-asl/1.9.13/jackson-mapper-asl-1.9.13.jar:\
$CPBASE/com/twitter/chill_2.10/0.5.0/chill_2.10-0.5.0.jar:\
$CPBASE/com/esotericsoftware/kryo/kryo/2.21/kryo-2.21.jar:\
$CPBASE/com/esotericsoftware/reflectasm/reflectasm/1.07/reflectasm-1.07-shaded.jar:\
$CPBASE/com/esotericsoftware/minlog/minlog/1.2/minlog-1.2.jar:\
$CPBASE/org/objenesis/objenesis/1.2/objenesis-1.2.jar:\
$CPBASE/com/twitter/chill-java/0.5.0/chill-java-0.5.0.jar:\
$CPBASE/org/apache/xbean/xbean-asm5-shaded/4.4/xbean-asm5-shaded-4.4.jar:\
$CPBASE/org/apache/hadoop/hadoop-client/2.2.0/hadoop-client-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-common/2.2.0/hadoop-common-2.2.0.jar:\
$CPBASE/org/apache/commons/commons-math/2.1/commons-math-2.1.jar:\
$CPBASE/commons-configuration/commons-configuration/1.6/commons-configuration-1.6.jar:\
$CPBASE/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar:\
$CPBASE/commons-digester/commons-digester/1.8/commons-digester-1.8.jar:\
$CPBASE/commons-beanutils/commons-beanutils/1.7.0/commons-beanutils-1.7.0.jar:\
$CPBASE/commons-beanutils/commons-beanutils-core/1.8.0/commons-beanutils-core-1.8.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-auth/2.2.0/hadoop-auth-2.2.0.jar:\
$CPBASE/org/apache/commons/commons-compress/1.4.1/commons-compress-1.4.1.jar:\
$CPBASE/org/tukaani/xz/1.0/xz-1.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-hdfs/2.2.0/hadoop-hdfs-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-mapreduce-client-app/2.2.0/hadoop-mapreduce-client-app-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-mapreduce-client-common/2.2.0/hadoop-mapreduce-client-common-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-yarn-client/2.2.0/hadoop-yarn-client-2.2.0.jar:\
$CPBASE/com/google/inject/guice/3.0/guice-3.0.jar:\
$CPBASE/javax/inject/javax.inject/1/javax.inject-1.jar:\
$CPBASE/aopalliance/aopalliance/1.0/aopalliance-1.0.jar:\
$CPBASE/com/sun/jersey/jersey-test-framework/jersey-test-framework-grizzly2/1.9/jersey-test-framework-grizzly2-1.9.jar:\
$CPBASE/com/sun/jersey/jersey-test-framework/jersey-test-framework-core/1.9/jersey-test-framework-core-1.9.jar:\
$CPBASE/javax/servlet/javax.servlet-api/3.0.1/javax.servlet-api-3.0.1.jar:\
$CPBASE/com/sun/jersey/jersey-client/1.9/jersey-client-1.9.jar:\
$CPBASE/com/sun/jersey/jersey-grizzly2/1.9/jersey-grizzly2-1.9.jar:\
$CPBASE/org/glassfish/grizzly/grizzly-http/2.1.2/grizzly-http-2.1.2.jar:\
$CPBASE/org/glassfish/grizzly/grizzly-framework/2.1.2/grizzly-framework-2.1.2.jar:\
$CPBASE/org/glassfish/gmbal/gmbal-api-only/3.0.0-b023/gmbal-api-only-3.0.0-b023.jar:\
$CPBASE/org/glassfish/external/management-api/3.0.0-b012/management-api-3.0.0-b012.jar:\
$CPBASE/org/glassfish/grizzly/grizzly-http-server/2.1.2/grizzly-http-server-2.1.2.jar:\
$CPBASE/org/glassfish/grizzly/grizzly-rcm/2.1.2/grizzly-rcm-2.1.2.jar:\
$CPBASE/org/glassfish/grizzly/grizzly-http-servlet/2.1.2/grizzly-http-servlet-2.1.2.jar:\
$CPBASE/org/glassfish/javax.servlet/3.1/javax.servlet-3.1.jar:\
$CPBASE/com/sun/jersey/jersey-json/1.9/jersey-json-1.9.jar:\
$CPBASE/org/codehaus/jettison/jettison/1.1/jettison-1.1.jar:\
$CPBASE/stax/stax-api/1.0.1/stax-api-1.0.1.jar:\
$CPBASE/org/codehaus/jackson/jackson-jaxrs/1.8.3/jackson-jaxrs-1.8.3.jar:\
$CPBASE/org/codehaus/jackson/jackson-xc/1.8.3/jackson-xc-1.8.3.jar:\
$CPBASE/com/sun/jersey/contribs/jersey-guice/1.9/jersey-guice-1.9.jar:\
$CPBASE/org/apache/hadoop/hadoop-yarn-server-common/2.2.0/hadoop-yarn-server-common-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-mapreduce-client-shuffle/2.2.0/hadoop-mapreduce-client-shuffle-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-yarn-api/2.2.0/hadoop-yarn-api-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-mapreduce-client-core/2.2.0/hadoop-mapreduce-client-core-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-yarn-common/2.2.0/hadoop-yarn-common-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-mapreduce-client-jobclient/2.2.0/hadoop-mapreduce-client-jobclient-2.2.0.jar:\
$CPBASE/org/apache/hadoop/hadoop-annotations/2.2.0/hadoop-annotations-2.2.0.jar:\
$CPBASE/org/apache/spark/spark-launcher_2.10/1.6.0/spark-launcher_2.10-1.6.0.jar:\
$CPBASE/org/apache/spark/spark-network-common_2.10/1.6.0/spark-network-common_2.10-1.6.0.jar:\
$CPBASE/org/apache/spark/spark-network-shuffle_2.10/1.6.0/spark-network-shuffle_2.10-1.6.0.jar:\
$CPBASE/org/fusesource/leveldbjni/leveldbjni-all/1.8/leveldbjni-all-1.8.jar:\
$CPBASE/com/fasterxml/jackson/core/jackson-annotations/2.3.0/jackson-annotations-2.3.0.jar:\
$CPBASE/org/apache/spark/spark-unsafe_2.10/1.6.0/spark-unsafe_2.10-1.6.0.jar:\
$CPBASE/net/java/dev/jets3t/jets3t/0.7.1/jets3t-0.7.1.jar:\
$CPBASE/org/apache/curator/curator-recipes/2.4.0/curator-recipes-2.4.0.jar:\
$CPBASE/org/apache/curator/curator-framework/2.5.0/curator-framework-2.5.0.jar:\
$CPBASE/org/apache/curator/curator-client/2.5.0/curator-client-2.5.0.jar:\
$CPBASE/org/apache/zookeeper/zookeeper/3.4.6/zookeeper-3.4.6.jar:\
$CPBASE/jline/jline/0.9.94/jline-0.9.94.jar:\
$CPBASE/com/google/guava/guava/17.0/guava-17.0.jar:\
$CPBASE/org/eclipse/jetty/orbit/javax.servlet/3.0.0.v201112011016/javax.servlet-3.0.0.v201112011016.jar:\
$CPBASE/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar:\
$CPBASE/org/apache/commons/commons-math3/3.4.1/commons-math3-3.4.1.jar:\
$CPBASE/com/google/code/findbugs/jsr305/1.3.9/jsr305-1.3.9.jar:\
$CPBASE/org/slf4j/slf4j-api/1.7.12/slf4j-api-1.7.12.jar:\
$CPBASE/org/slf4j/jul-to-slf4j/1.7.10/jul-to-slf4j-1.7.10.jar:\
$CPBASE/org/slf4j/slf4j-log4j12/1.7.12/slf4j-log4j12-1.7.12.jar:\
$CPBASE/com/ning/compress-lzf/1.0.3/compress-lzf-1.0.3.jar:\
$CPBASE/org/xerial/snappy/snappy-java/1.1.2/snappy-java-1.1.2.jar:\
$CPBASE/net/jpountz/lz4/lz4/1.3.0/lz4-1.3.0.jar:\
$CPBASE/org/roaringbitmap/RoaringBitmap/0.5.11/RoaringBitmap-0.5.11.jar:\
$CPBASE/commons-net/commons-net/2.2/commons-net-2.2.jar:\
$CPBASE/com/typesafe/akka/akka-remote_2.10/2.3.11/akka-remote_2.10-2.3.11.jar:\
$CPBASE/com/typesafe/akka/akka-actor_2.10/2.3.11/akka-actor_2.10-2.3.11.jar:\
$CPBASE/com/typesafe/config/1.2.1/config-1.2.1.jar:\
$CPBASE/io/netty/netty/3.8.0.Final/netty-3.8.0.Final.jar:\
$CPBASE/com/google/protobuf/protobuf-java/2.5.0/protobuf-java-2.5.0.jar:\
$CPBASE/org/uncommons/maths/uncommons-maths/1.2.2a/uncommons-maths-1.2.2a.jar:\
$CPBASE/com/typesafe/akka/akka-slf4j_2.10/2.3.11/akka-slf4j_2.10-2.3.11.jar:\
$CPBASE/org/scala-lang/scala-library/2.10.5/scala-library-2.10.5.jar:\
$CPBASE/org/json4s/json4s-jackson_2.10/3.2.10/json4s-jackson_2.10-3.2.10.jar:\
$CPBASE/org/json4s/json4s-core_2.10/3.2.10/json4s-core_2.10-3.2.10.jar:\
$CPBASE/org/json4s/json4s-ast_2.10/3.2.10/json4s-ast_2.10-3.2.10.jar:\
$CPBASE/org/scala-lang/scalap/2.10.0/scalap-2.10.0.jar:\
$CPBASE/org/scala-lang/scala-compiler/2.10.0/scala-compiler-2.10.0.jar:\
$CPBASE/com/sun/jersey/jersey-server/1.9/jersey-server-1.9.jar:\
$CPBASE/asm/asm/3.1/asm-3.1.jar:\
$CPBASE/com/sun/jersey/jersey-core/1.9/jersey-core-1.9.jar:\
$CPBASE/org/apache/mesos/mesos/0.21.1/mesos-0.21.1-shaded-protobuf.jar:\
$CPBASE/io/netty/netty-all/4.0.29.Final/netty-all-4.0.29.Final.jar:\
$CPBASE/com/clearspring/analytics/stream/2.7.0/stream-2.7.0.jar:\
$CPBASE/io/dropwizard/metrics/metrics-core/3.1.2/metrics-core-3.1.2.jar:\
$CPBASE/io/dropwizard/metrics/metrics-jvm/3.1.2/metrics-jvm-3.1.2.jar:\
$CPBASE/io/dropwizard/metrics/metrics-json/3.1.2/metrics-json-3.1.2.jar:\
$CPBASE/io/dropwizard/metrics/metrics-graphite/3.1.2/metrics-graphite-3.1.2.jar:\
$CPBASE/com/fasterxml/jackson/core/jackson-databind/2.3.0/jackson-databind-2.3.0.jar:\
$CPBASE/com/fasterxml/jackson/core/jackson-core/2.3.0/jackson-core-2.3.0.jar:\
$CPBASE/com/fasterxml/jackson/module/jackson-module-scala_2.10/2.4.4/jackson-module-scala_2.10-2.4.4.jar:\
$CPBASE/org/scala-lang/scala-reflect/2.10.4/scala-reflect-2.10.4.jar:\
$CPBASE/com/thoughtworks/paranamer/paranamer/2.6/paranamer-2.6.jar:\
$CPBASE/org/apache/ivy/ivy/2.4.0/ivy-2.4.0.jar:\
$CPBASE/oro/oro/2.0.8/oro-2.0.8.jar:\
$CPBASE/org/tachyonproject/tachyon-client/0.8.2/tachyon-client-0.8.2.jar:\
$CPBASE/org/tachyonproject/tachyon-underfs-hdfs/0.8.2/tachyon-underfs-hdfs-0.8.2.jar:\
$CPBASE/org/tachyonproject/tachyon-underfs-s3/0.8.2/tachyon-underfs-s3-0.8.2.jar:\
$CPBASE/org/tachyonproject/tachyon-underfs-local/0.8.2/tachyon-underfs-local-0.8.2.jar:\
$CPBASE/net/razorvine/pyrolite/4.9/pyrolite-4.9.jar:\
$CPBASE/net/sf/py4j/py4j/0.9/py4j-0.9.jar:\
$CPBASE/org/spark-project/spark/unused/1.0.0/unused-1.0.0.jar:\
$CPBASE/org/apache/spark/spark-sql_2.10/1.6.0/spark-sql_2.10-1.6.0.jar:\
$CPBASE/org/apache/spark/spark-catalyst_2.10/1.6.0/spark-catalyst_2.10-1.6.0.jar:\
$CPBASE/org/codehaus/janino/janino/2.7.8/janino-2.7.8.jar:\
$CPBASE/org/codehaus/janino/commons-compiler/2.7.8/commons-compiler-2.7.8.jar:\
$CPBASE/org/apache/parquet/parquet-column/1.7.0/parquet-column-1.7.0.jar:\
$CPBASE/org/apache/parquet/parquet-common/1.7.0/parquet-common-1.7.0.jar:\
$CPBASE/org/apache/parquet/parquet-encoding/1.7.0/parquet-encoding-1.7.0.jar:\
$CPBASE/org/apache/parquet/parquet-generator/1.7.0/parquet-generator-1.7.0.jar:\
$CPBASE/org/apache/parquet/parquet-hadoop/1.7.0/parquet-hadoop-1.7.0.jar:\
$CPBASE/org/apache/parquet/parquet-format/2.3.0-incubating/parquet-format-2.3.0-incubating.jar:\
$CPBASE/org/apache/parquet/parquet-jackson/1.7.0/parquet-jackson-1.7.0.jar:\
$CPBASE/org/apache/spark/spark-mllib_2.10/1.6.0/spark-mllib_2.10-1.6.0.jar:\
$CPBASE/org/apache/spark/spark-streaming_2.10/1.6.0/spark-streaming_2.10-1.6.0.jar:\
$CPBASE/org/apache/spark/spark-graphx_2.10/1.6.0/spark-graphx_2.10-1.6.0.jar:\
$CPBASE/com/github/fommil/netlib/core/1.1.2/core-1.1.2.jar:\
$CPBASE/net/sourceforge/f2j/arpack_combined_all/0.1/arpack_combined_all-0.1.jar:\
$CPBASE/org/scalanlp/breeze_2.10/0.11.2/breeze_2.10-0.11.2.jar:\
$CPBASE/org/scalanlp/breeze-macros_2.10/0.11.2/breeze-macros_2.10-0.11.2.jar:\
$CPBASE/org/scalamacros/quasiquotes_2.10/2.0.0-M8/quasiquotes_2.10-2.0.0-M8.jar:\
$CPBASE/net/sf/opencsv/opencsv/2.3/opencsv-2.3.jar:\
$CPBASE/com/github/rwl/jtransforms/2.4.0/jtransforms-2.4.0.jar:\
$CPBASE/org/spire-math/spire_2.10/0.7.4/spire_2.10-0.7.4.jar:\
$CPBASE/org/spire-math/spire-macros_2.10/0.7.4/spire-macros_2.10-0.7.4.jar:\
$CPBASE/org/jpmml/pmml-model/1.1.15/pmml-model-1.1.15.jar:\
$CPBASE/org/jpmml/pmml-agent/1.1.15/pmml-agent-1.1.15.jar:\
$CPBASE/org/jpmml/pmml-schema/1.1.15/pmml-schema-1.1.15.jar:\
$CPBASE/com/sun/xml/bind/jaxb-impl/2.2.7/jaxb-impl-2.2.7.jar:\
$CPBASE/com/sun/xml/bind/jaxb-core/2.2.7/jaxb-core-2.2.7.jar:\
$CPBASE/javax/xml/bind/jaxb-api/2.2.7/jaxb-api-2.2.7.jar:\
$CPBASE/org/eclipse/mylyn/wikitext/wikitext/0.9.4.I20090220-1600-e3x/wikitext-0.9.4.I20090220-1600-e3x.jar:\
$CPBASE/org/eclipse/mylyn/wikitext/wikitext.mediawiki/0.9.4.I20090220-1600-e3x/wikitext.mediawiki-0.9.4.I20090220-1600-e3x.jar:\
$CPBASE/org/apache/opennlp/opennlp-tools/1.6.0/opennlp-tools-1.6.0.jar:\
$CPBASE/org/apache/opennlp/opennlp-uima/1.6.0/opennlp-uima-1.6.0.jar:\
$CPBASE/net/sf/trove4j/trove4j/2.1.0/trove4j-2.1.0.jar:\
$CPBASE/log4j/log4j/1.2.16/log4j-1.2.16.jar:\
$CPBASE/org/apache/hadoop/hadoop-core/0.20.2/hadoop-core-0.20.2.jar:\
$CPBASE/commons-cli/commons-cli/1.2/commons-cli-1.2.jar:\
$CPBASE/xmlenc/xmlenc/0.52/xmlenc-0.52.jar:\
$CPBASE/commons-httpclient/commons-httpclient/3.0.1/commons-httpclient-3.0.1.jar:\
$CPBASE/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:\
$CPBASE/commons-codec/commons-codec/1.10/commons-codec-1.10.jar:\
$CPBASE/org/mortbay/jetty/jetty/6.1.14/jetty-6.1.14.jar:\
$CPBASE/org/mortbay/jetty/jetty-util/6.1.14/jetty-util-6.1.14.jar:\
$CPBASE/tomcat/jasper-runtime/5.5.12/jasper-runtime-5.5.12.jar:\
$CPBASE/tomcat/jasper-compiler/5.5.12/jasper-compiler-5.5.12.jar:\
$CPBASE/org/mortbay/jetty/jsp-api-2.1/6.1.14/jsp-api-2.1-6.1.14.jar:\
$CPBASE/org/mortbay/jetty/jsp-2.1/6.1.14/jsp-2.1-6.1.14.jar:\
$CPBASE/ant/ant/1.6.5/ant-1.6.5.jar:\
$CPBASE/commons-el/commons-el/1.0/commons-el-1.0.jar:\
$CPBASE/org/mortbay/jetty/servlet-api-2.5/6.1.14/servlet-api-2.5-6.1.14.jar:\
$CPBASE/net/sf/kosmosfs/kfs/0.3/kfs-0.3.jar:\
$CPBASE/hsqldb/hsqldb/1.8.0.10/hsqldb-1.8.0.10.jar:\
$CPBASE/org/eclipse/jdt/core/3.1.1/core-3.1.1.jar:\
$CPBASE/commons-io/commons-io/2.4/commons-io-2.4.jar:\
$CPBASE/com/sleepycat/je/4.0.92/je-4.0.92.jar:\
$CPBASE/wikipediaminer/api/1.0/api-1.0.jar:\
$CPBASE/org/slf4j/jcl-over-slf4j/1.7.12/jcl-over-slf4j-1.7.12.jar:\
$CPBASE/commons-lang/commons-lang/2.6/commons-lang-2.6.jar"