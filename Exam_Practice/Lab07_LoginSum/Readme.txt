Используя дрвайвер jdbc выполнить организацию соед с DB.
DB должна содержать login,code,sum.
На первой Jsp старнице разместите поля ввода login,code и кнопку login.
В случае совпадения со значением в бд выполнить переход на
страницу action.jsp с двумя кнопками
"check" - выводит сумму на счету(sum),
"pay" - отнимает 100 от sum,если возможно.
Все действия с sum запис в лог файл.
если login и code введен не верно перейдите на страницу с
соотв сообщением. JSP и сервлет, два пользователя(admin, user).
Получить время создания сессии, ид сессии, лог файл