FROM node:22-alpine AS build

WORKDIR /app

COPY package.json /app/package.json
RUN npm install
COPY . /app

RUN npm run build
# 2. For Nginx setup
FROM nginx:alpine
COPY --from=build /app/.nginx/nginx.conf /etc/nginx/conf.d/nginx.conf
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
COPY --from=build /app/build .
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]