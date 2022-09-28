FROM node:lts-alpine as build-stage

WORKDIR /app

COPY . .

RUN npm install --save --legacy-peer-deps

RUN npm run build

FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY ./deploy/nginx /etc/nginx/conf.d
COPY ./deploy/certbot/conf /etc/letsencrypt
COPY ./deploy/certbot/www /var/www/certbot

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
