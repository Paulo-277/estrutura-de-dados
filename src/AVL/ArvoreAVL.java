package AVL;

public class ArvoreAVL {
    private NoAVL raiz;

    private int altura(NoAVL noAVL) {
        if (noAVL == null) return -1;
        return 1 + Math.max(altura(noAVL.esquerda), altura(noAVL.direita));
    }
    private int fatorDeBalanceamento(NoAVL noAVL) {
        return altura(noAVL.esquerda) - altura(noAVL.direita);
    }
    private int contarNos(NoAVL noAVL) {
        if(noAVL == null) return 0;
        return 1 + contarNos(noAVL.esquerda) + contarNos(noAVL.direita);
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }
    private NoAVL inserir(NoAVL atual, int valor) {
        if (atual == null) return new NoAVL(valor); // caso base
        if (valor < atual.chave) {
            atual.esquerda = inserir(atual.esquerda, valor);
        } else if (valor > atual.chave) {
            atual.direita = inserir(atual.direita, valor);
        }
        return atual;
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

}
