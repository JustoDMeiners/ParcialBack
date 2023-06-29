db.createUser(
    {
        user: "usr-inscription-mongo",
        pwd: "pwd-inscription-mongo",
        roles: [
            {
                role: "readWrite",
                db: "final"
            }
        ]
    }
);