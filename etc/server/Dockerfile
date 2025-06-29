FROM nginx:1.22

RUN apt-get update
RUN apt-get -y install --no-install-recommends nginx-extras

RUN unlink /etc/nginx/sites-enabled/default && rm /etc/nginx/conf.d/default.conf

COPY ./etc/server/config/ /etc/nginx/conf.d

RUN rm -rf /var/lib/apt/lists/* && apt-get clean

HEALTHCHECK --interval=3s --timeout=10s CMD curl http://localhost/healthz
