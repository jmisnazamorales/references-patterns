package com.belatrix.references.patterns.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class URLProcess {

    List<URL> successList =  new ArrayList<>();

    List<String> wrongList =  new ArrayList<>();
}
