# ChefDecode

ChefDecode is a hybrid Java-Python application for managing and decoding recipe data. It integrates a Java-based GUI application with Python-powered database handling to create a smart and efficient food-related data processing tool.

## 🍽️ Features

- 🧠 Smart recipe decoding and management  
- 🗂️ Integrated SQLite databases (`rcp.db`, `recipes.db`)  
- 💡 Java-based UI powered by JavaFX or Swing  
- 🐍 Backend support via Python for flexible database operations  
- 🛠️ Built with Maven for easy dependency management

## 💻 Technologies Used

- Java 17+  
- Python 3  
- SQLite  
- Maven  
- (Possibly) JavaFX or Swing for UI

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/ChefDecode.git
cd ChefDecode
```

### 2. Build and run the Java application

```bash
./mvnw clean install
./mvnw javafx:run  # or your IDE's run button
```

> Make sure you have Java and Maven installed.

## 📁 Project Structure

```
ChefDecode/
│
├── src/
│   ├── main/java/com/example/chefdecode/    # Java app source code
│   └── database/                            # Python scripts and .db files
├── .mvn/                                    # Maven wrapper
├── pom.xml                                  # Maven project config
└── README.md
```

## 📄 License

This project is licensed under the MIT License.

## 🙌 Acknowledgements

Crafted as a cross-language experiment for smart recipe decoding using both Java and Python ecosystems.
