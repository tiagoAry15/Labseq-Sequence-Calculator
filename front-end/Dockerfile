FROM node:22 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

FROM nginx:alpine
# apaga tudo que veio no default
RUN rm -rf /usr/share/nginx/html/*

# copia apenas o conteúdo de browser pro root do nginx
COPY --from=build /app/dist/multivision-front-end/browser/ /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
