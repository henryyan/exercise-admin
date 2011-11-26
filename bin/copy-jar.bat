cd ../
call mvn dependency:copy-dependencies -DoutputDirectory=target/lib  -DincludeScope=runtime
:end
pause