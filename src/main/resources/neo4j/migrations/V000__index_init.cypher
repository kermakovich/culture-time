CREATE CONSTRAINT performances_unique_title IF NOT EXISTS FOR (p:Performance) REQUIRE p.title IS UNIQUE;
