package com.bestbudget.io;

import java.io.File;

public abstract class LocalFileReader<T> {
    protected String path;
    protected String name;
    protected File file;
    protected T data;
    
    protected LocalFileReader(String path) {
        this.path = path;
        this.file = new File(path);
        this.name = file.getName();
    }
    
    public abstract void read();
    
    public T getData() { return data; }
}
