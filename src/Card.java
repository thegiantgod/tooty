public class Card {
    private String cardType;
    private boolean isDrawn = false;


    public Card(String s) {
        this.cardType = s;
    }

    public String drawCard() {
        this.isDrawn = true;
        return this.getCardType();
    }

    public boolean isDrawn() {
        return this.isDrawn;
    }

    public String getCardType() {
        return this.cardType;
    }

    public String toString() {
        StringBuilder S = new StringBuilder();
        S.append(String.format("Carte %s : ", this.cardType));
        if (this.isDrawn())
            S.append("tiree");
        else {
            S.append("non tiree");
        }
        return S.toString();
    }
}
