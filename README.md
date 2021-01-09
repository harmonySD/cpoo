# Advanced calculator

## Why this calculator instead of another?
It is not a calculator simulating a physical calculator with buttons: that would be of little interest compared to the ergonomics that one can have with a keyboard and a computer screen. Rather it is a shell, wich *read, eval, print and loop (REPL)*.

## How does it work?

The REPL process use the reverse polish notation. It works thanks to a stack (LIFO) of operands, powered by successive user inputs, when you enter an operand (i.e. usually a number), it is added to the top of the stack; when we enter an operation, the operands in necessary quantity are unstacked (taken out of the top of the stack), the computation is carried out, then the result is stacked.

## How is it supposed to be used?

It is really simple! Just use the following command :

``` 
make
```

then ...
```
java -jar ./target/projet_cpoo-1.0-SNAPSHOT.jar
```

and now enter some operands!

## Extensions
We had two extensions, firstly you can recall some result from history or stack at any times like this :
```
hist(0)
```
or 
```
pile(-1)
```
And you can use symbolic numbers if you want and substitutes ! 
```
$x
```
```
3
```
```
subst
```
here x is changed in 3.
(attention when the expression only contains real numbers the calcul is not opreate)

## Architecture

UserInterface s'occupe de la relation entre la demande de l'utilisateur et les différentes classes.
Une interface Expression implementée par RealNumber, SymbNumber, BynaryOperatorExpre, HistCalllBack



