import random
import sys
import json

names = [
    "Cher",
    "Harley",
    "Casper",
    "Rusty",
    "Dexter",
    "Coco",
    "Cupcake",
    "Mittens",
    "Midnight",
    "Shakira",
    "Oscar",
    "Sophie",
    "Misty",
    "Twiggy",
    "George",
    "Oprah",
    "Boo",
    "Sasha",
    "Oreo",
    "Panda",
    "Ziggy",
    "Jake",
    "Tigger",
    "Sebastian",
    "Molly",
    "Kiki",
    "Beyonce",
    "Rocky",
    "Frankie",
    "Callie",
    "Simba",
    "Izzy",
    "Zoe",
    "Jasmine",
    "Lucy",
    "Bandit",
    "Loki",
    "Fiona",
    "Lilly",
    "Charlie",
    "Tucker",
    "Sassy",
    "Gracie",
    "Pumpkin",
    "Lola",
    "Jasper",
    "Jack",
    "Angel",
    "Oliver",
    "Sox",
    "Houdini",
    "Nala",
    "Peanut",
    "Romeo",
    "Elvis",
    "Marley",
    "Buddy",
    "Phoebe",
    "Precious",
    "Boomer",
    "Samantha",
    "BatMan",
    "Salem",
    "Smokey",
    "Max",
    "Missy",
    "Pepper",
    "Belle",
    "Emma",
    "Toby",
    "Sammy",
    "Murphy",
    "Blackie",
    "Tiger",
    "Ginger",
    "Leo",
    "Garfield",
    "Shadow",
    "Fluffy",
    "Sweetie",
    "Bailey",
    "Chester",
    "Snickers",
    "Scooter",
    "Lily",
    "Maggie",
    "Cali",
    "Zeus",
    "Baby",
    "Daisy",
    "Lucky",
    "Princess",
    "Muffin",
    "Sadie",
    "Luna",
    "Maddie",
    "Coco Chanel",
    "Milo",
    "Dusty",
    "Boots"
]

breed = [
    "Abyssinian",
    "American Bobtail",
    "American Curl",
    "American Shorthair",
    "American Wirehair",
    "Applehead Siamese",
    "Balinese",
    "Bengal",
    "Birman",
    "Bombay",
    "British Shorthair",
    "Burmese",
    "Burmilla",
    "Calico",
    "Canadian Hairless",
    "Chartreux",
    "Chausie",
    "Chinchilla",
    "Cornish Rex",
    "Cymric",
    "Devon Rex",
    "Dilute Calico",
    "Dilute Tortoiseshell",
    "Domestic Long Hair",
    "Domestic Medium Hair",
    "Domestic Short Hair",
    "Havana",
    "Himalayan",
    "Japanese Bobtail",
    "Javanese",
    "Korat",
    "LaPerm",
    "Maine Coon",
    "Manx",
    "Munchkin",
    "Nebelung",
    "Norwegian Forest Cat",
    "Ocicat",
    "Oriental Long Hair",
    "Oriental Short Hair",
    "Oriental Tabby",
    "Persian",
    "Pixiebob",
    "Ragamuffin",
    "Ragdoll",
    "Russian Blue",
    "Scottish Fold",
    "Selkirk Rex",
    "Siamese",
    "Siberian",
    "Silver",
    "Singapura",
    "Snowshoe",
    "Somali",
    "Sphynx",
    "Hairless Cat",
    "Tabby",
    "Tiger",
    "Tonkinese",
    "Torbie",
    "Tortoiseshell",
    "Toyger",
    "Turkish Angora",
    "Turkish Van",
    "Tuxedo",
    "York Chocolate"
]

age = [
    "Kitten",
    "Young",
    "Adult",
    "Senior"
]

size = [
    "Small",
    "Medium",
    "Large",
    "Extra Large"
]

gender = [
    "Male",
    "Female"
]

coatLength = [
    "Hairless",
    "Short",
    "Medium",
    "Long"
]

color = [
    "Black",
    "Tuxedo",
    "Chocolate",
    "White",
    "Tan",
    "Calico",
    "Cream",
    "Ivory",
    "Gray",
    "Silver",
    "Smoke",
    "Brown"
]

# name, breed, age, size, gender, coatLength, color
catAmount = 100
if len(sys.argv) == 2:
    catAmount = min(int(sys.argv[1]), 100)

def generateCat():
    cat = {
        "name": random.choice(names).lower().replace(' ', '_'),
        "breed": random.choice(breed).lower().replace(' ', '_'),
        "age": random.choice(age).lower().replace(' ', '_'),
        "size": random.choice(size).lower().replace(' ', '_'),
        "gender": random.choice(gender).lower().replace(' ', '_'),
        "coatLength": random.choice(coatLength).lower().replace(' ', '_'),
        "color": random.choice(color).lower().replace(' ', '_')
    }
    return cat

catArray = []
for _ in range(catAmount):
    catArray.append(generateCat())
print(json.dumps({"cats": catArray}, indent=4))
