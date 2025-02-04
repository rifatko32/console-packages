Для работы с программой необходимо запустить приложение и вводить команды в консоли.

Команды в файле имеют следующий формат:
commandName arguments

Где commandName - имя команды, а arguments - аргументы команды, разделенные пробелами.

В файле представлены следующие команды:

load, для загрузки посылки с парамерами:
-packages-file: путь к файлу с пакетами (optional)
-packages-text: текст посылок (optional)
-trucks: список грузовиков, разделенных точкой с запятой (required)
-type: тип алгоритма погрузки пакета (width, single или equal) (required)
-out: тип вывода (json-file или console) (optional)
-out-filename: имя файла для вывода (optional)

результат работы можно вывести либо в файл, либо на консоль

unload, для разгрузки грузовика с парамерами:
-infile: путь к файлу с пакетами (required)
-outfile: путь к файлу для вывода (required)
-withcount: функция подсчета (optional)

create, для создания типа посылки с парамерами:
-name: имя типа пакета (required)
-form: форма пакета (required)
-description: символ заполнения пакета (required)


find, для поиска типа посылки с парамерами:
-name: имя типа пакета (required)

delete, для удаления типа посылки
-name: имя типа пакета (required)

edit, для редактирования типа посылки
-name: имя типа пакета (required)
-form: форма пакета (optional)
-description: символ заполнения пакета (optional)

Примеры команд можно увидеть в файле command examples.txt

Бот для работы из телеграма: https://t.me/yrPackageBot_user_bot, формат команд такой же