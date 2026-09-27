package RubroNegra;

public class ArvoreRB {
    public NoRB raiz;

    public void desenhar() {
        desenhar(raiz, 0);
    }
    private void desenhar(NoRB atual, int nivel) {
        if (atual == null) return;
        desenhar(atual.direito, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }
        System.out.println(atual.valor + "(" + atual.cor + ")");
        desenhar(atual.esquerdo, nivel + 1);
    }

    public void verAlturaPreta(){
        int altPreta = verAlturaPreta(raiz);
        if(altPreta == -1){
            System.out.println("a árvore Rubro-Negra é incosnsistente!");
        }else{
            System.out.printf("\na árvore Rubro-negra tem a altura preta de %d", altPreta);
        }
    }
    private int verAlturaPreta(NoRB no) {
        //caso base
        if (no == null) {
            return 0;
        }
        //chamada recusriva para o lado esquedo e direito
        int e = verAlturaPreta(no.esquerdo);
        int d = verAlturaPreta(no.direito);
        //verifica se um dos lados já deu alguma inconsistência
        if (e == -1 || d == -1) {
            return -1;
        }
        //procura a diferença de altura preta;
        if (e != d) {
            return -1;
        }
        //caso o nó seja preto ele aumente 1 na altura
        if (no.cor == Cor.PRETO) {
            e++;
        }
        return e;
    }
}
