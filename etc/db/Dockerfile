FROM mariadb:10.3

COPY ./etc/db/init/*.sql /docker-entrypoint-initdb.d/

HEALTHCHECK --interval=3s --timeout=10s CMD mysql -u ${MARIADB_USER} -P 3306 -p${MARIADB_PASSWORD} ${MARIADB_DATABASE} -Bse 'SELECT 1'
