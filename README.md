**_IN PROGRESS_... how to show cube images without a heavily contrasting background??**

# Introduction

Ah, the Rubik's Cube—the iconic toy that bedevils and delights in equal measure. Born from the ingenious mind of Ernő Rubik in 1974, it's more than just a puzzle. Forget mere child's play; this cube is chaos in the physical. Just kidding. To go from the state of chaos to order, one only needs to know a solving protocol of which many exist.

## Review of Rubik's Cubes

Max Park, the current world record holder (11 June 2023) for the 2-handed solve, obliterated the cube in a mind-blowing 3.13 seconds. Lucky scramble? Hardly. Yiheng Wang, who holds the world record average-of-five, clocks in at a dizzying 4.48 second mean solve time throughout the five solves[^1]. And no, I won't depress you by mentioning his tender age of nine years old. But let's not divert. The Rubik's Cube--a mathematical marvel and a plaintext[^2] waiting to be encrypted.

> ![cube and moves image](https://github.com/thondascully/rotacrypt/assets/114739901/b79645d3-140b-4958-9ef6-ccbdf340fe01)
>
> _A visualization of the R operation (rotating the right layer clockwise)._

Let's dive into the mechanics of the 3x3x3 puzzle. The cube boasts centers, edges, and corners. The single-colored center pieces serve as the invariant axis around which the peripheral smaller unit cubes rotate. The six unit colors are yellow, blue, red, green, orange, and white.

...

## Project Structure
```bash
.
├── README.md
├── setup.py
├── src
│   ├── block
│   │   ├── __init__.py
│   │   └── block.py
│   ├── configs
│   │   ├── __init__.py
│   │   └── settings.cfg
│   ├── cube
│   │   ├── __init__.py
│   │   ├── cube.py
│   │   └── rotacube.py
│   ├── key
│   │   └── __init__.py
│   ├── manager.py
│   ├── tests
│   │   ├── __init__.py
│   │   └── cube_test.py
│   ├── tree.py
│   └── utils
│       ├── __init__.py
│       └── util.py
├── temp_structure.txt
└── whitepaper
    ├── cubies
    ├── encryption
    ├── key_gen
    ├── moves
    ├── whitepaper.pdf
    └── whitepaper.tex

13 directories, 19 files
```