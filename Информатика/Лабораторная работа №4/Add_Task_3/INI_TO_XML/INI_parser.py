def ini_parser(ini_file):
    current_section = None
    parsed_data = {}
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