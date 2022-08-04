public class PiecesList {

    private Piece [] neutralPiece;
    private Piece [] piecesList;

    public static final int TILES_NUMBER = 18;

    public PiecesList() {
        neutralPiece = new Piece[2];          //creation des pieces neutres
        neutralPiece[0] = new Piece(3,1,'x');
        neutralPiece[1] = new Piece(1,3,'x');
        piecesList = new Piece[TILES_NUMBER];   //creation des pieces colorées
        int counter=0, width=1;
        Character c='a', C='A';
        for (int i=0; i<Piece.MAXIMUM_SIZE; i++) {
            int height=1;
            for (int j=0; j<Piece.MAXIMUM_SIZE; j++) {
                piecesList[counter] = new Piece(width, height, c);
                counter++;
                piecesList[counter] = new Piece(width, height, C);
                counter++;
                height++;
                c++;
                C++;
            }
            width++;
        }
    }

    public Piece getNeutralPiece(int i){     // retourne la pice neutre horizontale ou verticale
        return this.neutralPiece[i];
    }


    public Piece getPiece(int i) {             //retourne la piece colorée du rang i
        return this.piecesList[i];
    }

    public int getTilesNumber() {
        return TILES_NUMBER;
    }
}
