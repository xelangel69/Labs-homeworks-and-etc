def serializer(dictionary):
    binary_object = dictionary
    for section, keys_values in binary_object.items():
        print(f"{section}:")
        for key, value in keys_values.items():
            print(f"   {key}: {value}")