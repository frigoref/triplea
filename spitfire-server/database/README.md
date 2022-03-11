# Database

Hosts database migrations and docker to run a database locally.

Stores:

- users
- lobby chat history
- user ban information
- moderator audit logs
- bug report history and rate limits
- uploaded map information

For more information see: [database documentation](/docs/development/database/)

## Install and set up docker database locally

- install [docker](https://www.docker.com/products/docker-desktop)
- install [PostgreSQL](https://www.postgresql.org/download/)
  - Linux: install package postgresql-XX
  - Windows: install with installer
  - Windows alternative:
    - download [binaries](https://www.enterprisedb.com/products-services-training/pgbindownload)
    - extract `bin` folder from zip file (at least psql.exe and all *.dll files)
- start docker database
  - Linux: run `./spitfire-server/database/start_docker_db`
  - Windows: run `./spitfire-server/database/start_docker_db.bat`
- initialize docker database
  - Linux: run `./spitfire-server/database/init_docker_db`
  - Windows: run `./spitfire-server/database/init_docker_db.bat`

## Working with database locally

- start docker database
  - Linux: run `./spitfire-server/database/start_docker_db`
  - Windows: run `./spitfire-server/database/start_docker_db.bat`
- reset and load example data into the docker database
  - Linux: run `./spitfire-server/database/reset_docker_db`
  - Windows: run `./spitfire-server/database/reset_docker_db.bat`
- connect to docker database
  - Linux: run `./spitfire-server/database/connect_to_docker_db`
  - Windows: run `./spitfire-server/database/connect_to_docker_db.bat`

## Example data

The example data inserted into a local docker will create an admin user
named "test" with password "test".
