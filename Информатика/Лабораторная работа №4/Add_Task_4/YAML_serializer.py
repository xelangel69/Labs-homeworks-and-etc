import timeit

main_code = """

def serializer(dictionary):
    binary_object = dictionary
    for section, keys_values in binary_object.items():
        print(f"{section}:")
        for key, value in keys_values.items():
            print(f"   {key}: {value}")

serializer({'Tuesday_lesson_1': {'format': 'Practice', 'subject': 'Алгебра и алгоритмы', 'teacher': 'Воробьев Владимир Сергеевич', 'time': '8:10-9:40', 'room': 'Zoom', 'place': 'Online'}, 'Tuesday_lesson_2': {'format': 'Practice', 'subject': 'Математический анализ', 'teacher': 'Романова Екатерина Владимировна', 'time': '9:50-11:20', 'room': '2330', 'place': 'Кронверкский пр.49, лит.А'}, 'Tuesday_lesson_3': {'format': 'Lecture', 'subject': 'Математический анализ', 'teacher': 'Романова Екатерина Владимировна', 'time': '11:30-13:00', 'room': '1405', 'place': 'Кронверкский пр.49, лит.А'}, 'Tuesday_lesson_4': {'format': 'Lecture', 'subject': 'Алгебра и алгоритмы', 'teacher': 'Попов Антон Игоревич', 'time': '13:30-15:00', 'room': '1405', 'place': 'Кронверкский пр.49, лит.А'}, 'Thursday_lesson_1': {'format': 'Lecture', 'subject': 'Реформы и реформаторы в истории России', 'teacher': 'Вычеров Дмитрий Александрович', 'time': '15:30-17:00', 'room': '1404', 'place': 'Кронверкский пр.49, лит.А'}, 'Thursday_lesson_2': {'format': 'Practice', 'subject': 'Реформы и реформаторы в истории России', 'teacher': 'Новиков Максим Дмитриевич', 'time': '17:10-18:40', 'room': '1428', 'place': 'Кронверкский пр.49, лит.А'}})

"""

execution_time = timeit.timeit(main_code, number=1)
print(f"\nВремя выполнения программы: {execution_time: .10f} секунд")