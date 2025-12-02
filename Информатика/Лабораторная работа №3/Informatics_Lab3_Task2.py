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

letters_input = input(f"{CYAN}{BOLD}Введите три любые буквы: {RESET}")

if len(letters_input) != 3 or any(x in '0123456789' for x in letters_input):
    print(f"{RED}Вы ввели не три буквы!{RESET}")
    exit()

letters = [x for x in letters_input]

for i in range(len(letters)):
    if letters.count(letters[i]) > 1:
        print(f"{RED}Есть повторяющиеся буквы!{RESET}")
        exit()

dist = int(input(f"{CYAN}{BOLD}Введите расстояние: {RESET}"))

print(f"{CYAN}{BOLD}Введите последовательность (Enter 2 раза - завершение ввода):{RESET}")
lines = []
while True:
    line = input()
    if line == "":
        break
    lines.append(line)

input_string = "\n".join(lines)

reg = rf'{letters[0]}\D{{{dist}}}{letters[1]}\D{{{dist}}}{letters[2]}'

words = findall(reg, input_string)

print(f"\n{YELLOW}{BOLD}Результаты поиска:{RESET}")

if not words:
    print(f"{RED}Подходящие слова не найдены!{RESET}")
else:
    for i in range(len(words)):
        print(f"{GREEN}Слово №{i+1}:{RESET} {BOLD}{words[i]}{RESET}")