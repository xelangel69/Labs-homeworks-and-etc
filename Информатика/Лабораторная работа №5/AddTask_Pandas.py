import pandas as pd

df = pd.read_excel('ЛР_5.xlsx', sheet_name='Лист1', header=None)

def format_binary(bits_list):
    bits = []

    for i in range(16):
        if i < len(bits_list) and pd.notna(bits_list[i]) and bits_list[i] != '.':
            bits.append(str(int(bits_list[i])))
        else:
            bits.append('0')

    groups = []
    for i in range(0, 16, 4):
        group = bits[i:i + 4]
        groups.append(''.join(group))

    return '.'.join(groups)

source = []

results = []

row = 0
value = int(df.iloc[row, 2])
source.append(['A =', f'{value}'])

row = 1
value = int(df.iloc[row, 2])
source.append(['C =', f'{value}'])

row = 3
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:24].values)
results.append(['X1', f'A = {value}', f'B1 = {binary}'])

row = 4
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X2', f'C = {value}', f'B2 = {binary}'])

row = 5
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X3', f'A + C = {value}', f'B3 = {binary}'])

row = 6
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X4', f'A + C + C = {value}', f'B4 = {binary}'])

row = 7
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X5', f'C - A = {value}', f'B5 = {binary}'])

row = 8
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X6', f'65536 - X4 = {value}', f'B6 = {binary}'])

row = 9
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X7', f'-X1 = {value}', f'B7 = {binary}'])

row = 10
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X8', f'-X2 = {value}', f'B8 = {binary}'])

row = 11
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X9', f'-X3 = {value}', f'B9 = {binary}'])

row = 12
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X10', f'-X4 = {value}', f'B10 = {binary}'])

row = 13
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X11', f'-X5 = {value}', f'B11 = {binary}'])

row = 14
value = int(df.iloc[row, 2])
binary = format_binary(df.iloc[row, 6:22].values)
results.append(['X12', f'-X6 = {value}', f'B12 = {binary}'])

result_table = pd.DataFrame(results, columns=['Переменная', 'Операция', 'Двоичное представление'])
source_table = pd.DataFrame(source, columns=['Переменная', 'Значение'])

print(source_table.to_string(index=False, justify='left'))
print(result_table.to_string(index=False, justify='left'))
