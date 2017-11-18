REM Repertoire courant
SET REP_COURANT=D:\Documents and Settings\GZOULI\workspace\Pacman

REM Definition Repertoire Librairie.
SET LIB=%REP_COURANT%\lib\commons-logging-1.1.jar;%REP_COURANT%\lib\log4j-1.2.8.jar;%REP_COURANT%\lib\commons-io-1.1.jar;



REM Tous les jars de l'appli sont stockes dans le classpath.
SET CLASSPATH=%LIB%;%REP_COURANT%\bin


REM Execution du programme.
java -cp %CLASSPATH% -Dcom.sun.management.jmxremote=true com.pacman.main.Starter

