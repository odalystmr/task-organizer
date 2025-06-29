FROM node:20 AS base

WORKDIR /ui

COPY ui /ui

HEALTHCHECK --interval=3s --timeout=10s CMD curl http://localhost:4200

FROM base AS dev

RUN npm install --dev

ENTRYPOINT ["npm", "run", "watch"]

FROM base AS prod

RUN npm install
RUN npm run build

ENTRYPOINT ["npm", "start"]
