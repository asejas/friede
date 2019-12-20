name := "friede"
organization := "org.burrosoft"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.0"

javaOptions in Test += "-Dconfig.file=conf/application.test.conf"

libraryDependencies += guice
libraryDependencies += javaJdbc
libraryDependencies += javaJpa
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.4.0.Final"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.18"
libraryDependencies += "com.h2database" % "h2" % "1.4.200" % Test
