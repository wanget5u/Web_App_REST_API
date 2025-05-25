@echo off
echo Uruchamianie aplikacji Web_App_REST_API...
cd %~dp0
IF NOT EXIST target\TPO6_GM_S31230-0.0.1-SNAPSHOT.jar (
    echo Plik JAR nie istnieje! Najpierw uruchom build_app.bat
    pause
    exit /b
)
java -jar target\TPO6_GM_S31230-0.0.1-SNAPSHOT.jar
pause