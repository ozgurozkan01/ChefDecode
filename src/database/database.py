import sqlite3

conn = sqlite3.connect('/Users/ubeydgur/Projects/Java/ChefDecode/src/database/recipes.db')

c = conn.cursor()

# Tablo oluşturma sorgusu
c.execute('''
CREATE TABLE IF NOT EXISTS recipes(
    RecipeID INT NOT NULL PRIMARY KEY,
    RecipeName VARCHAR(30) NOT NULL,
    Category VARCHAR(30) NOT NULL,
    PreparationTime INT NOT NULL,
    Instruction TEXT NOT NULL,
    NumberPoints INT NOT NULL,
    TotalPoints REAL NOT NULL
);
''')

c.execute('''
CREATE TABLE IF NOT EXISTS ingredients(
    IngredientID INT NOT NULL PRIMARY KEY,
    IngredientName VARCHAR(30) NOT NULL,
    StockQuantity VARCHAR(30) NOT NULL,
    Unit VARCHAR(30) NOT NULL,
    UnitPrice INT NOT NULL
);
''')

c.execute('''
CREATE TABLE IF NOT EXISTS relation(
    RecipeID INT,
    IngredientID INT,
    IngredientQuantity FLOAT NOT NULL,
    FOREIGN KEY (RecipeID) REFERENCES recipes(RecipeID),
    FOREIGN KEY (IngredientID) REFERENCES ingredients(IngredientID)
);
''')


c.execute('''
INSERT INTO recipes VALUES
    (1, 'Tavuk Tandır', 'mainCourse', 60, 'Tavukları marine edip, tandırda pişirin.', 12, 102.0),
    (2, 'Kuzu tandır', 'mainCourse', 120, 'Kuzu etini baharatlarla marine edip, fırında ağır ateşte pişirin.', 9, 81.0),
    (3, 'Sebzeli kısır', 'mainCourse', 30, 'Bulguru sıcak suyla ıslatıp, sebzeleri ekleyin ve karıştırın.', 26, 182.0),
    (4, 'Baklava', 'dessert', 90, 'Yufkaları üst üste koyup, fıstıkla aralarına serpin ve fırında pişirin.', 19, 190.0),
    (5, 'Çikolatalı mousse', 'dessert', 20, 'Çikolata ve krema ile karıştırıp, buzdolabında bekletin.', 7, 56.0),
    (6, 'Sütlaç', 'dessert', 40, 'Pirinci pişirip, süt ve şeker ekleyin ve karıştırın.', 14, 105.0),
    (7, 'Humus', 'appetizer', 15, 'Nohut, tahin, limon ve sarımsak ile karıştırın.', 5, 40.0),
    (8, 'Haydari', 'appetizer', 10, 'Yoğurt, sarımsak ve nane ile karıştırın.', 4, 26.5),
    (9, 'Cacık', 'appetizer', 10, 'Yoğurt, salatalık ve sarımsak ile karıştırın.', 17, 144.5),
    (10, 'Patates Kızartması', 'snack', 20, 'Patatesleri dilimleyip, kızartın.', 22, 165.0),
    (11, 'Popcorn', 'snack', 10, 'Mısır tanelerini patlatın.', 9, 45.0),
    (12, 'Fıstık Ezmesi', 'snack', 15, 'Kavrulmuş fıstıkları ezip, krem haline getirin.', 3, 18.0),
    (13, 'Çorba', 'soup', 30, 'Sebzeleri haşlayıp, püre haline getirin.', 16, 112.0),
    (14, 'Zeytinyağlı Enginar', 'soup', 40, 'Enginarları haşlayıp, zeytinyağı ile servis yapın.', 5, 42.5),
    (15, 'Tarator', 'soup', 15, 'Yoğurt, ceviz ve ekmek içi ile karıştırın.', 3, 18.0);
        (16, 'Pizza', 'mainCourse', 45, 'Hamuru hazırlayıp, üzerine malzemeleri ekleyin ve fırında pişirin.', 13, 110.0),
        (17, 'Karnıyarık', 'mainCourse', 60, 'Patlıcanları kızartıp, iç harcı ile doldurun ve fırında pişirin.', 12, 96.0),
        (18, 'Karnabahar Kızartma', 'mainCourse', 35, 'Karnabaharı haşlayıp, kızartın ve sosla servis yapın.', 10, 85.0),
        (19, 'Lazanya', 'mainCourse', 80, 'Kat kat malzeme ekleyip, fırında pişirin.', 18, 140.0),
        (20, 'İmam Bayıldı', 'mainCourse', 90, 'Patlıcanları yağda kızartıp, zeytinyağlı sosla pişirin.', 15, 105.0),
        (21, 'Mercimek Köftesi', 'appetizer', 25, 'Mercimeği haşlayıp, baharatlarla yoğurun.', 8, 68.0),
        (22, 'Zeytinyağlı Yaprak Sarma', 'appetizer', 50, 'Yapraklara iç harç koyup sarın ve pişirin.', 14, 112.0),
        (23, 'Acılı Ezme', 'appetizer', 15, 'Sebzeleri ince kıyıp, baharatlarla karıştırın.', 7, 56.0),
        (24, 'Panna Cotta', 'dessert', 25, 'Krema ile şeker karışımını dondurun.', 9, 72.0),
        (25, 'Tiramisu', 'dessert', 30, 'Kremayı bisküvilerle kat kat dizip buzdolabında bekletin.', 10, 80.0),
        (26, 'Supangle', 'dessert', 20, 'Çikolatayı sütle pişirip buzdolabında soğutun.', 6, 50.0),
        (27, 'Helva', 'dessert', 15, 'Un ve tereyağını kavurup, şekerle karıştırın.', 8, 64.0),
        (28, 'Şekerpare', 'dessert', 35, 'Tatlıları hazırlayıp şerbetle buluşturun.', 12, 90.0),
        (29, 'Fırında Makarna', 'mainCourse', 40, 'Makarnayı haşlayıp peynirle fırına verin.', 11, 88.0),
        (30, 'Kısır', 'appetizer', 20, 'Bulgurla sebzeleri karıştırın.', 16, 128.0),
        (31, 'Sigara Böreği', 'appetizer', 20, 'Yufkaları iç malzeme ile sarıp kızartın.', 6, 42.0),
        (32, 'Fırında Mücver', 'appetizer', 30, 'Kabak ve sebzeleri karıştırıp fırına verin.', 5, 40.0),
        (33, 'Paçanga Böreği', 'appetizer', 25, 'Yufkalara pastırma ve kaşar koyup kızartın.', 9, 72.0),
        (34, 'Soğan Halkası', 'snack', 15, 'Soğanları halka şeklinde doğrayıp kızartın.', 7, 49.0),
        (35, 'Kumpir', 'snack', 30, 'Patatesi fırında pişirip içini doldurun.', 13, 104.0),
        (36, 'Ayva Tatlısı', 'dessert', 40, 'Ayvaları pişirip şerbetle tatlandırın.', 10, 80.0),
        (37, 'Revani', 'dessert', 35, 'Revaniyi pişirip şerbetle buluşturun.', 12, 96.0),
        (38, 'Profiterol', 'dessert', 25, 'Hamur toplarını çikolata sosuyla kaplayın.', 14, 112.0),
        (39, 'Puding', 'dessert', 10, 'Sütle çikolatalı karışımı pişirip soğutun.', 4, 32.0),
        (40, 'Güllaç', 'dessert', 15, 'Güllaç yapraklarını sütle yumuşatıp aralarına fıstık serpin.', 5, 45.0),
        (41, 'Yoğurtlu Kabak', 'appetizer', 10, 'Kabakları yoğurtla karıştırıp servis yapın.', 3, 24.0),
        (42, 'Tavuk Şiş', 'mainCourse', 35, 'Tavukları şişe dizip ızgarada pişirin.', 17, 136.0),
        (43, 'Bulgur Pilavı', 'mainCourse', 25, 'Bulguru sebzelerle pişirin.', 12, 96.0),
        (44, 'Kaşarlı Mantar', 'appetizer', 20, 'Mantarları kaşarla fırına verin.', 8, 64.0),
        (45, 'Fırında Tavuk', 'mainCourse', 50, 'Tavuğu baharatlayıp fırına verin.', 20, 160.0),
        (46, 'Karnıbahar Graten', 'mainCourse', 40, 'Karnabaharı peynirle fırına verin.', 15, 120.0),
        (47, 'Çoban Salata', 'appetizer', 10, 'Sebzeleri doğrayıp karıştırın.', 5, 40.0),
        (48, 'Tavuklu Makarna', 'mainCourse', 30, 'Makarnayı haşlayıp tavukla karıştırın.', 11, 88.0),
        (49, 'Kuzu İncik', 'mainCourse', 90, 'Eti ağır ateşte pişirin.', 21, 168.0),
        (50, 'Elmalı Kurabiye', 'dessert', 25, 'Elmalı harcı hamura sarıp pişirin.', 9, 72.0);
''')


c.execute('''
INSERT INTO ingredients VALUES
    (1, 'Tavuk', '0', 'g', 40),
    (2, 'Kuzu eti', '0', 'g', 60),
    (3, 'Bulgur', '0', 'g', 5),
    (4, 'Yufka', '0', 'adet', 15),
    (5, 'Çikolata', '0', 'g', 12),
    (6, 'Pirinc', '0', 'g', 4),
    (7, 'Nohut', '0', 'g', 10),
    (8, 'Yoğurt', '0', 'g', 8),
    (9, 'Salatalık', '0', 'adet', 2),
    (10, 'Patates', '0', 'g', 6),
    (11, 'Mısır', '0', 'g', 3),
    (12, 'Fıstık', '0', 'g', 25),
    (13, 'Sebze', '0', 'g', 7),
    (14, 'Enginar', '0', 'adet', 20),
    (15, 'Ceviz', '0', 'g', 18),
    (16, 'Hamur', '0', 'g', 20),
    (17, 'Patlıcan', '0', 'adet', 4),
    (18, 'Karnabahar', '0', 'adet', 6),
    (19, 'Lazanya', '0', 'g', 15),
    (20, 'Zeytinyağı', '0', 'ml', 8),
    (21, 'Mercimek', '0', 'g', 6),
    (22, 'Asma Yaprağı', '0', 'adet', 2),
    (23, 'Acı Biber', '0', 'adet', 1),
    (24, 'Krema', '0', 'ml', 25),
    (25, 'Mascarpone', '0', 'g', 45),
    (26, 'Şeker', '0', 'g', 5),
    (27, 'Tereyağı', '0', 'g', 10),
    (28, 'Şerbet', '0', 'ml', 3),
    (29, 'Peynir', '0', 'g', 30),
    (30, 'Yeşillik', '0', 'g', 3),
    (31, 'Mantar', '0', 'g', 7),
    (32, 'Kaşar', '0', 'g', 20),
    (33, 'Fırın Kabı', '0', 'adet', 1),
    (34, 'Şekerpare Hamuru', '0', 'g', 8),
    (35, 'Patates', '0', 'g', 5),
    (36, 'Ayva', '0', 'adet', 3),
    (37, 'Mısır Nişastası', '0', 'g', 6),
    (38, 'Süt', '0', 'ml', 2),
    (39, 'Fıstık', '0', 'g', 10),
    (40, 'Yufka', '0', 'adet', 15),
    (41, 'Tavuk Göğsü', '0', 'g', 35),
    (42, 'Yoğurt', '0', 'g', 5),
    (43, 'Nane', '0', 'g', 1),
    (44, 'Limon', '0', 'adet', 2),
    (45, 'Salça', '0', 'g', 7),
    (46, 'Maydanoz', '0', 'g', 2),
    (47, 'Döner Eti', '0', 'g', 40),
    (48, 'Kuzu Ciğeri', '0', 'g', 50),
    (49, 'Un', '0', 'g', 6),
    (50, 'Bakliyat Karışımı', '0', 'g', 5);
''')


c.execute('''
INSERT INTO relation VALUES
    (1, 1, 500),    -- Tavuk Tandır için 500 gram Tavuk
    (1, 20, 50),    -- Tavuk Tandır için 50 ml Zeytinyağı
    (2, 2, 700),    -- Kuzu Tandır için 700 gram Kuzu eti
    (2, 20, 30),    -- Kuzu Tandır için 30 ml Zeytinyağı
    (3, 3, 200),    -- Sebzeli Kısır için 200 gram Bulgur
    (3, 13, 100),   -- Sebzeli Kısır için 100 gram Sebze
    (4, 4, 10),     -- Baklava için 10 adet Yufka
    (4, 26, 200),   -- Baklava için 200 gram Şeker
    (5, 5, 100),    -- Çikolatalı Mousse için 100 gram Çikolata
    (5, 24, 50),    -- Çikolatalı Mousse için 50 ml Krema
    (6, 6, 150),    -- Sütlaç için 150 gram Pirinç
    (6, 38, 500),   -- Sütlaç için 500 ml Süt
    (7, 7, 300),    -- Humus için 300 gram Nohut
    (7, 8, 100),    -- Humus için 100 gram Yoğurt
    (8, 8, 200),    -- Haydari için 200 gram Yoğurt
    (8, 43, 5),     -- Haydari için 5 gram Nane
    (9, 8, 250),    -- Cacık için 250 gram Yoğurt
    (9, 9, 1),      -- Cacık için 1 adet Salatalık
    (10, 10, 300),  -- Patates Kızartması için 300 gram Patates
    (11, 11, 50),   -- Popcorn için 50 gram Mısır
    (12, 12, 100),  -- Fıstık Ezmesi için 100 gram Fıstık
    (13, 13, 200),  -- Çorba için 200 gram Sebze
    (14, 14, 3),    -- Zeytinyağlı Enginar için 3 adet Enginar
    (15, 15, 50),   -- Tarator için 50 gram Ceviz
    (16, 16, 300),  -- Pizza için 300 gram Hamur
    (16, 20, 30),   -- Pizza için 30 ml Zeytinyağı
    (17, 17, 2),    -- Karnıyarık için 2 adet Patlıcan
    (17, 20, 20),   -- Karnıyarık için 20 ml Zeytinyağı
    (18, 18, 250),  -- Karnabahar Kızartma için 250 gram Karnabahar
    (18, 20, 25),   -- Karnabahar Kızartma için 25 ml Zeytinyağı
    (19, 19, 200),  -- Lazanya için 200 gram Lazanya
    (19, 20, 30);   -- Lazanya için 30 ml Zeytinyağı
    (20, 17, 2), -- İmam Bayıldı - Patlıcan, 2 adet
        (20, 20, 50), -- İmam Bayıldı - Zeytinyağı, 50 ml
        (21, 21, 100), -- Mercimek Köftesi - Mercimek, 100 g
        (21, 8, 50), -- Mercimek Köftesi - Yoğurt, 50 g
        (22, 22, 10), -- Zeytinyağlı Yaprak Sarma - Asma Yaprağı, 10 adet
        (22, 20, 30), -- Zeytinyağlı Yaprak Sarma - Zeytinyağı, 30 ml
        (23, 23, 2), -- Acılı Ezme - Acı Biber, 2 adet
        (23, 13, 50), -- Acılı Ezme - Sebze, 50 g
        (24, 24, 100), -- Panna Cotta - Krema, 100 ml
        (25, 25, 60), -- Tiramisu - Mascarpone, 60 g
        (25, 26, 50), -- Tiramisu - Şeker, 50 g
        (26, 5, 30), -- Supangle - Çikolata, 30 g
        (26, 38, 200), -- Supangle - Süt, 200 ml
        (27, 27, 40), -- Helva - Tereyağı, 40 g
        (27, 26, 60), -- Helva - Şeker, 60 g
        (28, 34, 80), -- Şekerpare - Şekerpare Hamuru, 80 g
        (28, 28, 50), -- Şekerpare - Şerbet, 50 ml
        (29, 29, 100), -- Fırında Makarna - Peynir, 100 g
        (29, 13, 50), -- Fırında Makarna - Sebze, 50 g
        (30, 3, 150), -- Kısır - Bulgur, 150 g
        (30, 13, 60), -- Kısır - Sebze, 60 g
        (31, 4, 3), -- Sigara Böreği - Yufka, 3 adet
        (31, 13, 40), -- Sigara Böreği - Sebze, 40 g
        (32, 18, 200), -- Fırında Mücver - Karnabahar, 200 g
        (32, 13, 30), -- Fırında Mücver - Sebze, 30 g
        (33, 4, 2), -- Paçanga Böreği - Yufka, 2 adet
        (33, 32, 60), -- Paçanga Böreği - Kaşar, 60 g
        (34, 13, 100), -- Soğan Halkası - Sebze, 100 g
        (34, 27, 20), -- Soğan Halkası - Tereyağı, 20 g
        (35, 10, 250), -- Kumpir - Patates, 250 g
        (35, 32, 40), -- Kumpir - Kaşar, 40 g
        (36, 36, 3), -- Ayva Tatlısı - Ayva, 3 adet
        (36, 28, 50), -- Ayva Tatlısı - Şerbet, 50 ml
        (37, 13, 80), -- Revani - Sebze, 80 g
        (37, 28, 60), -- Revani - Şerbet, 60 ml
        (38, 26, 50), -- Profiterol - Şeker, 50 g
        (38, 5, 40), -- Profiterol - Çikolata, 40 g
        (39, 38, 200), -- Puding - Süt, 200 ml
        (39, 5, 30), -- Puding - Çikolata, 30 g
        (40, 4, 2), -- Güllaç - Yufka, 2 adet
        (40, 39, 20), -- Güllaç - Fıstık, 20 g
        (41, 17, 1), -- Yoğurtlu Kabak - Kabak, 1 adet
        (41, 42, 100), -- Yoğurtlu Kabak - Yoğurt, 100 g
        (42, 1, 200), -- Tavuk Şiş - Tavuk, 200 g
        (42, 20, 20), -- Tavuk Şiş - Zeytinyağı, 20 ml
        (43, 3, 100), -- Bulgur Pilavı - Bulgur, 100 g
        (43, 13, 50), -- Bulgur Pilavı - Sebze, 50 g
        (44, 31, 200), -- Kaşarlı Mantar - Mantar, 200 g
        (44, 32, 30), -- Kaşarlı Mantar - Kaşar, 30 g
        (45, 1, 300), -- Fırında Tavuk - Tavuk, 300 g
        (45, 20, 15), -- Fırında Tavuk - Zeytinyağı, 15 ml
        (46, 18, 1), -- Karnıbahar Graten - Karnabahar, 1 adet
        (46, 32, 30), -- Karnıbahar Graten - Kaşar, 30 g
        (47, 30, 150), -- Çoban Salata - Yeşillik, 150 g
        (47, 20, 10), -- Çoban Salata - Zeytinyağı, 10 ml
        (48, 41, 150), -- Tavuklu Makarna - Tavuk Göğsü, 150 g
        (48, 13, 30), -- Tavuklu Makarna - Sebze, 30 g
        (49, 2, 400), -- Kuzu İncik - Kuzu eti, 400 g
        (49, 20, 15), -- Kuzu İncik - Zeytinyağı, 15 ml
        (50, 26, 50), -- Elmalı Kurabiye - Şeker, 50 g
        (50, 12, 20); -- Elmalı Kurabiye - Fıstık, 20 g
''')

"""# sonuçları çıktı olarak gösterme
c.execute("SELECT * FROM recipes, ingredients, relation")

# Tüm sonuçları al
rows = c.fetchall()

# Sonuçları yazdır
for row in rows:
    print(row)"""

# Değişiklikleri kaydet
conn.commit()

# Bağlantıyı kapat
conn.close()