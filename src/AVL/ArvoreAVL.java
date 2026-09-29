package AVL;

public class ArvoreAVL {
    private NoAVL raiz;

    private int altura(NoAVL no) {
        return (no == null) ? -1: no.altura;
    }
    private int fatorDeBalanceamento(NoAVL no) {
        return altura(no.direita) - altura(no.esquerda);
    }
    private void atualizarAltura(NoAVL no){
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    private int contarNos(NoAVL noAVL) {
        if(noAVL == null) return 0;
        return 1 + contarNos(noAVL.esquerda) + contarNos(noAVL.direita);
    }

    public void inserirVetor(int[] chaves){
        for(int i = 0; i < chaves.length; i++){
            inserir(chaves[i]);
        }
    }
    public void inserir(int chave) {
        raiz = inserir(raiz, chave);
    }
    private NoAVL inserir(NoAVL atual, int valor) {
        // caso base
        if (atual == null) return new NoAVL(valor);
        //posicionando o valor
        if (valor < atual.chave) {
            atual.esquerda = inserir(atual.esquerda, valor);
        } else if (valor > atual.chave) {
            atual.direita = inserir(atual.direita, valor);
        } else{
            atual.repeticoes++; //incremento do contador de repetições
            return atual;
        }

        atualizarAltura(atual);
        int fb = fatorDeBalanceamento(atual);
        //balanceando a AVL
        if(fb < -1){
            if (fatorDeBalanceamento(atual.esquerda) <= 0){
                return rotacaoDireita(atual); //LL
            } else{
                return rotacaoEsquerdaDireita(atual); //LR
                }
        }
        if(fb > 1) {
            if (fatorDeBalanceamento(atual.direita) >= 0) {
                return rotacaoEsquerda(atual); //RR
            } else{
                return rotacaoDireitaEsquerda(atual); //RL
                }
        }
        return atual;
    }

    public void busca(int chave){
        System.out.printf("\nO nó %d aparece %d vezes na árvore\n", chave, busca(raiz, chave));
    }

    private int busca(NoAVL atual, int chave){
        if(atual == null){
            return 0;
        }
        if(atual.chave == chave){
            return atual.repeticoes;
        }
        if(chave < atual.chave){
            return busca(atual.esquerda, chave);
        }else{
            return busca(atual.direita,chave);
        }
    }

    public void desenhar() {
        desenhar(raiz, 0);
    }
    private void desenhar(NoAVL atual, int nivel) {
        if (atual == null) return;
        desenhar(atual.direita, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }
        System.out.println(atual.chave);
        desenhar(atual.esquerda, nivel + 1);
    }

    public void ehAVLValida(){
        if(ehAVLValida(raiz)){
            System.out.println("A árvore é válida");
        }else{
            System.out.println("A árvore não é válida");
        }
    }
    private boolean ehAVLValida(NoAVL noAVL) {
        if (noAVL == null) {
            return true;
        }
        int fb = fatorDeBalanceamento(noAVL);
        if (fb < -1 || fb > 1) {
            return false;
        }
        return ehAVLValida(noAVL.esquerda) && ehAVLValida(noAVL.direita);
    }

    public void diagnosticar() {
        int n = contarNos(raiz);
        int alturaReal = altura(raiz);
        double alturaIdeal = Math.log(n + 1) / Math.log(2);
        System.out.println("nós: " + n);
        System.out.println("altura real: " + alturaReal);
        System.out.printf("altura ideal (aprox.): %.1f%n", alturaIdeal);
    }

    int maiorFb;
    NoAVL piorNoAVL;
    public void encontrarPiorNo(){
        maiorFb = 0;
        piorNoAVL = null;
        encontrarPiorNo(raiz);
        System.out.printf("\nO nó com pior balanceamento é: %d\ncom o fator de balanceamnto de: %d\n", piorNoAVL.chave, maiorFb);
    }
    private void encontrarPiorNo(NoAVL noAVL){
        if(noAVL == null) return;
        int fbAtual = fatorDeBalanceamento(noAVL);
        if(Math.abs(fbAtual) > Math.abs(maiorFb)){
            maiorFb = fbAtual;
            piorNoAVL = noAVL;
        }
        encontrarPiorNo(noAVL.esquerda);
        encontrarPiorNo(noAVL.direita);
    }

    public void contarViolacoes(){
        System.out.printf("\nNa árvore existem %d nós com fator balanceamento diferente de {-1,0,1}\n",contarViolacoes(raiz));
    }
    private int contarViolacoes(NoAVL noAVL){
        if (noAVL == null) return 0;
        int fb = fatorDeBalanceamento(noAVL);
        if(fb < -1 || fb > 1){
            return 1 + contarViolacoes(noAVL.esquerda) + contarViolacoes(noAVL.direita);
        }
        return contarViolacoes(noAVL.esquerda) + contarViolacoes(noAVL.direita);
    }

    public void alturaSeInseridoOrdenado(int n){
        System.out.printf("\nSe inserimos %d elementos em ordem crescente (ou decresente) em uma ABB normal sua altura será de %d\n",
                n, n - 1);
    }


    //caso LL
    private NoAVL rotacaoDireita(NoAVL z){
        NoAVL y = z.esquerda;
        NoAVL t3 = y.direita;

        y.direita = z;
        z.esquerda = t3;

        atualizarAltura(z);
        atualizarAltura(y);

        return y;
    }
    // caso RR
    private NoAVL rotacaoEsquerda(NoAVL z){
        NoAVL y = z.direita;
        NoAVL t2 = y.esquerda;

        y.esquerda = z;
        z.direita = t2;

        atualizarAltura(z);
        atualizarAltura(y);
        return y;
    }
    private NoAVL rotacaoEsquerdaDireita(NoAVL z){
        z.esquerda = rotacaoEsquerda(z.esquerda);
        return rotacaoDireita(z);
    }
    private NoAVL rotacaoDireitaEsquerda(NoAVL z){
        z.direita = rotacaoDireita(z.direita);
        return rotacaoEsquerda(z);
    }
}
