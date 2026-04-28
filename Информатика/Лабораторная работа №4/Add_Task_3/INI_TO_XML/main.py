import INI_parser as ini
import XML_serializer as xml

binary_object = ini.ini_parser("schedule.ini")
print("Результат конвертации INI файла в XML файл:\n")
xml.serializer(binary_object)