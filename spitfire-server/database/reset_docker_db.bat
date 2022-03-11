@echo off
title Reset Docker DB (incl. sample data)
@rem set -eu
@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

set PSQL=psql -h localhost -U postgres

set PGPASSWORD=postgres

@rem Check if we can connect to database
echo SELECT 1 | %PSQL% | findstr /c:"1 row">nul 2>&1 || (
 echo "ERROR: docker not running, start the database first"
 exit /B 1
)

echo "Force killing open connections to database"
echo "select pg_terminate_backend(pid) from pg_stat_activity where datname='lobby_db';" | %PSQL%
echo "drop database lobby_db" | %PSQL%

%PSQL% < "%cd%/sql/init/02-create-databases.sql"

echo "Deploying schema"
"%cd%/../../gradlew" flywayMigrateAll

"%cd%/load_sample_data"
