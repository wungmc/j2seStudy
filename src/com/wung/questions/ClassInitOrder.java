package com.wung.questions;

/**
 * 类加载和执行顺序的
 * 1、static 代码块优先于构造器执行；
 * 2、如果有父类，则要先加载父类；
 * 3、非 static 属性初始化；
 * 4、构造器
 *
 * Created by wung on 2016/12/7.
 */
public class ClassInitOrder {
    Person person = new Person("ClassInitOrder");
    static{
        System.out.println("test static");
    }

    public ClassInitOrder() {
        System.out.println("ClassInitOrder constructor");
    }

    public static void main(String[] args) {
        new MyClass();
    }

    // output
    //test static
    //myclass static
    //person static
    //person ClassInitOrder
    //ClassInitOrder constructor
    //person MyClass
    //myclass constructor
}

class Person{
    static{
        System.out.println("person static");
    }
    public Person(String str) {
        System.out.println("person "+str);
    }
}


class MyClass extends ClassInitOrder {
    Person person = new Person("MyClass");
    static{
        System.out.println("myclass static");
    }

    public MyClass() {
        System.out.println("myclass constructor");
    }
}
