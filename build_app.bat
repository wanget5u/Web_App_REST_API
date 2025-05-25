@echo off
echo Kompilacja aplikacji Web_App_REST_API...
cd %~dp0
call mvnw clean package
echo Kompilacja zakonczona.
exit