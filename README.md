# trades

Решение задачи по StreamAPI\
Путь к файлу является аргументом командной строки.

Формат файла: 1 строка на каждую сделку, поля разделены символом табуляции ('\t'):\
TRADENO - номер сделки\
TRADETIME - время\
SECBOARD - площадка\
SECCODE	- код ценной бумаги\
PRICE - цена\
VOLUME - кол-во бумаг\
ACCRUEDINT - НКД (для облигаций)\
YIELD - доодность (для облигаций)\
VALUE - сумма сделки.\
Напиши программу, которая прочитает этот файл и вычислит для бумаг, торгуемых на площадках TQBR (российские акции) и FQBR (иностраные акции), цену открытия и закрытия (первая и последняя сделки за день). Выведите 10 самых удачливых (максимальный рост цены) и 10 самых неудачливых (максимальное падение) акций этого дня. Для каждой из них выведите изменение цены в процентах и общий объем сделок в рублях (на TQBR/FQBR все сделки - в рублях).
