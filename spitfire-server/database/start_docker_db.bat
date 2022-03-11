@echo off
title Start Docker DB
@rem Start Docker DB and check access via psql psql (download https://www.enterprisedb.com/products-services-training/pgbindownload)

@rem set -eu

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

set bold=[1m
set bold_green=%bold%[32m
set normal=[0m

:main
  echo %bold_green%STARTING DATABASE%normal%

  CALL :runDocker
  CALL :waitForDatabaseToStart

  echo %bold_green%Deploying schemas - running flyway%normal%
@rem  pwd
  CALL "%cd%\..\..\gradlew.bat" flywayMigrateAll

  CALL "%cd%\load_sample_data"
  echo %bold_green%DONE%normal%
EXIT /B 0

:runDocker
  echo "Stopping database.."
  docker stop database || echo "[OK] Database not running.."
	
docker run --rm -d ^
--name="database" ^
-e "POSTGRES_PASSWORD=postgres" ^
-p "5432:5432" ^
-v "%cd%\sql\init:\docker-entrypoint-initdb.d" ^
"postgres:10"

  docker run --rm -d ^
    --name="database" ^
    -e "POSTGRES_PASSWORD=postgres" ^
    -p "5432:5432" ^
    -v "%cd%\sql\init\:\docker-entrypoint-initdb.d\" ^
    "postgres:10"
EXIT /B 0

:waitForDatabaseToStart
  echo "Waiting for Database start.."
  set tryAttempt=0
  set PGPASSWORD=postgres
@rem export PGPASSWORD=postgres
@rem download at least bin folder with psql.exe and all *.dll files 
@rem and update environment variable path with path to psql.exe
@rem  echo SELECT 1 ^
@rem   | psql -h localhost -U postgres
@rem   :whileStart
@rem  while ! (
@rem    echo SELECT 1 ^
@rem       | psql -h localhost -U postgres 2> /dev/null ^
@rem       | grep -q '1 row'); do
	
	echo SELECT 1 | psql -h localhost -U postgres | findstr /c:"1 row">nul 2>&1 || goto :whileInner
    goto :whileEnd
	:whileInner

    timeout 1
@rem    echo -n .

    set /A tryAttempt+=1
@rem    # timeout after 10s
    if %tryAttempt% GTR 10 (
      echo "Aborting DB startup (timed out)"
      goto :whileEnd
    )
	goto :whileStart
@rem  done
  :whileEnd
  echo ""
EXIT /B 0

CALL :main
