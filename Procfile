worker: java -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -cp ./target/classes:./target/dependency/* com.creation.App web: java -agentlib:jdwp=transport=dt_socket,server=y,address=9090,suspend=n -jar target/App.jar