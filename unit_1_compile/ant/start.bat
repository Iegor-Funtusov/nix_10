cd .\apache-ant
call .\setantenv.bat

cd ..

call ant remove
call ant compile
call ant jar
call ant run