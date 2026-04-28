import timeit

main_code = """

def ini_parser(ini_file):
    parsed_data = {}
    current_section = None
    with open(ini_file, "r") as ini_file:
        for line in ini_file:
            cleaned_line = line.strip()
            if not cleaned_line or cleaned_line.startswith(';'):
                continue
            if cleaned_line.startswith('[') and cleaned_line.endswith(']'):
                current_section = cleaned_line[1:-1]
                parsed_data[current_section] = {}
            elif current_section and '=' in cleaned_line:
                key, value = cleaned_line.split('=', 1)
                parsed_data[current_section][key.strip()] = value.strip()
    return parsed_data
print(ini_parser("schedule.ini"))

"""

execution_time = timeit.timeit(main_code, number=1)
print(f"\nВремя выполнения программы: {execution_time: .10f} секунд")