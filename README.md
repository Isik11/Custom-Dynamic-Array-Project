# Dynamic Grid (CS310 Project 1 — Generics and Lists)

A Java implementation of a fully dynamic 2D grid data structure using generics.  
This project demonstrates fundamental data-structure design without using Java’s built-in collections (no ArrayList, LinkedList, HashSet, etc.) and features a GUI visualization for color mixing.

---

## Overview 
The **Dynamic Grid** is a generic two-dimensional grid implemented from scratch using resizable arrays.  
Each cell `grid[i][j]` is computed as a binary operation:

---

## Key Features
- Fully generic - works with any type of combination
- Dynamically resizing arrays (grow x 2, shrink / 2)
- Modular design with interfaces and parameterized types
- Big-O complexity constraints enforced
- JavaFX GUI for color-mixing demonstration

---

## Run Instructions


## Compile (from CLI)
```bash
javac *.java

## Example Output
============================
Table
Operation: class StringAdder
Size: 3 rows, 3 cols

| apple | kiwi | banana
red   | red apple   | red kiwi   | red banana
green | green apple | green kiwi | green banana
============================
```
---

## Concepts applied
- Java Generics
- Two-dimensional Data Structures
- Interface Implementation
- JavaFX GUI Programming

---

## Project Structure
p1/
│
├── yourCodeHere/ # All project source code
│ ├── Combiner.java # Interface for combining two generic values
│ ├── DynamicArray.java # Generic resizable array implementation
│ ├── DynamicGrid.java # Two-dimensional dynamic grid built on DynamicArray
│ ├── IntegerComb.java # Combiner for integer addition
│ ├── StringAdder.java # Combiner for string concatenation
│ ├── StringTimer.java # Utility for time-based string operations
│ ├── SubstringCounter.java # Counts substring occurrences in strings
│ ├── Table.java # Displays grid structure and operations
│ │
│ └── GUI/ # JavaFX visual interface
│ ├── ColorComb.java # Combines colors dynamically
│ ├── ColorGB.java / ColorRB.java / ColorRG.java # Color mixing implementations
│ ├── ColorSquare.java # Visual color cell representation
│ ├── GUI.java # Main GUI controller
│ └── RotatedJButton.java # Custom rotated button component
│
├── project1-description.pdf # Original project prompt
├── project1-rubric.pdf # Grading criteria
├── checkstyle.jar # Code style enforcement tool
├── cs310code.xml, cs310comments.xml # Submission metadata
├── junit-4.11.jar # Testing library
└── .gitignore # Files to ignore in Git

--

## Author
**Ismoil Aknazarov**

