# Probation task
Тестовое задание для стажировки
## Описание
REST API Task Management System с использованием Java. 
Система обеспечивает создание, редактирование, удаление и просмотр задач. 
Каждая задача содержитт заголовок, описание, статус, приоритет, автора задачи и исполнителя. 


### [Репозиторий](https://github.com/GrayRoof/tms-emobile)

### Запустить решение (через Maven)
Чтобы запустить сервер решения, выполните код ниже:

```
mvn package spring-boot:run
```

### Запустить решение (через Docker)
Чтобы запустить сервер решения, выполните код ниже:

```
docker-compose build
```
```
docker-compose up
```

Данная инструкция запустит сервер. 
Приложение будет доступно по адресу: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Схема БД: 
![ER диаграмма TMS](src/main/resources/img/tms-emobile-diagram.png)

