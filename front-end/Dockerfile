# Imagem base Node.js LTS
FROM node:22

# Diretório de trabalho no container
WORKDIR /app

# Copia os arquivos de dependência
COPY package*.json ./

# Instala as dependências
RUN npm install

# Copia o restante do código
COPY . .

# Expõe a porta usada pelo ng serve
EXPOSE 4200

# Comando padrão para rodar o servidor Angular com live reload
CMD ["npm", "run", "start"]