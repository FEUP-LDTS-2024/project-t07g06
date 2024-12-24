Bankrobber é um jogo 2D do gênero shoot'em up e estratégia onde o personagem principal, o ladrão, tem como objetivo roubar um objeto valioso.

Para conseguir completar o jogo, o jogador tem de apanhar o objeto e sair pela porta que aparece mal apanha o tal objeto.

Há vários guardas e câmeras espalhados pelo mapa que irão detetar o ladrão mal este entre no seu campo de visão, lançando um estado de alerta para notificar todos os guardas. Quando é lançado o estado de alerta, os guardas começam a perseguir o ladrão pelo mapa na tentativa de o apanhar.

É possível completar o jogo sem ser visto, mas o jogador também tem a oportunidade de matar os guardas para escapar caso seja detetado.





Controlos do jogo:



Direções: setas ou wasd

Q: quit

E: entrar na porta

Space: disparar







Features:

Main Menu screen:  tela inicial do jogo que permite ao jogador escolher entre começar o jogo, ver as instruções ou sair do jogo.



Win screen:  aparece quando o jogador completa o jogo e permite-lhe escolher entre voltar ao menu ou recomeçar o jogo.





Game Over screen:  aparece quando o jogador é apanhado e permite-lhe escolher entre voltar ao menu principal ou recomeçar o jogo.



Menu Instruções:   tela que pode ser selecionada no main menu que informa o jogador dos controlos do jogo; permite ao jogador voltar ao menu principal ou começar o jogo diretamente.



Movimentação do ladrãof:  o jogador pode mover o personagem nas setas ou com wasd.



Disparo:  o jogador pode matar os guardas ao disparar com a tecla space.



Câmeras:   há dois tipos de câmeras no jogo que podem revelar a localização do ladrão: estáticas (não movem o campo de visão) e rotativas (movem o campo de visão da esquerda para a direita, olhando também para o centro).



Inimigos - Guardas e Policias:   há dois tipos de guardas que podem apanhar o ladrão e localizá-lo: guardas (apenas andam pelos cantos das salas) e polícias (movem –se em zig zag podendo entrar em salas diferentes).



Collectible System:   o jogador coleta um item ao passar por cima dele, o que faz com que a porta de saída apareça.



Escape:   após coletar o item o ladrão pode dirigir-se a uma porta para fugir da arena e completar o jogo.



Pursuit:   ao ser visto por um dos inimigos ou câmeras, os inimigos são notificados do paradeiro do ladrão e perseguem-no pelo mapa.



DrawImage:   classe que desenha imagens no jogo pixel a pixel, para cada elemento do mapa.



Explicação de algumas features

Para alcançar o efeito de visão em cone nós damos um valor de sight, que é o valor máximo que queremos que o elemento veja para a frente. A partir daí iniciamos o i de forma a percorrer as posições para a frente uma a uma.

Dentro desse loop iniciámos também uma variável j que vai fazer com que seja possível ver para os lados e para a frente.

A fórmula da visão em cone fica então j=2i+1







Para evirar que a visão em cone veja para lá das paredes, verificamos se o j está no meio ou não, se estiver no meio, apenas não se consegue ver o que estiver atrás da parede, mas se tiver num dos lados(esquerda/direita ou baixo/cima) deixa de conseguir ver para lá do j.







Para as câmeras ele vai definir o canto onde se encontra. Ao ver quais são as paredes que o rodeiam passa a saber as posições onde consegue ver (onde não tem parede). Depois de definido o canto em que a câmera se encontra, percorre-se o vetor corner que contem as direções para onde a câmera aponta. A posição do meio (diagonal) é a junção das duas outras posições (cima-2 esquerda-1, diagonal-12). Com a flag de isDown verifica-se se  está a subir ou a descer ao percorrer a lista, definindo assim a direção e visão naquele momento.































------------------------------------------------Uml -----------------------------------------------



Design Patterns ------------------------------------------------------------------------------



---MVC(model-viewer-controller)---

Utilizámos este padrão como maneira de organizar o código e separar as responsabilidades de cada componente.

O Modelo trata dos dados, como, por exemplo, a posição do ladrão. O Viewer é responsável pela exibição do jogo a quem joga. Já o Controller processa o input dado pelo jogador, servindo de intermediário entre o jogador e o modelo.

É benéfico usar este padrão pois separa as responsabilidades, fazendo com que cada parte do jogo seja independente e, por isso mesmo, facilita imenso a mudança de cada parte sem que as outras partes sejam muito prejudicadas.



---State Pattern---

Para lidar com os diferentes estados em que o jogo se pode encontrar (Game state, GameOver state, etc) usámos o state design pattern para podermos alterar o comportamento do jogo de acordo com o estado atual sem precisar usar if/else ou switches. O objeto principal mantém uma referência ao estado atual e atualiza o comportamento do jogo dependendo do estado em que se encontra. Cada um dos possíveis estados encontram-se em classes separadas que definem comportamentos específicos de cada estado.

A utilização deste padrão é benéfica pois permite não só torna o código mais limpo como também promove uma boa organização e extensibilidade.



---Factory Method---

Para incorporar diferentes sons no jogo decidimos usar um sound factory para apenas termos de recorrer a uma única classe (SoundFactory) para criar e gerenciar os sons.

Esta classe contém toda a lógica para carregar e criar diferentes sons o que permite evitar a duplicação de código ao instanciar sons.

A fábrica possui vários métodos como, por exemplo, createBackgroundSound(), createFootStepSound(), etc...que fornecem sons específicos como instâncias prontas de objetos LoopedSoundPlayer ou objetos InstanceSound para uso das classes Game e Thief. As classes que utilizam estes sons não precisam de saber como os sons são carregados/configurados, apenas usam a instância que a classe fornece.

O uso deste padrão promove uma boa organização do código pois centraliza a criação de sons. Para além disso, torna mais fácil acrescentar novos sons pois isso apenas exige alguns ajustes na fábrica.











Code smells

Duplicate code: em guard e cop controler ambos partilham um método pursuit ( método de perseguir o ladrão). Poderíamos criar uma interface por exemplo para deixar de haver este método duplicado mas para legibilidade e para facilitar a interpretação do código optamos por não o fazer mantendo assim em cada controler um pursuit.

Bugs no desenho dos guardas/cops: por algum motivo que não conseguimos descobrir, apenas o 1o guarda e o 1o polícia é que são desenhados corretamente (quando se movem o desenho do personagem vira-se na direção do movimento) e os seguintes ficam congelados na imagem inicial embora se movam.







Test coverage

Unit testing e mocks:









Pitest:



- Mutators

================================================================================

> org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator

>> Generated 61 Killed 11 (18%)

> KILLED 11 SURVIVED 9 TIMED_OUT 0 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 41

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.returns.PrimitiveReturnsMutator

>> Generated 19 Killed 9 (47%)

> KILLED 9 SURVIVED 3 TIMED_OUT 0 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 7

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.IncrementsMutator

>> Generated 11 Killed 3 (27%)

> KILLED 1 SURVIVED 2 TIMED_OUT 1 NON_VIABLE 0

> MEMORY_ERROR 1 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 6

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator

>> Generated 285 Killed 57 (20%)

> KILLED 57 SURVIVED 60 TIMED_OUT 0 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 168

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.returns.BooleanTrueReturnValsMutator

>> Generated 27 Killed 18 (67%)

> KILLED 18 SURVIVED 5 TIMED_OUT 0 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 4

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.InvertNegsMutator

>> Generated 2 Killed 1 (50%)

> KILLED 1 SURVIVED 1 TIMED_OUT 0 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 0

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.returns.NullReturnValsMutator

>> Generated 51 Killed 31 (61%)

> KILLED 31 SURVIVED 5 TIMED_OUT 0 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 15

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.MathMutator

>> Generated 93 Killed 37 (40%)

> KILLED 37 SURVIVED 16 TIMED_OUT 0 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 40

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.returns.EmptyObjectReturnValsMutator

>> Generated 50 Killed 35 (70%)

> KILLED 35 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 15

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.returns.BooleanFalseReturnValsMutator

>> Generated 22 Killed 18 (82%)

> KILLED 18 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 4

-------------------------------------------------------------------------------- 

> org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator

>> Generated 196 Killed 77 (39%)

> KILLED 75 SURVIVED 23 TIMED_OUT 2 NON_VIABLE 0

> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0

> NO_COVERAGE 96

-------------------------------------------------------------------------------- 

================================================================================

- Timings

================================================================================

> pre-scan for mutations : < 1 second

> scan classpath : < 1 second

> coverage and dependency analysis : 3 seconds

> build mutation tests : < 1 second

> run mutation analysis : 1 minutes and 43 seconds

-------------------------------------------------------------------------------- 

> Total  : 1 minutes and 47 seconds

-------------------------------------------------------------------------------- 

================================================================================

- Statistics

================================================================================

>> Line Coverage (for mutated classes only): 704/1257 (56%)

>> Generated 817 mutations Killed 297 (36%)

>> Mutations with no coverage 396. Test strength 71%

>> Ran 806 tests (0.99 tests per mutation) 

 

 

 

 

 

 

 

 

 

 