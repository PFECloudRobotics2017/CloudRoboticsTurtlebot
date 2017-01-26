# CloudRoboticsTurtlebot
*Master 2 Student project.*

Ce pojet est destiné à être utilisé sur le PC qui se connecte sur un Turtlebot. Il permet simplement de déplacer un turtlebot via connexion SSH.

## Prérequis

- Un turtlebot 2
- Un laptop connecté sur ce turtlebot 2 (de préférence sur Ubuntu 14.04)
- Un PC
- Un réseau commun au laptop et au pc

## Installation

Le laptop doit être installé sur le Turtlebot. ROS Indigo doit être installé sur cette machine. Pour cela il faut suivre scrupuleusement ce tutorial : http://wiki.ros.org/Robots/TurtleBot

### Installations sur le laptop

Résumé étapes par étapes pour l'installation de ROS Indigo. Pour toutes informations supplémentaires, suivre les tutoriaux ci-dessus.

Commencez par initialiser les sources :

    sudo sh -c 'echo "deb http://packages.ros.org/ros/ubuntu $(lsb_release -sc) main" > /etc/apt/sources.list.d/ros-latest.list'
    
Initialiser les clés :

    sudo apt-key adv --keyserver hkp://ha.pool.sks-keyservers.net --recv-key 421C365BD9FF1F717815A3895523BAEEB01FA116
    
Mettre à jour :

    sudo apt-get update

Installez ROS Indigo :

    sudo apt-get install ros-indigo-desktop-full

Initialisez rosdep :

    sudo rosdep init
    rosdep update
    
Mettez à jour votre environnement :

    echo "source /opt/ros/indigo/setup.bash" >> ~/.bashrc
    source ~/.bashrc
    
Installez rosinstall :

    sudo apt-get install python-rosinstall
    
Installez ssh :

    sudo apt-get install openssh-server
    
## Préparation du laptop pour recevoir la connexion du programme :

Avant toute chose, il faut vérifier que le Turtlebot ait son adresse IP bien configurée dans le bashrc. Pour ce faire, il vous faut éditer le bashrc. Vous pouvez utiliser nano ou votre éditeur favoris.

    sudo nano ~/.bashrc
    
Vous trouverez les deux lignes suivantes, généralement vers la fin de ce fichier :

    export ROS_MASTER_URI=http://xxx.xxx.xxx.xxx:11311
    export ROS_HOSTNAME=xxx.xxx.xxx.xxx
    
Remplacez l'adresse IP (xxx.xxx.xxx.xxx) par celle de votre Turtlebot. Après cette étape il est important de redémarrer le terminal.

La base Kobuki doit être physiquement allumée et les câbles reliés au Laptop. Démarrez ensuite deux terminaux. Lancer la commande suivante sur le premier terminal :

    roscore
    
Une fois *roscore* démarré, lancez la commande suivante :

    roslaunch turtlebot_bringup minimal.launch --screen
    
La suite se passe sur le PC de commandes.

## Le programme

CloudRoboticsTurtlebot est une API pour communiquer avec un turtlebot préparé. Voici un exemple de programme déplacer simplement le turtlebot :

	TurtlebotControl tbc = new TurtlebotControl(hostname, password, ipaddress);
  	tbc.connect();
	tbc.move_backward(2000);
	tbc.turnLeft(2000);
	tbc.move_forward(2000);
	tbc.disconnect();
