package com.github.nicejing.data.linkedlist;

/**
 * @author Jing Zhi Bao
 */
public class SingleLinkedList {

    private Node head = new Node(0, "", "");

    public void add(Node node) {

        Node temp = head;
        // 找到最后一个节点
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(node);
    }

    public void addByNo(Node node) {
        Node temp = head;
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break; //
            }
            if (temp.getNext().getNo() > node.getNo()) {
                break;
            } else if (temp.getNext().getNo() == node.getNo()) {
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            System.out.printf("准备插入的英雄的编号%d 已经存在了, 不能加入\n", node.getNo());
        } else {
            node.setNext(temp.getNext());
            temp.setNext(node);
        }
    }


    public void update(Node node) {
        //判断是否空
        if (head.getNext() == null) {
            System.out.println("链表为空~");
            return;
        }

        Node temp = head.getNext();
        //表示是否找到该节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break; //已经遍历完链表
            }
            if (temp.getNo() == node.getNo()) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag) {
            temp.setName(node.getName());
            temp.setName(node.getName());
        } else { //没有找到
            System.out.printf("没有找到编号%d 的节点，不能修改\n", node.getNo());
        }


    }


    public void delete(int no) {
        Node temp = head;
        boolean flag = false;
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            if (temp.getNext().getNo() == no) {
                //找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            //temp 后移，遍历
            temp = temp.getNext();
        }
        //判断flag
        if (flag) {
            // 可以删除
            temp.setNext(temp.getNext().getNext());
        } else {
            System.out.printf("要删除的%d 节点不存在\n", no);
        }
    }

    public void list() {
        //判断链表是否为空
        if (head.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点，不能动，因此我们需要一个辅助变量来遍历
        Node temp = head.getNext();
        while (temp != null) {
            //判断是否到链表最后
            //输出节点的信息
            System.out.println(temp);
            //将temp 后移， 一定小心
            temp = temp.getNext();
        }


    }
}
