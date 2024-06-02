print('Start initializing MongoDB');

db = db.getSiblingDB('tacodb')

db.createUser({
    user: "taco",
    pwd: "taco",
    roles: [
        {
            role: "readWrite",
            db: "tacodb"
        }
    ]
})

print('Completed initializing MongoDB');
