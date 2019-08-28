package com.github.nicejing.data.linkedlist;

/**
 * @author Jing Zhi Bao
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        Node hero1 = new Node(1, "宋江", "及时雨");
        Node hero3 = new Node(3, "吴用", "智多星");
        Node hero2 = new Node(2, "卢俊义", "玉麒麟");
        Node hero4 = new Node(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 加入按照编号的顺序
        singleLinkedList.addByNo(hero1);
        singleLinkedList.addByNo(hero4);
        singleLinkedList.addByNo(hero2);
        singleLinkedList.addByNo(hero3);

        singleLinkedList.list();
    }
}
