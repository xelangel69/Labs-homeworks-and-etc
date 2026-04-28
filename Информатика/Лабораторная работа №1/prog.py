# Author = Kirillov Ivan Alexandrovich
# Group = P3107
# Date = 18.10.2025

RESET = "\033[0m"
BOLD = "\033[1m"
GREEN = "\033[92m"
CYAN = "\033[96m"

def fib_to_decimal(fib_str: str) -> int:
    fib = [1, 2]
    while len(fib) < len(fib_str):
        fib.append(fib[-1] + fib[-2])
        
    result = 0
    for i, digit in enumerate(reversed(fib_str)):
        if digit == '1':
            result += fib[i]
    return result

fib_number = input(f"{CYAN}{BOLD}Введите ваше число в Фиббоначиевой системе счисления: {RESET}")
print(f"{GREEN}{BOLD}Ваше число в десятичной системе счисления: {RESET}", fib_to_decimal(fib_number))







