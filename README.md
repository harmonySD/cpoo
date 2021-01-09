# Calculatrice évoluée - Manuel

## Pourquoi cette calculatrice plutot qu'une autre?
Il ne s’agit pas d’une calculatrice simulant une calculatrice physique avec des boutons :
Cela aurait peu d’intérêt par rapport à l’ergonomie qu’on peut avoir avec un clavier et un écran d’ordinateur.
Il s’agit plutôt d’un shell, c’est-à-dire une boucle d’entrée/évaluation/affichage **(REPL : read, eval, print, loop)**.

## Comment ca marche ?

Nous utilisons la syntaxe polonaise inversée (RPN). Celle-ci fonctionne grace a une pile (LIFO) d'opérandes.
Quand l'utilisateur entre une opérande (par ex. un nombre réel), celui-ci est mis au sommet de la pile; 
quand l'utilisateur entre une opération, le nombre nécessaire d'opérande est dépilé (on prend le sommet et on l'enlève), 
puis le calcul est effectué, et enfin le résultat est empilé.

## Comment installer et exécuter notre programme ?

C'est très simple ! Il suffit d'entrer dans le terminal (attention au répertoire courant tout de meme) la commande suivante :

``` 
make
```

puis pour exécuter ...
```
java -jar ./target/projet_cpoo-1.0-SNAPSHOT.jar
```

Et voila ! Pour quitter vous pouvez soit entrer après l'invite de commande :
```
> q
```
```
> quit
```

## Extensions

Nous avons implémenté deux extensions, 
L'extension 3 consistant a ajouter des rappels (depuis la pile, depuis un historique ou depuis une variable nommée par l'utilisateur) :
Pour un rappel depuis l'historique, il faut entrer hist(x) avec x un entier compris entre -size et +size avec size la taille de l'historique
```
> hist(0)
> hist(12)
> hist(-1)
> hist(-5)
```
Pour un rappel depuis la pile, il faut entrer pile(x) avec x un entier compris entre -size et +size avec size la taille de la pile
```
> pile(-1)
> pile(-5)
> pile(8)
```
Pour un rappel depuis une variable :
Il faut d'abord entrer ![nom_variable] pour faire la sauvegarde (et etre sur que la pile ne soit pas vide !),
puis pour effectuer le rappel a proprement parler, entrer la commande ?[nom_variable] pour ravoir au sommet
de la pile le contenu de votre variable. Attention chaque nom de variable est unique et on ne peut lui
assigner une valeur qu'une seule fois (impossible de refaire !toto une fois que !toto a deja été rentré).
```
> 3
> !var1
> ?var1
> $x
> !var2
> ?var1
> ?var2
> +

```

Et on peux aussi utiliser des variables symboliques. Elles devront obligatoirement
être précédé de $ et être une unique lettre. Pour substituer une variable symbolique
il suffit d'indiquer dans une première requête la valeur qu'on veux lui assigner. 
Puis de donner le nom de la variable symbolique (ne pas oublier le $ avant). 
Et enfin de donner le mot magique subst. 
Seule les subst précédés de ces deux autres étapes seront admis. 
Comme par exemple:
```
$x
```
```
3
```
```
subst
```
Ici on substitue donc 3 a x.
(Attention : l'extension n'est pas finie puisque lorsque l'expression ne contient plus
de variables symboliques, le calcul n'est pas remplace par son resultat mais subsiste "tel quel")

# Calculatrice évoluée - Détails des fonctionnalités et architecture

## Architecture

UserInterface s'occupe de la relation entre la demande de l'utilisateur et les différentes classes.
UserInterface s'occupe de "tout coordonner" : elle fait le lien entre l'utilisateur, et ce qu'il entre,
et tout le traitement interne a la calculatrice. Plus precisément :
UserInterface donne a une ExpressionFactory la string que l'utilisateur a entré, 
l'ExpressionFactory renvoie une instance d'une classe qui implemente Expression*. 
UserInterface récupère cette instance dont on sait juste que c'est une Expression 
Apres on appelle la methode getValue de l'expression qui va tout calculer
s'il y a un traitement a faire (typiquement un opérateur ou un rappel) et met le 
résultat du calcul dans la pile et l'historique. 
Enfin UserInterface affiche le dernier élément mis dans la pile. 
Et ca c'est un tour de boucle, le programme fait ca jusqu'a ce que l'utilisateur 
demande a quitter en entrant soit 'q' soit 'quit'.

*Expression : une interface implémentée par :
RealNumber, BinaryOperatorExpr, SimpleCallback (et donc les classes qui en héritent),
SymbNumber et Substitution.

-RealNumber, renvoyé par la Factory dans le cas ou l'utilisateur a entré un nombre directement, 
c'est le cas le plus simple, getValue ne fait que mettre le nombre dans la pile et l'historique.

-BinaryOperatorExpr, renvoyé si l'utilisateur rentre un des opérateurs qu'on connait 
(on a dans une map les string que l'utilisateur doit rentré pour chaque opération
et associée a cette string, ce que fait cette opération sur deux double, 
ce qu'elle fait est stocké dans un BinaryOperator<Double>).

-HistCallback, StackCallback : fonctionnent tout deux de la meme maniere, leur traitement
est factorisé dans la classe abstraite SimpleCallback (qui implemente Expression et Callback).

-VarCallback : fonctionne grace a l'attribut set, propre a chaque instance, ainsi
qu'a une Map<String, VarCallback>, propre a chaque instance de CallbackFactory, qui associe a chaque instance de VarCallback
son nom (!toto a comme nom de variable de rappel toto) afin de pouvoir lire leur contenu
a tout moment.

Pour les substitutions, on réécrit la méthode getValue de Expression et au lieu d'ajouter une nouvelle expression dans la pile et l'historique, on remplace sa valeur dans l'expression la plus recente contenant la variable symbolique. Et empile cette nouvelle expression dans la pile.



