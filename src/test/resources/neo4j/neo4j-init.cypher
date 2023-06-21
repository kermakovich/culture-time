CREATE CONSTRAINT performances_unique_title IF NOT EXISTS FOR (p:Performance) REQUIRE p.title IS UNIQUE;

CREATE (:Dancer { surname: "lopenko", name: "vasiliy", description: "As he glides across the stage,
 his movements are fluid and graceful. His body seems to flow effortlessly from one pose to the next,
 each step executed with precision and poise.", id: "b8bc995c-e232-4787-a0f5-1b86d3d6ae9e", experience: "10.4"});

CREATE (:Dancer { surname: "remotuk", name: "tatyana", description: "Her arms stretch out like wings,
adding a sense of ethereal beauty to her performance. The way she moves seems almost otherworldly,
as if she is a creature of pure elegance and grace. ", id: "b8b4355c-ea3b-458c-a0f5-1b86d3d6ae9e", experience: "9.4"});

CREATE (:Dancer { surname: "firenelenko", name: "nina", description: "Her expression is serene, yet intense,
conveying both the joy and the passion she feels for her art. Watching her dance is like witnessing
a living work of art, a masterpiece in motion.", id: "2a404590-f9e7-463e-a11a-1fdac3a1d1c4", experience: "2.8"});


CREATE (:Performance { description: "Swan Lake, Op. 20, is a ballet composed by Russian composer
Pyotr Ilyich Tchaikovsky in 1875–76", id: "b01dd126-0086-4c22-98a8-6a9b71c634b2", title: "Swan Lake"});

CREATE (:Performance { description: "Romeo and Juliet is a tragedy written by William Shakespeare
early in his career about the romance between two Italian youths from feuding families.",
id: "d001a193-e070-4c1b-8bdd-ed86722a2e0e", title: "Romeo and Juliet"});

CREATE (:Performance { description: "Cinderella is a 1960 Soviet musical film directed
by Rostislav Zakharov and Aleksandr Rou.",
id: "d001a193-e232-4c1b-0086-1b86d3d6ae9e", title: "Cinderella"});


CREATE (:Visitor { surname: "lopenko", name: "polina", id: "643be014-9f73-417a-91c7-34f0850cfc68"});
CREATE (:Visitor { surname: "pilemko", name: "lera", id: "857d1c79-3f76-40d5-a00f-8c785595994b"});
CREATE (:Visitor { surname: "lopeko", name: "sergey", id: "b01dd126-3f76-8bdd-a00f-1b86d3d6ae9e"});


//Relations
MATCH (d1:Dancer {id: "b8bc995c-e232-4787-a0f5-1b86d3d6ae9e"})
MATCH (p1:Performance {id: "b01dd126-0086-4c22-98a8-6a9b71c634b2"})
MATCH (p2:Performance {id: "d001a193-e070-4c1b-8bdd-ed86722a2e0e"})
CREATE (d1) - [:ACTS_IN {firstPerformanceDate: "2023-09-09"}] -> (p1),
       (d1) - [:ACTS_IN {firstPerformanceDate: "2021-01-09"}] -> (p2);

MATCH (v1:Visitor {id: "643be014-9f73-417a-91c7-34f0850cfc68"})
MATCH (p1:Performance {id: "b01dd126-0086-4c22-98a8-6a9b71c634b2"})
MATCH (p2:Performance {id: "d001a193-e070-4c1b-8bdd-ed86722a2e0e"})
CREATE (v1) - [:LIKED] -> (p1),
       (v1) - [:LIKED] -> (p2);


MATCH (v1:Visitor {id: "643be014-9f73-417a-91c7-34f0850cfc68"})
MATCH (v2:Visitor {id: "857d1c79-3f76-40d5-a00f-8c785595994b"})
CREATE (v1) - [:FRIEND] -> (v2),
       (v2) - [:FRIEND] -> (v1);
