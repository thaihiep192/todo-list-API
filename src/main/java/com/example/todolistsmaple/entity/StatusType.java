package com.example.todolistsmaple.entity;

public enum StatusType {
    COMPLETED("completed"),
    PLANNING("planning"),
    PROGRESS("progress"),
    PENDING("pending");



    private  final  String name;

    StatusType(String name) {
        this.name = name;
    }
}
