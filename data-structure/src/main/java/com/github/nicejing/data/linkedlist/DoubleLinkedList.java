package com.github.nicejing.data.linkedlist;

/**
 * @author Jing Zhi Bao
 */
public class DoubleLinkedList {

    private Node head = new Node(0, "", "");

    public Node getHead() {
        return head;
    }

    public void list() {
        // 判断链表是否为空
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点，不能动，因此我们需要一个辅助变量来遍历
        Node temp = head.getNext();
        while (temp != null) {
            // 判断是否到链表最后
            // 输出节点的信息
            System.out.println(temp);
            // 将temp 后移， 一定小心
            temp = temp.getNext();
        }
    }

    public void add(Node heroNode) {
        // 因为head 节点不能动，因此我们需要一个辅助遍历temp
        Node temp = head;
        // 遍历链表，找到最后
        while (temp.getNext() != null) {
            // 找到链表的最后
            // 如果没有找到最后, 将将temp 后移
            temp = temp.getNext();
        }
        // 当退出while 循环时，temp 就指向了链表的最后
        // 形成一个双向链表
        temp.setNext(heroNode);
        heroNode.setPre(temp);
    }

    public void update(Node newHeroNode) {
        // 判断是否空
        if (head.getNext() == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点, 根据no 编号
        // 定义一个辅助变量
        Node temp = head.getNext();
        boolean flag = false;
        while (true) {
            if (temp == null) {
                // 已经遍历完链表
                break;
            }
            if (temp.getNo() == newHeroNode.getNo()) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        // 根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.setName(newHeroNode.getName());
            temp.setNickname(newHeroNode.getNickname());
        } else { // 没有找到
            System.out.printf("没有找到编号%d 的节点，不能修改\n", newHeroNode.getNo());
        }
    }
}
