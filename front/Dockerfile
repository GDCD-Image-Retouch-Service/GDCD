FROM node:lts-alpine as build-stage

WORKDIR /app

COPY . .

RUN npm install --save --legacy-peer-deps

RUN npm run build

FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY --from=build-stage /app/deploy/nginx /etc/nginx/conf.d

EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]
