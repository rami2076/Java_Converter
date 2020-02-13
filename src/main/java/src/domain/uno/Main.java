package src.domain.uno;


import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Deck<Uno> deck = Deck.Builder.<Uno>get().build();
        deck.getCards()
                .stream()
                .map(Card::getnum)
                .forEach(System.out::println);
    }
}

class Deck<T1 extends Card> {
    private int maxCardCount;

    private List<T1> cards;

    public List<T1> getCards() {
        return cards;
    }

    private Deck(Builder<T1> builder) {
        this.maxCardCount = builder.maxCardCount;
        cards = builder.cards;

    }

    public static class Builder<T2 extends Card> {
        private int maxCardCount;
        private List<T2> cards;

        private Builder(T2... e) throws IllegalAccessException, InstantiationException {
            Class<T2> cardType = (Class<T2>) e.getClass().getComponentType();
            Card card = cardType.newInstance();
            this.maxCardCount = card.getMaxCardCount();
            this.cards = IntStream.rangeClosed(1, card.getMaxCardCount())
                    .mapToObj(card.<T2>get())
                    .collect(Collectors.toList());
        }


        public static <T3 extends Card> Builder<T3> get(T3... e) throws InstantiationException, IllegalAccessException {
            return new Builder<T3>(e);
        }

        public Deck<T2> build() {
            return new Deck<T2>(this);
        }

    }

}

interface Game {
}

class Unos implements Game {
}

class Speeds implements Game {
}

interface Card {

    public default int getMaxCardCount() {
        return 0;
    }

    public int getnum();

    public <T extends Card> T setnum(int i);

    public <T extends Card> IntFunction<T> get();


}

class Uno implements Card {
    private int maxCardCount = 50;
    private int num;

    @Override
    public int getMaxCardCount() {
        return maxCardCount;
    }

    @Override
    public int getnum() {
        return num;
    }

    @Override
    public <T extends Card> T setnum(int i) {
        this.num = i;
        return (T) this;
    }

    @Override
    public <T extends Card> IntFunction<T> get() {
        return (e) -> {
            Uno uno = new Uno();
            return uno.setnum(e);
        };
    }


}

class Trump implements Card {
    private int maxCardCount = 100;
    private Integer num;


    @Override
    public int getMaxCardCount() {
        return maxCardCount;
    }

    @Override
    public int getnum() {
        return num;
    }

    @Override
    public <T extends Card> T setnum(int i) {
        this.num = i;
        return (T) this;
    }

    @Override
    public <T extends Card> IntFunction<T> get() {
        return (i) -> {
            Trump s = new Trump();
            return s.setnum(i);
        };
    }
}