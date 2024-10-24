# Java Text Adventure

This is a simple text-based adventure game written in Java. The player can explore different rooms, interact with objects and characters, and solve puzzles to progress through the game.

## Getting Started

To run the project, follow these steps:

1. Clone the repository:
```
git clone https://github.com/ToloCops/Java-Text-Adventure.git
```

2. Navigate to the project directory:
```
cd Java-Text-Adventure
```

3. Compile the project:
```
javac -d bin src/it/uniroma1/textadv/*.java src/it/uniroma1/textadv/elementi/*.java src/it/uniroma1/textadv/elementi/links/*.java src/it/uniroma1/textadv/elementi/oggetti/*.java src/it/uniroma1/textadv/elementi/personaggi/*.java src/it/uniroma1/textadv/eccezioni/*.java src/it/uniroma1/textadv/interfacce/*.java
```

4. Run the project:
```
java -cp bin it.uniroma1.textadv.Test
```

## Usage

Here are some examples of how to play the game:

- To look around the current room, type:
```
guarda
```

- To move to a different room, type:
```
vai [direction]
```
Replace `[direction]` with `nord`, `sud`, `est`, or `ovest`.

- To interact with an object, type:
```
usa [object]
```
Replace `[object]` with the name of the object you want to interact with.

- To pick up an object, type:
```
prendi [object]
```
Replace `[object]` with the name of the object you want to pick up.

- To check your inventory, type:
```
inventario
```


