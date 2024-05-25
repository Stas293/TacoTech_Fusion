CREATE KEYSPACE IF NOT EXISTS taco_spring WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}
                                           AND durable_writes = true;