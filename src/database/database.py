import sqlite3

# Veritabanına bağlan (varsa aç, yoksa oluştur)
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