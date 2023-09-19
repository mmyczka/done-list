# --- Sample dataset

# --- !Ups
insert into term (id,name) values (  1,'Daily');
insert into term (id,name) values (  2,'Weekly');
insert into term (id,name) values (  3,'Monthly');
insert into term (id,name) values (  4,'Annual');

insert into category (id,name) values (  1,'Cleaning');
insert into category (id,name) values (  2,'Cooking');
insert into category (id,name) values (  3,'Leisure');
insert into category (id,name) values (  4,'Gardening');
insert into category (id,name) values (  5,'Work');
insert into category (id,name) values (  6,'Programming');
insert into category (id,name) values (  7,'Shopping');
insert into category (id,name) values (  8,'Exercise');
insert into category (id,name) values (  9,'Personal Development');
insert into category (id,name) values ( 10,'Social Activities');

insert into completed_task (id,name,reflections,category_id,term_id,achieved) values (  1,'Vacuumed the living room',              'When I see the clean floor, I feel a sense of accomplishment because I have a need for order and beauty in my surroundings.'                                      ,1,2,'2023-09-19 09:30');                                                      
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values (  2,'Went grocery shopping',                 'I felt a bit overwhelmed at the store because I had a need for efficiency and calmness. But once it was done, I felt relief knowing I am prepared for two days.'  ,7,1,'2023-09-19 10:30');                                                                                          
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values (  3,'Cooked spaghetti for dinner',           'After cooking, I feel satisfied because I met my need to nourish my body and also to creatively express myself through cooking.'                                  ,2,1,'2023-09-19 12:30');                                                          
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values (  4,'Finished reading a chapter of a novel', 'Reading gives me joy as it satisfies my need for relaxation and escapism.'                                                                                        ,3,1,'2023-09-19 13:44');  
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values (  5,'Watered the indoor plants',             'Observing the plants thrive gives me a sense of contribution because I have a need to care for and nurture living things.'                                        ,4,2,'2023-09-19 15:26');                                                  
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values (  6,'Attended a team meeting at work',       'During the meeting, I felt a bit anxious wanting clarity and to be heard. Afterwards, I felt connected having aligned with my colleagues.'                        ,5,2,'2023-09-18 09:12');                                                                 
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values (  7,'Went for a 30-minute walk.',            'After the walk, I felt rejuvenated because I catered to my need for movement and connection with nature.'                                                         ,8,1,'2023-09-18 10:03');                                  
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values (  8,'Washed the dishes',                     'Seeing a clean kitchen sink gives me peace of mind, addressing my need for cleanliness and order.'                                                                ,1,1,'2023-09-18 12:37');                          
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values (  9,'Played board games with the family.',   'Engaging in the game, I felt cheerful and connected, fulfilling my need for bonding and shared experiences.'                                                      ,10,2,'2023-09-18 16:50');                                      
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values ( 10,'Wrote a page in my journal.',           'Writing down my thoughts and feelings helps me feel grounded and self-connected, addressing my need for self-expression and reflection.'                          ,9,1,'2023-09-18 19:30');                                                                  
insert into completed_task (id,name,reflections,category_id,term_id,achieved) values ( 11,'Readed my done list',                   'After reviewing my accomplishments, I feel encouraged and centered, recognizing my capacity to achieve and fulfill my needs for progress and self-awareness.'     ,9,1,'2023-09-18 20:31');                                                                                      

# --- !Downs

delete from completed_task;
delete from category;
delete from term
