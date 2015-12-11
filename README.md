# tsp
before you run this spring boot app you need to import websense ssl certificate for jive sand box:
keytool -import -alias jive_sand_box -keypass changeit -keystore "PATH_TO_YOUR_JAVA_HOME\jre\lib\security\cacerts" -file /PATH_TO_THE_CERTIFICATE_CHAIN/cert.pem
