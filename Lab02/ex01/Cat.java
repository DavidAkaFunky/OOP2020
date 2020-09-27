public class Cat{
    private String name;
    private int age;
    private double weight;

    public Cat(String name, int age, double weight){
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public void setName(String name){this.name = name;}
    public void setAge(int age){this.age = age;}
    public void setWeight(double weight){this.weight = weight;}

    public String getName(){return this.name;}
    public int getAge(){return this.age;}
    public double getWeight(){return this.weight;}

    @Override
    public boolean equals(Object other){
        if (other instanceof Cat){
            Cat cat = (Cat) other;
            return this.name.equals(cat.name) && this.age == cat.age && this.weight == cat.weight;
        }
        return false;
    }

    @Override
    public String toString(){
        return this.name + " is a cat. It's " + this.age + " years old and weighs " + this.weight + " kg.";
    }
}