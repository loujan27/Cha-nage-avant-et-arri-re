# Cha-nage-avant-et-arri-re; 
Dans ce projet, nous avons construire une petite application a qui, on fournit
une base de connaissance (base de faits + base de règles) et qui enrichit la base
de faits avec d'autre faits déductibles en utilisant la base de règles.
Notre système expert fonctionne en chaînage avant et arrière.
Le projet se compose de 4 classes :
Base de règles « BaseDeRegles » :
Cette classe permet de lire la base de règles à partir d’un fichier texte et
modéliser comme suit : Règle (Xi) : Si (Liste des prémisses), ALORS (Liste des
actions).
Nous avons représenté l’ensemble des règles d’inférence en utilisant une liste
de liste d’entiers, tel que, chaque liste contient une règle.
Toutes les règles sont de la forme : P1 et P2 …et …Pn alors C
Les règles utilisées par défauts sont :
SI B et D et E alors F
SI G et D alors A
SI C et F alors A
SI C alors D
SI D alors E
SI X et A alors H
SI B alors X
SI X et C alors A
SI X et B alors D
Base de faits « BaseDeFaits » :
Cette classe permet de lire les faits initiaux ce qui ont vrais pour la base de faits
du notre système et aussi utiliser pour afficher tous les faits que le système
expert a pu inférer pour chaque itération.
Moteur d'inférence « MoteurInference » :
Elle est utilisée pour le principe de notre travaille c’est-à-dire donnée la
nouvelle base de faits après l'application de toutes les règles applicables du
système (chaînage avant).
Elle peut aussi démontrer un fait donnée en entrée, sinon dire que c'est
impossible (chaînage arrière).
-Pour le chaînage avant (Idée de l’implémentation) :
On commence par parcourir la liste des listes d’entiers en explorant les
prémisses de chacune des règles.
Si toutes les prémisses d’une règle donnée existent dans la base de faits alors la
conclusion de cette dernière règle est ajoutée dans la base de faits et la règle
serait supprimée.
Sinon, on continue le parcours de la grande liste jusqu’en trouver une, dont,
toute la partie prémisse est vraie.
Le parcours s’arrête quand aucune règle n’existe dans la base des règles, c’està-dire toutes les connaissances qui seront utilisées (lues à partir du fichier) ont
été consommées.
-Pour le chaînage arrière (Idée de l’implémentation) :
Basé sur la même structure utilisée pour le chainage avant.
Commençant par trouver les règles qui ont pour conclusion le but à démontrer.
Les prémisses de ces dernières sont sauvegardées et considérées comme des
sous-buts qu’il faut prouver pour arriver à démonter le fait initial.
Et le processus se répète récursivement, et s’arrête quand tous les buts ont été
prouvés, ou bien, il existerait des sous-buts non-démontrables en utilisant la
base de règle actuelle pour les démontrés.
Main « Main» :
Elle est le point d’entrée du notre application, afin de lancer le programme
cette classe doit être présente, elle est utilisée pour suivre les étapes à afficher
dans la console, et pour comprendre mieux le fonctionnement de l’application.
Dans la base de faits, il vaut mieux d’introduire toujours des faits initiaux pour
le bon fonctionnement de cet exemple, et pour quitter il faut juste appuyer sur
‘n’.
En fin : il vaut mieux de lire les quelques commentaires dans les différentes
classes JAVA pour bien comprendre.
