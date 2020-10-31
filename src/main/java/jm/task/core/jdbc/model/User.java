package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity
//возможно я ошибась, но в данном случае не нужно обзывать таблицу как сделано ниже
//и оставить дефолтное значение ибо мы через SQL создаем таблицу с названием уже!
@Table (name = "dao")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private String name;


    private String lastName;


    private Byte age;



    public User() {

    }


    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }


        public Long getId () {
            return id;
        }

        public void setId(long id){
            this.id = id;
        }

        public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public String getLastName () {
            return lastName;
        }

        public void setLastName (String lastName){
            this.lastName = lastName;
        }

        public Byte getAge (byte age) {
            return age;
        }

        public void setAge (Byte age){
            this.age = age;
        }

        @Override
        public String toString () {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }


}
