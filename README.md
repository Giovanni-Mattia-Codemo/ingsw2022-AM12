Software Engineering Project 2021-2022

Valerio Capo valerio.capo@mail.polimi.it\
Giovanni Mattia Codemo giovannimattia.codemo@mail.polimi.it\
Cristiana Calvaresi cristiana.calvaresi@mail.polimi.it

| Functionality    |                       State                        |
|:-----------------|:--------------------------------------------------:|
| Basic rules      | 🟢 |
| Complete rules   | 🟢 |
| Socket           | 🟢 |
| GUI              | 🟢 |
| CLI              | 🟢 |
| Four players     | 🟢 |
| Persistence      | 🟢 |
| All Characters   | 🟢 |

🔴
🟢
🟡

Model Coverage: Class-100% Methods-92% Lines-89%

To launch the server jar file from command line enter: java -jar AM12-1.0-SNAPSHOT-Server.jar -p portNumber [-f targetDirectoryToSave]

To launch the client jar file from command line enter: java -jar AM12-1.0-SNAPSHOT-Client.jar -i ipAddress -p portNumber

replace "java" with the pathname of the JDK17 java.exe on your machine e.g.: 
"C:\Users\Giovanni M. Codemo\.jdks\openjdk-17\bin\java.exe" -jar "C:\Users\Giovanni M. Codemo\Desktop\Uni terzo anno\Progetto IdS\Progetto\target\AM12-1.0-SNAPSHOT-Server.jar"  -p 9000 -f C:\Users\Giovanni M. Codemo\Desktop\SaveGame

For persistence, the game is saved after every successful action and loaded if the server detects a savedGame.txt at the address specified on launch (or in the same
location as the jar if left unspecified), and the right players join with the same game settings. If a player disconnects during the match the saved game is instead
freed
When launching the client the first option is to select either GUI or CLI, and the client will proceed working accordingly. While using the CLI version it is possible to
enter the command "Help" to get a message with the accepted formats of input
