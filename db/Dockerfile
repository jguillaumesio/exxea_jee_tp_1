FROM mysql/mysql-server:8.0

# Copy the custom my.cnf file and set permissions
COPY ./db/my.cnf /etc/my.cnf
RUN chmod 600 /etc/my.cnf