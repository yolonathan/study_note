package com.github.nicejing.data.linkedlist;

import lombok.Data;

/**
 * @author Jing Zhi Bao
 */
@Data
public class Node {

    private int no;
    private String name;
    private String nickname;
    /**
     * 前一个节点
     */
    private Node pre;
    /**
     * 后一个节点
     */
    private Node next;

    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
}
