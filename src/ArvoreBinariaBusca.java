public class ArvoreBinariaBusca {
    private NoABB raiz;

    public void inserir(int valor) {
        raiz = inserir(raiz,valor);
    }
    private NoABB inserir(NoABB atual, int valor) {
        if (atual == null) return new NoABB(valor); // caso base
        if (valor < atual.valor) {
            atual.esquerda = inserir(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserir(atual.direita, valor);
        }
        return atual;
    }

    public void desenhar() {
        desenhar(raiz, 0);
    }
    private void desenhar(NoABB atual, int nivel) {
        if (atual == null) return;
        desenhar(atual.direita, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }
        System.out.println(atual.valor);
        desenhar(atual.esquerda, nivel + 1);
    }

    public void busca(int valor){
        if(busca(raiz, valor)){
            System.out.printf("\nO valor %d existe na árvore",valor);
        }else{
            System.out.printf("\nO valor %d não existe na árvore",valor);
        }
    }
    private boolean busca(NoABB atual, int valor){
        if(atual == null){
            return false;
        }
        if(atual.valor == valor){
            return true;
        }
        if(valor < atual.valor){
            return busca(atual.esquerda, valor);
        }else{
            return busca(atual.direita,valor);
        }
    }

    public void contarNos(){
        System.out.printf("\nA quantidade de nós da árvore é: %d", contarNos(raiz));
    }
    private int contarNos(NoABB atual){
        if (atual == null) return 0;
        return 1 + contarNos(atual.esquerda) + contarNos(atual.direita);
    }

    public void calcAlt(){
        System.out.printf("\nA altura da árvore é: %d", calcAlt(raiz));
    }
    private int calcAlt(NoABB atual){
        if(atual == null) return -1;
        int esq = calcAlt(atual.esquerda);
        int dir = calcAlt(atual.direita);
        return 1 + Math.max(esq, dir);
    }

    //atividades da lista 2
    public void contarNoIntervalo(int min, int max) {
        System.out.printf("A quantidade de nós entre %d e %d é: %d",
        min, max, contarNoIntervalo(raiz, min, max));
    }
    private int contarNoIntervalo(NoABB atual, int min, int max) {
        if(atual == null) return 0;
        //se o valor é menor que o mínimo, procurar valores maiores (a direita)
        if(atual.valor < min) return contarNoIntervalo(atual.direita, min, max);
        // se o valor é maior que o máximo, procurar valores menores (a esquerda)
        if(atual.valor > max) return contarNoIntervalo(atual.esquerda, min, max);
        // o caso apenas chega aqui caso esteja dentro do intervalo
        return 1 +
                contarNoIntervalo(atual.esquerda, min, max) +
                contarNoIntervalo(atual.direita, min, max);
    }


    public void mesmosValores(ArvoreBinariaBusca arvore2){
        int n1 = contarNos(raiz);
        int n2 = contarNos(arvore2.raiz);
        int[] r1 = new int[n1];
        int[] r2 = new int[n2];
        //chama o método recursivo que insere as árvores em-ordem nos vetores
        mesmosValores(raiz, r1, 0); mesmosValores(arvore2.raiz, r2, 0);

        //compara os vetores
        boolean igual = true;
        if(n1 != n2){
            igual = false;
        }
        if(igual){
            for (int i = 0; i < r1.length; i++) {
                if (r1[i] != r2[i]) {
                    igual = false;
                    break;
                }
            }
        }
        //imprime o resultado
        if(igual){
            System.out.println("As árvores possuem os mesmos valores");
        }else{
            System.out.println("As árvores não possuem os mesmos valores");
        }
    }
    private int mesmosValores(NoABB raiz, int[] vetor , int index){
        //método recursivo que insere a árvore em um vetor
        if(raiz == null) return index;
        index = mesmosValores(raiz.esquerda, vetor, index);

        vetor[index] = raiz.valor;
        index++;

        index = mesmosValores(raiz.direita, vetor, index);

        return index;
    }

    public void construirBalanceada(int[] valores) {
        raiz = construirBalanceada(valores, 0, valores.length - 1);
    }
    private NoABB construirBalanceada(int[] valores, int inicio, int fim) {
        //caso base
        if(inicio > fim) return null;
        //obtendo o meio do vetor
        int meio = (inicio + fim)/2;
        //adicionando o meio do vetor como raiz da subárvore
        NoABB novo = new NoABB(valores[meio]);
        //método recursivo
        novo.esquerda = construirBalanceada(valores, inicio, meio -1);
        novo.direita = construirBalanceada(valores, meio +1, fim);
        return novo;
    }

    public void nivelValor(int valor){
        int nivel = nivelValor(raiz, valor);
        if(nivel != -1){
            System.out.printf("\nO valor %d foi encontrado no nível %d",valor, nivel);
        }else{
            System.out.printf("\nO valor %d não foi encontrado na árvore",valor);
        }
    }
    private int nivelValor(NoABB atual, int valor){
        //caso base
        if(atual == null){
            return -1;
        }
        //caso encontrou o valor
        if(atual.valor == valor){
            return 0;
        }
        //caso o valor seja menor
        if(valor < atual.valor){
            int nivel = nivelValor(atual.esquerda, valor);
            //se chegou num null sem encontrar o valor então retorna -1
            if(nivel == -1){
                return -1;
            }
            //se não retorna o +1
            return 1 + nivel;
        //caso o valor seja maior
        }else{
            int nivel = nivelValor(atual.direita, valor);
            //se chegou num null sem encontrar o valor então retorna -1
            if(nivel == -1){
                return -1;
            }
            //se não retorna +1
            return 1 + nivel;
        }
    }
}