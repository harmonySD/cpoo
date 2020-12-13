# Advanced calculator

## Why this calculator instead of another?
It is not a calculator simulating a physical calculator with buttons: that would be of little interest compared to the ergonomics that one can have with a keyboard and a computer screen. Rather it is a shell, wich *read, eval, print and loop (REPL)*.

## How does it work?

The REPL process use the reverse polish notation. It works thanks to a stack (LIFO) of operands, powered by successive user inputs, when you enter an operand (i.e. usually a number), it is added to the top of the stack; when we enter an operation, the operands in necessary quantity are unstacked (taken out of the top of the stack), the computation is carried out, then the result is stacked.

## How it is supposed to be used?

It is really simple! Just use the following command :
``` 
javac UserInterface.java
```

then ...
```
java UserInterface 
```

and now enter some operands!

