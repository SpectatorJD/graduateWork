
Бэкенд-часть проекта предполагает реализацию следующего функционала:

Авторизация и аутентификация пользователей. Распределение ролей между пользователями: пользователь и администратор. CRUD-операции для объявлений и комментариев: администратор может удалять или редактировать все объявления и комментарии, а пользователи — только свои. Возможность для пользователей оставлять комментарии под каждым объявлением. Показ и сохранение картинок объявлений, а также аватарок пользователей.

Чтобы запустить фронтенд с помощью установленного Docker, нужно открыть командную строку (или терминал) и выполнить следующую команду: docker run -p 3000:3000 --rm ghcr.io/bizinmitya/front-react-avito:v1.21