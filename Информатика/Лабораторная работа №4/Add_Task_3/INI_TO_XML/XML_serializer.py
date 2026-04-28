def serializer(dictionary):
    print("<?xml version='1.0' encoding='UTF-8'?>")
    binary_object = dictionary
    print("<main>")
    for section, keys_values in binary_object.items():
        print(f"  <{section}>")
        for key, value in keys_values.items():
            print(f"    <{key}>{value}</{key}>")
        print(f"  </{section}>")
    print("</main>")