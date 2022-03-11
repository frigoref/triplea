@echo off
title Initialize Docker Database

set bold=[1m
set bold_green=%bold%[32m
set normal=[0m

echo %bold_green%Initialize docker database%normal%

@rem export PGPASSWORD=postgres
@rem extract psql
set PGPASSWORD=postgres
psql -h localhost -U postgres lobby_db < "%cd%/init/01-create-users.sql"
psql -h localhost -U postgres lobby_db < "%cd%/init/02-create-databases.sql"
