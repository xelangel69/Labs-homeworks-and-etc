# Author = Kirillov Ivan Alexandrovich
# Group = P3107
# Date = 05.10.2025

RESET = "\033[0m"
BOLD = "\033[1m"
RED = "\033[91m"
GREEN = "\033[92m"
YELLOW = "\033[93m"
CYAN = "\033[96m"

from re import *

print(f"{CYAN}{BOLD}Введите код с сайта isu.itmo.ru (Enter 2 раза - завершение ввода):{RESET}")
lines = []
while True:
    line = input()
    if line == "":
        break
    lines.append(line)

id_string = "\n".join(lines)

reg = r'id="([^"]+)"'
ids = findall(reg, id_string)

print(f"\n{YELLOW}{BOLD}Результаты поиска ID:{RESET}")

if not ids:
    print(f"{RED}ID-элементы не найдены!{RESET}")
else:
    for i in range(len(ids)):
        print(f"{GREEN}Значение элемента ID №{i+1}:{RESET} {BOLD}{ids[i]}{RESET}")