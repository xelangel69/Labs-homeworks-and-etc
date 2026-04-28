msg = input("Введите 7-битное сообщение: ")

if len(msg) != 7 or any(x not in '01' for x in msg):
    print("Неверная строка!")
    exit()

b = [None] + [int(x) for x in msg]

s1 = str((b[1]+b[3]+b[5]+b[7])%2)
s2 = str((b[2]+b[3]+b[6]+b[7])%2)
s3 = str((b[4]+b[5]+b[6]+b[7])%2)

errror_byte = int(s3+s2+s1,2)

if errror_byte != 0:
    print(f'Обнаружена ошибка в бите {errror_byte}')
    b[errror_byte] ^= 1
    print("Правильное сообщение - ", str(b[3])+str(b[5])+str(b[6])+str(b[7]))
else:
    print("Ошибки отсутсвуют")
    print("Правильное сообщение - ", str(b[3])+str(b[5])+str(b[6])+str(b[7]))

    