import timeit

main_code ="""

from configparser import ConfigParser
import yaml

ini_file = ConfigParser()
try:
    file = open("schedule.ini","r")
    ini_file.read_file(file)
    output_dict=dict()
    sections=ini_file.sections()
    for section in sections:
        items = ini_file.items(section)
        output_dict[section] = dict(items)
    yaml_string = yaml.dump(output_dict)
    # ИЗМЕНЕНИЕ ЗДЕСЬ: Используем \\n вместо \n
    print("Результат конвертации INI файла в YAML файл:\\n")
    print(yaml_string)
except FileNotFoundError:
    print("Ошибка: Файл 'schedule.ini' не найден.")

"""

execution_time = timeit.timeit(main_code, number=1)
print(f"\nВремя выполнения программы: {execution_time: .10f} секунд")

