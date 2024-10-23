import sqlite3

conn = sqlite3.connect('recipes.db')

# Bir imleç oluştur
c = conn.cursor()

# Tablo oluşturma sorgusu
"""c.execute('''
CREATE TABLE recipes(
    RecipeID INT NOT NULL PRIMARY KEY,
    RecipeName VARCHAR(30) NOT NULL,
    Category VARCHAR(30) NOT NULL,
    PreparationTime INT NOT NULL,
    Instruction TEXT NOT NULL,
    NumberPoints INT NOT NULL,
    TotalPoints REAL NOT NULL
);
''')"""

"""c.execute('''
INSERT INTO recipes VALUES
    (1, 'Tavuk Tandır', 'Ana Yemek', 60, 'Tavukları marine edip, tandırda pişirin.', 12, 102.0),
    (2, 'Kuzu tandır', 'Ana Yemek', 120, 'Kuzu etini baharatlarla marine edip, fırında ağır ateşte pişirin.', 9, 81.0),
    (3, 'Sebzeli kısır', 'Ana Yemek', 30, 'Bulguru sıcak suyla ıslatıp, sebzeleri ekleyin ve karıştırın.', 26, 182.0),
    (4, 'Baklava', 'Tatlı', 90, 'Yufkaları üst üste koyup, fıstıkla aralarına serpin ve fırında pişirin.', 19, 190.0),
    (5, 'Çikolatalı mousse', 'Tatlı', 20, 'Çikolata ve krema ile karıştırıp, buzdolabında bekletin.', 7, 56.0),
    (6, 'Sütlaç', 'Tatlı', 40, 'Pirinci pişirip, süt ve şeker ekleyin ve karıştırın.', 14, 105.0),
    (7, 'Humus', 'Meze', 15, 'Nohut, tahin, limon ve sarımsak ile karıştırın.', 5, 40.0),
    (8, 'Haydari', 'Meze', 10, 'Yoğurt, sarımsak ve nane ile karıştırın.', 4, 26.5),
    (9, 'Cacık', 'Meze', 10, 'Yoğurt, salatalık ve sarımsak ile karıştırın.', 17, 144.5),
    (10, 'Patates Kızartması', 'Atıştırmalık', 20, 'Patatesleri dilimleyip, kızartın.', 22, 165.0),
    (11, 'Popcorn', 'Atıştırmalık', 10, 'Mısır tanelerini patlatın.', 9, 45.0),
    (12, 'Fıstık Ezmesi', 'Atıştırmalık', 15, 'Kavrulmuş fıstıkları ezip, krem haline getirin.', 3, 18.0),
    (13, 'Çorba', 'Başlangıç', 30, 'Sebzeleri haşlayıp, püre haline getirin.', 16, 112.0),
    (14, 'Zeytinyağlı Enginar', 'Başlangıç', 40, 'Enginarları haşlayıp, zeytinyağı ile servis yapın.', 5, 42.5),
    (15, 'Tarator', 'Başlangıç', 15, 'Yoğurt, ceviz ve ekmek içi ile karıştırın.', 3, 18.0);
''')"""


c.execute('''
CREATE TABLE IF NOT EXISTS ingredients(
    IngredientID INT NOT NULL PRIMARY KEY,
    IngredientName VARCHAR(30) NOT NULL,
    Quantity VARCHAR(30) NOT NULL,
    Unit VARCHAR(30) NOT NULL,
    UnitPrice INT NOT NULL
);
''')

c.execute('''
INSERT INTO ingredients VALUES
    (1, 'Tavuk', '2', 'kg', 40),
    (2, 'Kuzu eti', '1.5', 'kg', 60),
    (3, 'Bulgur', '500', 'g', 5),
    (4, 'Yufka', '5', 'adet', 15),
    (5, 'Çikolata', '200', 'g', 12),
    (6, 'Pirinc', '200', 'g', 4),
    (7, 'Nohut', '300', 'g', 10),
    (8, 'Yoğurt', '500', 'g', 8),
    (9, 'Salatalık', '3', 'adet', 2),
    (10, 'Patates', '1', 'kg', 6),
    (11, 'Mısır', '200', 'g', 3),
    (12, 'Fıstık', '250', 'g', 25),
    (13, 'Sebze', '500', 'g', 7),
    (14, 'Enginar', '4', 'adet', 20),
    (15, 'Ceviz', '100', 'g', 18);
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
''')

# sonuçları çıktı olarak gösterme
"""c.execute("SELECT * FROM recipes")

# Tüm sonuçları al
rows = c.fetchall()

# Sonuçları yazdır
for row in rows:
    print(row)"""

# Değişiklikleri kaydet
"""conn.commit()"""

# Bağlantıyı kapat
conn.close()