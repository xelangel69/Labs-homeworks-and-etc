import INI_parser as ini
import YAML_serializer as yaml

binary_object = ini.ini_parser("schedule.ini")
print("Результат конвертации INI файла в YAML файл:\n")
yaml.serializer(binary_object)