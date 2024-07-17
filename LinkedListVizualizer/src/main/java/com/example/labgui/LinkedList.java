package com.example.labgui;

public class LinkedList {
    private Node head;
    LinkedList() {
        head = null;
    }

    public void add(int value) {
        Node node = new Node(value);
        if (head == null) head = node;
        else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = node;
        }
    }

    public void addToStart(int value) {
        Node node = new Node(value);
        if (head == null) head = node;
        else {
            node.next = head;
            head = node;
        }
    }

    public int getIndexOf(int value){
        int index = -1;
        Node temp = head;
        while (temp != null){
            if (temp.value == value){
                return index+1;
            }else {
                temp = temp.next;
                index++;
            }
        }
        return -1;
    }

    public void addToMiddle(int index, int value) {
        Node node = new Node(value);
        Boolean nodeAdded = false;
        int i = 0;
        if (head == null) head = node;
        else {
            Node temp  = head;
            while (temp !=null) {
                if (i == index) {
                    node.next = temp.next;
                    temp.next = node;
                    nodeAdded = true;
                }
                    temp = temp.next;
                    i++;
            }
            if (!nodeAdded) System.out.println("Girdiğiniz index listede bulunmamaktadır, Ekleme işlemi başarısız!");
        }
    }

    public void printList(){
        Node temp = head;
        System.out.print("[");
        while (head !=null) {
            System.out.print(head.value + ", ");
            head = head.next;
        }
        System.out.print("]");
        head = temp;
    }
    public void deleteFromHead(){
        if (head== null) System.out.println("Liste boştur silme işlemi başaısız!");
        else head = head.next;
    }

    public void deleteFromEnd(){
        if (head == null) System.out.println("Liste boştur silme işlemi başaısız!");
        else {
            Node temp = head;
            if (temp.next==null) head = null;
            else {
                while (temp.next.next != null) temp = temp.next;
                temp.next = null;
            }
        }
    }

    /*
    not: listelerden 1'den fazla eleman java dilinde silinecekse her bir elemanın tek başına
    bırakılması gerekir
    birden fazla silme işlemi için tek bir silme için yazılmış metodu döngü içinde birden fazla çağrılmalıdır
     */

    public void deleteFromLastMoreThanOne(int count){
        for (int i = 0; i < count; i++) {
            deleteFromEnd();
        }
    }

    public void deleteFromMidMoreThanOne(int count,int index){
        for (int i = 0; i < count; i++) {
            deleteFromMid(index);
        }
    }

    public static int listElemanSayisi(LinkedList list){
        int sayac = 0;
        if (list.head == null) return 0;
        else {
            Node temp = list.head;
            while (temp.next!= null){
                sayac++;
                temp = temp.next;
            }
            return ++sayac;
        }

    }

    public void deleteFromMid(int index){
        if (head == null) System.out.println("Liste boştur silme işlemi başaısız!");
        else {
            int i = 0;
            Node tempKonum = head;
            Node tempOnce = null;

            if (tempKonum.next == null && i == index) head = null;
            else {
                while (tempKonum != null) {
                    if (i == index) {
                        tempOnce.next = tempKonum.next;
                        return;
                    }
                    i++;
                    tempOnce = tempKonum;
                    tempKonum = tempKonum.next;
                }
                System.out.println("Girilen index dizide bulunmuyor, Silme işlemi başarısız!");
            }
        }
    }
    private static class Node {
        int value;
        Node next;
        private Node(int value) {
            this.value = value;
            next = null;
        }
    }
}



