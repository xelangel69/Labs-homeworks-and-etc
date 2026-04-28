from configparser import ConfigParser
from yaml import dump

ini_file = ConfigParser()
file = open("schedule.ini","r")
ini_file.read_file(file)
data = dict()
sections=ini_file.sections()
for section in sections:
    items = ini_file.items(section)
    data[section] = dict(items)
yaml_string = dump(data)
print("Результат конвертации INI файла в YAML файл:\n")
print(yaml_string)

