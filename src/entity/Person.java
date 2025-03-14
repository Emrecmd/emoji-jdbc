package entity;

public class Person {
    int age;
    String emoji;
    String name;

    public Person(String name, String emoji, int age) {
        this.name = name;
        this.emoji = emoji;
        this.age = age;
    }

    public Person(int id, String isim, int yas, String emoji) {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}