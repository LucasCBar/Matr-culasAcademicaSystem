# História do Usuário para o Sistema de Matrículas da Universidade

## História 1: Acesso ao Sistema de Matrículas
**Como** aluno da universidade,  
**Eu quero** acessar o sistema de matrículas,  
**Para que** eu possa inserir meu login e senha para validação e acessar minhas informações acadêmicas.

### Critérios de Aceitação:
- O aluno deve ser capaz de inserir um login e uma senha.
- O sistema deve validar as credenciais do aluno.
- Após a validação, o aluno deve ser redirecionado para a página inicial com as informações de suas disciplinas e opções de matrícula.

## História 2: Matrícula em Disciplinas
**Como** aluno validado no sistema,  
**Eu quero** selecionar e me matricular em 4 disciplinas obrigatórias e 2 disciplinas optativas,  
**Para que** eu possa garantir minha vaga nas disciplinas desejadas para o semestre.

### Critérios de Aceitação:
- O aluno deve poder visualizar a lista de disciplinas disponíveis, com a indicação de vagas disponíveis.
- O aluno deve poder selecionar até 4 disciplinas obrigatórias e 2 optativas.
- O sistema deve verificar se há vagas disponíveis nas disciplinas selecionadas.
- O sistema deve confirmar a matrícula nas disciplinas escolhidas e atualizar o número de vagas disponíveis.
- O sistema deve notificar o sistema de cobranças após a confirmação da matrícula.

## História 3: Cancelamento de Matrícula
**Como** aluno matriculado,  
**Eu quero** cancelar minha matrícula em uma disciplina,  
**Para que** eu possa ajustar minha carga horária ou escolher outra disciplina antes do fim do período de matrículas.

### Critérios de Aceitação:
- O aluno deve poder visualizar as disciplinas nas quais está matriculado.
- O aluno deve poder selecionar uma disciplina para cancelamento.
- O sistema deve remover o aluno da lista de matriculados na disciplina e atualizar o número de vagas disponíveis.
- O sistema deve notificar o sistema de cobranças sobre o cancelamento.

## História 4: Verificação da Ativação de Disciplinas
**Como** administrador do sistema,  
**Eu quero** verificar o número de alunos inscritos em cada disciplina ao final do período de matrículas,  
**Para que** eu possa cancelar disciplinas com menos de 3 alunos e informar os alunos afetados.

### Critérios de Aceitação:
- O sistema deve gerar um relatório com o número de alunos matriculados em cada disciplina.
- O sistema deve cancelar automaticamente disciplinas com menos de 3 alunos inscritos.
- O sistema deve enviar uma notificação para os alunos e professores das disciplinas canceladas.

## História 5: Acompanhamento das Matrículas por Professores
**Como** professor,  
**Eu quero** acessar a lista de alunos matriculados nas disciplinas que estou lecionando,  
**Para que** eu possa planejar minhas aulas e acompanhar o progresso dos alunos.

### Critérios de Aceitação:
- O professor deve poder visualizar as disciplinas que está lecionando.
- O professor deve poder acessar uma lista de alunos matriculados em cada disciplina.
- A lista de alunos deve incluir informações básicas como nome e número de matrícula.

## História 6: Gestão do Sistema pela Secretaria
**Como** funcionário da secretaria,  
**Eu quero** gerenciar as informações sobre disciplinas, cursos, professores e alunos,  
**Para que** eu possa garantir que o currículo e as matrículas estejam atualizados e corretos.

### Critérios de Aceitação:
- O funcionário da secretaria deve poder adicionar, editar e remover informações sobre disciplinas, cursos, professores e alunos.
- O sistema deve permitir a definição e edição dos períodos de matrícula.
- O sistema deve gerar e disponibilizar o currículo oficial do semestre.
- O sistema deve garantir que todas as alterações feitas pela secretaria sejam refletidas em tempo real.
