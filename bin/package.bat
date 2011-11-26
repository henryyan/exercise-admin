@echo off
echo [INFO] Use maven jetty-plugin run the project.

cd %~dp0
cd ..
call mvn package -Dmaven.test.skip=true
cd bin
pause