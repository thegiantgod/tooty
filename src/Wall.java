import java.util.ArrayList;

public class Wall {
    private ArrayList<Character> [] wall = new ArrayList[ROWS_NUMBER];

    public static final int ROWS_NUMBER = 5;


    public Wall() {
        for (int i = 0; i< ROWS_NUMBER; i++)
            wall[i] = new ArrayList<>();
        for (int i=0; i<2; i++) {
            for (int j = 0; j< ROWS_NUMBER; j++) {
                wall[j].add(' ');
            }
        }
    }

    private void addLines(int rowsNumber) {
        for (int i=0; i<rowsNumber; i++) {
            // System.out.println(mur[0].size());
            for (int j = 0; j< ROWS_NUMBER; j++) {
                wall[j].add(' ');
            }
        }
    }

    private void setCase (int x, int y, Character s) {        //remplace la case d'abcisse x-1 et d'ordonnée y-1 par un caractere
        this.wall[x-1].set(y-1, s);
    }

    public void insertPiece(int x, int y, Piece P) {         //insère une piece P dans le mur a partir des coordonnées x-1 et y-1
        if ((y+P.getHeight()-1)>= wall[0].size()) {
            addLines(y+P.getHeight()- wall[0].size());
        }
        for (int i=0; i<P.getWidth(); i++) {
            for (int j=0; j<P.getHeight(); j++) {
                setCase(x+i, y+j, P.getPieceCharacter());
            }
        }
        P.placePiece();
    }

    public void insertPieceBackward(int x, int y, Piece P) {         //insère une piece P dans le mur a partir des coordonnées x+l et y+h

        for (int i=0; i<P.getWidth(); i++) {
            for (int j=0; j<P.getHeight(); j++) {
                setCase(x-i, y+j, P.getPieceCharacter());
            }
        }
        P.placePiece();
    }


    public String toString() {
        String S = new String();
        String endl = System.getProperty("line.separator");
        for(int i = wall[0].size()-1; i>=0; i--) {
            if (i<9) {
                S+=" ";
            }
            S += i+1;
            for (int j = 0; j< ROWS_NUMBER; j++) {
                S += "  " + wall[j].get(i);
            }
            S += endl;
        }
        S += "  ";
        for (int i = 0; i< ROWS_NUMBER; i++) {
            S += "  " + (i+1);
        }
        return S;
    }

    public boolean touchesAnotherTile(int x, int y, Piece P) {
        boolean verif=false;
        for (int i=-1; i<=P.getWidth(); i++) {
            for (int j=-1; j<=P.getHeight(); j++) {
                if (((x+i>0) && (x+i<6)) && ((y+j>0) && (y+j<= wall[0].size()))) {
                    if (!wall[x+i-1].get(y+j-1).equals(' ')){
                        verif=true;
                    }
                    if (i==-1 && j==-1)
                        verif=false;
                }
            }
        }
        return verif;
    }
    public boolean notEmptyBase(int x, int y, Piece P) {
        boolean verif=false;
        if (y==1) {
            verif=true;
            //System.out.println("balise 1");
        }
        else {
            //System.out.println("balise 2");
            verif=true;
            for (int i=0; i<P.getWidth(); i++) {
                if (wall[x+i-1].get(y-2).equals(' ')) {
                    //System.out.println("balise 3  x:" + (x + i));
                    verif = false;
                }
            }
        }
        return verif;
    }
    public boolean clonesTile(int x, int y, Piece P) {
        boolean verif = true;
        if (y!=1){
            //System.out.println("checkpoint 1");
            if (x==1) {
                //System.out.println("checkpoint 2");
                Character cara;
                cara = wall[x-1].get(y-2);
                for (int i=0; i<P.getWidth(); i++) {
                    if (!wall[x-1+i].get(y-2).equals(cara)) {
                        verif = false;
                        //System.out.println("checkpoint 3");
                    }
                }
                if (wall[x-1+P.getWidth()].get(y-2).equals(cara)) {
                    //System.out.println("checkpoint 4");
                    verif = false;
                }
            }

            else if (x-1+P.getWidth()==5) {
                //System.out.println("checkpoint 2 bis");
                Character cara;
                cara = wall[x-1].get(y-2);
                for (int i=0; i<P.getWidth(); i++) {
                    if (!(wall[x-1+i].get(y-2).equals(cara))) {
                        verif = false;
                        //System.out.println("checkpoint 4");
                    }
                }
                if (wall[x-2].get(y-2).equals(cara)) {
                    verif = false;
                    //System.out.println("checkpoint 4 bis");
                }
            }

            else {
                //System.out.println("checkpoint 2 ter");
                Character cara;
                cara = wall[x-1].get(y-2);
                if (wall[x-2].get(y-2).equals(cara))
                    verif = false;
                else {
                    for (int i=0; i<P.getWidth(); i++) {
                        if (!wall[x-1+i].get(y-2).equals(cara)) {
                            verif = false;
                        }
                    }
                    if (wall[x-1+P.getWidth()].get(y-2).equals(cara))
                        verif = false;
                }
            }
        }
        else {
            //System.out.println("checkpoint 1 bis");
            verif = false;
        }
        return verif;
    }
    public boolean emptyTile(int x, int y) {
        boolean verif=true;
        if (!wall[x-1].get(y-1).equals(' ')){
            verif = false;
        }
        return verif;
    }
    public boolean filledRow(int y) {
        boolean verif = true;
        for (int i = 0; i< ROWS_NUMBER; i++) {
            if (wall[i].get(y).equals(' ')) {
                verif = false;
            }
        }
        return verif;
    }


    public int getRowsNumber() {
        return ROWS_NUMBER;
    }
    public int getHeight() {
        return this.wall[0].size();
    }
}
