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

minute = r'(?:\*|[0-9]|[1-5][0-9]|(?:[0-9]|[1-5][0-9])-(?:[0-9]|[1-5][0-9]))(?:/(?:[1-9]|[1-5][0-9]))?'
hour = r'(?:\*|[0-9]|1[0-9]|2[0-3]|(?:[0-9]|1[0-9]|2[0-3])-(?:[0-9]|1[0-9]|2[0-3]))(?:/(?:[1-9]|1[0-9]|2[0-3]))?'
day_of_month = r'(?:\*|[1-9]|[12][0-9]|3[01]|(?:[1-9]|[12][0-9]|3[01])-(?:[1-9]|[12][0-9]|3[01]))(?:/(?:[1-9]|[12][0-9]|3[01]))?'
month = r'(?:\*|[1-9]|1[0-2]|(?:[1-9]|1[0-2])-(?:[1-9]|1[0-2]))(?:/(?:[1-9]|1[0-2]))?'
day_of_week = r'(?:\*|[0-6]|(?:[0-6])-(?:[0-6]))(?:/(?:[0-6]))?'

cron_reg = compile(rf'^{minute}\s+{hour}\s+{day_of_month}\s+{month}\s+{day_of_week}$')

cron_string = input(f"{CYAN}{BOLD}Введите ваше cron-выражение: {RESET}").strip()

if cron_reg.match(cron_string):
    print(f"{GREEN}{BOLD}Формат cron-выражения корректный.{RESET}")
else:
    print(f"{RED}{BOLD}Формат cron-выражения имеет ошибку.{RESET}")