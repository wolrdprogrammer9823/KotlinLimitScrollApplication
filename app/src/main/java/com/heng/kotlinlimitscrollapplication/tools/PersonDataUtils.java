package com.heng.kotlinlimitscrollapplication.tools;

import com.heng.limitscrollapplication.bean.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class PersonDataUtils {

    private static volatile PersonDataUtils personDataUtils;
    private List<Person> personList = new ArrayList<>();

    private PersonDataUtils() {}

    public static PersonDataUtils getInstance() {
        if (personDataUtils == null) {
            synchronized (PersonDataUtils.class) {
                if (personDataUtils == null) {
                    personDataUtils = new PersonDataUtils();
                }
            }
        }
        return personDataUtils;
    }

    public List<Person> getDataSet(int methodType) {
        switch (methodType) {
            case 1:
                createPersonListByDFS(createPersonTree());
                break;
            case 2:
                createPersonListByBFS(createPersonTree());
                break;
            case 3:
                createPersonListByNTreePreOrder(createPersonTree());
//                Iterator<Person> personIterator = personList.iterator();
//                while (personIterator.hasNext()) {
//                    Person person = personIterator.next();
//                    if (person != null && person.getLevel() != 3 && person.getChildren() == null) {
//                        personIterator.remove();
//                    }
//                }
                break;
            default:
                break;
        }
        return personList;
    }

    private void createPersonListByNTreePreOrder(Person person) {

        if (person == null) {
            return;
        }

        Stack<Person> personStack = new Stack<>();
        personStack.add(person);

        while (!personStack.isEmpty()) {

            Person child = personStack.pop();
            int level = child.getLevel();
            if (level != 0) {

//                if (level == 3 || child.getChildren() != null) {
//
                    personList.add(child);
//                }

            }

            if (child.getChildren() != null) {

                Collections.reverse(child.getChildren());
                personStack.addAll(child.getChildren());
            }
        }
    }

    private void createPersonListByDFS(Person person) {
        if (person == null) {
            return;
        }

        Stack<Person> stack = new Stack<>();
        Set<Person> set = new HashSet<>();
        stack.add(person);
        set.add(person);

        //System.out.println("value:" + person.getName());

        while (!stack.isEmpty()) {

            Person child = stack.pop();

            if (child.getChildren() != null) {
                for (Person son : child.getChildren()) {
                    if (!set.contains(son)) {

                        stack.push(child);
                        stack.push(son);
                        set.add(son);
                        //System.out.println("value:" + son.getName());
                        personList.add(son);
                    }
                }
            }
        }
    }

    private  void createPersonListByBFS(Person person) {

        if (person == null) {
            return;
        }

        Set<Person> personSet = new HashSet<>();
        Queue<Person> personQueue = new LinkedList<>();
        personSet.add(person);
        personQueue.add(person);

        while (!personQueue.isEmpty()) {

            Person currentPerson = personQueue.poll();
            if (currentPerson.getLevel() != 0) {
                personList.add(currentPerson);
            }
            if (currentPerson.getChildren() != null) {
                for (Person child : currentPerson.getChildren()) {
                    if (!personSet.contains(child)) {

                        personSet.add(child);
                        personQueue.add(child);
                    }
                }
            }
        }
    }


    private Person createPersonTree() {

        Person person20 = new Person("20", 3, null);
        Person person19 = new Person("19", 3, null);
        Person person18 = new Person("18", 3, null);
        Person person17 = new Person("17", 3, null);
        Person person16 = new Person("16", 3, null);
        Person person15 = new Person("15", 3, null);
        Person person14 = new Person("14", 3, null);
        Person person13 = new Person("13", 3, null);
        Person person12 = new Person("12", 3, null);
        Person person11 = new Person("11", 3, null);

        Person person10 = new Person("10", 2, null);
        Person person9 = new Person("9", 2, null);
        Person person8 = new Person("8", 2, null);
        Person person7 = new Person("7", 2, null);
        Person person6 = new Person("6", 2, null);
        Person person5 = new Person("5", 2, null);

        person10.setChildren(Arrays.asList(person14));
        person9.setChildren(Arrays.asList(person13));
        person8.setChildren(Arrays.asList(person15, person16, person17));
        person7.setChildren(Arrays.asList(person20));
        person6.setChildren(Arrays.asList(person12, person18));
        person5.setChildren(Arrays.asList(person11, person19));

        Person person4 = new Person("4", 1, null);
        Person person3 = new Person("3", 1, null);
        Person person2 = new Person("2", 1, null);

        person4.setChildren(Arrays.asList(person8, person9, person10));
        person3.setChildren(Arrays.asList(person7));
        person2.setChildren(Arrays.asList(person6, person5));

        Person person1 = new Person("1", 0, null);
        person1.setChildren(Arrays.asList(person4, person3, person2));

        return person1;
    }
}
