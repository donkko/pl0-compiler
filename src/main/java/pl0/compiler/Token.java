package pl0.compiler;

import java.io.*;
import java.lang.*;

public class Token {
    int kind;
    String id;
    int value;
    public String toString(){
        if (kind < GetSource.end_of_KeySym)
            return GetSource.keyWdT[kind];
        else if (kind == GetSource.Id)
            return id;
        else //(kind == Num)
            return String.valueOf(value) ;
    }
}
