```bash
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Pass@word" \
  -p 1433:1433 --name mssql -h mssql \
  -d mcr.microsoft.com/mssql/server:2019-latest
```
