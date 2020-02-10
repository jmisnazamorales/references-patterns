package com.belatrix.references.patterns.commands;

public class Invoker {

    private Command command;

    public Invoker(Command command){
        this.command = command;
    }

    public Object execute() throws Exception {
        System.out.println("Start: Asynchronous Service Request");
        return command.call();
    }

}
