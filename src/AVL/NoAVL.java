package AVL;

public class NoAVL {
    int chave, altura, repeticoes;
    NoAVL esquerda, direita;

    NoAVL(int chave) {
        this.chave = chave;
        this.altura = 0;
        this.repeticoes = 1;
    }
}
