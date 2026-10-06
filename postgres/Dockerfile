FROM postgres:alpine

COPY /postgres/scripts/db/1-dump.sql /docker-entrypoint-initdb.d
COPY /postgres/scripts/db/2-data.sql /docker-entrypoint-initdb.d

RUN chmod a+r /docker-entrypoint-initdb.d/*