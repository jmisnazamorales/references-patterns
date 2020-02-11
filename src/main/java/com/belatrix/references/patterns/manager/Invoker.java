package com.belatrix.references.patterns.manager;

import com.belatrix.references.patterns.commands.Command;

public class Invoker<T> {

    public <T> Object execute(Command command) throws Exception {
        return command.call();
    }

}
