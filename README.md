# **BANKROBBER**

## Jogo

Em Bankrobber, o jogador assume o papel de um ladrão habilidoso, determinado a executar o roubo perfeito. O objetivo central é apoderar-se de um objeto valioso, que está localizado numa área protegida, repleta de obstáculos e inimigos. O jogador irá encontrar câmeras de vigilância, guardas e polícias que patrulham a área para impedir o jogador. Assim que o ladrão obtém o objeto desejado, uma porta de saída surge na sua localização inicial. A única sáida.

Para alcançar o sucesso, o jogador deve equilibrar elementos de estratégia e ação.
Os guardas e os polícias seguem atentos a qualquer movimento suspeito, enquanto as câmeras de segurança monitorizam áreas cruciais. Se o ladrão  entrar no campo de visão de um inimigo, os restantes oponentes são notificados da localização do ladrão e entram em modo de perseguição até capturá-lo.
No entanto, é possível completar o jogo sem ser visto, mas o jogador também tem a oportunidade de matar os guardas para escapar.

<p align="center">
<img src="./docs/game/level.png"/>
</p>





Controlos do jogo:



`setas ou wasd` : movimentar o jogador

`Q`: quit

`E`: entrar na porta

`Space`: disparar




## Features:

### Main Menu screen:
Tela inicial do jogo que permite ao jogador escolher entre começar o jogo, ver as instruções ou sair do jogo.

<p align="center">
<img src="./docs/game/menu.png"/>
</p>

### Win screen:
Aparece quando o jogador completa o jogo e permite-lhe escolher entre voltar ao menu ou recomeçar o jogo.

<p align="center">
<img src="./docs/game/vitoria.png"/>
</p>



### Game Over screen:
Aparece quando o jogador é apanhado e permite-lhe escolher entre voltar ao menu principal ou recomeçar o jogo.

<p align="center">
<img src="./docs/game/perder.png"/>
</p>

### Menu Instruções:
Tela que pode ser selecionada no main menu que informa o jogador dos controlos do jogo; permite ao jogador voltar ao menu principal ou começar o jogo diretamente.

<p align="center">
<img src="./docs/game/instrucoes.png"/>
</p>

### Movimentação do ladrão:
O jogador pode mover o personagem nas setas ou com wasd.

Disparo:  o jogador pode matar os guardas ao disparar com a tecla space.


Câmeras:   há dois tipos de câmeras no jogo que podem revelar a localização do ladrão: estáticas (não movem o campo de visão) e rotativas (movem o campo de visão da esquerda para a direita, olhando também para o centro).



Inimigos - Guardas e Policias:   há dois tipos de guardas que podem apanhar o ladrão e localizá-lo: guardas (apenas andam pelos cantos das salas) e polícias (movem –se em zig zag podendo entrar em salas diferentes).



Collectible System:   o jogador coleta um item ao passar por cima dele, o que faz com que a porta de saída apareça.



Escape:   após coletar o item o ladrão pode dirigir-se a uma porta para fugir da arena e completar o jogo.



Pursuit:   ao ser visto por um dos inimigos ou câmeras, os inimigos são notificados do paradeiro do ladrão e perseguem-no pelo mapa.
