@echo off
echo Czyszczenie projektu...
cd /d %~dp0
mvnw clean
echo Gotowe.
exit
