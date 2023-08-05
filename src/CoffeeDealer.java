import java.util.Scanner;

public class CoffeeDealer {
    boolean isRun = true;
    Water water;
    Milk milk;
    CoffeeBean coffeeBean;
    Cup cup;
    Money money;
    Ingredient[] ingredients;

    public CoffeeDealer(Water water, Milk milk, CoffeeBean coffeeBean, Cup cup, Money money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBean = coffeeBean;
        this.cup = cup;
        this.money = money;
        this.ingredients = new Ingredient[]{this.water, this.milk, this.coffeeBean, this.cup, this.money};
    }

    public static void main(String[] args) {
        CoffeeDealer coffeeDealer = new CoffeeDealer(new Water(400),
                new Milk(540), new CoffeeBean(120),
                new Cup(9), new Money(50000));
        coffeeDealer.work();
    }

    void work(){
        System.out.println("커피머신이 작동합니다!");
        output();
        while (isRun){
            int choice = userResponse("1. 구매 | 2. 채우기 | 3. 돈 가져오기 > ");

            switch (choice){
                case 1 -> buy();
                case 2 -> fill();
                case 3 -> System.out.println(takeMoney());
            }
        }
    }

    public int takeMoney(){
        int myMoney = Money.moneyAmount;
        return myMoney;
    }

    public void fill(){
        int water = Water.waterAmount = 4000;
        System.out.println("물이 " + water + "보충 되었습니다.");
    }

    private void buy(){
        while (isRun){
            int choice = userResponse("1. 에스프레소| 2. 라떼| 3. 카푸치노| 4. 상위설정| 5. 나가기 >");

            switch (choice){
                case 1:
                    System.out.println("에스프레소를 선택");
                    System.out.println("에스프레소에 드는 물의 양: " + Espresso.neededWater);
                    Water.waterAmount -= Espresso.neededWater;
                    System.out.println("남은 물의 양: " + Water.waterAmount);
                    Money.moneyAmount += Espresso.cost;
                    System.out.println("모인 돈: " + Money.moneyAmount);

                    // new Water(ingredients[0].getAmount() - Espresso.neededWater);
                    break;
                case 2:
                    System.out.println("2번선택");
                case 3:
                    System.out.println("3번선택");
                case 4:
                    work();
                case 5:
                        isRun = false;
            }
        }
    }

    int userResponse(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        int chosenNumber = Integer.parseInt(scanner.nextLine());
        return chosenNumber;
    }

    void output(){
        for(Ingredient ingredient:ingredients){
            System.out.println(ingredient.name + " : " + ingredient.getAmount() + " " + ingredient.unit);
        }
    }
}

abstract class Ingredient {
    private int amount;
    protected String name;
    protected String unit;

    public Ingredient(int amount) {
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    void addAmount(int amount){
        this.amount += amount;
    }

    void removeAmount(int amount){
        this.amount -= amount;
    }
}

class Water extends Ingredient {
    static int waterAmount = 400;
    public Water(int amount) {
        super(amount);
        this.name = "물";
        this.unit = "ml";
    }
}

class Milk extends Ingredient {
    public Milk(int amount) {
        super(amount);
        this.name = "우유";
        this.unit = "ml";
    }
}

class CoffeeBean extends Ingredient {
    public CoffeeBean(int amount) {
        super(amount);
        this.name = "원두";
        this.unit = "g";
    }
}

class Cup extends Ingredient {
    public Cup(int amount) {
        super(amount);
        this.name = "종이컵";
        this.unit = "개";
    }
}

class Money extends Ingredient {
    static int moneyAmount = 50000;
    public Money(int amount) {
        super(amount);
        this.name = "돈";
        this.unit = "원";
    }
}

class Espresso {
    static int neededWater = 250;
    static int neededMilk = 0;
    static int neededBean = 16;
    static int cost = 4000;
}

class Latte {
    static int neededWater = 200;
    static int neededMilk = 75;
    static int neededBean = 20;
    static int cost = 7000;
}

class Capuccino {
    static int neededWater = 350;
    static int neededMilk = 100;
    static int neededBean = 12;
    static int cost = 6000;
}
