@echo off
title Load Sample Data
@rem Simple helper script to connect to a DB running locally on docker
set PGPASSWORD=postgres
psql -h localhost -U postgres

@rem after connecting, use "\l" to print the list of database
@rem Use "\c <database_name>" to connect to a database
@rem After connecting to a database, use "\d" to list the tables in the current database
