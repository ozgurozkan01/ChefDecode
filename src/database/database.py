import sqlite3

conn = sqlite3.connect('/Users/ubeydgur/Projects/Java/ChefDecode/src/database/recipes.db')

c = conn.cursor()

"""# Tablo oluşturma sorgusu
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
    StockQuantity VARCHAR(30),
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
''')


c.execute('''
INSERT INTO ingredients VALUES
    (1, 'Tavuk', NULL, 'kg', 40),
    (2, 'Kuzu eti', NULL, 'kg', 60),
    (3, 'Bulgur', NULL, 'g', 5),
    (4, 'Yufka', NULL, 'adet', 15),
    (5, 'Çikolata', NULL, 'g', 12),
    (6, 'Pirinc', NULL, 'g', 4),
    (7, 'Nohut', NULL, 'g', 10),
    (8, 'Yoğurt', NULL, 'g', 8),
    (9, 'Salatalık', NULL, 'adet', 2),
    (10, 'Patates', NULL, 'kg', 6),
    (11, 'Mısır', NULL, 'g', 3),
    (12, 'Fıstık', NULL, 'g', 25),
    (13, 'Sebze', NULL, 'g', 7),
    (14, 'Enginar', NULL, 'adet', 20),
    (15, 'Ceviz', NULL, 'g', 18);
''')


c.execute('''
INSERT INTO relation VALUES
    (1, 1, 2.0),  -- Tavuk Tandır için 2 kg tavuk
    (2, 2, 1.5),  -- Kuzu Tandır için 1.5 kg kuzu eti
    (3, 3, 500.0), -- Sebzeli kısır için 500 g bulgur
    (4, 4, 5),  -- Baklava için 5 adet yufka
    (5, 5, 200.0), -- Çikolatalı mousse için 200 g çikolata
    (6, 6, 200.0), -- Sütlaç için 200 g pirinç
    (7, 7, 300.0), -- Humus için 300 g nohut
    (8, 8, 500.0), -- Haydari için 500 g yoğurt
    (9, 9, 3.0),  -- Cacık için 3 adet salatalık
    (10, 10, 1.0), -- Patates kızartması için 1 kg patates
    (11, 11, 200.0), -- Popcorn için 200 g mısır
    (12, 12, 250.0), -- Fıstık Ezmesi için 250 g fıstık
    (13, 13, 500.0), -- Çorba için 500 g sebze
    (14, 14, 4), -- Zeytinyağlı Enginar için 4 adet enginar
    (15, 15, 100.0); -- Tarator için 100 g ceviz
''')"""

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