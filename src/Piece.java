

public class Piece {
    private int width;
    private int height;
    private Character pieceCharacter;
    private boolean placed = false;

    public static final int MINIMUM_SIZE = 1;
    public static final int MAXIMUM_SIZE = 3;


    public Piece(int width, int height, Character c){    // methode de construction
        assert (width>= MINIMUM_SIZE && width<= MAXIMUM_SIZE);
        assert (height>= MINIMUM_SIZE && height<= MAXIMUM_SIZE);
        this.width =width;
        this.height =height;
        this.pieceCharacter =c;
    }


    public boolean checkColor() {      // verifie la couleur : renvoie true si rouge, false si bleue
        if(this.pieceCharacter.equals(Character.toUpperCase(pieceCharacter)))
            return true;   // Piece rouge
        else {
            return false;  // Piece bleue
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Character getPieceCharacter() { //retourne le caractere de la piece
        return this.pieceCharacter;
    }

    public boolean getPlaced(){   // retourne si oui ou non la piece est placée
        return this.placed;
    }

    public void placePiece() {
        this.placed =true;
    }

    public String toString() {
        String S = new String();
        String endl=System.getProperty("line.separator");
        for (int i = 0; i<this.height; i++){
            for (int j = 0; j<this.width; j++){
                S += this.pieceCharacter + "  " ;
            }
            if (i<this.height -1)
                S += endl ;
        }
        /*if (this.getplacee())
            S+= " Placee";
        else {
            S+= " Non placee";
        }*/
        return S;
    }
}
