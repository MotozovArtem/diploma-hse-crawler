# HSE Masters diploma

## Поднять приложение
Чтобы поднять систему в Docker в detach режиме
```
docker compose up -d
```

Чтобы поднять систему в Docker 
```
docker compose up
```

Контейнеры, поднимаемые Docker compose:
* mongo_db - контейнер с Mongo DB
* crawler_api - контейнер с приложением API
* crawler_service - контейнер с приложением сервиса сбора данных
