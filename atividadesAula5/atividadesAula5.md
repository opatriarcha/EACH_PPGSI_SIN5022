##Testes Estruturais de CoffeeMaker

#### Classe Recipe - 100% line e branch coverage;
#### Classe RecipeBook - 100% line e branch coverage;
#### Classe Inventory - Não foi possivel atingir os 100% de coverage;
- Os métodos set*** referentes aos ingredientes, embora não ocultados em termos de encapsulamento, não são interligados à interface pública de CoffeeMaker. Em virtude disso, ao utilizar CoffeeMaker como base de inicio dos testes, é impossivel testar toda a interface pública de Inventory. É possivel também notar duplicação de regras de negócio entre os metodos set*** e add***;
- Os  dois trechos código abaixo representam a duplicação encontrada. Atraves de CoffeeMaker somente é possivel testar o segundo método. O primeiro não faz parte da interface pública do chamador. Para testá-la seria necessario criar uma suite de testes para a classe Inventory e utilizar de sua interface pública.
```java
 public synchronized void setMilk(int milk) throws InvalidValueException{
    	if(milk >= 0 && milk<=100) {
    		this.milk = milk;
    	} else {
			throw new InvalidValueException("Invalid amount of milk"); //nao e possivel testar a partir de CoffeeMaker.java e Recipe.java
		}
    }
```

```java
 public synchronized void setMilk(int milk) throws InvalidValueException{
    	public synchronized void addMilk(int amtMilk) throws InvalidValueException{    	
        		if (amtMilk >= 0 && amtMilk + this.milk<=100) {
        			this.milk += amtMilk;
        		} else {
        			throw new InvalidValueException("Units of milk must be a positive integer and less equal than 100");
        		}
            }
```

#### Classe RecipeBook - 100% line e branch coverage;
#### Classe CoffeeMaker Impossivel de ser 100 de coverage

```java
    try {
		inventory = new Inventory();
	} catch (InvalidValueException e) { //nunca sera executado
			// TODO Auto-generated catch block
	}
```
   
Trecho de código acima nunca será executado devido a inclusão de numeros mágicos na chamada abaixo.

```java
  public Inventory() throws InvalidValueException {
    	setCoffee(20);
    	setMilk(20);
    	setSugar(20);
    	setChocolate(20);
    }
```

O trecho abaixo não executará plenamente a instrução if pois os predicados são mutuamente excludentes.
```java
    public synchronized int makeCoffee(String recipeName, int amtPaid) throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException {
		if (amtPaid < 0 && amtPaid > 500) { //mutuamente excludente
			throw new InvalidValueException("Payment must be positive or less than 500 cents"); //nunca sera executado
		} else {
```



