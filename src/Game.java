import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int placedTilesNumber =0;
    private int drawnCardsNumber =0;
    private int points;
    private int discardedCardsNumber =0;
    private int fullLines =0;
    private int notPlacedTiles =0;
    private int gameState =0;
    private Wall wall;
    private PiecesList Pieces;
    private ArrayList<Piece> turnPieces;
    private Deck deck;


    public Game() {
        points = 0;
        wall = new Wall();
        Pieces = new PiecesList();
        deck = new Deck();
        turnPieces = new ArrayList<>();
    }

    public void filterPieces(String S) {
        this.turnPieces.clear();
        int cmpt = 0;
        for (int i=0; i<Pieces.getTilesNumber(); i++) {
            if (S.equals("Bleu")){
                if (!this.Pieces.getPiece(i).getPlaced() && !this.Pieces.getPiece(i).checkColor()) {
                    this.turnPieces.add(this.Pieces.getPiece(i));
                }
            }

            if (S.equals("Rouge")){
                if (!this.Pieces.getPiece(i).getPlaced() && this.Pieces.getPiece(i).checkColor()) {
                    this.turnPieces.add(this.Pieces.getPiece(i));
                }
            }

            if (S.equals("Taille 1")) {
                if (!this.Pieces.getPiece(i).getPlaced() && (Pieces.getPiece(i).getWidth() == 1 || Pieces.getPiece(i).getHeight() == 1)) {
                    this.turnPieces.add(Pieces.getPiece(i));
                }
            }

            if (S.equals("Taille 2")) {
                if (!this.Pieces.getPiece(i).getPlaced() && (Pieces.getPiece(i).getWidth() == 2 || Pieces.getPiece(i).getHeight() == 2)) {
                    this.turnPieces.add(Pieces.getPiece(i));
                }
            }

            if (S.equals("Taille 3")) {
                if (!this.Pieces.getPiece(i).getPlaced() && (Pieces.getPiece(i).getWidth() == 3 || Pieces.getPiece(i).getHeight() == 3)) {
                    this.turnPieces.add(Pieces.getPiece(i));
                }
            }
        }
    }

    public boolean placeNeutralPiece(int x, int y, Piece P) {    // permet de placer une piece neutre, renvoie true si la piece est placee a un endroit valide, false sinon
        boolean b= false;

        if((x!=1 && x-1+P.getWidth()!=5) || (y!=1)) {
            System.out.println("Emplacement non valide");
            b=false;
        }
        else if (x!=5){
            wall.insertPiece(x, y, P);
            b=true;
        }
        else if (x==5) {
            wall.insertPiece(x, y, P);
            b=true;
        }
        return b;
    }

    public boolean placePiece(int x, int y, Character S ) {
        boolean b = false;
        for (int i = 0; i<this.turnPieces.size(); i++) {
            if (turnPieces.get(i).getPieceCharacter().equals(S)){
                if ((x-1+ turnPieces.get(i).getWidth()>5) || (!wall.touchesAnotherTile(x,y, turnPieces.get(i))) ||
                        (!wall.notEmptyBase(x,y, turnPieces.get(i))) || wall.clonesTile(x,y, turnPieces.get(i))
                         /* || mur.Case_Vide(x,y)*/      ){
                    System.out.println("Emplacement non valide");
                }
                if (x-1+ turnPieces.get(i).getWidth()>5) {
                    System.out.println("La pièce sort du terrain");
                }
                if (!wall.touchesAnotherTile(x,y, turnPieces.get(i))) {
                    System.out.println("La pièce ne touche aucune autre pièces");
                }
                if (!wall.notEmptyBase(x,y, turnPieces.get(i))) {
                    System.out.println("La base de la pièce est invalide");
                }
                if (wall.clonesTile(x,y, turnPieces.get(i))) {
                    System.out.println("La pièce clone la surface d'une autre pièce");
                }

                else {
                    wall.insertPiece(x,y, turnPieces.get(i));

                    b=true;
                }

            }
        }
        return b;
    }

    private void firstTurn() {   // déroulement du premier tour (celui du placement de la piece neutre)
        System.out.println("Vous commencerez a placer la piece neutre qui a une longueur de 3 blocs.");
        System.out.println("Vous devez poser la piece neutre dans un coin en précisant ou vous la posez en partant du coin en bas a gauche de ou la piece est :");
        System.out.println("Préciser 0 pour la placer horizontalement et 1 verticalement");
        System.out.println(this.getWall());
        Scanner c = new Scanner(System.in);
        int aux=0;
        while (aux==0) {
            System.out.println("entrez sous la forme : x espace y espace 0 ou 1");
            int x= c.nextInt();
            int y = c.nextInt();
            int index = c.nextInt();
            placeNeutralPiece(x,y,getPieces().getNeutralPiece(index));
            if (placeNeutralPiece(x,y,getPieces().getNeutralPiece(index))){
                aux=1;
            }
        }
    }

    private void turn() {

        this.filterPieces(this.getDeck().Tirer_Carte());
        System.out.println(this.displayPlayablePieces());
        System.out.println("Vous pouvez jouer les pièces affichées :");
        System.out.println(getWall());
        int aux=0;
        String endl = System.getProperty("line.separator");
        Scanner c = new Scanner(System.in);
        while (aux==0) {
            System.out.println("entrez x espace y espace identifiant de la pièce(d, H...) ou x espace y espace next si vous voulez passer votre tour ou x espace y espace stop si vous voulez finir la partie :");
            int x= c.nextInt();
            int y = c.nextInt();
            String index = c.next();
            if (index.equals("next")) {
                this.points -= 1;
                this.discardedCardsNumber++;
                return;
            }
            if (index.equals("stop")) {
                gameState =1;
                return;
            }
            Character cara = index.charAt(0);
            //System.out.println(cara);
            placePiece(x, y, cara);
            if (placePiece(x,y,cara)) {
                aux=1;
                this.placedTilesNumber +=1;
            }
        }
        this.drawnCardsNumber++;
        System.out.println(endl);
    }    // deroulement d'un tour classique

    private void countPoints() {


        for (int i = 0; i<this.wall.getHeight(); i++) {
            if (wall.filledRow(i)) {
                this.points += 5;
                fullLines++;
            }
        }
        for (int i=0; i<this.getPieces().getTilesNumber(); i++) {
            if (!this.getPieces().getPiece(i).getPlaced()) {
                this.points -= 1;
                notPlacedTiles++;
            }
        }
    }

    private void displayPoints() {
        System.out.print("Vous avez finis la partie avec " + this.points + " points ! (" + this.fullLines + " niveaux complets, " + this.notPlacedTiles + " carreaux non posés, " + this.discardedCardsNumber + " cartes écartées)");
        if (this.points <= 0) {
            System.out.print("  Dommage !");
        }
        else {
            System.out.print("  Bien joué !");
        }
    }

    private void Introduction () {     // affiché au début du jeu
        System.out.println("Bienvenue au Tooty, pourrez-vous battre le jeu et faire un maximum de points ?");
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("Vous devrez placer des carreaux de différentes tailles sur le mur afin de le compléter, passer un tour vous fera predre 1 point.");
        System.out.println("Chaque carreau non placé à la fin vous fera perdre 1 point.");
        System.out.println("Il y a des règles pour le placement des carreaux :");
        System.out.println("- Interdit de placer un carreau sans qu'il en touche un autre");
        System.out.println("- Interdit de changer l'orientation d'une piece");
        System.out.println("- Un carreau ne doit pas dépasser la zone a carreler sur l'axe des abcisses");
        System.out.println("- Toute la base du carreau repose, soit sur le bas de la zone a carreler, soit sur d’autres carreaux");
        System.out.println("- un carreau ne dois pas reposer sur uniquement toute la surface donnee par un autre carreau");
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println(" ");

    }

    public void gameCourse() {         // déroulement du jeu dans son intégralité
        Introduction();
        firstTurn();
        while (placedTilesNumber < this.getPieces().getTilesNumber() && drawnCardsNumber < this.getDeck().getTaillePaquet()
            && gameState ==0) {
            turn();
            //System.out.println(NbCarreauxPoses);
            //System.out.println(NbCartesPiochees);
        }
        countPoints();
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("Mur final :");
        System.out.println(this.getWall());
        displayPoints();
    }


    public ArrayList<Piece> getTurnPieces() {
        return this.turnPieces;
    }
    public Deck getDeck() {
        return deck;
    }
    public PiecesList getPieces() {
        return Pieces;
    }
    public Wall getWall() {
        return wall;
    }
    public int getPoints() {
        return points;
    }


    public String displayPlayablePieces() {
        String S = new String();
        String endl = System.getProperty("line.separator");
        for (int i = 0; i<this.getTurnPieces().size(); i++) {
           S += this.getTurnPieces().get(i);
           S +=endl;
        }
        return S;
    }

    public int getNbCarreauxPosés() {
        return placedTilesNumber;
    }

    public int getDrawnCardsNumber() {
        return drawnCardsNumber;
    }

    public int getGameState() {
        return gameState;
    }
}
