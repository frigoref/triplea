@echo off
title Load Sample Data

set bold=[1m
set bold_green=%bold%[32m
set normal=[0m

echo %bold_green%Inserting sample data%normal%

@rem export PGPASSWORD=postgres
@rem extract psql
set PGPASSWORD=postgres
psql -h localhost -U postgres lobby_db < "%cd%/sql/sample_data/lobby_db_sample_data.sql"